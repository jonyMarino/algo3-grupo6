package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedorDePartes.fabricas.InformacionDelModelo;

public class PantallaTaller extends JPanelConImagen implements ActionListener {

	private DefaultComboBoxModel listaPartesEnTaller;
	private static final long serialVersionUID = 1L;

	
	public PantallaTaller(PanelBase panelBase) {
		
		super();
		
		this.setImage("FondoTransparente");
		
		this.setPreferredSize(new Dimension(900,675));
	
		this.setLayout(new GridBagLayout());
		
		crearPaneCatalogo();
		crearPanelAuto();
		crearPanelPistaDineroBodega();
		
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

	private void crearPaneCatalogo(){
		
		Color nc = new Color(0,0,0,50);
   
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
	
	private void crearPanelPistaDineroBodega(){
		
		JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setPreferredSize(new Dimension(250,100));
	    Color nc = new Color(176,196,222);
	    
	    JPanel panelDinero = new JPanel();
        panelDinero.setBackground(nc);
        JTextField boxDinero = new JTextField("dinero del usuario",20);
		boxDinero.setBackground(Color.white);
		panelDinero.add(boxDinero);
        tabbedPane.addTab("Dinero",panelDinero);

        tabbedPane.addTab("Bodega",crearPanelBodega());
         
	    JPanel panelPista = new JPanel();
	    panelPista.setLayout(new GridBagLayout());
	    panelPista.setBackground(nc);
        JTextField boxPista = new JTextField("datos de la pista",20);
		boxPista.setBackground(Color.white);
		panelPista.add(boxPista);
	    tabbedPane.addTab("Proxima Pista",panelPista);
	    
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx =1;
	    c.gridy =0;
	    c.gridheight =1;
	    c.anchor = GridBagConstraints.SOUTH;
	    this.add(tabbedPane,c);
    }

	private JPanel crearPanelBodega(){
		listaPartesEnTaller = new DefaultComboBoxModel();
		Color nc = new Color(176,196,222);
		  
		JPanel panelBodega = new JPanel();
	    panelBodega.setBackground(nc);

	    JComboBox comboBox = new JComboBox();
	    agregarABodega("Escape");
	    comboBox.setModel(listaPartesEnTaller);
	  
	    /* comboBox.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	            	JComboBox combo = (JComboBox)e.getSource();
	            	  String parte = (String)combo.getSelectedItem();
	                  System.out.println(parte);
	            }
	        });
	    */
	    panelBodega.add(comboBox);

	    Boton botonCambiar = new Boton("Cambiar");
	    panelBodega.add(botonCambiar);
		
		return panelBodega;
	}
	
	private void agregarABodega(String nombreParte){
		 listaPartesEnTaller.addElement(nombreParte);
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
	 
		/*ESTA FABRICA ES TEMPORAL DE PRUEBA NOMAS*/
		 FabricaDeEscapes  fabricaEscapes = new FabricaDeEscapes();
	     ArrayList<InformacionDelModelo> temporal = fabricaEscapes.getModelos();
	    
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3,2));
		
		for (int i=0;i<3;i++)
	    	for (int j=0;j<3;j++)
	    			panel.add(crearCombosPartes(temporal));
	     return panel;
	}

	
	public JComboBox crearCombosPartes( ArrayList<InformacionDelModelo> contenedorDeListas) {
		/*SIGUE EN PROCESO ESTE METODO, PORQUE EN LA TEORIA ESTE RECIBE TODAS LAS LISTAS DE LAS FABRICAS
		PARA CREAR LOS COMBO BOX*/
		
        JComboBox combo = new JComboBox();
        Iterator it;
        InformacionDelModelo c;
		it = contenedorDeListas.iterator();
		while (it.hasNext()) {
			c = (InformacionDelModelo)it.next();
	        c.getInformacionDeEstaParte().get("DESCRIPCION");
	        combo.addItem(c.getInformacionDeEstaParte().get("DESCRIPCION")); //GUARDA EN EL COMBO EL NOMBRE -OSEA LA DESCRIPCION-
		}    
	     
     
        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 
            }
        });

        return combo;
    }
	
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
