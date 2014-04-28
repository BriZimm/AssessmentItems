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
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTable;
import javax.swing.JSeparator;


public class MainWindow {

	private JFrame frame;
	private JTextField StudentName;
	private JTextField txtCriteria;
	private JTextField txtStudent;
	private JTextField txtAssessment;
	private JTextField txtEmphasis;
	private JTextField textField;
	private JTable ResultsTable;

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
		
	
		
		// Bottom Buttons
		JPanel panel = new JPanel();
		panel.setBounds(0, 521, 1000, 57);
		panel.setBackground(new Color(30, 144, 255));
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		frame.getContentPane().add(panel);
		
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSearchBy.setForeground(new Color(255, 255, 255));
		panel.add(lblSearchBy);
		
		JButton btnSearchByStudent = new JButton("Student");
		btnSearchByStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnSearchByStudent);
		
		JButton btnAssessment = new JButton("Assessment");
		panel.add(btnAssessment);
		
		JButton btnFaculty = new JButton("Faculty");
		panel.add(btnFaculty);
		
		JButton btnSemesterYear = new JButton("Semester / Year");
		panel.add(btnSemesterYear);
		
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
		LeftPanel.setBounds(6, 91, 238, 253);
		frame.getContentPane().add(LeftPanel);
		LeftPanel.setLayout(null);
		
		JLabel lblCriteria = new JLabel("Criteria ID");
		lblCriteria.setBounds(5, 164, 75, 16);
		LeftPanel.add(lblCriteria);
		
		txtCriteria = new JTextField();
		txtCriteria.setBounds(107, 159, 124, 28);
		LeftPanel.add(txtCriteria);
		txtCriteria.setColumns(10);
		
		txtStudent = new JTextField();
		txtStudent.setBounds(108, 35, 124, 28);
		LeftPanel.add(txtStudent);
		txtStudent.setColumns(10);
		
		txtAssessment = new JTextField();
		txtAssessment.setBounds(108, 65, 124, 28);
		LeftPanel.add(txtAssessment);
		txtAssessment.setColumns(10);
		
		JLabel lblAssessment = new JLabel("Assessment ID");
		lblAssessment.setBounds(6, 71, 104, 16);
		LeftPanel.add(lblAssessment);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(6, 41, 90, 16);
		LeftPanel.add(lblStudentName);
		
		JLabel lblSearchIndex = new JLabel("Search Index");
		lblSearchIndex.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSearchIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchIndex.setBounds(6, 6, 226, 16);
		LeftPanel.add(lblSearchIndex);
		
		JLabel lblEmphasis = new JLabel("Emphasis");
		lblEmphasis.setBounds(6, 102, 90, 16);
		LeftPanel.add(lblEmphasis);
		
		txtEmphasis = new JTextField();
		txtEmphasis.setColumns(10);
		txtEmphasis.setBounds(108, 96, 124, 28);
		LeftPanel.add(txtEmphasis);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(107, 127, 124, 28);
		LeftPanel.add(textField);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(5, 131, 90, 16);
		LeftPanel.add(lblStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(6, 350, 238, 164);
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 91, 743, 423);
		frame.getContentPane().add(panel_3);
		
		ResultsTable = new JTable();
		panel_3.add(ResultsTable);
		
		
		// End Frame
		frame.setBounds(50, 50, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
