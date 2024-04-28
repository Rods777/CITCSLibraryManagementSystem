package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedPanel;
import inheritances.RoundedTextField;
import modals.BorrowBookScanner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;

public class BorrowedBooksPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField search;
	private RoundedPanel studentDetailsPanel, barcodeScannerPanel;

	

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
		search.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		inter_regular.applyFont(search, 14f, Color.BLACK);
		search.setBackground(new Color(220, 220, 220));
		search.setBounds(34, 117, 159, 32);
		add(search);
		//adding icon on searchbutton
		RoundedButton searchBut = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchBut.setIcon(new ImageIcon(BookListPanel.class.getResource("/icons/search-iconpng.png")));
		searchBut.setBounds(196, 117, 38, 32);
		add(searchBut);
		
		barcodeScannerPanel = new RoundedPanel(60);
		barcodeScannerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		barcodeScannerPanel.addMouseListener(this);
		barcodeScannerPanel.setBounds(34,385, 214,233);
		barcodeScannerPanel.setBackground(new Color(216,216,216));
		add(barcodeScannerPanel);
		barcodeScannerPanel.setLayout(null);
		
		JLabel scanLabel = new JLabel("Scan To Borrow");
		scanLabel.setBounds(28, 170, 170, 50);
		inter_bold.applyFont(scanLabel, 20F, Color.black);
		barcodeScannerPanel.add(scanLabel);
		
		JLabel ScanLogo = new JLabel("");
		ScanLogo.setIcon(new ImageIcon(BorrowedBooksPanel.class.getResource("/icons/qr-code-scan.png")));
		ScanLogo.setBounds(33, 24, 148, 148);
		barcodeScannerPanel.add(ScanLogo);
		
		
		
		studentDetailsPanel = new RoundedPanel(40);
		studentDetailsPanel.setBounds(258, 385, 655, 270);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel lblBorrowNo = new JLabel("Borrow No.:");
		inter_bold.applyFont(lblBorrowNo, 15F, Color.black);
		lblBorrowNo.setBounds(500, 15, 108, 27);
		studentDetailsPanel.add(lblBorrowNo);
		
		JLabel BorrowNumberNoValue = new JLabel("1");
		BorrowNumberNoValue.setBounds(596, 15, 31, 27);
		inter_regular.applyFont(BorrowNumberNoValue, 15f, Color.black);
		studentDetailsPanel.add(BorrowNumberNoValue);
		
		JLabel bookAuthor = new JLabel("Book Author");
		bookAuthor.setBounds(24, 147, 183,27);
		inter_bold.applyFont(bookAuthor, 13F, Color.black);
		studentDetailsPanel.add(bookAuthor);
		
		JLabel category = new JLabel("Category:");
		category.setBounds(24, 205, 138, 20);
		inter_bold.applyFont(category, 13F, Color.black);
		studentDetailsPanel.add(category);
		
		JLabel StudentDetails = new JLabel("Borrow Details: ");
		StudentDetails.setBounds(24, 15, 186, 27);
		inter_bold.applyFont(StudentDetails, 18F, Color.black);
		studentDetailsPanel.add(StudentDetails);
		
		
		JLabel borrowersInfo = new JLabel("<html> <u>Book Information:</u> </html>");
		inter_regular.applyFont(borrowersInfo, 13F, Color.black);
		borrowersInfo.setBounds(24,45, 200,40);
		studentDetailsPanel.add(borrowersInfo);
		
		JLabel bookTitle = new JLabel("Book Title:");
		bookTitle.setBounds(24, 76, 183, 28);
		inter_bold.applyFont(bookTitle, 13F, Color.black);
		studentDetailsPanel.add(bookTitle);
		
		JTextArea bookTitleValue = new JTextArea("Web Development I: An introduction to Web Computing 1st Edition");
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBounds(24, 100, 213, 54);
		bookTitleValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(bookTitleValue, 13F, Color.black);
		studentDetailsPanel.add(bookTitleValue);
		
		JTextArea bookAuthorValue = new JTextArea("Jaymark Cabildo");
		bookAuthorValue.setWrapStyleWord(true);
		bookAuthorValue.setLineWrap(true);
		bookAuthorValue.setFocusable(false);
		bookAuthorValue.setEditable(false);
		bookAuthorValue.setBounds(24, 167, 213, 50);
		bookAuthorValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookAuthorValue, 13F, Color.black);
		studentDetailsPanel.add( bookAuthorValue);
		
		JTextArea bookCategoryValue = new JTextArea("Local");
		bookCategoryValue.setWrapStyleWord(true);
		bookCategoryValue.setLineWrap(true);
		bookCategoryValue.setFocusable(false);
		bookCategoryValue.setEditable(false);
		bookCategoryValue.setBounds(24, 223, 128, 28);
		bookCategoryValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookCategoryValue, 13F, Color.black);
		studentDetailsPanel.add( bookCategoryValue);
		
		JLabel borrowInfo = new JLabel("<html> <u>Borrower's Information:</u></html>");
		borrowInfo.setBounds(254, 45, 200, 40);
		inter_regular.applyFont(borrowInfo, 13F, Color.black);
		studentDetailsPanel.add(borrowInfo);
		
		JLabel studentName = new JLabel("Student Name:");
		studentName.setBounds(254, 83, 160, 14);
		inter_bold.applyFont(studentName, 13F, Color.black);
		studentDetailsPanel.add(studentName);
		
		JTextArea nameValue = new JTextArea("Boks");
		nameValue.setWrapStyleWord(true);
		nameValue.setLineWrap(true);
		nameValue.setFocusable(false);
		nameValue.setEditable(false);
		nameValue.setBounds(254, 100, 200, 28);
		nameValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont( nameValue, 13F, Color.black);
		studentDetailsPanel.add( nameValue);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(254, 143, 70, 14);
		inter_bold.applyFont(gender, 13F, Color.black);
		studentDetailsPanel.add(gender);
		
		JTextArea genderValue = new JTextArea("Male");
		 genderValue .setWrapStyleWord(true);
		 genderValue .setLineWrap(true);
		 genderValue .setFocusable(false);
		 genderValue .setEditable(false);
		 genderValue .setBounds(254, 158, 172, 28);
		 genderValue .setBackground(new Color(216,216,216));
		inter_regular.applyFont( genderValue , 13F, Color.black);
		studentDetailsPanel.add(  genderValue );
		
		JLabel department = new JLabel("Department:");
		department.setBounds(254, 208, 90, 14);
		inter_bold.applyFont(department, 13F, Color.black);
		studentDetailsPanel.add(department);
		
		JTextArea deparText = new JTextArea("CITCS");
		deparText .setWrapStyleWord(true);
		deparText .setLineWrap(true);
		deparText .setFocusable(false);
		deparText .setEditable(false);
		deparText .setBounds(254, 223, 128, 28);
		deparText .setBackground(new Color(216,216,216));
		inter_regular.applyFont( deparText , 13F, Color.black);
		studentDetailsPanel.add(  deparText );
		
		JLabel borrowDate = new JLabel("Borrowed Date:");
		borrowDate.setBounds(464, 83, 110, 14);
		inter_bold.applyFont(borrowDate, 13F, Color.black);
		studentDetailsPanel.add(borrowDate);
		
		
		JTextArea borroweredDateValue = new JTextArea("2024-11-11 13:23:44");
		borroweredDateValue.setWrapStyleWord(true);
		borroweredDateValue.setLineWrap(true);
		borroweredDateValue.setFocusable(false);
		borroweredDateValue.setEditable(false);
		borroweredDateValue.setBounds(464, 100, 138, 28);
		borroweredDateValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(borroweredDateValue , 13F, Color.black);
		studentDetailsPanel.add(borroweredDateValue);
		
		JLabel dueDate = new JLabel("Due Date:");
		 dueDate.setBounds(464, 145, 110, 14);
		inter_bold.applyFont( dueDate, 13F, Color.black);
		studentDetailsPanel.add( dueDate);
		
		JTextArea dueDateValue = new JTextArea("2024-11-11 13:23:44");
		dueDateValue.setWrapStyleWord(true);
		dueDateValue.setLineWrap(true);
		dueDateValue.setFocusable(false);
		dueDateValue.setEditable(false);
		dueDateValue.setBounds(464, 160, 138, 26);
		dueDateValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(dueDateValue , 13F, Color.black);
		studentDetailsPanel.add(dueDateValue);
		
		JLabel status = new JLabel("Status:");
		status.setBounds(464, 205, 90, 14);
		inter_bold.applyFont(status, 13F, Color.black);
		studentDetailsPanel.add(status);
		
		JTextArea statusValue = new JTextArea("Pending for return");
		statusValue.setWrapStyleWord(true);
		statusValue.setLineWrap(true);
		statusValue.setFocusable(false);
		statusValue.setEditable(false);
		statusValue.setBounds(464, 219, 138, 26);
		statusValue.setBackground(new Color(216,216,216));
		inter_regular.applyFont(statusValue , 13F, Color.black);
		studentDetailsPanel.add(statusValue);

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
    
    // Button Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == barcodeScannerPanel) {
			BorrowBookScanner borrowScanner = new BorrowBookScanner();
			borrowScanner.setVisible(true);			
			borrowScanner.setLocationRelativeTo(null);
			borrowScanner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
