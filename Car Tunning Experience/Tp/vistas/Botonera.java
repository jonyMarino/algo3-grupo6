package vistas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Botonera extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private List<Boton> botones;
		
	public Botonera(PanelPrincipal panelPrincipal){

		this.setBotones(new ArrayList<Boton>());
		
		Boton botonNuevaPartida = new Boton("Nueva Partida");
		botonNuevaPartida.addActionListener(panelPrincipal);
		
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
	
	private List<Boton> getBotones(){
		return this.botones;
	}
	
	private void setBotones(List<Boton> lista){
		this.botones = lista;
	}
	
	public void agregarBoton(Boton boton) {
		this.getBotones().add(boton);
		
	}

	public void sacarBoton(Boton boton) {
		this.getBotones().remove(boton);
		
	}

	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}


