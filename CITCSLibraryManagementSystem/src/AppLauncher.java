import javax.swing.SwingUtilities;

import guis.Login;

// Class that launches the Application
public class AppLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
					login.setLocationRelativeTo(null);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}