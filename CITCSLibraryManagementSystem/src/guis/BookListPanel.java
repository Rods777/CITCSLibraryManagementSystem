package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import inheritances.RoundedTextField;
import modals.AddBookModal;
import modals.EditBookModal;
import modals.EditLogModal;
import modals.ViewBookModal;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookListPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField searchTxt;
	private RoundedButton AddBookButton, editBookButton, viewMoreButton, deleteBookButton, searchBtn;
	
	private JLabel bookIdValue, bookCategoryValue, bookStatusValue, bookBarcodeValue;
	private JTextArea bookTitleValue, bookAuthorValue;
	
	private JTable bookListTable;
	private DefaultTableModel model = new DefaultTableModel();
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the panel.
	 */
	public BookListPanel() {
		connect.Connect();
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
		bookIdValue = new JLabel();
		bookIdValue.setBounds(43, 87, 138, 14);
		inter_regular.applyFont(bookIdValue, 15f, Color.BLACK);
		bookDetailsPanel.add(bookIdValue);
		
		bookCategoryValue = new JLabel();
		inter_regular.applyFont(bookCategoryValue, 15f, Color.BLACK);
		bookCategoryValue.setBounds(222, 170, 190, 18);
		bookDetailsPanel.add(bookCategoryValue);
		
		bookStatusValue = new JLabel();
		inter_regular.applyFont(bookStatusValue, 15f, Color.BLACK);
		bookStatusValue.setBounds(434, 87, 214, 18);
		bookDetailsPanel.add(bookStatusValue);
		
		bookBarcodeValue = new JLabel();
		inter_regular.applyFont(bookBarcodeValue, 15f, Color.BLACK);
		bookBarcodeValue.setBounds(434, 172, 196, 14);
		bookDetailsPanel.add(bookBarcodeValue);
		
		bookTitleValue = new JTextArea();
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBackground(Color.decode("#D8D8D8"));
		bookTitleValue.setBounds(43, 170, 159, 76);
		inter_regular.applyFont(bookTitleValue, 15f, Color.BLACK);
		bookDetailsPanel.add(bookTitleValue);
		
		bookAuthorValue = new JTextArea();
		bookAuthorValue.setWrapStyleWord(true);
		bookAuthorValue.setLineWrap(true);
		bookAuthorValue.setFocusable(false);
		bookAuthorValue.setEditable(false);
		bookAuthorValue.setBackground(new Color(216, 216, 216));
		bookAuthorValue.setBounds(222, 82, 178, 57);
		inter_regular.applyFont(bookAuthorValue, 15f, Color.BLACK);
		bookDetailsPanel.add(bookAuthorValue);
		
		
		// Action Buttons
		viewMoreButton = new RoundedButton("View More", 15, Color.WHITE);
		viewMoreButton.addActionListener(this);
		inter_bold.applyFont(viewMoreButton, 14f, Color.BLACK);
		viewMoreButton.setBounds(705, 66, 116, 35);
		bookDetailsPanel.add(viewMoreButton);
		
		editBookButton = new RoundedButton("Edit Book", 15, CommonConstants.EDIT_BUTTON);
		editBookButton.addActionListener(this);
		inter_bold.applyFont(editBookButton, 14f, Color.WHITE);
		editBookButton.setBounds(705, 112, 116, 35);
		bookDetailsPanel.add(editBookButton);
		
		deleteBookButton = new RoundedButton("Delete Book", 15, CommonConstants.DELETE_BUTTON);
		deleteBookButton.addActionListener(this);
		inter_bold.applyFont(deleteBookButton, 14f, Color.WHITE);
		deleteBookButton.setBounds(704, 162, 117, 35);
		bookDetailsPanel.add(deleteBookButton);
		
		AddBookButton = new RoundedButton("Add Book", 13, new Color(66, 151, 55));
		AddBookButton.addActionListener(this);
		inter_bold.applyFont(AddBookButton, 14f, Color.WHITE);
		AddBookButton.setBounds(824, 123, 106, 33);
		add(AddBookButton);
		
		searchTxt = new RoundedTextField(10);
		searchTxt.setPlaceholder("Search...");
		inter_regular.applyFont(searchTxt, 14f, Color.BLACK);
		searchTxt.setBackground(new Color(220, 220, 220));
		searchTxt.setBounds(43, 123, 159, 32);
		searchTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		add(searchTxt);
		
		searchBtn = new RoundedButton("", 10, Color.decode("#569FF0"));
		searchBtn.addActionListener(this);
		searchBtn.setIcon(new ImageIcon(BookListPanel.class.getResource("/icons/search-iconpng.png")));
		searchBtn.setBounds(204, 123, 38, 33);
		add(searchBtn);
		
		// Table
		Object[] column = {"Book ID", "Book Name", "Book Author", "Category", "Bar Code", "Status"};
		model.setColumnIdentifiers(column);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 166, 887, 219);
		add(scrollPane);
		
		bookListTable = new JTable();
		bookListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = bookListTable.getSelectedRow();
				
				bookIdValue.setText(model.getValueAt(row, 0).toString());
				bookTitleValue.setText(model.getValueAt(row, 1).toString());
				bookAuthorValue.setText(model.getValueAt(row, 2).toString());
				bookCategoryValue.setText(model.getValueAt(row, 3).toString());
				bookBarcodeValue.setText(model.getValueAt(row, 4).toString());
				bookStatusValue.setText(model.getValueAt(row, 5).toString());
			}
		});
		bookListTable.setSelectionBackground(Color.LIGHT_GRAY);
		inter_regular.applyFont(bookListTable, 14f, Color.BLACK);
		scrollPane.setViewportView(bookListTable);
		bookListTable.setModel(model);
		bookListTable.getTableHeader().setReorderingAllowed(false);
		bookListTable.getTableHeader().setResizingAllowed(false);
		bookListTable.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			bookListTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// Table header
		JTableHeader header = bookListTable.getTableHeader();
		inter_bold.applyFont(header, 14f, Color.WHITE);
		header.setBackground(Color.decode("#35782D"));
		header.setForeground(Color.WHITE);
		bookListTable.setRowHeight(30);
		bookListTable.setFocusable(true);
		bookListTable.setTableHeader(header);
		
		fetchBookData();
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
    
    // Fetch all Book in database
	public void fetchBookData() {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books");
			rs = prep_stmt.executeQuery();
			model.setRowCount(0); // Resets the row
			
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String book_title = rs.getString("book_title");
				String book_author = rs.getString("book_author");
				String book_category = rs.getString("book_category");
				String book_barcode = rs.getString("book_barcode");
				String book_status = rs.getString("book_status");
				
				model.addRow(new Object[] {book_id, book_title, book_author, book_category, book_barcode, book_status});
			}
			
			rs.close();
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Search Data
	public void searchStudent(String searchTxt) {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE "
					+ "book_id LIKE ? OR book_title LIKE ? OR book_author LIKE ? OR book_category LIKE ? "
					+ "OR book_barcode LIKE ? OR book_status LIKE ?");
			prep_stmt.setString(1, "%"+searchTxt+"%");
			prep_stmt.setString(2, "%"+searchTxt+"%");
			prep_stmt.setString(3, "%"+searchTxt+"%");
			prep_stmt.setString(4, "%"+searchTxt+"%");
			prep_stmt.setString(5, "%"+searchTxt+"%");
			prep_stmt.setString(6, "%"+searchTxt+"%");
			rs = prep_stmt.executeQuery();			
			model.setRowCount(0); // Resets the row
			
			// Checks for match Results
			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "No results match your search", "Error", JOptionPane.ERROR_MESSAGE);
				fetchBookData();
			} else {
				// Iterate each row
				do {
					int book_id = rs.getInt("book_id");
					String book_title = rs.getString("book_title");
					String book_author = rs.getString("book_author");
					String book_category = rs.getString("book_category");
					String book_barcode = rs.getString("book_barcode");
					String book_status = rs.getString("book_status");
					
					model.addRow(new Object[] {book_id, book_title, book_author, book_category, book_barcode, book_status});
				} while(rs.next());
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Search Button
		if(e.getSource() == searchBtn) {
			String search = searchTxt.getText();
			searchStudent(search);
		}
		
		// Methods
		if(e.getSource() == AddBookButton) {
			AddBookModal modal = new AddBookModal();
			modal.setLocationRelativeTo(null);
			modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			modal.setVisible(true);
			modal.addWindowListener(new WindowAdapter() {
				@Override
	            public void windowClosed(WindowEvent e) {
	                // This method is called when the window is closed.
					fetchBookData();
	            }
			});	
		}
		if(e.getSource() == editBookButton) {
			String bID = bookIdValue.getText();
			if(!bID.isEmpty()) {
				int bookID = Integer.parseInt(bID);
				EditBookModal modal = new EditBookModal(bookID);
				modal.setLocationRelativeTo(null);
				modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				modal.setVisible(true);
				modal.addWindowListener(new WindowAdapter() {
					@Override
		            public void windowClosed(WindowEvent e) {
		                // This method is called when the window is closed.
						fetchBookData();
		            }
				});	
			} else {
				JOptionPane.showMessageDialog(null, "Select Book to Edit first", "WARNING", JOptionPane.WARNING_MESSAGE);
			}	
		}
		if(e.getSource() == viewMoreButton) {
			String bID = bookIdValue.getText();
			if(!bID.isEmpty()) {
				int bookID = Integer.parseInt(bID);
				ViewBookModal modal = new ViewBookModal(bookID);
				modal.setLocationRelativeTo(null);
				modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				modal.setVisible(true);
				modal.addWindowListener(new WindowAdapter() {
					@Override
		            public void windowClosed(WindowEvent e) {
		                // This method is called when the window is closed.
						fetchBookData();
		            }
				});	
			} else {
				JOptionPane.showMessageDialog(null, "Select Book to View first", "WARNING", JOptionPane.WARNING_MESSAGE);
			}	

		}
		if(e.getSource() == deleteBookButton) {
			String bID = bookIdValue.getText();
			if(!bID.isEmpty()) {
				int result = JOptionPane.showConfirmDialog(null , "Are you sure you want to Delete this data?", 
						"Delete Confirmation",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					int selectedBookID = Integer.parseInt(bID);
					try {
						prep_stmt = connect.conn.prepareStatement("DELETE FROM books WHERE book_id = ?");
						prep_stmt.setInt(1, selectedBookID);
						
						int deletedRow = prep_stmt.executeUpdate();
						
						if(deletedRow == 1) {
							JOptionPane.showMessageDialog(null, "Book has been Deleted!");
							fetchBookData();
						}
						
						prep_stmt.close();
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Select Book to Delete first", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}
