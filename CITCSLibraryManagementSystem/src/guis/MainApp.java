package guis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.CommonConstants;
import db.DBConnection;
import inheritances.FontLoader;
import inheritances.GradientPanel;
import inheritances.ModelColor;
import inheritances.RoundedPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class MainApp extends JFrame implements MouseListener {
	
	private static MainApp instance;

	private static final long serialVersionUID = 1L;
	private GradientPanel bgPanel;
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private FontLoader inter_extrabold = new FontLoader("/fonts/Inter-ExtraBold.ttf");
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
	private JLabel lblNoOfOverdue;
	
	private DashboardPanel homeDashboardPanel;
	private BookListPanel bookListPanel;
	private OverdueBooksPanel overdueBooksPanel;
	private BorrowedBooksPanel borrowedBooksPanel;
	private ReturnedBooksPanel returnedBooksPanel;
	private RoundedPanel overdueNotif;
	
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
		if (instance == null) {
            instance = this;
        }
		connect.Connect();
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
		homeDashboardPanel = new DashboardPanel();
		contentPanel.add(homeDashboardPanel, "homeDashboardPanel");
		
		// Student's Log Panel
		StudentsLogPanel studentsLogPanel = new StudentsLogPanel();
		contentPanel.add(studentsLogPanel, "studentsLogPanel");
		
		// Book List Panel
		bookListPanel = new BookListPanel();
		contentPanel.add(bookListPanel, "bookListPanel");
		
		// Borrowed Books Panel
		borrowedBooksPanel = new BorrowedBooksPanel();
		contentPanel.add(borrowedBooksPanel, "borrowedBooksPanel");
		
		// Returned Books Panel
		returnedBooksPanel = new ReturnedBooksPanel();
		contentPanel.add(returnedBooksPanel, "returnedBooksPanel");
		
		// Returned Books Panel
		overdueBooksPanel = new OverdueBooksPanel();
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
		
		overdueNotif = new RoundedPanel(50);
		overdueNotif.setVisible(false);
		overdueNotif.setBackground(Color.decode("#E74343"));
		overdueNotif.setBounds(208, 18, 23, 21);
		overdueBooksNav.add(overdueNotif);
		overdueNotif.setLayout(new BorderLayout(0, 0));
		
		lblNoOfOverdue = new JLabel("1");
		inter_extrabold.applyFont(lblNoOfOverdue, 12, Color.WHITE);
		lblNoOfOverdue.setHorizontalAlignment(SwingConstants.CENTER);
		overdueNotif.add(lblNoOfOverdue);
		
		overdueBooks = new JLabel("Overdue Books");
		inter_bold.applyFont(overdueBooks, 20f, Color.WHITE);
		overdueBooks.setBounds(20, 5, 243, 45);
		overdueBooksNav.add(overdueBooks);
		
		// Log out label
		JLabel logoutLbl = new JLabel("Log out");
		logoutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(new JFrame() , "Are you sure you want to Log out?", 
						"Logout",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					dispose();
					JOptionPane.showMessageDialog(new JFrame(), "You logged out Successfully!");
					Login login = new Login();
					login.setVisible(true);
					login.setLocationRelativeTo(null);
				}
			}
		});
		logoutLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutLbl.setBounds(45, 618, 106, 45);
		inter_bold.applyFont(logoutLbl, 24, Color.WHITE);
		bgPanel.add(logoutLbl);
		
		getTotalOverdue();
	}
	
	 public static MainApp getInstance() {
	        return instance;
	 }
	
	public void getTotalOverdue() {
		try {
			int count = 0;
			prep_stmt = connect.conn.prepareStatement("SELECT COUNT(*) as overdueCount FROM borrows WHERE DATE(borrow_dueDate) < CURDATE() AND borrow_status = 'Pending for Return'");
			rs = prep_stmt.executeQuery();
			lblNoOfOverdue.setText("0");
			overdueNotif.setVisible(false);
			if(rs.next()) {
				count = rs.getInt("overdueCount");		
				if(count > 0) {
					overdueNotif.setVisible(true);
					lblNoOfOverdue.setText(String.valueOf(count));
				}else {
					overdueNotif.setVisible(false);
				}
			}
			
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOverdueNotification() {
	    getTotalOverdue(); // Check for overdue books
	}
	
	// Buttons Functionalities
	@Override
	public void mouseClicked(MouseEvent e) {
		// Navigation Buttons
		if(e.getSource() == homeNav) {
			homeDashboardPanel.fetchTotalBooks();
			homeDashboardPanel.fetchAvailableBooks();
			homeDashboardPanel.fetchTotalStudents();
			homeDashboardPanel.fetchTotalBorrowedBooks();
			homeDashboardPanel.fetchTotalReturnedBooks();
			homeDashboardPanel.recentlyBorrowedBooks();
			homeDashboardPanel.recentlyReturnedBooks();
			getTotalOverdue();
			
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
			getTotalOverdue();
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
			bookListPanel.fetchBookData();
			bookListNav.setBackground(Color.WHITE);
			bookList.setForeground(Color.BLACK);
			getTotalOverdue();
			
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
			borrowedBooksPanel.fetchBorrowedBooks();
			borrowedBooksNav.setBackground(Color.WHITE);
			borrowedBooks.setForeground(Color.BLACK);
			getTotalOverdue();
			
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
			returnedBooksPanel.fetchReturnedBooks();
			returnedBooksNav.setBackground(Color.WHITE);
			returnedBooks.setForeground(Color.BLACK);
			getTotalOverdue();
			
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
			overdueBooksPanel.fetchOverdueBooks();
			overdueBooksNav.setBackground(Color.WHITE);
			overdueBooks.setForeground(Color.BLACK);
			getTotalOverdue();
			
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
