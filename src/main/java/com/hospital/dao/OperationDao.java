package com.hospital.dao;

import com.hospital.model.Diagnosis;
import com.hospital.model.Operation;
import com.hospital.model.OperInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationDao {

    private final Connection connection;//Объект соединения с БД

    public OperationDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("OperationDao received connection");
    }

    public void createDiagOper(int diagId, Date operDate, int operId) throws Exception{

        System.out.println("OperationDao createDiagOper()");
        java.sql.Date d;
        int nextId=0;
        d=new java.sql.Date(operDate.getTime());

        String statement="SELECT do_id FROM diag_oper";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("do_id");
        }
        nextId++;
        System.out.println("OperationDao createDiagOper() nextId: "+nextId);




        statement="INSERT INTO diag_oper " +
                "VALUES(?,?,?,?,?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setInt(2, diagId);
        ps.setDate(3, d);
        ps.setInt(4, 0);
        ps.setInt(5, operId);
        ps.executeUpdate();

    }


    //Возвращает все ID диагнозов данного пациента
    public List<Integer> getOperationIds(int cardId) throws Exception{
        System.out.println("--------------------------");
        System.out.println("OperationDao getOperationIds()");
        List<Integer> temp=new ArrayList<>();
        String statement="SELECT DISTINCT d.diag_id " +
                "FROM diagnosis d " +
                "INNER JOIN diag_oper do ON (d.diag_id=do.diag_id) AND (d.card_id=?)";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, cardId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            temp.add(rs.getInt("diag_id"));
        }
        if(temp.size()==0){
            System.out.println("NO OPERATIONS FOR THE PATIENT NUMBER "+cardId);
            return null;
        }
        else{
            for (int i = 0; i < temp.size(); i++) {
                System.out.println("diag_id with operations for patient: " + temp.get(i));
            }
        }

        return temp;
    }

    //Возвращает полный список операций по всем диагнозам для данного пациента
    public List<OperInfo> getAllDiagOper(int pCardId) throws Exception{

        List<Integer> ids=new ArrayList<>();
        List<OperInfo> operInfos=new ArrayList<>();
        List<OperInfo> temp=new ArrayList<>();
        if(getOperationIds(pCardId)!=null) {
            ids = getOperationIds(pCardId);
            for (int i = 0; i < ids.size(); i++) {
                temp = getOperations(ids.get(i));
                System.out.println("temp size: " + temp.size());
                ;
                for (int j = 0; j < temp.size(); j++) {
                    operInfos.add(temp.get(j));
                }
            }
        }
        System.out.println("final count of the operations: "+operInfos.size());
        return operInfos;
    }


    //По номеру диагноза пациента возвращает все назначенные на этот диагноз операции
    public List<OperInfo> getOperations(int diagId) throws Exception{

        List<OperInfo> operInfos=new ArrayList<>();
        System.out.println("-----------------------------------");
        System.out.println("OperationDao getPatientOperations()");
        OperInfo operInfo=new OperInfo();
        String statement="SELECT do.do_id, do.diag_id, d.diag_name,  do.oper_id, o.oper_name, do.oper_date, do.oper_done, o.doctor_id, e.emp_surname, p.pos_name " +
                "FROM diag_oper do " +
                "INNER JOIN diagnosis d ON((d.diag_id=do.diag_id)AND(do.diag_id=?)) " +
                "INNER JOIN operation o ON(o.oper_id=do.oper_id) " +
                "INNER JOIN employee e ON(o.doctor_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";

        System.out.println("current diagId: "+diagId);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, diagId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            operInfo.setDoId(rs.getInt("do_id"));
            operInfo.setDiagId(rs.getInt("diag_id"));
            operInfo.setDiagName(rs.getString("diag_name"));
            operInfo.setOperId(rs.getInt("oper_id"));
            operInfo.setOperName(rs.getString("oper_name"));
            operInfo.setOperDate(rs.getDate("oper_date"));
            if(rs.getInt("oper_done")==1){
                operInfo.setOperDone(true);
            }
            else{
                operInfo.setOperDone(false);
            }
            operInfo.setDoctorId(rs.getInt("doctor_id"));
            operInfo.setEmpSurname(rs.getString("emp_surname"));
            operInfo.setPosName(rs.getString("pos_name"));




            System.out.println("---------------------------------");
            System.out.println("do_id"+operInfo.getDoId());
            System.out.println("diag_id"+operInfo.getDiagId());
            System.out.println("diag_name"+operInfo.getDiagName());
            System.out.println("oper_id"+operInfo.getOperId());
            System.out.println("oper_name"+operInfo.getOperName());
            System.out.println("oper_date"+operInfo.getOperDate());
            System.out.println("oper_done"+operInfo.isOperDone());
            System.out.println("doctor_id"+operInfo.getDoctorId());
            System.out.println("emp_surname"+operInfo.getEmpSurname());
            System.out.println("pos_name"+operInfo.getPosName());

            operInfos.add(operInfo);
            operInfo=new OperInfo();
        }


//
        return operInfos;
    }


    public List<Operation> getAll() throws Exception {
        System.out.println("OperationDao read()");
        List<Operation> operations=new ArrayList<>();
        String statement="SELECT o.oper_id, o.oper_name, o.oper_value, o.oper_length, o.doctor_id, e.emp_id, e.emp_surname, e.emp_patronymic, e.emp_name, p.pos_name " +
                "FROM operation o " +
                "INNER JOIN employee e ON(o.doctor_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";
        System.out.println(statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        Operation o=new Operation();
        while(rs.next()) {
           o.setOperId(rs.getInt("oper_id"));
           o.setOperName(rs.getString("oper_name"));
           o.setOperValue(rs.getInt("oper_value"));
           o.setOperLength(rs.getInt("oper_length"));
           o.setDoctorId(rs.getInt("doctor_id"));
           o.setName(rs.getString("emp_name"));
           o.setSurname(rs.getString("emp_surname"));
           o.setPatronymic(rs.getString("emp_patronymic"));
           o.setPosName(rs.getString("pos_name"));
            System.out.println("oper_id:"+o.getOperId());
            System.out.println("oper_name:"+o.getOperName());
            System.out.println("oper_value:"+o.getOperValue());
            System.out.println("oper_length:"+o.getOperLength());
            System.out.println("doctor_id:"+o.getDoctorId());
            System.out.println("emp_name:"+o.getName());
            System.out.println("emp_surname:"+o.getSurname());
            System.out.println("emp_patronymic:"+o.getPatronymic());
            System.out.println("pos_name:"+o.getPosName());
            System.out.println("-------------------------------------");
           operations.add(o);
           o=new Operation();

        }

        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

        return operations;
    }

    public void finishOperation(List<OperInfo> operInfos) throws Exception{

        System.out.println("------------------------------------");
        System.out.println("OperationDao finishOperation()");
        String statement="UPDATE diag_oper SET oper_done=1 WHERE do_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        Date curDate=new Date();
        for(int i=0;i<operInfos.size();i++){
            if(curDate.compareTo(operInfos.get(i).getOperDate())>=0){
                ps.setInt(1, operInfos.get(i).getDoId());
                ResultSet rs=ps.executeQuery();
                operInfos.get(i).setOperDone(true);
                System.out.println("operation is finished");
            }
        }

    }
}
