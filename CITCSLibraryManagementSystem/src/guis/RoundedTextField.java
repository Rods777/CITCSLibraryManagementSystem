package guis;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedTextField extends JTextField {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int radius;
	 
	 public RoundedTextField(int radius) {
	        this.radius = radius;
	        setOpaque(false);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D graphics = (Graphics2D) g.create();
	        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        graphics.setColor(getBackground());
	        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
	        super.paintComponent(graphics);
	        graphics.dispose();
	    }

	    @Override
	    protected void paintBorder(Graphics g) {
	        Graphics2D graphics = (Graphics2D) g.create();
	        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        graphics.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
	        graphics.dispose();
	    }

	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(super.getPreferredSize().width, 30); // Adjust height as needed
	    }
}