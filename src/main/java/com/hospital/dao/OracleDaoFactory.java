package com.hospital.dao;



import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;





/**
 * @author Rostislav Stakhov
 * Dao class for creating the basic connection objects and delegating them to the appropriate Dao classes
 */
public class OracleDaoFactory{




    private String user = "admin";//Логин пользователя
    private String password = "admin";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private OracleDataSource oracleDS = null;
    private Connection connection = null;
    private static final Logger logger= LoggerFactory.getLogger(OracleDaoFactory.class);


    public OracleDaoFactory()
    {
        try
        {
            logger.info("-------- Oracle JDBC Connection Testing ------");
            Class.forName(driver);//Регистрируем драйвер
            connection=getOracleConnection();
        }

        catch (ClassNotFoundException e)
        {
            logger.error("JDBC Driver class load failture");
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        logger.info("Oracle JDBC Driver Registered!");

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
        logger.info("DB Connection returned");
        return this.connection;

    }
//
    public PatientDao getPatientDao() throws Exception
    {
            logger.info("getPatientDao() returned connection");

            return new PatientDao(getOracleConnection());


    }

    public AppointmentDao getAppointmentDao() throws Exception{
        logger.info("getAppointmentDao() returned connection");

        return new AppointmentDao(getOracleConnection());
    }

    public DiagDao getDiagDao() throws Exception{
        logger.info("getDiagDao() returned connection");

        return new DiagDao(getOracleConnection());
    }

    public UuserDao getUuserDao() throws Exception{
        logger.info("getUuserDao() returned connection");

        return new UuserDao(getOracleConnection());
    }

    public OperationDao getOperationDao() throws Exception{
        logger.info("getOperationDao() returned connection");

        return new OperationDao(getOracleConnection());
    }

    public MedicineDao getMedicineDao() throws Exception{
        logger.info("getMedicineDao() returned connection");

        return new MedicineDao(getOracleConnection());
    }

    public ProcedureDao getProcedureDao() throws Exception{

        logger.info("getProcedureDao() returned connection");
        return new ProcedureDao(getOracleConnection());
    }


}
