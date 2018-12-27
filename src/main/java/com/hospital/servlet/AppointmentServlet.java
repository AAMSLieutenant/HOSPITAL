package com.hospital.servlet;

import com.hospital.dao.PatientDao;
import com.hospital.dao.UserDAO;
import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Employee;
import com.hospital.model.Patient;
import com.hospital.dao.AppointmentDao;
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









    @Override
    public void init() throws ServletException {

        final Object appointmentDao=getServletContext().getAttribute("appointmentDao");
        this.appointmentDao=(AtomicReference<IAppointmentDao>)appointmentDao;
        this.doctorsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        System.out.println("AppointmentServlet doPost()");
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
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
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

//        pCardId=Integer.parseInt(req.getParameter("pCardId"));
//        System.out.println("CURRENT PATIENT ID:"+pCardId);
//        try {
//            List<Employee> emps = this.appointmentDao.get().getDoctors();
//            for(int i=0;i<emps.size();i++){
//                this.doctorsDb.put(emps.get(i).getEmpId(), emps.get(i));
//            }
//            req.setAttribute("doctorsDb", doctorsDb.values());
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        req.setAttribute("pCardId", pCardId);
      resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
//        req.getRequestDispatcher("/WEB-INF/view/patient.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("AppointmentServlet doGet()");
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
        req.getRequestDispatcher("/WEB-INF/view/appointment.jsp").forward(req, resp);

    }
}
