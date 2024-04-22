package guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.CommonConstants;
import inheritances.FontLoader;

public class BookListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FontLoader inter_black = new FontLoader("/fonts/Inter-Black.ttf");
	private FontLoader inter_bold = new FontLoader("/fonts/Inter-Bold.ttf");

	/**
	 * Create the panel.
	 */
	public BookListPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(953, 676)); // Set size relative to contentPanel
		setLayout(null);
		
		// Header
		JLabel bookList = new JLabel("Book List");
		bookList.setBounds(34, 32, 341, 50);
		inter_black.applyFont(bookList, 36f, CommonConstants.HEADER_COLOR);
		add(bookList);
		
		JLabel librarianIcon = new JLabel("");
		librarianIcon.setIcon(new ImageIcon(DashboardPanel.class.getResource("/img/librarian-icon.png")));
		librarianIcon.setBounds(851, 32, 48, 48);
		add(librarianIcon);
		
		JLabel adminName = new JLabel("Admin");
		inter_bold.applyFont(adminName, 20f, CommonConstants.HEADER_COLOR);
		adminName.setBounds(777, 45, 65, 25);
		add(adminName);

	}

	// Override method for border radius
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 100, 100);
        g2d.dispose();
    }
}
