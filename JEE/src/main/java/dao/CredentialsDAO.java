package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.credentialsC;
import tools.MySqlConnection;

public class CredentialsDAO {

    public static credentialsC checkcrdentials(String l, String p) {
        credentialsC res= new credentialsC();

        String sql= "select * from logins where login =? and password =?";
        PreparedStatement ps=null;
        ResultSet rs= null;
        Connection con= MySqlConnection.getConnectionMysql();

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, l);
            ps.setString(2, p);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("erreur avec PS");
        }

        try {
            rs= ps.executeQuery();
            if(rs.next())
            {
                res.setIdcredential(rs.getInt(1));
                res.setLogin(rs.getString(2));
                res.setPassword(rs.getString(3));

            }
            else
                return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("erreur avec RS");
            return null;
        }

        return res;
    }



}
