import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;

import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JComboBox;


public class MainWindow {

	private JFrame frame;
	private JTextField StudentName;
	private JTextField txtCriteria;
	private JTextField txtStudentFirst;
	private JTextField txtAssessment;
	private JTable ResultsTable;
	private JTextField txtStudentLast;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Start Frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 22, 1, 499);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("92px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("134px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("28px"),}));
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setBounds(6, 91, 238, 301);
		frame.getContentPane().add(LeftPanel);
		LeftPanel.setLayout(null);
		
		JLabel lblCriteria = new JLabel("Criteria ID");
		lblCriteria.setBounds(7, 247, 75, 16);
		LeftPanel.add(lblCriteria);
		
		txtCriteria = new JTextField();
		txtCriteria.setToolTipText("e.g. A1S12C1");
		txtCriteria.setBounds(4, 263, 155, 28);
		LeftPanel.add(txtCriteria);
		txtCriteria.setColumns(10);
		
		txtAssessment = new JTextField();
		txtAssessment.setToolTipText("e.g. A1 or A1F12");
		txtAssessment.setBounds(4, 124, 155, 28);
		LeftPanel.add(txtAssessment);
		txtAssessment.setColumns(10);
		
		JLabel lblAssessment = new JLabel("Search by Assessment");
		lblAssessment.setBounds(7, 108, 179, 16);
		LeftPanel.add(lblAssessment);
		
		JLabel lblStudentName = new JLabel("Search for a Student");
		lblStudentName.setBounds(7, 26, 155, 22);
		LeftPanel.add(lblStudentName);
		
		JLabel lblSearchIndex = new JLabel("Search Index");
		lblSearchIndex.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSearchIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchIndex.setBounds(6, 6, 226, 16);
		LeftPanel.add(lblSearchIndex);
		
		JLabel lblEmphasis = new JLabel("Emphasis");
		lblEmphasis.setBounds(7, 153, 75, 16);
		LeftPanel.add(lblEmphasis);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(7, 200, 80, 16);
		LeftPanel.add(lblStatus);
		
		JButton btnStudentSearch = new JButton("Search");
		btnStudentSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String firstName = txtStudentFirst.getText();
				String lastName = txtStudentLast.getText();
				// Search
				searchStudent(firstName, lastName);
			}
		});
		btnStudentSearch.setBounds(156, 60, 70, 29);
		LeftPanel.add(btnStudentSearch);
		
		JButton btnAssessSearch = new JButton("Search");
		btnAssessSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CDAI = txtAssessment.getText();
				searchCDAI(CDAI);
			}
		});
		btnAssessSearch.setBounds(156, 123, 70, 29);
		LeftPanel.add(btnAssessSearch);
		
		JButton btnEmphSearch = new JButton("Search");
		btnEmphSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEmphSearch.setBounds(156, 168, 70, 29);
		LeftPanel.add(btnEmphSearch);
		
		JButton btnStatusSearch = new JButton("Search");
		btnStatusSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnStatusSearch.setBounds(156, 216, 70, 29);
		LeftPanel.add(btnStatusSearch);
		
		JButton btnCriteriaSearch = new JButton("Search");
		btnCriteriaSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCriteriaSearch.setBounds(156, 262, 70, 29);
		LeftPanel.add(btnCriteriaSearch);
		
		
		// Combo Boxes for Emphasis and Status
		JComboBox<String> emphasisComboBox = new JComboBox<String>();
		emphasisComboBox.setBounds(5, 170, 154, 27);
		LeftPanel.add(emphasisComboBox);
		fillEmphasisComboBox(emphasisComboBox);
		
		JComboBox<String> StatusComboBox = new JComboBox<String>();
		StatusComboBox.setBounds(4, 218, 155, 27);
		LeftPanel.add(StatusComboBox);
		fillStatusComboBox(StatusComboBox);
		
		txtStudentFirst = new JTextField();
		txtStudentFirst.setToolTipText("First Name");
		txtStudentFirst.setBounds(41, 46, 118, 28);
		LeftPanel.add(txtStudentFirst);
		txtStudentFirst.setColumns(10);
		
		txtStudentLast = new JTextField();
		txtStudentLast.setToolTipText("Last Name");
		txtStudentLast.setColumns(10);
		txtStudentLast.setBounds(41, 73, 118, 28);
		LeftPanel.add(txtStudentLast);
		
		JLabel lblFirst = new JLabel("First");
		lblFirst.setBounds(6, 52, 37, 16);
		LeftPanel.add(lblFirst);
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setBounds(6, 80, 37, 16);
		LeftPanel.add(lblLast);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(6, 397, 238, 164);
		frame.getContentPane().add(panel_2);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAdministration.setBounds(6, 6, 226, 16);
		panel_2.add(lblAdministration);
		
		JButton btnAddAnAssessment = new JButton("Assessments");
		btnAddAnAssessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddAnAssessment.setBounds(6, 34, 117, 29);
		panel_2.add(btnAddAnAssessment);
		
		JButton btnStudents = new JButton("Students");
		btnStudents.setBounds(115, 34, 117, 29);
		panel_2.add(btnStudents);
		
		JButton btnRubricLevels = new JButton("Rubric Levels");
		btnRubricLevels.setBounds(6, 64, 117, 29);
		panel_2.add(btnRubricLevels);
		
		JButton btnEmphasis = new JButton("Emphasis");
		btnEmphasis.setBounds(115, 64, 117, 29);
		panel_2.add(btnEmphasis);
		
		JButton btnStudentStatus = new JButton("Student Status");
		btnStudentStatus.setBounds(115, 94, 117, 29);
		panel_2.add(btnStudentStatus);
		
		JButton btnFaculty_1 = new JButton("Faculty");
		btnFaculty_1.setBounds(6, 94, 117, 29);
		panel_2.add(btnFaculty_1);
		
		JButton btnNewButton = new JButton("Criteria");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton.setBounds(6, 124, 117, 29);
		panel_2.add(btnNewButton);
		
		JButton btnCustomSqlQuery = new JButton("Custom Query");
		btnCustomSqlQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCustomSqlQuery.setBounds(115, 124, 117, 29);
		panel_2.add(btnCustomSqlQuery);
		
		JPanel HeaderPanel = new JPanel();
		HeaderPanel.setBackground(Color.ORANGE);
		HeaderPanel.setBounds(6, 6, 988, 79);
		frame.getContentPane().add(HeaderPanel);
		HeaderPanel.setLayout(null);
		
		JLabel lblUwOshkoshComputer = new JLabel("UW Oshkosh Computer Science Department");
		lblUwOshkoshComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblUwOshkoshComputer.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblUwOshkoshComputer.setBounds(6, 6, 976, 25);
		HeaderPanel.add(lblUwOshkoshComputer);
		
		JLabel lblNewLabel = new JLabel("(AIPE) Assessment Item Performance Evaluation");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(252, 43, 488, 30);
		HeaderPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(249, 34, 491, 12);
		HeaderPanel.add(separator);
		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBounds(251, 91, 743, 470);
		frame.getContentPane().add(resultsPanel);
		
		ResultsTable = new JTable();
		resultsPanel.add(ResultsTable);
		
		
		// End Frame
		frame.setBounds(50, 50, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void fillEmphasisComboBox(JComboBox<String> emphasisComboBox) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT description from stu_emphasis";
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				String emphasisname = MySQLConnect.results.getString("description");
				emphasisComboBox.addItem(emphasisname);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
	}
	
	private void fillStatusComboBox(JComboBox<String> StatusComboBox) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "SELECT status_description from stu_status";
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				String emphasisname = MySQLConnect.results.getString("status_description");
				StatusComboBox.addItem(emphasisname);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	
	}
	
	private void searchStudent(String studentFirstName, String studentLastName) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query;
			if (studentFirstName.length() < 1 && studentLastName.length() < 1 ) {
				query = ""; // No text was entered in the name fields. Return No Results
			} else if (studentFirstName.length() < 1) {
				query = "SELECT * FROM student WHERE `lname` LIKE '" + studentLastName + "'";
			} else if (studentLastName.length() < 1) {
				query = "SELECT * FROM student WHERE `fname` LIKE '" + studentFirstName + "'";
			} else {
				query = "SELECT * FROM student WHERE `fname` LIKE '" + studentFirstName + "' OR `lname` LIKE '" + studentLastName + "'";
			}
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				String id = MySQLConnect.results.getString("uni_id");
				String fname = MySQLConnect.results.getString("fname");
				String mname = MySQLConnect.results.getString("mname");
				String lname = MySQLConnect.results.getString("lname");
				String sdate = MySQLConnect.results.getString("sdate");
				String edate = MySQLConnect.results.getString("edate");
				String emphasis = MySQLConnect.results.getString("emphasis");
				String status = MySQLConnect.results.getString("status");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	

	}
	
	private void searchCDAI(String CDAI) {
		try {
			MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query;
			if (studentFirstName.length() < 1 && studentLastName.length() < 1 ) {
				query = ""; // No text was entered in the name fields. Return No Results
			} else if (studentFirstName.length() < 1) {
				query = "SELECT * FROM student WHERE `lname` LIKE '" + studentLastName + "'";
			} else if (studentLastName.length() < 1) {
				query = "SELECT * FROM student WHERE `fname` LIKE '" + studentFirstName + "'";
			} else {
				query = "SELECT * FROM student WHERE `fname` LIKE '" + studentFirstName + "' OR `lname` LIKE '" + studentLastName + "'";
			}
			System.out.println(query);
			MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
			
			while(MySQLConnect.results.next()) {
				String id = MySQLConnect.results.getString("uni_id");
				String fname = MySQLConnect.results.getString("fname");
				String mname = MySQLConnect.results.getString("mname");
				String lname = MySQLConnect.results.getString("lname");
				String sdate = MySQLConnect.results.getString("sdate");
				String edate = MySQLConnect.results.getString("edate");
				String emphasis = MySQLConnect.results.getString("emphasis");
				String status = MySQLConnect.results.getString("status");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e);
		}	

	}
}
