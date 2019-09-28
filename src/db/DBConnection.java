/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class DBConnection 
{
    final static private String USER = "doaa";
    final static private String PASSWORD = "doaa";
    final static private String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
    private Connection conn;
    
    public DBConnection() throws SQLException
    {
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            
            System.out.println(">> connected...");

        }
        catch(SQLException ex)
        {
            System.out.println(">>"+ ex.getMessage());
        }
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }
}
