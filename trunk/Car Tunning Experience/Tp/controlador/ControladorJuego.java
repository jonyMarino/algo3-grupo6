package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import excepciones.WrongUserNameException;
import programaAuto.NotContainedPistaException;
import programaAuto.PistaPickedException;
import programaAuto.ProgramaAuto;
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


	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("nueva"))
			panelBase.crearPantalla(new PantallaUsuario(this));
		if (comando.equals("continuar"))
			panelBase.crearPantalla(new PantallaContinuarPartida(this));
		if (comando.equals("volver"))
			panelBase.pantallaAnterior();
		if (comando.equals("aceptar"))
			inicializarJuego();
		if (comando.equals("comenzar")){
			panelBase.crearPantalla(new PantallaCarrera(this));
		}
			
	}
	
	private void inicializarJuego(){
			try {
				programaAuto = new ProgramaAuto(((PantallaUsuario)panelBase.getPantallaActual()).obtenerNombreBoxUsuario());
				programaAuto.setPista(programaAuto.generarProximaPista());		
				this.controladorTaller = new ControladorTaller(programaAuto);
				ImageIcon imagen = ((PantallaUsuario)panelBase.getPantallaActual()).obtenerImagenSeleccionada(); 
				
				panelBase.crearPantalla(new PantallaTaller(this));	
				controladorTaller.setPantallaTaller( (PantallaTaller)panelBase.getPantallaActual() );
				
				controladorTaller.cargarPantallaTaller(imagen);
			} catch (NotContainedPistaException e1) {} 
			  catch (PistaPickedException e1) {}		
			  catch (WrongUserNameException e) {
				this.MensajeError();
			}
	}

	private void MensajeError() {
		((PantallaUsuario)panelBase.getPantallaActual()).generarMensajeError("Debe ingresar: NOMBRE USUARIO");
	}

	public void setPanelBase(PanelBase panelBase) {
		this.panelBase = panelBase;
	}
	
	public ProgramaAuto getProgramaAuto(){
		return programaAuto;
	}

	public ControladorTaller getControladorTaller() {
		return controladorTaller;
	}
	
}
