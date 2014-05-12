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
import java.sql.ResultSet;
 
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
 
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
     
     
public class MainWindow {
 
        public static JFrame frmUwOshkoshComputer;
        public static JComboBox<String> emphasisComboBox;
        public static JComboBox<String> StatusComboBox;
        public static JPanel resultsPanel = new JPanel();
        public ResultSetTableModelFactory rstmf;
        private JFrame adminFrame;
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
                                        window.frmUwOshkoshComputer.setVisible(true);
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
                frmUwOshkoshComputer = new JFrame();
                frmUwOshkoshComputer.setTitle("UW Oshkosh Computer Science");
                frmUwOshkoshComputer.getContentPane().setBackground(Color.ORANGE);
                frmUwOshkoshComputer.getContentPane().setLayout(null);
               
                JPanel LeftPanel = new JPanel();
                LeftPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
                LeftPanel.setBounds(6, 70, 238, 301);
                frmUwOshkoshComputer.getContentPane().add(LeftPanel);
                LeftPanel.setLayout(null);
               
                JLabel lblSearchIndex = new JLabel("Search Index");
                lblSearchIndex.setFont(new Font("Lucida Grande", Font.BOLD, 13));
                lblSearchIndex.setHorizontalAlignment(SwingConstants.CENTER);
                lblSearchIndex.setBounds(6, 6, 226, 16);
                LeftPanel.add(lblSearchIndex);
               
                JLabel lblStudentName = new JLabel("Search for a Student");
                lblStudentName.setBounds(7, 26, 155, 22);
                LeftPanel.add(lblStudentName);
               
                JLabel lblFirst = new JLabel("First");
                lblFirst.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
                lblFirst.setBounds(6, 52, 37, 16);
                LeftPanel.add(lblFirst);
               
                txtStudentFirst = new JTextField();
                txtStudentFirst.setToolTipText("First Name");
                txtStudentFirst.setBounds(41, 46, 118, 28);
                LeftPanel.add(txtStudentFirst);
                txtStudentFirst.setColumns(10);
               
                JLabel lblLast = new JLabel("Last");
                lblLast.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
                lblLast.setBounds(6, 80, 37, 16);
                LeftPanel.add(lblLast);
               
                txtStudentLast = new JTextField();
                txtStudentLast.setToolTipText("Last Name");
                txtStudentLast.setColumns(10);
                txtStudentLast.setBounds(41, 73, 118, 28);
                LeftPanel.add(txtStudentLast);
               
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
               
                JLabel lblAssessment = new JLabel("Search by Assessment");
                lblAssessment.setBounds(7, 108, 179, 16);
                LeftPanel.add(lblAssessment);
               
                txtAssessment = new JTextField();
                txtAssessment.setToolTipText("e.g. A1 or A1F12");
                txtAssessment.setBounds(4, 124, 155, 28);
                LeftPanel.add(txtAssessment);
                txtAssessment.setColumns(10);
               
