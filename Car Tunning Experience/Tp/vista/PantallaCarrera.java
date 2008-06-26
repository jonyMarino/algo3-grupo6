package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import sun.awt.RepaintArea;
import sun.java2d.loops.DrawRect;

import controlador.ControladorJuego;

public class PantallaCarrera extends JPanel{

	private static final long serialVersionUID = 1L;

	Image autoPrincipal;
    BufferedImage bufferPrincipal;
    
	public PantallaCarrera(ControladorJuego controladorJuego) {
		super();
		int width = 900;
		int height = 675;
		this.setSize(width, height);
		bufferPrincipal = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();
		buffer.setColor(Color.cyan);
		buffer.fillRect(0, 0, width, height);
		buffer.setColor(Color.green);
		buffer.fillRect(0, height*3/4, width, height/4);
		this.setBorder(BorderFactory.createEmptyBorder(2, 0, 2,0));
		this.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);
		autoPrincipal = new ImageIcon(getClass().getResource("/vista/images/"+ "UnAuto" +".gif")).getImage();
	}
	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 =(Graphics2D) g;
	        if(bufferPrincipal!=null){
	            bufferPrincipal.getGraphics().drawImage(autoPrincipal, 0, 0, null);
	        	g2.drawImage(bufferPrincipal, 0, 0, getWidth(), getHeight(), null);
	        }
	}
}