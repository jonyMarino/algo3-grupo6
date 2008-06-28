package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controlador.ControladorJuego;

public class PantallaContinuarPartida extends JPanelConImagen {

private static final long serialVersionUID = 1L;
	
	JPanel panelPartidas;
	ButtonGroup bgroup;

	public PantallaContinuarPartida(ControladorJuego controladorJuego) {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setImage("FondoTransparente");
		c.gridx=0;
		c.gridy=0;
		c.anchor = GridBagConstraints.CENTER;
		
		this.add(armarPanelPartidas(),c);
		//BOTONERA
		BotoneraContinuarPartida botonera = new BotoneraContinuarPartida(controladorJuego);
		c.gridx=0;
		c.gridy=1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(botonera, c);

	}

	public class BotoneraContinuarPartida extends JPanel {

		private static final long serialVersionUID = 1L;

		public BotoneraContinuarPartida(ControladorJuego controladorJuego) {

			Boton botonVolver = new Boton("Volver");
			botonVolver.setActionCommand("volver");
			botonVolver.addActionListener(controladorJuego);

			Boton botonAceptar = new Boton("Cargar Partida");
			//botonVolver.setActionCommand("cargarPartida");
			//botonAceptar.addActionListener(controladorJuego);

			this.add(botonVolver);
			this.add(botonAceptar);

			setOpaque(false);
		}

	}
	
	private ScrollPane armarPanelPartidas(){

		ScrollPane scroller = new ScrollPane( ScrollPane.SCROLLBARS_ALWAYS );
		scroller.setPreferredSize(new Dimension(350,200));
		panelPartidas = new JPanel();
		panelPartidas.setLayout(new BoxLayout(panelPartidas,BoxLayout.Y_AXIS));
		panelPartidas.setPreferredSize(new Dimension(500,550));
		bgroup = new ButtonGroup();
		//de muestra nomas
		JRadioButton btn = new JRadioButton("Partida1");
		bgroup.add(btn);
		JRadioButton btn1 = new JRadioButton("Partida2");
		bgroup.add(btn1);
		panelPartidas.add(btn);
		panelPartidas.add(btn1);
		//
		scroller.add (panelPartidas);
		return scroller;

	}
	
	public void agregarPartida(String nombrePartida){
		JRadioButton btnPartida = new JRadioButton(nombrePartida);
		bgroup.add(btnPartida);
		panelPartidas.add(btnPartida);	
	}

	public JPanel getPanelPartidas() {
		return panelPartidas;
	}

	public ButtonGroup getBgroup() {
		return bgroup;
	}
	
	public String partidaSeleccionada(){	
		return bgroup.getSelection().getSelectedObjects().toString();
	}
}
