import java.sql.*;

public class MySQLClass {
	
	public static void main(String[] args) {
		  String dbName = "Assessment";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root"; 
		  String password = "";
		  
		  String url = "jdbc:mysql://localhost:3306/"+dbName;
		  try {
			  Class.forName(driver).newInstance();
			  System.out.println("Attempting to connect...");
			  Connection conn = DriverManager.getConnection(url, userName, password);
			  System.out.println("Success");
			  
			  conn.close();
		  } catch (Exception e) {
			  System.out.println("Failure!");
			  e.printStackTrace();
		  }
	}
} 