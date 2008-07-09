package vista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import controlador.ControladorJuego;

/**
*
* Clase que se encarga de modelar la vista de la pantalla inicial del juego.
*
*/

public class PantallaInicio extends JPanelConImagen {	
	
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	
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
	
	public JFileChooser getfileChooser() {
		return fileChooser;
	}
	
	public String buscarArchivo(FileFilter filtro){	
		JFileChooser chooser = getfileChooser();
	    chooser.setFileFilter(filtro);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {      
	       return chooser.getSelectedFile().getPath();
	    }
	    return "";
	}
	
	private class BotoneraInicio extends JPanel{
			
		private static final long serialVersionUID = 1L;

		private BotoneraInicio(ControladorJuego controladorJuego) {

			Boton botonNuevaPartida = new Boton("Nueva Partida");
			botonNuevaPartida.setActionCommand("nueva");
			botonNuevaPartida.addActionListener(controladorJuego);

			Boton botonContinuarPartida = new Boton("Continuar Partida");
			botonContinuarPartida.setActionCommand("continuar");
			fileChooser = new JFileChooser();
			botonContinuarPartida.addActionListener(controladorJuego);

			Boton botonSalir = new Boton("Salir");
			botonSalir.setActionCommand("salir");
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

		private class CloseListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		
	}

	
}

	

