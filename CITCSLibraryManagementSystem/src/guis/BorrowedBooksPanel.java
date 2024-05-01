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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constants.CommonConstants;
import db.DBConnection;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BorrowedBooksPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField searchTxt;
	private RoundedPanel studentDetailsPanel, barcodeScannerPanel;
	
	private JLabel borrowIdTxt;
	private JTextArea bookTitleTxt, bookAuthorTxt, bookCategoryTxt, nameTxt, departmentTxt,
	genderTxt, borrowedDateTxt, dueDateTxt, statusTxt, studentNumberTxt;
	
	private JTable borrowedBooksTable;
	private DefaultTableModel model = new DefaultTableModel();

	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the panel.
	 */
	public BorrowedBooksPanel() {
		connect.Connect();
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
		
		searchTxt = new RoundedTextField(10);
		searchTxt.setPlaceholder("Search...");
		searchTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		inter_regular.applyFont(searchTxt, 14f, Color.BLACK);
		searchTxt.setBackground(new Color(220, 220, 220));
		searchTxt.setBounds(34, 117, 159, 32);
		add(searchTxt);
		//adding icon on searchbutton
		RoundedButton searchBut = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchTxt.getText();
				searchBorrowedBook(search);
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
		studentDetailsPanel.setBounds(258, 385, 655, 275);
		studentDetailsPanel.setBackground(new Color(216,216,216));
		add(studentDetailsPanel);
		studentDetailsPanel.setLayout(null);
		
		JLabel department = new JLabel("Department:");
		department.setBounds(254, 220, 90, 14);
		inter_bold.applyFont(department, 13F, Color.black);
		studentDetailsPanel.add(department);
		
		JLabel category = new JLabel("Category:");
		category.setBounds(24, 205, 138, 20);
		inter_bold.applyFont(category, 13F, Color.black);
		studentDetailsPanel.add(category);
		
		JLabel lblBorrowNo = new JLabel("Borrow No.:");
		inter_bold.applyFont(lblBorrowNo, 15F, Color.black);
		lblBorrowNo.setBounds(500, 15, 108, 27);
		studentDetailsPanel.add(lblBorrowNo);
		
		borrowIdTxt = new JLabel("");
		borrowIdTxt.setBounds(596, 15, 31, 27);
		inter_regular.applyFont(borrowIdTxt, 15f, Color.black);
		studentDetailsPanel.add(borrowIdTxt);
		
		JLabel bookAuthor = new JLabel("Book Author");
		bookAuthor.setBounds(24, 147, 183,27);
		inter_bold.applyFont(bookAuthor, 13F, Color.black);
		studentDetailsPanel.add(bookAuthor);
		
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
		
		bookTitleTxt = new JTextArea("");
		bookTitleTxt.setWrapStyleWord(true);
		bookTitleTxt.setLineWrap(true);
		bookTitleTxt.setFocusable(false);
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(24, 100, 213, 54);
		bookTitleTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(bookTitleTxt, 13F, Color.black);
		studentDetailsPanel.add(bookTitleTxt);
		
		bookAuthorTxt = new JTextArea("");
		bookAuthorTxt.setWrapStyleWord(true);
		bookAuthorTxt.setLineWrap(true);
		bookAuthorTxt.setFocusable(false);
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setBounds(24, 167, 213, 40);
		bookAuthorTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookAuthorTxt, 13F, Color.black);
		studentDetailsPanel.add( bookAuthorTxt);
		
		bookCategoryTxt = new JTextArea("");
		bookCategoryTxt.setWrapStyleWord(true);
		bookCategoryTxt.setLineWrap(true);
		bookCategoryTxt.setFocusable(false);
		bookCategoryTxt.setEditable(false);
		bookCategoryTxt.setBounds(24, 223, 128, 28);
		bookCategoryTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookCategoryTxt, 13F, Color.black);
		studentDetailsPanel.add( bookCategoryTxt);
		
		JLabel borrowInfo = new JLabel("<html> <u>Borrower's Information:</u></html>");
		borrowInfo.setBounds(254, 45, 200, 40);
		inter_regular.applyFont(borrowInfo, 13F, Color.black);
		studentDetailsPanel.add(borrowInfo);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(254, 83, 192, 14);
		inter_bold.applyFont(lblStudentNumber, 13F, Color.black);
		studentDetailsPanel.add(lblStudentNumber);
		
		studentNumberTxt = new JTextArea("");
		studentNumberTxt.setWrapStyleWord(true);
		studentNumberTxt.setLineWrap(true);
		studentNumberTxt.setFocusable(false);
		studentNumberTxt.setEditable(false);
		studentNumberTxt.setBackground(new Color(216, 216, 216));
		studentNumberTxt.setBounds(254, 100, 172, 28);
		inter_regular.applyFont(studentNumberTxt, 13F, Color.black);
		studentDetailsPanel.add(studentNumberTxt);
		
		JLabel studentName = new JLabel("Student Name:");
		studentName.setBounds(254, 126, 160, 14);
		inter_bold.applyFont(studentName, 13F, Color.black);
		studentDetailsPanel.add(studentName);
		
		nameTxt = new JTextArea("");
		nameTxt.setWrapStyleWord(true);
		nameTxt.setLineWrap(true);
		nameTxt.setFocusable(false);
		nameTxt.setEditable(false);
		nameTxt.setBounds(254, 140, 200, 28);
		nameTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( nameTxt, 13F, Color.black);
		studentDetailsPanel.add( nameTxt);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(254, 172, 70, 14);
		inter_bold.applyFont(gender, 13F, Color.black);
		studentDetailsPanel.add(gender);
		
		genderTxt = new JTextArea("");
		genderTxt.setWrapStyleWord(true);
		genderTxt.setLineWrap(true);
		genderTxt.setFocusable(false);
		genderTxt.setEditable(false);
		genderTxt.setBounds(254, 189, 172, 28);
		genderTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( genderTxt , 13F, Color.black);
		studentDetailsPanel.add(  genderTxt );
		
		departmentTxt = new JTextArea("");
		departmentTxt.setWrapStyleWord(true);
		departmentTxt.setLineWrap(true);
		departmentTxt.setFocusable(false);
		departmentTxt.setEditable(false);
		departmentTxt.setBounds(254, 236, 128, 28);
		departmentTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( departmentTxt , 13F, Color.black);
		studentDetailsPanel.add(  departmentTxt );
		
		JLabel borrowDate = new JLabel("Borrowed Date:");
		borrowDate.setBounds(464, 83, 110, 14);
		inter_bold.applyFont(borrowDate, 13F, Color.black);
		studentDetailsPanel.add(borrowDate);
		
		
		borrowedDateTxt = new JTextArea("");
		borrowedDateTxt.setWrapStyleWord(true);
		borrowedDateTxt.setLineWrap(true);
		borrowedDateTxt.setFocusable(false);
		borrowedDateTxt.setEditable(false);
		borrowedDateTxt.setBounds(464, 100, 138, 28);
		borrowedDateTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(borrowedDateTxt , 13F, Color.black);
		studentDetailsPanel.add(borrowedDateTxt);
		
		JLabel dueDate = new JLabel("Due Date:");
		 dueDate.setBounds(464, 145, 110, 14);
		inter_bold.applyFont( dueDate, 13F, Color.black);
		studentDetailsPanel.add( dueDate);
		
		dueDateTxt = new JTextArea("");
		dueDateTxt.setWrapStyleWord(true);
		dueDateTxt.setLineWrap(true);
		dueDateTxt.setFocusable(false);
		dueDateTxt.setEditable(false);
		dueDateTxt.setBounds(464, 160, 138, 26);
		dueDateTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(dueDateTxt , 13F, Color.black);
		studentDetailsPanel.add(dueDateTxt);
		
		JLabel status = new JLabel("Status:");
		status.setBounds(464, 205, 90, 14);
		inter_bold.applyFont(status, 13F, Color.black);
		studentDetailsPanel.add(status);
		
		statusTxt = new JTextArea("");
		statusTxt.setWrapStyleWord(true);
		statusTxt.setLineWrap(true);
		statusTxt.setFocusable(false);
		statusTxt.setEditable(false);
		statusTxt.setBounds(464, 219, 138, 26);
		statusTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(statusTxt , 13F, Color.black);
		studentDetailsPanel.add(statusTxt);
		
		// Tables
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 160, 879, 216);
		add(scrollPane);
		
		borrowedBooksTable = new JTable();
		borrowedBooksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Selecting Data
				int row = borrowedBooksTable.getSelectedRow();				
				int borrowID = Integer.parseInt(model.getValueAt(row, 0).toString());
				try {
					prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
							+ "INNER JOIN students ON borrows.student_id = students.student_id "
							+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
							+ "WHERE borrow_id = ?");
					prep_stmt.setInt(1, borrowID);
					rs = prep_stmt.executeQuery();
					if(rs.next()) {
						borrowIdTxt.setText(rs.getString("borrow_id"));
						bookTitleTxt.setText(rs.getString("book_title"));
						bookAuthorTxt.setText(rs.getString("book_author"));
						bookCategoryTxt.setText(rs.getString("book_category"));
						nameTxt.setText(rs.getString("student_name"));
						studentNumberTxt.setText(rs.getString("student_number"));
						departmentTxt.setText(rs.getString("student_department"));
						genderTxt.setText(rs.getString("student_gender"));
						borrowedDateTxt.setText(rs.getString("borrow_date"));
						dueDateTxt.setText(rs.getString("borrow_dueDate"));
						statusTxt.setText(rs.getString("borrow_status"));
					}
					
					rs.close();
					prep_stmt.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(borrowedBooksTable);
		
		Object[] column = {"Borrow ID", "Book Title", "Category", "Borrower's Name", "Borrowed Date", "Due Date", "Status"};
		model.setColumnIdentifiers(column);
		
		borrowedBooksTable.setModel(model);
		borrowedBooksTable.getTableHeader().setReorderingAllowed(false);
		borrowedBooksTable.getTableHeader().setResizingAllowed(false);
		borrowedBooksTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			borrowedBooksTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = borrowedBooksTable.getTableHeader();
		inter_bold.applyFont(header, 13f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		borrowedBooksTable.setRowHeight(30);
		borrowedBooksTable.setFocusable(true);
		borrowedBooksTable.setTableHeader(header);
		
		fetchBorrowedBooks();

	}
	
	// displays all data
	public void fetchBorrowedBooks() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
					+ "INNER JOIN students ON borrows.student_id = students.student_id "
					+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					+ "ORDER BY borrow_id");
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				int borrow_id = rs.getInt("borrow_id");
				String book_title = rs.getString("book_title");
				String book_category = rs.getString("book_category");
				String student_name = rs.getString("student_name");
				String borrow_dueDate = rs.getString("borrow_dueDate");
				String borrow_date = rs.getString("borrow_date");
				String borrow_status = rs.getString("borrow_status");
				
				model.addRow(new Object[] {borrow_id, book_title, book_category, student_name, borrow_date, borrow_dueDate, borrow_status});
			}
			
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Search Data
	public void searchBorrowedBook(String searchTxt) {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
	                + "INNER JOIN students ON borrows.student_id = students.student_id "
	                + "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
	                + "WHERE borrow_id LIKE ? OR books.book_title LIKE ? OR books.book_category LIKE ? "
	                + "OR students.student_name LIKE ? OR borrow_dueDate LIKE ? OR borrow_date LIKE ? "
	                + "OR borrow_status LIKE ? OR students.student_number LIKE ? ORDER BY borrow_id");
			prep_stmt.setString(1, "%" + searchTxt + "%");
			prep_stmt.setString(2, "%" + searchTxt + "%");
			prep_stmt.setString(3, "%" + searchTxt + "%");
			prep_stmt.setString(4, "%" + searchTxt + "%");
			prep_stmt.setString(5, "%" + searchTxt + "%");
			prep_stmt.setString(6, "%" + searchTxt + "%");
			prep_stmt.setString(7, "%" + searchTxt + "%");
			prep_stmt.setString(8, "%" + searchTxt + "%");
			rs = prep_stmt.executeQuery();

			model.setRowCount(0); // Resets the row
			
			// Checks for match Results
			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "No results match your search", "Error", JOptionPane.ERROR_MESSAGE);
				fetchBorrowedBooks();
			} else {
				// Iterate each row
				do {
					int borrow_id = rs.getInt("borrow_id");
					String book_title = rs.getString("book_title");
					String book_category = rs.getString("book_category");
					String student_name = rs.getString("student_name");
					String borrow_dueDate = rs.getString("borrow_dueDate");
					String borrow_date = rs.getString("borrow_date");
					String borrow_status = rs.getString("borrow_status");
					
					model.addRow(new Object[] {borrow_id, book_title, book_category, student_name, borrow_date, borrow_dueDate, borrow_status});
				} while(rs.next());
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
    
    // Button Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == barcodeScannerPanel) {
			BorrowBookScanner borrowScanner = new BorrowBookScanner();
			borrowScanner.setVisible(true);			
			borrowScanner.setLocationRelativeTo(null);
			borrowScanner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			borrowScanner.addWindowListener(new WindowAdapter() {
				@Override
	            public void windowClosed(WindowEvent e) {
	                // This method is called when the window is closed.
					fetchBorrowedBooks();
	            }
			});	
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
