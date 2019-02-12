package com.hospital.dao;



import com.hospital.model.Diagnosis;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DiagDaoTest {

    OracleDaoFactory factory=new OracleDaoFactory();
    DiagDao diagnosisDao;

    public DiagDaoTest(){
        Locale.setDefault(Locale.US);
        try
        {
            diagnosisDao = factory.getDiagDao();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void read() {

        Diagnosis diagExp=new Diagnosis();
        Diagnosis diagAct=new Diagnosis();

        diagExp.setDiagId(1);
        diagExp.setDiagName("Вирусное орви");
        diagExp.setCardId(1);


        try{
            diagAct=diagnosisDao.read(4);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        assertEquals(diagExp, diagAct);
    }
}