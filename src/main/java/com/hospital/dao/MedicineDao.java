package com.hospital.dao;


import com.hospital.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineDao {

    private final Connection connection;//Объект соединения с БД

    public MedicineDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("MedicineDao received connection");
    }

    public void createDiagMed(int diagId, Date medStart, Date medEnd, int medId) throws Exception{

        System.out.println("MedicineDao createDiagMed()");
        java.sql.Date start;
        java.sql.Date end;
        start=new java.sql.Date(medStart.getTime());
        end=new java.sql.Date(medEnd.getTime());

        String statement="INSERT INTO diag_med " +
                "VALUES(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, diagId);
        ps.setDate(2, start);
        ps.setDate(3, end);
        ps.setInt(4, 0);
        ps.setInt(5, medId);
        ps.executeUpdate();

    }

    public List<Integer> getMedicineIds(int cardId) throws Exception{
        System.out.println("--------------------------");
        System.out.println("MedicineDao getMedicineIds()");
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
            System.out.println("NO MEDICINE FOR THE PATIENT NUMBER "+cardId);
            return null;
        }
        else{
            for (int i = 0; i < temp.size(); i++) {
                System.out.println("diag_id with MEDICINE for patient: " + temp.get(i));
            }
        }
        return temp;
    }


    public List<MedInfo> getAllDiagMed(int pCardId) throws Exception{

        List<Integer> ids=new ArrayList<>();;
        List<MedInfo> medInfos=new ArrayList<>();
        List<MedInfo> temp=new ArrayList<>();
        ids=getMedicineIds(pCardId);
        if(ids.size()>0) {
            for (int i = 0; i < ids.size(); i++) {
                temp = getMedicine(ids.get(i));
                System.out.println("temp size: " + temp.size());
                ;
                for (int j = 0; j < temp.size(); j++) {
                    medInfos.add(temp.get(j));
                }
            }
        }
        System.out.println("final count of the medicine: "+medInfos.size());
        return medInfos;
    }


    public List<MedInfo> getMedicine(int diagId) throws Exception{

        List<MedInfo> medInfos=new ArrayList<>();
        System.out.println("-----------------------------------");
        System.out.println("MedicineDao getMedicine()");
        MedInfo medInfo=new MedInfo();
        String statement="SELECT dm.diag_id, d.diag_name,  dm.med_id, m.med_name, dm.med_start, dm.med_end, dm.med_done, m.emp_id, e.emp_surname, p.pos_name " +
                "FROM diag_med dm " +
                "INNER JOIN diagnosis d ON((d.diag_id=dm.diag_id)AND(dm.diag_id=?)) " +
                "INNER JOIN medicine m ON(m.med_id=dm.med_id) " +
                "INNER JOIN employee e ON(m.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";

        System.out.println("current diagId: "+diagId);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, diagId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
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




            System.out.println("---------------------------------");
            System.out.println("diag_id:"+medInfo.getDiagId());
            System.out.println("diag_name:"+medInfo.getDiagName());
            System.out.println("med_id:"+medInfo.getMedId());
            System.out.println("med_name:"+medInfo.getMedName());
            System.out.println("med_start:"+medInfo.getMedStart());
            System.out.println("med_end:"+medInfo.getMedEnd());
            System.out.println("med_done:"+medInfo.isMedDone());
            System.out.println("emp_id"+medInfo.getEmpId());
            System.out.println("emp_surname:"+medInfo.getEmpSurname());
            System.out.println("pos_name:"+medInfo.getPosName());

            medInfos.add(medInfo);
            medInfo=new MedInfo();
        }


//
        return medInfos;
    }


    public List<Medicine> getAll() throws Exception {
        System.out.println("-----------------------");
        System.out.println("MedicineDao getAllMedicine()");
        List<Medicine> medicine=new ArrayList<>();
        String statement="SELECT m.med_id, m.med_name, m.med_value, e.emp_id, e.emp_surname, e.emp_patronymic, e.emp_name, p.pos_name " +
                "FROM medicine m " +
                "INNER JOIN employee e ON(m.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";
        System.out.println(statement);
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
            System.out.println("med_id:"+m.getMedId());
            System.out.println("med_name:"+m.getMedName());
            System.out.println("med_value:"+m.getMedValue());
            System.out.println("emp_id:"+m.getEmpId());
            System.out.println("emp_name:"+m.getName());
            System.out.println("emp_surname:"+m.getSurname());
            System.out.println("emp_patronymic:"+m.getPatronymic());
            System.out.println("pos_name:"+m.getPosName());
            System.out.println("-------------------------------------");
            medicine.add(m);
            m=new Medicine();
        }
        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return medicine;
    }

}
