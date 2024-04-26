package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedPanel;
import inheritances.RoundedTextField;

public class BorrowedBooksPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField search;
	private RoundedPanel studentDetailsPanel;
	

	/**
	 * Create the panel.
	 */
	public BorrowedBooksPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 682)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel borrowedBooks = new JLabel("Borrowered Books");
		borrowedBooks.setBounds(34, 32, 341, 50);
		inter_black.applyFont(borrowedBooks, 36f, CommonConstants.HEADER_COLOR);
		add(borrowedBooks);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);
		
		search = new RoundedTextField(10);
		search.setBounds(40, 117, 159, 32);
		search.setForeground(new Color(0,0,0,0));
		search.setBackground(new Color(220,220,220));
		search.addFocusListener(new FocusListener(){
		    @Override
		    public void focusGained(FocusEvent event) {
		        if (search.getText().equals("Search...")) {
		            search.setText("");
		            search.setForeground(Color.BLACK);
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent event) {
		        if (search.getText().equals("")) {
		            search.setText("Search...");
		            search.setForeground(Color.decode("#797878"));
		        }
		    }
		});
		add(search);
		
		
		studentDetailsPanel = new RoundedPanel(20);
		studentDetailsPanel.setBounds(341, 417, 530, 232);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel StudentDetails = new JLabel("Borrow Details: ");
		StudentDetails.setBounds(24, 17, 186, 27);
		inter_bold.applyFont(StudentDetails, 20F, Color.black);
		studentDetailsPanel.add(StudentDetails);
		
		JLabel StudentNumber = new JLabel("Borrow ID:");
		StudentNumber.setBounds(24, 53, 183, 38);
		inter_bold.applyFont(StudentNumber, 15F, Color.black);
		studentDetailsPanel.add(StudentNumber); 
		
		JLabel bookTitle = new JLabel("Book Title:");
		bookTitle.setBounds(24, 88, 183, 38);
		inter_bold.applyFont(bookTitle, 15F, Color.black);
		studentDetailsPanel.add(bookTitle);
		
		JLabel status = new JLabel("Status:");
		status.setBounds(24, 123, 183, 38);
		inter_bold.applyFont(status, 15F, Color.black);
		studentDetailsPanel.add(status);
		
		JLabel borrowersInfo = new JLabel("Borrower's Information");
		inter_regular.applyFont(borrowersInfo, 17F, Color.black);
		borrowersInfo.setBounds(250,20, 200,40);
		studentDetailsPanel.add(borrowersInfo);
		
		JLabel studentId = new JLabel("Student ID:");
		studentId.setBounds(250, 60, 183, 38);
		inter_bold.applyFont(studentId, 15F, Color.black);
		studentDetailsPanel.add(studentId);
		
		JLabel StudentName = new JLabel("Name: ");
		StudentName.setBounds(250, 88, 183, 38);
		inter_bold.applyFont(StudentName, 15F, Color.black);
		studentDetailsPanel.add(StudentName);
		
		JLabel year = new JLabel("Year:");
		year.setBounds(250, 116, 138, 38);
		inter_bold.applyFont(year, 15F, Color.black);
		studentDetailsPanel.add(year);
		
		JLabel Department = new JLabel("Course:");
		Department.setBounds(250, 144, 138, 38);
		inter_bold.applyFont(Department, 15F, Color.black);
		studentDetailsPanel.add(Department);
		
		JLabel TimeIn = new JLabel("Borrowed Date: ");
		TimeIn.setBounds(250, 172, 138, 38);
		inter_bold.applyFont(TimeIn, 15F, Color.black);
		studentDetailsPanel.add(TimeIn);
		
		// Values 
		JLabel StudentNumberNoValue = new JLabel("1");
		StudentNumberNoValue.setBounds(110, 65, 50, 14);
		inter_regular.applyFont(StudentNumberNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNumberNoValue);
		
		JLabel jujutsu = new JLabel("Jujutsu Kaisen");
		jujutsu.setBounds(112, 100, 108, 14);
		inter_regular.applyFont(jujutsu, 15F, Color.black);
		studentDetailsPanel.add(jujutsu);
		
		JLabel StudentNameNoValue = new JLabel("Bok Wan");
		StudentNameNoValue.setBounds(312, 100, 160, 14);
		inter_regular.applyFont(StudentNameNoValue, 15F, Color.black);
		studentDetailsPanel.add(StudentNameNoValue);
		
		JLabel yearlabel = new JLabel("2nd year");
		yearlabel.setBounds(300, 129, 70, 14);
		inter_regular.applyFont(yearlabel, 15F, Color.black);
		studentDetailsPanel.add(yearlabel);
		
		//nabaliktad lang pero inayos ko naman positioning nyan
		JLabel TimeInNoValue = new JLabel("2024-11-11 13:23:34");
		TimeInNoValue.setBounds(371, 184, 160, 14);
		inter_regular.applyFont(TimeInNoValue, 15F, Color.black);
		studentDetailsPanel.add(TimeInNoValue);
		
		JLabel studid = new JLabel("12345678");
		studid.setBounds(344, 72, 74, 14);
		inter_regular.applyFont(studid, 15F, Color.black);
		studentDetailsPanel.add(studid);
		
		JLabel course = new JLabel("CS");
		course.setBounds(315, 157, 46, 14);
		inter_regular.applyFont(course, 15F, Color.black);
		studentDetailsPanel.add(course);
		
		JLabel pending = new JLabel("Pending");
		pending.setBounds(90, 133, 70, 20);
		inter_regular.applyFont(pending, 15F, Color.black);
		studentDetailsPanel.add(pending);
		

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
