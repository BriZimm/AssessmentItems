import java.sql .*;
public class JDBCUpdate {
	
	//define the driver to use
		public static String driver = Login.DRIVER_CLASS;
		//the database info
	    public static String dbName =Login.DB;  
	    public static String user = Login.USER;
	    public static String pw = Login.PWD;
	    // define the DB connection URL to use
	    public static String connectionURL = Login.DRIVER+dbName; 
	    public static Connection conn = null ;
	    public static Statement stmt;
	    public static ResultSet results;
	    
    public static void main ( String [] args ) {
        String connectionURL = Login.DRIVER + Login.DB;
        Connection conn = null ;
        Statement stmt = null ;
        try {
            Class.forName (Login.DRIVER_CLASS); 
            System .out.println
                ("Attempting connection to " + connectionURL );
            conn = DriverManager.getConnection
                    (connectionURL,Login.USER,Login.PWD );
            System .out.println ("Connection successful ");
        }
        catch ( ClassNotFoundException e) 
        { System .out.println (" Error " + e );} 
        catch ( SQLException e) 
        { System.out. println (" Error " + e);} 
        catch ( Exception e) 
        { System .out.println (" Error " + e );}; 
        try {
            conn.setAutoCommit( false ); 
            // Override the default of auto - committing
            // The next two updates must succeed as a pair
            stmt = conn.createStatement();
            String query = "INSERT into Beers " 
                +" VALUES ('Spotted Cow', 'NewGlarus')";
            int outcome = stmt.executeUpdate(query );
            query = "INSERT into Sells " 
                +" VALUES ('Joe''s', 'Spotted Cow', 2.75) ";
            outcome = stmt.executeUpdate(query );
            conn.commit(); 
            // If no exception , commit the two-update transaction
        }
        catch ( SQLException e) { 
            System.out. println (" Error " + e);
            try {
                conn.rollback (); // Exception requires rollback
            } 
            catch ( SQLException e1) {}
        }
        finally { // Always executes -- whether or not exception
            try {
                stmt.close ();
                conn.close ();
            } 
            catch ( SQLException e1) {}
        }
    }
    
    // A query to update a specific CDAI (identified by id such as A1) Rarely used except on initial setup of database
    public static boolean UpdateCDAI() {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement();
	        String query = "UPDATE grades SET CDAI=SUBSTRING(grades.criteria, 1, 2);";
	        int outcome = stmt.executeUpdate(query );
	        return true;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return true;
    }
}

