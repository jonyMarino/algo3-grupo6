package controlador;

import programaAuto.ProgramaAuto;
import java.awt.event.*;

public class Controlador {

	private ProgramaAuto programaAuto;
	
	public Controlador(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
	}
	
	private class EscuchaBotonNuevaPartida implements ActionListener {	
		
		public void actionPerformed(ActionEvent e) {	
			//programaAuto.;
		}
	}
	
	public ActionListener getListenerBotonSubir() {
		return new EscuchaBotonNuevaPartida();
	}
	
}
