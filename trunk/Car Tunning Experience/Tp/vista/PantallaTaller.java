package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PantallaTaller extends JPanelConImagen implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public PantallaTaller(PanelBase panelBase) {
		
		super();
		
		this.setImage("FondoTransparente");
		
		this.setPreferredSize(new Dimension(900,675));
	
		this.setLayout(new GridBagLayout());
		
		crearPanelBodegaCatalogo();
		crearPanelAuto();
		crearPanelPista();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=2;
    	c.gridy=2;
    	Insets in=new Insets(0,50,20,0);
    	c.insets=in;
		c.anchor = GridBagConstraints.SOUTHEAST;
		
		Boton botonComenzar = new Boton("Comenzar Carrera");
		botonComenzar.addActionListener(panelBase);
		this.add(botonComenzar, c);
	}

	private void crearPanelBodegaCatalogo(){
		
		Color nc = new Color(0,0,0,50);
    /*El JTabbedPane es el panel que tiene las pestañas de Bodega y Catalogo*/
		JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(600,200));
  
        JPanel panelCatalogo = new JPanel();
        panelCatalogo.setBackground(nc);
        panelCatalogo.setLayout(new GridBagLayout());
        
        GridBagConstraints c4 = new GridBagConstraints();
    	Insets in=new Insets(0,30,0,0);
    	c4.insets=in;
        panelCatalogo.add(contenedorPartes(),c4);
       
        Boton botonComprar = new Boton("Comprar");
        c4 = posicionBoton();
        panelCatalogo.add(botonComprar,c4);
        tabbedPane.addTab("Catalogo",panelCatalogo);
      
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx=0;
    	c.gridy=2;
    	c.weighty = 1.0;
    	c.gridwidth =2;
    	c.anchor = GridBagConstraints.SOUTH;
    	
        this.add(tabbedPane,c);
    }
	
	private void crearPanelPista(){
		
		JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setPreferredSize(new Dimension(250,100));
	     
	    JPanel panelPista = new JPanel();
	    panelPista.setLayout(new GridBagLayout());
	     
	    Color nc = new Color(0,0,0,50);
	    panelPista.setBackground(nc);
	    
	    JPanel panelDinero = new JPanel();
        panelDinero.setBackground(nc);
        tabbedPane.addTab("Dinero",panelDinero);
        
        JPanel panelBodega = new JPanel();
        panelBodega.setBackground(nc);
        tabbedPane.addTab("Bodega",panelBodega);
        
	    tabbedPane.addTab("Proxima Pista",panelPista);
	    
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx =1;
	    c.gridy =0;
	    c.gridheight =1;
	    c.anchor = GridBagConstraints.SOUTH;
	    this.add(tabbedPane,c);
    }
	
	private void crearPanelAuto() {
        
		JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setPreferredSize(new Dimension(300,300));
	    
	    JPanel panelAuto = new JPanel();
	    panelAuto.setLayout(new GridBagLayout());
	   
	    GridBagConstraints c2 = posicionBoton();
	     
	    Boton botonCargar = new Boton("Cargar Nafta");
	    botonCargar.setOpaque(false);
	    Color nc = new Color(0,0,0,50);
	    panelAuto.setBackground(nc);
	      
	    panelAuto.add(botonCargar,c2);
	    tabbedPane.addTab("Mi Auto",panelAuto);
        
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx =0;
	    c.gridy =0;
	    c.weighty = 1.0;
	    c.anchor = GridBagConstraints.SOUTH;
	    this.add(tabbedPane,c);
    }
	
	private GridBagConstraints posicionBoton(){
		
		GridBagConstraints ubicacion = new GridBagConstraints();
	    Insets in=new Insets(0,0,5,5);
	    ubicacion.insets=in;
	    ubicacion.weightx = 1.0;
	    ubicacion.weighty = 1.0;
	    ubicacion.anchor = GridBagConstraints.SOUTHEAST;
		
		return ubicacion;
	}

	private JPanel contenedorPartes(){
	 
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		String[] lista={"NombreDeParte","ModeloA","ModeloB","ModeloC","ModeloD","ModeloE","ModeloF"};
		for (int i=0;i<3;i++)
	    	for (int j=0;j<3;j++){
	    		JComboBox petList = new JComboBox(lista);
	    		petList.setOpaque(false);
	    		panel.add(petList);
	         }
	    
	     return panel;
}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
