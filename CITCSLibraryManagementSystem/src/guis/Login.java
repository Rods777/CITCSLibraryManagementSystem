package guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private GradientPanel bgPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
		
		// Gradient Background Panel
		bgPanel = new GradientPanel();
		bgPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgPanel.addColor(new ModelColor(CommonConstants.GRADIENT_START, 0f), new ModelColor(CommonConstants.GRADIENT_MID, 0.59f), new ModelColor(CommonConstants.GRADIENT_END, 1f));
		setContentPane(bgPanel);
		bgPanel.setLayout(null);
		
		// Logos
		JLabel CITCS_logo = new JLabel();
		CITCS_logo.setIcon(new ImageIcon(Login.class.getResource("/img/citcs_logo.png")));
		CITCS_logo.setBounds(104, 106, 70, 62);
		bgPanel.add(CITCS_logo);
		
		JLabel PLMUN_Logo = new JLabel();
		PLMUN_Logo.setIcon(new ImageIcon(Login.class.getResource("/img/plmun_logo.png")));
		PLMUN_Logo.setBounds(236, 111, 53, 53);
		bgPanel.add(PLMUN_Logo);
		
		
		
		
	}
}
