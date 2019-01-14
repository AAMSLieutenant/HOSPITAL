/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital.dao;






import oracle.jdbc.pool.OracleDataSource;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 *
 * @author Rostislav Stakhov
 */
public class OracleDaoFactory{




    private String user = "admin";//Логин пользователя
    private String password = "admin";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера
    private OracleDataSource oracleDS = null;
    private Connection connection = null;


    public OracleDaoFactory()
    {
        try
        {
//            log.info("-------- Oracle JDBC Connection Testing ------");
            Class.forName(driver);//Регистрируем драйвер
//            connection=DriverManager.getConnection(url, user, password);
            connection=getOracleConnection();
        }

        catch (ClassNotFoundException e)
        {
//            log.error("JDBC Driver class load failture");
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
//        log.info("Oracle JDBC Driver Registered!");

    }


    public Connection getOracleConnection() throws SQLException{

        oracleDS = new OracleDataSource();
        oracleDS.setURL(url);
        oracleDS.setUser(user);
        oracleDS.setPassword(password);
        connection = oracleDS.getConnection();

        return connection;
    }



    public Connection getConnection() throws SQLException
    {
//        log.info("DB Connection returned");
//        return DriverManager.getConnection(url, user, password);
        return this.connection;


    }
//
    public PatientDao getPatientDao() throws Exception
    {
            System.out.println("getPatientDao() returned connection");

            return new PatientDao(getOracleConnection());


    }

    public AppointmentDao getAppointmentDao() throws Exception{
        System.out.println("getAppointmentDao() returned connection");

        return new AppointmentDao(getOracleConnection());
    }

    public DiagDao getDiagDao() throws Exception{
        System.out.println("getDiagDao() returned connection");

        return new DiagDao(getOracleConnection());
    }

    public UuserDao getUuserDao() throws Exception{
        System.out.println("getUuserDao() returned connection");

        return new UuserDao(getOracleConnection());
    }

    public OperationDao getOperationDao() throws Exception{
        System.out.println("getOperationDao() returned connection");

        return new OperationDao(getOracleConnection());
    }

    public MedicineDao getMedicineDao() throws Exception{
        System.out.println("getMedicineDao() returned connection");

        return new MedicineDao(getOracleConnection());
    }

    public ProcedureDao getProcedureDao() throws Exception{

        System.out.println("getProcedureDao() returned connection");
        return new ProcedureDao(getOracleConnection());
    }


}
