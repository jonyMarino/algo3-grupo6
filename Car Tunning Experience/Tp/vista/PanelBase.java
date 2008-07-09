package vista;



import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import controlador.ControladorJuego;

/**
 * Panel que sirve de base a las vistas del juego.
 *
 */

public class PanelBase extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pantallaActual;
	private JPanel pantallaAnterior;
	private ControladorJuego controladorJuego;
	
	public PanelBase(ControladorJuego controladorJuego) {
		super();
		this.controladorJuego = controladorJuego;
		controladorJuego.setPanelBase(this);
		
		this.pantallaAnterior = null;
		this.pantallaActual = null;
	}

	public void crearPantallaInicio() {
		PantallaInicio pantallaInicio = new PantallaInicio(controladorJuego);
		this.add(pantallaInicio);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.pantallaActual = pantallaInicio;
	}

	public void crearPantalla(JPanel pantalla) {
		this.pantallaActual.setVisible(false);
		this.add(pantalla);
		this.pantallaAnterior = pantallaActual;
		this.pantallaActual = pantalla;
	}

	public void pantallaAnterior() {
		this.pantallaActual.setVisible(false);
		this.pantallaAnterior.setVisible(true);
		JPanel pantallaAuxiliar = pantallaAnterior;
		this.pantallaAnterior = pantallaActual;
		this.pantallaActual = pantallaAuxiliar;
	}
	
	public void mostrarMensajeDeError(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public String salvarArchivo(FileFilter filtro){	
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter(filtro);
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {      
	    	return chooser.getSelectedFile().getPath();
	    }
	    return "";
	}
	

	public JPanel getPantallaAnterior() {
		return pantallaAnterior;
	}
	
	public JPanel getPantallaActual() {
		return pantallaActual;
	}

	public ControladorJuego getcontroladorJuego() {
		return controladorJuego;
	}

}
