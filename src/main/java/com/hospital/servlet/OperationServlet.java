package com.hospital.servlet;

import com.hospital.dao.AppointmentDao;
import com.hospital.dao.DiagDao;
import com.hospital.dao.OperationDao;
import com.hospital.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class OperationServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<AppointmentDao> appointmentDao;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<OperationDao> operationDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Operation> operationsDb;
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
        final Object operationDao=getServletContext().getAttribute("operationDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.operationDao=(AtomicReference<OperationDao>)operationDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.operationsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doPost() is started;");
        System.out.println("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String operId=req.getParameter("operId");
        final String operDate=req.getParameter("operDate");


        System.out.println("operationServlet doPost() Chosen STRING diagId:"+diagId);
        System.out.println("operationServlet doPost() Chosen STRING operId:"+operId);
        System.out.println("operationServlet doPost() Chosen STRING operDate:"+operDate);

        java.util.Date oDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            oDate=sdf.parse(operDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("operDate: "+oDate);

        try {
            this.operationDao.get().createDiagOper(Integer.parseInt(diagId), oDate, Integer.parseInt(operId));
        }
        catch(Exception e){
            e.printStackTrace();
        }




//);
//
        System.out.println("-----------------------------------------");
        System.out.println("OperationServlet doPost() is finished;");
        System.out.println("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.diagnosesDb.clear();

        System.out.println("-----------------------------------------");
        System.out.println("OperationServlet doGet() is started;");
        System.out.println("-----------------------------------------");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Diagnosis> diags = this.diagDao.get().getAllById(pCardId);
            for(int i=0;i<diags.size();i++){
                this.diagnosesDb.put(diags.get(i).getDiagId(), diags.get(i));
            }
            System.out.println("diagnosesDb size():"+diagnosesDb.size());
            req.setAttribute("diagnosesDb", diagnosesDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try {
            List<Operation> opers = this.operationDao.get().getAll();

//            System.out.println("opers size: "+opers.size());
            for(int i=0;i<opers.size();i++){
//                System.out.println("i: "+i+" operId: "+opers.get(i).getOperId());
                this.operationsDb.put(opers.get(i).getOperId(), opers.get(i));
            }
//            System.out.println("OperationsDb size: "+this.operationsDb.size());
            req.setAttribute("fin", fin);
            req.setAttribute("operationsDb", operationsDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }



//



        req.setAttribute("pCardId", pCardId);
        System.out.println("-----------------------------------------");
        System.out.println("OperationServlet doGet() is finished;");
        System.out.println("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/operation.jsp").forward(req, resp);

    }
}
