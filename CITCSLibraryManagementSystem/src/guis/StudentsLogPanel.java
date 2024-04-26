package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedPanel;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

public class StudentsLogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedPanel studentDetailsPanel; 
	private RoundedTextField SearchBar;

	/**
	 * Create the panel.
	 */
	public StudentsLogPanel() {
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
		
		RoundedButton AddLogButton = new RoundedButton ("Add Log",13, Color.decode("#429737"));
		AddLogButton.setBounds(824, 117, 97, 33);
		inter_bold.applyFont(AddLogButton, 14F, Color.white);
		add(AddLogButton);
		
		SearchBar = new RoundedTextField(15);
		SearchBar.setBounds(40, 117, 159, 32);
		SearchBar.setBackground(Color.decode("#D9D9D9"));
		SearchBar.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		SearchBar.setText("Search...");
		SearchBar.setForeground(Color.decode("#797878"));
		SearchBar.addFocusListener(new FocusListener(){
		    @Override
		    public void focusGained(FocusEvent event) {
		        if (SearchBar.getText().equals("Search...")) {
		            SearchBar.setText("");
		            SearchBar.setForeground(Color.BLACK);
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent event) {
		        if (SearchBar.getText().equals("")) {
		            SearchBar.setText("Search...");
		            SearchBar.setForeground(Color.decode("#797878"));
		        }
		    }
		});
		add(SearchBar);
		SearchBar.setColumns(10);
		
		//Student's Details Panel
		
		studentDetailsPanel = new RoundedPanel(44);
		studentDetailsPanel.setBounds(34, 417, 887, 232);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel StudentDetails = new JLabel("Student Details: ");
		StudentDetails.setBounds(24, 17, 186, 27);
		inter_bold.applyFont(StudentDetails, 20F, Color.black);
		studentDetailsPanel.add(StudentDetails);
		
		JLabel StudentNumber = new JLabel("Student No.: ");
		StudentNumber.setBounds(37, 68, 183, 38);
		inter_bold.applyFont(StudentNumber, 15F, Color.black);
		studentDetailsPanel.add(StudentNumber);
		
		JLabel StudentName = new JLabel("Student Name: ");
		StudentName.setBounds(37, 103, 183, 38);
		inter_bold.applyFont(StudentName, 15F, Color.black);
		studentDetailsPanel.add(StudentName);
		
		JLabel Gender = new JLabel("Gender: ");
		Gender.setBounds(37, 143, 186, 27);
		inter_bold.applyFont(Gender, 15F, Color.black);
		studentDetailsPanel.add(Gender);
		
		JLabel Department = new JLabel("Department: ");
		Department.setBounds(334, 68, 138, 38);
		inter_bold.applyFont(Department, 15F, Color.black);
		studentDetailsPanel.add(Department);
		
		JLabel TimeIn = new JLabel("Time In: ");
		TimeIn.setBounds(334, 103, 138, 38);
		inter_bold.applyFont(TimeIn, 15F, Color.black);
		studentDetailsPanel.add(TimeIn);
		
		// Values 
		JLabel StudentNumberNoValue = new JLabel("1");
		StudentNumberNoValue.setBounds(130, 80, 50, 14);
		inter_regular.applyFont(StudentNumberNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNumberNoValue);
		
		JLabel StudentNameNoValue = new JLabel("Bok Wan");
		StudentNameNoValue.setBounds(150, 115, 160, 14);
		inter_regular.applyFont(StudentNameNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNameNoValue);
		
		JLabel GenderNoValue = new JLabel("Male");
		GenderNoValue.setBounds(100, 149, 50, 14);
		inter_regular.applyFont(GenderNoValue, 15F, Color.black);
		studentDetailsPanel.add(GenderNoValue);
		
		JLabel DepartmentNoValue = new JLabel("CITCS");
		DepartmentNoValue.setBounds(429, 80, 50, 14);
		inter_regular.applyFont(DepartmentNoValue, 15F, Color.black);
		studentDetailsPanel.add(DepartmentNoValue);
		
		JLabel TimeInNoValue = new JLabel("2024-11-11 13:23:34");
		TimeInNoValue.setBounds(394, 115, 160, 14);
		inter_regular.applyFont(TimeInNoValue, 15F, Color.black);
		studentDetailsPanel.add(TimeInNoValue);
		
		//Action Label and Buttons
		
		JLabel Actions = new JLabel("Actions:");
		Actions.setBounds(636, 138, 186, 27);
		inter_bold.applyFont(Actions, 20F, Color.black);
		studentDetailsPanel.add(Actions);
	
		RoundedButton EditLogButton = new RoundedButton("Edit Log", 13, Color.decode("#0060CC"));
		EditLogButton.setBounds(636, 169, 100, 35);
		inter_bold.applyFont(EditLogButton, 14F, Color.white);
		studentDetailsPanel.add(EditLogButton);
		
		RoundedButton DeleteLogButton = new RoundedButton("Delete Log", 13, Color.decode("#E74343"));
		DeleteLogButton.setBounds(752, 169, 110, 35);
		inter_bold.applyFont(DeleteLogButton, 14F, Color.white);
		studentDetailsPanel.add(DeleteLogButton);		
		
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
}
