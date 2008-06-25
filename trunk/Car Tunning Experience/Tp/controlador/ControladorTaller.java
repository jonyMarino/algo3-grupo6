package controlador;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.ImageIcon;
import excepciones.BoundsException;
import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import programaAuto.Taller;
import proveedorDePartes.fabricas.FabricaDePartes;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;

public class ControladorTaller {
	
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	private ProgramaAuto programaAuto;
	
	public ControladorTaller(Taller taller, ProgramaAuto programaAuto) {
		this.taller = taller;
		this.programaAuto = programaAuto;
	}

	public void cargarPantallaTaller(ImageIcon avatarUsuario) {
		//PANEL DINERO
		pantallaTaller.actualizarInformacionDinero( Double.toString(taller.getUsuario().getDinero()) );
		
		//PANEL PROXIMA PISTA
		pantallaTaller.actualizarInformacionPista( Double.toString(proximaPista.getLongitud()) , proximaPista.getNombre(), Integer.toString(proximaPista.getVelocidadAire()) );
		
		//PANEL RESERVA
		this.actualizarInformacionReserva();
			
		//PANEL NAFTA
		pantallaTaller.actualizarInformacionNafta( taller.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible() , taller.getUsuario().getAuto().getTanqueCombustible().getCapacidad() );
		
		//PANEL USUARIO
		pantallaTaller.actualizarInformacionUsuario( taller.getUsuario().getNombre(), avatarUsuario );
		
		//PANEL AUTO
		this.actualizarInformacionAuto();
		
		//PANEL CATALOGO
		this.actualizarCatalogo();
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	public void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}

	private void actualizarInformacionReserva() {	
		pantallaTaller.limpiarInformacionReserva();
		
		String descripcion,vidaUtil;
		boolean cargo = false;
		
		while (taller.getPartesDeReserva().hasNext()) {
			cargo = true;
			try {
				descripcion = taller.getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
				vidaUtil = Double.toString(taller.getPartesDeReserva().next().getVidaUtil());
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
        tabla = taller.getUsuario().getAuto().getHashDePartes();
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
		
		while(it.hasNext()){
			FabricaDePartes fabrica = (FabricaDePartes)it.next();
			ArrayList<InformacionDelModelo> productos = fabrica.getModelos();
			Iterator<InformacionDelModelo> itProducto = productos.iterator();
			while(it.hasNext()){
				InformacionDelModelo info = (InformacionDelModelo) itProducto.next();
				String descripcion;
				try {
					descripcion = info.getCaracteristica("DESCRIPCION");
					pantallaTaller.agregarAComboCajas(descripcion);
				} catch (BoundsException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
			}
		}
		
	}
}
