package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import inheritances.RoundedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class BookListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField search;

	/**
	 * Create the panel.
	 */
	public BookListPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(973, 682)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel bookList = new JLabel("Book List");
		bookList.setBounds(34, 32, 341, 50);
		inter_black.applyFont(bookList, 36f, CommonConstants.HEADER_COLOR);
		add(bookList);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);
		
		// Book Details Panel
		RoundedPanel bookDetailsPanel = new RoundedPanel(10);
		bookDetailsPanel.setBounds(43, 394, 887, 260);
		bookDetailsPanel.setBackground(Color.decode("#D8D8D8"));
		add(bookDetailsPanel);
		bookDetailsPanel.setLayout(null);
		
		JLabel BookDetails = new JLabel("Book Details:");
		BookDetails.setBounds(35, 16, 146, 27);
		inter_bold.applyFont(BookDetails, 20f, Color.BLACK);
		bookDetailsPanel.add(BookDetails);
		
		JLabel BookNo = new JLabel("Book No.:");
		BookNo.setBounds(43, 61, 71, 15);
		inter_bold.applyFont(BookNo, 15f, Color.BLACK);
		bookDetailsPanel.add(BookNo);
		
		JLabel BookTitle = new JLabel("Book Title:");
		BookTitle.setBounds(43, 144, 95, 15);
		inter_bold.applyFont(BookTitle, 15f, Color.BLACK);
		bookDetailsPanel.add(BookTitle);
		
		JLabel BookAuthor = new JLabel("Book Author:");
		BookAuthor.setBounds(222, 61, 95, 15);
		inter_bold.applyFont(BookAuthor, 15f, Color.BLACK);
		bookDetailsPanel.add(BookAuthor);
		
		JLabel Actions = new JLabel("Actions:");
		Actions.setBounds(636, 21, 146, 27);
		inter_bold.applyFont(Actions, 20f, Color.BLACK);
		bookDetailsPanel.add(Actions);
		
		JLabel Category = new JLabel("Category:");
		Category.setBounds(222, 142, 95, 18);
		inter_bold.applyFont(Category, 15f, Color.BLACK);
		bookDetailsPanel.add(Category);
		
		JLabel Status = new JLabel("Status:");
		Status.setBounds(434, 61, 95, 15);
		inter_bold.applyFont(Status, 15f, Color.BLACK);
		bookDetailsPanel.add(Status);
		
		JLabel BarCode = new JLabel("Bar Code:");
		BarCode.setBounds(434, 144, 95, 15);
		inter_bold.applyFont(BarCode, 15f, Color.BLACK);
		bookDetailsPanel.add(BarCode);
		
		// Values
		JLabel bookNoValue = new JLabel("1");
		bookNoValue.setBounds(43, 87, 84, 14);
		inter_regular.applyFont(bookNoValue, 15f, Color.BLACK);
		bookDetailsPanel.add(bookNoValue);
		
		JLabel bookCategoryValue = new JLabel("Foreign");
		inter_regular.applyFont(bookCategoryValue, 15f, Color.BLACK);
		bookCategoryValue.setBounds(222, 170, 190, 18);
		bookDetailsPanel.add(bookCategoryValue);
		
		JLabel bookStatusValue = new JLabel("Available");
		inter_regular.applyFont(bookStatusValue, 15f, Color.BLACK);
		bookStatusValue.setBounds(434, 87, 214, 18);
		bookDetailsPanel.add(bookStatusValue);
		
		JLabel bookBarcodeValue = new JLabel("PLMUN000000123");
		inter_regular.applyFont(bookBarcodeValue, 15f, Color.BLACK);
		bookBarcodeValue.setBounds(434, 172, 196, 14);
		bookDetailsPanel.add(bookBarcodeValue);
		
		JTextArea bookTitleValue = new JTextArea();
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBackground(Color.decode("#D8D8D8"));
		bookTitleValue.setBounds(43, 170, 159, 76);
		inter_regular.applyFont(bookTitleValue, 15f, Color.BLACK);
		bookTitleValue.setText("Web Development I: An introduction to Web Computing 1st Edition");
		bookDetailsPanel.add(bookTitleValue);
		
		JTextArea bookAuthorValue = new JTextArea();
		bookAuthorValue.setText("Bok Wan");
		bookAuthorValue.setWrapStyleWord(true);
		bookAuthorValue.setLineWrap(true);
		bookAuthorValue.setFocusable(false);
		bookAuthorValue.setEditable(false);
		bookAuthorValue.setBackground(new Color(216, 216, 216));
		bookAuthorValue.setBounds(222, 82, 178, 57);
		inter_regular.applyFont(bookAuthorValue, 15f, Color.BLACK);
		bookDetailsPanel.add(bookAuthorValue);
		
		
		// Action Buttons
		RoundedButton viewMoreButton = new RoundedButton("View More", 15, Color.WHITE);
		inter_bold.applyFont(viewMoreButton, 14f, Color.BLACK);
		viewMoreButton.setBounds(705, 66, 116, 35);
		bookDetailsPanel.add(viewMoreButton);
		
		RoundedButton editBookButton = new RoundedButton("Edit Book", 15, CommonConstants.EDIT_BUTTON);
		inter_bold.applyFont(editBookButton, 14f, Color.WHITE);
		editBookButton.setBounds(705, 112, 116, 35);
		bookDetailsPanel.add(editBookButton);
		
		RoundedButton deleteBookButton = new RoundedButton("Delete Book", 15, CommonConstants.DELETE_BUTTON);
		inter_bold.applyFont(deleteBookButton, 14f, Color.WHITE);
		deleteBookButton.setBounds(704, 162, 117, 35);
		bookDetailsPanel.add(deleteBookButton);
		
		RoundedButton AddBookButton = new RoundedButton("Add Book", 13, new Color(66, 151, 55));
		inter_bold.applyFont(AddBookButton, 14f, Color.WHITE);
		AddBookButton.setBounds(824, 123, 106, 33);
		add(AddBookButton);
		
		search = new RoundedTextField(10);
		search.setPlaceholder("Search...");
		inter_regular.applyFont(search, 14f, Color.BLACK);
		search.setBackground(new Color(220, 220, 220));
		search.setBounds(43, 123, 159, 32);
		search.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(search);
		
		RoundedButton btnNewButton = new RoundedButton("", 10, Color.decode("#569FF0"));
		btnNewButton.setIcon(new ImageIcon(BookListPanel.class.getResource("/icons/search-iconpng.png")));
		btnNewButton.setBounds(204, 123, 38, 33);
		add(btnNewButton);
		
		
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
