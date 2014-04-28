import java.sql .*;
import javax.swing.table.*;
import javax.swing.event.*;
/**
* Takes a JDBC ResultSet object and implements the needed methods
* from the abstract base class .
**/
public class ResultSetTableModel extends AbstractTableModel {
    private ResultSet results ; // The ResultSet to interpret to bind to the model
    private ResultSetMetaData metadata ; // Additional information about the results
    private int numcols , numrows ; // How many rows and columns in the table
   
    /**
    * Creates a TableModel from a ResultSet .
    **/
    ResultSetTableModel(ResultSet results) throws SQLException {
        this.results = results ;
        metadata = results.getMetaData (); // Get metadata on them
        numcols = metadata.getColumnCount (); // How many columns ?
        results.last (); // Here ’s the kludge -- Move to last row
        numrows = results.getRow (); // And use getRow to determine how many rows
    }
    
    // These two TableModel methods return the size of the table
    public int getColumnCount () { return numcols ; }
    public int getRowCount () { return numrows ; }
    
    // This TableModel method returns columns names from the
    // ResultSetMetaData so that we can provide column labels in the
    // JTable display
    public String getColumnName(int column) {
        try {
            return metadata.getColumnLabel(column +1);
        } catch (SQLException e) { return e. toString (); }
    }
    
    /**
    * Returns the value , as a String , at each cell of the table .
    * Note that SQL row and column numbers start at 1, but TableModel
    * column numbers start at 0.
    **/
    public Object getValueAt(int row , int column) {
        try {
            results.absolute(row +1); // Go to the specified row
            Object o = results.getObject(column +1); // Get value of the column
            if (o == null) return null ;
            else return o. toString (); // Convert it to a string
        } 
        catch ( SQLException e) { return e. toString (); }
    }
    
    public void close() {
        try { results.getStatement(). close(); }
        catch ( SQLException e) {};
    }
    
    protected void finalize () { close(); }
}