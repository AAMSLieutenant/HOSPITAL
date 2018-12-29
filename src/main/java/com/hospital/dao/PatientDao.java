/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital.dao;

import com.hospital.interfaces.IPatientDao;

import java.sql.*;
import java.text.DateFormat;
import java.util.List;
import com.hospital.model.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Rostislav Stakhov
 */
public class PatientDao implements IPatientDao {

    /** Объект для управления персистентным состоянием объекта Office */



    private final Connection connection;//Объект соединения с БД
//    private static final org.apache.log4j.Logger log= Logger.getLogger(PatientDao.class);
//    static {
//        PropertyConfigurator.configure("log4j.properties");
//    }

    public PatientDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("PatientDao received connection");
    }

    /** Создает новую запись и соответствующий ей объект */
    public void create(Patient patient) throws Exception{

        java.sql.Date d;
        int nextId=0;
        System.out.println("PatientDao create()");
        String statement="SELECT card_id FROM patient";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("card_id");
        }
        nextId++;
        System.out.println("nextId: "+nextId);
        statement="INSERT INTO patient(card_id, p_name, p_surname, p_patronymic, p_birth_date, p_sex, p_arrival_date) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setString(2, patient.getpName());
        ps.setString(3, patient.getpSurname());
        ps.setString(4, patient.getpPatronymic());
        d=new java.sql.Date(patient.getpBirthDate().getTime());
        ps.setDate(5, d);
//        System.out.println("pBirthDate: "+d);
        ps.setString(6, patient.getpSex());
        d=new java.sql.Date(patient.getpArrivalDate().getTime());
//        System.out.println("pArrivalDate: "+d);
        ps.setDate(7, d);
        ps.executeUpdate();


//        if((consider(getRole(), 'w', 15))==1){
//            log.info("Employee create()");
//
////            System.out.println("Employee create()");
//
//
//            int count=0;
//            String statement="SELECT COUNT(*) FROM Objects";
//            PreparedStatement ps=connection.prepareStatement(statement);
//            ResultSet rs=ps.executeQuery();
//
//            while(rs.next()){
//                count=rs.getInt(1);
//            }
//            System.out.println("Number of objects: "+count);
//            count++;
//
//
//            ps=connection.prepareStatement("INSERT INTO Objects (object_id, object_name, type_id, parent_object_id) "
//                    + "VALUES ((select to_number('3" + count + "' || (to_char(sysdate, 'DDMMYYHH24MI')))from dual), ?, ?, ?) ");
//
//            ps.setString(1, employee.getEmpName());
//            ps.setInt(2, 15);
//            ps.setObject(3, null);
//            if(employee.getParObjId()!=null){
//                ps.setLong(3, employee.getParObjId());
//            }
//
//            ps.executeUpdate();
//            connection.commit();
//
//
//            statement="INSERT INTO PARAMETERS (attr_id, object_id, number_info) "
//                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
//            ps=connection.prepareStatement(statement);
//            ps.setInt(1, 21);
//            ps.setString(2, employee.getEmpName());
//            ps.setInt(3, employee.getEmpNum());
//            ps.executeUpdate();
//            connection.commit();
//
//
//            statement="INSERT INTO PARAMETERS (attr_id, object_id, text_info) "
//                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
//            ps=connection.prepareStatement(statement);
//            ps.setInt(1, 22);
//            ps.setString(2, employee.getEmpName());
//            ps.setString(3, employee.getEmpName());
//            ps.executeUpdate();
//            connection.commit();
//
//            statement="INSERT INTO PARAMETERS (attr_id, object_id, text_info) "
//                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
//            ps=connection.prepareStatement(statement);
//            ps.setInt(1, 23);
//            ps.setString(2, employee.getEmpName());
//            ps.setString(3, employee.getEmpJob());
//            ps.executeUpdate();
//            connection.commit();
//
//
//            java.sql.Date d=new java.sql.Date(employee.getEmpHireDate().getTime());
//            System.out.println(d);
//            statement="INSERT INTO PARAMETERS (attr_id, object_id, date_info) "
//                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
//            ps=connection.prepareStatement(statement);
//            ps.setInt(1, 24);
//            ps.setString(2, employee.getEmpName());
//            ps.setDate(3, d);
//            ps.executeUpdate();
//            connection.commit();
//
//            statement="INSERT INTO PARAMETERS (attr_id, object_id, number_info) "
//                    +"VALUES (?, (SELECT object_id FROM objects WHERE object_name=?), ?)";
//            ps=connection.prepareStatement(statement);
//            ps.setInt(1, 25);
//            ps.setString(2, employee.getEmpName());
//            ps.setDouble(3, employee.getSalary());
//            ps.executeUpdate();
//            connection.commit();
//
//
//
//            try{
//                ps.close();
//            }
//            catch(SQLException e){
//                log.error("EmployeeDao create(): SQLException");
//                e.printStackTrace();
//            }
//
//        }

    }

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Patient read(int key) throws Exception{

        System.out.println("PatientDao read()");
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
            p.setpSex(rs.getString("p_sex"));
            p.setpBirthDate(rs.getDate("p_birth_date"));
            p.setpArrivalDate(rs.getDate("p_arrival_date"));
        }

        System.out.println("card_id:"+p.getpCardId());
        System.out.println("p_name:"+p.getpName());
        System.out.println("p_surname:"+p.getpSurname());
        System.out.println("p_patronymic:"+p.getpPatronymic());
        System.out.println("p_sex:"+p.getpSex());
        System.out.println("p_birth_date:"+p.getpBirthDate());
        System.out.println("p_arrival_date:"+p.getpArrivalDate());
        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return p;

