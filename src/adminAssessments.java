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
import javax.swing.JTable;



public class adminAssessments {

	JFrame adminFrame;
	private JTextField AddedCDAI;
	private JTextField RemovedCDAI;
	private JTextField txtSearchForAssessment;
	private JTextField CDAI_NAME;
	private JTextField CDAI_semester;
	private JTable AssessResultsTable;
	private JTextField CDAICourseNum;
	private JTextField CDAI_Date;
	private JComboBox<String> CDAIFacultycomboBox;
	
	/**
	 * Create the window.
	 */
	public adminAssessments() {
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
		adminFrame.setSize(500, 450);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		adminFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblEditAssessmentList = new JLabel("Edit Assessments");
		lblEditAssessmentList.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEditAssessmentList.setForeground(Color.BLACK);
		panel.add(lblEditAssessmentList);
		
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
		
		JLabel lblAdd = new JLabel("Add CDAI");
		lblAdd.setBounds(19, 328, 61, 16);
		panel_2.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(289, 328, 51, 16);
		panel_2.add(lblRemove);
		
		RemovedCDAI = new JTextField();
		RemovedCDAI.setToolTipText("e.g. A1S11");
		RemovedCDAI.setColumns(10);
		RemovedCDAI.setBounds(339, 322, 73, 28);
		panel_2.add(RemovedCDAI);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(409, 322, 82, 29);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String CDAI = RemovedCDAI.getText();
				// Search
				if (CDAI.length() != 0) {
					removeCDAI(CDAI);
					RemovedCDAI.setText("");
				}
			}
		});
		panel_2.add(btnRemove);
		
		AddedCDAI = new JTextField();
		AddedCDAI.setToolTipText("e.g. A1S11");
		AddedCDAI.setBounds(87, 322, 134, 28);
		panel_2.add(AddedCDAI);
		AddedCDAI.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(215, 323, 58, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String CDAI = AddedCDAI.getText();
				// Search
				if (CDAI.length() != 0) {
					if (CDAI.length() == 5) {
						addCDAI(CDAI);
						AddedCDAI.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "You must add a specific semester CDAI (e.g. A2S11).");
					}	
				} else {
					JOptionPane.showMessageDialog(null, "You cannot add a blank CDAI.");
				}
			}
		});
		panel_2.add(btnAdd);
		
		JLabel lblEnterAssessmentTo = new JLabel("Enter Assessment to Edit (e.g. A1F11)");
		lblEnterAssessmentTo.setBounds(22, 9, 246, 16);
		panel_2.add(lblEnterAssessmentTo);
		
		txtSearchForAssessment = new JTextField();
		txtSearchForAssessment.setBounds(265, 3, 97, 28);
		panel_2.add(txtSearchForAssessment);
		txtSearchForAssessment.setColumns(10);
		
		JButton btnSearchForCDAI = new JButton("Search");
		btnSearchForCDAI.setBounds(358, 4, 117, 29);
		btnSearchForCDAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CDAI = txtSearchForAssessment.getText();
				
			}
		});
		panel_2.add(btnSearchForCDAI);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(21, 48, 465, -15);
		panel_2.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(4, 29, 490, 12);
		panel_2.add(separator_1);
		
		JLabel lblCdai = new JLabel("CDAI:");
		lblCdai.setBounds(19, 52, 45, 16);
		panel_2.add(lblCdai);
		
		CDAI_NAME = new JTextField();
		CDAI_NAME.setBounds(60, 46, 58, 28);
		panel_2.add(CDAI_NAME);
		CDAI_NAME.setColumns(10);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(135, 52, 61, 16);
		panel_2.add(lblSemester);
		
		CDAI_semester = new JTextField();
		CDAI_semester.setBounds(199, 46, 97, 28);
		panel_2.add(CDAI_semester);
		CDAI_semester.setColumns(10);
		
		AssessResultsTable = new JTable();
		AssessResultsTable.setBounds(19, 123, 456, 173);
		panel_2.add(AssessResultsTable);
		
		JButton btnSaveAssessment = new JButton("Save");
		btnSaveAssessment.setBounds(369, 88, 117, 29);
		panel_2.add(btnSaveAssessment);
		
		JLabel lblCourseNumber = new JLabel("Course #");
		lblCourseNumber.setBounds(19, 94, 61, 16);
		panel_2.add(lblCourseNumber);
		
		CDAICourseNum = new JTextField();
		CDAICourseNum.setBounds(78, 88, 61, 28);
		panel_2.add(CDAICourseNum);
		CDAICourseNum.setColumns(10);
		
		CDAIFacultycomboBox = new JComboBox();
		CDAIFacultycomboBox.setBounds(193, 89, 165, 27);
		panel_2.add(CDAIFacultycomboBox);
		fillFacultyList(CDAIFacultycomboBox);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(146, 94, 51, 16);
		panel_2.add(lblFaculty);
		
		JLabel lblDate = new JLabel("Start Date");
		lblDate.setBounds(310, 52, 65, 16);
		panel_2.add(lblDate);
		
		CDAI_Date = new JTextField();
		CDAI_Date.setBounds(377, 46, 101, 28);
		panel_2.add(CDAI_Date);
		CDAI_Date.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(4, 308, 490, 12);
		panel_2.add(separator_2);
		
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
	
	private void fetchCDAI(String CDAI) {
		try {
			String cdai_id = "";
			String assess_id = "";
			String course_num = "";
			String faculty = "";
			String startDate = "";
			int faculty_id;
			
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT * FROM assessments JOIN faculty on faculty.name = assessments.faculty WHERE `assess_id` = '" + CDAI + "'";
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				cdai_id = MySQLConnect.results.getString("CDAI");
				assess_id = MySQLConnect.results.getString("assess_id");
				course_num = MySQLConnect.results.getString("course_num");
				faculty = MySQLConnect.results.getString("faculty");
				startDate = MySQLConnect.results.getString("sdate");
			}
			CDAI_NAME.setText(cdai_id);
			CDAI_semester.setText(assess_id);
			CDAI_Date.setText(startDate);
			CDAICourseNum.setText(course_num);
			//CDAIFacultycomboBox.setSelectedIndex(faculty_id)
			//CDAI_NAME.setText(faculty);
			
			
			conn.close();
		} catch ( Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}
	}
	
	
	// Grab Selected Items and Save 
	
	// combobox.getSelectedItem();
	
	private void addCDAI(String addedCDAI) {
    	try {
    		String CDAI = addedCDAI.substring(0,2);
    		String assess_id = addedCDAI;
    		String unique_id = assess_id;
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "INSERT INTO assessment (CDAI, assess_id) VALUE ('" + CDAI + "', '" + assess_id + "');";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			query = "INSERT INTO criteria (CDAI, unique_id) VALUE ('" + CDAI + "', '" + unique_id + "');";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
	 
	private void removeCDAI(String removedCDAI) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM assessment WHERE `assess_id` = '" + removedCDAI + "';";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			query = "DELETE FROM criteria WHERE `unique_id` = '" + removedCDAI + "'";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}