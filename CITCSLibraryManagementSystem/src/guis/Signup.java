package guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.GradientPanel;
import inheritances.ModelColor;
import inheritances.RoundedButton;
import inheritances.RoundedPasswordField;
import inheritances.RoundedTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;

public class Signup extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private GradientPanel bgPanel;
	private RoundedTextField librarianNameTxt, staffIdTxt;
	private JPasswordField prepasswordTxt, passwordTxt;
	private RoundedButton SIGNUP_BUTTON;
	private JCheckBox SHOWPASS;
	
	private DBConnection connect = new DBConnection();
	
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Signup() {
		connect.Connect(); // Connecting to database
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 680);
	    setLocationRelativeTo(null);
	    
	    // Gradient panel
	    
		bgPanel = new GradientPanel();
		bgPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgPanel.addColor(new ModelColor(CommonConstants.GRADIENT_START, 0f), new ModelColor(CommonConstants.GRADIENT_MID, 0.59f), new ModelColor(CommonConstants.GRADIENT_END, 1f));
		setContentPane(bgPanel);
		bgPanel.setLayout(null);
		
		
		
		
		// Components for the title 
		JLabel CITCS_logo = new JLabel();
		CITCS_logo.setIcon(new ImageIcon(Login.class.getResource("/img/citcs_logo.png")));
		CITCS_logo.setBounds(366, 172, 70, 62);
		bgPanel.add(CITCS_logo);
		
		JLabel PLMUN_Logo = new JLabel();
		PLMUN_Logo.setIcon(new ImageIcon(Login.class.getResource("/img/plmun_logo.png")));
		PLMUN_Logo.setBounds(96, 181, 53, 53);
		bgPanel.add(PLMUN_Logo);
		
		JLabel PLMUN_CITCS = new JLabel("PLMUN - CITCS");
		inter_extrabold.applyFont(PLMUN_CITCS, 26F, Color.white);
		PLMUN_CITCS.setBounds(164, 178, 205, 53);
		bgPanel.add(PLMUN_CITCS);
		
		JLabel LIBRARY = new JLabel("LIBRARY");
		LIBRARY.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(LIBRARY, 55F, Color.white);
		LIBRARY.setBounds(130, 242, 256, 95);
		bgPanel.add(LIBRARY);
		
		JLabel ASSISTANT = new JLabel("ASSISTANT");
		ASSISTANT.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(ASSISTANT, 55F, Color.white);
		ASSISTANT.setBounds(38, 306, 426, 95);
		bgPanel.add(ASSISTANT);
		
		JLabel TOOL = new JLabel("TOOL");
		TOOL.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(TOOL, 55F, Color.white);
		TOOL.setBounds(130, 370, 256, 95);
		bgPanel.add(TOOL);
		
		// sign up panel and contents
		JPanel SIGNUP_PANEL = new JPanel();
		SIGNUP_PANEL.setBounds(520, 15, 440, 600);
		SIGNUP_PANEL.setBackground(Color.WHITE);
		bgPanel.add(SIGNUP_PANEL);
		SIGNUP_PANEL.setLayout(null);
		
		JLabel Signup = new JLabel("Sign Up");
		Signup.setHorizontalAlignment(SwingConstants.CENTER);
		Signup.setBounds(159, 11, 137, 55);
		inter_bold.applyFont(Signup, 35F, Color.black);
		SIGNUP_PANEL.add(Signup);
		
		// Textfields
		JLabel LIBRARIAN_NAME = new JLabel("Librarian Name");
		LIBRARIAN_NAME.setBounds(55, 93, 130, 20);
		inter_bold.applyFont(LIBRARIAN_NAME, 17F, Color.black);
		SIGNUP_PANEL.add(LIBRARIAN_NAME);
		
		librarianNameTxt = new RoundedTextField(15);
		librarianNameTxt.setPlaceholder("Surname, Firstname, M.I.");
		librarianNameTxt.setBackground(Color.decode("#F2F2F2"));
		librarianNameTxt.setBounds(55, 120, 344, 40);
		librarianNameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		SIGNUP_PANEL.add(librarianNameTxt);
		librarianNameTxt.setColumns(10);
		inter_regular.applyFont(librarianNameTxt, 17F, Color.black); 
		
		JLabel STAFF_ID = new JLabel("Staff ID");
		STAFF_ID.setBounds(55, 170, 130, 20);
		inter_bold.applyFont(STAFF_ID, 17F, Color.black);
		SIGNUP_PANEL.add(STAFF_ID);
		
		staffIdTxt = new RoundedTextField(15);
		staffIdTxt.setColumns(10);
		staffIdTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		staffIdTxt.setBackground(new Color(242, 242, 242));
		staffIdTxt.setBounds(55, 196, 344, 40);
		SIGNUP_PANEL.add(staffIdTxt);
		inter_regular.applyFont(staffIdTxt, 17F, Color.black); 
		
		JLabel PASSWORD = new JLabel("Password");
		PASSWORD.setBounds(55, 242, 130,20);
		inter_bold.applyFont(PASSWORD, 17F, Color.BLACK);
		SIGNUP_PANEL.add(PASSWORD);
		
		prepasswordTxt = new RoundedPasswordField(15);
		prepasswordTxt.setBackground(Color.decode("#F2F2F2"));
		prepasswordTxt.setBounds(55, 271, 344,40);
		prepasswordTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		SIGNUP_PANEL.add(prepasswordTxt);
		prepasswordTxt.setColumns(10);
		inter_regular.applyFont(prepasswordTxt, 17F, Color.black);
		
		JLabel CONFIRM_PASS = new JLabel("Confirm Password");
		CONFIRM_PASS.setBounds(55, 319, 180,20);
		inter_bold.applyFont(CONFIRM_PASS, 17F, Color.BLACK);
		SIGNUP_PANEL.add(CONFIRM_PASS);
			
		passwordTxt = new RoundedPasswordField(15);
		passwordTxt.setBackground(Color.decode("#F2F2F2"));
		passwordTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		passwordTxt.setBounds(55, 346, 344,40);
		SIGNUP_PANEL.add(passwordTxt);
		passwordTxt.setColumns(10);
		inter_regular.applyFont(passwordTxt, 17F, Color.black);
		
		SHOWPASS = new JCheckBox("Show Password");
		SHOWPASS.setFocusable(false);
		SHOWPASS.setBounds(55, 393, 150, 25);
		SHOWPASS.setBackground(Color.WHITE);
		inter_regular.applyFont(SHOWPASS, 14F, Color.black);
		SIGNUP_PANEL.add(SHOWPASS);
		SHOWPASS.addActionListener(this);
		
		SIGNUP_BUTTON = new RoundedButton("SIGN UP",15, Color.decode("#195B29"));
		SIGNUP_BUTTON.addActionListener(this);
		SIGNUP_BUTTON.setBounds(95, 446, 265, 55);
		inter_bold.applyFont(SIGNUP_BUTTON, 28F, Color.white);
		SIGNUP_PANEL.add(SIGNUP_BUTTON);
		
		JLabel ALREADYHAVE = new JLabel("Already have an Account?");
		ALREADYHAVE.setBounds(55, 545, 200, 20);
		inter_regular.applyFont(ALREADYHAVE, 16F, Color.black);
		SIGNUP_PANEL.add(ALREADYHAVE);
		
		JLabel LOGIN_HERE = new JLabel("Sign In Here!");
		LOGIN_HERE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LOGIN_HERE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		LOGIN_HERE.setBounds(263, 545, 120, 20);
		inter_bold.applyFont(LOGIN_HERE, 16F, Color.black);
		SIGNUP_PANEL.add(LOGIN_HERE);

	}
	
	// Functionalities for buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Shows password
		if(e.getSource() == SHOWPASS) {
			if (SHOWPASS.isSelected()) {
				prepasswordTxt.setEchoChar((char)0);
				passwordTxt.setEchoChar((char)0);
			} else {
				prepasswordTxt.setEchoChar('\u2022');
				passwordTxt.setEchoChar('\u2022');
			}
		}
		//librarianNameTxt, staffIdTxt;
		//prepasswordTxt, passwordTxt;
		
		// Sign-up Button
		if(e.getSource() == SIGNUP_BUTTON) {
			String name = librarianNameTxt.getText();
			String staffIDstr = staffIdTxt.getText();
			String prePassword = new String(prepasswordTxt.getPassword());
			String password = new String(passwordTxt.getPassword());
			if (name.equals("") || staffIDstr.equals("") || prePassword.equals("") || password.equals("")) {
			    JOptionPane.showMessageDialog(null, "Please fill out all fields", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if (!password.equals(prePassword)) {
			    JOptionPane.showMessageDialog(null, "Passwords do not match", "Alert", JOptionPane.WARNING_MESSAGE);
			} else {
			    // Checks if the staff ID is a valid integer
			    try {
			        int staffID = Integer.parseInt(staffIDstr);
			        try {
			            prep_stmt = connect.conn.prepareStatement(
			                    "SELECT librarian_name, librarian_staffID FROM librarians WHERE librarian_name = ? OR librarian_staffID = ?");
			            prep_stmt.setString(1, name);
			            prep_stmt.setInt(2, staffID);
			            ResultSet rs = prep_stmt.executeQuery();
			            if (rs.next()) {
			                JOptionPane.showMessageDialog(null, "Name or Staff ID already exists!", "Alert", JOptionPane.WARNING_MESSAGE);
			            } else {
			                // Insert data into the database as staff ID is valid and does not exist
			                prep_stmt = connect.conn.prepareStatement(
			                        "INSERT INTO librarians (librarian_name, librarian_staffID, librarian_password) VALUES (?, ?, ?)");
			                prep_stmt.setString(1, name);
			                prep_stmt.setInt(2, staffID);
			                prep_stmt.setString(3, password);
			                int row = prep_stmt.executeUpdate();
			                // Checks if data inserted to database
							if(row == 1) {
								JOptionPane.showMessageDialog(null, "New librarian added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
								Login frame = new Login();
								frame.setVisible(true);
								frame.setLocationRelativeTo(null);
								dispose();
							} else {
								JOptionPane.showMessageDialog(new JFrame(),
										"Signin Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
							}
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Staff ID must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		}
		
		
	}
}
