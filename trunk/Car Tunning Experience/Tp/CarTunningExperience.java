import programaAuto.ProgramaAuto;
import controlador.ControladorJuego;
import vista.PanelBase;
import vista.VistaPrincipal;

public class CarTunningExperience {

	public static void main(String[] args) {
	
		ProgramaAuto programaAuto = null;
		
		ControladorJuego controlador = new ControladorJuego(programaAuto);
				
		VistaPrincipal vistaPrincipal = new VistaPrincipal();
		PanelBase panelBase = new PanelBase(controlador);
		panelBase.crearPantallaInicio();
			
		vistaPrincipal.add(panelBase);
		vistaPrincipal.setVisible(true);
	}

}
