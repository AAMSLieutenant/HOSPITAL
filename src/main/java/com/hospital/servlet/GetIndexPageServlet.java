package com.hospital.servlet;

import com.hospital.dao.OracleDaoFactory;
import com.hospital.interfaces.DaoFactory;
import com.hospital.interfaces.IPatientDao;
import com.hospital.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
Сервлет для вывода данных
 */
public class GetIndexPageServlet extends HttpServlet {


    private Map<Integer, Patient> patients;
    private Map<Integer, Patient> patientsDb;

    /*
    Метод запуска сервлета
     */
    @Override
    public void init() throws ServletException {

       //Позволяет достать атрибут из ServletContext

        final Object patientsDb = getServletContext().getAttribute("patientsDb");

        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
        }

    }

    /*
    Метод вывода данных
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        req.setAttribute("patientsDb", patientsDb.values());
        //Пересылка на сервлет
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
