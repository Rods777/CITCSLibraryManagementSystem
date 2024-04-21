package guis;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private GradientPanel bgPanel;
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private JTextField textField;
	private JPasswordField passwordField;
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
		CITCS_logo.setBounds(375, 172, 70, 62);
		bgPanel.add(CITCS_logo);
		
		JLabel PLMUN_Logo = new JLabel();
		PLMUN_Logo.setIcon(new ImageIcon(Login.class.getResource("/img/plmun_logo.png")));
		PLMUN_Logo.setBounds(105, 181, 53, 53);
		bgPanel.add(PLMUN_Logo);
		
		JLabel PLMUN_CITCS = new JLabel("PLMUN - CITCS");
		inter_extrabold.applyFont(PLMUN_CITCS, 26F, Color.white);
		PLMUN_CITCS.setBounds(173, 178, 205, 53);
		bgPanel.add(PLMUN_CITCS);
		
		JLabel LIBRARY = new JLabel("LIBRARY");
		inter_black.applyFont(LIBRARY, 55F, Color.white);
		LIBRARY.setBounds(150, 238, 256, 95);
		bgPanel.add(LIBRARY);
		
		JLabel MANAGEMENT = new JLabel("MANAGEMENT");
		inter_black.applyFont(MANAGEMENT, 55F, Color.white);
		MANAGEMENT.setBounds(70, 300, 475, 95);
		bgPanel.add(MANAGEMENT);
		
		JLabel SYSTEM = new JLabel("SYSTEM");
		inter_black.applyFont(SYSTEM, 55F, Color.white);
		SYSTEM.setBounds(150, 365, 256, 95);
		bgPanel.add(SYSTEM);
		
		//Login Panel and Contents
		JPanel LOGIN_PANEL = new JPanel();
		LOGIN_PANEL.setBounds(520, 15, 440, 600);
		bgPanel.add(LOGIN_PANEL);
		LOGIN_PANEL.setLayout(null);
		
		JLabel Login = new JLabel("Login");
		Login.setBounds(165, 80, 140, 55);
		inter_bold.applyFont(Login, 40F, Color.black);
		LOGIN_PANEL.add(Login);
		
		JLabel USERNAME = new JLabel("Username");
		USERNAME.setBounds(70, 200, 130, 20);
		inter_bold.applyFont(USERNAME, 20F, Color.black);
		LOGIN_PANEL.add(USERNAME);
		
		textField = new JTextField();
		textField.setBounds(69, 230, 310, 50);
		LOGIN_PANEL.add(textField);
		textField.setColumns(10);
		
		JLabel PASSWORD = new JLabel("Password");
		PASSWORD.setBounds(70, 300, 130, 20);
		inter_bold.applyFont(PASSWORD, 20F, Color.black);
		LOGIN_PANEL.add(PASSWORD);
				
		passwordField = new JPasswordField();
		passwordField.setBounds(70, 330, 310, 50);
		LOGIN_PANEL.add(passwordField);
		
		JCheckBox SHOWPASS = new JCheckBox("Show Password");
		SHOWPASS.setBounds(70, 385, 150, 25);
		inter_regular.applyFont(SHOWPASS, 14F, Color.black);
		LOGIN_PANEL.add(SHOWPASS);
		
		JButton LOGIN_BUTTON = new JButton("LOGIN");
		LOGIN_BUTTON.setBounds(95, 460, 265, 50);
		inter_bold.applyFont(LOGIN_BUTTON, 28F, Color.white);
		LOGIN_PANEL.add(LOGIN_BUTTON);
		
		JLabel DONTHAVEACCOUNT = new JLabel("Don't Have an Account?");
		DONTHAVEACCOUNT.setBounds(55, 545, 200, 20);
		inter_regular.applyFont(DONTHAVEACCOUNT, 16F, Color.black);
		LOGIN_PANEL.add(DONTHAVEACCOUNT);
		
		JLabel SIGN_UP_HERE = new JLabel("Sign Up Here!");
		SIGN_UP_HERE.setBounds(250, 545, 120, 20);
		inter_bold.applyFont(SIGN_UP_HERE, 16F, Color.black);
		LOGIN_PANEL.add(SIGN_UP_HERE);
		
		
	}
}
