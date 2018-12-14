/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital.dao;


import com.hospital.interfaces.DaoFactory;
import com.hospital.interfaces.IPatientDao;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Rostislav Stakhov
 */
public class OracleDaoFactory implements DaoFactory{

    private String user = "admin";//Логин пользователя
    private String password = "admin";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера

    private static final Logger log=Logger.getLogger(OracleDaoFactory.class);
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    public OracleDaoFactory()
    {
        try
        {
            log.info("-------- Oracle JDBC Connection Testing ------");
            Class.forName(driver);//Регистрируем драйвер
        }
        catch (ClassNotFoundException e)
        {
            log.error("JDBC Driver class load failture");
            e.printStackTrace();
        }
        log.info("Oracle JDBC Driver Registered!");

    }


    public Connection getConnection() throws SQLException
    {
        log.info("DB Connection returned");
        return DriverManager.getConnection(url, user, password);

        //return null;
    }

    public IPatientDao getPatientDao() throws Exception
    {

            log.info("Employee class connection returned");
            return new PatientDao(DriverManager.getConnection(url, user, password));


        //return null;
    }

    }
