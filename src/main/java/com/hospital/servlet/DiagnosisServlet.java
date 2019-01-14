package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.model.Diagnosis;
import com.hospital.model.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class DiagnosisServlet extends HttpServlet {


    private Integer pCardId=0;
    private AtomicReference<DiagDao> diagDao;
    private Map<Integer, Employee> doctorsDb;




    @Override
    public void init() throws ServletException {

        final Object diagDao=getServletContext().getAttribute("diagDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.doctorsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        System.out.println("----------------------------------");
        System.out.println("DiagnosisServlet doPost()");
        req.setCharacterEncoding("UTF-8");
//
        final String diagName=req.getParameter("diagName");
//
        System.out.println("diagnosis doPost() STRING diagName:"+diagName);


        Diagnosis diagnosis=new Diagnosis();
        diagnosis.setCardId(pCardId);
        diagnosis.setDiagName(diagName);
        try{
            diagDao.get().create(diagnosis);
        }
        catch (Exception e){
            e.printStackTrace();
        }


//

        req.setAttribute("pCardId", pCardId);
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("----------------------------------");
        System.out.println("DiagnosisServlet doGet()");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        System.out.println("CURRENT PATIENT`S CARD ID:"+pCardId);

        req.setAttribute("pCardId", pCardId);

        req.getRequestDispatcher("/WEB-INF/view/diagnosis.jsp").forward(req, resp);
//
    }
}
