package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import controlador.ControladorJuego;

public class PantallaContinuarPartida extends JPanelConImagen {

private static final long serialVersionUID = 1L;
	
	public PantallaContinuarPartida(ControladorJuego controladorJuego) {
		super();
		this.setImage("FondoTransparente");
		
		//BOTONERA
		BotoneraContinuarPartida botonera = new BotoneraContinuarPartida(controladorJuego);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1; 
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(botonera, c);	
	}

	public class BotoneraContinuarPartida extends JPanel {

		private static final long serialVersionUID = 1L;

		public BotoneraContinuarPartida(ControladorJuego controladorJuego) {

			Boton botonVolver = new Boton("Volver");
			botonVolver.addActionListener(controladorJuego);

			Boton botonAceptar = new Boton("Cargar Partida");
			//botonAceptar.addActionListener(controladorJuego);

			this.add(botonVolver);
			this.add(botonAceptar);

			setOpaque(false);
		}

	}
}
