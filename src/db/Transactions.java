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
public class Transactions 
{
    private Connection conn;
    Statement st;
    ResultSet rs;
    String sql;
    int  x=0;
    
    Transactions() throws SQLException
    {
         DBConnection dbConn = new DBConnection();
         conn = dbConn.getConn();
    }
    /**
     * 
     * @throws SQLException 
     */
    public void delete()throws SQLException
    {
        int mid = 3, fid=3;
        String date = "02/05/2015";
        
        sql = "delete MEDICINE_FORMULA"
                +" where MEDICINE_ID = 1" 
                +" and FORMULA_ID= 1"
                +" and dosage = 32"
                ;
        st = conn.createStatement();
        x = st.executeUpdate(sql);
        
        conn.commit();
        closeConnection();  
        
        System.out.println(x + " Rows deleted !!");
    }

    private void closeConnection() throws SQLException 
    {
        st.close();
        conn.close();
    }
    public void edit() throws SQLException
    {
        sql = "update MEDICINE set REG_CODE= 'test new code' "
                +" where id = 3"
                ;
        st = conn.createStatement();
        x = st.executeUpdate(sql);
        conn.commit();
        closeConnection();  
        
        System.out.println(x + " Rows updated !!");
        
    }
    public void create() throws SQLException
    {
        sql = "insert into MEDICINE_FORMULA "
                +" (MEDICINE_ID, FORMULA_ID, QUANTITY, DOSAGE, EXPIRATION)"
                +" values (3 , 5 , 777, 16 , "
                + " to_date('23-07-2003','dd-mm-yyyy')"
                + ")"
                ;
        st = conn.createStatement();
        x = st.executeUpdate(sql);
        conn.commit();
        closeConnection();  
        
        System.out.println(x + " Rows added !!");
    }
    public void view() throws SQLException
    {
        sql =" select m.NAME , to_char(mf.EXPIRATION , 'dd/mm/yyyy' ) "
                +" from MEDICINE_FORMULA mf , MEDICINE m "
                +" where mf.FORMULA_ID = 4"
                +" and mf.MEDICINE_ID = m.ID"
                ;
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next())
        {
            System.out.println(rs.getString(1) + " - " + rs.getString(2));
        }
        closeConnection();  
    }
}
