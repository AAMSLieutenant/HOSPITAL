package com.hospital.dao;



import com.hospital.model.Appointment;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDaoTest {


    OracleDaoFactory factory=new OracleDaoFactory();
    AppointmentDao appointmentDao;
    public AppointmentDaoTest(){
        Locale.setDefault(Locale.US);
        try
        {
            appointmentDao = factory.getAppointmentDao();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




    @Test
    void read() {

        String d="2018-12-30";
        Date date=new Date();
        Appointment appointmentExp=new Appointment();
        Appointment appointmentAct=new Appointment();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(d);
        }
        catch(ParseException pe){
            pe.printStackTrace();
        }
        appointmentExp.setAppId(2);
        appointmentExp.setAppDate(date);
        appointmentExp.setAppValue(250);
        appointmentExp.setDocId(1);
        appointmentExp.setDocId(1);
        appointmentExp.setCardId(3);
        appointmentExp.setAppComplaint("asdasdasas");
        try{
            appointmentAct=appointmentDao.read(2);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        assertEquals(appointmentExp, appointmentAct);
    }
}