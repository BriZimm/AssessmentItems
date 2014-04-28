import java.sql.*;
public class JDBCPreparedQuery {
    public static void main ( String [] args ){
        String connectionURL = Login.DRIVER + Login.DB;
        Connection conn = null ;
        try{
            Class.forName (Login.DRIVER_CLASS); 
            System .out.println ("Attempting connection to " 
                        + connectionURL );
            conn = DriverManager.getConnection 
                    (connectionURL,Login.USER,Login.PWD );
            System .out.println ("Connection successful ");
     
            String beerManufacturer ="Miller Brewing";
            String query ="SELECT * FROM Beers where manf=?";
            PreparedStatement pstmt=conn.prepareStatement(query);
            pstmt.clearParameters(); 
        
            pstmt.setString(1,beerManufacturer);
            ResultSet results = pstmt.executeQuery ();
            System .out.println (" Results \n");
            while ( results.next ()){
                String name = results.getString ("name");
                String manf = results.getString ("manf");
                System.out. println 
                    (" Name = " + name + " Manufacturer = " + manf );
            }
            pstmt.close ();
            conn.close ();
        }
        catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    }
}