package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OverdueBooksPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	
	private JTable overdueBooksTable;
	private DefaultTableModel model = new DefaultTableModel();

	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the panel.
	 */
	public OverdueBooksPanel() {
		connect.Connect();
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
		
		JLabel adminName = new JLabel("Librarian Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(690, 45, 159, 25);
		add(adminName);
		
		RoundedTextField searchTxt = new RoundedTextField(15);
		searchTxt.setPlaceholder("Search...");
		searchTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		inter_regular.applyFont(searchTxt, 14f, Color.BLACK);
		searchTxt.setBackground(new Color(220, 220, 220));
		searchTxt.setBounds(40, 117, 159, 32);
		add(searchTxt);
		
		RoundedButton searchBut = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchTxt.getText();
				searchOverdueBook(search);
			}
		});
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
		lblBorrowNo.setBounds(763, 13, 108, 27);
		borDetail.add(lblBorrowNo);
		
		JLabel borrowIdTxt = new JLabel("1");
		borrowIdTxt.setBounds(853, 13, 31, 27);
		inter_regular.applyFont(borrowIdTxt, 15f, Color.black);
		borDetail.add(borrowIdTxt);
		
		JLabel lblbookAuthor = new JLabel("Book Author");
		lblbookAuthor.setBounds(47, 147, 183,27);
		inter_bold.applyFont(lblbookAuthor, 13F, Color.black);
		borDetail.add(lblbookAuthor);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(47, 215, 138, 20);
		inter_bold.applyFont(lblCategory, 13F, Color.black);
		borDetail.add(lblCategory);
		
		
		JLabel borrowersInfo = new JLabel("<html> <u>Book Information:</u> </html>");
		inter_regular.applyFont(borrowersInfo, 13F, Color.black);
		borrowersInfo.setBounds(47,45, 200,40);
		borDetail.add(borrowersInfo);
		
		JLabel lblbookTitle = new JLabel("Book Title:");
		lblbookTitle.setBounds(47, 76, 183, 28);
		inter_bold.applyFont(lblbookTitle, 13F, Color.black);
		borDetail.add(lblbookTitle);
		
		JTextArea bookTitleTxt = new JTextArea("");
		bookTitleTxt.setWrapStyleWord(true);
		bookTitleTxt.setLineWrap(true);
		bookTitleTxt.setFocusable(false);
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(47, 100, 200, 54);
		bookTitleTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(bookTitleTxt, 13F, Color.black);
		borDetail.add(bookTitleTxt);
		
		JTextArea bookAuthorTxt = new JTextArea("");
		bookAuthorTxt.setWrapStyleWord(true);
		bookAuthorTxt.setLineWrap(true);
		bookAuthorTxt.setFocusable(false);
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setBounds(47, 167, 200, 50);
		bookAuthorTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookAuthorTxt, 13F, Color.black);
		borDetail.add( bookAuthorTxt);
		
		JTextArea bookCategoryTxt = new JTextArea("");
		bookCategoryTxt.setWrapStyleWord(true);
		bookCategoryTxt.setLineWrap(true);
		bookCategoryTxt.setFocusable(false);
		bookCategoryTxt.setEditable(false);
		bookCategoryTxt.setBounds(48, 232, 128, 28);
		bookCategoryTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont( bookCategoryTxt, 13F, Color.black);
		borDetail.add( bookCategoryTxt);
		
		JLabel borrowInfo = new JLabel("<html> <u>Borrower's Information:</u></html>");
		borrowInfo.setBounds(257, 45, 200, 40);
		inter_regular.applyFont(borrowInfo, 13F, Color.black);
		borDetail.add(borrowInfo);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(257, 83, 192, 14);
		inter_bold.applyFont(lblStudentNumber, 13F, Color.black);
		borDetail.add(lblStudentNumber);
		
		JTextArea studentNumberTxt = new JTextArea("");
		studentNumberTxt.setWrapStyleWord(true);
		studentNumberTxt.setLineWrap(true);
		studentNumberTxt.setFocusable(false);
		studentNumberTxt.setEditable(false);
		studentNumberTxt.setBackground(new Color(216, 216, 216));
		studentNumberTxt.setBounds(257, 100, 172, 28);
		inter_regular.applyFont(studentNumberTxt, 13F, Color.black);
		borDetail.add(studentNumberTxt);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		lblStudentName.setBounds(257, 128, 160, 14);
		inter_bold.applyFont(lblStudentName, 13F, Color.black);
		borDetail.add(lblStudentName);
		
		JTextArea nameTxt = new JTextArea("");
		nameTxt.setWrapStyleWord(true);
		nameTxt.setLineWrap(true);
		nameTxt.setFocusable(false);
		nameTxt.setEditable(false);
		nameTxt.setBounds(257, 148, 190, 40);
		nameTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(nameTxt, 13F, Color.black);
		borDetail.add( nameTxt);
		
		JLabel lblgender = new JLabel("Gender:");
		lblgender.setBounds(257, 190, 70, 14);
		inter_bold.applyFont(lblgender, 13F, Color.black);
		borDetail.add(lblgender);
		
		JTextArea genderTxt = new JTextArea("");
		 genderTxt .setWrapStyleWord(true);
		 genderTxt .setLineWrap(true);
		 genderTxt .setFocusable(false);
		 genderTxt .setEditable(false);
		 genderTxt .setBounds(257, 207, 172, 28);
		 genderTxt .setBackground(new Color(216,216,216));
		inter_regular.applyFont( genderTxt , 13F, Color.black);
		borDetail.add(  genderTxt );
		
		JLabel lbldepartment = new JLabel("Department:");
		lbldepartment.setBounds(257, 237, 90, 14);
		inter_bold.applyFont(lbldepartment, 13F, Color.black);
		borDetail.add(lbldepartment);
		
		JTextArea departmentTxt = new JTextArea("");
		departmentTxt .setWrapStyleWord(true);
		departmentTxt .setLineWrap(true);
		departmentTxt .setFocusable(false);
		departmentTxt .setEditable(false);
		departmentTxt .setBounds(257, 251, 128, 28);
		departmentTxt .setBackground(new Color(216,216,216));
		inter_regular.applyFont( departmentTxt , 13F, Color.black);
		borDetail.add(  departmentTxt );
		
		JLabel lblborrowDate = new JLabel("Borrowed Date:");
		lblborrowDate.setBounds(464, 83, 110, 14);
		inter_bold.applyFont(lblborrowDate, 13F, Color.black);
		borDetail.add(lblborrowDate);
		
		
		JTextArea borrowedDateTxt = new JTextArea("");
		borrowedDateTxt.setWrapStyleWord(true);
		borrowedDateTxt.setLineWrap(true);
		borrowedDateTxt.setFocusable(false);
		borrowedDateTxt.setEditable(false);
		borrowedDateTxt.setBounds(464, 100, 138, 28);
		borrowedDateTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(borrowedDateTxt , 13F, Color.black);
		borDetail.add(borrowedDateTxt);
		
		JLabel lbldueDate = new JLabel("Due Date:");
		 lbldueDate.setBounds(464, 152, 110, 14);
		inter_bold.applyFont( lbldueDate, 13F, Color.black);
		borDetail.add( lbldueDate);
		
		JTextArea dueDateTxt = new JTextArea("");
		dueDateTxt.setWrapStyleWord(true);
		dueDateTxt.setLineWrap(true);
		dueDateTxt.setFocusable(false);
		dueDateTxt.setEditable(false);
		dueDateTxt.setBounds(464, 166, 138, 26);
		dueDateTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(dueDateTxt , 13F, Color.black);
		borDetail.add(dueDateTxt);
		
		JLabel lblstatus = new JLabel("Status:");
		lblstatus.setBounds(464, 216, 90, 14);
		inter_bold.applyFont(lblstatus, 13F, Color.black);
		borDetail.add(lblstatus);
		
		JTextArea statusTxt = new JTextArea("");
		statusTxt.setWrapStyleWord(true);
		statusTxt.setLineWrap(true);
		statusTxt.setFocusable(false);
		statusTxt.setEditable(false);
		statusTxt.setBounds(464, 231, 138, 26);
		statusTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(statusTxt , 13F, Color.black);
		borDetail.add(statusTxt);
		
		JLabel lbloverDue = new JLabel("No. of Overdue");
		inter_bold.applyFont(lbloverDue, 20F, Color.black);
		lbloverDue.setBounds(650,120,180,30);
		borDetail.add(lbloverDue);
		
		JLabel lblDays = new JLabel("Days:");
		inter_bold.applyFont(lblDays, 20F, Color.black);
		lblDays.setBounds(650,150,100,30);
		borDetail.add(lblDays);
		
		JTextArea overdueDaysTxt = new JTextArea("");
		overdueDaysTxt.setWrapStyleWord(true);
		overdueDaysTxt.setLineWrap(true);
		overdueDaysTxt.setFocusable(false);
		overdueDaysTxt.setEditable(false);
		overdueDaysTxt.setBounds(713,153,100,30);
		overdueDaysTxt.setBackground(new Color(216,216,216));
		inter_regular.applyFont(overdueDaysTxt, 20F, Color.black);
		borDetail.add(overdueDaysTxt);
		
		// Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 160, 900, 199);
		add(scrollPane);
		
		overdueBooksTable = new JTable();
		overdueBooksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Selecting Data
				int row = overdueBooksTable.getSelectedRow();				
				int borrowID = Integer.parseInt(model.getValueAt(row, 0).toString());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
					
					// Calculate overdue days if today's date is past due date
			        LocalDate dueDate = LocalDate.parse(rs.getString("borrow_dueDate"), formatter);
			        LocalDate today = LocalDate.now();
			        if (today.isAfter(dueDate)) {
			            long overdueDays = ChronoUnit.DAYS.between(dueDate, today);
			            overdueDaysTxt.setText(String.valueOf(overdueDays));
			            System.out.println("Overdue by " + overdueDays + " days.");
			        } else {
			            System.out.println("Not overdue.");
			        }
					rs.close();
					prep_stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(overdueBooksTable);
		
		Object[] column = {"Borrow ID", "Book Title", "Category", "Borrower's Name", "Borrowed Date", "Due Date", "Status"};
		model.setColumnIdentifiers(column);
		
		overdueBooksTable.setModel(model);
		overdueBooksTable.getTableHeader().setReorderingAllowed(false);
		overdueBooksTable.getTableHeader().setResizingAllowed(false);
		overdueBooksTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			overdueBooksTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = overdueBooksTable.getTableHeader();
		inter_bold.applyFont(header, 13f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		overdueBooksTable.setRowHeight(30);
		overdueBooksTable.setFocusable(true);
		overdueBooksTable.setTableHeader(header);
		
		fetchOverdueBooks();
	}
	
	// displays all data
	public void fetchOverdueBooks() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
					+ "INNER JOIN students ON borrows.student_id = students.student_id "
					+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					+ "WHERE DATE(borrow_dueDate) < CURDATE() AND borrow_status = 'Pending for Return' "
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
			e.printStackTrace();
		}
	}
	
	// Search Data
		public void searchOverdueBook(String searchTxt) {
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
					fetchOverdueBooks();
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
