/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital.dao;

import java.sql.*;
import java.util.List;

import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author Rostislav Stakhov
 * Dao class for working with the Patient object
 */
public class PatientDao{

    /** Объект для управления персистентным состоянием объекта Office */



    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(PatientDao.class);

    public PatientDao(Connection connection)
    {
        this.connection = connection;
        logger.info("PatientDao received connection");
    }

    /** Создает новую запись и соответствующий ей объект */
    public void create(Patient patient) throws Exception{

        java.sql.Date d;
        int nextId=0;
        logger.info("PatientDao create()");
        String statement="SELECT card_id FROM patient";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("card_id");
        }
        nextId++;
        logger.info("nextId: "+nextId);
        statement="INSERT INTO patient(card_id, p_name, p_surname, p_patronymic, p_age, p_birth_date, p_sex, p_arrival_date) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setString(2, patient.getpName());
        ps.setString(3, patient.getpSurname());
        ps.setString(4, patient.getpPatronymic());
        ps.setInt(5, patient.getpAge());
        d=new java.sql.Date(patient.getpBirthDate().getTime());
        ps.setDate(6, d);
//        logger.info("pBirthDate: "+d);
        ps.setString(7, patient.getpSex());
        d=new java.sql.Date(patient.getpArrivalDate().getTime());
//        logger.info("pArrivalDate: "+d);
        ps.setDate(8, d);
        ps.executeUpdate();

        statement="INSERT INTO address (card_id, city, street, house_number, flat_number) " +
                "VALUES(?, ?, ?, ?, ?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setString(2, patient.getpAddress().getCity());
        ps.setString(3, patient.getpAddress().getStreet());
        ps.setInt(4, patient.getpAddress().getHouseNumber());
        ps.setInt(5, patient.getpAddress().getFlatNumber());
        ps.executeUpdate();

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }



    }

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Patient read(int key) throws Exception{

        logger.info("PatientDao read()");
        String statement="SELECT * from patient WHERE card_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        ResultSet rs=ps.executeQuery();
        Patient p=new Patient();

        while(rs.next()){

            p.setpCardId(rs.getInt("card_id"));
            p.setpName(rs.getString("p_name"));
            p.setpSurname(rs.getString("p_surname"));
            p.setpPatronymic(rs.getString("p_patronymic"));
            p.setpAge(rs.getInt("p_age"));
            p.setpSex(rs.getString("p_sex"));
            p.setpBirthDate(rs.getDate("p_birth_date"));
            p.setpArrivalDate(rs.getDate("p_arrival_date"));
            p.setpDischargeDate(rs.getDate("p_discharge_date"));
        }

        statement="SELECT * FROM address WHERE card_id=?";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        rs=ps.executeQuery();
        while(rs.next()){
            p.getpAddress().setCity(rs.getString("city"));
            p.getpAddress().setStreet(rs.getString("street"));
            p.getpAddress().setHouseNumber(rs.getInt("house_number"));
            p.getpAddress().setFlatNumber(rs.getInt("flat_number"));
        }

        logger.info("card_id:"+p.getpCardId());
        logger.info("p_name:"+p.getpName());
        logger.info("p_surname:"+p.getpSurname());
        logger.info("p_patronymic:"+p.getpPatronymic());
        logger.info("p_age:"+p.getpAge());
        logger.info("p_sex:"+p.getpSex());
        logger.info("p_birth_date:"+p.getpBirthDate());
        logger.info("p_arrival_date:"+p.getpArrivalDate());
        logger.info("p_discharge_date:"+p.getpDischargeDate());
        logger.info("p_city:"+p.getpAddress().getCity());
        logger.info("p_street:"+p.getpAddress().getStreet());
        logger.info("p_house:"+p.getpAddress().getHouseNumber());
        logger.info("p_flat:"+p.getpAddress().getFlatNumber());


        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }



        return p;
    }

    /** Сохраняет состояние объекта Employee в базе данных */
    public void update(int key, Patient patient) throws Exception{

        boolean flag=false;
        logger.info("PatientDao update()");
        String statement="SELECT CARD_ID FROM PATIENT";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("card_id")==key){
                flag=true;
                logger.info("PatientDao update() MATCH: "+rs.getLong("card_id"));
                break;
            }
        }
        if(flag==true){
            statement="UPDATE PATIENT SET p_name=?, p_surname=?, p_patronymic=? WHERE card_id=?";
            ps=connection.prepareStatement(statement);
            ps.setString(1, patient.getpName());
            ps.setString(2, patient.getpSurname());
            ps.setString(3, patient.getpPatronymic());
            ps.setInt(4, key);
            ps.executeUpdate();
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }


    }

    /** Удаляет запись об объекте из базы данных */
    public void delete(int key) throws Exception{

        boolean flag=false;
        logger.info("PatientDao delete()");
        String statement="SELECT card_id from PATIENT";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("card_id")==key){
                flag=true;
                logger.info("MATCH: "+rs.getInt("card_id"));
                break;
            }
        }
        if(flag==true){
            ps=connection.prepareStatement("DELETE FROM PATIENT WHERE card_id=?");
            ps.setInt(1, key);
            ps.executeUpdate();
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Patient> getAll() throws Exception{

        logger.info("PatientDao getAll()");
        List<Integer> ids=new ArrayList<>();
        List<Patient> patients=new ArrayList<>();

        String statement="SELECT card_id FROM patient";
        logger.info("read statement: "+statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            ids.add(rs.getInt("card_id"));
        }
        logger.info("---------------------------------------------------------");
//        logger.info("Count of cardIds: "+ids.size());
        for(int i=0;i<ids.size();i++){
            logger.info("current CARD_ID: "+ids.get(i));
            patients.add(read(ids.get(i)));
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }


        return patients;
    }


    public void discharge(int pCardId) throws Exception{
        Date curDate=new Date();
        logger.info("--------------------------------");
        logger.info("PatientDao discharge()");
        logger.info("--------------------------------");
        String statement="UPDATE PATIENT SET p_discharge_date=? WHERE card_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        java.sql.Date d=new java.sql.Date(curDate.getTime());
        ps.setDate(1, d);
        ps.setInt(2, pCardId);
        ps.executeUpdate();
        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }


}
