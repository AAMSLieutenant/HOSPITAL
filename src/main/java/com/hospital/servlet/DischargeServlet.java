package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.dao.PatientDao;
import com.hospital.dao.UserDAO;
import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Diagnosis;
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

public class DischargeServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<PatientDao> patientDao;




    @Override
    public void init() throws ServletException {

        final Object patientDao=getServletContext().getAttribute("patientDao");
        this.patientDao=(AtomicReference<PatientDao>)patientDao;

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("----------------------------------");
        System.out.println("DischargeServlet doGet()");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("DISCHARGE PATIENT`S CARD ID:"+pCardId);
        try {
            this.patientDao.get().discharge(pCardId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

//        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/read");
    }
}
