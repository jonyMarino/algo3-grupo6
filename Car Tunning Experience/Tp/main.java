import programaAuto.ProgramaAuto;
import vistas.PanelPrincipal;
import vistas.VistaPrincipal;
import controlador.Controlador;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ProgramaAuto programaAuto = new ProgramaAuto();
		Controlador controlador = new Controlador(programaAuto);

		VistaPrincipal vistaPrincipal = new VistaPrincipal( );
		PanelPrincipal panelPrincipal = new PanelPrincipal();
		
		vistaPrincipal.add(panelPrincipal);
		
		vistaPrincipal.setVisible(true);
	}
	
}
