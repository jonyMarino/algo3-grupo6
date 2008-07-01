package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import excepciones.WrongUserNameException;
import programaAuto.NotContainedPistaException;
import programaAuto.PistaPickedException;
import programaAuto.ProgramaAuto;
import programaAuto.ProgramaAuto.TipoAuto;
import vista.PanelBase;
import vista.PantallaCarrera;
import vista.PantallaInicio;
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
		if (comando.equals("nueva")) {
			panelBase.crearPantalla(new PantallaUsuario(this));
			this.cargarPantallaUsuario((PantallaUsuario)panelBase.getPantallaActual());
		}
		if (comando.equals("continuar"))
			buscarArchivo();
		if (comando.equals("volver"))
			panelBase.pantallaAnterior();
		if (comando.equals("aceptar"))
			inicializarJuego();
		if (comando.equals("comenzar")){
			panelBase.crearPantalla(new PantallaCarrera(this));
		}
		if (comando.equals("volveraltaller")){
			volverAlTaller();
		}			
	}
	
	private void cargarPantallaUsuario(PantallaUsuario pantallaUsuario) {
		pantallaUsuario.agregarTipoAuto(TipoAuto.MANUAL.toString());
		pantallaUsuario.agregarTipoAuto(TipoAuto.AUTOMATICO.toString());
	}

	private void buscarArchivo(){
		PantallaInicio pantallaInicio =((PantallaInicio)panelBase.getPantallaActual());
		JFileChooser fileChooser = pantallaInicio.getfileChooser();
		fileChooser.showOpenDialog(fileChooser);
	}
	
	private void inicializarJuego(){
			try {
				String nombreUsuario = ((PantallaUsuario)panelBase.getPantallaActual()).obtenerNombreBoxUsuario();
				TipoAuto tipoAuto = this.buscarTipoAuto(((PantallaUsuario)panelBase.getPantallaActual()).obtenerTipoAutoSeleccionado()); 
				programaAuto = new ProgramaAuto(nombreUsuario, tipoAuto);
				programaAuto.setPista(programaAuto.generarProximaPista());		
				ImageIcon imagen = ((PantallaUsuario)panelBase.getPantallaActual()).obtenerImagenSeleccionada(); 		
				this.controladorTaller = new ControladorTaller(programaAuto);
				panelBase.crearPantalla(new PantallaTaller(this));	
				controladorTaller.setPantallaTaller((PantallaTaller)panelBase.getPantallaActual());
				controladorTaller.getActualizadorTaller().cargarPantallaTaller(imagen);
			} catch (NotContainedPistaException e1) {	
			} catch (PistaPickedException e1) {		
			} catch (WrongUserNameException e) {
				this.MensajeError();
			}
	}
	
	private void volverAlTaller(){
		try {
			programaAuto.setPista(programaAuto.generarProximaPista());
			controladorTaller.getActualizadorTaller().actualizarPantallaTaller();
			panelBase.pantallaAnterior();
		} catch (NotContainedPistaException e1) {
		} catch (PistaPickedException e1) {
		}

	}

	private TipoAuto buscarTipoAuto(String tipoAuto) {
		if(TipoAuto.AUTOMATICO.toString() == tipoAuto)
			return TipoAuto.AUTOMATICO;
		else
			return TipoAuto.MANUAL;
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
