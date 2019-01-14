package com.hospital.dao;


import com.hospital.model.Uuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 * @author Rostislav Stakhov
 * Dao class for working with the USer object
 */
public class UuserDao {
    private final Connection connection;//Объект соединения с БД
    private static final Logger logger= LoggerFactory.getLogger(ProcedureDao.class);

    public UuserDao(Connection connection)
    {
        this.connection = connection;
        logger.info("UuserDao received connection");
    }

    public String authorize(String login, String password) throws Exception{

        logger.info("UuserDao authorize()");
        String statement="SELECT login, password FROM uuser";
        PreparedStatement ps=connection.prepareStatement(statement);
        ResultSet rs=ps.executeQuery();
        Uuser user;
        String log;
        String pass;
        while(rs.next()){
            log=rs.getString("login");
            pass=rs.getString("password");
            if((log.equals(login))&&(pass.equals(password))){
                return login;
            }

        }
        try{
            ps.close();
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return new String("-1");
    }

}
