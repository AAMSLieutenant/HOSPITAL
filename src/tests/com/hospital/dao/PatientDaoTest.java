package com.hospital.dao;

import com.hospital.model.Address;
import com.hospital.model.Diagnosis;
import com.hospital.model.Patient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PatientDaoTest {


    OracleDaoFactory factory=new OracleDaoFactory();
    PatientDao patientDao;

    public PatientDaoTest(){
        Locale.setDefault(Locale.US);
        try
        {
            patientDao = factory.getPatientDao();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void read() {

        Patient patExp=new Patient();
        Patient patAct=new Patient();
        Address address=new Address();
        address.setCity("Киев");
        address.setStreet("Крещатик");
        address.setHouseNumber(22);
        address.setFlatNumber(32);
        Date bd=new Date();
        Date ad=new Date();
        Date dd=new Date();

        patExp.setpCardId(1);
        patExp.setpName("Андрей");
        patExp.setpSurname("Васильев");
        patExp.setpPatronymic("Иванович");
        patExp.setpSex("муж");
        patExp.setpAge(43);
        patExp.setpArrivalDate(ad);
        patExp.setpBirthDate(bd);
        patExp.setpDischargeDate(dd);

        patExp.setpAddress(address);

        try{
            patAct=patientDao.read(1);
            patAct.setpArrivalDate(ad);
            patAct.setpBirthDate(bd);
            patAct.setpDischargeDate(dd);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        assertEquals(patExp, patAct);
    }
}