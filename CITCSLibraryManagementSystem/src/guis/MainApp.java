package guis;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import javax.swing.JLabel;

public class MainApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private GradientPanel bgPanel;
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf"); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
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
	public MainApp() {
		setTitle("CITCS Library Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 730);
		setLocationRelativeTo(null);
		
		// Gradient Background Panel
		bgPanel = new GradientPanel();
		bgPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgPanel.addColor(new ModelColor(CommonConstants.GRADIENT_START, 0f), new ModelColor(CommonConstants.GRADIENT_MID, 0.59f), new ModelColor(CommonConstants.GRADIENT_END, 1f));
		setContentPane(bgPanel);
		bgPanel.setLayout(null);
		
		// Log out label
		JLabel logoutLbl = new JLabel("Log out");
		logoutLbl.setBounds(45, 618, 106, 45);
		inter_bold.applyFont(logoutLbl, 24, Color.WHITE);
		bgPanel.add(logoutLbl);
		
		
	}
}
