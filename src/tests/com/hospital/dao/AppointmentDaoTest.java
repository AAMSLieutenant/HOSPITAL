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

        String d="2019-01-14";
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
        appointmentExp.setAppId(1);
        appointmentExp.setAppDate(date);
        appointmentExp.setAppValue(250);
        appointmentExp.setDocId(1);
        appointmentExp.setCardId(1);
        appointmentExp.setAppComplaint("Очень сильная боль в горле");
        try{
            appointmentAct=appointmentDao.read(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        assertEquals(appointmentExp, appointmentAct);
    }
}