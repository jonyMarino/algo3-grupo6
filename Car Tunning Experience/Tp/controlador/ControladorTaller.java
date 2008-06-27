package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;
import excepciones.TankIsFullException;
import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.FabricaDePartes;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.Boton;
import vista.PantallaTaller;

public class ControladorTaller implements ActionListener {
	
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	private ProgramaAuto programaAuto;
	
	public ControladorTaller(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
	}

	public void cargarPantallaTaller(ImageIcon avatarUsuario) {
		pantallaTaller.actualizarInformacionUsuario(programaAuto.getUsuario().getNombre(), avatarUsuario);
		this.actualizarPantallaTaller();
	}
	
	public void actualizarPantallaTaller(){
		pantallaTaller.actualizarInformacionDinero(Double.toString(programaAuto.getUsuario().getDinero()));
		pantallaTaller.actualizarInformacionPista(Double.toString(proximaPista.getLongitud()) , proximaPista.getNombre(), Integer.toString(proximaPista.getVelocidadAire()));
		this.actualizarInformacionReserva();
		pantallaTaller.actualizarInformacionNafta(programaAuto.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible() , programaAuto.getUsuario().getAuto().getTanqueCombustible().getCapacidad());
		this.actualizarInformacionAuto();
		this.actualizarCatalogo();
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}

	private void actualizarInformacionReserva() {	
		pantallaTaller.limpiarInformacionReserva();
		
		String descripcion,vidaUtil;
		boolean cargo = false;
		
		while (programaAuto.getUsuario().getTaller().getPartesDeReserva().hasNext()) {
			cargo = true;
			try {
				descripcion = programaAuto.getUsuario().getTaller().getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
				vidaUtil = Double.toString(programaAuto.getUsuario().getTaller().getPartesDeReserva().next().getVidaUtil());
				pantallaTaller.agregarAReserva(descripcion, vidaUtil);
			} catch (BoundsException e) {}	    	
		}
		
		if(!cargo){
			pantallaTaller.agregarAReserva("- Lista ", "Vacía -");
		}
	}
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();
		
		ParteAuto parte;
		Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
        tabla = programaAuto.getUsuario().getAuto().getHashDePartes();
        Enumeration<ParteAuto> enumeracion = tabla.elements();
        String vidaUtil,nombreParte;
         
        while(enumeracion.hasMoreElements()) {   
      	   		parte=enumeracion.nextElement();
      	   		try{
                   nombreParte = parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                   vidaUtil = Double.toString(parte.getVidaUtil());
                   pantallaTaller.agregarInformacionAuto(nombreParte, vidaUtil);
      	   		}catch (BoundsException e){}
         }
	}
	
	private void actualizarCatalogo() {
		ArrayList<FabricaDePartes> fabricas = programaAuto.getUnProveedor().getMiCadenaDeFabricas().getMiCadenaDeFabricas();
		Iterator<FabricaDePartes> it = fabricas.iterator();
		String descripcion;
		FabricaDePartes fabrica;
		ArrayList<InformacionDelModelo> productos;
		Iterator<InformacionDelModelo> itProducto;
		int contador = 0;
		while(it.hasNext()){
			contador++;
			fabrica = (FabricaDePartes)it.next();
			productos = fabrica.getModelos();
			itProducto = productos.iterator();
			while(itProducto.hasNext()){
				InformacionDelModelo info = (InformacionDelModelo) itProducto.next();			
				try {
					descripcion = info.getCaracteristica("DESCRIPCION");
		
						pantallaTaller.agregarACatalogo(descripcion);
					
				} catch (BoundsException e) { }
			}
		}
		
		
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("cargar"))
			this.llenarTanque(Double.valueOf(pantallaTaller.obtenerCantidadPanelNafta()));
		  
	}

	private void llenarTanque(double cantidad) {
		try {
			if(cantidad != 0){
			programaAuto.comprarNafta(cantidad);
			this.lanzarMensajeOperacionRealizada();
			}
		} catch (TankIsFullException e1) {
	    	pantallaTaller.generarMensajeError("El tanque esta lleno");		
		} catch (BoundsException e1) {
	    	pantallaTaller.generarMensajeError("No puede cargar esa cantidad de nafta");		
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (NotEnoughMoneyException e1) {
			pantallaTaller.generarMensajeError("No posee el dinero necesario");
		} finally {
			this.actualizarPantallaTaller();
		}
		
	}

	private void lanzarMensajeOperacionRealizada() {
		pantallaTaller.generarMensaje("La operacion ha sido realizada satisfactoriamente");

	}
}
