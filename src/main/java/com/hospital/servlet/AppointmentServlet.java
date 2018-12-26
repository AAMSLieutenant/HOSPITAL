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


        req.getRequestDispatcher("/WEB-INF/view/appointment.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
