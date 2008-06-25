package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controlador.ControladorJuego;

public class PantallaCarrera extends JPanel {

	private static final long serialVersionUID = 1L;

	public PantallaCarrera(ControladorJuego controladorJuego) {
		super();

		this.setBorder(BorderFactory.createEmptyBorder(2, 0, 2,0));
		this.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);


	}

}