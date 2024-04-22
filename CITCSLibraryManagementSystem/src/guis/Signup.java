package guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.GradientPanel;
import inheritances.ModelColor;
import inheritances.RoundedButton;
import inheritances.RoundedPasswordField;
import inheritances.RoundedTextField;

public class Signup extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private GradientPanel bgPanel;
	private RoundedTextField textField;
	private JPasswordField passField;
	private JPasswordField confirmPass;
	
	JCheckBox SHOWPASS;
	

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
		
		// sign up panel and contents
		JPanel SIGNUP_PANEL = new JPanel();
		SIGNUP_PANEL.setBounds(520, 15, 440, 600);
		SIGNUP_PANEL.setBackground(Color.WHITE);
		bgPanel.add(SIGNUP_PANEL);
		SIGNUP_PANEL.setLayout(null);
		
		JLabel Signup = new JLabel("Sign Up");
		Signup.setBounds(147, 10, 170, 55);
		inter_bold.applyFont(Signup, 40F, Color.black);
		SIGNUP_PANEL.add(Signup);
		
		JLabel USERNAME = new JLabel("Username");
		USERNAME.setBounds(70, 103, 130, 20);
		inter_bold.applyFont(USERNAME, 20F, Color.black);
		SIGNUP_PANEL.add(USERNAME);
		
		textField = new RoundedTextField(15);
		textField.setBackground(Color.decode("#F2F2F2"));
		textField.setBounds(69, 130, 310, 50);
		textField.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		SIGNUP_PANEL.add(textField);
		textField.setColumns(10);
		inter_regular.applyFont(textField, 17F, Color.black); 
		
		JLabel PASSWORD = new JLabel("Password");
		PASSWORD.setBounds(70, 202, 130,20);
		inter_bold.applyFont(PASSWORD, 20F, Color.BLACK);
		SIGNUP_PANEL.add(PASSWORD);
		
		passField = new RoundedPasswordField(15);
		passField.setBackground(Color.decode("#F2F2F2"));
		passField.setBounds(69, 230, 310,50);
		passField.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		SIGNUP_PANEL.add(passField);
		passField.setColumns(10);
		inter_regular.applyFont(passField, 17F, Color.black);
		
		JLabel CONFIRM_PASS = new JLabel("Confirm Password");
		CONFIRM_PASS.setBounds(70, 303, 180,20);
		inter_bold.applyFont(CONFIRM_PASS, 20F, Color.BLACK);
		SIGNUP_PANEL.add(CONFIRM_PASS);
			
		confirmPass = new RoundedPasswordField(15);
		confirmPass.setBackground(Color.decode("#F2F2F2"));
		confirmPass.setBorder(new LineBorder(new Color(171, 173, 179), 5));
		confirmPass.setBounds(69, 330, 310,50);
		SIGNUP_PANEL.add(confirmPass);
		confirmPass.setColumns(10);
		inter_regular.applyFont(confirmPass, 17F, Color.black);
		
		SHOWPASS = new JCheckBox("Show Password");
		SHOWPASS.setBounds(70, 394, 150, 25);
		SHOWPASS.setBackground(Color.WHITE);
		inter_regular.applyFont(SHOWPASS, 14F, Color.black);
		SIGNUP_PANEL.add(SHOWPASS);
		SHOWPASS.addActionListener(this);
		
		JButton SIGNUP_BUTTON = new RoundedButton("SIGN UP",15, Color.decode("#195B29"));
		SIGNUP_BUTTON.setBounds(95, 446, 265, 50);
		inter_bold.applyFont(SIGNUP_BUTTON, 28F, Color.white);
		SIGNUP_PANEL.add(SIGNUP_BUTTON);
		
		JLabel ALREADYHAVE = new JLabel("Already have an Account?");
		ALREADYHAVE.setBounds(55, 545, 200, 20);
		inter_regular.applyFont(ALREADYHAVE, 16F, Color.black);
		SIGNUP_PANEL.add(ALREADYHAVE);
		
		JLabel LOGIN_HERE = new JLabel("Sign Up Here!");
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
				passField.setEchoChar((char)0);
				confirmPass.setEchoChar((char)0);
			} else {
				passField.setEchoChar('\u2022');
				confirmPass.setEchoChar('\u2022');
			}
		}
		
		
	}

}
