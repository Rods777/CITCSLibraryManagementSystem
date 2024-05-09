package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

public class EditBookModal extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField bookTitleTxt, bookAuthorTxt, barCodeTxt;
	private JComboBox categoryCb, statusCb;
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Create the dialog.
	 */
	int bookID;
	public EditBookModal(int bID) {
		this.bookID = bID;
		connect.Connect();
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel Book = new JLabel("Edit Book");
		Book.setBounds(40, 35, 181, 46);
		inter_extrabold.applyFont(Book, 36f, Color.decode("#14412D"));
		contentPanel.add(Book);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(27, 103, 480, 1);
		contentPanel.add(separator);		
		
		// Edit Modal Details
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
		bookAuthor.setBounds(50, 191, 131, 14);
		contentPanel.add(bookAuthor);
		
		bookAuthorTxt = new RoundedTextField(30);
		inter_regular.applyFont(bookAuthorTxt, 16f, Color.BLACK);
		bookAuthorTxt.setColumns(10);
		bookAuthorTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookAuthorTxt.setBackground(new Color(242, 242, 242));
		bookAuthorTxt.setBounds(191, 178, 312, 40);
		contentPanel.add(bookAuthorTxt);
		
		JLabel lblBookcategory = new JLabel("Category:");
		inter_bold.applyFont(lblBookcategory, 16f, Color.BLACK);
		lblBookcategory.setBounds(50, 238, 87, 19);
		contentPanel.add(lblBookcategory);
		
		categoryCb = new JComboBox();
		categoryCb.addItem("Local");
		categoryCb.addItem("Foreign");
		categoryCb.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(categoryCb, 16f, Color.BLACK);
		categoryCb.setSelectedItem(null);
		categoryCb.setFocusable(false);
		categoryCb.setBounds(191, 232, 312, 30);
		contentPanel.add(categoryCb);
		
		JLabel lblStatus = new JLabel("Status:");
		inter_bold.applyFont(lblStatus, 16f, Color.BLACK);
		lblStatus.setBounds(50, 292, 131, 14);
		contentPanel.add(lblStatus);
		
		statusCb = new JComboBox();
		statusCb.addItem("Available");
		statusCb.addItem("Unavailable");
		statusCb.setFocusable(false);
		statusCb.setSelectedItem(null);
		statusCb.setBackground(new Color(242, 242, 242));
		inter_regular.applyFont(statusCb, 16f, Color.BLACK);
		statusCb.setBounds(191, 280, 312, 30);
		
		contentPanel.add(statusCb);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		inter_bold.applyFont(lblBarcode, 16f, Color.BLACK);
		lblBarcode.setBounds(50, 343, 131, 14);
		contentPanel.add(lblBarcode);
		
		barCodeTxt = new RoundedTextField(30);
		barCodeTxt.setColumns(10);
		barCodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		barCodeTxt.setBackground(new Color(242, 242, 242));
		barCodeTxt.setBounds(191, 330, 312, 40);
		inter_regular.applyFont(barCodeTxt, 16f, Color.BLACK);
		contentPanel.add(barCodeTxt);
		
		// Save Button
		RoundedButton saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		saveBtn.addActionListener(this);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 415, 453, 62);
		contentPanel.add(saveBtn);
		
		// Set the value of student data
			try {
				prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE book_id = ?");
				prep_stmt.setInt(1, bookID);
				rs = prep_stmt.executeQuery();
					
				if(rs.next()) {
					String title = rs.getString(2);
				    String author = rs.getString(3);
				    String category = rs.getString(4);
				    String barcode = rs.getString(5);
				    String status = rs.getString(6);
				    
					bookTitleTxt.setText(title);
					bookAuthorTxt.setText(author);
			        categoryCb.setSelectedItem(category);
					barCodeTxt.setText(barcode);
			        statusCb.setSelectedItem(status);
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
		String bookTitle = bookTitleTxt.getText();
		String bookAuthor = bookAuthorTxt.getText();
        String category = (String) categoryCb.getSelectedItem();
		String barCode = barCodeTxt.getText();
        String status = (String) statusCb.getSelectedItem();
        
        if (bookTitle.isEmpty() || bookAuthor.isEmpty() || category == null || barCode.isEmpty() || status == null) {
            JOptionPane.showMessageDialog(null, "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				prep_stmt = connect.conn.prepareStatement("UPDATE books SET book_title = ?, book_author = ?, book_category = ?, book_barcode = ?, book_status = ? WHERE book_id = ?");
				prep_stmt.setString(1, bookTitle);
				prep_stmt.setString(2, bookAuthor);
				prep_stmt.setString(3, category);
				prep_stmt.setString(4, barCode);
				prep_stmt.setString(5, status);
				prep_stmt.setInt(6, bookID);
				
				int row = prep_stmt.executeUpdate();
				
				if(row == 1) {
					JOptionPane.showMessageDialog(null, "Successfully Edit Book!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Editing Book Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
