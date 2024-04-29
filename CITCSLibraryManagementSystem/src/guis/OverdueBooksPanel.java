package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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

public class OverdueBooksPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");

	/**
	 * Create the panel.
	 */
	public OverdueBooksPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 682)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel overdueBooks = new JLabel("Overdue Books");
		overdueBooks.setBounds(34, 32, 341, 50);
		inter_black.applyFont(overdueBooks, 36f, CommonConstants.HEADER_COLOR);
		add(overdueBooks);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(777, 45, 65, 25);
		add(adminName);
		
		RoundedTextField search = new RoundedTextField(15);
		search.setPlaceholder("Search...");
		search.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		inter_regular.applyFont(search, 14f, Color.BLACK);
		search.setBackground(new Color(220, 220, 220));
		search.setBounds(40, 117, 159, 32);
		add(search);
		
		RoundedButton searchBut = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBut.setIcon(new ImageIcon(BookListPanel.class.getResource("/icons/search-iconpng.png")));
		searchBut.setBounds(200,117,38,32);
		add(searchBut);
		
		RoundedPanel borDetail = new RoundedPanel(15);
		borDetail.setBounds(40, 370, 894, 290);
		borDetail.setBackground(new Color(216,216,216));
		add(borDetail);
		borDetail.setLayout(null);
		
		JLabel borrowDetails = new JLabel("Borrow Details:");
		borrowDetails.setBounds(30, 10, 160, 30);
		inter_bold.applyFont(borrowDetails,20F, Color.BLACK);
		borDetail.add(borrowDetails);
		
		JLabel lblBorrowNo = new JLabel("Borrow No.:");
		inter_bold.applyFont(lblBorrowNo, 15F, Color.black);
		lblBorrowNo.setBounds(770, 15, 108, 27);
		borDetail.add(lblBorrowNo);
		
		JLabel BorrowNumberNoValue = new JLabel("1");
		BorrowNumberNoValue.setBounds(860, 15, 31, 27);
		inter_regular.applyFont(BorrowNumberNoValue, 15f, Color.black);
		borDetail.add(BorrowNumberNoValue);
		
		JLabel bookAuthor = new JLabel("Book Author");
		bookAuthor.setBounds(47, 147, 183,27);
		inter_bold.applyFont(bookAuthor, 13F, Color.black);
		borDetail.add(bookAuthor);
		
		JLabel category = new JLabel("Category:");
		category.setBounds(47, 215, 138, 20);
		inter_bold.applyFont(category, 13F, Color.black);
		borDetail.add(category);
		
		
		JLabel borrowersInfo = new JLabel("<html> <u>Book Information:</u> </html>");
		inter_regular.applyFont(borrowersInfo, 13F, Color.black);
		borrowersInfo.setBounds(47,45, 200,40);
		borDetail.add(borrowersInfo);
		
		JLabel bookTitle = new JLabel("Book Title:");
		bookTitle.setBounds(47, 76, 183, 28);
		inter_bold.applyFont(bookTitle, 13F, Color.black);
		borDetail.add(bookTitle);
		
		JTextArea bookTitleValue = new JTextArea("Jujutsu Kaisen");
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBounds(47, 100, 213, 54);
		bookTitleValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(bookTitleValue, 13F, Color.black);
		borDetail.add(bookTitleValue);
		
		JTextArea bookAuthorValue = new JTextArea("Rodel");
		bookAuthorValue.setWrapStyleWord(true);
		bookAuthorValue.setLineWrap(true);
		bookAuthorValue.setFocusable(false);
		bookAuthorValue.setEditable(false);
		bookAuthorValue.setBounds(47, 167, 213, 50);
		bookAuthorValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookAuthorValue, 13F, Color.black);
		borDetail.add( bookAuthorValue);
		
		JTextArea bookCategoryValue = new JTextArea("Local");
		bookCategoryValue.setWrapStyleWord(true);
		bookCategoryValue.setLineWrap(true);
		bookCategoryValue.setFocusable(false);
		bookCategoryValue.setEditable(false);
		bookCategoryValue.setBounds(48, 232, 128, 28);
		bookCategoryValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookCategoryValue, 13F, Color.black);
		borDetail.add( bookCategoryValue);
		
		JLabel borrowInfo = new JLabel("<html> <u>Borrower's Information:</u></html>");
		borrowInfo.setBounds(259, 45, 200, 40);
		inter_regular.applyFont(borrowInfo, 13F, Color.black);
		borDetail.add(borrowInfo);
		
		JLabel studentName = new JLabel("Student Name:");
		studentName.setBounds(259, 83, 160, 14);
		inter_bold.applyFont(studentName, 13F, Color.black);
		borDetail.add(studentName);
		
		JTextArea nameValue = new JTextArea("Boks");
		nameValue.setWrapStyleWord(true);
		nameValue.setLineWrap(true);
		nameValue.setFocusable(false);
		nameValue.setEditable(false);
		nameValue.setBounds(259, 100, 200, 28);
		nameValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( nameValue, 13F, Color.black);
		borDetail.add( nameValue);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(259, 152, 70, 14);
		inter_bold.applyFont(gender, 13F, Color.black);
		borDetail.add(gender);
		
		JTextArea genderValue = new JTextArea("Gay");
		 genderValue .setWrapStyleWord(true);
		 genderValue .setLineWrap(true);
		 genderValue .setFocusable(false);
		 genderValue .setEditable(false);
		 genderValue .setBounds(259, 166, 172, 28);
		 genderValue .setBackground(new Color(216,216,216));
		inter_regular.applyFont( genderValue , 13F, Color.black);
		borDetail.add(  genderValue );
		
		JLabel department = new JLabel("Department:");
		department.setBounds(259, 218, 90, 14);
		inter_bold.applyFont(department, 13F, Color.black);
		borDetail.add(department);
		
		JTextArea deparText = new JTextArea("CITCS");
		deparText .setWrapStyleWord(true);
		deparText .setLineWrap(true);
		deparText .setFocusable(false);
		deparText .setEditable(false);
		deparText .setBounds(259, 233, 128, 28);
		deparText .setBackground(new Color(216,216,216));
		inter_regular.applyFont( deparText , 13F, Color.black);
		borDetail.add(  deparText );
		
		JLabel borrowDate = new JLabel("Borrowed Date:");
		borrowDate.setBounds(464, 83, 110, 14);
		inter_bold.applyFont(borrowDate, 13F, Color.black);
		borDetail.add(borrowDate);
		
		
		JTextArea borroweredDateValue = new JTextArea("2024-11-11 13:23:44");
		borroweredDateValue.setWrapStyleWord(true);
		borroweredDateValue.setLineWrap(true);
		borroweredDateValue.setFocusable(false);
		borroweredDateValue.setEditable(false);
		borroweredDateValue.setBounds(464, 100, 138, 28);
		borroweredDateValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(borroweredDateValue , 13F, Color.black);
		borDetail.add(borroweredDateValue);
		
		JLabel dueDate = new JLabel("Due Date:");
		 dueDate.setBounds(464, 152, 110, 14);
		inter_bold.applyFont( dueDate, 13F, Color.black);
		borDetail.add( dueDate);
		
		JTextArea dueDateValue = new JTextArea("2024-11-11 13:23:44");
		dueDateValue.setWrapStyleWord(true);
		dueDateValue.setLineWrap(true);
		dueDateValue.setFocusable(false);
		dueDateValue.setEditable(false);
		dueDateValue.setBounds(464, 166, 138, 26);
		dueDateValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(dueDateValue , 13F, Color.black);
		borDetail.add(dueDateValue);
		
		JLabel status = new JLabel("Status:");
		status.setBounds(464, 216, 90, 14);
		inter_bold.applyFont(status, 13F, Color.black);
		borDetail.add(status);
		
		JTextArea statusValue = new JTextArea("Pending for return");
		statusValue.setWrapStyleWord(true);
		statusValue.setLineWrap(true);
		statusValue.setFocusable(false);
		statusValue.setEditable(false);
		statusValue.setBounds(464, 231, 138, 26);
		statusValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(statusValue , 13F, Color.black);
		borDetail.add(statusValue);
		
		JLabel overDue = new JLabel("No. of Overdue");
		inter_bold.applyFont(overDue, 20F, Color.black);
		overDue.setBounds(650,120,180,30);
		borDetail.add(overDue);
		
		JLabel days = new JLabel("Days:");
		inter_bold.applyFont(days, 20F, Color.black);
		days.setBounds(650,150,100,30);
		borDetail.add(days);
		
		JTextArea numberDays = new JTextArea("69");
		numberDays.setWrapStyleWord(true);
		numberDays.setLineWrap(true);
		numberDays.setFocusable(false);
		numberDays.setEditable(false);
		numberDays.setBounds(713,153,100,30);
		numberDays.setBackground(new Color(216,216,216));
		inter_regular.applyFont(numberDays, 20F, Color.black);
		borDetail.add(numberDays);
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
