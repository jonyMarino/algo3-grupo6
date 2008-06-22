package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import programaAuto.ProgramaAuto;
import programaAuto.Usuario;
import vista.Boton;
import vista.PanelBase;
import vista.PantallaCarrera;
import vista.PantallaTaller;
import vista.PantallaUsuario;

public class ControladorJuego implements ActionListener {
	
	private ProgramaAuto programaAuto;
	private PanelBase panelBase;
	private ControladorTaller controladorTaller;
	
	public ControladorJuego(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;	
	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	public void setPanelBase(PanelBase panelBase) {
		this.panelBase = panelBase;
	}
	
	public void actionPerformed(ActionEvent e) {
		Boton boton = (Boton)e.getSource();
		if (boton.getText().equals("Nueva Partida"))
			panelBase.crearPantalla( new PantallaUsuario(panelBase));
		if (boton.getText().equals("Volver"))
			panelBase.pantallaAnterior();
		if (boton.getText().equals("Aceptar"))
			inicializarJuego();
		if (boton.getText().equals("Comenzar Carrera"))
			panelBase.crearPantalla( new PantallaCarrera(panelBase));
}
	
	private void inicializarJuego(){
		String nombre = ((PantallaUsuario)panelBase.getPantallaActual()).getPanelIngreso().getBox().getText();
		Usuario unUsuario = this.programaAuto.nuevoUsuario(nombre);
		unUsuario.setAvatar(((PantallaUsuario)panelBase.getPantallaActual()).getComboBoxCars().getSeleccionado());
		controladorTaller = new ControladorTaller();
		panelBase.crearPantalla( new PantallaTaller(panelBase, controladorTaller) );
	}

}
