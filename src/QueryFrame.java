import javax.swing .*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException ;

/**
* Creates a GUI that allows the user to enter a SQL query.It then
* obtains a ResultSetTableModel for the query and uses it to display
* the results of the query in a JTable .
**/
public class QueryFrame extends JFrame {
    private ResultSetTableModelFactory factory; // A factory to obtain the table model
    private JTextField query; // A field to enter a query in
    private JTable table; // The table for displaying data
 
    /**
    * Create the GUI and associate with it an event listener that
    * updates the table when the user enters a new query .
    **/
    public QueryFrame( ResultSetTableModelFactory f) {
        super ("QueryFrame");
        addWindowListener (new WindowAdapter() {
            public void windowClosing ( WindowEvent e) { System.exit (0); }
        });
        this.factory = f;
        // Create the Swing components
        query = new JTextField(); // Lets the user enter a query
        table = new JTable(); // Displays the table
        
        // Place the components within this window
        Container contentPane = getContentPane ();
        contentPane.add (query , BorderLayout.NORTH );
        contentPane.add (new JScrollPane ( table ), BorderLayout.CENTER );
        
        // Connect entry of query in the JTextField with the result
        // set that we want displayed in the JTable
        query.addActionListener (new ActionListener() {
            public void actionPerformed ( ActionEvent e) {
                displayQueryResults(query.getText());
            }
        });
    }
    
    /**
    * Use the SQL query string and the ResultSetTableModelFactory
    * object to create a TableModel that holds the results of the
    * database query.
    **/
    public void displayQueryResults(String q) {
        try {
            // Use the factory object to obtain a
            // TableModel object for the query results
            table.setModel(factory.getResultSetTableModel(q));
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog ( QueryFrame.this ,
                new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()
                });
        }
    }
    
    /**
    * The main method tests the class by taking the DB name from our Constants class
    **/
    public static void main ( String args []) throws Exception {
        // Create the factory object that holds the database connection 
        ResultSetTableModelFactory factory = new ResultSetTableModelFactory 
                (Login.DRIVER_CLASS,Login.DRIVER,Login.DB,Login.USER,Login.PWD);
        // Create a QueryFrame component that uses the factory object .
        QueryFrame qf = new QueryFrame( factory );
        qf.setSize (500 , 600);
        qf.setVisible( true );
    }
}