package com.hospital.dao;

import com.hospital.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcedureDao {

    private final Connection connection;//Объект соединения с БД

    public ProcedureDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("MedicineDao received connection");
    }

    public void createDiagProc(int diagId, Date procDate, int procId) throws Exception{

        System.out.println("ProcedureDao createDiagProc()");
        java.sql.Date d;
        d=new java.sql.Date(procDate.getTime());

        int nextId=0;
        String statement="SELECT dp_id FROM diag_proc";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            nextId=rs.getInt("dp_id");
        }
        nextId++;
        System.out.println("ProcedureDao createDiagProc() nextId: "+nextId);



        statement="INSERT INTO diag_proc " +
                "VALUES(?,?,?,?,?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setInt(2, diagId);
        ps.setDate(3, d);
        ps.setInt(4, 0);
        ps.setInt(5, procId);
        ps.executeUpdate();

    }


    public List<Integer> getProcedureIds(int cardId) throws Exception{
        System.out.println("--------------------------");
        System.out.println("ProcedureDao getProcedureIds()");
        List<Integer> temp=new ArrayList<>();
        String statement="SELECT DISTINCT d.diag_id " +
                "FROM diagnosis d " +
                "INNER JOIN diag_proc dp ON (d.diag_id=dp.diag_id) AND (d.card_id=?)";
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, cardId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            temp.add(rs.getInt("diag_id"));
        }
        if(temp.size()==0){
            System.out.println("NO PROCEDURES FOR THE PATIENT NUMBER "+cardId);
            return null;
        }
        else{
            for (int i = 0; i < temp.size(); i++) {
                System.out.println("diag_id with procedures for patient: " + temp.get(i));
            }
        }

//        List<Integer> ids=new ArrayList<>();
//
//        for(int i=0;i<temp.size();i++){
//            if(ids.size()!=0) {
//                if (!ids.contains(temp.get(i))) {
//                    ids.add(temp.get(i));
//                }
//            }
//            else{
//                ids.add(temp.get(i));
//            }
//            i++;
//        }
//        System.out.println("------------");
//        for (int i = 0; i < ids.size(); i++) {
//            System.out.println("ids  diag_id: " + ids.get(i));
//        }
//        return ids;
        return temp;
    }

    public List<ProcInfo> getAllDiagProc(int pCardId) throws Exception{

        List<Integer> ids=new ArrayList<>();;
        List<ProcInfo> procInfos=new ArrayList<>();
        List<ProcInfo> temp=new ArrayList<>();
        if(getProcedureIds(pCardId)!=null) {
            ids = getProcedureIds(pCardId);
            for (int i = 0; i < ids.size(); i++) {
                temp = getProcedures(ids.get(i));
                System.out.println("temp size: " + temp.size());
                ;
                for (int j = 0; j < temp.size(); j++) {
                    procInfos.add(temp.get(j));
                }
            }
        }
        System.out.println("final count of the procedures: "+procInfos.size());
        return procInfos;
    }


    public List<ProcInfo> getProcedures(int diagId) throws Exception{

        List<ProcInfo> procInfos=new ArrayList<>();
        System.out.println("-----------------------------------");
        System.out.println("ProcedureInfo getProcedure()");
        ProcInfo procInfo=new ProcInfo();
        String statement="SELECT dp.dp_id, dp.diag_id, d.diag_name,  dp.proc_id, pr.proc_name, dp.proc_date, dp.proc_done, pr.emp_id, e.emp_surname, p.pos_name " +
                "FROM diag_proc dp " +
                "INNER JOIN diagnosis d ON((d.diag_id=dp.diag_id)AND(dp.diag_id=?)) " +
                "INNER JOIN proced pr ON(pr.proc_id=dp.proc_id) " +
                "INNER JOIN employee e ON(pr.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";

        System.out.println("current diagId: "+diagId);
        PreparedStatement ps=connection.prepareStatement(statement);
        ps.setInt(1, diagId);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            procInfo.setDpId(rs.getInt("dp_id"));
            procInfo.setDiagId(rs.getInt("diag_id"));
            procInfo.setDiagName(rs.getString("diag_name"));
            procInfo.setProcId(rs.getInt("proc_id"));
            procInfo.setProcName(rs.getString("proc_name"));
            procInfo.setProcDate(rs.getDate("proc_date"));
            if(rs.getInt("proc_done")==1){
                procInfo.setProcDone(true);
            }
            else{
                procInfo.setProcDone(false);
            }
            procInfo.setEmpId(rs.getInt("emp_id"));
            procInfo.setEmpSurname(rs.getString("emp_surname"));
            procInfo.setPosName(rs.getString("pos_name"));




            System.out.println("---------------------------------");
            System.out.println("diag_id:"+procInfo.getDiagId());
            System.out.println("diag_name:"+procInfo.getDiagName());
            System.out.println("proc_id:"+procInfo.getProcId());
            System.out.println("proc_name:"+procInfo.getProcName());
            System.out.println("proc_start:"+procInfo.getProcDate());
            System.out.println("proc_done:"+procInfo.isProcDone());
            System.out.println("emp_id"+procInfo.getEmpId());
            System.out.println("emp_surname:"+procInfo.getEmpSurname());
            System.out.println("pos_name:"+procInfo.getPosName());

            procInfos.add(procInfo);
            procInfo=new ProcInfo();
        }


//
        return procInfos;
    }



    public List<Procedure> getAll() throws Exception {
        System.out.println("-----------------------");
        System.out.println("ProcedureDao getAllMedicine()");
        List<Procedure> procedures=new ArrayList<>();
        String statement="SELECT pr.proc_id, pr.proc_name, pr.proc_value, e.emp_id, e.emp_surname, e.emp_patronymic, e.emp_name, p.pos_name " +
                "FROM proced pr " +
                "INNER JOIN employee e ON(pr.emp_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";
        System.out.println(statement);
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        Procedure p=new Procedure();
        while(rs.next()) {
            p.setProcId(rs.getInt("proc_id"));
            p.setProcName(rs.getString("proc_name"));
            p.setProcValue(rs.getInt("proc_value"));
            p.setEmpId(rs.getInt("emp_id"));
            p.setName(rs.getString("emp_name"));
            p.setSurname(rs.getString("emp_surname"));
            p.setPatronymic(rs.getString("emp_patronymic"));
            p.setPosName(rs.getString("pos_name"));
            System.out.println("med_id:"+p.getProcId());
            System.out.println("med_name:"+p.getProcName());
            System.out.println("med_value:"+p.getProcValue());
            System.out.println("emp_id:"+p.getEmpId());
            System.out.println("emp_name:"+p.getName());
            System.out.println("emp_surname:"+p.getSurname());
            System.out.println("emp_patronymic:"+p.getPatronymic());
            System.out.println("pos_name:"+p.getPosName());
            System.out.println("-------------------------------------");
            procedures.add(p);
            p=new Procedure();
        }
        try{
            ps.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return procedures;
    }


    public void finishProcedure(List<ProcInfo> procInfos) throws Exception{

        System.out.println("------------------------------------");
        System.out.println("ProcedureDao finishProcedure()");
        String statement="UPDATE diag_proc SET proc_done=1 WHERE dp_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        Date curDate=new Date();
        for(int i=0;i<procInfos.size();i++){
            if(curDate.compareTo(procInfos.get(i).getProcDate())>=0){
                ps.setInt(1, procInfos.get(i).getDpId());
                ResultSet rs=ps.executeQuery();
                procInfos.get(i).setProcDone(true);
                System.out.println("procedure is finished");
            }
        }

    }
}