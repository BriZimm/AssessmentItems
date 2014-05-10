import java.awt.Color;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;



public class adminEmphasis {

	JFrame adminFrame;
	
	/**
	 * Create the application.
	 */
	public adminEmphasis() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Start Frame
		adminFrame = new JFrame();
		adminFrame.setTitle("CDAI Administration");
		adminFrame.pack();
		adminFrame.setSize(300, 300);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblEditEmphasisList = new JLabel("Edit Emphasis List");
		lblEditEmphasisList.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEditEmphasisList.setForeground(Color.BLACK);
		panel.add(lblEditEmphasisList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Cancel");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JComboBox<String> emphasisBox = new JComboBox<String>();
		emphasisBox.setEditable(true);
		fillEmphasisList(emphasisBox);
		panel_2.add(emphasisBox);
		
	}
	
	private void fillEmphasisList(JComboBox<String> emphasisBox) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT description from stu_emphasis";
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				String emphasisname = MySQLConnect.results.getString("description");
				emphasisBox.addItem(emphasisname);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
	}
	
	 private void addEmphasis(String addedEmphasis) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "INSERT INTO stu_emphasis (description) VALUE ('" + addedEmphasis + "')";
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
	 // NOT_DONE
	 private void removeEmphasis() {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT avg( score ) AS 'average', grades.CDAI AS 'CDAI' FROM grades JOIN criteria ON criteria.name = grades.criteria GROUP BY grades.CDAI ORDER BY avg( score ) ASC LIMIT 1;";
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}
