package guis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import constants.CommonConstants;

import javax.swing.JLabel;

import inheritances.FontLoader;
import inheritances.RoundedPanel;

import javax.swing.ImageIcon;
import java.awt.Color;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_semibold = new FontLoader("/fonts/Inter-SemiBold.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");

	/**
	 * Create the panel.
	 */
	public DashboardPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 682)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel dashboard = new JLabel("Dashboard");
		dashboard.setBounds(34, 32, 341, 50);
		inter_black.applyFont(dashboard, 36f, CommonConstants.HEADER_COLOR);
		add(dashboard);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);
		
		// Summary Panels
		RoundedPanel totalBookPanel = new RoundedPanel(20);
		totalBookPanel.setBackground(Color.decode("#1E5631"));
		totalBookPanel.setBounds(36, 126, 170, 120);
		add(totalBookPanel);
		totalBookPanel.setLayout(null);
		
		JLabel totalBooks = new JLabel("Total Books");
		inter_semibold.applyFont(totalBooks, 15f, Color.WHITE);
		totalBooks.setBounds(19, 10, 94, 14);
		totalBookPanel.add(totalBooks);
		
		JLabel bookIcon = new JLabel("");
		bookIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/book-icon.png")));
		bookIcon.setBounds(10, 33, 66, 69);
		totalBookPanel.add(bookIcon);
		
		JLabel totalBooksValue = new JLabel("10");
		inter_bold.applyFont(totalBooksValue, 60f, Color.WHITE);
		totalBooksValue.setBounds(87, 39, 70, 65);
		totalBookPanel.add(totalBooksValue);
		
		RoundedPanel availableBooksPanel = new RoundedPanel(20);
		availableBooksPanel.setLayout(null);
		availableBooksPanel.setBackground(new Color(30, 86, 49));
		availableBooksPanel.setBounds(211, 126, 170, 120);
		add(availableBooksPanel);
		
		JLabel availableBooks = new JLabel("Available Books");
		inter_semibold.applyFont(availableBooks, 15f, Color.WHITE);
		availableBooks.setBounds(15, 11, 124, 14);
		availableBooksPanel.add(availableBooks);
		
		JLabel availableIcon = new JLabel("");
		availableIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/check-icon.png")));
		availableIcon.setBounds(15, 48, 53, 54);
		availableBooksPanel.add(availableIcon);
		
		JLabel availableBooksValue = new JLabel("5");
		inter_bold.applyFont(availableBooksValue, 60f, Color.WHITE);
		availableBooksValue.setBounds(87, 39, 70, 65);
		availableBooksPanel.add(availableBooksValue);
		
		RoundedPanel noOfStudentsPanel = new RoundedPanel(20);
		noOfStudentsPanel.setLayout(null);
		noOfStudentsPanel.setBackground(new Color(30, 86, 49));
		noOfStudentsPanel.setBounds(386, 126, 170, 120);
		add(noOfStudentsPanel);
		
		JLabel noOfStudents = new JLabel("No. of Students");
		inter_semibold.applyFont(noOfStudents, 15f, Color.WHITE);
		noOfStudents.setBounds(15, 11, 142, 14);
		noOfStudentsPanel.add(noOfStudents);
		
		JLabel studentsIcon = new JLabel("");
		studentsIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/reading-icon.png")));
		studentsIcon.setBounds(15, 42, 62, 62);
		noOfStudentsPanel.add(studentsIcon);
		
		JLabel noOfStudentsValue = new JLabel("7");
		inter_bold.applyFont(noOfStudentsValue, 60f, Color.WHITE);
		noOfStudentsValue.setBounds(87, 39, 70, 65);
		noOfStudentsPanel.add(noOfStudentsValue);
		
		RoundedPanel borrowedBooksPanel = new RoundedPanel(20);
		borrowedBooksPanel.setLayout(null);
		borrowedBooksPanel.setBackground(new Color(30, 86, 49));
		borrowedBooksPanel.setBounds(561, 126, 170, 120);
		add(borrowedBooksPanel);
		
		JLabel borrowedBooks = new JLabel("Borrowed  Books");
		inter_semibold.applyFont(borrowedBooks, 15f, Color.WHITE);
		borrowedBooks.setBounds(15, 11, 124, 14);
		borrowedBooksPanel.add(borrowedBooks);
		
		JLabel borrowedIcon = new JLabel("");
		borrowedIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/borrowing-icon.png")));
		borrowedIcon.setBounds(10, 36, 74, 71);
		borrowedBooksPanel.add(borrowedIcon);
		
		JLabel borrowedBooksValue = new JLabel("5");
		inter_bold.applyFont(borrowedBooksValue, 60f, Color.WHITE);
		borrowedBooksValue.setBounds(87, 39, 70, 65);
		borrowedBooksPanel.add(borrowedBooksValue);
		
		RoundedPanel returnedBooksPanel = new RoundedPanel(20);
		returnedBooksPanel.setLayout(null);
		returnedBooksPanel.setBackground(new Color(30, 86, 49));
		returnedBooksPanel.setBounds(736, 126, 170, 120);
		add(returnedBooksPanel);
		
		JLabel returnedBooks = new JLabel("Returned Books");
		inter_semibold.applyFont(returnedBooks, 15f, Color.WHITE);
		returnedBooks.setBounds(15, 11, 124, 14);
		returnedBooksPanel.add(returnedBooks);
		
		JLabel returnedIcon = new JLabel("");
		returnedIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/icons/return-icon.png")));
		returnedIcon.setBounds(15, 40, 65, 64);
		returnedBooksPanel.add(returnedIcon);
		
		JLabel returnedBooksValue = new JLabel("2");
		inter_bold.applyFont(returnedBooksValue, 60f, Color.WHITE);
		returnedBooksValue.setBounds(87, 39, 70, 65);
		returnedBooksPanel.add(returnedBooksValue);
		
		JLabel recentlyBorrowed = new JLabel("Recently Borrowered");
		inter_bold.applyFont(recentlyBorrowed, 20f, Color.BLACK);
		recentlyBorrowed.setBounds(32, 326, 223, 25);
		add(recentlyBorrowed);
		
		JLabel recentlyReturned = new JLabel("Recently Returned");
		inter_bold.applyFont(recentlyReturned, 20f, Color.BLACK);
		recentlyReturned.setBounds(493, 326, 223, 25);
		add(recentlyReturned);
		
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
