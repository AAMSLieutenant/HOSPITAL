package com.hospital.servlet;

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

public class DeleteUserServlet extends HttpServlet {

    private Map<Integer, User> users;
    private Map<Integer, Patient> patients;

    @Override
    public void init() throws ServletException {

//        final Object users = getServletContext().getAttribute("users");
        final Object patients = getServletContext().getAttribute("patients");

//        if (users == null || !(users instanceof ConcurrentHashMap)) {
//
//            throw new IllegalStateException("You're repo does not initialize!");
//        } else {
//
//            this.users = (ConcurrentHashMap<Integer, User>) users;
//        }

        if (patients == null || !(patients instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patients = (ConcurrentHashMap<Integer, Patient>) patients;
            System.out.println("Patients size in delete:"+this.patients.size());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

//        if (Utils.idIsNumber(req)) {
//            users.remove(Integer.valueOf(req.getParameter("id")));
//        }
            System.out.println("Patient id in DELETE doPost():"+req.getParameter("pCardId"));
            patients.remove(Integer.valueOf(req.getParameter("pCardId")));


        resp.sendRedirect(req.getContextPath() + "/");
    }
}
