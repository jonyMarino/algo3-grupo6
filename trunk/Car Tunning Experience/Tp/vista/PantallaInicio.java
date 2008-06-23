package vista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controlador.ControladorJuego;

public class PantallaInicio extends JPanelConImagen {

	private static final long serialVersionUID = 1L;
	
	public PantallaInicio(ControladorJuego controladorJuego) {
		super();
		this.setImage("FondoPantallaInicio");
		
		BotoneraInicio botonera = new BotoneraInicio(controladorJuego);

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1; 
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(botonera, c);	
	}
	
	public class BotoneraInicio extends JPanel{

		private static final long serialVersionUID = 1L;

		public BotoneraInicio(ControladorJuego controladorJuego) {

			Boton botonNuevaPartida = new Boton("Nueva Partida");
			botonNuevaPartida.addActionListener(controladorJuego);

			Boton botonContinuarPartida = new Boton("Continuar Partida");

			Boton botonSalir = new Boton("Salir");
			botonSalir.addActionListener(new CloseListener());

			botonNuevaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
			botonContinuarPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
			botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

			this.add(botonNuevaPartida);
			this.add(botonContinuarPartida);
			this.add(botonSalir);

			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			this.setOpaque(false);
		}

		//Clase auxiliar para escuchar el evento de cerrado de la ventana
		public class CloseListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		}

	}

}
