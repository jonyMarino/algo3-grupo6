package controlador;

import javax.swing.ImageIcon;

import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import programaAuto.Taller;
import vista.PantallaTaller;

public class ControladorTaller {
	
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	private ImageIcon avatarUsuario;
	private String nombreUsuario;
	
	public ControladorTaller(ProgramaAuto programaAuto, String nombreUsuario , ImageIcon avatarUsuario) {
		this.avatarUsuario = avatarUsuario;
		this.nombreUsuario = nombreUsuario;
	}

	public void actualizarPantallaTaller() {
		//PANEL DINERO
		pantallaTaller.actualizarInformacionDinero( "$$$" );
		
		//PANEL PROXIMA PISTA
		//pantallaTaller.actualizarInformacionPista( proximaPista );
		
		//PANEL RESERVA
		//pantallaTaller.actualizarInformacionReserva( taller );
			
		//PANEL NAFTA
		//pantallaTaller.actualizarInformacionNafta( taller.getUsuario().getAuto() );
		
		//PANEL USUARIO
		pantallaTaller.actualizarInformacionUsuario( nombreUsuario, avatarUsuario );
		
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
