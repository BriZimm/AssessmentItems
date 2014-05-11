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



public class adminStatus {

	JFrame adminFrame;
	private JTextField AddedStatus;
	private JTextField RemovedStatus;
	private JComboBox<String> statusBox;
	
	/**
	 * Create the window.
	 */
	public adminStatus() {
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
		
		JLabel lblEditStatusList = new JLabel("Edit Status List");
		lblEditStatusList.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEditStatusList.setForeground(Color.BLACK);
		panel.add(lblEditStatusList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("OK");
		panel_1.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminFrame.setVisible(false);
				adminFrame.dispose();
				MainWindow.StatusComboBox.removeAllItems();
				MainWindow.fillStatusComboBox(MainWindow.StatusComboBox);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		statusBox = new JComboBox<String>();
		statusBox.setBounds(78, 6, 191, 27);
		statusBox.setEditable(true);
		fillStatusList(statusBox);
		panel_2.add(statusBox);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setBounds(19, 12, 58, 16);
		panel_2.add(lblCurrent);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setBounds(19, 64, 58, 16);
		panel_2.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(19, 112, 58, 16);
		panel_2.add(lblRemove);
		
		RemovedStatus = new JTextField();
		RemovedStatus.setColumns(10);
		RemovedStatus.setBounds(78, 106, 134, 28);
		panel_2.add(RemovedStatus);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(212, 107, 82, 29);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String emphasis = RemovedStatus.getText();
				// Search
				if (emphasis.length() != 0) {
					removeStatus(emphasis);
					RemovedStatus.setText("");
					statusBox.removeAllItems();
					fillStatusList(statusBox);
				}
			}
		});
		panel_2.add(btnRemove);
		
		AddedStatus = new JTextField();
		AddedStatus.setBounds(78, 58, 134, 28);
		panel_2.add(AddedStatus);
		AddedStatus.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(212, 59, 82, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String emphasis = AddedStatus.getText();
				// Search
				if (emphasis.length() != 0) {
					addStatus(emphasis);
					AddedStatus.setText("");
					statusBox.removeAllItems();
					fillStatusList(statusBox);
				}
			}
		});
		panel_2.add(btnAdd);
	}
	
	private void fillStatusList(JComboBox<String> statusBox) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT status_description from stu_status";
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			while(MySQLConnect.results.next()) {
				String emphasisname = MySQLConnect.results.getString("status_description");
				statusBox.addItem(emphasisname);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
	}
	
	 private void addStatus(String addedStatus) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "INSERT INTO stu_status (status_description) VALUE ('" + addedStatus + "')";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }

	 private void removeStatus(String removedStatus) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM stu_status WHERE `status_description` = '" + removedStatus + "'";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}

