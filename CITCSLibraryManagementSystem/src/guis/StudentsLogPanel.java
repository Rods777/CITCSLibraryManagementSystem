package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedPanel;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;
import modals.AddLogModal;
import modals.EditLogModal;

import javax.swing.JTextArea;

public class StudentsLogPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedPanel studentDetailsPanel; 
	private RoundedTextField SearchBar;
	private RoundedButton AddLogButton;
	private RoundedButton EditLogButton;

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
		
		AddLogButton = new RoundedButton ("Add Log",13, Color.decode("#429737"));
		AddLogButton.addActionListener(this);
		AddLogButton.setBounds(824, 117, 97, 33);
		inter_bold.applyFont(AddLogButton, 14F, Color.white);
		add(AddLogButton);
		
		SearchBar = new RoundedTextField(15);
		SearchBar.setPlaceholder("Search...");
		SearchBar.setBounds(40, 117, 159, 32);
		inter_regular.applyFont(SearchBar, 15F, Color.black);
		SearchBar.setBackground(Color.decode("#D9D9D9"));
		SearchBar.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(SearchBar);
		SearchBar.setColumns(10);
		
		RoundedButton SearchButton = new RoundedButton("", 10, Color.decode("#569FF0"));
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
		
		JLabel StudentNumber = new JLabel("Student No.: ");
		StudentNumber.setBounds(62, 63, 183, 38);
		inter_bold.applyFont(StudentNumber, 15F, Color.black);
		studentDetailsPanel.add(StudentNumber);
		
		JLabel StudentName = new JLabel("Student Name: ");
		StudentName.setBounds(62, 110, 183, 38);
		inter_bold.applyFont(StudentName, 15F, Color.black);
		studentDetailsPanel.add(StudentName);
		
		JLabel Gender = new JLabel("Gender: ");
		Gender.setBounds(331, 69, 186, 27);
		inter_bold.applyFont(Gender, 15F, Color.black);
		studentDetailsPanel.add(Gender);
		
		JLabel Department = new JLabel("Department: ");
		Department.setBounds(331, 110, 183, 38);
		inter_bold.applyFont(Department, 15F, Color.black);
		studentDetailsPanel.add(Department);
		
		JLabel TimeIn = new JLabel("Time In: ");
		TimeIn.setBounds(331, 164, 64, 27);
		inter_bold.applyFont(TimeIn, 15F, Color.black);
		studentDetailsPanel.add(TimeIn);
		
		// Values 
		JLabel StudentNumberNoValue = new JLabel("1");
		StudentNumberNoValue.setBounds(62, 91, 50, 14);
		inter_regular.applyFont(StudentNumberNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNumberNoValue);
		
		JTextArea StudentNameNoValue = new JTextArea("Bok Wan Two Three Four Five Six Seven Eight");
		StudentNameNoValue.setWrapStyleWord(true);
		StudentNameNoValue.setLineWrap(true);
		StudentNameNoValue.setFocusable(false);
		StudentNameNoValue.setEditable(false);
		StudentNameNoValue.setBounds(62, 139, 200, 80);
		StudentNameNoValue.setBackground(new Color(216, 216, 216));
		inter_regular.applyFont(StudentNameNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNameNoValue);
		
		JLabel GenderNoValue = new JLabel("Male");
		GenderNoValue.setBounds(331, 91, 50, 14);
		inter_regular.applyFont(GenderNoValue, 15F, Color.black);
		studentDetailsPanel.add(GenderNoValue);
		
		JLabel DepartmentNoValue = new JLabel("CITCS");
		DepartmentNoValue.setBounds(331, 140, 50, 14);
		inter_regular.applyFont(DepartmentNoValue, 15F, Color.black);
		studentDetailsPanel.add(DepartmentNoValue);
		
		JLabel TimeInNoValue = new JLabel("2024-11-11 13:23:34");
		TimeInNoValue.setBounds(331, 190, 160, 14);
		inter_regular.applyFont(TimeInNoValue, 15F, Color.black);
		studentDetailsPanel.add(TimeInNoValue);
		
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
		
		RoundedButton DeleteLogButton = new RoundedButton("Delete Log", 13, Color.decode("#E74343"));
		DeleteLogButton.setBounds(730, 134, 110, 35);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// Button Functions for Modals
		if(e.getSource() == AddLogButton) {
			AddLogModal frame = new AddLogModal();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}
		if(e.getSource() == EditLogButton) {
			EditLogModal frame = new EditLogModal();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
}
