import java.awt.Color;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;



public class adminCriteria {

	JFrame adminFrame;
	private JTextField addedCriteriaName;
	private JTextField removedCriteria;
	private JTextArea txtDescription;
	
	/**
	 * Create the window.
	 */
	public adminCriteria() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Start Frame
		adminFrame = new JFrame();
		adminFrame.setTitle("CDAI Administration");
		//adminFrame.pack();
		adminFrame.setSize(300, 250);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblEditCriteria = new JLabel("Edit Criteria");
		lblEditCriteria.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEditCriteria.setForeground(Color.BLACK);
		panel.add(lblEditCriteria);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("OK");
		panel_1.add(btnOK);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setBounds(22, 12, 38, 16);
		panel_2.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(21, 130, 58, 16);
		panel_2.add(lblRemove);
		
		removedCriteria = new JTextField();
		removedCriteria.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		removedCriteria.setToolTipText("Enter Criteria Name to delete.");
		removedCriteria.setColumns(10);
		removedCriteria.setBounds(84, 124, 126, 28);
		panel_2.add(removedCriteria);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(210, 125, 82, 29);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String emphasis = removedCriteria.getText();
				// Search
				if (emphasis.length() != 0) {
					removeCriteria(emphasis);
					removedCriteria.setText("");
				}
			}
		});
		panel_2.add(btnRemove);
		
		addedCriteriaName = new JTextField();
		addedCriteriaName.setBounds(84, 6, 126, 28);
		panel_2.add(addedCriteriaName);
		addedCriteriaName.setColumns(10);
		
		txtDescription = new JTextArea();
		txtDescription.setBounds(87, 39, 194, 73);
		panel_2.add(txtDescription);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(210, 7, 82, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String criteria = addedCriteriaName.getText();
				String description = txtDescription.getText();
				// Search
				if (criteria.length() != 0 && description.length() != 0) {
					addCriteria(criteria, description);
					addedCriteriaName.setText("");
					txtDescription.setText("");
				}
			}
		});
		panel_2.add(btnAdd);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 113, 260, 12);
		panel_2.add(separator);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 67, 73, 16);
		panel_2.add(lblDescription);
		
	}
	
	 private void addCriteria(String addedCriteria, String description) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String cdai = addedCriteria.substring(0,2);
			String unique_id = addedCriteria.substring(0,5);
			String query = "INSERT INTO criteria (CDAI, unique_id, name, description) VALUE ('" + cdai + "', '" + unique_id + "', '" + addedCriteria + "','" + description + "')";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
	 // NOT_DONE
	 private void removeCriteria(String removedCriteria) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM stu_emphasis WHERE `name` = '" + removedCriteria + "'";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}
