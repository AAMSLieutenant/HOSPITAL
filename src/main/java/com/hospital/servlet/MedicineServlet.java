package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.dao.MedicineDao;
import com.hospital.dao.OperationDao;
import com.hospital.interfaces.IAppointmentDao;
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

public class MedicineServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<MedicineDao> medicineDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Medicine> medicineDb;

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
        final Object medicineDao=getServletContext().getAttribute("medicineDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.medicineDao=(AtomicReference<MedicineDao>)medicineDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.medicineDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        System.out.println("-----------------------------------------");
        System.out.println("MedicineServlet doPost() is started;");
        System.out.println("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String operId=req.getParameter("medId");
        final String medEnd=req.getParameter("medEnd");


        System.out.println("operationServlet doPost() Chosen STRING diagId:"+diagId);
        System.out.println("operationServlet doPost() Chosen STRING operId:"+operId);
        System.out.println("operationServlet doPost() Chosen STRING medEnd:"+medEnd);

        java.util.Date mStart=curDate;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: "+mStart);


        java.util.Date mEnd=null;
        try {
            mEnd=sdf.parse(medEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("endDate: "+mEnd);

        try {
            this.medicineDao.get().createDiagMed(Integer.parseInt(diagId), mStart, mEnd, Integer.parseInt(operId));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("-----------------------------------------");
        System.out.println("MedicineServlet doPost() is finished;");
        System.out.println("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("-----------------------------------------");
        System.out.println("Medicine doGet() is started;");
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
            List<Medicine> meds = this.medicineDao.get().getAll();

            System.out.println("meds size: "+meds.size());
            for(int i=0;i<meds.size();i++){
//                System.out.println("i: "+i+" operId: "+opers.get(i).getOperId());
                this.medicineDb.put(meds.get(i).getMedId(), meds.get(i));
            }
            System.out.println("medicineDb size: "+this.medicineDb.size());
            req.setAttribute("medicineDb", medicineDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }


//

        req.setAttribute("fin", fin);
        req.setAttribute("pCardId", pCardId);


        System.out.println("-----------------------------------------");
        System.out.println("MedicineServlet doGet() is finished;");
        System.out.println("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/medicine.jsp").forward(req, resp);

    }
}
