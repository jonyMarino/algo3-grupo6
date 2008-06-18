package vista;

import java.util.Observer;
import javax.swing.JPanel;
import programaAuto.Observado;

public class BotoneraUsuario extends JPanel implements Observado {

	private static final long serialVersionUID = 1L;

	public BotoneraUsuario(PanelBase panelBase) {

		Boton botonVolver = new Boton("Volver");
		botonVolver.addActionListener(panelBase);

		Boton botonAceptar = new Boton("Aceptar");
		//PARA QUE VEAS :P
		botonAceptar.addActionListener(panelBase);
		//botonAceptar.addActionListener(panelBase.getProgramaAuto());

		this.add(botonVolver);
		this.add(botonAceptar);

		setOpaque(false);
	}

	public void agregarObservador(Observer obs) {
		// TODO Auto-generated method stub
		
	}

	public void cambie() {
		// TODO Auto-generated method stub
		
	}

	public void sacarObservador(Observer obs) {
		// TODO Auto-generated method stub
		
	}

}