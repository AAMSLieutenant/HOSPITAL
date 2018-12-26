package com.hospital.dao;

import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.model.Employee;
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
        System.out.println(statement);
        System.out.println("CURRENT ID IS: "+key);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, key);
        ResultSet rs=ps.executeQuery();
        Appointment a=new Appointment();
        if(rs.next()) {
           do{

                a.setAppId(rs.getInt("app_id"));
                a.setAppDate(rs.getDate("app_date"));
                a.setAppValue(rs.getInt("app_value"));
                a.setAppCompliant(rs.getString("app_complaint"));
                a.setDocId(rs.getInt("doc_id"));
                a.setCardId(rs.getInt("card_id"));

            }while(rs.next());
        }
        else{
            System.out.println("NO APPOINTMENTS FOR THE CURRENT PATIENT");
        }

        System.out.println("app_id:"+a.getAppId());
        System.out.println("app_date:"+a.getAppDate());
        System.out.println("app_value:"+a.getAppValue());
        System.out.println("app_compliant:"+a.getAppCompliant());
        System.out.println("doc_id:"+a.getDocId());
        System.out.println("card_id:"+a.getCardId());
//        System.out.println("diag_id:"+p.getpArrivalDate());
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
                System.out.println("---------------------------------------------------------");
            }
        return appointments;
    }

    public Employee getDoctor(int key)throws Exception{
        return null;
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

            System.out.println("emp_id:"+e.getEmpId());
            System.out.println("emp_name:"+e.getEmpName());
            System.out.println("emp_surname:"+e.getEmpSurname());
            System.out.println("emp_patronymic:"+e.getEmpPatronymic());
            System.out.println("pos_name:"+e.getPosName());
            System.out.println("------------------------");
        }



        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

        return emps;
    }

    public void quit(){

    }
}
