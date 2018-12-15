package com.hospital.servlet;

import com.hospital.model.Patient;
import com.hospital.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
Сервлет для вывода данных
 */
public class GetIndexPageServlet extends HttpServlet {

    private Map<Integer, User> users;
    private Map<Integer, Patient> patients;

    /*
    Метод запуска сервлета
     */
    @Override
    public void init() throws ServletException {

       //Позволяет достать атрибут из ServletContext
        final Object users = getServletContext().getAttribute("users");
        final Object patients = getServletContext().getAttribute("patients");

        if (users == null || !(users instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

        if (patients == null || !(patients instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patients = (ConcurrentHashMap<Integer, Patient>) patients;
        }

    }

    /*
    Метод вывода данных
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        req.setAttribute("users", users.values());
//        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);

        req.setAttribute("patients", patients.values());
        //Пересылка на сервлет
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
