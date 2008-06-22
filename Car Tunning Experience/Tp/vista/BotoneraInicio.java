package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BotoneraInicio extends JPanel{

	private static final long serialVersionUID = 1L;

	public BotoneraInicio(PanelBase panelBase) {

		Boton botonNuevaPartida = new Boton("Nueva Partida");
		botonNuevaPartida.addActionListener(panelBase.getControlador());

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

		setOpaque(false);
	}

	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}
