package controlador;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.ImageIcon;



import excepciones.BoundsException;
import programaAuto.Pista;
import programaAuto.Taller;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;

public class ControladorTaller {
	
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	
	public ControladorTaller(Taller taller) {
		this.taller = taller;
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
		
		while (taller.getPartesDeReserva().hasNext()){
			cargo = true;
			try {
				descripcion = taller.getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
				vidaUtil = Double.toString(taller.getPartesDeReserva().next().getVidaUtil());
				pantallaTaller.agregarAReserva(descripcion, vidaUtil);
			} catch (BoundsException e) {}	    	
		}
		
		if(!cargo){
			pantallaTaller.agregarAReserva("- Lista ", "Vac�a -");
		}
	}
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();
		
		ParteAuto parte;
		Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
        tabla = taller.getUsuario().getAuto().getHashDePartes();
        Enumeration<ParteAuto> enumeracion = tabla.elements();
        String vidaUtil,nombreParte;
         
        while(enumeracion.hasMoreElements()){   
      	   		parte=enumeracion.nextElement();
      	   		try{
                   nombreParte = parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                   vidaUtil = Double.toString(parte.getVidaUtil());
                   pantallaTaller.agregarInformacionAuto(nombreParte, vidaUtil);
      	   		}catch (BoundsException e){}
         }
	}
}
