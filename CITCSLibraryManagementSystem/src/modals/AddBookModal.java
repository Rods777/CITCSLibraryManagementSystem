package modals;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddBookModal extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField bookTitleTxt, bookAuthorTxt, barCodeTxt;
	private JComboBox categoryCb;
	private RoundedButton saveBtn;
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;
	/**
	 * Create the dialog.
	 */
	public AddBookModal() {
		connect.Connect();
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel addBook = new JLabel("Add Book");
		addBook.setBounds(40, 35, 181, 46);
		inter_extrabold.applyFont(addBook, 36f, Color.decode("#14412D"));
		contentPanel.add(addBook);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(27, 103, 480, 1);
		contentPanel.add(separator);
		
		// Add Modal Details	
		JLabel bookTitle = new JLabel("Book Title:");
		inter_bold.applyFont(bookTitle, 16f, Color.BLACK);
		bookTitle.setBounds(50, 139, 87, 14);
		contentPanel.add(bookTitle);
		
		bookTitleTxt = new RoundedTextField(30);
		inter_regular.applyFont(bookTitleTxt, 16f, Color.BLACK);
		bookTitleTxt.setBounds(191, 126, 312, 40);
		bookTitleTxt.setBackground(Color.decode("#F2F2F2"));
		bookTitleTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		contentPanel.add(bookTitleTxt);
		bookTitleTxt.setColumns(10);
		
		JLabel bookAuthor = new JLabel("Book Author:");
		inter_bold.applyFont(bookAuthor, 16f, Color.BLACK);
		bookAuthor.setBounds(50, 204, 131, 14);
		contentPanel.add(bookAuthor);
		
		bookAuthorTxt = new RoundedTextField(30);
		inter_regular.applyFont(bookAuthorTxt, 16f, Color.BLACK);
		bookAuthorTxt.setColumns(10);
		bookAuthorTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookAuthorTxt.setBackground(new Color(242, 242, 242));
		bookAuthorTxt.setBounds(191, 191, 312, 40);
		contentPanel.add(bookAuthorTxt);
		
		JLabel lblBookcategory = new JLabel("Category:");
		inter_bold.applyFont(lblBookcategory, 16f, Color.BLACK);
		lblBookcategory.setBounds(50, 265, 87, 19);
		contentPanel.add(lblBookcategory);
		
		categoryCb = new JComboBox();
		categoryCb.addItem("Local");
		categoryCb.addItem("Foreign");
		categoryCb.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(categoryCb, 16f, Color.BLACK);
		categoryCb.setSelectedItem(null);
		categoryCb.setFocusable(false);
		categoryCb.setBounds(191, 259, 312, 30);
		contentPanel.add(categoryCb);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		inter_bold.applyFont(lblBarcode, 16f, Color.BLACK);
		lblBarcode.setBounds(50, 330, 131, 14);
		contentPanel.add(lblBarcode);
		
		barCodeTxt = new RoundedTextField(30);
		barCodeTxt.setColumns(10);
		barCodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		barCodeTxt.setBackground(new Color(242, 242, 242));
		barCodeTxt.setBounds(191, 317, 312, 40);
		inter_regular.applyFont(barCodeTxt, 16f, Color.BLACK);
		contentPanel.add(barCodeTxt);
		
		// Save Button
		saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		saveBtn.addActionListener(this);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 413, 453, 62);
		contentPanel.add(saveBtn);
	}
	
	
	// Save Button Functionality
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String bookTitle = bookTitleTxt.getText();
		String bookAuthor = bookAuthorTxt.getText();
		String category = (String) categoryCb.getSelectedItem();
		String barCode = barCodeTxt.getText();
		
		if(bookTitle.isEmpty() || bookAuthor.isEmpty() || category == null || barCode.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
		}else {
			try {
				prep_stmt = connect.conn.prepareStatement("INSERT INTO books (book_title, book_author, book_category, book_barcode, book_status) VALUES (?, ?, ?, ?, ?)");
				prep_stmt.setString(1, bookTitle);
				prep_stmt.setString(2, bookAuthor);
				prep_stmt.setString(3, category);
				prep_stmt.setString(4, barCode);
				prep_stmt.setString(5, "Available");
				
				int row = prep_stmt.executeUpdate();
				if(row == 1) {
					JOptionPane.showMessageDialog(null, "Successfully Added a new Book!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Adding Book Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				prep_stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	}
}
