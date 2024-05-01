package guis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constants.CommonConstants;
import db.DBConnection;

import javax.swing.JLabel;

import inheritances.FontLoader;
import inheritances.RoundedPanel;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_semibold = new FontLoader("/fonts/Inter-SemiBold.ttf");
	private FontLoader inter_medium = new FontLoader("/fonts/Inter-Medium.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	
	private JLabel totalBooksValue, availableBooksValue, noOfStudentsValue, borrowedBooksValue, returnedBooksValue;
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;
	private JTable recentlyBorrowedTable;
	private JTable recentlyReturnedTable;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1 = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public DashboardPanel() {
		connect.Connect();
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
		
		totalBooksValue = new JLabel("0");
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
		
		availableBooksValue = new JLabel("0");
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
		
		noOfStudentsValue = new JLabel("0");
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
		
		borrowedBooksValue = new JLabel("0");
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
		
		returnedBooksValue = new JLabel("0");
		inter_bold.applyFont(returnedBooksValue, 60f, Color.WHITE);
		returnedBooksValue.setBounds(87, 39, 70, 65);
		returnedBooksPanel.add(returnedBooksValue);
		
		JLabel recentlyBorrowed = new JLabel("Recently Borrowered");
		inter_bold.applyFont(recentlyBorrowed, 20f, Color.BLACK);
		recentlyBorrowed.setBounds(34, 285, 223, 25);
		add(recentlyBorrowed);
		
		JLabel recentlyReturned = new JLabel("Recently Returned");
		inter_bold.applyFont(recentlyReturned, 20f, Color.BLACK);
		recentlyReturned.setBounds(34, 475, 223, 25);
		add(recentlyReturned);
		
		// Recently Borrowed Table
		Object[] recentlyBorrowedColumn = {"Book Name", "Borrower's Name", "Borrowed Date"};
		model.setColumnIdentifiers(recentlyBorrowedColumn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 321, 863, 130);
		add(scrollPane);
		
		recentlyBorrowedTable = new JTable();
		recentlyBorrowedTable.setEnabled(false);
		scrollPane.setViewportView(recentlyBorrowedTable);
		inter_medium.applyFont(recentlyBorrowedTable, 14f, Color.BLACK);
		recentlyBorrowedTable.setModel(model);
		recentlyBorrowedTable.getTableHeader().setReorderingAllowed(false);
		recentlyBorrowedTable.getTableHeader().setResizingAllowed(false);
		recentlyBorrowedTable.setDefaultEditor(Object.class, null);
		
		// Table header
		JTableHeader borrowedHeader = recentlyBorrowedTable.getTableHeader();
		recentlyBorrowedTable.setRowHeight(35);
		recentlyBorrowedTable.setFocusable(true);
		recentlyBorrowedTable.setTableHeader(borrowedHeader);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < recentlyBorrowedColumn.length; i++) {
			recentlyBorrowedTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		inter_bold.applyFont(borrowedHeader, 14f, Color.WHITE);
		borrowedHeader.setBackground(Color.decode("#35782D"));
		borrowedHeader.setForeground(Color.WHITE);
		
		// Recently Returned Table
		Object[] recentlyReturnedColumn = {"Book Name", "Returner's Name", "Returned Date"};
		
		model1.setColumnIdentifiers(recentlyReturnedColumn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(34, 511, 865, 130);
		add(scrollPane_1);
		recentlyReturnedTable = new JTable();
		recentlyReturnedTable.setEnabled(false);
		scrollPane_1.setViewportView(recentlyReturnedTable);
		inter_medium.applyFont(recentlyReturnedTable, 14f, Color.BLACK);
		recentlyReturnedTable.setModel(model1);
		recentlyReturnedTable.getTableHeader().setReorderingAllowed(false);
		recentlyReturnedTable.getTableHeader().setResizingAllowed(false);
		recentlyReturnedTable.setDefaultEditor(Object.class, null);
		
		// Table header
		JTableHeader returnedHeader = recentlyReturnedTable.getTableHeader();
		recentlyReturnedTable.setRowHeight(35);
		recentlyReturnedTable.setFocusable(true);
		recentlyReturnedTable.setTableHeader(returnedHeader);
		
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < recentlyReturnedColumn.length; i++) {
			recentlyReturnedTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
		}
		inter_bold.applyFont(returnedHeader, 14f, Color.WHITE);
		returnedHeader.setBackground(Color.decode("#35782D"));
		returnedHeader.setForeground(Color.WHITE);
		
		fetchTotalBooks();
		fetchAvailableBooks();
		fetchTotalStudents();
		fetchTotalBorrowedBooks();
		fetchTotalReturnedBooks();
		recentlyBorrowedBooks();
		recentlyReturnedBooks();
	}
	
	// Fetch borrowed book
	public void recentlyBorrowedBooks() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
					+ "INNER JOIN students ON borrows.student_id = students.student_id\r\n"
					+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					+ "ORDER BY borrow_id DESC LIMIT 3");
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				String book_title = rs.getString("book_title");
				String student_name = rs.getString("student_name");
				String borrow_date = rs.getString("borrow_date");
				
				model.addRow(new Object[] {book_title, student_name, borrow_date});
			}
				
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Fetch returned books
	public void recentlyReturnedBooks() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM returns "
					   + "INNER JOIN borrows ON returns.borrow_id = borrows.borrow_id "
					   + "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					   + "INNER JOIN students ON borrows.student_id = students.student_id "
					   + "ORDER BY returns.return_id DESC LIMIT 3");
			rs = prep_stmt.executeQuery();
			model1.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				String book_title = rs.getString("book_title");
				String student_name = rs.getString("student_name");
				String return_date = rs.getString("return_date");
				
				model1.addRow(new Object[] {book_title, student_name, return_date});
			}
			
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fetchTotalBooks() {
		try {
			int count;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as booksCount FROM books");
			rs = prep_stmt.executeQuery();
			totalBooksValue.setText("0");
			if(rs.next()) {
				count = rs.getInt("booksCount");
				totalBooksValue.setText(String.valueOf(count));
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchAvailableBooks() {
		try {
			int count;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as booksCount FROM books WHERE book_status = 'Available'");
			rs = prep_stmt.executeQuery();
			availableBooksValue.setText("0");
			if(rs.next()) {
				count = rs.getInt("booksCount");
				availableBooksValue.setText(String.valueOf(count));
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchTotalStudents() {
		try {
			int count;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as studentsCount FROM students WHERE DATE(student_timeIn) = CURDATE()");
			rs = prep_stmt.executeQuery();
			noOfStudentsValue.setText("0");
			if(rs.next()) {
				count = rs.getInt("studentsCount");
				noOfStudentsValue.setText(String.valueOf(count));
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchTotalBorrowedBooks() {
		try {
			int count;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as borrowedCount FROM borrows WHERE borrow_status = 'Pending for Return'");
			rs = prep_stmt.executeQuery();
			borrowedBooksValue.setText("0");
			if(rs.next()) {
				count = rs.getInt("borrowedCount");
				borrowedBooksValue.setText(String.valueOf(count));
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchTotalReturnedBooks() {
		try {
			int count;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as returnedCount FROM returns WHERE return_date = CURDATE()");
			rs = prep_stmt.executeQuery();
			returnedBooksValue.setText("0");
			if(rs.next()) {
				count = rs.getInt("returnedCount");
				returnedBooksValue.setText(String.valueOf(count));
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
