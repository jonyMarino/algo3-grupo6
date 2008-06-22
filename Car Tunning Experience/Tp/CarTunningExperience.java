import controlador.ControladorJuego;
import vista.PanelBase;
import vista.VistaPrincipal;

public class CarTunningExperience {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ControladorJuego controlador = new ControladorJuego();
		
		VistaPrincipal vistaPrincipal = new VistaPrincipal();
		PanelBase panelBase = new PanelBase(controlador);
		panelBase.crearPantallaInicio();
			
		vistaPrincipal.add(panelBase);
		vistaPrincipal.setVisible(true);

	}

}
