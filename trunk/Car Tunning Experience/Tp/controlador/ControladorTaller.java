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
		pantallaTaller.actualizarInformacionDinero( taller.getUsuario().getDinero() );
		
		//PANEL PROXIMA PISTA
		pantallaTaller.actualizarInformacionPista( proximaPista );
		
		//PANEL RESERVA
		pantallaTaller.actualizarInformacionReserva( taller );
			
		//PANEL USUARIO
		pantallaTaller.actualizarInformacionUsuario( taller.getUsuario() );
		
		//PANEL AUTO
		pantallaTaller.actualizarInformacionAuto( taller.getUsuario().getAuto() );
	
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	public void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}
	
}
