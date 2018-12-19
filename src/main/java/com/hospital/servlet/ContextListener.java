package com.hospital.servlet;


import com.hospital.dao.UserDAO;
import com.hospital.model.User;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static com.hospital.model.User.ROLE.ADMIN;
import static com.hospital.model.User.ROLE.USER;
/*
Эта сущность реализует область видимости всего приложения (максимум)
Здесь лежат данные для всего приложения (видимые для всех сервлетов)
Это необходимо сделать до загрузки всех сервлетов.
1. Реализовать интерфейс ServletContextListener
2. Добавить аннтоацию @WebListener
 */

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, Patient> patientsDb;
    private DaoFactory factory;
    private IPatientDao patientDao;
    private AtomicReference<UserDAO> dao;

    /*
    Когда приложение запускается
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Locale.setDefault(Locale.US);

        dao = new AtomicReference<>(new UserDAO());

        dao.get().add(new User(1, "Pavel", "1", ADMIN));
        dao.get().add(new User(2, "Egor", "1", USER));



        this.patientsDb=new ConcurrentHashMap<>();
        final ServletContext servletContext =
                servletContextEvent.getServletContext();


        servletContext.setAttribute("dao", dao);
        this.factory=new OracleDaoFactory();
        try {
            this.patientDao = factory.getPatientDao();
            List<Patient> pats=patientDao.getAll();

            for(int i=0;i<pats.size();i++){
                this.patientsDb.put(pats.get(i).getpCardId(),pats.get(i));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*
        Надо для многопоточности
         */

        /*
        Передача данных по ключу для остальных сервлетов
         */

        servletContext.setAttribute("factory", factory);
        servletContext.setAttribute("patientDao", patientDao);
        servletContext.setAttribute("patientsDb", patientsDb);




//        final Patient patient=new Patient(1,"Name1","Surname1","Patro1","Male");
//        final Patient patient2=new Patient(2,"Name2","Surname2","PAtro2","Male");
//        final Patient patient3=new Patient(3,"Name3","Surname3","Patro3","Male");
//        this.patients.put(patient.getpCardId(),patient);
//        this.patients.put(patient2.getpCardId(),patient2);
//        this.patients.put(patient3.getpCardId(),patient3);
//        System.out.println("PATIENTS "+this.patients.size());


    }


    /*
    Когда приложение завершается
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close recourse.
        patientsDb=null;
        dao=null;
    }
}