package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.dao.ProcedureDao;
import com.hospital.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Rostislav Stakhov
 * Servlet for appointing procedures for the patient
 */
public class ProcedureServlet extends HttpServlet {


    private static final Logger logger= LoggerFactory.getLogger(ProcedureServlet.class);
    private Integer pCardId=0;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<ProcedureDao> procedureDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Procedure> proceduresDb;

    private Date curDate;
    private String year;
    private String month;
    private String date;
    private String fin;


    @Override
    public void init() throws ServletException {


        curDate=new Date();
        year=String.valueOf(curDate.getYear()+1900);
        int m=(curDate.getMonth());
        if(m<10){
            month="0"+String.valueOf(m+1);
        }
        else{
            month=String.valueOf(m+1);
        }
        int d=(curDate.getDate());
        if(d<10){
            date="0"+String.valueOf(d);
        }
        else{
            date=String.valueOf(d);
        }
        fin=year+"-"+month+"-"+date;

        final Object diagDao=getServletContext().getAttribute("diagDao");
        final Object procedureDao=getServletContext().getAttribute("procedureDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.procedureDao=(AtomicReference<ProcedureDao>)procedureDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.proceduresDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        logger.info("-----------------------------------------");
        logger.info("ProcedureServlet doPost() is started;");
        logger.info("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String procId=req.getParameter("procId");
        final String procDate=req.getParameter("procDate");


        logger.info("ProcedureServlet doPost() Chosen STRING diagId:"+diagId);
        logger.info("ProcedureServlet doPost() Chosen STRING procId:"+procId);
        logger.info("ProcedureServlet doPost() Chosen STRING procDate:"+procDate);

        java.util.Date pDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            pDate=sdf.parse(procDate);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info("procDate: "+pDate);

        try {
            this.procedureDao.get().createDiagProc(Integer.parseInt(diagId), pDate, Integer.parseInt(procId));
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }


        logger.info("-----------------------------------------");
        logger.info("ProcedureServlet doPost() is finished;");
        logger.info("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("-----------------------------------------");
        logger.info("ProcedureServlet doGet() is started;");
        logger.info("-----------------------------------------");
        this.diagnosesDb.clear();
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        logger.info("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Diagnosis> diags = this.diagDao.get().getAllById(pCardId);
            for(int i=0;i<diags.size();i++){
                this.diagnosesDb.put(diags.get(i).getDiagId(), diags.get(i));
            }
            req.setAttribute("diagnosesDb", diagnosesDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        try {
            List<Procedure> procs = this.procedureDao.get().getAll();

            logger.info("procs size: "+procs.size());
            for(int i=0;i<procs.size();i++){
//                logger.info("i: "+i+" operId: "+opers.get(i).getOperId());
                this.proceduresDb.put(procs.get(i).getProcId(), procs.get(i));
            }
//            logger.info("OperationsDb size: "+this.operationsDb.size());
            req.setAttribute("proceduresDb", proceduresDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }


        req.setAttribute("fin", fin);
        req.setAttribute("pCardId", pCardId);

        logger.info("-----------------------------------------");
        logger.info("Procedureervlet doGet() is finished;");
        logger.info("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/procedure.jsp").forward(req, resp);

    }
}
