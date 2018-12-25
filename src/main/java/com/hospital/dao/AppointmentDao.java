package com.hospital.dao;

import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements IAppointmentDao {

    private final Connection connection;//Объект соединения с БД

    public AppointmentDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("AppointmentDao received connection");
    }

    public void create(Appointment appointment) throws Exception {

    }

    public Appointment read(int key) throws Exception{

        System.out.println("AppointmentDao read()");
        String statement="SELECT * from appointment WHERE app_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        ResultSet rs=ps.executeQuery();
        Appointment a=new Appointment();
        if(rs.next()) {
            while (rs.next()) {

                a.setAppId(rs.getInt("app_id"));
                a.setAppDate(rs.getDate("app_date"));
                a.setAppValue(rs.getInt("app_value"));
                a.setAppCompliant(rs.getString("app_compliant"));
                a.setCardId(rs.getInt("card_id"));
                if ((rs.getInt("diag_id") == 0)) {
                    System.out.println("NO DIAGNOSIS FOR PATIENT № " + a.getCardId());
                } else {
                    a.setDiagId(rs.getInt("diag_id"));
                }

            }
        }
        else{
            System.out.println("NO APPOINTMENTS FOR THE CURRENT PATIENT");
        }

//        System.out.println("card_id:"+p.getpCardId());
//        System.out.println("p_name:"+p.getpName());
//        System.out.println("p_surname:"+p.getpSurname());
//        System.out.println("p_patronymic:"+p.getpPatronymic());
//        System.out.println("p_sex:"+p.getpSex());
//        System.out.println("p_birth_date:"+p.getpBirthDate());
//        System.out.println("p_arrival_date:"+p.getpArrivalDate());
        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return a;
    }

    public void update(int key, Appointment appointment) throws Exception{

    }

    /** Удаляет запись об объекте из базы данных */
    public void delete(int key) throws Exception{

    }

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Appointment> getAll() throws Exception{
        System.out.println("AppointmentDao getAll()");
        List<Integer> ids=new ArrayList<>();
        List<Appointment> appointments=new ArrayList<>();

        String statement="SELECT app_id FROM appointment";
        System.out.println("read statement: "+statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("app_id"));
            }
            System.out.println("---------------------------------------------------------");
//        System.out.println("Count of cardIds: "+ids.size());

            for (int i = 0; i < ids.size(); i++) {
                System.out.println("current APP_ID: " + ids.get(i));
                appointments.add(read(ids.get(i)));
            }

//        else{
////            System.out.println("AppointmentDao getAll() NO DATA IN Appointment TABLE");
////        }
        return appointments;
    }

    public List<Appointment> getAllById(int id) throws Exception{
        System.out.println("AppointmentDao getAllById()");
        System.out.println("current id: "+id);
        List<Integer> ids=new ArrayList<>();
        List<Appointment> appointments=new ArrayList<>();

        String statement="SELECT app_id FROM appointment WHERE card_id=?";

        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, id);
        System.out.println("read statement: "+statement);
        ResultSet rs=ps.executeQuery();
        if(rs.next()==false) {
            System.out.println("AppointmentDao getAllById() NO DATA IN Appointment TABLE for the patient id " + id);
        }
        else{
            do{
                ids.add(rs.getInt("app_id"));
            }
            while (rs.next());

        }




            System.out.println("---------------------------------------------------------");
            System.out.println("Count of cardIds: "+ids.size());
            for (int i = 0; i < ids.size(); i++) {
                System.out.println("current APP_ID: " + ids.get(i));
                appointments.add(read(ids.get(i)));
            }


        return appointments;
    }

    public void quit(){

    }
}
