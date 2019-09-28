/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.controller;

import db.DBConnection;
import java.sql.*;
import java.util.Vector;
import login.model.UserLogin;


/**
 *
 * @author Administrator
 */
public class LoginHandler 
{
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private String sql;
    
     public LoginHandler() throws SQLException
     {
          DBConnection dbConn = new DBConnection();
          conn = dbConn.getConn();
     }
     /**
      * check valid uaser 
      * @param userName
      * @param Password
      * @return 
      */
     
     public int isValid(UserLogin userBean) throws SQLException
     {
         int total=0;
         String userName = userBean.getName();
         String Password = userBean.getPassword();
                 
        sql =" select count(ID) from USER_LOGIN "
                + " where NAME='"+userName+"' and PASSWORD='"+Password+"' "
                ;
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
             total = rs.getInt(1);
        }
        return total;
     }
     /**
      * 
      * @param userBean
      * @return 
      */
     public int addUser(UserLogin userBean) throws SQLException
     {
          int total=0;
          String userName = userBean.getName();
          String Password = userBean.getPassword();
         
          sql = "insert into USER_LOGIN "
                +" (NAME, PASSWORD) "
                +" values ('"+userName+"' , '"+Password+"' )"
                ;
        st = conn.createStatement();
        total = st.executeUpdate(sql);
        conn.commit();
        
        return total;
     }
     /**
      * 
      * @param userBean
      * @return
      * @throws SQLException 
      */
      public int deleteUser(UserLogin userBean) throws SQLException
     {
          int total=0;
          int id = userBean.getId();
          
          sql = "delete USER_LOGIN where ID ="+id;
        st = conn.createStatement();
        total = st.executeUpdate(sql);
        conn.commit();
        
        return total;
     }
             
     /**
      * 
      * @param userBean
      * @return
      * @throws SQLException 
      */
     public int editUser(UserLogin userBean) throws SQLException
     {
          int total=0;
          String userName = userBean.getName();
          String Password = userBean.getPassword();
          int id = userBean.getId();
          
          sql = "update USER_LOGIN "
                +" set NAME ='"+userName+"', PASSWORD = '"+Password+"' "
                +" where ID ="+id
                ;
        st = conn.createStatement();
        total = st.executeUpdate(sql);
        conn.commit();
        
        return total;
     }
     /**
      * 
      * @return
      * @throws SQLException 
      */
     public Vector getUsers() throws SQLException
     {
        Vector data = new Vector();
        
              
        sql =" select * from USER_LOGIN ";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            UserLogin userBean = new UserLogin();
            userBean.setId( rs.getInt("ID") );
            userBean.setName( rs.getString("NAME") );
            userBean.setPassword( rs.getString("PASSWORD") );
             
             data.addElement(userBean);
        }
        return data;
     }
     /** 
      * close connection
      * @throws SQLException 
      */
     public void closeConnection() throws SQLException 
     {
        if(rs!= null) 
            rs.close();
        
        st.close();
        conn.close();
     }
}
