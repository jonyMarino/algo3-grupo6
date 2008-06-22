package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import taller.Taller;
import vista.PanelBase;

public class ControladorTaller implements ActionListener {
	
	private PanelBase panelBase;
	private Taller taller;
	
	public ControladorTaller(PanelBase panelBase, Taller taller) {
		this.panelBase = panelBase;
		this.taller = taller;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
