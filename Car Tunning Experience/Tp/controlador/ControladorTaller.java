package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import taller.Taller;
import vista.Boton;
import vista.PanelBase;
import vista.PantallaCarrera;

public class ControladorTaller implements ActionListener {
	
	private PanelBase panelBase;
	private Taller taller;
	
	public ControladorTaller(PanelBase panelBase, Taller taller) {
		this.panelBase = panelBase;
		this.taller = taller;
	}
	
	public void actionPerformed(ActionEvent e) {
		Boton boton = (Boton)e.getSource();
		if (boton.getText().equals("Comenzar Carrera"))
			panelBase.crearPantalla( new PantallaCarrera(panelBase.getcontroladorJuego()));
	}
	
}