//            e.setId(rs.getLong("object_id"));
//
//            e.setObjName(rs.getString("object_name"));
//
//            e.setParObjId(rs.getLong("parent_object_id"));
//
//            if(rs.getInt("attr_id")==21) e.setEmpNum(rs.getInt("number_info"));
//
//            if(rs.getInt("attr_id")==22) e.setEmpName(rs.getString("text_info"));
//
//            if(rs.getInt("attr_id")==23) e.setEmpJob(rs.getString("text_info"));
//
//            if(rs.getInt("attr_id")==24) e.setEmpHireDate(rs.getDate("date_info"));
//
//            if(rs.getInt("attr_id")==25) e.setSalary(rs.getDouble("number_info"));
//
//


//        if((consider(getRole(), 'r', 15))==-1){
//            log.error("EmployeeDao read() access error");
//            return null;
//        }
//        log.info("EmployeeDao read()");
//
//
//
//
//        String statement="select o.object_id, o.object_name, p.text_info, p.number_info, p.date_info, o.parent_object_id, a.attr_id"
//                +" from OBJECTS o"
//                +" inner join attributes a on a.TYPE_ID=15"
//                +" inner join parameters p on p.object_id = o.object_id"
//                +" and a.ATTR_ID=p.ATTR_ID"
//                +" and o.OBJECT_ID=?"
//                + "order by OBJECT_ID, ATTR_ID";
//
//        PreparedStatement ps=connection.prepareStatement(statement);
//        ps.setLong(1, key);
//        ResultSet rs=ps.executeQuery();
//        Employee e=new Employee();
//        while(rs.next()){
//
//
//
//            e.setId(rs.getLong("object_id"));
//
//            e.setObjName(rs.getString("object_name"));
//
//            e.setParObjId(rs.getLong("parent_object_id"));
//
//            if(rs.getInt("attr_id")==21) e.setEmpNum(rs.getInt("number_info"));
//
//            if(rs.getInt("attr_id")==22) e.setEmpName(rs.getString("text_info"));
//
//            if(rs.getInt("attr_id")==23) e.setEmpJob(rs.getString("text_info"));
//
//            if(rs.getInt("attr_id")==24) e.setEmpHireDate(rs.getDate("date_info"));
//
//            if(rs.getInt("attr_id")==25) e.setSalary(rs.getDouble("number_info"));
//
//
//        }
//        System.out.println("id:"+e.getId());
//        System.out.println("number:"+e.getEmpNum());
//        System.out.println("name:"+e.getEmpName());
//        System.out.println("job:"+e.getEmpJob());
//        System.out.println("hiredate:"+e.getEmpHireDate());
//        System.out.println("salary:"+e.getSalary());
//
//
//        System.out.println("------------------------------");
//
//        try{
//            ps.close();
//        }
//        catch(SQLException ex){
//            log.error("Employee read(): SQLException");
//        }
////
//        return e;
//
    }

    /** Сохраняет состояние объекта Employee в базе данных */
    public void update(int key, Patient patient) throws Exception{

        boolean flag=false;
        System.out.println("PatientDao update()");
        String statement="SELECT CARD_ID FROM PATIENT";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("card_id")==key){
                flag=true;
                System.out.println("PatientDao update() MATCH: "+rs.getLong("card_id"));
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

//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getObjName());
//                ps.setObject(2, null);
//                if(employee.getParObjId()!=null){
//                    ps.setLong(2, employee.getParObjId());
//                }
//                ps.setLong(3, key);
//                ps.executeUpdate();
//                connection.commit();
//
//
//
//                statement="UPDATE parameters"
//                        + " SET number_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=21";
//                ps=connection.prepareStatement(statement);
//                ps.setInt(1, employee.getEmpNum());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET text_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=22";
//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getEmpName());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET text_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=23";
//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getEmpJob());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET date_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=24";
//                ps=connection.prepareStatement(statement);
//
//                java.sql.Date d=new java.sql.Date(employee.getEmpHireDate().getTime());
//                ps.setDate(1, d);
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET number_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=25";
//                ps=connection.prepareStatement(statement);
//                ps.setDouble(1, employee.getSalary());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//
//            }
//        if((consider(getRole(), 'w', 15))==1){
//
//
//
//            log.info("EmployeeDao update()");
//            boolean flag=false;
//            String statement="SELECT object_id FROM Objects";
//            PreparedStatement ps=connection.prepareStatement(statement);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                if(rs.getLong(1)==key){
//                    flag=true;
//                    log.info("EmployeeDao update() MATCH: "+rs.getLong(1));
//                    break;
//                }
//            }
//
//            if(flag==true){
//                log.info("EmployeeDao update() started");
//                log.info(employee.getObjName());
//                statement="UPDATE objects SET object_name=?, parent_object_id=? WHERE object_id=?";
//
//
//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getObjName());
//                ps.setObject(2, null);
//                if(employee.getParObjId()!=null){
//                    ps.setLong(2, employee.getParObjId());
//                }
//                ps.setLong(3, key);
//                ps.executeUpdate();
//                connection.commit();
//
//
//
//                statement="UPDATE parameters"
//                        + " SET number_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=21";
//                ps=connection.prepareStatement(statement);
//                ps.setInt(1, employee.getEmpNum());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET text_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=22";
//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getEmpName());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET text_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=23";
//                ps=connection.prepareStatement(statement);
//                ps.setString(1, employee.getEmpJob());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET date_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=24";
//                ps=connection.prepareStatement(statement);
//
//                java.sql.Date d=new java.sql.Date(employee.getEmpHireDate().getTime());
//                ps.setDate(1, d);
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//                statement="UPDATE parameters"
//                        + " SET number_info=?"
//                        + " WHERE object_id=?"
//                        + " AND attr_id=25";
//                ps=connection.prepareStatement(statement);
//                ps.setDouble(1, employee.getSalary());
//                ps.setLong(2, key);
//                ps.executeUpdate();
//                connection.commit();
//                //
//
//            }
//            else{
//                log.error("EmployeeDao update() error: wrong object ID");
//            }
//
//            try{
//                ps.close();
//            }
//            catch(SQLException e){
//                log.error("EmployeeDao update(): SQLException");
//                e.printStackTrace();
//            }
//
//        }

    }

    /** Удаляет запись об объекте из базы данных */
    public void delete(int key) throws Exception{

        boolean flag=false;
        System.out.println("PatientDao delete()");
        String statement="SELECT card_id from PATIENT";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(rs.getInt("card_id")==key){
                flag=true;
                System.out.println("MATCH: "+rs.getInt("card_id"));
                break;
            }
        }
        if(flag==true){
            ps=connection.prepareStatement("DELETE FROM PATIENT WHERE card_id=?");
            ps.setInt(1, key);
            ps.executeUpdate();
        }
