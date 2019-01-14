package com.hospital.dao;


import com.hospital.model.Operation;
import com.hospital.model.OperInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Rostislav Stakhov
 * Dao class for working with the Operation object
 */
public class OperationDao {

    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(OperationDao.class);

    public OperationDao(Connection connection)
    {
        this.connection = connection;
        logger.info("OperationDao received connection");
    }

    public void createDiagOper(int diagId, Date operDate, int operId) throws Exception{

        logger.info("OperationDao createDiagOper()");
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
        logger.info("OperationDao createDiagOper() nextId: "+nextId);




        statement="INSERT INTO diag_oper " +
                "VALUES(?,?,?,?,?)";
        ps=connection.prepareStatement(statement);
        ps.setInt(1, nextId);
        ps.setInt(2, diagId);
        ps.setDate(3, d);
        ps.setInt(4, 0);
        ps.setInt(5, operId);
        ps.executeUpdate();

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }


    //Возвращает все ID диагнозов данного пациента
    public List<Integer> getOperationIds(int cardId) throws Exception{
        logger.info("--------------------------");
        logger.info("OperationDao getOperationIds()");
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

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
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
                logger.info("temp size: " + temp.size());
                ;
                for (int j = 0; j < temp.size(); j++) {
                    operInfos.add(temp.get(j));
                }
            }
        }
        logger.info("final count of the operations: "+operInfos.size());


        return operInfos;
    }


    //По номеру диагноза пациента возвращает все назначенные на этот диагноз операции
    public List<OperInfo> getOperations(int diagId) throws Exception{

        List<OperInfo> operInfos=new ArrayList<>();
        logger.info("-----------------------------------");
        logger.info("OperationDao getPatientOperations()");
        OperInfo operInfo=new OperInfo();
        String statement="SELECT do.do_id, do.diag_id, d.diag_name,  do.oper_id, o.oper_name, do.oper_date, do.oper_done, o.doctor_id, e.emp_surname, p.pos_name " +
                "FROM diag_oper do " +
                "INNER JOIN diagnosis d ON((d.diag_id=do.diag_id)AND(do.diag_id=?)) " +
                "INNER JOIN operation o ON(o.oper_id=do.oper_id) " +
                "INNER JOIN employee e ON(o.doctor_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";

        logger.info("current diagId: "+diagId);
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




            logger.info("---------------------------------");
            logger.info("do_id"+operInfo.getDoId());
            logger.info("diag_id"+operInfo.getDiagId());
            logger.info("diag_name"+operInfo.getDiagName());
            logger.info("oper_id"+operInfo.getOperId());
            logger.info("oper_name"+operInfo.getOperName());
            logger.info("oper_date"+operInfo.getOperDate());
            logger.info("oper_done"+operInfo.isOperDone());
            logger.info("doctor_id"+operInfo.getDoctorId());
            logger.info("emp_surname"+operInfo.getEmpSurname());
            logger.info("pos_name"+operInfo.getPosName());

            operInfos.add(operInfo);
            operInfo=new OperInfo();
        }



        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

//
        return operInfos;
    }


    public List<Operation> getAll() throws Exception {
        logger.info("OperationDao read()");
        List<Operation> operations=new ArrayList<>();
        String statement="SELECT o.oper_id, o.oper_name, o.oper_value, o.oper_length, o.doctor_id, e.emp_id, e.emp_surname, e.emp_patronymic, e.emp_name, p.pos_name " +
                "FROM operation o " +
                "INNER JOIN employee e ON(o.doctor_id=e.emp_id) " +
                "INNER JOIN position p ON(e.emp_pos_id=p.pos_id)";
        logger.info(statement);
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
            logger.info("oper_id:"+o.getOperId());
            logger.info("oper_name:"+o.getOperName());
            logger.info("oper_value:"+o.getOperValue());
            logger.info("oper_length:"+o.getOperLength());
            logger.info("doctor_id:"+o.getDoctorId());
            logger.info("emp_name:"+o.getName());
            logger.info("emp_surname:"+o.getSurname());
            logger.info("emp_patronymic:"+o.getPatronymic());
            logger.info("pos_name:"+o.getPosName());
            logger.info("-------------------------------------");
           operations.add(o);
           o=new Operation();

        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

        return operations;
    }

    public void finishOperation(List<OperInfo> operInfos) throws Exception{

        logger.info("------------------------------------");
        logger.info("OperationDao finishOperation()");
        String statement="UPDATE diag_oper SET oper_done=1 WHERE do_id=?";
        PreparedStatement ps=connection.prepareStatement(statement);
        Date curDate=new Date();
        for(int i=0;i<operInfos.size();i++){
            if(curDate.compareTo(operInfos.get(i).getOperDate())>=0){
                ps.setInt(1, operInfos.get(i).getDoId());
                ResultSet rs=ps.executeQuery();
                operInfos.get(i).setOperDone(true);
                logger.info("operation is finished");
            }
        }

        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }
}
