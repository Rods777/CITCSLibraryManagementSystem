package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedPanel;
import inheritances.RoundedTextField;
import javax.swing.JTextArea;

public class ReturnedBooksPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField SearchBar;
	private RoundedPanel ReturnDetailsPanel;
	private RoundedPanel ScanPanel;
	/**
	 * Create the panel.
	 */
	public ReturnedBooksPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 676)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel returnedBooks = new JLabel("Returned Books");
		returnedBooks.setBounds(34, 32, 341, 50);
		inter_black.applyFont(returnedBooks, 36f, CommonConstants.HEADER_COLOR);
		add(returnedBooks);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);

		SearchBar = new RoundedTextField(15);
		SearchBar.setPlaceholder("Search...");
		inter_regular.applyFont(SearchBar, 14f, Color.BLACK);
		SearchBar.setBounds(40, 117, 159, 32);
		SearchBar.setBackground(Color.decode("#D9D9D9"));
		SearchBar.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(SearchBar);
		SearchBar.setColumns(10);
		
		RoundedButton SearchButton = new RoundedButton("", 10, Color.decode("#569FF0"));
		SearchButton.setIcon(new ImageIcon(StudentsLogPanel.class.getResource("/icons/search-iconpng.png")));
		SearchButton.setBounds(200, 117, 38, 32);
		add(SearchButton);
		
		//Scan Panel and Logo
		
		ScanPanel = new RoundedPanel(44);
		ScanPanel.setBounds(34, 391, 214, 233);
		ScanPanel.setBackground(new Color(216,216,216));
		add(ScanPanel);
		ScanPanel.setLayout(null);
		
		JLabel ScanLogo = new JLabel("");
		ScanLogo.setIcon(new ImageIcon(ReturnedBooksPanel.class.getResource("/icons/qr-code-scan.png")));
		ScanLogo.setBounds(33, 30, 148, 148);
		ScanPanel.add(ScanLogo);
		
		JLabel scanToBorrow = new JLabel("Scan to Return");
		scanToBorrow.setBounds(34, 185, 157, 37);
		inter_bold.applyFont(scanToBorrow, 20F, Color.black);
		ScanPanel.add(scanToBorrow);
		
		
		//Return Details Panel
		
		ReturnDetailsPanel = new RoundedPanel(40);
		ReturnDetailsPanel.setBounds(258, 391, 662, 257);
		ReturnDetailsPanel.setBackground(new Color(216,216,216));
		add(ReturnDetailsPanel);
		ReturnDetailsPanel.setLayout(null);
		
		JLabel ReturnDetails = new JLabel("Return Details: ");
		ReturnDetails.setBounds(34, 14, 186, 27);
		inter_bold.applyFont(ReturnDetails, 20F, Color.black);
		ReturnDetailsPanel.add(ReturnDetails);
		
		JLabel ReturnNumber = new JLabel("Return No.:");
		ReturnNumber.setBounds(514, 14, 108, 27);
		inter_bold.applyFont(ReturnNumber, 15F, Color.black);
		ReturnDetailsPanel.add(ReturnNumber);
		
		JLabel ReturnNumberNoValue = new JLabel("1");
		ReturnNumberNoValue.setBounds(610, 14, 31, 27);
		inter_regular.applyFont(ReturnNumberNoValue, 15F, Color.black);
		ReturnDetailsPanel.add(ReturnNumberNoValue);
		
		//Return Details Panel Contents
		
		JLabel BookInformation = new JLabel("<html><u>Book Information:</u></html>");
		BookInformation.setBounds(34, 52, 186, 27);
		inter_regular.applyFont(BookInformation, 13F, Color.black);
		ReturnDetailsPanel.add(BookInformation);
		
		JLabel ReturnerInformation = new JLabel("<html><u>Returner's Information:</u></html>");
		ReturnerInformation.setBounds(251, 52, 186, 27);
		inter_regular.applyFont(ReturnerInformation, 13F, Color.black);
		ReturnDetailsPanel.add(ReturnerInformation);
		
		// Book Info and Returner's Info Details
		
		JLabel BookTitle = new JLabel("Book Title:");
		BookTitle.setBounds(34, 83, 100, 14);
		inter_bold.applyFont(BookTitle, 13F, Color.black);
		ReturnDetailsPanel.add(BookTitle);
		
		JLabel BookAuthor = new JLabel("Book Author:");
		BookAuthor.setBounds(34, 146, 100, 14);
		inter_bold.applyFont(BookAuthor, 13F, Color.black);
		ReturnDetailsPanel.add(BookAuthor);
		
		JLabel BookCategory = new JLabel("Category:");
		BookCategory.setBounds(34, 199, 100, 14);
		inter_bold.applyFont(BookCategory, 13F, Color.black);
		ReturnDetailsPanel.add(BookCategory);
		
		JLabel StudentName = new JLabel("Student Name:");
		StudentName.setBounds(251, 83, 120, 14);
		inter_bold.applyFont(StudentName, 13F, Color.black);
		ReturnDetailsPanel.add(StudentName);
		
		JLabel StudentGender = new JLabel("Gender:");
		StudentGender.setBounds(251, 144, 100, 14);
		inter_bold.applyFont(StudentGender, 13F, Color.black);
		ReturnDetailsPanel.add(StudentGender);
		
		JLabel StudentDepartment = new JLabel("Department:");
		StudentDepartment.setBounds(251, 199, 120, 14);
		inter_bold.applyFont(StudentDepartment, 13F, Color.black);
		ReturnDetailsPanel.add(StudentDepartment);
		
		JLabel BorrowedDate = new JLabel("Borrowed Date:");
		BorrowedDate.setBounds(472, 83, 120, 14);
		inter_bold.applyFont(BorrowedDate, 13F, Color.black);
		ReturnDetailsPanel.add(BorrowedDate);
		
		JLabel ReturnedDate = new JLabel("Returned Date:");
		ReturnedDate.setBounds(472, 154, 120, 14);
		inter_bold.applyFont(ReturnedDate, 13F, Color.black);
		ReturnDetailsPanel.add(ReturnedDate);
		
		//Sample Values
		
		JTextArea BookTitleValue = new JTextArea("All Quiet on the Western Front");
		BookTitleValue.setWrapStyleWord(true);
		BookTitleValue.setLineWrap(true);
		BookTitleValue.setFocusable(false);
		BookTitleValue.setEditable(false);
		BookTitleValue.setBounds(34, 97, 199, 48);
		inter_regular.applyFont(BookTitleValue, 13F, Color.black);
		BookTitleValue.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(BookTitleValue);
		
		JTextArea BookAuthorValue = new JTextArea("Erich Maria Remarque");
		BookAuthorValue.setWrapStyleWord(true);
		BookAuthorValue.setLineWrap(true);
		BookAuthorValue.setFocusable(false);
		BookAuthorValue.setEditable(false);
		BookAuthorValue.setBounds(34, 161, 199, 40);
		inter_regular.applyFont(BookAuthorValue, 13F, Color.black);
		BookAuthorValue.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(BookAuthorValue);
		
		JTextArea StudentNameNoValue = new JTextArea("Adolf Schwarzenegger Jr.");
		StudentNameNoValue.setWrapStyleWord(true);
		StudentNameNoValue.setLineWrap(true);
		StudentNameNoValue.setFocusable(false);
		StudentNameNoValue.setEditable(false);
		StudentNameNoValue.setBounds(251, 97, 199, 46);
		inter_regular.applyFont(StudentNameNoValue, 13F, Color.black);
		StudentNameNoValue.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(StudentNameNoValue);
		
		JLabel CategoryValue = new JLabel("Foreign");
		CategoryValue.setBounds(34, 214, 60, 20);
		inter_regular.applyFont(CategoryValue, 13F, Color.black);
		ReturnDetailsPanel.add(CategoryValue);
		
		JLabel GenderNoValue = new JLabel("Male");
		GenderNoValue.setBounds(251, 159, 60, 20);
		inter_regular.applyFont(GenderNoValue, 13F, Color.black);
		ReturnDetailsPanel.add(GenderNoValue);
		
		JLabel DepartmentNoValue = new JLabel("CITCS");
		DepartmentNoValue.setBounds(251, 214, 60, 20);
		inter_regular.applyFont(DepartmentNoValue, 13F, Color.black);
		ReturnDetailsPanel.add(DepartmentNoValue);
		
		JLabel BorrowedDateNoValue = new JLabel("2024-11-11 13:23:44");
		BorrowedDateNoValue.setBounds(472, 97, 150, 20);
		inter_regular.applyFont(BorrowedDateNoValue, 13F, Color.black);
		ReturnDetailsPanel.add(BorrowedDateNoValue);
		
		JLabel ReturnedDateNoValue = new JLabel("2024-11-11 13:23:44");
		ReturnedDateNoValue.setBounds(472, 169, 150, 20);
		inter_regular.applyFont(ReturnedDateNoValue, 13F, Color.black);
		ReturnDetailsPanel.add(ReturnedDateNoValue);
			
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