                JButton btnAssessSearch = new JButton("Search");
                btnAssessSearch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String CDAI = txtAssessment.getText();
                                searchCDAI(CDAI);
                        }
                });
                btnAssessSearch.setBounds(156, 123, 70, 29);
                LeftPanel.add(btnAssessSearch);
               
                JLabel lblEmphasis = new JLabel("Emphasis");
                lblEmphasis.setBounds(7, 153, 75, 16);
                LeftPanel.add(lblEmphasis);
               
               
                // Combo Boxes for Emphasis and Status
                emphasisComboBox = new JComboBox<String>();
                emphasisComboBox.setBounds(5, 170, 154, 27);
                LeftPanel.add(emphasisComboBox);
                fillEmphasisComboBox(emphasisComboBox);
               
                JButton btnEmphSearch = new JButton("Search");
                btnEmphSearch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                               
                        }
                });
                btnEmphSearch.setBounds(156, 168, 70, 29);
                LeftPanel.add(btnEmphSearch);
               
                JLabel lblStatus = new JLabel("Status");
                lblStatus.setBounds(7, 200, 80, 16);
                LeftPanel.add(lblStatus);
               
                StatusComboBox = new JComboBox<String>();
                StatusComboBox.setBounds(4, 218, 155, 27);
                LeftPanel.add(StatusComboBox);
                fillStatusComboBox(StatusComboBox);
               
                JButton btnStatusSearch = new JButton("Search");
                btnStatusSearch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                               
                        }
                });
                btnStatusSearch.setBounds(156, 216, 70, 29);
                LeftPanel.add(btnStatusSearch);
               
                JLabel lblCriteria = new JLabel("Criteria ID");
                lblCriteria.setBounds(7, 247, 75, 16);
                LeftPanel.add(lblCriteria);
               
                txtCriteria = new JTextField();
                txtCriteria.setToolTipText("e.g. A1S12C1");
                txtCriteria.setBounds(4, 263, 155, 28);
                LeftPanel.add(txtCriteria);
                txtCriteria.setColumns(10);
               
                JButton btnCriteriaSearch = new JButton("Search");
                btnCriteriaSearch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String criteria_id = txtCriteria.getText();
                                searchCriteria(criteria_id);
                        }
                });
                btnCriteriaSearch.setBounds(156, 262, 70, 29);
                LeftPanel.add(btnCriteriaSearch);
               
                JPanel panel_2 = new JPanel();
                panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
                panel_2.setBounds(6, 374, 238, 134);
                panel_2.setLayout(null);
                frmUwOshkoshComputer.getContentPane().add(panel_2);
               
                JLabel lblAdministration = new JLabel("Administration");
                lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
                lblAdministration.setFont(new Font("Lucida Grande", Font.BOLD, 13));
                lblAdministration.setBounds(6, 6, 226, 16);
                panel_2.add(lblAdministration);
               
                JButton btnAdminAssessment = new JButton("Assessments");
                btnAdminAssessment.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminAssessments window = new adminAssessments();
                                window.adminFrame.setVisible(true);
                        }
                });
                btnAdminAssessment.setBounds(6, 31, 117, 29);
                panel_2.add(btnAdminAssessment);
               
                JButton btnAdminStudents = new JButton("Students");
                btnAdminStudents.setBounds(115, 31, 117, 29);
                btnAdminStudents.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminStudents window = new adminStudents();
                                window.adminFrame.setVisible(true);
                        }
                });
                panel_2.add(btnAdminStudents);
               
                JButton btnAdminEmphasis = new JButton("Emphasis");
                btnAdminEmphasis.setBounds(115, 61, 117, 29);
                btnAdminEmphasis.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminEmphasis window = new adminEmphasis();
                                window.adminFrame.setVisible(true);
                        }
                });
                panel_2.add(btnAdminEmphasis);
               
                JButton btnAdminStudentStatus = new JButton("Student Status");
                btnAdminStudentStatus.setBounds(6, 61, 117, 29);
                btnAdminStudentStatus.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminStatus window = new adminStatus();
                                window.adminFrame.setVisible(true);
                        }
                });
                panel_2.add(btnAdminStudentStatus);
               
                JButton btnAdminFaculty = new JButton("Faculty");
                btnAdminFaculty.setBounds(6, 91, 117, 29);
                btnAdminFaculty.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminFaculty window = new adminFaculty();
                                window.adminFrame.setVisible(true);
                        }
                });
                panel_2.add(btnAdminFaculty);
               
                JButton btnAdminCriteria = new JButton("Criteria");
                btnAdminCriteria.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                adminFrame = new JFrame();
                                adminCriteria window = new adminCriteria();
                                window.adminFrame.setVisible(true);
                        }
                });
                btnAdminCriteria.setBounds(115, 91, 117, 29);
                panel_2.add(btnAdminCriteria);
               
                JPanel HeaderPanel = new JPanel();
                HeaderPanel.setBounds(6, 6, 988, 60);
                HeaderPanel.setBackground(Color.ORANGE);
                frmUwOshkoshComputer.getContentPane().add(HeaderPanel);
                HeaderPanel.setLayout(null);
               
                JLabel lblUwOshkoshComputer = new JLabel("UW Oshkosh Computer Science Department");
                lblUwOshkoshComputer.setHorizontalAlignment(SwingConstants.CENTER);
                lblUwOshkoshComputer.setFont(new Font("Lucida Grande", Font.BOLD, 20));
                lblUwOshkoshComputer.setBounds(6, 6, 976, 25);
                HeaderPanel.add(lblUwOshkoshComputer);
               
                JLabel lblNewLabel = new JLabel("(AIPE) Assessment Item Performance Evaluation");
                lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
                lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel.setBounds(252, 32, 488, 30);
                HeaderPanel.add(lblNewLabel);
               
                JSeparator separator = new JSeparator();
                separator.setForeground(Color.BLACK);
                separator.setBounds(252, 26, 491, 12);
                HeaderPanel.add(separator);
               
                resultsPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
                resultsPanel.setBounds(247, 70, 747, 438);
                frmUwOshkoshComputer.getContentPane().add(resultsPanel);
               
                try{
                        rstmf = new ResultSetTableModelFactory(Login.DRIVER_CLASS, Login.DRIVER, Login.DB, Login.USER, Login.PWD);
                }catch(Exception e){
                        System.out.println("Error on creating results table!");
                }
               
                ResultsTable = new JTable();
                resultsPanel.add(ResultsTable);
               
                JPanel panel = new JPanel();
                panel.setBackground(new Color(0, 191, 255));
                panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                panel.setBounds(5, 512, 988, 60);
                frmUwOshkoshComputer.getContentPane().add(panel);
                panel.setLayout(null);
               
               
               
               
               
               
               
                //
                // Ready Made Query Buttons from Phase 3 of the Project
                //
               
               
                //Query 1
                JButton btnQuery1 = new JButton("Query 1");
                btnQuery1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter Criterion (e.g. A1F12C1)?",
                                "Criterion Name",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            getAvgCriteriaScore(txtEntered);
                        }
                });
                btnQuery1.setToolTipText("Display the average score for a specific criterion (identified like A1F12C1)");
                btnQuery1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery1.setBounds(268, 4, 150, 29);
                panel.add(btnQuery1);
               
                //Query 2
                JButton btnQuery2 = new JButton("Query 2");
                btnQuery2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter specific semester CDAI (e.g. A1F12)",
                                "Semester CDAI Name",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                                getAvgCDAIScore(txtEntered);
                        }
                });
                btnQuery2.setToolTipText("Displays the average scores for all criteria in a specific CDAI in a specific semester (CDAI identified like A1F12)");
                btnQuery2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery2.setBounds(410, 4, 150, 29);
                panel.add(btnQuery2);
               
                //Query 3
                JButton btnQuery3 = new JButton("Query 3");
                btnQuery3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter specific CDAI (e.g. A1)",
                                "CDAI Name",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                                getAvgEntireCDAI(txtEntered);
                        }
                });
                btnQuery3.setToolTipText("Displays the average scores for all criteria in a specific CDAI (identified like A1)");
                btnQuery3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery3.setBounds(553, 4, 150, 29);
                panel.add(btnQuery3);
               
                //Query 4
                JButton btnQuery4 = new JButton("Query 4");
                btnQuery4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter specific semester CDAI (e.g. A1F12)",
                                "Semester CDAI Name",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                                getAvgOfAllCDAI(txtEntered);
                        }
                });
                btnQuery4.setToolTipText("Displays the average score over all criteria in a specific CDAI in a specific semester (CDAI identified like A1F12)");
                btnQuery4.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery4.setBounds(695, 4, 150, 29);
                panel.add(btnQuery4);
               
                //Query 5
                JButton btnQuery5 = new JButton("Query 5");
                btnQuery5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter specific CDAI (e.g. A1)",
                                "CDAI Name",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                                getAvgOfAllCDAICriteria(txtEntered);
                        }
                });
                btnQuery5.setToolTipText("Displays the average score over all criteria for a specific CDAI (identified like A1)");
                btnQuery5.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery5.setBounds(837, 4, 150, 29);
                panel.add(btnQuery5);
               
                //Query 6
                JButton btnQuery6 = new JButton("Query 6");
                btnQuery6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                getHighestAvgOfAllCDAI();
                        }
                });
                btnQuery6.setToolTipText("Identifies the CDAI with the highest average score across all CDAIs.");
                btnQuery6.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery6.setBounds(268, 28, 150, 29);
                panel.add(btnQuery6);
               
                //Query 7
                JButton btnQuery7 = new JButton("Query 7");
                btnQuery7.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                getLowestAvgOfAllCDAI();
                        }
                });
                btnQuery7.setToolTipText("Identifies the CDAI with the lowest average score across all CDAIs.");
                btnQuery7.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery7.setBounds(410, 28, 150, 29);
                panel.add(btnQuery7);
               
                //Query 8
                JButton btnQuery8 = new JButton("Query 8");
                btnQuery8.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                getAllAvgsOfAllCDAI();
                        }
                });
                btnQuery8.setToolTipText("Provides the average, lowest and highest CDAI scores, over all CDAI scores in each emphasis, grouped by emphasis.");
                btnQuery8.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery8.setBounds(553, 28, 150, 29);
                panel.add(btnQuery8);
               
                //Query 9
                JButton btnQuery9 = new JButton("Query 9");
                btnQuery9.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter First and Last Name of Student",
                                "Student Grades",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                                String[] name = txtEntered.split("/s");
                                getCDAIforstudent(name[0], name[1]);
                        }
                });
                btnQuery9.setToolTipText("Displays all the CDAI data (every criteria score earned by that student) for a student, identified by first name and last name.");
                btnQuery9.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery9.setBounds(695, 28, 150, 29);
                panel.add(btnQuery9);
               
                //Query 10
                JButton btnQuery10 = new JButton("Query10");
                btnQuery10.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                getInfoForstudents();
                        }
                });
                btnQuery10.setToolTipText("Returns Min, Max and Average Grade for all Students.");
                btnQuery10.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnQuery10.setBounds(837, 28, 150, 29);
                panel.add(btnQuery10);
               
                // Custom Query
                JButton btnCustomSqlQuery = new JButton("Custom Query");
                btnCustomSqlQuery.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String txtEntered = JOptionPane.showInputDialog(
                                frmUwOshkoshComputer,
                                "Enter Custom Query (Be Careful)",
                                "Customize Query",
                                JOptionPane.WARNING_MESSAGE
                            );
                            customQuery(txtEntered);
                        }
                });
                btnCustomSqlQuery.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                btnCustomSqlQuery.setBounds(65, 30, 117, 30);
                panel.add(btnCustomSqlQuery);
               
                JLabel lblHoldMouseOver = new JLabel("Hold mouse over each Query to learn what each does,");
                lblHoldMouseOver.setHorizontalAlignment(SwingConstants.CENTER);
                lblHoldMouseOver.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
                lblHoldMouseOver.setBounds(6, 3, 264, 17);
                panel.add(lblHoldMouseOver);
               
                JLabel lblNewLabel_1 = new JLabel("or try a custom query below.");
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
                lblNewLabel_1.setBounds(7, 16, 228, 16);
                panel.add(lblNewLabel_1);
                btnCustomSqlQuery.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        }
                });
               
               
                // End Frame
                frmUwOshkoshComputer.setBounds(50, 50, 1000, 600);
                frmUwOshkoshComputer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
       
        public static void fillEmphasisComboBox(JComboBox<String> emphasisComboBox) {
                try {
                        MySQLConnect conn = new MySQLConnect();
                        conn.connect();
                        String query = "SELECT description from stu_emphasis";
                        MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
                       
                        while(MySQLConnect.results.next()) {
                                String emphasisname = MySQLConnect.results.getString("description");
                                emphasisComboBox.addItem(emphasisname);
                        }
                        conn.close();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,  e);
                }      
        }
       
        public static void fillStatusComboBox(JComboBox<String> StatusComboBox) {
                try {
                        MySQLConnect conn = new MySQLConnect();
                        conn.connect();
                        String query = "SELECT status_description from stu_status";
                        MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
                       
                        while(MySQLConnect.results.next()) {
                                String emphasisname = MySQLConnect.results.getString("status_description");
                                StatusComboBox.addItem(emphasisname);
                        }
                        conn.close();
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
                        conn.close();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,  e);
                }      
 
        }
       
        private void searchCDAI(String CDAI) {
                try {
                        MySQLConnect conn = new MySQLConnect();
                        conn.connect();
                        String query = "";
                        if (CDAI.length() == 2) { // User is searching for a two char CDAI string e.g. A1
                                query = "SELECT * FROM assessment WHERE `CDAI` = '" + CDAI + "'";
                        }
                        if (CDAI.length() == 5) { // User is searching for a 5 char CDAI string e.g. A1F11
                                query = "SELECT * FROM assessment WHERE `assess_id` = '" + CDAI + "'";
                        }
                        System.out.println(query);
                        MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
                       
                        while(MySQLConnect.results.next()) {
                                String cdai_id = MySQLConnect.results.getString("CDAI");
                                String assess_id = MySQLConnect.results.getString("assess_id");
                                String courseNum = MySQLConnect.results.getString("course_num");
                                String faculty = MySQLConnect.results.getString("faculty");
                                String startDate = MySQLConnect.results.getString("startdate");
                        }
                        conn.close();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,  e);
                }      
 
        }
       
        private void searchCriteria(String criteria_id) {
                try {
                        MySQLConnect conn = new MySQLConnect();
                        conn.connect();
                        String query = "SELECT * FROM criteria WHERE `name` = '" + criteria_id + "'";
                        System.out.println(query);
                        MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
                       
                        while(MySQLConnect.results.next()) {
                                String cdai_id = MySQLConnect.results.getString("CDAI");
                                String unique_id = MySQLConnect.results.getString("unique_id");
                                String name = MySQLConnect.results.getString("name");
                                String description = MySQLConnect.results.getString("description");
                        }
                        conn.close();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,  e);
                }      
        }
       
       
        private void customQuery(String query) {
                try {
                        if (query.length() != 0) {
                                MySQLConnect conn = new MySQLConnect();
                                conn.connect();
                                System.out.println(query);
                                MySQLConnect.results=MySQLConnect.stmt.executeQuery(query);
                               
                                while(MySQLConnect.results.next()) {
                                        String cdai_id = MySQLConnect.results.getString("CDAI");
                                        String unique_id = MySQLConnect.results.getString("unique_id");
                                        String name = MySQLConnect.results.getString("name");
                                        String description = MySQLConnect.results.getString("description");
                                }
                                conn.close();
                        } else {
                                JOptionPane.showMessageDialog(null, "Your Query must not be empty.");
                        }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,  e);
                }      
        }
       
        //**************************************************************//
        //******************** Phase 2 Queries *************************//
        //**************************************************************//
       
        // Phase 2 Query 1
    // A query to display the average score for a specific criterion (identified by id such as
    // A1F12C1) in a specific CDAI in a specific semester.
    private void getAvgCriteriaScore(String AssessCriteriaID) {
        try {
                        String query = "SELECT avg(score) AS 'Avg' FROM grades WHERE criteria = '" + AssessCriteriaID + "'";
                        System.out.println(query);     
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 2
    // A query to display the average scores for all criteria in a specific CDAI in a specific
    // semester (CDAI identified by id such as A1F12)
    private void getAvgCDAIScore(String AssessID) {
        try {
                        String query = "SELECT avg(score) AS 'Avg for specific CDAI', criteria AS 'Criteria Name' FROM grades WHERE criteria IN (SELECT name FROM `criteria` WHERE unique_id = '" + AssessID + "')";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
            System .out.println (" Error " + e );
        }
    }
   
    // Phase 2 Query 3
    // A query to display the average scores for all criteria in a specific CDAI (identified by id such as A1)
    private void getAvgEntireCDAI(String AssessID) {
        try {
                        String query = "SELECT avg(score) AS 'average', criteria AS 'Criteria Name' FROM grades WHERE criteria IN (SELECT name FROM criteria WHERE CDAI = '" + AssessID + "') GROUP BY criteria ORDER BY criteria";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 4
    // A query to display the average score over all criteria in a specific CDAI in a specific semester (CDAI identified by id such as A1F12)
    private void getAvgOfAllCDAI(String AssessID) {
        try {
                        String query = "SELECT unique_id, AVG(score) AS 'average' FROM(SELECT uni_id, unique_id, score FROM grades g JOIN criteria c WHERE g.criteria = c.name) scores WHERE unique_id = '" + AssessID + "' GROUP BY unique_id ORDER BY uni_id";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 5
    // A query to display the average score over all criteria for a specific CDAI (identified by id such as A1)
    private void getAvgOfAllCDAICriteria(String AssessID) {
        try {
                        String query = "SELECT avg(score) AS 'average' FROM grades WHERE criteria IN (SELECT name AS 'criteria' FROM criteria WHERE CDAI = '" + AssessID + "')";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 6
    // A query to identify the CDAI with the highest average score across all CDAIs.
    private void getHighestAvgOfAllCDAI() {
        try {
                        String query = "SELECT avg( score ) AS 'average', grades.CDAI AS 'CDAI' FROM grades JOIN criteria ON criteria.name = grades.criteria GROUP BY grades.CDAI ORDER BY avg( score ) DESC LIMIT 1";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 7
    // A query to identify the CDAI with the lowest average score across all CDAIs.
    private void getLowestAvgOfAllCDAI() {
        try {
                        String query = "SELECT avg( score ) AS 'average', grades.CDAI AS 'CDAI' FROM grades JOIN criteria ON criteria.name = grades.criteria GROUP BY grades.CDAI ORDER BY avg( score ) ASC LIMIT 1;";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
    // Phase 2 Query 8
    // A query to provide the average, lowest and highest CDAI scores, over all CDAI scores in
    // each emphasis, grouped by emphasis. Include ���undeclared��� as its own category.
    private void getAllAvgsOfAllCDAI() {
        try {
                        String query = "SELECT emphasis, avg( score ) AS 'average', max( score ) AS 'maxscore', min( score ) AS 'minscore' FROM grades JOIN student ON student.uni_id = grades.uni_id GROUP BY emphasis ORDER BY emphasis";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
 
    // Phase 2 Query 9
    // A query to display all the CDAI data (every criteria score earned by that student) for
    // a student, identified by first name and last name.
    private void getCDAIforstudent(String fname, String lname) {
        try {
                        String query = "SELECT fname, lname, g.CDAI AS 'CDAI', criteria, score FROM grades g JOIN criteria c JOIN student s WHERE g.criteria = c.name AND s.uni_id = g.uni_id AND g.uni_id = (SELECT uni_id FROM student WHERE `fname` LIKE '" + fname + "' AND `lname` LIKE '%" + lname + "') ORDER BY g.uni_id";
                        System.out.println(query);
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
   
 // Phase 2 Query 10
    // A query to display all min, max and avg scores for all students..
    private void getInfoForstudents() {
        try {
                MySQLConnect conn = new MySQLConnect();
                        conn.connect();
                        String query = "SELECT uni_id, fname, lname, max(scores.scored) as maxScore, min(scores.scored) as minScore, avg(scores.scored) as avgScore FROM (SELECT uni_id, cdai, avg(score) as scored FROM grades GROUP BY cdai, uni_id) scores NATURAL JOIN student GROUP BY uni_id";
                        System.out.println(query);                             
                        ResultsTable.setModel(rstmf.getResultSetTableModel(query));
                        conn.close();
        } catch ( Exception e) {
                JOptionPane.showMessageDialog(null,  e);
        }
    }
}

