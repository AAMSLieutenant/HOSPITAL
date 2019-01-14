package com.hospital.servlet;


import com.hospital.dao.PatientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Rostislav Stakhov
 * Servlet for discharging the patient
 */
public class DischargeServlet extends HttpServlet {


    private static final Logger logger= LoggerFactory.getLogger(DischargeServlet.class);
    private Integer pCardId=0;
    private AtomicReference<PatientDao> patientDao;




    @Override
    public void init() throws ServletException {

        final Object patientDao=getServletContext().getAttribute("patientDao");
        this.patientDao=(AtomicReference<PatientDao>)patientDao;

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("----------------------------------");
        logger.info("DischargeServlet doGet()");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        logger.info("DISCHARGE PATIENT`S CARD ID:"+pCardId);
        try {
            this.patientDao.get().discharge(pCardId);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/read");
    }
}
