package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

public class PanelPrincipal extends JPanel implements  ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imgFondo;
	
	public PanelPrincipal() {
		super();
		preInit();
		initComponents();
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(900,600));
		Botonera botonera = new Botonera(this);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1; 
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(botonera, c);
			
	}
	
	public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		this.add(panel);
	}

	private void initComponents() {
	}

	private void preInit(){
		imgFondo = new ImageIcon(getClass().getResource("/vistas/images/FondoPantalla.jpg")).getImage();
	}
	
	public void paintComponent(Graphics g) {
		 g.drawImage(imgFondo,0,0,null);
	}
	
}
