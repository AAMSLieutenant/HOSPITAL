package com.hospital.servlet;

import com.hospital.dao.AppointmentDao;
import com.hospital.dao.DiagDao;
import com.hospital.dao.OperationDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Rostislav Stakhov
 * Servlet for appointing patient operations
 */
public class OperationServlet extends HttpServlet {


    private static final Logger logger= LoggerFactory.getLogger(OperationServlet.class);
    private Integer pCardId=0;
    private AtomicReference<AppointmentDao> appointmentDao;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<OperationDao> operationDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Operation> operationsDb;
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
        final Object operationDao=getServletContext().getAttribute("operationDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.operationDao=(AtomicReference<OperationDao>)operationDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.operationsDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doPost() is started;");
        logger.info("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String operId=req.getParameter("operId");
        final String operDate=req.getParameter("operDate");


        logger.info("operationServlet doPost() Chosen STRING diagId:"+diagId);
        logger.info("operationServlet doPost() Chosen STRING operId:"+operId);
        logger.info("operationServlet doPost() Chosen STRING operDate:"+operDate);

        java.util.Date oDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            oDate=sdf.parse(operDate);
        } catch (ParseException pe) {
            logger.error(pe.getMessage());
        }
        logger.info("operDate: "+oDate);

        try {
            this.operationDao.get().createDiagOper(Integer.parseInt(diagId), oDate, Integer.parseInt(operId));
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }




//);
//
        logger.info("-----------------------------------------");
        logger.info("OperationServlet doPost() is finished;");
        logger.info("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.diagnosesDb.clear();

        logger.info("-----------------------------------------");
        logger.info("OperationServlet doGet() is started;");
        logger.info("-----------------------------------------");
        pCardId=Integer.parseInt(req.getParameter("pCardId"));
        logger.info("CURRENT PATIENT ID:"+pCardId);
        try {
            List<Diagnosis> diags = this.diagDao.get().getAllById(pCardId);
            for(int i=0;i<diags.size();i++){
                this.diagnosesDb.put(diags.get(i).getDiagId(), diags.get(i));
            }
            logger.info("diagnosesDb size():"+diagnosesDb.size());
            req.setAttribute("diagnosesDb", diagnosesDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        try {
            List<Operation> opers = this.operationDao.get().getAll();

//            logger.info("opers size: "+opers.size());
            for(int i=0;i<opers.size();i++){
//                logger.info("i: "+i+" operId: "+opers.get(i).getOperId());
                this.operationsDb.put(opers.get(i).getOperId(), opers.get(i));
            }
//            logger.info("OperationsDb size: "+this.operationsDb.size());
            req.setAttribute("fin", fin);
            req.setAttribute("operationsDb", operationsDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }



//



        req.setAttribute("pCardId", pCardId);
        logger.info("-----------------------------------------");
        logger.info("OperationServlet doGet() is finished;");
        logger.info("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/operation.jsp").forward(req, resp);

    }
}
