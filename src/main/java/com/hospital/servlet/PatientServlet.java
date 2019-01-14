package com.hospital.servlet;

import com.hospital.dao.*;


import com.hospital.model.*;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


public class PatientServlet extends HttpServlet {

    private Integer currentId=0;//Айди выбранного пациента
    private Map<Integer, Patient> patientsDb;//Массив с пациентами
    private Map<Integer, Appointment> appointmentsDb;//Массив с приемами у врача данного пациента
    private AtomicReference<AppointmentDao> appointmentDao;//ДАО для работы с приемами
    private Map<Integer, Diagnosis> diagnosesDb;
    private AtomicReference<DiagDao> diagDao;
    private Map<Integer, OperInfo> operInfosDb;
    private Map<Integer, MedInfo> medInfosDb;
    private Map<Integer, ProcInfo> procInfosDb;
    private AtomicReference<OperationDao> operationDao;
    private AtomicReference<MedicineDao> medicineDao;
    private AtomicReference<ProcedureDao> procedureDao;
    private AtomicReference<PatientDao> patientDao;//ДАО для работы с пациентом
    private boolean isApp=false;//Поле для проверки наличия приемов конкретного пациента
    private boolean isDiag=false;
    private boolean operMedPro=false;
    private int operSize=0;
    private int medSize=0;
    private int procSize=0;
    private Date curDate;
    private String year;
    private String month;
    private String date;
    private String fin;
    private List<Boolean> dones;
    private int discharge=1;

