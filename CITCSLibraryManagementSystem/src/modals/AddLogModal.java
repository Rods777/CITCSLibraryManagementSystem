package modals;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddLogModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField nameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddLogModal dialog = new AddLogModal();
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
	public AddLogModal() {
		setBounds(100, 100, 550, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		contentPanel.setLayout(null);
		
		JLabel addLog = new JLabel("Add Log");
		addLog.setBounds(43, 39, 159, 59);
		inter_extrabold.applyFont(addLog, 36f, Color.decode("#14412D"));
		contentPanel.add(addLog);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(27, 120, 480, 1);
		contentPanel.add(separator);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(43, 160, 102, 14);
		inter_bold.applyFont(name, 16f, Color.BLACK);
		contentPanel.add(name);
		
		nameTxt = new RoundedTextField(30);
		nameTxt.setPlaceholder("Surname, Firstname, M.I.");
		inter_regular.applyFont(nameTxt, 16f, Color.BLACK);
		nameTxt.setBounds(190, 147, 312, 40);
		nameTxt.setBackground(Color.decode("#F2F2F2"));
		nameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		contentPanel.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel gender = new JLabel("Gender:");
		gender.setBounds(43, 220, 102, 22);
		inter_bold.applyFont(gender, 16f, Color.BLACK);
		contentPanel.add(gender);
		
		JComboBox genderCb = new JComboBox();
		genderCb.addItem("Male");
		genderCb.addItem("Female");
		genderCb.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(genderCb, 16f, Color.BLACK);
		genderCb.setSelectedItem(null);
		genderCb.setFocusable(false);
		genderCb.setBounds(190, 216, 312, 30);
		contentPanel.add(genderCb);
		
		JLabel department = new JLabel("Department:");
		department.setBounds(43, 280, 102, 22);
		inter_bold.applyFont(department, 16f, Color.BLACK);
		contentPanel.add(department);
		
		JComboBox departmentCb = new JComboBox();
		departmentCb.addItem("CITCS");
		departmentCb.addItem("CAS");
		departmentCb.addItem("CCJ");
		inter_regular.applyFont(departmentCb, 16f, Color.BLACK);
		departmentCb.setSelectedItem(null);
		departmentCb.setFocusable(false);
		departmentCb.setBackground(Color.decode("#F2F2F2"));
		departmentCb.setBounds(190, 276, 312, 30);
		contentPanel.add(departmentCb);
		
		RoundedButton saveBtn = new RoundedButton("SAVE", 30, CommonConstants.SAVE_BUTTON);
		inter_extrabold.applyFont(saveBtn, 32f, Color.WHITE);
		saveBtn.setBounds(40, 356, 453, 62);
		contentPanel.add(saveBtn);
	}
}
