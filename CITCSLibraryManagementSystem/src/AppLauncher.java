import javax.swing.SwingUtilities;

// Class that launches the Application
public class AppLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
