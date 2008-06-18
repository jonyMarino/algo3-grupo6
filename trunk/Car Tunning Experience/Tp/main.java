import programaAuto.ProgramaAuto;
import vista.PanelBase;
import vista.VistaPrincipal;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ProgramaAuto programaAuto = new ProgramaAuto();
		
		VistaPrincipal vistaPrincipal = new VistaPrincipal();
		PanelBase panelBase = new PanelBase(programaAuto);
		panelBase.crearPantallaInicio();
			
		vistaPrincipal.add(panelBase);
		vistaPrincipal.setVisible(true);
	}
	
}
