package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pista.Pista;
import taller.Taller;
import vista.Boton;
import vista.PanelBase;
import vista.PantallaCarrera;
import vista.PantallaTaller;

public class ControladorTaller implements ActionListener {
	
	private PanelBase panelBase;
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	
	public ControladorTaller(PanelBase panelBase, Taller taller) {
		this.panelBase = panelBase;
		this.taller = taller;
	}
	
	public void actionPerformed(ActionEvent e) {
		Boton boton = (Boton)e.getSource();
		if (boton.getText().equals("Comenzar Carrera"))
			panelBase.crearPantalla(new PantallaCarrera(panelBase.getcontroladorJuego()));
	}
	
	public void actualizarPantallaTaller() {
		//PANEL DINERO
		pantallaTaller.actualizarPanelDinero( taller.getUsuario().getDinero() );
		
		//PANEL PROXIMA PISTA
		pantallaTaller.actualizarPanelPista( proximaPista.getLongitud(), 
				                             proximaPista.getVelocidadAire(),
		                                     proximaPista.getCoeficienteDeRozamientoRelativo());
		
		//PANEL BODEGA
		/*
		 * pantallaTaller.actualizarPanelBodega(datosBodega);
		 */
		
		//INFORMACION AUTO
		/*
		 *  pantallaTaller.actualizarInformacionAuto();
		 */
		
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	public void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}
	
}
