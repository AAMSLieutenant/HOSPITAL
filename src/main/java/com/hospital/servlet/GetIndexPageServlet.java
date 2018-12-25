package com.hospital.servlet;

import com.hospital.dao.OracleDaoFactory;
import com.hospital.dao.PatientDao;
import com.hospital.dao.UserDAO;
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
import java.util.concurrent.atomic.AtomicReference;

/*
Сервлет для вывода данных
 */
public class GetIndexPageServlet extends HttpServlet {


    private Map<Integer, Patient> patientsDb;
    private AtomicReference<UserDAO> dao;
    private AtomicReference<PatientDao> patientDao;
    /*
    Метод запуска сервлета
     */
    @Override
    public void init() throws ServletException {

       //Позволяет достать атрибут из ServletContext

        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object dao=getServletContext().getAttribute("dao");
        final Object patientDao=getServletContext().getAttribute("patientDao");

        if (patientsDb == null || !(patientsDb instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
            this.dao=(AtomicReference<UserDAO>)dao;
            this.patientDao=(AtomicReference<PatientDao>)patientDao;
        }

    }

    /*
    Метод вывода данных
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            List<Patient> pats=patientDao.get().getAll();
            patientsDb.clear();
            for(int i=0;i<pats.size();i++){
                this.patientsDb.put(pats.get(i).getpCardId(),pats.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            List<Patient> pats=patientDao.get
//
//            for(int i=0;i<pats.size();i++){
//                this.patientsDb.put(pats.get(i).getpCardId(),pats.get(i));
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }


        req.setAttribute("patientsDb", patientsDb.values());
        req.setAttribute("dao", dao);
        //Пересылка на сервлет
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
