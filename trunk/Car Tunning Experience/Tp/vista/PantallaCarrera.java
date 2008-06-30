package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import programaAuto.ProgramaAuto;

import sun.awt.RepaintArea;
import sun.java2d.loops.DrawRect;

import controlador.ControladorCarrera;
import controlador.ControladorJuego;

public class PantallaCarrera extends JPanel{

	private static final long serialVersionUID = 1L;


    ControladorCarrera elControlador;
    VistaGrafica unaVista;
    VistaGrafica informacion;
    ProgramaAuto elPrograma;
	ArrayList<Point2D> listaDeArboles;
	public static int pixelesPorMetro;
	
	public PantallaCarrera(ControladorJuego controladorJuego) {
		super();
		pixelesPorMetro=10;
		int width = 900;
		int height = 675;
		this.setSize(width, height);
		//this.setBorder(BorderFactory.createEmptyBorder(2, 0, 2,0));
		this.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);
		elPrograma = controladorJuego.getProgramaAuto();
		elControlador = new ControladorCarrera(elPrograma, this);
		generarArboles();
		unaVista = new VistaDeCostado(width, height, controladorJuego.getProgramaAuto());
		informacion = new VistaCarreraInformativa(width, height/4, controladorJuego.getProgramaAuto());
		((VistaDeCostado)unaVista).setArboles(listaDeArboles);
		setFocusable(true);
		setVisible(true);
		addKeyListener(elControlador);
		JFrame.getFrames()[0].setVisible(false);
		JFrame.getFrames()[0].setVisible(true);
		elControlador.comenzar();
	}
	
	private void generarArboles() {
		int cantidadArboles = 60;
		int distanciaEntreArboles = (int) (elPrograma.getPista().getLongitud()*pixelesPorMetro/cantidadArboles);
		
		System.out.println(distanciaEntreArboles);
		
		listaDeArboles = new ArrayList<Point2D>();
		
		for(int i=0; i<cantidadArboles; i++){
			int y=0;
			do{
				y = (int) Math.abs((Math.random()*6));
			}while(y==2 || y>4);
			System.out.println(y);
			Point2D temporal = new Point((int) (i*distanciaEntreArboles),y);
			listaDeArboles.add(temporal);
		}
	}

	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 =(Graphics2D) g;
	        if(g2!=null){
	        	g2.drawImage(unaVista.getVista(), 0, 0, getWidth(), getHeight(), null);
	        	g2.drawImage(informacion.getVista(), 0, 0, informacion.getWidth(), informacion.getHeight(), null);
	        }
	}
	
	public void actualizar(){
		repaint();
	}

}