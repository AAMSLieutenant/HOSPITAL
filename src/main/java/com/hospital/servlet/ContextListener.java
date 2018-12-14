package com.hospital.servlet;

import com.hospital.dao.PatientDao;
import com.hospital.interfaces.DaoFactory;
import com.hospital.interfaces.IPatientDao;
import com.hospital.model.User;
import com.hospital.util.Utils;
import com.hospital.model.Patient;
import com.hospital.dao.OracleDaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, User> users;
    private Map<Integer, Patient> patients;



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        DaoFactory factory=new OracleDaoFactory();
        try {
            IPatientDao patientDao = factory.getPatientDao();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        users = new ConcurrentHashMap<>();
        patients=new ConcurrentHashMap<>();

        servletContext.setAttribute("users", users);
        servletContext.setAttribute("patients", patients);

        final User user = Utils.createStubUser(1, "Первый", 10);
        this.users.put(user.getId(), user);
        final Patient patient=new Patient(00003,"Name2","Surname2","PAtro","Male");
        final Patient patient2=new Patient(00001,"Name","Surname","PAtro","Male");
        this.patients.put(patient.getCardId(),patient);
        this.patients.put(patient2.getCardId(),patient2);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close recourse.
        users = null;
        patients=null;
    }
}