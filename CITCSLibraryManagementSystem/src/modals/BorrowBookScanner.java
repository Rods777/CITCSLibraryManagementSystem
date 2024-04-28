package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import inheritances.FontLoader;
import inheritances.RoundedTextField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import inheritances.RoundedButton;

public class BorrowBookScanner extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_medium = new FontLoader("/fonts/Inter-Medium.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField bookTitleTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrowBookScanner dialog = new BorrowBookScanner();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrowBookScanner() {
		setBounds(100, 100, 1000, 710);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);		
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		contentPanel.setLayout(null);
		
		JLabel lblBookInformation = new JLabel("Book Information");
		inter_bold.applyFont(lblBookInformation, 19f, Color.BLACK);
		lblBookInformation.setBounds(560, 50, 235, 28);
		contentPanel.add(lblBookInformation);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		inter_medium.applyFont(lblBookTitle, 15f, Color.BLACK);
		lblBookTitle.setBounds(585, 111, 104, 20);
		contentPanel.add(lblBookTitle);
		
		bookTitleTxt = new RoundedTextField(10);
		bookTitleTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(699, 101, 261, 40);
		bookTitleTxt.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(bookTitleTxt, 15f, Color.DARK_GRAY);
		bookTitleTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookTitleTxt.setText("Jujutsu Kaisen");
		contentPanel.add(bookTitleTxt);
		bookTitleTxt.setColumns(10);
		
		JLabel lblBookAuthor = new JLabel("Book Author:");
		inter_medium.applyFont(lblBookAuthor, 15f, Color.BLACK);
		lblBookAuthor.setBounds(585, 172, 104, 20);
		contentPanel.add(lblBookAuthor);
		
		RoundedTextField bookAuthorTxt = new RoundedTextField(10);
		inter_regular.applyFont(bookAuthorTxt, 15f, Color.DARK_GRAY);
		bookAuthorTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setColumns(10);
		bookAuthorTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookAuthorTxt.setBackground(new Color(242, 242, 242));
		bookAuthorTxt.setBounds(699, 162, 261, 40);
		bookAuthorTxt.setText("Gege Akutami");
		contentPanel.add(bookAuthorTxt);
		
		JLabel lblCategory = new JLabel("Category:");
		inter_medium.applyFont(lblCategory, 15f, Color.BLACK);
		lblCategory.setBounds(585, 235, 104, 20);
		contentPanel.add(lblCategory);
		
		RoundedTextField bookCategoryTxt = new RoundedTextField(10);
		bookCategoryTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookCategoryTxt.setEditable(false);
		bookCategoryTxt.setColumns(10);
		inter_regular.applyFont(bookCategoryTxt, 15f, Color.DARK_GRAY);
		bookCategoryTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookCategoryTxt.setBackground(new Color(242, 242, 242));
		bookCategoryTxt.setBounds(699, 225, 261, 40);
		bookCategoryTxt.setText("Foreign");
		contentPanel.add(bookCategoryTxt);
		
		JLabel lblBorrowersInformation = new JLabel("Borrower's Information");
		inter_bold.applyFont(lblBorrowersInformation, 19f, Color.BLACK);
		lblBorrowersInformation.setBounds(560, 295, 235, 28);
		contentPanel.add(lblBorrowersInformation);
		
		JLabel lblName = new JLabel("Name:");
		inter_medium.applyFont(lblName, 15f, Color.BLACK);
		lblName.setBounds(585, 356, 104, 20);
		contentPanel.add(lblName);
		
		RoundedTextField borrowersNameTxt = new RoundedTextField(10);
		borrowersNameTxt.setPlaceholder("Surname, Firstname, M.I.");
		borrowersNameTxt.setColumns(10);
		borrowersNameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		borrowersNameTxt.setBackground(new Color(242, 242, 242));
		borrowersNameTxt.setBounds(699, 346, 261, 40);
		inter_regular.applyFont(borrowersNameTxt, 15f, Color.BLACK);
		contentPanel.add(borrowersNameTxt);
		
		JLabel lblGender = new JLabel("Gender:");
		inter_medium.applyFont(lblGender, 15f, Color.BLACK);
		lblGender.setBounds(585, 417, 104, 20);
		contentPanel.add(lblGender);
		
		JLabel lblDepartment = new JLabel("Department:");
		inter_medium.applyFont(lblDepartment, 15f, Color.BLACK);
		lblDepartment.setBounds(585, 480, 104, 20);
		contentPanel.add(lblDepartment);
		
		JComboBox genderCb = new JComboBox();
		genderCb.addItem("Male");
		genderCb.addItem("Female");
		genderCb.setBackground(Color.decode("#F2F2F2"));
		genderCb.setSelectedItem(null);
		genderCb.setFocusable(false);
		genderCb.setBackground(new Color(242, 242, 242));
		genderCb.setBounds(699, 417, 261, 29);
		inter_regular.applyFont(genderCb, 15f, Color.BLACK);
		contentPanel.add(genderCb);
		
		JComboBox departmentCb = new JComboBox();
		departmentCb.addItem("CITCS");
		departmentCb.addItem("CAS");
		departmentCb.addItem("CCJ");
		departmentCb.setFocusable(false);
		departmentCb.setSelectedItem(null);
		departmentCb.setBackground(new Color(242, 242, 242));
		departmentCb.setBounds(699, 479, 261, 30);
		inter_regular.applyFont(departmentCb, 15f, Color.BLACK);
		contentPanel.add(departmentCb);
		
		// Barcode Scanner 
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(57, 370, 115, 1);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(238, 370, 115, 1);
		contentPanel.add(separator_1);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		inter_regular.applyFont(lblOr, 24f, Color.BLACK);
		lblOr.setBounds(182, 356, 46, 28);
		contentPanel.add(lblOr);
		
		JLabel lblEnterCode = new JLabel("Enter code:");
		inter_medium.applyFont(lblEnterCode, 20f, Color.BLACK);
		lblEnterCode.setBounds(57, 426, 136, 23);
		contentPanel.add(lblEnterCode);
		
		RoundedTextField bookBarcodeTxt = new RoundedTextField(10);
		inter_regular.applyFont(bookBarcodeTxt, 15f, Color.BLACK);
		bookBarcodeTxt.setColumns(10);
		bookBarcodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookBarcodeTxt.setBackground(new Color(242, 242, 242));
		bookBarcodeTxt.setBounds(57, 460, 196, 40);
		contentPanel.add(bookBarcodeTxt);
		
		RoundedButton enterBtn = new RoundedButton("ENTER", 15, new Color(0, 96, 204));
		inter_bold.applyFont(enterBtn, 15f, Color.WHITE);
		enterBtn.setBounds(258, 459, 95, 43);
		contentPanel.add(enterBtn);
		
		RoundedButton borrowBookBtn = new RoundedButton("BORROW BOOK", 15, Color.decode("#2DC653"));
		inter_bold.applyFont(borrowBookBtn, 18f, Color.WHITE);
		borrowBookBtn.setBounds(560, 574, 196, 55);
		contentPanel.add(borrowBookBtn);
		
		RoundedButton cancelBtn = new RoundedButton("CANCEL", 15, Color.decode("#E74343"));
		inter_bold.applyFont(cancelBtn, 18f, Color.WHITE);
		cancelBtn.setBounds(766, 574, 194, 55);
		contentPanel.add(cancelBtn);
		
	}
}
