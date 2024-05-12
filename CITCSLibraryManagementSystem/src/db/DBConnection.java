package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import constants.CommonConstants;

public class DBConnection {
	
	public Connection conn = null;
	
	public void Connect(){
		try {
			Class.forName(CommonConstants.DB_JDBCDRIVER);
			conn = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
			if(conn != null) {
				System.out.println("Sucessfully Connected to Database!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
			JOptionPane.showMessageDialog(null, 
					"Connection Error: Please Connect to Database First!", "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}
