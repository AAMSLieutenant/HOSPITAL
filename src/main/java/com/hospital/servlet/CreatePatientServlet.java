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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author Rostislav Stakhov
 * Servlet for creating and adding patients
 */
public class CreatePatientServlet extends HttpServlet {


    private static final Logger logger= LoggerFactory.getLogger(CreatePatientServlet.class);
    private AtomicReference<PatientDao> patientDao;
    private Integer currentId=0;
    private String year;
    private String month;
    private String date;
    private String fin;
    private Date curDate;
    private Date arrDate;

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

        final Object patientsDb = getServletContext().getAttribute("patientsDb");
        final Object patientDao = getServletContext().getAttribute("patientDao");

        this.patientDao=(AtomicReference<PatientDao>) patientDao;


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("-----------------------------------------");
        logger.info("CreatePatientServlet doPost() is started;");
        logger.info("-----------------------------------------");
        req.setCharacterEncoding("UTF-8");

        Date birthDate=null;

        final String pName = req.getParameter("pName");
        final String pSurname = req.getParameter("pSurname");
        final String pPatronymic = req.getParameter("pPatronymic");
        final String pAge = req.getParameter("pAge");
        final String pSex = req.getParameter("pSex");
        final String pBirthDate = req.getParameter("pBirthDate");
        final String pArrivalDate = req.getParameter("pArrivalDate");
        final String city = req.getParameter("city");
        final String street = req.getParameter("street");
        final String houseNumber = req.getParameter("houseNumber");
        final String flatNumber = req.getParameter("flatNumber");
//
        logger.info("CreatePatient doPost() Chosen STRING pName:"+pName);
        logger.info("CreatePatient doPost() Chosen STRING pSurname:"+pSurname);
        logger.info("CreatePatient doPost() Chosen STRING pPatronymic:"+pPatronymic);
        logger.info("CreatePatient doPost() Chosen STRING pAge:"+pAge);
        logger.info("CreatePatient doPost() Chosen STRING pSex:"+pSex);
        logger.info("CreatePatient doPost() Chosen STRING pBirthDate:"+pBirthDate);
        logger.info("ADDRESS:");
        logger.info("CreatePatient doPost() Chosen STRING city:"+city);
        logger.info("CreatePatient doPost() Chosen STRING street:"+street);
        logger.info("CreatePatient doPost() Chosen STRING houseNumber:"+houseNumber);
        logger.info("CreatePatient doPost() Chosen STRING flatNumber:"+flatNumber);

        Patient patient=new Patient();
        patient.setpName(pName);
        patient.setpSurname(pSurname);
        patient.setpPatronymic(pPatronymic);
        patient.setpAge(Integer.parseInt(pAge));
        patient.setpSex(pSex);




        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthDate=sdf.parse(pBirthDate);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        patient.setpBirthDate(birthDate);
        patient.setpArrivalDate(new Date());
        patient.getpAddress().setCity(city);
        patient.getpAddress().setStreet(street);
        patient.getpAddress().setHouseNumber(Integer.parseInt(houseNumber));
        patient.getpAddress().setFlatNumber(Integer.parseInt(flatNumber));
        try{
            patientDao.get().create(patient);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

        logger.info("-----------------------------------------");
        logger.info("AppointmentServlet doGet() is finished;");
        logger.info("-----------------------------------------");
        resp.sendRedirect(req.getContextPath() + "/read");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("fin", this.fin);
        req.getRequestDispatcher("/WEB-INF/view/createPatient.jsp")
                .forward(req, resp);
    }
}
