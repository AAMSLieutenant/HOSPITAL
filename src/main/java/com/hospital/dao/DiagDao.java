package com.hospital.dao;


import com.hospital.model.Diagnosis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Rostislav Stakhov
 * Dao class for working with the Diagnosis object
 */
public class DiagDao {

    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(DiagDao.class);


    public DiagDao(Connection connection)
    {
        this.connection = connection;
        logger.info("DiagDao received connection");
    }

    public void create(Diagnosis diagnosis) throws Exception {

        int nextId = 0;
        logger.info("DiagDao create()");
        String statement = "SELECT diag_id FROM diagnosis";
        PreparedStatement ps = connection.prepareStatement(statement);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            nextId = rs.getInt("diag_id");
        }
        nextId++;
        logger.info("nextId: " + nextId);
        statement = "INSERT INTO diagnosis(diag_id, diag_name, card_id) " +
                "VALUES(?, ?, ?)";
        ps = connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setString(2, diagnosis.getDiagName());
        ps.setInt(3, diagnosis.getCardId());
        ps.executeUpdate();

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }

    public Diagnosis read(int key) throws Exception {
        logger.info("DiagnosisDao read()");
        String statement="SELECT * from diagnosis WHERE diag_id=?";
        logger.info(statement);
        logger.info("CURRENT DIAG_ID IS: "+key);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        ResultSet rs=ps.executeQuery();
        Diagnosis d=new Diagnosis();
        if(rs.next()) {
            do{

                d.setDiagId(rs.getInt("diag_id"));
                d.setDiagName(rs.getString("diag_name"));
                d.setCardId(rs.getInt("card_id"));

            }while(rs.next());
        }
        else{
            logger.info("NO DIAGNOSES FOR THE CURRENT PATIENT");
        }

        logger.info("diag_id:"+d.getDiagId());
        logger.info("diag_name:"+d.getDiagName());
        logger.info("card_id:"+d.getCardId());

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return d;
    }

    public List<Diagnosis> getAllById(int id) throws Exception{

        logger.info("DiagnosisDao getAllById()");
        logger.info("current id: "+id);
        List<Integer> ids=new ArrayList<>();
        List<Diagnosis> diagnoses=new ArrayList<>();

        String statement="SELECT diag_id FROM diagnosis WHERE card_id=?";

        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, id);
        logger.info("read statement: "+statement);
        ResultSet rs=ps.executeQuery();
        if(rs.next()==false) {
            logger.info("DiagnosisDao getAllById() NO DATA IN Diagnosis TABLE for the patient id " + id);
        }
        else{
            do{
                ids.add(rs.getInt("diag_id"));
            }
            while (rs.next());

        }




        logger.info("---------------------------------------------------------");
        logger.info("Count of diagIds: "+ids.size());
        for (int i = 0; i < ids.size(); i++) {
            logger.info("current DIAG_ID: " + ids.get(i));
            diagnoses.add(read(ids.get(i)));
            logger.info("---------------------------------------------------------");
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return diagnoses;
    }
}
