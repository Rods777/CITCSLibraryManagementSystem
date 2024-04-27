package modals;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

import javax.swing.JLabel;
import javax.swing.JSeparator;

public class AddBookModal extends JDialog {

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
			AddBookModal dialog = new AddBookModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBookModal() {
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
		
		RoundedTextField bookAuthorTxt = new RoundedTextField(30);
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
		
		JComboBox categoryCb = new JComboBox();
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
		
		RoundedTextField barCodeTxt = new RoundedTextField(30);
		barCodeTxt.setColumns(10);
		barCodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		barCodeTxt.setBackground(new Color(242, 242, 242));
		barCodeTxt.setBounds(191, 317, 312, 40);
		inter_regular.applyFont(barCodeTxt, 16f, Color.BLACK);
		contentPanel.add(barCodeTxt);
		
		// Save Button
		RoundedButton saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 413, 453, 62);
		contentPanel.add(saveBtn);
	}
}