//        if((consider(getRole(), 'w', 15))==1){
//
//            log.info("EmployeeDao delete()");
//            boolean flag=false;
//            String statement="SELECT object_id FROM Objects";
//            PreparedStatement ps=connection.prepareStatement(statement);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                if(rs.getLong(1)==key){
//                    flag=true;
//                    System.out.println("MATCH: "+rs.getLong(1));
//                    break;
//                }
//            }
//
//            if(flag==true){
//
//                ps=connection.prepareStatement("DELETE FROM Parameters WHERE object_id=?");
//                ps.setLong(1, key);
//                ps.executeUpdate();
//
//
//                ps=connection.prepareStatement("DELETE FROM Objects WHERE object_id=?");
//                ps.setLong(1, key);
//                ps.executeUpdate();
//
//
//                connection.commit();
//
//            }
//            else{
//                log.error("OfficeDao delete():wrong object ID");
//            }
//
//
//            try{
//                ps.close();
//            }
//            catch(SQLException e) {
//                e.printStackTrace();
//                log.error("EmployeeDao delete(): SQLException");
//            }
//
//        }


    }

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Patient> getAll() throws Exception{

        System.out.println("PatientDao getAll()");
        List<Integer> ids=new ArrayList<>();
        List<Patient> patients=new ArrayList<>();

        String statement="SELECT card_id FROM patient";
        System.out.println("read statement: "+statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            ids.add(rs.getInt("card_id"));
        }
        System.out.println("---------------------------------------------------------");
//        System.out.println("Count of cardIds: "+ids.size());
        for(int i=0;i<ids.size();i++){
            System.out.println("current CARD_ID: "+ids.get(i));
            patients.add(read(ids.get(i)));
        }

        return patients;


//        log.info("EmployeeDao getAll()");
//        if((consider(getRole(), 'r', 15))==-1){
//            log.error("EmployeeDao getAll() access error");
//            return null;
//        }
//
//        log.info("EmployeeDao getAll()");
//        List<Employee> objects=new ArrayList<Employee>();
//        List<Long> arr=new ArrayList<Long>();
//
//
//        log.error("EmployeeDao getAll(): SQLException");
//
//        String statement="SELECT object_id FROM Objects where type_id=15";
//
//        PreparedStatement ps=connection.prepareStatement(statement);
//        ResultSet rs=ps.executeQuery();
//        while(rs.next()){
//
//            arr.add(rs.getLong("object_id"));
//        }
//
//
//
//        for(int i=0;i<arr.size();i++){
//            objects.add(read(arr.get(i)));
//        }
//
//        return objects;

    }

    public void quit(){
//        log.info("EmployeeDao closing connection");
//        try
//        {
//            this.connection.close();
//        }
//        catch(Exception e)
//        {
//            log.error("EmployeeDao quit(): SQLException");
//            e.printStackTrace();
//        }

    }







}
