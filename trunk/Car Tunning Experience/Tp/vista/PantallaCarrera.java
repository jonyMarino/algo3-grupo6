package vista;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import programaAuto.ProgramaAuto;
import controlador.ControladorCarrera;
import controlador.ControladorJuego;

/**
 * Panel que sirve de base a las vistas de la carrera.
 * 
 * @see JPanel
 * @see ControladorCarrera
 * @see VistaGrafica
 */
public class PantallaCarrera extends JPanel{

	private static final long serialVersionUID = 1L;


    ControladorCarrera elControlador;
    VistaGrafica unaVista;
    VistaGrafica informacion;
    VistaGrafica velocimetro;
    VistaGrafica lineal;
    ProgramaAuto elPrograma;
	ArrayList<Point2D> listaDeArboles;
	public static int pixelesPorMetro;
	private ControladorJuego elJuego;
	
	/**
	 * Crea una nueva PantallaCarrera para el ControladorJuego dado.
	 * @param controladorJuego La instancia de {@link ControladorJuego}, para la cual creamos la {@link PantallaCarrera}.
	 * @see ControladorJuego
	 */
	
	public PantallaCarrera(ControladorJuego controladorJuego) {
		super();
		elJuego = controladorJuego;
		pixelesPorMetro=20;
		int width = 900;
		int height = 675;
		this.setSize(width, height);
		this.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);
		elPrograma = controladorJuego.getProgramaAuto();
		elControlador = new ControladorCarrera(elPrograma, this);
		generarArboles();
		unaVista = new VistaDeCostado(width, height, controladorJuego.getProgramaAuto());
		((VistaDeCostado)unaVista).setArboles(listaDeArboles);
		((VistaDeCostado)unaVista).setCompetidores(elControlador.getListaDeCompetidores());
		informacion = new VistaCarreraInformativa(width, height/4, controladorJuego.getProgramaAuto());
		velocimetro = new VistaVelocimetro(150, 150, controladorJuego.getProgramaAuto());
		lineal = new VistaLineal(width, 50, controladorJuego.getProgramaAuto());
		((VistaLineal)lineal).setCompetidores(elControlador.getListaDeCompetidores());
		setFocusable(true);
		setVisible(true);
		addKeyListener(elControlador);
		JFrame.getFrames()[0].setVisible(false);
		JFrame.getFrames()[0].setVisible(true);
		elControlador.comenzar();
	}
	
	private void generarArboles() {
		int cantidadArboles = (int) (elPrograma.getPista().getLongitud()/105);
		int distanciaEntreArboles = (int) (10*pixelesPorMetro);
		
		listaDeArboles = new ArrayList<Point2D>();
		
		for(int i=0; i<cantidadArboles; i++){
			int y=0;
			do{
				y = (int) Math.abs((Math.random()*6));
			}while(y==2 || y>4);
			Point2D temporal = new Point((int) (i*distanciaEntreArboles)*(5-y),y);
			listaDeArboles.add(temporal);
		}
	}

	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 =(Graphics2D) g;
	        if(g2!=null){
	        	g2.drawImage(unaVista.getVista(), 0, 0, getWidth(), getHeight(), null);
	        	g2.drawImage(informacion.getVista(), 0, 0, informacion.getWidth(), informacion.getHeight(), null);
	        	g2.drawImage(velocimetro.getVista(), getWidth()*3/4, 0, velocimetro.getWidth(), velocimetro.getHeight(), null);
	        	g2.drawImage(lineal.getVista(), 0, 150, lineal.getWidth(), lineal.getHeight(), null);
	        }
	}
	/**
	 * Actualiza el panel y todas las vistas gráficas que contiene.
	 * @see VistaGrafica
	 */
	public void actualizar(){
		repaint();
	}

	/**
	 * Finaliza la carrera y regresa a la pantalla del taller.
	 * @see PantallaTaller
	 */
	public void finalizarCarrera() {
		elJuego.actionPerformed(new ActionEvent(this,0, "volveraltaller"));
		
	}


}