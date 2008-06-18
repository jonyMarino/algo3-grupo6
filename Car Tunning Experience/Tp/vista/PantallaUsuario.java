package vista;

import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PantallaUsuario extends JPanelConImagen {

	private static final long serialVersionUID = 1L;

	public PantallaUsuario(PanelBase panelBase) {
		super();
		//FONDO
		Image imgFondo = new ImageIcon(getClass().getResource("/vista/images/FondoTransparente.jpg")).getImage();
		this.setImage(imgFondo);

		//PANEL USUARIO
		JPanel panelUsuario = new JPanel();
			//PANEL INGRESO
			CuadroIngresoUsuario panelIngreso = new CuadroIngresoUsuario();
			panelIngreso.setOpaque(false);
			//PERSONAJES
			ComboBoxCars personajes = new ComboBoxCars();
   			personajes.setOpaque(false);
   		panelUsuario.add(panelIngreso);
		panelUsuario.add(personajes);
		panelUsuario.setOpaque(false);

		//BOTONERA
       	BotoneraUsuario botoneraUsuario = new BotoneraUsuario(panelBase);

		this.add(panelUsuario);
		this.add(botoneraUsuario);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   		this.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));
	}

}
