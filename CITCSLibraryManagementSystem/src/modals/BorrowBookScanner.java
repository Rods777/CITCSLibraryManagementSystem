package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
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
import inheritances.FontLoader;
import inheritances.RoundedTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import inheritances.RoundedButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class BorrowBookScanner extends JDialog implements ActionListener,Runnable,ThreadFactory{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FontLoader inter_regular = new FontLoader("/fonts/Inter-Regular.ttf");
	private FontLoader inter_medium = new FontLoader("/fonts/Inter-Medium.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");
	private RoundedTextField bookTitleTxt, bookAuthorTxt, bookCategoryTxt, borrowersNumberTxt, bookBarcodeTxt;
	private JDateChooser dateChooser;
	private RoundedButton enterBtn, borrowBookBtn, cancelBtn;
	
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
			BorrowBookScanner dialog = new BorrowBookScanner();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrowBookScanner() {
		connect.Connect();
		setBounds(100, 100, 1000, 710);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);		
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		contentPanel.setLayout(null);
		
		JLabel lblBookInformation = new JLabel("Book Information");
		inter_bold.applyFont(lblBookInformation, 19f, Color.BLACK);
		lblBookInformation.setBounds(523, 60, 235, 28);
		contentPanel.add(lblBookInformation);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		inter_medium.applyFont(lblBookTitle, 15f, Color.BLACK);
		lblBookTitle.setBounds(523, 121, 104, 20);
		contentPanel.add(lblBookTitle);
		
		bookTitleTxt = new RoundedTextField(10);
		bookTitleTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookTitleTxt.setEditable(false);
		bookTitleTxt.setBounds(662, 111, 261, 40);
		bookTitleTxt.setBackground(Color.decode("#F2F2F2"));
		inter_regular.applyFont(bookTitleTxt, 15f, Color.DARK_GRAY);
		bookTitleTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		contentPanel.add(bookTitleTxt);
		bookTitleTxt.setColumns(10);
		
		JLabel lblBookAuthor = new JLabel("Book Author:");
		inter_medium.applyFont(lblBookAuthor, 15f, Color.BLACK);
		lblBookAuthor.setBounds(523, 182, 104, 20);
		contentPanel.add(lblBookAuthor);
		
		bookAuthorTxt = new RoundedTextField(10);
		inter_regular.applyFont(bookAuthorTxt, 15f, Color.DARK_GRAY);
		bookAuthorTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookAuthorTxt.setEditable(false);
		bookAuthorTxt.setColumns(10);
		bookAuthorTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookAuthorTxt.setBackground(new Color(242, 242, 242));
		bookAuthorTxt.setBounds(662, 172, 261, 40);
		contentPanel.add(bookAuthorTxt);
		
		JLabel lblCategory = new JLabel("Category:");
		inter_medium.applyFont(lblCategory, 15f, Color.BLACK);
		lblCategory.setBounds(523, 245, 104, 20);
		contentPanel.add(lblCategory);
		
		bookCategoryTxt = new RoundedTextField(10);
		bookCategoryTxt.setCaretColor(new Color(0, 0, 0, 0));
		bookCategoryTxt.setEditable(false);
		bookCategoryTxt.setColumns(10);
		inter_regular.applyFont(bookCategoryTxt, 15f, Color.DARK_GRAY);
		bookCategoryTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		bookCategoryTxt.setBackground(new Color(242, 242, 242));
		bookCategoryTxt.setBounds(662, 235, 261, 40);
		contentPanel.add(bookCategoryTxt);
		
		JLabel lblBorrowersInformation = new JLabel("Borrower's Information");
		inter_bold.applyFont(lblBorrowersInformation, 19f, Color.BLACK);
		lblBorrowersInformation.setBounds(523, 314, 235, 28);
		contentPanel.add(lblBorrowersInformation);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		inter_medium.applyFont(lblStudentNumber, 15f, Color.BLACK);
		lblStudentNumber.setBounds(523, 388, 141, 20);
		contentPanel.add(lblStudentNumber);
		
		borrowersNumberTxt = new RoundedTextField(10);
		borrowersNumberTxt.setPlaceholder("Enter your Student Number...");
		borrowersNumberTxt.setColumns(10);
		borrowersNumberTxt.setBorder(new LineBorder(new Color(171, 173, 179), 10));
		borrowersNumberTxt.setBackground(new Color(242, 242, 242));
		borrowersNumberTxt.setBounds(662, 378, 261, 40);
		inter_regular.applyFont(borrowersNumberTxt, 15f, Color.BLACK);
		contentPanel.add(borrowersNumberTxt);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		inter_medium.applyFont(lblDueDate, 15f, Color.BLACK);
		lblDueDate.setBounds(523, 453, 141, 20);
		contentPanel.add(lblDueDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(662, 444, 261, 40);
		inter_regular.applyFont(dateChooser, 15f, Color.BLACK);
		contentPanel.add(dateChooser);
		
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
		
		borrowBookBtn = new RoundedButton("BORROW BOOK", 15, Color.decode("#2DC653"));
		inter_bold.applyFont(borrowBookBtn, 18f, Color.WHITE);
		borrowBookBtn.setBounds(523, 574, 196, 55);
		borrowBookBtn.addActionListener(this);
		contentPanel.add(borrowBookBtn);
		
		cancelBtn = new RoundedButton("CANCEL", 15, Color.decode("#E74343"));
		inter_bold.applyFont(cancelBtn, 18f, Color.WHITE);
		cancelBtn.setBounds(729, 574, 194, 55);
		cancelBtn.addActionListener(this);
		contentPanel.add(cancelBtn);
		
		//Webcam
		webcam = Webcam.getDefault();
		if (webcam != null) {
			webcam.setViewSize(new Dimension(640, 480)); // [176x144] [320x240] [640x480]
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelBtn) {
			dispose();
		}
		if(e.getSource() == enterBtn) {
			String barcode = bookBarcodeTxt.getText();
			if(barcode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter the barcode first!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(!checkBarcode(barcode)) {
	        		JOptionPane.showMessageDialog(null, "Book Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
	        	} else {
	        		if(!checkBookStatus(barcode)) {
	        			JOptionPane.showMessageDialog(null, "Book is already borrowed!", "Error", JOptionPane.ERROR_MESSAGE);
	        		} else {
	                    System.out.println("Bar Code Detected!");
	                    JOptionPane.showMessageDialog(null, "Book Found!");
	        		}
	        	}
			}
		}
		if(e.getSource() == borrowBookBtn) {
			String bookTitle = bookTitleTxt.getText();
			String bookAuthor = bookAuthorTxt.getText();
			String bookCategory = bookCategoryTxt.getText();
			String bookBarcode = bookBarcodeTxt.getText();
			String studNumber = borrowersNumberTxt.getText();
			
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dueDate = formatter.format(dateChooser.getDate());
			

			if(bookTitle.isEmpty() || bookAuthor.isEmpty() || bookCategory.isEmpty() || bookBarcode.isEmpty() || studNumber.isEmpty() || dateChooser.getDate() == null ) {
				JOptionPane.showMessageDialog(null, "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					int studentNumber = Integer.parseInt(studNumber);
					if(checkStudentNumber(studentNumber)) {
						try {
							int studentID = getStudentId(studentNumber);
							prep_stmt = connect.conn.prepareStatement("INSERT INTO borrows (book_barcode, student_id, borrow_date, borrow_dueDate, borrow_status)"
									+ " VALUES (?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), ?, ?)");
							prep_stmt.setString(1, bookBarcode);
							prep_stmt.setInt(2, studentID);
							prep_stmt.setString(3, dueDate);
							prep_stmt.setString(4, "Pending for Return");
							
							int row = prep_stmt.executeUpdate();
							if(row == 1) {
								updateBookStatus(bookBarcode);
								JOptionPane.showMessageDialog(null, "Successfully Borrow a book!");
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,
										"Borrowing Book Error, Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
							prep_stmt.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Student doesn't log yet, please log First!", "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Student Number must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public int getStudentId(int studentNumber) {
		int studentID = 0;
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT student_id FROM students WHERE student_number = ? AND DATE(student_timeIn) = CURDATE()");
			prep_stmt.setInt(1, studentNumber);
			rs = prep_stmt.executeQuery();	
			if(rs.next()) {
				studentID = rs.getInt("student_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentID;
	}
	
	public void updateBookStatus(String barcode) {
		try {
			prep_stmt = connect.conn.prepareStatement("UPDATE books SET book_status = 'Unavailable' WHERE book_barcode = ?");
			prep_stmt.setString(1, barcode);
			prep_stmt.executeUpdate();
			
			prep_stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkBookStatus(String barcode) {
		boolean isAvailable = false;
		
        // Create a Calendar and add 2 days for due date
		Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date twoDaysAfter = calendar.getTime();
        
		try {
			prep_stmt = connect.conn.prepareStatement("SELECT * FROM books WHERE book_barcode = ? AND book_status = 'Available'");
			prep_stmt.setString(1, barcode);
			rs = prep_stmt.executeQuery();
			if(rs.next()) { 	
				isAvailable = true;
				bookTitleTxt.setText(rs.getString("book_title"));
				bookAuthorTxt.setText(rs.getString("book_author"));
				bookCategoryTxt.setText(rs.getString("book_category"));
				bookBarcodeTxt.setText(rs.getString("book_barcode"));
				dateChooser.setDate(twoDaysAfter);
			}
			prep_stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return isAvailable;
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
	    super.dispose(); 

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
	                	} else {
	                		if(!checkBookStatus(barcodeResult)) {
	                			JOptionPane.showMessageDialog(null, "Book is already borrowed!", "Error", JOptionPane.ERROR_MESSAGE);
	                		}else {
			                    System.out.println("Bar Code Detected!");
			                    JOptionPane.showMessageDialog(null, "Book Found!");
	                		}
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