import java.sql.*;
public class JDBCCallable{
    public static void main ( String [] args ){
        String connectionURL = Login.DRIVER + Login.DB;
        Connection conn = null ;
        try {
            Class.forName (Login.DRIVER_CLASS); 
            System .out.println ("Attempting connection to " 
                    + connectionURL );
            conn = DriverManager.getConnection 
                    (connectionURL,Login.USER,Login.PWD );
            System.out.println ("Connection successful ");
            
            double price = 2.3;
            int greaterThan = 0;
            
            String storedProc = "{call countBeers(?,?,?,?)}";
            CallableStatement callableStatement = 
                    conn.prepareCall(storedProc);
            callableStatement.clearParameters(); 
            
            callableStatement.setDouble(1, price);
            callableStatement.setInt(2, greaterThan);
            
            callableStatement.registerOutParameter
                        (3, java.sql.Types.INTEGER);
            callableStatement.registerOutParameter
                        (4, java.sql.Types.DOUBLE);
 
            // execute store procedure
            ResultSet results = 
                    callableStatement.executeQuery();
            
            System .out.println ("Results:");
            while (results.next ()){
                String name = results.getString ("bar");
                String manf = results.getString ("beer");
                price =   results.getDouble ("price");
                System.out. println 
                    ("Name = " + name + ", Manufacturer = " + manf 
                        + ", price = " + price);
            }
            
            int  total = callableStatement.getInt(3);
            double bounds = callableStatement.getDouble(4);
            System.out. println
                ("Total rows="+total+", bounds = "+bounds);
            
            callableStatement.close ();
            conn.close ();
        }
        catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    }
}