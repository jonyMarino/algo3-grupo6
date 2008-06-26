package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
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

import controlador.ControladorCarrera;
import controlador.ControladorJuego;

public class PantallaCarrera extends JPanel{

	private static final long serialVersionUID = 1L;


    ControladorCarrera elControlador;
    int xAuto, yAuto;
    VistaGrafica unaVista;
    
	public PantallaCarrera(ControladorJuego controladorJuego) {
		super();
		int width = 900;
		int height = 675;
		this.setSize(width, height);
		this.setBorder(BorderFactory.createEmptyBorder(2, 0, 2,0));
		this.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);
		
		unaVista = new VistaDeCostado(width, height, controladorJuego.getProgramaAuto());
		setFocusable(true);
		setVisible(true);
		elControlador = new ControladorCarrera(controladorJuego.getProgramaAuto(), this);
		addKeyListener(elControlador);
		elControlador.comenzar();
	}
	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 =(Graphics2D) g;
	        if(g2!=null){
	        	g2.drawImage(unaVista.getVista(), 0, 0, getWidth(), getHeight(), null);
	        }
	}

	public int getXAuto() {
		return xAuto;
	}

	public void setXAuto(int auto) {
		xAuto = auto;
	}

	public int getYAuto() {
		return yAuto;
	}

	public void setYAuto(int auto) {
		yAuto = auto;
	}
}