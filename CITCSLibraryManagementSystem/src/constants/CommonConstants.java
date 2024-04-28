package constants;

import java.awt.Color;
// Class for Constant Values
public class CommonConstants {
	
	// Gradient Background Color Hex codes
	public static final Color GRADIENT_START = Color.decode("#17392B");
	public static final Color GRADIENT_MID = Color.decode("#0E5332");
	public static final Color GRADIENT_END = Color.decode("#08683A");
	
	// Color for header
	public static final Color HEADER_COLOR = Color.decode("#0E5332");
	
	// Color for buttons
	public static final Color SAVE_BUTTON = Color.decode("#0060CC");
	public static final Color EDIT_BUTTON = Color.decode("#0060CC");
	public static final Color DELETE_BUTTON = Color.decode("#E74343");
	
	// Database Credentials
	public static final String DB_JDBCDRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_NAME = "citcslibrary";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";
}
