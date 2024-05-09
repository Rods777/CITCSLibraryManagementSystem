package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditLogModal extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField nameTxt, studentNumberTxt;
	private JComboBox genderCb, departmentCb;
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the dialog.
	 */
	
	int studentID;
	
	public EditLogModal(int studID) {
		this.studentID = studID;
		
		connect.Connect();
		setBounds(100, 100, 550, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		contentPanel.setLayout(null);
		
		JLabel addLog = new JLabel("Edit Log");
		addLog.setBounds(43, 39, 159, 59);
		inter_extrabold.applyFont(addLog, 36f, Color.decode("#14412D"));
		contentPanel.add(addLog);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(27, 120, 480, 1);
		contentPanel.add(separator);
		
		JLabel lblStudentNumber = new JLabel("Student No.:");
		inter_bold.applyFont(lblStudentNumber, 16f, Color.BLACK);
		lblStudentNumber.setBounds(43, 153, 102, 14);
		contentPanel.add(lblStudentNumber);
		
		studentNumberTxt = new RoundedTextField(30);
		inter_regular.applyFont(studentNumberTxt, 16f, Color.BLACK);
		studentNumberTxt.setColumns(10);
		studentNumberTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		studentNumberTxt.setBackground(new Color(242, 242, 242));
		studentNumberTxt.setBounds(190, 140, 312, 40);
		contentPanel.add(studentNumberTxt);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(43, 216, 102, 14);
		inter_bold.applyFont(name, 16f, Color.BLACK);
		contentPanel.add(name);
		
		nameTxt = new RoundedTextField(30);
		nameTxt.setPlaceholder("Surname, Firstname, M.I.");
		inter_regular.applyFont(nameTxt, 16f, Color.BLACK);
		nameTxt.setBounds(190, 203, 312, 40);
		nameTxt.setBackground(Color.decode("#F2F2F2"));
		nameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		contentPanel.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(43, 276, 102, 22);
		inter_bold.applyFont(gender, 16f, Color.BLACK);
		contentPanel.add(gender);
		
		genderCb = new JComboBox();
		genderCb.addItem("Male");
		genderCb.addItem("Female");
		genderCb.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(genderCb, 16f, Color.BLACK);
		genderCb.setSelectedItem(null);
		genderCb.setFocusable(false);
		genderCb.setBounds(190, 272, 312, 30);
		contentPanel.add(genderCb);
		
		JLabel department = new JLabel("Department:");
		department.setBounds(43, 336, 102, 22);
		inter_bold.applyFont(department, 16f, Color.BLACK);
		contentPanel.add(department);
		
		departmentCb = new JComboBox();
		departmentCb.addItem("CITCS");
		departmentCb.addItem("CBA");
		departmentCb.addItem("CCJ");
		departmentCb.addItem("COM");
		departmentCb.addItem("CTE");
		departmentCb.addItem("IPPG");
		departmentCb.addItem("GS");
		departmentCb.addItem("COA");
		inter_regular.applyFont(departmentCb, 16f, Color.BLACK);
		departmentCb.setSelectedItem(null);
		departmentCb.setFocusable(false);
		departmentCb.setBackground(Color.decode("#F2F2F2"));
		departmentCb.setBounds(190, 332, 312, 30);
		contentPanel.add(departmentCb);	
		
		RoundedButton saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		saveBtn.addActionListener(this);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 405, 453, 62);
		contentPanel.add(saveBtn);
		
		// Set the value of student data
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM students WHERE student_id = ?");
			prep_stmt.setInt(1, studentID);
			rs = prep_stmt.executeQuery();
			
			if(rs.next()) {
				String studentNumber = rs.getString(2);
				String studentName = rs.getString(3);
		        String studentGender = rs.getString(4);
		        String studentDepartment = rs.getString(5);
				
		        studentNumberTxt.setText(studentNumber);
		        nameTxt.setText(studentName);
		        genderCb.setSelectedItem(studentGender);
				departmentCb.setSelectedItem(studentDepartment);
			}
			
			prep_stmt.close();
			rs.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String studNum = studentNumberTxt.getText();
		String name = nameTxt.getText();
        String gender = (String) genderCb.getSelectedItem();
        String department = (String) departmentCb.getSelectedItem();

        if (studNum.isEmpty() || name.isEmpty() || gender == null || department == null) {
            JOptionPane.showMessageDialog(null, "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int studentNumber = Integer.parseInt(studNum);
				try {
					prep_stmt = connect.conn.prepareStatement("UPDATE students SET student_number = ?, student_name = ?, student_gender = ?, student_department = ? WHERE student_id = ?");
					prep_stmt.setInt(1, studentNumber);
					prep_stmt.setString(2, name);
					prep_stmt.setString(3, gender);
					prep_stmt.setString(4, department);
					prep_stmt.setInt(5, studentID);
					
					int row = prep_stmt.executeUpdate();
					
					if(row == 1) {
						JOptionPane.showMessageDialog(null, "Successfully Edit Log!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Editing Log Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					prep_stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Student Number must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	


}
