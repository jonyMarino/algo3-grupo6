package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import excepciones.WrongUserNameException;
import programaAuto.ProgramaAuto;
import programaAuto.Usuario;
import vista.Boton;
import vista.PanelBase;
import vista.PantallaCarrera;
import vista.PantallaContinuarPartida;
import vista.PantallaTaller;
import vista.PantallaUsuario;

public class ControladorJuego implements ActionListener {
	
	private ProgramaAuto programaAuto;
	private PanelBase panelBase;
	private ControladorTaller controladorTaller;
	
	public ControladorJuego(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;	
		this.controladorTaller = null;
	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	public void setPanelBase(PanelBase panelBase) {
		this.panelBase = panelBase;
	}
	
	//TODO: Escribirlo de mejor forma
	public void actionPerformed(ActionEvent e) {
		Boton boton = (Boton)e.getSource();
		if (boton.getText().equals("Nueva Partida"))
			panelBase.crearPantalla(new PantallaUsuario(this));
		if (boton.getText().equals("Continuar Partida"))
			panelBase.crearPantalla(new PantallaContinuarPartida(this));
		if (boton.getText().equals("Volver"))
			panelBase.pantallaAnterior();
		if (boton.getText().equals("Aceptar"))
			inicializarJuego();
		if (boton.getText().equals("Comenzar Carrera"))
			panelBase.crearPantalla(new PantallaCarrera(this));
	}
	
	private void inicializarJuego(){
			String nombre = ((PantallaUsuario)panelBase.getPantallaActual()).getPanelIngreso().getBox().getText();
			Usuario usuario;
			try {
				usuario = this.programaAuto.nuevoUsuario(nombre);
				usuario.setAvatar(((PantallaUsuario)panelBase.getPantallaActual()).getComboBoxCars().getSeleccionado());
				this.controladorTaller = new ControladorTaller(usuario.getTaller());
				panelBase.crearPantalla(new PantallaTaller(this));	
				this.controladorTaller.setPantallaTaller((PantallaTaller)panelBase.getPantallaActual());
				programaAuto.generarProximaPista();
				controladorTaller.setProximaPista(programaAuto.getPista());
				controladorTaller.actualizarPantallaTaller();
			} catch (WrongUserNameException e) {
				this.MensajeError();
			}
	}

	public ControladorTaller getControladorTaller() {
		return controladorTaller;
	}
	
	private void MensajeError() {
		String mensajeError = "Debe ingresar: NOMBRE USUARIO";
		((PantallaUsuario)panelBase.getPantallaActual()).getBotonError().setText(mensajeError);
		((PantallaUsuario)panelBase.getPantallaActual()).getPanelIngreso().getBox().setBackground(Color.WHITE);
		((PantallaUsuario)panelBase.getPantallaActual()).getPanelIngreso().getBox().setOpaque(true);			
	}

}
