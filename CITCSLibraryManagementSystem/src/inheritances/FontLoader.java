package inheritances;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComponent;

public class FontLoader {
	private Font font = null;
	
	public FontLoader(String fontFilePath){
		try {
			// Load the font file
			InputStream is = FontLoader.class.getResourceAsStream(fontFilePath);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			
			// Register the font in the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			 System.err.println("Failed to load font: " + e.getMessage());
		}		
	}
	
	// Method that applies the font to any component
	public void applyFont(JComponent component, float fontSize, Color color) {
		// Derive the font to the specified size and color
        if (font != null) {
            component.setFont(font.deriveFont(fontSize));
            component.setForeground(color);
        }
	}
}
