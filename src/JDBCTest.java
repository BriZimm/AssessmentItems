import java.sql.*;
public class JDBCTest{
  public static void main(String [] args){
      String db = Login.DB;
      String user = Login.USER;
      String pw = Login.PWD;

      try{
          Class.forName(Login.DRIVER_CLASS);  // register the driver
          String dbURL = Login.DRIVER+db;
          System.out.println("Attempting connection");
          Connection conn = DriverManager.getConnection(dbURL,user,pw);
          System.out.println("Connection successfully made");
          conn.close();
      }
      catch (SQLException e) 
      {System.out.println("SQL Error " + e.getMessage());}
      catch (Exception e) 
      {System.out.println("General Error " + e.getMessage());}
  }
}
