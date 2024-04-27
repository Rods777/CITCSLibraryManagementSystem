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
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

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
	private RoundedPanel studentDetailsPanel, qrCodePanel;

	

	/**
	 * Create the panel.
	 */
	public BorrowedBooksPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 682)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header Changed
		JLabel borrowedBooks = new JLabel("Borrowed Books");
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
		search.setPlaceholder("Search...");
		inter_regular.applyFont(search, 14f, Color.BLACK);
		search.setBackground(new Color(220, 220, 220));
		search.setBounds(80, 117, 159, 32);
		search.setBorder(new LineBorder(new Color(0,0,0,0)));
		add(search);
		//adding icon on searchbutton
		RoundedButton searchBut = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBut.setIcon(new ImageIcon(BookListPanel.class.getResource("/icons/search-iconpng.png")));
		searchBut.setBounds(240, 117, 38, 32);
		add(searchBut);
		
		qrCodePanel = new RoundedPanel(60);
		qrCodePanel.setBounds(80,385, 214,233);
		qrCodePanel.setBackground(new Color(216,216,216));
		add(qrCodePanel);
		qrCodePanel.setLayout(null);
		
		JLabel scanLabel = new JLabel("Scan To Borrow");
		scanLabel.setBounds(28, 170, 170, 50);
		inter_bold.applyFont(scanLabel, 20F, Color.black);
		qrCodePanel.add(scanLabel);
		
		
		
		studentDetailsPanel = new RoundedPanel(20);
		studentDetailsPanel.setBounds(325, 385, 620, 270);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel StudentDetails = new JLabel("Borrow Details: ");
		StudentDetails.setBounds(24, 15, 186, 27);
		inter_bold.applyFont(StudentDetails, 18F, Color.black);
		studentDetailsPanel.add(StudentDetails);
		
		
		
		JLabel borrowersInfo = new JLabel("<html> <u>Book Information:</u> </html>");
		inter_regular.applyFont(borrowersInfo, 13F, Color.black);
		borrowersInfo.setBounds(24,55, 200,40);
		studentDetailsPanel.add(borrowersInfo);
		
		JLabel bookTitle = new JLabel("Book Title:");
		bookTitle.setBounds(24, 98, 183, 38);
		inter_bold.applyFont(bookTitle, 13F, Color.black);
		studentDetailsPanel.add(bookTitle);
		
		JTextArea jujutsu = new JTextArea("Jujutsu Kaisen");
		jujutsu.setWrapStyleWord(true);
		jujutsu.setLineWrap(true);
		jujutsu.setFocusable(false);
		jujutsu.setEditable(false);
		jujutsu.setBounds(24, 123, 108, 28);
		jujutsu.setBackground(new Color(216,216,216));
		inter_regular.applyFont(jujutsu, 13F, Color.black);
		studentDetailsPanel.add(jujutsu);
		
		JLabel bookAuthor = new JLabel("Book Author");
		bookAuthor.setBounds(24, 145, 183,38);
		inter_bold.applyFont(bookAuthor, 13F, Color.black);
		studentDetailsPanel.add(bookAuthor);
		
		JTextArea autText = new JTextArea("Jaymark D. Cabildo");
		autText.setWrapStyleWord(true);
		autText.setLineWrap(true);
		autText.setFocusable(false);
		autText.setEditable(false);
		autText.setBounds(24, 170, 128, 28);
		autText.setBackground(new Color(216,216,216));
		inter_regular.applyFont( autText, 13F, Color.black);
		studentDetailsPanel.add( autText);
		
		JLabel category = new JLabel("Category:");
		category.setBounds(24, 185, 138, 38);
		inter_bold.applyFont(category, 13F, Color.black);
		studentDetailsPanel.add(category);
		
		JTextArea catText = new JTextArea("Tagalog");
		catText.setWrapStyleWord(true);
		catText.setLineWrap(true);
		catText.setFocusable(false);
		catText.setEditable(false);
		catText.setBounds(24, 210, 128, 28);
		catText.setBackground(new Color(216,216,216));
		inter_regular.applyFont( catText, 13F, Color.black);
		studentDetailsPanel.add( catText);
		
		JLabel borrowInfo = new JLabel("<html> <u>Borrower's Information:</u></html>");
		borrowInfo.setBounds(240, 55, 200, 40);
		inter_regular.applyFont(borrowInfo, 13F, Color.black);
		studentDetailsPanel.add(borrowInfo);
		
		JLabel studentName = new JLabel("Student Name:");
		studentName.setBounds(240, 110, 160, 14);
		inter_bold.applyFont(studentName, 13F, Color.black);
		studentDetailsPanel.add(studentName);
		
		JTextArea nameText = new JTextArea("Boks");
		nameText.setWrapStyleWord(true);
		nameText.setLineWrap(true);
		nameText.setFocusable(false);
		nameText.setEditable(false);
		nameText.setBounds(240, 124, 128, 28);
		nameText.setBackground(new Color(216,216,216));
		inter_regular.applyFont( nameText, 13F, Color.black);
		studentDetailsPanel.add( nameText);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(240, 157, 70, 14);
		inter_bold.applyFont(gender, 13F, Color.black);
		studentDetailsPanel.add(gender);
		
		JTextArea genderText = new JTextArea("Male");
		 genderText .setWrapStyleWord(true);
		 genderText .setLineWrap(true);
		 genderText .setFocusable(false);
		 genderText .setEditable(false);
		 genderText .setBounds(240, 171, 128, 28);
		 genderText .setBackground(new Color(216,216,216));
		inter_regular.applyFont( genderText , 13F, Color.black);
		studentDetailsPanel.add(  genderText );
		
		JLabel department = new JLabel("Department:");
		department.setBounds(240, 197, 90, 14);
		inter_bold.applyFont(department, 13F, Color.black);
		studentDetailsPanel.add(department);
		
		JTextArea deparText = new JTextArea("CITCS");
		deparText .setWrapStyleWord(true);
		deparText .setLineWrap(true);
		deparText .setFocusable(false);
		deparText .setEditable(false);
		deparText .setBounds(240, 211, 128, 28);
		deparText .setBackground(new Color(216,216,216));
		inter_regular.applyFont( deparText , 13F, Color.black);
		studentDetailsPanel.add(  deparText );
		
		JLabel borrowDate = new JLabel("Borrowed Date:");
		borrowDate.setBounds(460, 111, 110, 14);
		inter_bold.applyFont(borrowDate, 13F, Color.black);
		studentDetailsPanel.add(borrowDate);
		
		
		JTextArea borDate = new JTextArea("2024-11-11 13:23:44");
		borDate.setWrapStyleWord(true);
		borDate.setLineWrap(true);
		borDate.setFocusable(false);
		borDate.setEditable(false);
		borDate.setBounds(460, 126, 128, 28);
		borDate.setBackground(new Color(216,216,216));
		inter_regular.applyFont(borDate , 13F, Color.black);
		studentDetailsPanel.add(borDate);
		
		JLabel dueDate = new JLabel("Due Date:");
		 dueDate.setBounds(460, 157, 110, 14);
		inter_bold.applyFont( dueDate, 13F, Color.black);
		studentDetailsPanel.add( dueDate);
		
		JTextArea dDate = new JTextArea("2024-11-11 13:23:44");
		dDate.setWrapStyleWord(true);
		dDate.setLineWrap(true);
		dDate.setFocusable(false);
		dDate.setEditable(false);
		dDate.setBounds(460, 172, 128, 26);
		dDate.setBackground(new Color(216,216,216));
		inter_regular.applyFont(dDate , 13F, Color.black);
		studentDetailsPanel.add(dDate);
		
		JLabel status = new JLabel("Status:");
		status.setBounds(460, 196, 90, 14);
		inter_bold.applyFont(status, 13F, Color.black);
		studentDetailsPanel.add(status);
		
		JTextArea statusText = new JTextArea("Pending for return");
		statusText.setWrapStyleWord(true);
		statusText.setLineWrap(true);
		statusText.setFocusable(false);
		statusText.setEditable(false);
		statusText.setBounds(460, 210, 128, 26);
		statusText.setBackground(new Color(216,216,216));
		inter_regular.applyFont(statusText , 13F, Color.black);
		studentDetailsPanel.add(statusText);
		
		

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
