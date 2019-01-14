package com.hospital.servlet;

import com.hospital.dao.DiagDao;
import com.hospital.dao.MedicineDao;
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
 * Servlet for appointing medicine for the patient
 */
public class MedicineServlet extends HttpServlet {


    private static final Logger logger= LoggerFactory.getLogger(MedicineServlet.class);
    private Integer pCardId=0;
    private AtomicReference<DiagDao> diagDao;
    private AtomicReference<MedicineDao> medicineDao;
    private Map<Integer, Employee> doctorsDb;
    private Map<Integer, Diagnosis> diagnosesDb;
    private Map<Integer, Medicine> medicineDb;

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
        final Object medicineDao=getServletContext().getAttribute("medicineDao");
        this.diagDao=(AtomicReference<DiagDao>)diagDao;
        this.medicineDao=(AtomicReference<MedicineDao>)medicineDao;
        this.doctorsDb=new ConcurrentHashMap<>();
        this.diagnosesDb=new ConcurrentHashMap<>();
        this.medicineDb=new ConcurrentHashMap<>();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        logger.info("-----------------------------------------");
        logger.info("MedicineServlet doPost() is started;");
        logger.info("-----------------------------------------");

        req.setCharacterEncoding("UTF-8");

        final String diagId=req.getParameter("diagId");
        final String operId=req.getParameter("medId");
        final String medEnd=req.getParameter("medEnd");


        logger.info("operationServlet doPost() Chosen STRING diagId:"+diagId);
        logger.info("operationServlet doPost() Chosen STRING operId:"+operId);
        logger.info("operationServlet doPost() Chosen STRING medEnd:"+medEnd);

        java.util.Date mStart=curDate;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        logger.info("startDate: "+mStart);


        java.util.Date mEnd=null;
        try {
            mEnd=sdf.parse(medEnd);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info("endDate: "+mEnd);

        try {
            this.medicineDao.get().createDiagMed(Integer.parseInt(diagId), mStart, mEnd, Integer.parseInt(operId));
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        logger.info("-----------------------------------------");
        logger.info("MedicineServlet doPost() is finished;");
        logger.info("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/patient?pCardId="+pCardId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("-----------------------------------------");
        logger.info("Medicine doGet() is started;");
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
            List<Medicine> meds = this.medicineDao.get().getAll();

            logger.info("meds size: "+meds.size());
            for(int i=0;i<meds.size();i++){
//                logger.info("i: "+i+" operId: "+opers.get(i).getOperId());
                this.medicineDb.put(meds.get(i).getMedId(), meds.get(i));
            }
            logger.info("medicineDb size: "+this.medicineDb.size());
            req.setAttribute("medicineDb", medicineDb.values());
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }


//

        req.setAttribute("fin", fin);
        req.setAttribute("pCardId", pCardId);


        logger.info("-----------------------------------------");
        logger.info("MedicineServlet doGet() is finished;");
        logger.info("-----------------------------------------");
        req.getRequestDispatcher("/WEB-INF/view/medicine.jsp").forward(req, resp);

    }
}
