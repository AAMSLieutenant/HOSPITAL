package com.hospital.servlet;

import com.hospital.interfaces.IPatientDao;
import com.hospital.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class CreatePatientServlet extends HttpServlet {


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

            this.patientDao=(AtomicReference<IPatientDao>) patientDao;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("-----------------------------------------");
        System.out.println("CreatePatientServlet doPost() is started;");
        System.out.println("-----------------------------------------");
        req.setCharacterEncoding("UTF-8");

        Date birthDate=null;
        Date arrivalDate=null;

        final String pName = req.getParameter("pName");
        final String pSurname = req.getParameter("pSurname");
        final String pPatronymic = req.getParameter("pPatronymic");
        final String pAge = req.getParameter("pAge");
        final String pSex = req.getParameter("pSex");
        final String pBirthDate = req.getParameter("pBirthDate");
        final String pArrivalDate = req.getParameter("pArrivalDate");
        final String city = req.getParameter("city");
        final String street = req.getParameter("street");
        final String houseNumber = req.getParameter("houseNumber");
        final String flatNumber = req.getParameter("flatNumber");
//
        System.out.println("CreatePatient doPost() Chosen STRING pName:"+pName);
        System.out.println("CreatePatient doPost() Chosen STRING pSurname:"+pSurname);
        System.out.println("CreatePatient doPost() Chosen STRING pPatronymic:"+pPatronymic);
        System.out.println("CreatePatient doPost() Chosen STRING pAge:"+pAge);
        System.out.println("CreatePatient doPost() Chosen STRING pSex:"+pSex);
        System.out.println("CreatePatient doPost() Chosen STRING pBirthDate:"+pBirthDate);
        System.out.println("CreatePatient doPost() Chosen STRING pArrivalDate:"+pArrivalDate);
        System.out.println("ADDRESS:");
        System.out.println("CreatePatient doPost() Chosen STRING city:"+city);
        System.out.println("CreatePatient doPost() Chosen STRING street:"+street);
        System.out.println("CreatePatient doPost() Chosen STRING houseNumber:"+houseNumber);
        System.out.println("CreatePatient doPost() Chosen STRING flatNumber:"+flatNumber);

        Patient patient=new Patient();
        patient.setpName(pName);
        patient.setpSurname(pSurname);
        patient.setpPatronymic(pPatronymic);
        patient.setpAge(Integer.parseInt(pAge));
        patient.setpSex(pSex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        try {
            birthDate=sdf.parse(pBirthDate);
            arrivalDate=sdf.parse(pArrivalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient.setpBirthDate(birthDate);
        patient.setpArrivalDate(arrivalDate);
        patient.getpAddress().setCity(city);
        patient.getpAddress().setStreet(street);
        patient.getpAddress().setHouseNumber(Integer.parseInt(houseNumber));
        patient.getpAddress().setFlatNumber(Integer.parseInt(flatNumber));
        try{
            patientDao.get().create(patient);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("-----------------------------------------");
        System.out.println("AppointmentServlet doGet() is finished;");
        System.out.println("-----------------------------------------");
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
