package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.RoundedPanel;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;
import modals.AddLogModal;
import modals.EditLogModal;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentsLogPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_medium = new FontLoader("/fonts/Inter-Medium.ttf");
	private RoundedPanel studentDetailsPanel; 
	private RoundedTextField searchTxt;
	private RoundedButton AddLogButton, EditLogButton, DeleteLogButton;
	private JTable studentsTable;
	private RoundedButton SearchButton;
	private JLabel studentNumberValue, genderValue, departmentValue, timeInValue, studentIDValue;
	private JTextArea studentNameValue;
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;
	DefaultTableModel model = new DefaultTableModel();


	/**
	 * Create the panel.
	 */
	public StudentsLogPanel() {
		connect.Connect();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 676)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel studentsLog = new JLabel("Student's Log");
		studentsLog.setBounds(34, 32, 341, 50);
		inter_black.applyFont(studentsLog, 36f, CommonConstants.HEADER_COLOR);
		add(studentsLog);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);
		
		AddLogButton = new RoundedButton ("Add Log",13, Color.decode("#429737"));
		AddLogButton.addActionListener(this);
		AddLogButton.setBounds(824, 117, 97, 33);
		inter_bold.applyFont(AddLogButton, 14F, Color.white);
		add(AddLogButton);
		
		searchTxt = new RoundedTextField(15);
		searchTxt.setPlaceholder("Search...");
		searchTxt.setBounds(40, 117, 159, 32);
		inter_regular.applyFont(searchTxt, 15F, Color.black);
		searchTxt.setBackground(Color.decode("#D9D9D9"));
		searchTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(searchTxt);
		searchTxt.setColumns(10);
		
		SearchButton = new RoundedButton("", 10, Color.decode("#569FF0"));
		SearchButton.addActionListener(this);
		SearchButton.setIcon(new ImageIcon(StudentsLogPanel.class.getResource("/icons/search-iconpng.png")));
		SearchButton.setBounds(200, 117, 38, 32);
		add(SearchButton);
		
		
		//Student's Details Panel
		
		studentDetailsPanel = new RoundedPanel(44);
		studentDetailsPanel.setBounds(34, 418, 887, 232);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel StudentDetails = new JLabel("Student Details: ");
		StudentDetails.setBounds(24, 17, 186, 27);
		inter_bold.applyFont(StudentDetails, 20F, Color.black);
		studentDetailsPanel.add(StudentDetails);
		
		JLabel StudentNumber = new JLabel("Student Number: ");
		StudentNumber.setBounds(62, 55, 183, 38);
		inter_bold.applyFont(StudentNumber, 15F, Color.black);
		studentDetailsPanel.add(StudentNumber);
		
		JLabel StudentName = new JLabel("Student Name: ");
		StudentName.setBounds(62, 107, 183, 38);
		inter_bold.applyFont(StudentName, 15F, Color.black);
		studentDetailsPanel.add(StudentName);
		
		JLabel Gender = new JLabel("Gender: ");
		Gender.setBounds(331, 61, 186, 27);
		inter_bold.applyFont(Gender, 15F, Color.black);
		studentDetailsPanel.add(Gender);
		
		JLabel Department = new JLabel("Department: ");
		Department.setBounds(331, 102, 183, 38);
		inter_bold.applyFont(Department, 15F, Color.black);
		studentDetailsPanel.add(Department);
		
		JLabel TimeIn = new JLabel("Time In: ");
		TimeIn.setBounds(331, 160, 64, 27);
		inter_bold.applyFont(TimeIn, 15F, Color.black);
		studentDetailsPanel.add(TimeIn);
		
		// Values 
		studentIDValue = new JLabel("");
		studentIDValue.setVisible(false);
		studentIDValue.setBounds(114, 17, 148, 27);
		inter_regular.applyFont(studentIDValue, 15F, Color.black);
		studentDetailsPanel.add(studentIDValue);
		
		studentNumberValue = new JLabel("");
		studentNumberValue.setBounds(62, 85, 148, 27);
		inter_regular.applyFont(studentNumberValue, 15F, Color.black);
		studentDetailsPanel.add(studentNumberValue);
		
		studentNameValue = new JTextArea("");
		studentNameValue.setWrapStyleWord(true);
		studentNameValue.setLineWrap(true);
		studentNameValue.setFocusable(false);
		studentNameValue.setEditable(false);
		studentNameValue.setBounds(62, 136, 200, 80);
		studentNameValue.setBackground(new Color(216, 216, 216));
		inter_regular.applyFont(studentNameValue, 15F, Color.black);
		studentDetailsPanel.add(studentNameValue);
		
		genderValue = new JLabel("");
		genderValue.setBounds(331, 84, 160, 27);
		inter_regular.applyFont(genderValue, 15F, Color.black);
		studentDetailsPanel.add(genderValue);
		
		departmentValue = new JLabel("");
		departmentValue.setBounds(331, 133, 186, 29);
		inter_regular.applyFont(departmentValue, 15F, Color.black);
		studentDetailsPanel.add(departmentValue);
		
		timeInValue = new JLabel("");
		timeInValue.setBounds(331, 182, 160, 29);
		inter_regular.applyFont(timeInValue, 15F, Color.black);
		studentDetailsPanel.add(timeInValue);
		
		//Action Label and Buttons
		
		JLabel Actions = new JLabel("Actions:");
		Actions.setBounds(648, 21, 186, 27);
		inter_bold.applyFont(Actions, 20F, Color.black);
		studentDetailsPanel.add(Actions);
	
		EditLogButton = new RoundedButton("Edit Log", 13, Color.decode("#0060CC"));
		EditLogButton.addActionListener(this);
		EditLogButton.setBounds(730, 70, 110, 35);
		inter_bold.applyFont(EditLogButton, 14F, Color.white);
		studentDetailsPanel.add(EditLogButton);
		
		DeleteLogButton = new RoundedButton("Delete Log", 13, Color.decode("#E74343"));
		DeleteLogButton.addActionListener(this);
		DeleteLogButton.setBounds(730, 134, 110, 35);
		inter_bold.applyFont(DeleteLogButton, 14F, Color.white);
		studentDetailsPanel.add(DeleteLogButton);
		
		// Student Log Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 154, 887, 253);
		add(scrollPane);
		
		Object[] column = { "ID", "Student Number", "Students Name", "Gender", "Department", "Time in"};
		model.setColumnIdentifiers(column);
		
		studentsTable = new JTable();
		studentsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = studentsTable.getSelectedRow();
				studentIDValue.setText(model.getValueAt(row, 0).toString());
				studentNumberValue.setText(model.getValueAt(row, 1).toString());
				studentNameValue.setText(model.getValueAt(row, 2).toString());
				genderValue.setText(model.getValueAt(row, 3).toString());
				departmentValue.setText(model.getValueAt(row, 4).toString());
				timeInValue.setText(model.getValueAt(row, 5).toString());
				
			}
		});
		studentsTable.setSelectionBackground(Color.LIGHT_GRAY);
		inter_regular.applyFont(studentsTable, 13f, Color.BLACK);
		scrollPane.setViewportView(studentsTable);
		studentsTable.setModel(model);
		studentsTable.getTableHeader().setReorderingAllowed(false);
		studentsTable.getTableHeader().setResizingAllowed(false);
		studentsTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			studentsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = studentsTable.getTableHeader();
		inter_bold.applyFont(header, 14f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		studentsTable.setRowHeight(32);
		studentsTable.setFocusable(true);
		studentsTable.setTableHeader(header);
		
		fetchStudentData();
	}
	
	// Fetch All Students in Database
	public void fetchStudentData() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM students");
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				int student_id = rs.getInt("student_id");
				int student_number = rs.getInt("student_number");
				String student_name = rs.getString("student_name");
				String student_gender = rs.getString("student_gender");
				String student_department = rs.getString("student_department");
				String student_timeIn = rs.getString("student_timeIn");
				
				model.addRow(new Object[] {student_id, student_number, student_name, student_gender, student_department, student_timeIn});
			}
			
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchStudent(String searchTxt) {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM students WHERE "
					+ "student_id LIKE ? OR student_name LIKE ? OR student_gender LIKE ? OR student_department LIKE ? "
					+ "OR student_timeIn LIKE ?");
			prep_stmt.setString(1, "%"+searchTxt+"%");
			prep_stmt.setString(2, "%"+searchTxt+"%");
			prep_stmt.setString(3, "%"+searchTxt+"%");
			prep_stmt.setString(4, "%"+searchTxt+"%");
			prep_stmt.setString(5, "%"+searchTxt+"%");
			rs = prep_stmt.executeQuery();			
			model.setRowCount(0); // Resets the row
			
			// Checks for match Results
			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "No results match your search", "Error", JOptionPane.ERROR_MESSAGE);
				fetchStudentData();
			} else {
				// Iterate each row
				do {
					int student_id = rs.getInt("student_id");
					int student_number = rs.getInt("student_number");
					String student_name = rs.getString("student_name");
					String student_gender = rs.getString("student_gender");
					String student_department = rs.getString("student_department");
					String student_timeIn = rs.getString("student_timeIn");
					
					model.addRow(new Object[] {student_id, student_number, student_name, student_gender, student_department, student_timeIn});
				} while(rs.next());
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Override method for border radius
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 100, 100);
        g2d.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// Search Button
		if(e.getSource() == SearchButton) {
			String search = searchTxt.getText();
			searchStudent(search);
		}
		
		// Button Functions for Modals
		if(e.getSource() == AddLogButton) {
			AddLogModal modal = new AddLogModal();
			modal.setLocationRelativeTo(null);
			modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			modal.setVisible(true);
			modal.addWindowListener(new WindowAdapter() {
				@Override
	            public void windowClosed(WindowEvent e) {
	                // This method is called when the window is closed.
					fetchStudentData();
	            }
			});
		}
		if(e.getSource() == EditLogButton) {
			String sID = studentIDValue.getText();
			if(!sID.isEmpty()) {
				int studentID = Integer.parseInt(sID);
				EditLogModal modal = new EditLogModal(studentID);
				modal.setLocationRelativeTo(null);
				modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				modal.setVisible(true);
				modal.addWindowListener(new WindowAdapter() {
					@Override
		            public void windowClosed(WindowEvent e) {
		                // This method is called when the window is closed.
						fetchStudentData();
		            }
				});	
			} else {
				JOptionPane.showMessageDialog(null, "Select Data to Edit first", "WARNING", JOptionPane.WARNING_MESSAGE);
			}	
		}
		
		// Delete Log
		if(e.getSource() == DeleteLogButton) {
			String sID = studentIDValue.getText();
			if(!sID.isEmpty()) {
				int result = JOptionPane.showConfirmDialog(null , "Are you sure you want to Delete this data?", 
						"Delete Confirmation",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					int selectedId = Integer.valueOf(sID);
					try {
						prep_stmt = connect.conn.prepareStatement("DELETE FROM students WHERE student_id = ?");
						prep_stmt.setInt(1, selectedId);
						
						int deletedRow = prep_stmt.executeUpdate();
						
						if(deletedRow == 1) {
							JOptionPane.showMessageDialog(null, "Record has been Deleted!");
							fetchStudentData();
						}
						
						prep_stmt.close();
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Select Data to Delete first", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}
}