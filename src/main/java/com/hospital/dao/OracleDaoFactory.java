/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital.dao;


import com.hospital.interfaces.DaoFactory;
import com.hospital.interfaces.IPatientDao;

import oracle.jdbc.pool.OracleDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 *
 * @author Rostislav Stakhov
 */
public class OracleDaoFactory implements DaoFactory{




    private String user = "admin";//Логин пользователя
    private String password = "admin";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера

    public DataSource getOracleDataSource(){
        OracleDataSource oracleDS = null;
        try {
            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            oracleDS.setUser("admin");
            oracleDS.setPassword("admin");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;

            con = oracleDS.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT card_id, p_name, p_surname, p_patronymic, p_sex FROM patient");
            while(rs.next()){
                System.out.println("card_id="
                        +rs.getInt("card_id")
                        +", p_name="
                        +rs.getString("p_name")
                        +", p_surname="
                        +rs.getString("p_surname")
                +", p_patronymic="
                        +rs.getString("p_patronymic")
                +", p_sex="
                        +rs.getString("p_sex"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }


//    private static void testDataSource(String dbType) {
//        DataSource ds = null;
//        if("mysql".equals(dbType)){
//            ds = MyDataSourceFactory.getMySQLDataSource();
//        }else if("oracle".equals(dbType)){
//            ds = MyDataSourceFactory.getOracleDataSource();
//        }else{
//            System.out.println("invalid db type");
//            return;
//        }

//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            con = ds.getConnection();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery("select empid, name from Employee");
//            while(rs.next()){
//                System.out.println("Employee ID="+rs.getInt("empid")+", Name="+rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//
//    private String user = "admin";//Логин пользователя
//    private String password = "admin";//Пароль пользователя
//    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
//    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера
//    private Connection connection;
//
////    private static final Logger log=Logger.getLogger(OracleDaoFactory.class);
////    static {
////        PropertyConfigurator.configure("log4j.properties");
////    }
//
//    public OracleDaoFactory()
//    {
//        try
//        {
////            log.info("-------- Oracle JDBC Connection Testing ------");
//            Class.forName(driver);//Регистрируем драйвер
//            connection=DriverManager.getConnection(url, user, password);
//        }
//        catch (ClassNotFoundException e)
//        {
////            log.error("JDBC Driver class load failture");
//            e.printStackTrace();
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
////        log.info("Oracle JDBC Driver Registered!");
//
//    }
//
//
//    public Connection getConnection() throws SQLException
//    {
////        log.info("DB Connection returned");
////        return DriverManager.getConnection(url, user, password);
//        return this.connection;
//
//        //return null;
//    }
//
//    public IPatientDao getPatientDao() throws Exception
//    {
//            System.out.println("getPatientDao() returned connection");
////            return new PatientDao(DriverManager.getConnection(url, user, password));
//            return new PatientDao(this.getConnection());
//
////            log.info("Employee class connection returned");
//
//
//
//
//    }

    }
