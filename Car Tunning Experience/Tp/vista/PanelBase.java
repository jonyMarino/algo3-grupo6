package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import controlador.ControladorJuego;

public class PanelBase extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pantallaActual;
	private JPanel pantallaAnterior;
	private ControladorJuego controlador;
	
	public PanelBase(ControladorJuego controlador) {
		super();
		this.setControlador(controlador);
		controlador.setPanelBase(this);
		
		//Amarillo para que se vea!!!
		this.setBackground(Color.YELLOW);
		Canvas lienzo = new Canvas();
		this.setPreferredSize(new Dimension(lienzo.getWidth(),lienzo.getHeight()));
		this.setPantallaAnterior(null);
		this.setPantallaActual(null);
	}

	public void crearPantallaInicio() {
		PantallaInicio pantallaInicio = new PantallaInicio(this);
		this.add(pantallaInicio);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		pantallaActual = pantallaInicio;
	}

	public void crearPantalla(JPanel pantalla) {
		pantallaActual.setVisible(false);
		this.add(pantalla);
		pantallaAnterior = pantallaActual;
		pantallaActual = pantalla;
	}


	public void pantallaAnterior() {
		this.getPantallaActual().setVisible(false);
		this.getPantallaAnterior().setVisible(true);
		JPanel pantallaAuxiliar = pantallaAnterior;
		pantallaAnterior = pantallaActual;
		pantallaActual = pantallaAuxiliar;
	}

	public JPanel getPantallaAnterior() {
		return pantallaAnterior;
	}

	private void setPantallaAnterior(JPanel pantallaAnterior) {
		this.pantallaAnterior = pantallaAnterior;
	}

	public JPanel getPantallaActual() {
		return pantallaActual;
	}

	private void setPantallaActual(JPanel pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

	public ControladorJuego getControlador() {
		return controlador;
	}

	public void setControlador(ControladorJuego controlador) {
		this.controlador = controlador;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
