package com.hospital.servlet;


import com.hospital.dao.PatientDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DischargeServlet extends HttpServlet {


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
        System.out.println("----------------------------------");
        System.out.println("DischargeServlet doGet()");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("DISCHARGE PATIENT`S CARD ID:"+pCardId);
        try {
            this.patientDao.get().discharge(pCardId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/read");
    }
}
