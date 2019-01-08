package com.hospital.servlet;

import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Employee;
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

public class AppointmentServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<IAppointmentDao> appointmentDao;
    private Map<Integer, Employee> doctorsDb;
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


        final Object appointmentDao=getServletContext().getAttribute("appointmentDao");
        this.appointmentDao=(AtomicReference<IAppointmentDao>)appointmentDao;
        this.doctorsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doPost() is started;");
        System.out.println("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String appDate=req.getParameter("appDate");
        final String appValue=req.getParameter("appValue");
        final String appComplaint=req.getParameter("appComplaint");
        final String docId=req.getParameter("docId");

        System.out.println("appointment doPost() Chosen STRING appDate:"+appDate);
        System.out.println("appointment doPost() Chosen STRING appValue:"+appValue);
        System.out.println("appointment doPost() Chosen STRING appComplaint:"+appComplaint);
        System.out.println("appointment doPost() Chosen STRING docId:"+docId);


        Appointment appointment=new Appointment();
        appointment.setCardId(pCardId);
        appointment.setDocId(Integer.parseInt(docId));
        appointment.setAppValue(Integer.parseInt(appValue));
        appointment.setAppComplaint(appComplaint);
        Date d=null;
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        try {
            d=sdf.parse(appDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        appointment.setAppDate(d);
        try{
            appointmentDao.get().create(appointment);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        req.setAttribute("pCardId", pCardId);
        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doPost() is finished;");
        System.out.println("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doGet() is started;");
        System.out.println("-----------------------------------------");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Employee> emps = this.appointmentDao.get().getDoctors();
            for(int i=0;i<emps.size();i++){
                this.doctorsDb.put(emps.get(i).getEmpId(), emps.get(i));
            }
            req.setAttribute("doctorsDb", doctorsDb.values());
        }
        catch(Exception e){
            e.printStackTrace();
        }

//        resp.sendRedirect(req.getContextPath() + "/appointment");
        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doGet() is finished;");
        System.out.println("-----------------------------------------");

        req.setAttribute("fin", fin);
        req.getRequestDispatcher("/WEB-INF/view/appointment.jsp").forward(req, resp);

    }
}