    @Override
    public void init() throws ServletException {


        dones=new ArrayList<>();
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
        System.out.println(fin);

        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao = getServletContext().getAttribute("patientDao");
        final Object appointmentDao = getServletContext().getAttribute("appointmentDao");
        final Object diagDao = getServletContext().getAttribute("diagDao");
        final Object operationDao=getServletContext().getAttribute("operationDao");
        final Object medicineDao=getServletContext().getAttribute("medicineDao");
        final Object procedureDao=getServletContext().getAttribute("procedureDao");

        //Получаем из контекста и берем копии
        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
            this.patientDao=(AtomicReference<PatientDao>) patientDao;
            this.appointmentsDb=new ConcurrentHashMap<>();
            this.appointmentDao=(AtomicReference<AppointmentDao>) appointmentDao;
            this.diagDao=(AtomicReference<DiagDao>) diagDao;
            this.diagnosesDb=new ConcurrentHashMap<>();
            this.operationDao=(AtomicReference<OperationDao>) operationDao;
            this.medicineDao=(AtomicReference<MedicineDao>)medicineDao;
            this.procedureDao=(AtomicReference<ProcedureDao>)procedureDao;
            this.operInfosDb=new ConcurrentHashMap<>();
            this.medInfosDb=new ConcurrentHashMap<>();
            this.procInfosDb=new ConcurrentHashMap<>();

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
        System.out.println("----------------------------------");
        System.out.println("PatientServlet doGet()");
        System.out.println("----------------------------------");
        req.setCharacterEncoding("UTF-8");
        operSize=0;
        medSize=0;
        procSize=0;
        discharge=1;

        final String pCardId=req.getParameter("pCardId");
        final Patient patient=patientsDb.get(Integer.parseInt(pCardId));
        req.setAttribute("pCardId", pCardId);
        req.setAttribute("patient", patient);
        this.currentId=Integer.parseInt(pCardId);
        System.out.println("pCardId: "+pCardId);

        try {
            List<Appointment> apps = appointmentDao.get().getAllById(this.currentId);
            if (apps.size() != 0) {
                System.out.println("PatientServlet appointmentDao.getAllById() has info");
                System.out.println("apps.size(): " + apps.size());
                isApp = true;
                this.appointmentsDb.clear();
                for (int i = 0; i < apps.size(); i++) {

                    this.appointmentsDb.put(apps.get(i).getAppId(), apps.get(i));
                }
                req.setAttribute("appointmentsDb", appointmentsDb.values());


            } else {
                isApp = false;
                System.out.println("PatientServlet appointmentDao.getAllById() IS NULL");
            }

            List<Diagnosis> diags = diagDao.get().getAllById(this.currentId);
            if (diags.size() != 0) {
                System.out.println("PatientServlet diagDao.getAllById() has info");
                System.out.println("diags.size(): " + diags.size());
                isDiag = true;
                this.diagnosesDb.clear();
                for (int i = 0; i < diags.size(); i++) {

                    this.diagnosesDb.put(diags.get(i).getDiagId(), diags.get(i));
                }
                req.setAttribute("diagnosesDb", diagnosesDb.values());
            } else {
                isDiag = false;
                System.out.println("PatientServlet diagDao.getAllById() IS NULL");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        if((isDiag==true)&&(isApp==true))
        {
            operMedPro=true;
        }
        else{
            operMedPro=false;
        }
        System.out.println("-------------------------PatientServlet isApp=="+isApp+"---------------------------");
        System.out.println("-------------------------PatientServlet isDiag=="+isDiag+"---------------------------");
        System.out.println("-------------------------PatientServlet OperMEdPro=="+operMedPro+"---------------------------");
            if(operMedPro==true) {

                try {
                    List<OperInfo> operInfos=new ArrayList<OperInfo>();
                    if(this.operationDao.get().getAllDiagOper(this.currentId)!=null) {
                        operInfos = this.operationDao.get().getAllDiagOper(this.currentId);
                    }
                    operSize=operInfos.size();
                    if(operSize>0) {
//                    req.setAttribute("operSize",operSize);

                        operationDao.get().finishOperation(operInfos);

                        for(int i=0;i<operInfos.size();i++) {
                            dones.add(operInfos.get(i).isOperDone());
                        }

                        this.operInfosDb.clear();
                        for (int i = 0; i < operInfos.size(); i++) {
                            this.operInfosDb.put(i, operInfos.get(i));
                        }
                        req.setAttribute("operInfosDb", operInfosDb.values());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    List<MedInfo> medInfos=new ArrayList<MedInfo>();
                    if(this.medicineDao.get().getAllDiagMed(this.currentId)!=null) {
                       medInfos = this.medicineDao.get().getAllDiagMed(this.currentId);
                    }
                    medSize=medInfos.size();

                    if(medSize>0) {

                        medicineDao.get().finishMedicine(medInfos);

                        for(int i=0;i<medInfos.size();i++) {
                            dones.add(medInfos.get(i).isMedDone());
                        }
                        this.medInfosDb.clear();
                        for (int i = 0; i < medInfos.size(); i++) {
                            this.medInfosDb.put(i, medInfos.get(i));
                        }
                        req.setAttribute("medInfosDb", medInfosDb.values());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    List<ProcInfo> procInfos=new ArrayList<ProcInfo>();
                    procInfos = this.procedureDao.get().getAllDiagProc(this.currentId);
                    procSize=procInfos.size();

                    if(procSize>0) {

                        procedureDao.get().finishProcedure(procInfos);

                        for(int i=0;i<procInfos.size();i++) {
                            dones.add(procInfos.get(i).isProcDone());
                        }
                        this.procInfosDb.clear();
                        for (int i = 0; i < procInfos.size(); i++) {
                            this.procInfosDb.put(i, procInfos.get(i));
                        }
                        req.setAttribute("procInfosDb", procInfosDb.values());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            if(dones.size()!=0) {
                for (int i = 0; i < dones.size(); i++) {
                    if (dones.get(i) == false) {
                        discharge = 0;
                    }
                }
            }
            dones=new ArrayList<>();


        req.setAttribute("isApp", isApp);
        req.setAttribute("isDiag", isDiag);
        req.setAttribute("operMedPro", operMedPro);
        req.setAttribute("operSize",operSize);
        req.setAttribute("medSize",medSize);
        req.setAttribute("procSize",procSize);
        req.setAttribute("fin", fin);
        req.setAttribute("discharge", discharge);

        if(patient.getpDischargeDate()==null){
            req.getRequestDispatcher("/WEB-INF/view/patient.jsp")
                    .forward(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/patientDis.jsp")
                .forward(req, resp);

    }
}

