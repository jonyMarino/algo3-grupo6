package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ImageIcon;

import excepciones.WrongUserNameException;
import programaAuto.NoPistaPickedException;
import programaAuto.NotAbleWhileSimulatingException;
import programaAuto.NotContainedPistaException;
import programaAuto.Pista;
import programaAuto.PistaPickedException;
import programaAuto.ProgramaAuto;
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
			try {
				programaAuto = new ProgramaAuto(((PantallaUsuario)panelBase.getPantallaActual()).obtenerNombreBoxUsuario());
						
				try {
					programaAuto.setPista(this.generarProximaPista());
				} catch (NotContainedPistaException e1) {} 
				  catch (PistaPickedException e1) {}
				
				try {
					this.controladorTaller = new ControladorTaller(programaAuto.entrarAlTaller());
				} catch (NoPistaPickedException e) {}
				  catch (NotAbleWhileSimulatingException e) {}
				
				ImageIcon imagen = ((PantallaUsuario)panelBase.getPantallaActual()).obtenerImagenSeleccionada(); 
				
				panelBase.crearPantalla(new PantallaTaller(this));	
				controladorTaller.setPantallaTaller( (PantallaTaller)panelBase.getPantallaActual() );
				
				controladorTaller.setProximaPista(programaAuto.getPista());
				controladorTaller.cargarPantallaTaller(imagen);	
				
			} catch (WrongUserNameException e) {
				this.MensajeError();
			}
	}

	private void MensajeError() {

		((PantallaUsuario)panelBase.getPantallaActual()).generarMensajeError("Debe ingresar: NOMBRE USUARIO");
	}

	public void setPanelBase(PanelBase panelBase) {
		this.panelBase = panelBase;
	}
	
	public void entrarATaller(){
		
	}
	
	//TODO: provisorio para que entre al taller, esto lo debe generar el programa auto
	private Pista generarProximaPista(){
		Iterator<Pista> pistas = programaAuto.getPistas();
		
		return (pistas.next());
	}

}
