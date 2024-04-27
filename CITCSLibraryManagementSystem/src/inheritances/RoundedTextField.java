package inheritances;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

public class RoundedTextField extends JTextField {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int radius;
	String placeholder = "";
	 
	 public RoundedTextField(int radius) {
	        this.radius = radius;
	        setOpaque(false);
	 }
	 
	 // function that sets placeholder
	 public void setPlaceholder(String ph) {
		 this.placeholder = ph;
	 }
	 
	 public String getPlaceholder() {
		 return placeholder;
	 }
	 
	 	@Override
		public void paint(Graphics g) {
			super.paint(g);
			if(getText().length() == 0) {
				int h = getHeight();
				Insets ins = getInsets();
				FontMetrics fm = g.getFontMetrics();
				int bg = getBackground().getRGB();
				int fg = getForeground().getRGB();
				int m = 0xfefefefe;
				int c = ((bg&m)>>>1) + ((fg&m)>>>1);
				g.setColor(new Color(c, true));
				g.drawString(getPlaceholder(), ins.left, h/2+fm.getAscent()/2-2);
			}
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