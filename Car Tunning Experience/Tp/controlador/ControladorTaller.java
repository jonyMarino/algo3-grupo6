package controlador;

import pista.Pista;
import taller.Taller;
import vista.PantallaTaller;

public class ControladorTaller {
	
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	
	public ControladorTaller(Taller taller) {
		this.taller = taller;
	}

	public void actualizarPantallaTaller() {
		//PANEL DINERO
		pantallaTaller.actualizarPanelDinero( taller.getUsuario().getDinero() );
		
		//PANEL PROXIMA PISTA
		pantallaTaller.actualizarPanelPista( proximaPista );
		
		//PANEL BODEGA
		//pantallaTaller.actualizarListaReserva( taller.getPartesDeReserva() );
			
		//PANEL AVATAR Y NOMBRE USUARIO
		pantallaTaller.actualizarInformacionUsuario( taller.getUsuario() );
		
		//INFORMACION AUTO
		pantallaTaller.actualizarInformacionAuto( taller.getUsuario().getAuto() );
	
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	public void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}
	
}
