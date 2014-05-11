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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JSeparator;

public class adminStudents {

	JFrame adminFrame;
	private JTextField txtSearchUniId;
	private JTextField txtFirst;
	private JTextField txtMiddle;
	private JTextField txtLast;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JComboBox<String> emphasisComboBox;
	private JComboBox<String> statusComboBox;
	private JTextField txtStudentId;
	
	/**
	 * Create the window.
	 */
	public adminStudents() {
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
		adminFrame.setSize(400, 450);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblStudentList = new JLabel("Edit Student");
		lblStudentList.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblStudentList.setForeground(Color.BLACK);
		panel.add(lblStudentList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminFrame.setVisible(false);
				adminFrame.dispose();
				MainWindow.emphasisComboBox.removeAllItems();
				MainWindow.fillEmphasisComboBox(MainWindow.emphasisComboBox);
			}
				
		});
		panel_1.add(btnOK);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(158, 313, 82, 29);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uni_id = txtSearchUniId.getText();
				String fname = txtFirst.getText();
				String mname = txtLast.getText();
				String lname = txtMiddle.getText();
				String sdate = txtStartDate.getText();
				String edate = txtEndDate.getText();
				String emphasis = (String)emphasisComboBox.getSelectedItem();
				String status = (String)statusComboBox.getSelectedItem();
				saveStudent(uni_id, fname, mname, lname, sdate, edate, emphasis, status);
			}
		});
		panel_2.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(278, 313, 82, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add a confirmation Dialog here
				String id = txtSearchUniId.getText();
				deleteStudent(id);
			}
		});
		panel_2.add(btnDelete);
		
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNew.setBounds(38, 313, 82, 29);
		panel_2.add(btnNew);
		
		JLabel lblEnterStudentId = new JLabel("Enter ID of Student to Edit");
		lblEnterStudentId.setBounds(22, 17, 174, 16);
		panel_2.add(lblEnterStudentId);
		
		txtSearchUniId = new JTextField();
		txtSearchUniId.setBounds(195, 11, 91, 28);
		panel_2.add(txtSearchUniId);
		txtSearchUniId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(172, 68, 82, 16);
		panel_2.add(lblFirstName);
		
		JLabel lblMiddle = new JLabel("Middle");
		lblMiddle.setBounds(41, 110, 49, 16);
		panel_2.add(lblMiddle);
		
		txtFirst = new JTextField();
		txtFirst.setBounds(243, 62, 117, 28);
		panel_2.add(txtFirst);
		txtFirst.setColumns(10);
		
		txtMiddle = new JTextField();
		txtMiddle.setBounds(87, 104, 42, 28);
		panel_2.add(txtMiddle);
		txtMiddle.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(145, 110, 82, 16);
		panel_2.add(lblLastName);
		
		txtLast = new JTextField();
		txtLast.setColumns(10);
		txtLast.setBounds(216, 104, 144, 28);
		panel_2.add(txtLast);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(35, 254, 72, 16);
		panel_2.add(lblStartDate);
		
		txtStartDate = new JTextField();
		txtStartDate.setBounds(105, 248, 91, 28);
		panel_2.add(txtStartDate);
		txtStartDate.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(207, 254, 60, 16);
		panel_2.add(lblEndDate);
		
		txtEndDate = new JTextField();
		txtEndDate.setColumns(10);
		txtEndDate.setBounds(269, 248, 91, 28);
		panel_2.add(txtEndDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 45, 388, 12);
		panel_2.add(separator);
		
		emphasisComboBox = new JComboBox();
		emphasisComboBox.setBounds(122, 148, 205, 27);
		panel_2.add(emphasisComboBox);
		fillEmphasisList(emphasisComboBox);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setBounds(122, 194, 205, 27);
		panel_2.add(statusComboBox);
		fillStatusList(statusComboBox);
		
		JLabel lblEmphasis = new JLabel("Emphasis");
		lblEmphasis.setBounds(55, 152, 61, 16);
		panel_2.add(lblEmphasis);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(55, 198, 61, 16);
		panel_2.add(lblStatus);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(288, 12, 82, 29);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtSearchUniId.getText();
				fetchStudent(id);
			}
		});
		panel_2.add(btnSearch);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(43, 67, 26, 16);
		panel_2.add(lblId);
		
		txtStudentId = new JTextField();
		txtStudentId.setEditable(false);
		txtStudentId.setBounds(67, 62, 93, 28);
		panel_2.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(5, 296, 388, 12);
		panel_2.add(separator_1);
		
	}
	
	private void saveStudent(String uni_id, String fname, String mname, String lname, String sdate, String edate, String emphasis, String status) {
		try {
			String startDate = "";
			String endDate = "";
			// Do work with sdate and edate to get into the correct format
			//String pattern = "dd/MM/yyyy";
			//SimpleDateFormat formatter = new SimpleDateFormat(pattern);

			if (sdate.equals("")) {
				startDate = "00/00/0000";
			}
			if (edate.equals("")) {
				endDate = "00/00/0000";
			}

			// For now, set them to NULL
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "UPDATE student SET `uni_id` = '" + uni_id + "', `fname` = '" + fname + "', mname = '" + mname + "', `lname` = '" + lname + "', sdate = '" + startDate + "', `edate` = '" + endDate + "', emphasis = '" + emphasis + "', status = '" + status + "' ";
			query += "WHERE `uni_id` = '" + uni_id + "';";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			conn.close();
			
		} catch ( Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}
	}
	
	private void fetchStudent(String uni_id) {
		try {
			String student_id = "";
			String first = "";
			String middle = "";
			String last = "";
			String sdate = "";
			String edate = "";
			String emphasis = "";
			String status = "";
			String emphasis_id = "";
			String status_id = "";
			
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT * FROM student WHERE `uni_id` = '" + uni_id + "'";
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				student_id = MySQLConnect.results.getString("uni_id");
				first = MySQLConnect.results.getString("fname");
				middle = MySQLConnect.results.getString("mname");
				last = MySQLConnect.results.getString("lname");
				emphasis = MySQLConnect.results.getString("emphasis");
				status = MySQLConnect.results.getString("status");
				edate = MySQLConnect.results.getString("edate");
				sdate = MySQLConnect.results.getString("sdate");
			}
			conn.close();
			
			//Set fields
			txtStudentId.setText(student_id);
			txtFirst.setText(first);
			txtMiddle.setText(middle);
			txtLast.setText(last);
			txtStartDate.setText(sdate);
			txtEndDate.setText(edate);
			emphasisComboBox.setSelectedItem(emphasis);
			statusComboBox.setSelectedItem(status);
			
		} catch ( Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}
	}
	
	private void deleteStudent(String id) {
		try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM student WHERE `uni_id` = '" + id + "';";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
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
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
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
}
