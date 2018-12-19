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

public class UpdateUserServlet extends HttpServlet {


    private Map<Integer, Patient> patients;
    private Map<Integer, Patient> patientsDb;
    private IPatientDao patientDao;
    private Integer currentId=0;

    @Override
    public void init() throws ServletException {

//        final Object users = getServletContext().getAttribute("users");
        final Object patients = getServletContext().getAttribute("patients");
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
            this.patientDao=(IPatientDao) patientDao;

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

        System.out.println("Update doPost() Chosen STRING pName:"+pName);
        System.out.println("Update doPost() Chosen STRING pSurname:"+pSurname);
        System.out.println("Update doPost() Chosen STRING pPatronymic:"+pPatronymic);
        System.out.println("Update doPost() Chosen STRING cardId:"+pCardId);
        Patient patient = patientsDb.get(Integer.parseInt(pCardId));

        patient.setpName(pName);
        patient.setpSurname(pSurname);
        patient.setpPatronymic(pPatronymic);
        try {
            patientDao.update(Integer.parseInt(pCardId), patient);
        }
        catch(Exception e){
            e.printStackTrace();
        }


        resp.sendRedirect(req.getContextPath() + "/read");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        final String id = req.getParameter("id");
        final String pCardId=req.getParameter("pCardId");
//        if (Utils.idIsInvalid(id, users)) {
//            resp.sendRedirect(req.getContextPath() + "/");
//            return;
//        }


        final Patient patient=patientsDb.get(Integer.parseInt(pCardId));
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/view/update.jsp")
                .forward(req, resp);
    }
}
