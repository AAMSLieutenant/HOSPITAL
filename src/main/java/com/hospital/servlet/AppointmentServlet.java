package com.hospital.servlet;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


/**
 * @author Rostislav Stakhov
 * Servlet for creating patient appointments
 */
public class AppointmentServlet extends HttpServlet {


    private Integer pCardId=0;
    private static final Logger logger= LoggerFactory.getLogger(AppointmentServlet.class);
    private AtomicReference<AppointmentDao> appointmentDao;
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
        this.appointmentDao=(AtomicReference<AppointmentDao>)appointmentDao;
        this.doctorsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doPost() is started;");
        logger.info("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String appDate=req.getParameter("appDate");
        final String appValue=req.getParameter("appValue");
        final String appComplaint=req.getParameter("appComplaint");
        final String docId=req.getParameter("docId");

        logger.info("appointment doPost() Chosen STRING appDate:"+appDate);
        logger.info("appointment doPost() Chosen STRING appValue:"+appValue);
        logger.info("appointment doPost() Chosen STRING appComplaint:"+appComplaint);
        logger.info("appointment doPost() Chosen STRING docId:"+docId);


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
            logger.error(e.getMessage());
        }
        appointment.setAppDate(d);
        try{
            appointmentDao.get().create(appointment);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

        req.setAttribute("pCardId", pCardId);
        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doPost() is finished;");
        logger.info("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doGet() is started;");
        logger.info("-----------------------------------------");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        logger.info("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Employee> emps = this.appointmentDao.get().getDoctors();
            for(int i=0;i<emps.size();i++){
                this.doctorsDb.put(emps.get(i).getEmpId(), emps.get(i));
            }
            req.setAttribute("doctorsDb", doctorsDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doGet() is finished;");
        logger.info("-----------------------------------------");

        req.setAttribute("fin", fin);
        req.setAttribute("pCardId", pCardId);
        req.getRequestDispatcher("/WEB-INF/view/appointment.jsp").forward(req, resp);

    }
}
