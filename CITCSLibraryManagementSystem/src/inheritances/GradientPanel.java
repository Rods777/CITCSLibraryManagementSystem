package inheritances;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

// Class that sets gradient to Panel
public class GradientPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GradientPanel() {
		setOpaque(false);
		colors = new ArrayList<>();
	}
	
	private List<ModelColor> colors;
	
	public void addColor(ModelColor... color) {
		for(ModelColor c : color){
			colors.add(c);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		if(!colors.isEmpty()) {
			int w = getWidth();
	        int h = getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        Color color[] = new Color[colors.size()];
	        float position[] = new float[colors.size()];
	        
	        for(int i = 0; i < colors.size(); i++) {
	        	color[i] = colors.get(i).getColor();
	        	position[i] = colors.get(i).getPosition();
	        }
	        
	        // as Start x, y
	        int sx = 0; 
	        int sy = 0;
	        // as end x, y
	        int ex = w;
	        int ey = 0;
	        
	        LinearGradientPaint lg = new LinearGradientPaint(sx, sy, ex, ey, position, color);
	        g2d.setPaint(lg);
	        g2d.fillRect(0, 0, w, h);
		}
        
        super.paintComponent(g);
    }


}
