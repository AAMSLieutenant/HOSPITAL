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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
Эта сущность реализует область видимости всего приложения (максимум)
Здесь лежат данные для всего приложения (видимые для всех сервлетов)
Это необходимо сделать до загрузки всех сервлетов.
1. Реализовать интерфейс ServletContextListener
2. Добавить аннтоацию @WebListener
 */

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, Patient> patients;
    private Map<Integer, Patient> patientsDb;


    /*
    Когда приложение запускается
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Locale.setDefault(Locale.US);
        patientsDb=new ConcurrentHashMap<>();
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        DaoFactory factory=new OracleDaoFactory();
        IPatientDao patientDao;
        try {
            patientDao = factory.getPatientDao();
//            patientDao.read(1);
            List<Patient> pats=patientDao.getAll();

            for(int i=0;i<pats.size();i++){
//                patientsDb.put(pats.get(i));
            }
        }
        catch(Exception e){
//            e.printStackTrace();
        }
        /*
        Надо для многопоточности
         */

        patients=new ConcurrentHashMap<>();

        /*
        Передача данных по ключу для остальных сервлетов
         */

        servletContext.setAttribute("patients", patients);
        servletContext.setAttribute("patientsDb", patientsDb);




        final Patient patient=new Patient(1,"Name1","Surname1","Patro1","Male");
        final Patient patient2=new Patient(2,"Name2","Surname2","PAtro2","Male");
        final Patient patient3=new Patient(3,"Name3","Surname3","Patro3","Male");
        this.patients.put(patient.getpCardId(),patient);
        this.patients.put(patient2.getpCardId(),patient2);
        this.patients.put(patient3.getpCardId(),patient3);
//        System.out.println("PATIENTS "+this.patients.size());


    }


    /*
    Когда приложение завершается
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close recourse.

        patients=null;
    }
}