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



public class adminEmphasis {

	JFrame adminFrame;
	private JTextField AddedEmphasis;
	private JTextField RemovedEmphasis;
	private JComboBox<String> emphasisBox;
	
	/**
	 * Create the window.
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
		//adminFrame.pack();
		adminFrame.setSize(300, 250);
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
		
		emphasisBox = new JComboBox<String>();
		emphasisBox.setBounds(78, 6, 134, 27);
		emphasisBox.setEditable(true);
		fillEmphasisList(emphasisBox);
		panel_2.add(emphasisBox);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setBounds(19, 12, 58, 16);
		panel_2.add(lblCurrent);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setBounds(19, 64, 58, 16);
		panel_2.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(19, 112, 58, 16);
		panel_2.add(lblRemove);
		
		RemovedEmphasis = new JTextField();
		RemovedEmphasis.setColumns(10);
		RemovedEmphasis.setBounds(78, 106, 134, 28);
		panel_2.add(RemovedEmphasis);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(212, 107, 82, 29);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String emphasis = RemovedEmphasis.getText();
				// Search
				if (emphasis.length() != 0) {
					removeEmphasis(emphasis);
					RemovedEmphasis.setText("");
					emphasisBox.removeAllItems();
					fillEmphasisList(emphasisBox);
				}
			}
		});
		panel_2.add(btnRemove);
		
		AddedEmphasis = new JTextField();
		AddedEmphasis.setBounds(78, 58, 134, 28);
		panel_2.add(AddedEmphasis);
		AddedEmphasis.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(212, 59, 82, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Grab the values from the fields
				String emphasis = AddedEmphasis.getText();
				// Search
				if (emphasis.length() != 0) {
					addEmphasis(emphasis);
					AddedEmphasis.setText("");
					emphasisBox.removeAllItems();
					fillEmphasisList(emphasisBox);
				}
			}
		});
		panel_2.add(btnAdd);
		
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
	
	 private void addEmphasis(String addedEmphasis) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "INSERT INTO stu_emphasis (description) VALUE ('" + addedEmphasis + "')";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
	 
	 private void removeEmphasis(String removedEmphasis) {
    	try {
    		MySQLConnect conn = new MySQLConnect();
			conn.connect();
			String query = "DELETE FROM stu_emphasis WHERE `description` = '" + removedEmphasis + "'";
			System.out.println(query);
			MySQLConnect.stmt.executeUpdate(query);
			adminFrame.repaint();
			
			conn.close();
    	} catch ( Exception e) { 
    		JOptionPane.showMessageDialog(null,  e);
        }
    }
}
