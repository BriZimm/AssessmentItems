import java.sql.*;

// Test Class to Verify the Database is being connected to the Application
public class MySQLConnect {
	//define the driver to use
	public static String driver = Login.DRIVER_CLASS;
	//the database info
    public static String dbName =Login.DB;  
    public static String user = Login.USER;
    public static String pw = Login.PWD;
    // define the DB connection URL to use
    public static String connectionURL = Login.DRIVER+dbName; 
    public static Connection conn = null;
    public static Statement stmt;
    public static ResultSet results;
	
	public void connect() {
		  
		  String url = "jdbc:mysql://localhost:3306/"+dbName;
		  try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection (connectionURL,user,pw );
		      stmt = conn.createStatement();
		  } catch (Exception e) {
			  System.out.println("Failure!");
			  e.printStackTrace();
		  }
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	
} 