package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PantallaInicio extends JPanelConImagen {

	private static final long serialVersionUID = 1L;
	
	public PantallaInicio(PanelBase panelBase) {
		super();
		Image imgFondo = new ImageIcon(getClass().getResource("/vista/images/FondoPantallaInicio.jpg")).getImage();
		this.setImage(imgFondo);

		this.setPreferredSize(new Dimension(900,675));
		BotoneraInicio botonera = new BotoneraInicio(panelBase);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1; 
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(botonera, c);	
	}
	
}
