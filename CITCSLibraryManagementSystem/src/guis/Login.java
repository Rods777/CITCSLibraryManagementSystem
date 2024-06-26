package guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.GradientPanel;
import inheritances.ModelColor;
import inheritances.RoundedButton;
import inheritances.RoundedPasswordField;
import inheritances.RoundedTextField;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;

public class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private GradientPanel bgPanel;
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField staffIDTxt;
	private RoundedPasswordField passwordTxt;
	private RoundedButton LOGIN_BUTTON;
	public DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt;
	public ResultSet rs;
	
	JCheckBox SHOWPASS;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		connect.Connect(); //database connector
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 680);
		setLocationRelativeTo(null);
		
		// Gradient Background Panel
		bgPanel = new GradientPanel();
		bgPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgPanel.addColor(new ModelColor(CommonConstants.GRADIENT_START, 0f), new ModelColor(CommonConstants.GRADIENT_MID, 0.59f), new ModelColor(CommonConstants.GRADIENT_END, 1f));
		setContentPane(bgPanel);
		bgPanel.setLayout(null);
		
		// Logos
		JLabel CITCS_logo = new JLabel();
		CITCS_logo.setIcon(new ImageIcon(Login.class.getResource("/img/citcs_logo.png")));
		CITCS_logo.setBounds(351, 172, 70, 62);
		bgPanel.add(CITCS_logo);
		
		JLabel PLMUN_Logo = new JLabel();
		PLMUN_Logo.setIcon(new ImageIcon(Login.class.getResource("/img/plmun_logo.png")));
		PLMUN_Logo.setBounds(81, 181, 53, 53);
		bgPanel.add(PLMUN_Logo);
		
		JLabel PLMUN_CITCS = new JLabel("PLMUN - CITCS");
		inter_extrabold.applyFont(PLMUN_CITCS, 26F, Color.white);
		PLMUN_CITCS.setBounds(149, 178, 205, 53);
		bgPanel.add(PLMUN_CITCS);
		
		JLabel LIBRARY = new JLabel("LIBRARY");
		LIBRARY.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(LIBRARY, 55F, Color.white);
		LIBRARY.setBounds(121, 236, 256, 95);
		bgPanel.add(LIBRARY);
		
		JLabel ASSISTANT = new JLabel("ASSISTANT");
		ASSISTANT.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(ASSISTANT, 55F, Color.white);
		ASSISTANT.setBounds(29, 300, 426, 95);
		bgPanel.add(ASSISTANT);
		
		JLabel TOOL = new JLabel("TOOL");
		TOOL.setHorizontalAlignment(SwingConstants.CENTER);
		inter_black.applyFont(TOOL, 55F, Color.white);
		TOOL.setBounds(121, 364, 256, 95);
		bgPanel.add(TOOL);
		
		//Login Panel and Contents
		JPanel LOGIN_PANEL = new JPanel();
		LOGIN_PANEL.setBackground(Color.WHITE);
		LOGIN_PANEL.setBounds(502, 15, 458, 600);
		bgPanel.add(LOGIN_PANEL);
		LOGIN_PANEL.setLayout(null);
		
		JLabel Login = new JLabel("Login");
		Login.setHorizontalAlignment(SwingConstants.CENTER);
		Login.setBounds(168, 58, 140, 55);
		inter_bold.applyFont(Login, 40F, Color.black);
		LOGIN_PANEL.add(Login);
		
		JLabel STAFFID = new JLabel("Staff ID");
		STAFFID.setBounds(57, 166, 130, 20);
		inter_bold.applyFont(STAFFID, 20F, Color.black);
		LOGIN_PANEL.add(STAFFID);
		
		staffIDTxt = new RoundedTextField(15);
		staffIDTxt.setBackground(Color.decode("#F2F2F2"));
		staffIDTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		staffIDTxt.setBounds(57, 197, 344, 50);
		inter_regular.applyFont(staffIDTxt, 20F, Color.BLACK);
		LOGIN_PANEL.add(staffIDTxt);
		staffIDTxt.setColumns(10);
		
		JLabel PASSWORD = new JLabel("Password");
		PASSWORD.setBounds(57, 266, 130, 20);
		inter_bold.applyFont(PASSWORD, 20F, Color.black);
		LOGIN_PANEL.add(PASSWORD);
				
		passwordTxt = new RoundedPasswordField(15);
		passwordTxt.setBackground(Color.decode("#F2F2F2"));
		passwordTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		passwordTxt.setBounds(57, 297, 344, 50);
		inter_regular.applyFont(passwordTxt, 20F, Color.BLACK);
		LOGIN_PANEL.add(passwordTxt);
		
		SHOWPASS = new JCheckBox("Show Password");
		SHOWPASS.setFocusable(false);
		SHOWPASS.setOpaque(false);
		SHOWPASS.setBounds(57, 354, 150, 25);
		inter_regular.applyFont(SHOWPASS, 14F, Color.black);
		LOGIN_PANEL.add(SHOWPASS);
		SHOWPASS.addActionListener(this);
		
		 LOGIN_BUTTON = new RoundedButton("LOGIN", 15, Color.decode("#195B29"));
		LOGIN_BUTTON.setBounds(102, 418, 265, 55);
		inter_bold.applyFont(LOGIN_BUTTON, 26F, Color.white);
		LOGIN_BUTTON.addActionListener(this);
		LOGIN_PANEL.add(LOGIN_BUTTON);
		
		JLabel DONTHAVEACCOUNT = new JLabel("Don't Have an Account?");
		DONTHAVEACCOUNT.setBounds(87, 545, 200, 20);
		inter_regular.applyFont(DONTHAVEACCOUNT, 16F, Color.black);
		LOGIN_PANEL.add(DONTHAVEACCOUNT);
		
		JLabel SIGN_UP_HERE = new JLabel("Sign Up Here!");
		SIGN_UP_HERE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SIGN_UP_HERE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signup signup = new Signup();
				signup.setVisible(true);
				signup.setLocationRelativeTo(null);
				dispose();
			}
		});
		SIGN_UP_HERE.setBounds(282, 545, 120, 20);
		inter_bold.applyFont(SIGN_UP_HERE, 16F, Color.black);
		LOGIN_PANEL.add(SIGN_UP_HERE);
	}
	
	// Functionalities for buttons
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Shows password
		if(e.getSource() == SHOWPASS) {
			if (SHOWPASS.isSelected()) {
				passwordTxt.setEchoChar((char)0);
			} else {
				passwordTxt.setEchoChar('\u2022');
			}
		}
		//action listener for button 
		if(e.getSource()==LOGIN_BUTTON) {
		  String staffId = staffIDTxt.getText();
		  String password = String.valueOf(passwordTxt.getPassword());
		  //error handler if fields are empty
		if(staffId.isEmpty()|| password.isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			//database stuffs
			try {
					int staffID = Integer.parseInt(staffId);
					
					try {
						prep_stmt = connect.conn.prepareStatement(
			                    "SELECT * FROM librarians WHERE librarian_staffID = ?");
			            prep_stmt.setInt(1, staffID);
			            ResultSet rs = prep_stmt.executeQuery();
			            if (!rs.next()) {
			            	//error handler for staff id
							JOptionPane.showInternalMessageDialog(null, "Staff ID doesn't Exist, Please Sign Up first!", "Error" ,JOptionPane.ERROR_MESSAGE);
			            } else {
			            	String libPass = rs.getString("librarian_password");
			            	if(!libPass.equals(password)) {
			            		JOptionPane.showInternalMessageDialog(null, "Wrong Password!", "Error" ,JOptionPane.ERROR_MESSAGE);
			            	} else {
				            	JOptionPane.showMessageDialog(null, "Login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				                MainApp framer = new MainApp();
				                framer.setVisible(true);
				                framer.setLocationRelativeTo(null);
				                dispose();
			            	}
						}
			            
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Invalid Staff ID", "Input Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		}
		
		
	}
}

	
			
		
			
		
		 
		
		
	
		
	




		
	
	

