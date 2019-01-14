package com.hospital.dao;


import com.hospital.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Rostislav Stakhov
 * Dao class for working with the Medicine object
 */
public class MedicineDao {

    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(MedicineDao.class);

    public MedicineDao(Connection connection)
    {
        this.connection = connection;
        logger.info("MedicineDao received connection");
    }

    public void createDiagMed(int diagId, Date medStart, Date medEnd, int medId) throws Exception{

        logger.info("MedicineDao createDiagMed()");
        java.sql.Date start;
        java.sql.Date end;
        int nextId=0;
        String statement="SELECT dm_id FROM diag_med";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("dm_id");
        }
        nextId++;
        logger.info("OperationDao createDiagOper() nextId: "+nextId);


        start=new java.sql.Date(medStart.getTime());
        end=new java.sql.Date(medEnd.getTime());

        statement="INSERT INTO diag_med " +
                "VALUES(?,?,?,?,?,?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setInt(2, diagId);
        ps.setDate(3, start);
        ps.setDate(4, end);
        ps.setInt(5, 0);
        ps.setInt(6, medId);
        ps.executeUpdate();

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }

    public List<Integer> getMedicineIds(int cardId) throws Exception{
        logger.info("--------------------------");
        logger.info("MedicineDao getMedicineIds()");
        List<Integer> temp=new ArrayList<>();
        String statement="SELECT DISTINCT d.diag_id " +
                "FROM diagnosis d " +
                "INNER JOIN diag_med dm ON (d.diag_id=dm.diag_id) AND (d.card_id=?)";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, cardId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            temp.add(rs.getInt("diag_id"));
        }
        if(temp.size()==0){
            logger.info("NO MEDICINE FOR THE PATIENT NUMBER "+cardId);
            return null;
        }
        else{
            for (int i = 0; i < temp.size(); i++) {
                logger.info("diag_id with MEDICINE for patient: " + temp.get(i));
            }
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return temp;
    }


    public List<MedInfo> getAllDiagMed(int pCardId) throws Exception{

        List<Integer> ids=new ArrayList<>();;
        List<MedInfo> medInfos=new ArrayList<>();
        List<MedInfo> temp=new ArrayList<>();
        if(getMedicineIds(pCardId)!=null) {
            ids=getMedicineIds(pCardId);
            for (int i = 0; i < ids.size(); i++) {
                temp = getMedicine(ids.get(i));
                logger.info("temp size: " + temp.size());
                ;
                for (int j = 0; j < temp.size(); j++) {
                    medInfos.add(temp.get(j));
                }
            }
        }
        logger.info("final count of the medicine: "+medInfos.size());

        return medInfos;
    }


    public List<MedInfo> getMedicine(int diagId) throws Exception{

        List<MedInfo> medInfos=new ArrayList<>();
        logger.info("-----------------------------------");
        logger.info("MedicineDao getMedicine()");
        MedInfo medInfo=new MedInfo();
        String statement="SELECT dm.dm_id, dm.diag_id, d.diag_name,  dm.med_id, m.med_name, dm.med_start, dm.med_end, dm.med_done, m.emp_id, e.emp_surname, p.pos_name " +
                "FROM diag_med dm " +
                "INNER JOIN diagnosis d ON((d.diag_id=dm.diag_id)AND(dm.diag_id=?)) " +
                "INNER JOIN medicine m ON(m.med_id=dm.med_id) " +
                "INNER JOIN employee e ON(m.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";

        logger.info("current diagId: "+diagId);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, diagId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            medInfo.setDmId(rs.getInt("dm_id"));
            medInfo.setDiagId(rs.getInt("diag_id"));
            medInfo.setDiagName(rs.getString("diag_name"));
            medInfo.setMedId(rs.getInt("med_id"));
            medInfo.setMedName(rs.getString("med_name"));
            medInfo.setMedStart(rs.getDate("med_start"));
            medInfo.setMedEnd(rs.getDate("med_end"));
            if(rs.getInt("med_done")==1){
                medInfo.setMedDone(true);
            }
            else{
                medInfo.setMedDone(false);
            }
            medInfo.setEmpId(rs.getInt("emp_id"));
            medInfo.setEmpSurname(rs.getString("emp_surname"));
            medInfo.setPosName(rs.getString("pos_name"));




            logger.info("---------------------------------");
            logger.info("dm_id:"+medInfo.getDmId());
            logger.info("diag_id:"+medInfo.getDiagId());
            logger.info("diag_name:"+medInfo.getDiagName());
            logger.info("med_id:"+medInfo.getMedId());
            logger.info("med_name:"+medInfo.getMedName());
            logger.info("med_start:"+medInfo.getMedStart());
            logger.info("med_end:"+medInfo.getMedEnd());
            logger.info("med_done:"+medInfo.isMedDone());
            logger.info("emp_id"+medInfo.getEmpId());
            logger.info("emp_surname:"+medInfo.getEmpSurname());
            logger.info("pos_name:"+medInfo.getPosName());

            medInfos.add(medInfo);
            medInfo=new MedInfo();
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

        return medInfos;
    }


    public List<Medicine> getAll() throws Exception {
        logger.info("-----------------------");
        logger.info("MedicineDao getAllMedicine()");
        List<Medicine> medicine=new ArrayList<>();
        String statement="SELECT m.med_id, m.med_name, m.med_value, e.emp_id, e.emp_surname, e.emp_patronymic, e.emp_name, p.pos_name " +
                "FROM medicine m " +
                "INNER JOIN employee e ON(m.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";
        logger.info(statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        Medicine m=new Medicine();
        while(rs.next()) {
            m.setMedId(rs.getInt("med_id"));
            m.setMedName(rs.getString("med_name"));
            m.setMedValue(rs.getInt("med_value"));
            m.setEmpId(rs.getInt("emp_id"));
            m.setName(rs.getString("emp_name"));
            m.setSurname(rs.getString("emp_surname"));
            m.setPatronymic(rs.getString("emp_patronymic"));
            m.setPosName(rs.getString("pos_name"));
            logger.info("med_id:"+m.getMedId());
            logger.info("med_name:"+m.getMedName());
            logger.info("med_value:"+m.getMedValue());
            logger.info("emp_id:"+m.getEmpId());
            logger.info("emp_name:"+m.getName());
            logger.info("emp_surname:"+m.getSurname());
            logger.info("emp_patronymic:"+m.getPatronymic());
            logger.info("pos_name:"+m.getPosName());
            logger.info("-------------------------------------");
            medicine.add(m);
            m=new Medicine();
        }
        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return medicine;
    }

    public void finishMedicine(List<MedInfo> medInfos) throws Exception{

        logger.info("------------------------------------");
        logger.info("MedicineDao finishMedicine()");
        String statement="UPDATE diag_med SET med_done=1 WHERE dm_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        Date curDate=new Date();
        for(int i=0;i<medInfos.size();i++){
            if(curDate.compareTo(medInfos.get(i).getMedEnd())>=0){
                ps.setInt(1, medInfos.get(i).getDmId());
                ResultSet rs=ps.executeQuery();
                medInfos.get(i).setMedDone(true);
                logger.info("medicine is finished");
            }
        }


        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }

}
