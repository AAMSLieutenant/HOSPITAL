package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.dao.ProcedureDao;
import com.hospital.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ProcedureServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<ProcedureDao> procedureDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Procedure> proceduresDb;

    private Date curDate;
    private String year;
    private String month;
    private String date;
    private String fin;

    @Override
    public void init() throws ServletException {


        curDate=new Date();
        year=String.valueOf(curDate.getYear()+1900);
        int m=(curDate.getMonth());
        if(m<10){
            month="0"+String.valueOf(m+1);
        }
        else{
            month=String.valueOf(m+1);
        }
        int d=(curDate.getDate());
        if(d<10){
            date="0"+String.valueOf(d);
        }
        else{
            date=String.valueOf(d);
        }
        fin=year+"-"+month+"-"+date;

        final Object diagDao=getServletContext().getAttribute("diagDao");
        final Object procedureDao=getServletContext().getAttribute("procedureDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.procedureDao=(AtomicReference<ProcedureDao>)procedureDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.proceduresDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        System.out.println("-----------------------------------------");
        System.out.println("ProcedureServlet doPost() is started;");
        System.out.println("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String procId=req.getParameter("procId");
        final String procDate=req.getParameter("procDate");


        System.out.println("ProcedureServlet doPost() Chosen STRING diagId:"+diagId);
        System.out.println("ProcedureServlet doPost() Chosen STRING procId:"+procId);
        System.out.println("ProcedureServlet doPost() Chosen STRING procDate:"+procDate);

        java.util.Date pDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            pDate=sdf.parse(procDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("procDate: "+pDate);

        try {
            this.procedureDao.get().createDiagProc(Integer.parseInt(diagId), pDate, Integer.parseInt(procId));
        }
        catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("-----------------------------------------");
        System.out.println("ProcedureServlet doPost() is finished;");
        System.out.println("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("-----------------------------------------");
        System.out.println("ProcedureServlet doGet() is started;");
        System.out.println("-----------------------------------------");
        this.diagnosesDb.clear();
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Diagnosis> diags = this.diagDao.get().getAllById(pCardId);
            for(int i=0;i<diags.size();i++){
                this.diagnosesDb.put(diags.get(i).getDiagId(), diags.get(i));
            }
            req.setAttribute("diagnosesDb", diagnosesDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try {
            List<Procedure> procs = this.procedureDao.get().getAll();

            System.out.println("procs size: "+procs.size());
            for(int i=0;i<procs.size();i++){
//                System.out.println("i: "+i+" operId: "+opers.get(i).getOperId());
                this.proceduresDb.put(procs.get(i).getProcId(), procs.get(i));
            }
//            System.out.println("OperationsDb size: "+this.operationsDb.size());
            req.setAttribute("proceduresDb", proceduresDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }


        req.setAttribute("fin", fin);
        req.setAttribute("pCardId", pCardId);

        System.out.println("-----------------------------------------");
        System.out.println("Procedureervlet doGet() is finished;");
        System.out.println("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/procedure.jsp").forward(req, resp);

    }
}
