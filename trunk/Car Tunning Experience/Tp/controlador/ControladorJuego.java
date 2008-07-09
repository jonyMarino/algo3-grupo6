package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;
import excepciones.NotContainedPistaException;
import excepciones.PistaPickedException;
import excepciones.WrongUserNameException;
import programaAuto.ProgramaAuto;
import programaAuto.ProgramaAuto.TipoAuto;
import vista.PanelBase;
import vista.PantallaCarrera;
import vista.PantallaInicio;
import vista.PantallaTaller;
import vista.PantallaUsuario;

/**
 * 
 * Clase que se encarga de controlar todos los aspectos de la interacción
 * del usuario durante todo el transcurso del juego.
 * 
 */
public class ControladorJuego implements ActionListener {
	
	private ProgramaAuto programaAuto;
	private PanelBase panelBase;
	private ControladorTaller controladorTaller;
	
	/**
	 * Crea un nuevo controladorJuego para el programa auto especificado
	 * @param programaAuto La instancia de ProgramaAuto.
	 * @see ProgramaAuto
	 */
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
		if (comando.equals("continuar")){
			FiltroDeArchivos filtro= new FiltroDeArchivos();
			filtro.addExtension("xml");
			filtro.setDescription("Archivo guardado(*.xml)");
			PantallaInicio pantallaInicio =((PantallaInicio)panelBase.getPantallaActual());
			String nombreArchivo=pantallaInicio.buscarArchivo(filtro);
			if(nombreArchivo=="")
				return;
			Document doc;
			try {
				InputStream entrada = new BufferedInputStream(new FileInputStream(nombreArchivo));
				doc = new Builder().build(entrada);
				programaAuto= new ProgramaAuto(doc.getRootElement());
			} catch (Exception e1) {
				e1.printStackTrace();
				panelBase.mostrarMensajeDeError("El Archivo elegido se encuentra corrupto.");
				return;
			}  
			
			try {
				programaAuto.setPista(programaAuto.generarProximaPista());
			} catch (NotContainedPistaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PistaPickedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ImageIcon imagen = new PantallaUsuario(this).obtenerImagenSeleccionada(); 		
			this.controladorTaller = new ControladorTaller(programaAuto);
			panelBase.crearPantalla(new PantallaTaller(this));	
			controladorTaller.setPantallaTaller((PantallaTaller)panelBase.getPantallaActual());
			controladorTaller.getActualizadorTaller().cargarPantallaTaller(imagen);
			
			/////////////////////////////////////
			
		}
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

		if (comando.equals("guardar")){
			Element raiz= new Element("root");
			raiz.appendChild(programaAuto.getElement());
			FiltroDeArchivos filtro= new FiltroDeArchivos();
			filtro.addExtension("xml");
			filtro.setDescription("Archivo guardado(*.xml)");
			
			String nombreArchivo=panelBase.salvarArchivo(filtro);
			if(nombreArchivo==""){
				return;
			}
			Document doc= new Document(raiz);
			BufferedOutputStream file;
			try {
				file = new BufferedOutputStream(new FileOutputStream(nombreArchivo));
				format(file,doc);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	
	}
    private void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(1000);
        serializer.write(doc);
        serializer.flush();
      }
	private void cargarPantallaUsuario(PantallaUsuario pantallaUsuario) {
		pantallaUsuario.agregarTipoAuto(TipoAuto.MANUAL.toString());
		pantallaUsuario.agregarTipoAuto(TipoAuto.AUTOMATICO.toString());
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
			controladorTaller.entrarAlTaller();
			controladorTaller.getActualizadorTaller().actualizarResultado();
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
