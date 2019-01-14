package com.hospital.servlet;

import com.hospital.dao.PatientDao;
import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Rostislav Stakhov
 * Main servlet for patients table data
 */
/*
Сервлет для вывода данных
 */
public class GetIndexPageServlet extends HttpServlet {


    private Map<Integer, Patient> patientsDb;
    private AtomicReference<PatientDao> patientDao;
    private static final Logger logger= LoggerFactory.getLogger(GetIndexPageServlet.class);
    /*
    Метод запуска сервлета
     */
    @Override
    public void init() throws ServletException {

       //Позволяет достать атрибут из ServletContext

        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao=getServletContext().getAttribute("patientDao");



        this.patientsDb = (ConcurrentHashMap<Integer, Patient>) patientsDb;
        this.patientDao=(AtomicReference<PatientDao>)patientDao;


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
            logger.error(e.getMessage());
        }


        req.setAttribute("patientsDb", patientsDb.values());
        //Пересылка на сервлет
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
