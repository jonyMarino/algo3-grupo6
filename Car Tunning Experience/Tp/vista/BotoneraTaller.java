package vista;

import javax.swing.JPanel;
import programaAuto.Observado;
import java.util.Observer;

public class BotoneraTaller extends JPanel implements Observado {
		
	private static final long serialVersionUID = 1L;
	private PanelBase panelBase;
		
	public BotoneraTaller(PanelBase panelBase) {
			
		this.setPanelBase(panelBase);
			
		Boton botonComprar = new Boton("Comprar");
		Boton botonCargar = new Boton("Cargar Nafta");
		Boton botonComenzar = new Boton("Comenzar Carrera");
		botonComenzar.addActionListener(panelBase);
			
		this.add(botonComprar);
		this.add(botonCargar);
		this.add(botonComenzar);
			
		setOpaque(false);
	}

	public PanelBase getPanelBase() {
		return panelBase;
	}

	public void setPanelBase(PanelBase panelBase) {
		this.panelBase = panelBase;
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

