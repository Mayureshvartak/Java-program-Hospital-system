
package com.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mayuresh
 */
public class Account {
    //step1
    String user="root";
    String pass="";
    String url="jdbc:mysql://localhost:3306/hospital";
    String driver="com.mysql.jdbc.Driver";
   Connection con;
    //step2
    void dbConnect() throws ClassNotFoundException, SQLException{
        Class.forName(driver); //load the driver
        con=DriverManager.getConnection(url, user, pass);
    }
    void dbclose() throws SQLException{
      con.close();  
    }
   public void insertPatientinfo(String name,String contact,String age,String doctor_name,String date,String time) throws ClassNotFoundException, SQLException{
     dbConnect();
     String sql="insert into patient(name,contact,age,dname,date,time)values(?,?,?,?,?,?)";
     PreparedStatement pstmt =con.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setString(2, contact);
      pstmt.setString(3, age);
      pstmt.setString(4, doctor_name);
      pstmt.setString(5, date);
      pstmt.setString(6, time);
      
      pstmt.executeUpdate();
     dbclose();
 }

   public ResultSet fetchPatient() throws ClassNotFoundException, SQLException{
        dbConnect();
    String sql="select * from Patient where date=?";
     PreparedStatement pstmt =con.prepareStatement(sql);
       DateFormat df=DateFormat.getDateInstance();
       String d=df.format(new Date());
     pstmt.setString(1, d);
     ResultSet rst=pstmt.executeQuery();
     return rst;
   }
  public ResultSet fetchPatientTomorrow() throws ClassNotFoundException, SQLException{
        dbConnect();
        DateFormat df=DateFormat.getDateInstance();
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,1);
        Date d1=c.getTime();//tomorrow date
        String d2=df.format(d1);
    String sql="select * from Patient where date=?";
     PreparedStatement pstmt =con.prepareStatement(sql);
      
     pstmt.setString(1, d2);
     ResultSet rst=pstmt.executeQuery();
     return rst;
}
}

