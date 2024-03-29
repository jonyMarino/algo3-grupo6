package vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase que modela un panel con imagen de fondo.
 *
 */

public class JPanelConImagen extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image imgFondo;
	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 =(Graphics2D) g;
	        if(getImage()!=null)
	            g2.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null);
	}
	
	public Image getImage() {
        return imgFondo;
    }

	public void setImage(String nombreImage) {
		this.imgFondo = new ImageIcon(getClass().getResource("/vista/images/"+ nombreImage +".jpg")).getImage();
    }
	
}
