package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import modals.ReturnBookScanner;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ReturnedBooksPanel extends JPanel implements MouseListener,ActionListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField searchTxt;
	private RoundedPanel ReturnDetailsPanel;
	private RoundedPanel returnScanPanel;
	private RoundedButton searchBtn;
	private JTable returnBooksTable;
	
	private DefaultTableModel model = new DefaultTableModel();
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;
	
	/**
	 * Create the panel.
	 */
	public ReturnedBooksPanel() {
		connect.Connect();
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

		searchTxt = new RoundedTextField(15);
		searchTxt.setPlaceholder("Search...");
		inter_regular.applyFont(searchTxt, 14f, Color.BLACK);
		searchTxt.setBounds(40, 117, 159, 32);
		searchTxt.setBackground(Color.decode("#D9D9D9"));
		searchTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(searchTxt);
		searchTxt.setColumns(10);
		
		searchBtn = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBtn.addActionListener(this);
		searchBtn.setIcon(new ImageIcon(StudentsLogPanel.class.getResource("/icons/search-iconpng.png")));
		searchBtn.setBounds(200, 117, 38, 32);
		add(searchBtn);
		
		//Scan Panel and Logo
		
		returnScanPanel = new RoundedPanel(44);
		returnScanPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnScanPanel.addMouseListener(this);
		returnScanPanel.setBounds(34, 391, 214, 233);
		returnScanPanel.setBackground(new Color(216,216,216));
		add(returnScanPanel);
		returnScanPanel.setLayout(null);
		
		JLabel ScanLogo = new JLabel("");
		ScanLogo.setIcon(new ImageIcon(ReturnedBooksPanel.class.getResource("/icons/qr-code-scan.png")));
		ScanLogo.setBounds(33, 30, 148, 148);
		returnScanPanel.add(ScanLogo);
		
		JLabel scanToBorrow = new JLabel("Scan to Return");
		scanToBorrow.setBounds(34, 185, 157, 37);
		inter_bold.applyFont(scanToBorrow, 20F, Color.black);
		returnScanPanel.add(scanToBorrow);
		
		
		//Return Details Panel
		
		ReturnDetailsPanel = new RoundedPanel(40);
		ReturnDetailsPanel.setBounds(258, 391, 662, 263);
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
		
		JLabel returnIdTxt = new JLabel("");
		returnIdTxt.setBounds(610, 14, 31, 27);
		inter_regular.applyFont(returnIdTxt, 15F, Color.black);
		ReturnDetailsPanel.add(returnIdTxt);
		
		//Return Details Panel Contents
		
		JLabel BookInformation = new JLabel("<html><u>Book Information:</u></html>");
		BookInformation.setBounds(34, 46, 186, 27);
		inter_regular.applyFont(BookInformation, 13F, Color.black);
		ReturnDetailsPanel.add(BookInformation);
		
		JLabel ReturnerInformation = new JLabel("<html><u>Returner's Information:</u></html>");
		ReturnerInformation.setBounds(253, 46, 186, 27);
		inter_regular.applyFont(ReturnerInformation, 13F, Color.black);
		ReturnDetailsPanel.add(ReturnerInformation);
		
		// Book Info and Returner's Info Details
		
		JLabel BookTitle = new JLabel("Book Title:");
		BookTitle.setBounds(34, 77, 100, 14);
		inter_bold.applyFont(BookTitle, 13F, Color.black);
		ReturnDetailsPanel.add(BookTitle);
		
		JLabel BookAuthor = new JLabel("Book Author:");
		BookAuthor.setBounds(34, 140, 100, 14);
		inter_bold.applyFont(BookAuthor, 13F, Color.black);
		ReturnDetailsPanel.add(BookAuthor);
		
		JLabel BookCategory = new JLabel("Category:");
		BookCategory.setBounds(34, 193, 100, 14);
		inter_bold.applyFont(BookCategory, 13F, Color.black);
		ReturnDetailsPanel.add(BookCategory);
		
		JLabel StudentName = new JLabel("Student Name:");
		StudentName.setBounds(253, 115, 120, 14);
		inter_bold.applyFont(StudentName, 13F, Color.black);
		ReturnDetailsPanel.add(StudentName);
		
		JLabel StudentGender = new JLabel("Gender:");
		StudentGender.setBounds(253, 170, 100, 14);
		inter_bold.applyFont(StudentGender, 13F, Color.black);
		ReturnDetailsPanel.add(StudentGender);
		
		JLabel StudentDepartment = new JLabel("Department:");
		StudentDepartment.setBounds(253, 210, 120, 14);
		inter_bold.applyFont(StudentDepartment, 13F, Color.black);
		ReturnDetailsPanel.add(StudentDepartment);
		
		JLabel BorrowedDate = new JLabel("Borrowed Date:");
		BorrowedDate.setBounds(480, 89, 120, 14);
		inter_bold.applyFont(BorrowedDate, 13F, Color.black);
		ReturnDetailsPanel.add(BorrowedDate);
		
		JLabel ReturnedDate = new JLabel("Returned Date:");
		ReturnedDate.setBounds(480, 160, 120, 14);
		inter_bold.applyFont(ReturnedDate, 13F, Color.black);
		ReturnDetailsPanel.add(ReturnedDate);
		
		// Values
		
		JTextArea bookTitleTxt = new JTextArea("");
		bookTitleTxt.setWrapStyleWord(true);
		bookTitleTxt.setLineWrap(true);
		bookTitleTxt.setFocusable(false);
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(34, 91, 199, 48);
		inter_regular.applyFont(bookTitleTxt, 13F, Color.black);
		bookTitleTxt.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(bookTitleTxt);
		
		JTextArea bookAuthorTxt = new JTextArea("");
		bookAuthorTxt.setWrapStyleWord(true);
		bookAuthorTxt.setLineWrap(true);
		bookAuthorTxt.setFocusable(false);
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setBounds(34, 155, 199, 40);
		inter_regular.applyFont(bookAuthorTxt, 13F, Color.black);
		bookAuthorTxt.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(bookAuthorTxt);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(253, 77, 192, 14);
		inter_bold.applyFont(lblStudentNumber, 13F, Color.black);
		ReturnDetailsPanel.add(lblStudentNumber);
		
		JTextArea studentNumberTxt = new JTextArea("");
		studentNumberTxt.setWrapStyleWord(true);
		studentNumberTxt.setLineWrap(true);
		studentNumberTxt.setFocusable(false);
		studentNumberTxt.setEditable(false);
		studentNumberTxt.setBackground(new Color(216, 216, 216));
		studentNumberTxt.setBounds(253, 93, 199, 20);
		inter_regular.applyFont(studentNumberTxt, 13F, Color.black);
		ReturnDetailsPanel.add(studentNumberTxt);
		
		JTextArea studentNameTxt = new JTextArea("");
		studentNameTxt.setWrapStyleWord(true);
		studentNameTxt.setLineWrap(true);
		studentNameTxt.setFocusable(false);
		studentNameTxt.setEditable(false);
		studentNameTxt.setBounds(253, 133, 202, 35);
		inter_regular.applyFont(studentNameTxt, 13F, Color.black);
		studentNameTxt.setBackground(new Color(216, 216, 216));
		ReturnDetailsPanel.add(studentNameTxt);
		
		JLabel bookCategoryTxt = new JLabel("");
		bookCategoryTxt.setBounds(34, 208, 60, 20);
		inter_regular.applyFont(bookCategoryTxt, 13F, Color.black);
		ReturnDetailsPanel.add(bookCategoryTxt);
		
		JLabel genderTxt = new JLabel("");
		genderTxt.setBounds(253, 188, 60, 20);
		inter_regular.applyFont(genderTxt, 13F, Color.black);
		ReturnDetailsPanel.add(genderTxt);
		
		JLabel departmentTxt = new JLabel("");
		departmentTxt.setBounds(253, 229, 100, 20);
		inter_regular.applyFont(departmentTxt, 13F, Color.black);
		ReturnDetailsPanel.add(departmentTxt);
		
		JLabel borrowedDateTxt = new JLabel("");
		borrowedDateTxt.setBounds(480, 103, 150, 20);
		inter_regular.applyFont(borrowedDateTxt, 13F, Color.black);
		ReturnDetailsPanel.add(borrowedDateTxt);
		
		JLabel returnedDateTxt = new JLabel("");
		returnedDateTxt.setBounds(480, 175, 150, 20);
		inter_regular.applyFont(returnedDateTxt, 13F, Color.black);
		ReturnDetailsPanel.add(returnedDateTxt);
		
		// Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 160, 886, 220);
		add(scrollPane);
		
		returnBooksTable = new JTable();
		returnBooksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = returnBooksTable.getSelectedRow();				
				int returnID = Integer.parseInt(model.getValueAt(row, 0).toString());
				try {
					prep_stmt = connect.conn.prepareStatement("SELECT * FROM returns "
							   + "INNER JOIN borrows ON returns.borrow_id = borrows.borrow_id "
							   + "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
							   + "INNER JOIN students ON borrows.student_id = students.student_id "
							   + "WHERE return_id = ?");
					prep_stmt.setInt(1, returnID);
					rs = prep_stmt.executeQuery();
					if(rs.next()) {
						returnIdTxt.setText(rs.getString("return_id"));
						bookTitleTxt.setText(rs.getString("book_title"));
						bookAuthorTxt.setText(rs.getString("book_author"));
						bookCategoryTxt.setText(rs.getString("book_category"));
						studentNumberTxt.setText(rs.getString("student_number"));
						studentNameTxt.setText(rs.getString("student_name"));
						departmentTxt.setText(rs.getString("student_department"));
						genderTxt.setText(rs.getString("student_gender"));
						borrowedDateTxt.setText(rs.getString("borrow_date"));
						returnedDateTxt.setText(rs.getString("return_date"));
					}
					
					rs.close();
					prep_stmt.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(returnBooksTable);
		
		Object[] column = {"Return ID", "Book Title", "Category", "Returner's Name", "Borrowed Date", "Returned Date"};
		model.setColumnIdentifiers(column);
		
		returnBooksTable.setModel(model);
		returnBooksTable.getTableHeader().setReorderingAllowed(false);
		returnBooksTable.getTableHeader().setResizingAllowed(false);
		returnBooksTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			returnBooksTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = returnBooksTable.getTableHeader();
		inter_bold.applyFont(header, 13f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		returnBooksTable.setRowHeight(30);
		returnBooksTable.setFocusable(true);
		returnBooksTable.setTableHeader(header);
		
		fetchReturnedBooks();
	}
	
	// displays all data
	public void fetchReturnedBooks() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM returns "
					   + "INNER JOIN borrows ON returns.borrow_id = borrows.borrow_id "
					   + "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					   + "INNER JOIN students ON borrows.student_id = students.student_id "
					   + "ORDER BY returns.return_id");
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
				
			while(rs.next()) {
				int return_id = rs.getInt("return_id");
				String book_title = rs.getString("book_title");
				String book_category = rs.getString("book_category");
				String student_name = rs.getString("student_name");
				String borrow_date = rs.getString("borrow_date");
				String return_date = rs.getString("return_date");
					
				model.addRow(new Object[] {return_id, book_title, book_category, student_name, borrow_date, return_date});
			}
				
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Search Data
		public void searchReturnedBook(String searchTxt) {
			try {
				prep_stmt = connect.conn.prepareStatement("SELECT * FROM returns "
						   + "INNER JOIN borrows ON returns.borrow_id = borrows.borrow_id "
						   + "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
						   + "INNER JOIN students ON borrows.student_id = students.student_id "
						   + "WHERE return_id LIKE ? OR books.book_title LIKE ? OR books.book_category LIKE ? "
						   + "OR students.student_name LIKE ? OR return_date LIKE ? OR borrows.borrow_date LIKE ? "
						   + "OR students.student_number LIKE ? ORDER BY returns.return_id");
				prep_stmt.setString(1, "%" + searchTxt + "%");
				prep_stmt.setString(2, "%" + searchTxt + "%");
				prep_stmt.setString(3, "%" + searchTxt + "%");
				prep_stmt.setString(4, "%" + searchTxt + "%");
				prep_stmt.setString(5, "%" + searchTxt + "%");
				prep_stmt.setString(6, "%" + searchTxt + "%");
				prep_stmt.setString(7, "%" + searchTxt + "%");
				rs = prep_stmt.executeQuery();

				model.setRowCount(0); // Resets the row
				
				// Checks for match Results
				if(!rs.next()) {
					JOptionPane.showMessageDialog(this, "No results match your search", "Error", JOptionPane.ERROR_MESSAGE);
					fetchReturnedBooks();
				} else {
					// Iterate each row
					do {
						int return_id = rs.getInt("return_id");
						String book_title = rs.getString("book_title");
						String book_category = rs.getString("book_category");
						String student_name = rs.getString("student_name");
						String borrow_date = rs.getString("borrow_date");
						String return_date = rs.getString("return_date");
						
						model.addRow(new Object[] {return_id, book_title, book_category, student_name, borrow_date, return_date});
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
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchBtn) {
			String search = searchTxt.getText();
			searchReturnedBook(search);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == returnScanPanel) {
			ReturnBookScanner returnScanner = new ReturnBookScanner();
			returnScanner.setVisible(true);			
			returnScanner.setLocationRelativeTo(null);
			returnScanner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			returnScanner.addWindowListener(new WindowAdapter() {
				@Override
	            public void windowClosed(WindowEvent e) {
	                // This method is called when the window is closed.
					fetchReturnedBooks();
					MainApp.getInstance().updateOverdueNotification();
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
