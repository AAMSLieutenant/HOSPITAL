package com.hospital.dao;

import com.hospital.model.Appointment;
import com.hospital.model.Diagnosis;
import com.hospital.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagDao {

    private final Connection connection;//Объект соединения с БД
//    private static final org.apache.log4j.Logger log= Logger.getLogger(PatientDao.class);
//    static {
//        PropertyConfigurator.configure("log4j.properties");
//    }

    public DiagDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("DiagDao received connection");
    }

    public void create(Diagnosis diagnosis) throws Exception {

        int nextId = 0;
        System.out.println("DiagDao create()");
        String statement = "SELECT diag_id FROM diagnosis";
        PreparedStatement ps = connection.prepareStatement(statement);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            nextId = rs.getInt("diag_id");
        }
        nextId++;
        System.out.println("nextId: " + nextId);
        statement = "INSERT INTO diagnosis(diag_id, diag_name, card_id) " +
                "VALUES(?, ?, ?)";
        ps = connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setString(2, diagnosis.getDiagName());
        ps.setInt(3, diagnosis.getCardId());
        ps.executeUpdate();
    }

    public Diagnosis read(int key) throws Exception {
        System.out.println("DiagnosisDao read()");
        String statement="SELECT * from diagnosis WHERE diag_id=?";
        System.out.println(statement);
        System.out.println("CURRENT DIAG_ID IS: "+key);
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
            System.out.println("NO DIAGNOSES FOR THE CURRENT PATIENT");
        }

        System.out.println("diag_id:"+d.getDiagId());
        System.out.println("diag_name:"+d.getDiagName());
        System.out.println("card_id:"+d.getCardId());

        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return d;
    }

    public List<Diagnosis> getAllById(int id) throws Exception{

        System.out.println("DiagnosisDao getAllById()");
        System.out.println("current id: "+id);
        List<Integer> ids=new ArrayList<>();
        List<Diagnosis> diagnoses=new ArrayList<>();

        String statement="SELECT diag_id FROM diagnosis WHERE card_id=?";

        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, id);
        System.out.println("read statement: "+statement);
        ResultSet rs=ps.executeQuery();
        if(rs.next()==false) {
            System.out.println("DiagnosisDao getAllById() NO DATA IN Diagnosis TABLE for the patient id " + id);
        }
        else{
            do{
                ids.add(rs.getInt("diag_id"));
            }
            while (rs.next());

        }




        System.out.println("---------------------------------------------------------");
        System.out.println("Count of diagIds: "+ids.size());
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("current DIAG_ID: " + ids.get(i));
            diagnoses.add(read(ids.get(i)));
            System.out.println("---------------------------------------------------------");
        }
        return diagnoses;
    }
}
