import java.sql.*;

public class JDBCQuery{
    public static void main ( String [] args ){
        //define the driver to use
        String driver = Login.DRIVER_CLASS;
        
        //the database name
        String dbName =Login.DB;  
        String user = Login.USER;
        String pw = Login.PWD;
        
        // define the DB connection URL to use
        String connectionURL = Login.DRIVER+dbName; 
        Connection conn = null ;
        try{
            Class.forName (driver ); // register the JDBC driver
            System.out.println ("Attempting connection to " 
                                + connectionURL );
            conn = DriverManager.getConnection (connectionURL,user,pw );
            System.out.println ("Connection successful ");
            Statement stmt = conn.createStatement ();
            ResultSet results = stmt.executeQuery
                ("SELECT fname , lname FROM student WHERE uni_id=6");
            System.out.println (" Results:");
            while ( results.next ()) {
                String fname = results.getString ("fname");
                String lname = results.getString ("lname");
                System.out. println(fname + " " + lname );
            }
            stmt.close ();
            conn.close ();
        }
        catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    }
}