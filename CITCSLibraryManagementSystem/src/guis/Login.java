package guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private GradientPanel bgPanel;
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private RoundedTextField usernameTxt;
	private RoundedPasswordField passwordTxt;
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
		inter_black.applyFont(LIBRARY, 55F, Color.white);
		LIBRARY.setBounds(126, 238, 256, 95);
		bgPanel.add(LIBRARY);
		
		JLabel MANAGEMENT = new JLabel("MANAGEMENT");
		inter_black.applyFont(MANAGEMENT, 55F, Color.white);
		MANAGEMENT.setBounds(46, 300, 475, 95);
		bgPanel.add(MANAGEMENT);
		
		JLabel SYSTEM = new JLabel("SYSTEM");
		inter_black.applyFont(SYSTEM, 55F, Color.white);
		SYSTEM.setBounds(126, 365, 256, 95);
		bgPanel.add(SYSTEM);
		
		//Login Panel and Contents
		JPanel LOGIN_PANEL = new JPanel();
		LOGIN_PANEL.setBackground(Color.WHITE);
		LOGIN_PANEL.setBounds(502, 15, 458, 600);
		bgPanel.add(LOGIN_PANEL);
		LOGIN_PANEL.setLayout(null);
		
		JLabel Login = new JLabel("Login");
		Login.setHorizontalAlignment(SwingConstants.CENTER);
		Login.setBounds(168, 65, 140, 55);
		inter_bold.applyFont(Login, 40F, Color.black);
		LOGIN_PANEL.add(Login);
		
		JLabel USERNAME = new JLabel("Username");
		USERNAME.setBounds(80, 188, 130, 20);
		inter_bold.applyFont(USERNAME, 20F, Color.black);
		LOGIN_PANEL.add(USERNAME);
		
		usernameTxt = new RoundedTextField(15);
		usernameTxt.setBackground(Color.decode("#F2F2F2"));
		usernameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		usernameTxt.setBounds(80, 218, 310, 50);
		inter_regular.applyFont(usernameTxt, 20F, Color.BLACK);
		LOGIN_PANEL.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel PASSWORD = new JLabel("Password");
		PASSWORD.setBounds(80, 287, 130, 20);
		inter_bold.applyFont(PASSWORD, 20F, Color.black);
		LOGIN_PANEL.add(PASSWORD);
				
		passwordTxt = new RoundedPasswordField(15);
		passwordTxt.setBackground(Color.decode("#F2F2F2"));
		passwordTxt.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		passwordTxt.setBounds(80, 318, 310, 50);
		inter_regular.applyFont(passwordTxt, 20F, Color.BLACK);
		LOGIN_PANEL.add(passwordTxt);
		
		SHOWPASS = new JCheckBox("Show Password");
		SHOWPASS.setFocusable(false);
		SHOWPASS.setOpaque(false);
		SHOWPASS.setBounds(80, 375, 150, 25);
		inter_regular.applyFont(SHOWPASS, 14F, Color.black);
		LOGIN_PANEL.add(SHOWPASS);
		SHOWPASS.addActionListener(this);
		
		RoundedButton LOGIN_BUTTON = new RoundedButton("LOGIN", 15, Color.decode("#195B29"));
		LOGIN_BUTTON.setBounds(102, 443, 265, 55);
		inter_bold.applyFont(LOGIN_BUTTON, 26F, Color.white);
		LOGIN_PANEL.add(LOGIN_BUTTON);
		
		JLabel DONTHAVEACCOUNT = new JLabel("Don't Have an Account?");
		DONTHAVEACCOUNT.setBounds(87, 545, 200, 20);
		inter_regular.applyFont(DONTHAVEACCOUNT, 16F, Color.black);
		LOGIN_PANEL.add(DONTHAVEACCOUNT);
		
		JLabel SIGN_UP_HERE = new JLabel("Sign Up Here!");
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
		
		
	}
}
