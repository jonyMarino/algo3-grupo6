package vista;

import javax.swing.JPanel;

public class BotoneraUsuario extends JPanel{

	private static final long serialVersionUID = 1L;

	public BotoneraUsuario(PanelBase panelBase) {

		Boton botonVolver = new Boton("Volver");
		botonVolver.addActionListener(panelBase.getControlador());

		Boton botonAceptar = new Boton("Aceptar");
		//PARA QUE VEAS :P
		botonAceptar.addActionListener(panelBase.getControlador());

		this.add(botonVolver);
		this.add(botonAceptar);

		setOpaque(false);
	}

}