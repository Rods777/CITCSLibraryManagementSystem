package modals;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

public class EditBookModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField bookTitleTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditBookModal dialog = new EditBookModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditBookModal() {
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
		
		RoundedTextField bookAuthorTxt = new RoundedTextField(30);
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
		
		JComboBox categoryCb = new JComboBox();
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
		
		JComboBox statusCb = new JComboBox();
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
		
		RoundedTextField barCodeTxt = new RoundedTextField(30);
		barCodeTxt.setColumns(10);
		barCodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		barCodeTxt.setBackground(new Color(242, 242, 242));
		barCodeTxt.setBounds(191, 330, 312, 40);
		inter_regular.applyFont(barCodeTxt, 16f, Color.BLACK);
		contentPanel.add(barCodeTxt);
		
		// Save Button
		RoundedButton saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 415, 453, 62);
		contentPanel.add(saveBtn);
	}
}
