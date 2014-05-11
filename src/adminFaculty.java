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



public class adminFaculty {

	JFrame adminFrame;
	private JTextField AddedFaculty;
	private JTextField RemovedFaculty;
	private JComboBox<String> facultyBox;
	
	/**
	 * Create the window.
	 */
	public adminFaculty() {
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
		
		JLabel lblEditFacultyList = new JLabel("Edit Faculty List");
		lblEditFacultyList.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEditFacultyList.setForeground(Color.BLACK);
		panel.add(lblEditFacultyList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminFrame.setVisible(false);
				adminFrame.dispose();
			}
		});
		panel_1.add(btnOK);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		facultyBox = new JComboBox<String>();
		facultyBox.setBounds(78, 6, 187, 27);
		facultyBox.setEditable(true);
		fillFacultyList(facultyBox);
		panel_2.add(facultyBox);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setBounds(19, 12, 58, 16);
		panel_2.add(lblCurrent);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setBounds(19, 64, 58, 16);
		panel_2.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(19, 112, 58, 16);
		panel_2.add(lblRemove);
		
		RemovedFaculty = new JTextField();
		RemovedFaculty.setColumns(10);
		RemovedFaculty.setBounds(78, 106, 134, 28);
		panel_2.add(RemovedFaculty);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(212, 107, 82, 29);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String faculty = RemovedFaculty.getText();
				// Search
				if (faculty.length() != 0) {
					removeFaculty(faculty);
					RemovedFaculty.setText("");
					facultyBox.removeAllItems();
					fillFacultyList(facultyBox);
				}
			}
		});
		panel_2.add(btnRemove);
		
		AddedFaculty = new JTextField();
		AddedFaculty.setBounds(78, 58, 134, 28);
		panel_2.add(AddedFaculty);
		AddedFaculty.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(212, 59, 82, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String faculty = AddedFaculty.getText();
				// Search
				if (faculty.length() != 0) {
					addFaculty(faculty);
					AddedFaculty.setText("");
					facultyBox.removeAllItems();
					fillFacultyList(facultyBox);
				}
			}
		});
		panel_2.add(btnAdd);
		
	}
	
	private void fillFacultyList(JComboBox<String> facultyBox) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT name from faculty";
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			while(MySQLConnect.results.next()) {
				String faculty = MySQLConnect.results.getString("name");
				facultyBox.addItem(faculty);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
	}
	
	 private void addFaculty(String addedFaculty) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "INSERT INTO faculty (name) VALUE ('" + addedFaculty + "')";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
	
	 private void removeFaculty(String removedFaculty) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM faculty WHERE `name` = '" + removedFaculty + "'";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}
