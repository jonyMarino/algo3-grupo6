package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PantallaUsuario extends JPanelConImagen {

	private static final long serialVersionUID = 1L;

	public PantallaUsuario(PanelBase panelBase) {
		super();
		//FONDO
		this.setImage("FondoTransparente");
		
		//PANEL USUARIO
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(new BoxLayout(panelUsuario,BoxLayout.Y_AXIS));
		
		
		//PANEL INGRESO
			CuadroIngresoUsuario panelIngreso = new CuadroIngresoUsuario();
			panelIngreso.setOpaque(false);
			//PERSONAJES
			ComboBoxCars personajes = new ComboBoxCars();
   			personajes.setOpaque(false);

   		GridBagConstraints c = new GridBagConstraints();
	    c.gridx =0;
	    c.gridy =0;
	    c.anchor = GridBagConstraints.NORTH;
	    panelUsuario.setLayout(new GridBagLayout());
	    panelUsuario.add(panelIngreso,c);
	    c.gridy =1;
	    panelUsuario.add(personajes,c);
		panelUsuario.setOpaque(false);

		//BOTONERA
       	BotoneraUsuario botoneraUsuario = new BotoneraUsuario(panelBase);

		this.add(panelUsuario);
		this.add(botoneraUsuario);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   		this.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));
	}

}

