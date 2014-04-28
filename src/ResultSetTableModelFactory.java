import java.sql.*;
import javax.swing.table.*;
/**
* Encapsulate a JDBC database connection along with a method that ,
* given a SQL query as a string , returns a ResultSetTableModel object
* suitable for display in a JTable
**/
public class ResultSetTableModelFactory {
    Connection connection;
    
    /** The constructor method uses the argument to create db Connection */
    public ResultSetTableModelFactory(String driverClassName, String dbURL, String dbname, String user, String pwd) throws ClassNotFoundException , SQLException
    {
        Class driver = Class.forName( driverClassName );
        connection = DriverManager.getConnection( dbURL+dbname, user,pwd );
    }
    
    /**
    * Takes a SQL query , pass it to the database , obtain the results
    * as a ResultSet , and return a ResultSetTableModel object
    **/
    public ResultSetTableModel getResultSetTableModel(String query)throws SQLException
    {
        if ( connection == null )
        throw new IllegalStateException ("Connection already closed .");
        // Create a Statement object that will be used to execute the
        // query . The arguments specify that the returned ResultSet
        // will be scrollable and read - only . The scrollable property
        // is a necessary kludge to determine the number of rows in
        // the table / ResultSet
        Statement statement = connection. createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY );
        // Run the query , creating a ResultSet
        ResultSet r = statement.executeQuery( query );
        // Create and return a TableModel for the ResultSet
        return new ResultSetTableModel(r);
    }
    
    public void close () {
        try { connection.close(); } // Try to close the connection
        catch ( Exception e) {} // Do nothing on error
        connection = null ;
    }
    
    protected void finalize () { 
        close (); 
    }
}
