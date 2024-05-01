package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import db.DBConnection;
import inheritances.FontLoader;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewBookModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	
	private DefaultTableModel model = new DefaultTableModel();

	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the dialog.
	 */
	int bookID;
	private JTable historyBorrowersTable;
	public ViewBookModal(int bID) {
		this.bookID = bID;
		connect.Connect();
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 589, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		//Header
		JLabel lblBookInformation = new JLabel("Book Information");
		lblBookInformation.setBounds(46, 21, 401, 46);
		inter_extrabold.applyFont(lblBookInformation, 36f, Color.decode("#14412D"));
		contentPanel.add(lblBookInformation);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(46, 78, 480, 1);
		contentPanel.add(separator);
		
		
		// Book Details
		JLabel lblBookNo = new JLabel("Book ID:");
		inter_bold.applyFont(lblBookNo, 14f, Color.BLACK);
		lblBookNo.setBounds(56, 102, 78, 14);
		contentPanel.add(lblBookNo);
		
		JLabel bookIdValue = new JLabel();
		bookIdValue.setBounds(56, 127, 46, 14);
		inter_regular.applyFont(bookIdValue, 14f, Color.BLACK);
		contentPanel.add(bookIdValue);
		
		JLabel lblCategory = new JLabel("Category:");
		inter_bold.applyFont(lblCategory, 14f, Color.BLACK);
		lblCategory.setBounds(336, 99, 78, 22);
		contentPanel.add(lblCategory);
		
		JLabel bookCategoryValue = new JLabel();
		bookCategoryValue.setBounds(336, 122, 78, 22);
		inter_regular.applyFont(bookCategoryValue, 14f, Color.BLACK);
		contentPanel.add(bookCategoryValue);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		inter_bold.applyFont(lblBookTitle, 14f, Color.BLACK);
		lblBookTitle.setBounds(56, 166, 105, 14);
		contentPanel.add(lblBookTitle);
		
		JLabel lblStatus = new JLabel("Status:");
		inter_bold.applyFont(lblStatus, 14f, Color.BLACK);
		lblStatus.setBounds(336, 163, 78, 22);
		contentPanel.add(lblStatus);
		
		JLabel statusValue = new JLabel();
		statusValue.setBounds(336, 186, 159, 22);
		inter_regular.applyFont(statusValue, 14f, Color.BLACK);
		contentPanel.add(statusValue);
		
		JLabel lblBookAuthor = new JLabel("Book Author:");
		inter_bold.applyFont(lblBookAuthor, 14f, Color.BLACK);
		lblBookAuthor.setBounds(56, 241, 105, 14);
		contentPanel.add(lblBookAuthor);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		inter_bold.applyFont(lblBarcode, 14f, Color.BLACK);
		lblBarcode.setBounds(336, 238, 78, 22);
		contentPanel.add(lblBarcode);
		
		JLabel barcodeValue = new JLabel();
		barcodeValue.setBounds(336, 261, 159, 22);
		inter_regular.applyFont(barcodeValue, 14f, Color.BLACK);
		contentPanel.add(barcodeValue);
		
		JTextArea bookTitleValue = new JTextArea();
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBounds(56, 187, 244, 46);
		inter_regular.applyFont(bookTitleValue, 14F, Color.black);
		bookTitleValue.setBackground(Color.WHITE);
		contentPanel.add(bookTitleValue);
		
		JTextArea bookAuthorValue = new JTextArea();
		bookAuthorValue.setWrapStyleWord(true);
		bookAuthorValue.setLineWrap(true);
		bookAuthorValue.setFocusable(false);
		bookAuthorValue.setEditable(false);
		bookAuthorValue.setBackground(Color.WHITE);
		inter_regular.applyFont(bookAuthorValue, 14F, Color.black);
		bookAuthorValue.setBounds(56, 262, 244, 46);
		contentPanel.add(bookAuthorValue);
		
		JLabel lblHistoryBorrowers = new JLabel("History Borrowers");
		lblHistoryBorrowers.setBounds(56, 324, 225, 22);
		inter_bold.applyFont(lblHistoryBorrowers, 18f, Color.BLACK);
		contentPanel.add(lblHistoryBorrowers);
		
		// History Borrowers Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 357, 480, 201);
		contentPanel.add(scrollPane);
		
		historyBorrowersTable = new JTable();
		scrollPane.setViewportView(historyBorrowersTable);
		Object[] column = {"Borrow ID", "Borrower's Name", "Borrowed Date"};
		model.setColumnIdentifiers(column);
		
		historyBorrowersTable.setModel(model);
		historyBorrowersTable.getTableHeader().setReorderingAllowed(false);
		historyBorrowersTable.getTableHeader().setResizingAllowed(false);
		historyBorrowersTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			historyBorrowersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = historyBorrowersTable.getTableHeader();
		inter_bold.applyFont(header, 13f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		historyBorrowersTable.setRowHeight(35);
		historyBorrowersTable.setFocusable(true);
		historyBorrowersTable.setTableHeader(header);
		
		// Display the book data
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE book_id = ?");
			prep_stmt.setInt(1, bookID);
			rs = prep_stmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
			    String author = rs.getString(3);
			    String category = rs.getString(4);
			    String barcode = rs.getString(5);
			    String status = rs.getString(6);
			    
				bookIdValue.setText(id);
				bookTitleValue.setText(title);
				bookAuthorValue.setText(author);
				bookCategoryValue.setText(category);
				barcodeValue.setText(barcode);
				statusValue.setText(status);
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fetchHistoryBorrowers(bookID);
	}
	
	public void fetchHistoryBorrowers(int bookID) {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
					+ "INNER JOIN students ON borrows.student_id = students.student_id\r\n"
					+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					+ "WHERE books.book_id = ? ORDER BY borrow_id DESC");
			prep_stmt.setInt(1, bookID);
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				String borrow_id = rs.getString("borrow_id");
				String student_name = rs.getString("student_name");
				String borrow_date = rs.getString("borrow_date");
				
				model.addRow(new Object[] {borrow_id, student_name, borrow_date});
			}
				
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
