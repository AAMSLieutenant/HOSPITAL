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
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class DeletePatientServlet extends HttpServlet {


    private Map<Integer, Patient> patientsDb;
//    private IPatientDao patientDao;
    private AtomicReference<IPatientDao> patientDao;
    @Override
    public void init() throws ServletException {


        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao=getServletContext().getAttribute("patientDao");



        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
            System.out.println("Patients size in delete:"+this.patientsDb.size());
            this.patientDao=(AtomicReference<IPatientDao>)patientDao;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

//        if (Utils.idIsNumber(req)) {
//            users.remove(Integer.valueOf(req.getParameter("id")));
//        }
            System.out.println("Patient id in DELETE doPost():"+req.getParameter("pCardId"));
            this.patientsDb.remove(Integer.valueOf(req.getParameter("pCardId")));
            try {
                this.patientDao.get().delete(Integer.valueOf(req.getParameter("pCardId")));
            }
            catch(Exception e){
                e.printStackTrace();
            }

        resp.sendRedirect(req.getContextPath() + "/read");
    }
}
