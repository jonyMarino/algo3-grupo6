package vista;

import javax.swing.JFrame;

public class VistaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public VistaPrincipal() {
		super("Tunnig Car Experience '08");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,675);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);	
	}

}
