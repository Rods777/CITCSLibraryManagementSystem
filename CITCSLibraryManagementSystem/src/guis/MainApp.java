package guis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import inheritances.FontLoader;
import inheritances.GradientPanel;
import inheritances.ModelColor;
import inheritances.RoundedPanel;

import javax.swing.JLabel;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class MainApp extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private GradientPanel bgPanel;
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private CardLayout cardLayout = new CardLayout();
	private RoundedPanel homeNav;
	private RoundedPanel studentLogNav;
	private RoundedPanel bookListNav;
	private RoundedPanel borrowedBooksNav;
	private RoundedPanel returnedBooksNav;
	private RoundedPanel overdueBooksNav; 
	private JPanel contentPanel;
	private JLabel home;
	private JLabel studentsLog;
	private JLabel bookList;
	private JLabel borrowedBooks;
	private JLabel returnedBooks;
	private JLabel overdueBooks;

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
		
		
		// Main Contents
		contentPanel = new JPanel();
		contentPanel.setBounds(292, 7, 953, 682);
		contentPanel.setOpaque(false);
		contentPanel.setLayout(cardLayout);
		bgPanel.add(contentPanel);
		
		// Home Dashboard Panel
		DashboardPanel homeDashboardPanel = new DashboardPanel();
		contentPanel.add(homeDashboardPanel, "homeDashboardPanel");
		
		// Student's Log Panel
		StudentsLogPanel studentsLogPanel = new StudentsLogPanel();
		contentPanel.add(studentsLogPanel, "studentsLogPanel");
		
		// Book List Panel
		BookListPanel bookListPanel = new BookListPanel();
		contentPanel.add(bookListPanel, "bookListPanel");
		
		// Borrowed Books Panel
		BorrowedBooksPanel borrowedBooksPanel = new BorrowedBooksPanel();
		contentPanel.add(borrowedBooksPanel, "borrowedBooksPanel");
		
		// Returned Books Panel
		ReturnedBooksPanel returnedBooksPanel = new ReturnedBooksPanel();
		contentPanel.add(returnedBooksPanel, "returnedBooksPanel");
		
		// Returned Books Panel
		OverdueBooksPanel overdueBooksPanel = new OverdueBooksPanel();
		contentPanel.add(overdueBooksPanel, "overdueBooksPanel");
		
		// System Logo
		JLabel lblSystemLogo = new JLabel("");
		lblSystemLogo.setIcon(new ImageIcon(MainApp.class.getResource("/img/PLMUN CITCS LOGO.png")));
		lblSystemLogo.setBounds(22, 22, 272, 76);
		bgPanel.add(lblSystemLogo);
		
		// Navigation
		homeNav = new RoundedPanel(15);
		homeNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeNav.addMouseListener(this);
		homeNav.setBounds(22, 141, 338, 56);
		homeNav.setBackground(Color.WHITE);
		bgPanel.add(homeNav);
		homeNav.setLayout(null);
		
		home = new JLabel("Home");
		inter_bold.applyFont(home, 20f, Color.BLACK);
		home.setBounds(20, 5, 243, 45);
		homeNav.add(home);
		
		studentLogNav = new RoundedPanel(15);
		studentLogNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		studentLogNav.addMouseListener(this);
		studentLogNav.setBounds(22, 211, 338, 56);
		studentLogNav.setBackground(new Color(0, 0, 0, 0));
		bgPanel.add(studentLogNav);
		studentLogNav.setLayout(null);
		
		studentsLog = new JLabel("Student's Log");
		inter_bold.applyFont(studentsLog, 20f, Color.WHITE);
		studentsLog.setBounds(20, 5, 245, 45);
		studentLogNav.add(studentsLog);
		
		bookListNav = new RoundedPanel(15);
		bookListNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookListNav.addMouseListener(this);
		bookListNav.setBounds(22, 281, 338, 56);
		bookListNav.setBackground(new Color(0, 0, 0, 0));
		bgPanel.add(bookListNav);
		bookListNav.setLayout(null);
		
		bookList = new JLabel("Book List");
		inter_bold.applyFont(bookList, 20f, Color.WHITE);
		bookList.setBounds(20, 5, 241, 45);
		bookListNav.add(bookList);
		
		borrowedBooksNav = new RoundedPanel(15);
		borrowedBooksNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		borrowedBooksNav.addMouseListener(this);
		borrowedBooksNav.setBounds(22, 351, 338, 56);
		borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
		bgPanel.add(borrowedBooksNav);
		borrowedBooksNav.setLayout(null);
		
		borrowedBooks = new JLabel("Borrowed Books");
		inter_bold.applyFont(borrowedBooks, 20f, Color.WHITE);
		borrowedBooks.setBounds(20, 5, 243, 45);
		borrowedBooksNav.add(borrowedBooks);
		
		returnedBooksNav = new RoundedPanel(15);
		returnedBooksNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnedBooksNav.addMouseListener(this);
		returnedBooksNav.setBounds(22, 421, 338, 56);
		returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
		bgPanel.add(returnedBooksNav);
		returnedBooksNav.setLayout(null);
		
		returnedBooks = new JLabel("Returned Books");
		inter_bold.applyFont(returnedBooks, 20f, Color.WHITE);
		returnedBooks.setBounds(20, 5, 243, 45);
		returnedBooksNav.add(returnedBooks);
		
		overdueBooksNav = new RoundedPanel(15);
		overdueBooksNav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		overdueBooksNav.setLayout(null);
		overdueBooksNav.addMouseListener(this);
		overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
		overdueBooksNav.setBounds(22, 488, 338, 56);
		overdueBooksNav.setLayout(null);
		bgPanel.add(overdueBooksNav);
		
		overdueBooks = new JLabel("Overdue Books");
		inter_bold.applyFont(overdueBooks, 20f, Color.WHITE);
		overdueBooks.setBounds(20, 5, 243, 45);
		overdueBooksNav.add(overdueBooks);
		
		// Log out label
		JLabel logoutLbl = new JLabel("Log out");
		logoutLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutLbl.setBounds(45, 618, 106, 45);
		inter_bold.applyFont(logoutLbl, 24, Color.WHITE);
		bgPanel.add(logoutLbl);
	}
	
	// Buttons Functionalities
	@Override
	public void mouseClicked(MouseEvent e) {
		// Navigation Buttons
		if(e.getSource() == homeNav) {
			homeNav.setBackground(Color.WHITE);
			home.setForeground(Color.BLACK);
			
			studentLogNav.setBackground(new Color(0, 0, 0, 0));
			studentsLog.setForeground(Color.WHITE);
			
			bookListNav.setBackground(new Color(0, 0, 0, 0));
			bookList.setForeground(Color.WHITE);
			
			borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
			borrowedBooks.setForeground(Color.WHITE);
			
			returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
			returnedBooks.setForeground(Color.WHITE);
			
			overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
			overdueBooks.setForeground(Color.WHITE);
			
			cardLayout.show(contentPanel, "homeDashboardPanel");
		}
		if(e.getSource() == studentLogNav) {
			studentLogNav.setBackground(Color.WHITE);
			studentsLog.setForeground(Color.BLACK);
			
			homeNav.setBackground(new Color(0, 0, 0, 0));
			home.setForeground(Color.WHITE);
			
			bookListNav.setBackground(new Color(0, 0, 0, 0));
			bookList.setForeground(Color.WHITE);
			
			borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
			borrowedBooks.setForeground(Color.WHITE);
			
			returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
			returnedBooks.setForeground(Color.WHITE);
			
			overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
			overdueBooks.setForeground(Color.WHITE);

			cardLayout.show(contentPanel, "studentsLogPanel");
		}
		if(e.getSource() == bookListNav) {
			bookListNav.setBackground(Color.WHITE);
			bookList.setForeground(Color.BLACK);
			
			studentLogNav.setBackground(new Color(0, 0, 0, 0));
			studentsLog.setForeground(Color.WHITE);
			
			homeNav.setBackground(new Color(0, 0, 0, 0));
			home.setForeground(Color.WHITE);
			
			borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
			borrowedBooks.setForeground(Color.WHITE);
			
			returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
			returnedBooks.setForeground(Color.WHITE);
			
			overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
			overdueBooks.setForeground(Color.WHITE);
			
			cardLayout.show(contentPanel, "bookListPanel");
		}
		if(e.getSource() == borrowedBooksNav) {
			borrowedBooksNav.setBackground(Color.WHITE);
			borrowedBooks.setForeground(Color.BLACK);
			
			studentLogNav.setBackground(new Color(0, 0, 0, 0));
			studentsLog.setForeground(Color.WHITE);
			
			bookListNav.setBackground(new Color(0, 0, 0, 0));
			bookList.setForeground(Color.WHITE);
			
			homeNav.setBackground(new Color(0, 0, 0, 0));
			home.setForeground(Color.WHITE);
			
			returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
			returnedBooks.setForeground(Color.WHITE);
			
			overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
			overdueBooks.setForeground(Color.WHITE);
			
			cardLayout.show(contentPanel, "borrowedBooksPanel");
		}
		if(e.getSource() == returnedBooksNav) {
			returnedBooksNav.setBackground(Color.WHITE);
			returnedBooks.setForeground(Color.BLACK);
			
			studentLogNav.setBackground(new Color(0, 0, 0, 0));
			studentsLog.setForeground(Color.WHITE);
			
			bookListNav.setBackground(new Color(0, 0, 0, 0));
			bookList.setForeground(Color.WHITE);
			
			borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
			borrowedBooks.setForeground(Color.WHITE);
			
			homeNav.setBackground(new Color(0, 0, 0, 0));
			home.setForeground(Color.WHITE);
			
			overdueBooksNav.setBackground(new Color(0, 0, 0, 0));
			overdueBooks.setForeground(Color.WHITE);
			
			cardLayout.show(contentPanel, "returnedBooksPanel");
		}	
		if(e.getSource() == overdueBooksNav) {
			overdueBooksNav.setBackground(Color.WHITE);
			overdueBooks.setForeground(Color.BLACK);
			
			studentLogNav.setBackground(new Color(0, 0, 0, 0));
			studentsLog.setForeground(Color.WHITE);
			
			bookListNav.setBackground(new Color(0, 0, 0, 0));
			bookList.setForeground(Color.WHITE);
			
			borrowedBooksNav.setBackground(new Color(0, 0, 0, 0));
			borrowedBooks.setForeground(Color.WHITE);
			
			homeNav.setBackground(new Color(0, 0, 0, 0));
			home.setForeground(Color.WHITE);
			
			returnedBooksNav.setBackground(new Color(0, 0, 0, 0));
			returnedBooks.setForeground(Color.WHITE);
			
			cardLayout.show(contentPanel, "overdueBooksPanel");
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
