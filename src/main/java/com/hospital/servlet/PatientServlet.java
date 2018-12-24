package com.hospital.servlet;

import com.hospital.interfaces.IPatientDao;
import com.hospital.model.Patient;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


public class PatientServlet extends HttpServlet {

    private Integer currentId=0;
    private Map<Integer, Patient> patientsDb;
//    private IPatientDao patientDao;
    private AtomicReference<IPatientDao> patientDao;


    @Override
    public void init() throws ServletException {

        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao = getServletContext().getAttribute("patientDao");


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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String pCardId=req.getParameter("pCardId");
//        if (Utils.idIsInvalid(id, users)) {
//            resp.sendRedirect(req.getContextPath() + "/");
//            return;
//        }


        final Patient patient=patientsDb.get(Integer.parseInt(pCardId));
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/view/patient.jsp")
                .forward(req, resp);
        this.currentId=Integer.parseInt(pCardId);
    }
}

