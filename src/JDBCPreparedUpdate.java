import java.sql .*;
public class JDBCPreparedUpdate{
    public static void main ( String [] args ){
        String connectionURL = Login.DRIVER + Login.DB;
        Connection conn = null ;
        PreparedStatement pstmt = null ;
        try{
            Class.forName (Login.DRIVER_CLASS); 
            System .out.println ("Attempting connection to " 
                                + connectionURL );
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
        try{
            conn.setAutoCommit ( false ); 
            //Override the default of auto - committing
            String beerName = "New Beer2";
            String beerManufacturer ="NewGlarus";
            String query ="INSERT INTO Beers VALUES(?,?)";
            pstmt=conn.prepareStatement(query);
            pstmt.clearParameters(); 
            // Not necessary if you are setting all of them
            
            pstmt.setString(1,beerName);
            pstmt.setString(2,beerManufacturer);
            int outcome = pstmt.executeUpdate();
            conn.commit(); 
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
                pstmt.close ();
                conn.close ();
            } 
            catch ( SQLException e1) {}
        }
    }
}

