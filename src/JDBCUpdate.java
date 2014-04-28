import java.sql .*;
public class JDBCUpdate {
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
}

