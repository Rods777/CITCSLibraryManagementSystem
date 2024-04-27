package modals;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import inheritances.FontLoader;

public class ViewBookModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewBookModal dialog = new ViewBookModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewBookModal() {
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
		JLabel lblBookNo = new JLabel("Book No.:");
		inter_bold.applyFont(lblBookNo, 14f, Color.BLACK);
		lblBookNo.setBounds(56, 102, 78, 14);
		contentPanel.add(lblBookNo);
		
		JLabel bookNoValue = new JLabel("1");
		bookNoValue.setBounds(56, 127, 46, 14);
		inter_regular.applyFont(bookNoValue, 14f, Color.BLACK);
		contentPanel.add(bookNoValue);
		
		JLabel lblCategory = new JLabel("Category:");
		inter_bold.applyFont(lblCategory, 14f, Color.BLACK);
		lblCategory.setBounds(336, 99, 78, 22);
		contentPanel.add(lblCategory);
		
		JLabel categoryValue = new JLabel("Foreign");
		categoryValue.setBounds(336, 122, 78, 22);
		inter_regular.applyFont(categoryValue, 14f, Color.BLACK);
		contentPanel.add(categoryValue);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		inter_bold.applyFont(lblBookTitle, 14f, Color.BLACK);
		lblBookTitle.setBounds(56, 166, 105, 14);
		contentPanel.add(lblBookTitle);
		
		JLabel lblStatus = new JLabel("Status:");
		inter_bold.applyFont(lblStatus, 14f, Color.BLACK);
		lblStatus.setBounds(336, 163, 78, 22);
		contentPanel.add(lblStatus);
		
		JLabel statusValue = new JLabel("Pending for Return");
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
		
		JLabel barcodeValue = new JLabel("PLMUN000000123");
		barcodeValue.setBounds(336, 261, 159, 22);
		inter_regular.applyFont(barcodeValue, 14f, Color.BLACK);
		contentPanel.add(barcodeValue);
		
		JTextArea bookTitleValue = new JTextArea("Adolf Schwarzenegger Jr.");
		bookTitleValue.setWrapStyleWord(true);
		bookTitleValue.setLineWrap(true);
		bookTitleValue.setFocusable(false);
		bookTitleValue.setEditable(false);
		bookTitleValue.setBounds(56, 187, 244, 46);
		inter_regular.applyFont(bookTitleValue, 14F, Color.black);
		bookTitleValue.setBackground(Color.WHITE);
		contentPanel.add(bookTitleValue);
		
		JTextArea bookAuthorValue = new JTextArea("Adolf Schwarzenegger Jr.");
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
	}
}
