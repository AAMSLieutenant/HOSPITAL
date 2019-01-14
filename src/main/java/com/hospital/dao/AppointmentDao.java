package com.hospital.dao;

import com.hospital.filter.AuthFilter;
import com.hospital.model.Appointment;
import com.hospital.model.Employee;
import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao{

    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(AppointmentDao.class);

    public AppointmentDao(Connection connection)
    {
        this.connection = connection;
        logger.info("AppointmentDao received connection");
    }

    public void create(Appointment appointment) throws Exception {
        logger.info("AppointmentDao create()");
        int nextId=0;
        String statement="SELECT app_id FROM appointment";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("app_id");
        }
        nextId++;

        statement="INSERT INTO appointment(app_id, app_date, app_value, app_complaint, doc_id, card_id)" +
                "VALUES(?,?,?,?,?,?)";
        System.out.println(statement);
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        java.sql.Date d=new java.sql.Date(appointment.getAppDate().getTime());
        ps.setDate(2, d);
        ps.setInt(3, appointment.getAppValue());
        ps.setString(4, appointment.getAppComplaint());
        ps.setInt(5, appointment.getDocId());
        ps.setInt(6, appointment.getCardId());
        ps.executeUpdate();
        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }


    public Appointment read(int key) throws Exception{

        logger.info("AppointmentDao read()");
        String statement="SELECT * from appointment WHERE app_id=?";
        logger.info(statement);
        logger.info("CURRENT ID IS: "+key);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        ResultSet rs=ps.executeQuery();
        Appointment a=new Appointment();
        if(rs.next()) {
           do{

                a.setAppId(rs.getInt("app_id"));
                a.setAppDate(rs.getDate("app_date"));
                a.setAppValue(rs.getInt("app_value"));
                a.setAppComplaint(rs.getString("app_complaint"));
                a.setDocId(rs.getInt("doc_id"));
                a.setCardId(rs.getInt("card_id"));

            }while(rs.next());
        }
        else{
            logger.info("NO APPOINTMENTS FOR THE CURRENT PATIENT");
        }

        logger.info("app_id:"+a.getAppId());
        logger.info("app_date:"+a.getAppDate());
        logger.info("app_value:"+a.getAppValue());
        logger.info("app_compliant:"+a.getAppComplaint());
        logger.info("doc_id:"+a.getDocId());
        logger.info("card_id:"+a.getCardId());

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return a;
    }



    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Appointment> getAll() throws Exception{
        logger.info("AppointmentDao getAll()");
        List<Integer> ids=new ArrayList<>();
        List<Appointment> appointments=new ArrayList<>();

        String statement="SELECT app_id FROM appointment";
        logger.info("read statement: "+statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();

        while (rs.next()) {
            ids.add(rs.getInt("app_id"));
        }


        for (int i = 0; i < ids.size(); i++) {
            logger.info("current APP_ID: " + ids.get(i));
            appointments.add(read(ids.get(i)));
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }


        return appointments;
    }

    public List<Appointment> getAllById(int id) throws Exception{

        logger.info("AppointmentDao getAllById()");
        logger.info("current id: "+id);
        List<Integer> ids=new ArrayList<>();
        List<Appointment> appointments=new ArrayList<>();

        String statement="SELECT app_id FROM appointment WHERE card_id=?";

        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, id);
        logger.info("read statement: "+statement);
        ResultSet rs=ps.executeQuery();
        if(rs.next()==false) {
            logger.info("AppointmentDao getAllById() NO DATA IN Appointment TABLE for the patient id " + id);
        }
        else{
            do{
                ids.add(rs.getInt("app_id"));
            }
            while (rs.next());

        }




        logger.info("---------------------------------------------------------");
        logger.info("Count of cardIds: "+ids.size());
        for (int i = 0; i < ids.size(); i++) {
            logger.info("current APP_ID: " + ids.get(i));
            appointments.add(read(ids.get(i)));
            logger.info("---------------------------------------------------------");
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return appointments;
    }


    public List<Employee> getDoctors() throws Exception{
        System.out.println("AppointmentDao getDoctors()");
        List<Employee> emps=new ArrayList<>();

        String statement="select e.emp_id, e.emp_name, e.EMP_SURNAME, e.EMP_PATRONYMIC, p.pos_name "+
        "from employee e "+
        "inner join position p on (e.emp_pos_id=p.pos_id) and ((p.pos_id=1) or (p.pos_id=2))";
        System.out.println(statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        Employee e;
        while(rs.next()){
            e=new Employee();
            e.setEmpId(rs.getInt("emp_id"));
            e.setEmpName(rs.getString("emp_name"));
            e.setEmpSurname(rs.getString("emp_surname"));
            e.setEmpPatronymic(rs.getString("emp_patronymic"));
            e.setPosName(rs.getString("pos_name"));
            emps.add(e);

            logger.info("emp_id:"+e.getEmpId());
            logger.info("emp_name:"+e.getEmpName());
            logger.info("emp_surname:"+e.getEmpSurname());
            logger.info("emp_patronymic:"+e.getEmpPatronymic());
            logger.info("pos_name:"+e.getPosName());
            logger.info("------------------------");
        }

        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

        return emps;
    }

}
