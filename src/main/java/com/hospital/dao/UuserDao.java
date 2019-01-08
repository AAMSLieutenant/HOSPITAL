package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.model.Uuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UuserDao {
    private final Connection connection;//Объект соединения с БД

    public UuserDao(Connection connection)
    {
        this.connection = connection;
        System.out.println("UuserDao received connection");
    }

    public String authorize(String login, String password) throws Exception{

        System.out.println("UuserDao authorize()");
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
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return new String("-1");
    }

}
