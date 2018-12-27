package com.hospital.servlet;

import com.hospital.dao.AppointmentDao;
import com.hospital.interfaces.IAppointmentDao;
import com.hospital.interfaces.IPatientDao;
import com.hospital.model.Appointment;
import com.hospital.model.Patient;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


public class PatientServlet extends HttpServlet {

    private Integer currentId=0;//Айди выбранного пациента
    private Map<Integer, Patient> patientsDb;//Массив с пациентами
    private Map<Integer, Appointment> appointmentsDb;//Массив с приемами у врача данного пациента
    private AtomicReference<IAppointmentDao> appointmentDao;//ДАО для работы с приемами
    private AtomicReference<IPatientDao> patientDao;//ДАО для работы с пациентом
    private boolean isApp=false;//Поле для проверки наличия приемов конкретного пациента


    @Override
    public void init() throws ServletException {



        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao = getServletContext().getAttribute("patientDao");
        final Object appointmentDao = getServletContext().getAttribute("appointmentDao");

        //Получаем из контекста и берем копии
        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
            this.patientDao=(AtomicReference<IPatientDao>) patientDao;
            this.appointmentsDb=new ConcurrentHashMap<>();
            this.appointmentDao=(AtomicReference<IAppointmentDao>) appointmentDao;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("PatientServlet doGet()");
        req.setCharacterEncoding("UTF-8");

        String[] arr={"one", "two", "three"};
        req.setAttribute("arr", arr);

        final String pCardId=req.getParameter("pCardId");
        final Patient patient=patientsDb.get(Integer.parseInt(pCardId));
        req.setAttribute("pCardId", pCardId);
        req.setAttribute("patient", patient);
        this.currentId=Integer.parseInt(pCardId);
        System.out.println("pCardId: "+pCardId);
        try {
            List<Appointment> apps = appointmentDao.get().getAllById(this.currentId);
            if(apps.size()!=0){
                System.out.println("PatientServlet appointmentDao.getAllById() has info");
                System.out.println("apps.size(): "+apps.size());
                isApp=true;
                for(int i=0;i<apps.size();i++){

                    this.appointmentsDb.put(apps.get(i).getAppId(), apps.get(i));
                }
                req.setAttribute("appointmentsDb", appointmentsDb.values());
            }
            else{
                isApp=false;
                System.out.println("PatientServlet appointmentDao.getAllById() IS NULL");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        req.setAttribute("isApp", isApp);


        req.getRequestDispatcher("/WEB-INF/view/patient.jsp")
                .forward(req, resp);

    }
}

