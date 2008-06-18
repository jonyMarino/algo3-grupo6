package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import programaAuto.ProgramaAuto;

public class PanelBase extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pantallaActual;
	private JPanel pantallaAnterior;
	private ProgramaAuto programaAuto;
	
	public PanelBase(ProgramaAuto programaAuto) {
		super();
		this.setProgramaAuto(programaAuto);
		
		//Amarillo para que se vea!!!
		this.setBackground(Color.YELLOW);
		this.setPreferredSize(new Dimension(900,675));
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

	public void actionPerformed(ActionEvent e) {
			Boton boton = (Boton)e.getSource();
			if (boton.getText().equals("Nueva Partida"))
				this.crearPantalla( new PantallaUsuario(this));
			if (boton.getText().equals("Volver"))
				this.pantallaAnterior();
			if (boton.getText().equals("Aceptar"))
				this.crearPantalla( new PantallaTaller(this));
			if (boton.getText().equals("Comenzar Carrera"))
				this.crearPantalla( new PantallaCarrera(this));
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

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	private void setProgramaAuto(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
	}

}
