package com.hospital.servlet;

import com.hospital.interfaces.IPatientDao;
import com.hospital.model.User;
import com.hospital.util.Utils;
import com.hospital.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class CreatePatientServlet extends HttpServlet {


    private Map<Integer, Patient> patients;
    private Map<Integer, Patient> patientsDb;
//    private IPatientDao patientDao;
    private AtomicReference<IPatientDao> patientDao;
    private Integer currentId=0;

    @Override
    public void init() throws ServletException {


        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao = getServletContext().getAttribute("patientDao");


//        if (users == null || !(users instanceof ConcurrentHashMap)) {
//
//            throw new IllegalStateException("You're repo does not initialize!");
//        } else {
//
//            this.users = (ConcurrentHashMap<Integer, User>) users;
//        }

        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
            this.patientDao=(AtomicReference<IPatientDao>) patientDao;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");



        final String pCardId = req.getParameter("pCardId");
        final String pName = req.getParameter("pName");
        final String pSurname = req.getParameter("pSurname");
        final String pPatronymic = req.getParameter("pPatronymic");
        final String pSex = req.getParameter("pSex");
        final String pBirthDate = req.getParameter("pBirthDate");
        final String pArrivalDate = req.getParameter("pArrivalDate");
//
        System.out.println("CreatePatient doPost() Chosen STRING pName:"+pName);
        System.out.println("CreatePatient doPost() Chosen STRING pSurname:"+pSurname);
        System.out.println("CreatePatient doPost() Chosen STRING pPatronymic:"+pPatronymic);
        System.out.println("CreatePatient doPost() Chosen STRING pSex:"+pSex);
        System.out.println("CreatePatient doPost() Chosen STRING pBirthDate:"+pBirthDate);
        System.out.println("CreatePatient doPost() Chosen STRING pArrivalDate:"+pArrivalDate);
//        Patient patient = patientsDb.get(Integer.parseInt(pCardId));
//
//        patient.setpName(pName);
//        patient.setpSurname(pSurname);
//        patient.setpPatronymic(pPatronymic);
//        try {
//            patientDao.update(Integer.parseInt(pCardId), patient);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }


        resp.sendRedirect(req.getContextPath() + "/read");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        final String id = req.getParameter("id");
//        final String pCardId=req.getParameter("pCardId");
//        if (Utils.idIsInvalid(id, users)) {
//            resp.sendRedirect(req.getContextPath() + "/");
//            return;
//        }


//        final Patient patient=patientsDb.get(Integer.parseInt(pCardId));
//        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/view/createPatient.jsp")
                .forward(req, resp);
    }
}