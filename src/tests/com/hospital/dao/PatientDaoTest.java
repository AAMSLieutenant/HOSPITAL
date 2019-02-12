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
        String bd="1976-07-10";
        String ad="2018-12-20";
//        String dd=;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            patExp.setpBirthDate(sdf.parse(bd));
            patExp.setpArrivalDate(sdf.parse(ad));
        }
        catch(ParseException pe){
            pe.printStackTrace();
        }

        patExp.setpCardId(1);
        patExp.setpName("Андрей");
        patExp.setpSurname("Васильев");
        patExp.setpPatronymic("Иванович");
        patExp.setpSex("муж");
        patExp.setpAge(43);

        patExp.setpAddress(address);

        try {
            patAct = patientDao.read(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        assertEquals(patExp, patAct);
    }
}