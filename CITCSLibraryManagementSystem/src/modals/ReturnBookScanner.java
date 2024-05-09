package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import db.DBConnection;
import guis.MainApp;
import inheritances.FontLoader;
import inheritances.RoundedButton;
import inheritances.RoundedTextField;

public class ReturnBookScanner extends JDialog implements ActionListener,Runnable,ThreadFactory{
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_medium = new FontLoader("/fonts/Inter-Medium.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	
	private RoundedTextField studentNumberTxt, bookTitleTxt, bookAuthorTxt, bookCategoryTxt, bookBarcodeTxt;
	private RoundedTextField nameTxt, genderTxt, departmentTxt, dueDateTxt, borrowIDTxt;
	
	private RoundedButton enterBtn, returnBookBtn, cancelBtn;
	
	private Webcam webcam = null;
	private WebcamPanel webcamPanel = null;
	private ExecutorService executor = Executors.newSingleThreadExecutor(this);
	
	private DBConnection connect = new DBConnection();
	public PreparedStatement prep_stmt = null;
	public ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReturnBookScanner dialog = new ReturnBookScanner();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReturnBookScanner() {
		connect.Connect();
		setBounds(100, 100, 1000, 710);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);		
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		contentPanel.setLayout(null);
		
		// Book Information
		JLabel lblBookInformation = new JLabel("Book Information");
		inter_bold.applyFont(lblBookInformation, 19f, Color.BLACK);
		lblBookInformation.setBounds(536, 11, 235, 28);
		contentPanel.add(lblBookInformation);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		inter_medium.applyFont(lblBookTitle, 15f, Color.BLACK);
		lblBookTitle.setBounds(536, 59, 104, 20);
		contentPanel.add(lblBookTitle);
		
		bookTitleTxt = new RoundedTextField(10);
		bookTitleTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(675, 50, 261, 38);
		bookTitleTxt.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(bookTitleTxt, 15f, Color.DARK_GRAY);
		bookTitleTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		contentPanel.add(bookTitleTxt);
		bookTitleTxt.setColumns(10);
		
		JLabel lblBookAuthor = new JLabel("Book Author:");
		inter_medium.applyFont(lblBookAuthor, 15f, Color.BLACK);
		lblBookAuthor.setBounds(536, 108, 104, 20);
		contentPanel.add(lblBookAuthor);
		
		bookAuthorTxt = new RoundedTextField(10);
		inter_regular.applyFont(bookAuthorTxt, 15f, Color.DARK_GRAY);
		bookAuthorTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setColumns(10);
		bookAuthorTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookAuthorTxt.setBackground(new Color(242, 242, 242));
		bookAuthorTxt.setBounds(675, 99, 261, 38);
		contentPanel.add(bookAuthorTxt);
		
		JLabel lblCategory = new JLabel("Category:");
		inter_medium.applyFont(lblCategory, 15f, Color.BLACK);
		lblCategory.setBounds(536, 157, 104, 20);
		contentPanel.add(lblCategory);
		
		bookCategoryTxt = new RoundedTextField(10);
		bookCategoryTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookCategoryTxt.setEditable(false);
		bookCategoryTxt.setColumns(10);
		inter_regular.applyFont(bookCategoryTxt, 15f, Color.DARK_GRAY);
		bookCategoryTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookCategoryTxt.setBackground(new Color(242, 242, 242));
		bookCategoryTxt.setBounds(675, 148, 261, 38);
		contentPanel.add(bookCategoryTxt);
		
		// Borrower's Information
		JLabel lblBorrowersInformation = new JLabel("Borrower's Information");
		inter_bold.applyFont(lblBorrowersInformation, 19f, Color.BLACK);
		lblBorrowersInformation.setBounds(536, 222, 235, 28);
		contentPanel.add(lblBorrowersInformation);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		inter_medium.applyFont(lblStudentNumber, 15f, Color.BLACK);
		lblStudentNumber.setBounds(536, 327, 129, 20);
		contentPanel.add(lblStudentNumber);
		
		studentNumberTxt = new RoundedTextField(10);
		inter_regular.applyFont(studentNumberTxt, 15f, Color.DARK_GRAY);
		studentNumberTxt.setEditable(false);
		studentNumberTxt.setColumns(10);
		studentNumberTxt.setCaretColor(new Color(0, 0, 0, 0));
		studentNumberTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		studentNumberTxt.setBackground(new Color(242, 242, 242));
		studentNumberTxt.setBounds(675, 318, 261, 38);
		contentPanel.add(studentNumberTxt);
		
		JLabel lblName = new JLabel("Name:");
		inter_medium.applyFont(lblName, 15f, Color.BLACK);
		lblName.setBounds(536, 376, 104, 20);
		contentPanel.add(lblName);
		
		JLabel lblGender = new JLabel("Gender:");
		inter_medium.applyFont(lblGender, 15f, Color.BLACK);
		lblGender.setBounds(536, 423, 104, 20);
		contentPanel.add(lblGender);
		
		JLabel lblDepartment = new JLabel("Department:");
		inter_medium.applyFont(lblDepartment, 15f, Color.BLACK);
		lblDepartment.setBounds(536, 472, 104, 20);
		contentPanel.add(lblDepartment);
		
		bookBarcodeTxt = new RoundedTextField(10);
		inter_regular.applyFont(bookBarcodeTxt, 15f, Color.BLACK);
		bookBarcodeTxt.setColumns(10);
		bookBarcodeTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookBarcodeTxt.setBackground(new Color(242, 242, 242));
		bookBarcodeTxt.setBounds(66, 467, 196, 40);
		contentPanel.add(bookBarcodeTxt);
		
		enterBtn = new RoundedButton("ENTER", 15, new Color(0, 96, 204));
		enterBtn.addActionListener(this);
		inter_bold.applyFont(enterBtn, 15f, Color.WHITE);
		enterBtn.setBounds(267, 466, 95, 43);
		contentPanel.add(enterBtn);
		
		returnBookBtn = new RoundedButton("BORROW BOOK", 15, Color.decode("#2DC653"));
		returnBookBtn.setText("RETURN BOOK");
		inter_bold.applyFont(returnBookBtn, 18f, Color.WHITE);
		returnBookBtn.setBounds(536, 590, 196, 55);
		returnBookBtn.addActionListener(this);
		contentPanel.add(returnBookBtn);
		
		cancelBtn = new RoundedButton("CANCEL", 15, Color.decode("#E74343"));
		inter_bold.applyFont(cancelBtn, 18f, Color.WHITE);
		cancelBtn.setBounds(742, 590, 194, 55);
		cancelBtn.addActionListener(this);
		contentPanel.add(cancelBtn);
		
		nameTxt = new RoundedTextField(10);
		inter_regular.applyFont(nameTxt, 15f, Color.DARK_GRAY);
		nameTxt.setEditable(false);
		nameTxt.setColumns(10);
		nameTxt.setCaretColor(new Color(0, 0, 0, 0));
		nameTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		nameTxt.setBackground(new Color(242, 242, 242));
		nameTxt.setBounds(675, 367, 261, 38);
		contentPanel.add(nameTxt);
		
		genderTxt = new RoundedTextField(10);
		inter_regular.applyFont(genderTxt, 15f, Color.DARK_GRAY);
		genderTxt.setEditable(false);
		genderTxt.setColumns(10);
		genderTxt.setCaretColor(new Color(0, 0, 0, 0));
		genderTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		genderTxt.setBackground(new Color(242, 242, 242));
		genderTxt.setBounds(675, 414, 261, 38);
		contentPanel.add(genderTxt);
		
		departmentTxt = new RoundedTextField(10);
		inter_regular.applyFont(departmentTxt, 15f, Color.DARK_GRAY);
		departmentTxt.setEditable(false);
		departmentTxt.setColumns(10);
		departmentTxt.setCaretColor(new Color(0, 0, 0, 0));
		departmentTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		departmentTxt.setBackground(new Color(242, 242, 242));
		departmentTxt.setBounds(675, 463, 261, 38);
		contentPanel.add(departmentTxt);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(536, 521, 104, 20);
		inter_medium.applyFont(lblDueDate, 15f, Color.BLACK);
		contentPanel.add(lblDueDate);
		
		dueDateTxt = new RoundedTextField(10);
		inter_regular.applyFont(dueDateTxt, 15f, Color.DARK_GRAY);
		dueDateTxt.setEditable(false);
		dueDateTxt.setColumns(10);
		dueDateTxt.setCaretColor(new Color(0, 0, 0, 0));
		dueDateTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		dueDateTxt.setBackground(new Color(242, 242, 242));
		dueDateTxt.setBounds(675, 512, 261, 38);
		contentPanel.add(dueDateTxt);
		
		// Barcode Scanner 
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(66, 384, 115, 1);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(247, 384, 115, 1);
		contentPanel.add(separator_1);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		inter_regular.applyFont(lblOr, 24f, Color.BLACK);
		lblOr.setBounds(191, 370, 46, 28);
		contentPanel.add(lblOr);
		
		JLabel lblEnterCode = new JLabel("Enter code:");
		inter_medium.applyFont(lblEnterCode, 20f, Color.BLACK);
		lblEnterCode.setBounds(66, 433, 136, 23);
		contentPanel.add(lblEnterCode);
		
		JLabel lblBorrowID = new JLabel("Borrow ID:");
		inter_medium.applyFont(lblBorrowID, 15f, Color.BLACK);
		lblBorrowID.setBounds(536, 276, 129, 20);
		contentPanel.add(lblBorrowID);
		
		borrowIDTxt = new RoundedTextField(10);
		inter_regular.applyFont(borrowIDTxt, 15f, Color.DARK_GRAY);
		borrowIDTxt.setEditable(false);
		borrowIDTxt.setColumns(10);
		borrowIDTxt.setCaretColor(new Color(0, 0, 0, 0));
		borrowIDTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		borrowIDTxt.setBackground(new Color(242, 242, 242));
		borrowIDTxt.setBounds(675, 267, 261, 38);
		contentPanel.add(borrowIDTxt);
		
		//Webcam

		webcam = Webcam.getDefault();
		if (webcam != null) {
			webcam.setViewSize(new Dimension(176, 144)); // [176x144] [320x240] [640x480]
		    webcamPanel = new WebcamPanel(webcam);
		    webcamPanel.setMirrored(false);
		    webcamPanel.setFPSDisplayed(true);
		    webcamPanel.setBounds(30, 22, 381, 318);
		    webcamPanel.setLayout(null);
		    contentPanel.add(webcamPanel);
		 } else {
		    System.err.println("No webcam detected!");
		    webcam.close();
		 }

		executor.execute(this);
		
		// Call the dispose function when exiting the modal
		addWindowListener((WindowListener) new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        dispose();
		    }
		});
		
	}
	
	public void getBorrowedInfo(String barcode) {
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM borrows "
					+ "INNER JOIN students ON borrows.student_id = students.student_id "
					+ "INNER JOIN books ON borrows.book_barcode = books.book_barcode "
					+ "WHERE borrows.book_barcode = ? AND borrows.borrow_status = 'Pending for Return'");
			prep_stmt.setString(1, barcode);
			rs = prep_stmt.executeQuery();
			if(rs.next()){
				borrowIDTxt.setText(rs.getString("borrow_id"));
				bookBarcodeTxt.setText(rs.getString("book_barcode"));
				bookTitleTxt.setText(rs.getString("book_title"));
				bookAuthorTxt.setText(rs.getString("book_author"));
				bookCategoryTxt.setText(rs.getString("book_category"));
				studentNumberTxt.setText(rs.getString("student_number"));
				nameTxt.setText(rs.getString("student_name"));
				genderTxt.setText(rs.getString("student_gender"));
				departmentTxt.setText(rs.getString("student_department"));
				dueDateTxt.setText(rs.getString("borrow_dueDate"));
			}
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkBarcode(String barcode) {
		boolean bookExist = false;
		
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE book_barcode = ?");
			prep_stmt.setString(1, barcode);
			rs = prep_stmt.executeQuery();
			if(rs.next()) {
				bookExist = true;
			}
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return bookExist;
	}
	
	public boolean checkBookStatus(String barcode) {
		boolean isAvailable = false;
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE book_barcode = ? AND book_status = 'Available'");
			prep_stmt.setString(1, barcode);
			rs = prep_stmt.executeQuery();
			if(rs.next()) { 	
				isAvailable = true;
			}
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return isAvailable;
	}
	
	public void updateBookStatus(String barcode) {
		try {
			prep_stmt = connect.conn.prepareStatement("UPDATE books SET book_status = 'Available' WHERE book_barcode = ?");
			prep_stmt.setString(1, barcode);
			prep_stmt.executeUpdate();
			
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Updates the borrow status
	public void updateBorrowStatus(int borrowId) {
		try {
			prep_stmt = connect.conn.prepareStatement("UPDATE borrows SET borrow_status = 'Returned' WHERE borrow_id = ?");
			prep_stmt.setInt(1, borrowId);
			prep_stmt.executeUpdate();
			
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Check if the student logs
	public boolean checkStudentNumber(int studNo) {
		boolean studentExist = false;
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT student_number FROM students WHERE student_number = ? AND DATE(student_timeIn) = CURDATE()");
			prep_stmt.setInt(1, studNo);
			rs = prep_stmt.executeQuery();
			if(rs.next()) {
				studentExist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentExist;
	}

	@Override
	public void dispose() {
	    super.dispose(); // Call the superclass method first

	    // Stop the webcam and release resources
	    if (webcam != null) {
	        webcam.close();
	    }

	    // Shutdown the executor service to stop running threads
	    if (executor != null && !executor.isShutdown()) {
	        executor.shutdownNow();
	    }
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Cancel button
		if(e.getSource() == cancelBtn) {
			dispose();
		}
		// Manually types barcode value
		if(e.getSource() == enterBtn) {
			String barcode = bookBarcodeTxt.getText();
			if(barcode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter the barcode first!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(!checkBarcode(barcode)) {
					JOptionPane.showMessageDialog(null, "Book Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if(checkBookStatus(barcode)) {
		        	JOptionPane.showMessageDialog(null, "Book is already returned!", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            System.out.println("Bar Code Detected!");
		            JOptionPane.showMessageDialog(null, "Book Found!");
		            getBorrowedInfo(barcode);
		        }
			}
		}
		// Return book
		if(e.getSource() == returnBookBtn) {
			String borrowID = borrowIDTxt.getText();
			String bookBarcode = bookBarcodeTxt.getText();
			String title = bookTitleTxt.getText();
			String author = bookAuthorTxt.getText();
			String category = bookCategoryTxt.getText();
			int studNum = Integer.parseInt(studentNumberTxt.getText());
			String name = nameTxt.getText();
			String gender = genderTxt.getText();
			String department = departmentTxt.getText();
			String dueDate = dueDateTxt.getText();
			
			if(borrowID.isEmpty() || bookBarcode.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty() || studNum == 0 || name.isEmpty() || gender.isEmpty() || department.isEmpty() || dueDate.isEmpty()){
				JOptionPane.showMessageDialog(null, "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
			} else if(!checkStudentNumber(studNum)) {
				JOptionPane.showMessageDialog(null, "Student doesn't, please log First!", "Input Error", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					int bID = Integer.parseInt(borrowID);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					prep_stmt = connect.conn.prepareStatement("INSERT INTO returns (borrow_ID, return_date)"
							+ " VALUES (?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'))");
					prep_stmt.setInt(1, bID);
					
					int row = prep_stmt.executeUpdate();
					if(row == 1) {
						updateBookStatus(bookBarcode);
						updateBorrowStatus(bID);
						// checks for if the book is overdue
						LocalDate borrowDueDate = LocalDate.parse(dueDate, formatter);
				        LocalDate today = LocalDate.now();
				        if (today.isAfter(borrowDueDate)) {
				            long overdueDays = ChronoUnit.DAYS.between(borrowDueDate, today);
				            JOptionPane.showMessageDialog(null, "This book is overdue for " + overdueDays + " days", "Alert", JOptionPane.WARNING_MESSAGE);
				        }
						JOptionPane.showMessageDialog(null, "Successfully Return a book!");
						MainApp.getInstance().updateOverdueNotification();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Returning Book Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					prep_stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "Barcode scanner Thread");
		t.setDaemon(true);
		return t;
	}

	@Override
	public void run() {
	    try {
	        while (true) {
	            if (Thread.interrupted()) {
	                throw new InterruptedException();
	            }

	            if (!webcam.isOpen()) {
	                continue;  // Skip this loop iteration if the webcam is not open
	            }

	            BufferedImage image = webcam.getImage();
	            if (image == null) {
	                continue;  // Skip this loop iteration if no image is captured
	            }
	            try {
		            LuminanceSource source = new BufferedImageLuminanceSource(image);
		            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		            Result result = new MultiFormatReader().decode(bitmap);
		            String barcodeResult = String.valueOf(result.getText());
	                if (result != null && result.getText() != null) {
	                	// check if book exist
	                	if(!checkBarcode(barcodeResult)) {
	                		JOptionPane.showMessageDialog(null, "Book Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
	                	} else if(checkBookStatus(barcodeResult)) {
	                		JOptionPane.showMessageDialog(null, "Book is already returned!", "Error", JOptionPane.ERROR_MESSAGE);
	                	} else {
			                System.out.println("Bar Code Detected!");
			                JOptionPane.showMessageDialog(null, "Book Found!");
			                getBorrowedInfo(barcodeResult);
	                	}
	                }
	            } catch (NotFoundException e) {
	                System.out.println("Bar Code not found in the image");
	            } catch (NullPointerException e) {
	                System.err.println("Failed to decode Bar code because the image was null.");
	            }
	        }
	    } catch (InterruptedException e) {
	        System.out.println("Scanning thread was interrupted");
	    } finally {
	        webcam.close();
	    }
	}
}
