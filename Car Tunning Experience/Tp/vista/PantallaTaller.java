package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.Border;

import proveedores.proveedorDePartes.fabricas.InformacionDelModelo;
import proveedores.proveedorDePartes.fabricas.RegistroDeModelos;
import controlador.ControladorJuego;
import controlador.ControladorTaller;

/**
*
* Clase que se encarga de modelar la vista de la pantalla taller.
*
*/

public class PantallaTaller extends JPanelConImagen {

	   private JPanel panelAutoActual;
       private JPanel panelPista;
       private JPanel panelUsuario;
       private JPanel panelCatalogo;
       private JPanel panelNafta;
       private JLabel labelDinero;
       private JLabel labelMensaje;
       private JTextArea textoInformacionParte;
       private JLabel labelPrecio;
       private JLabel labelPrecioNafta;
       private JSpinner cantidadSeleccionada = new JSpinner();
       private JComboBox comboPartesReserva;    
       private JComboBox comboParteARemover; 
       private JComboBox catalogo;
       private ControladorTaller controladorTaller;;
       private static final long serialVersionUID = 1L;
       private Hashtable<String,String> reservas = new Hashtable<String,String>();
     
       public PantallaTaller(ControladorJuego controladorJuego) {
    	   super();
           this.controladorTaller = controladorJuego.getControladorTaller();
           
           this.setImage("FondoTransparente");
               
           this.setLayout(new GridBagLayout());
               
           crearPanelCatalogo();
           crearPanelAuto();
           crearPanelPistaDineroBodega();
           crearPanelUsuario();
           crearPanelMensajes();
           crearPanelInformacion();          
               
           GridBagConstraints c = new GridBagConstraints();
           c.gridx=2;
           c.gridy=2;
           Insets in=new Insets(170,20,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTH;
              
           Boton botonComenzar = new Boton("Comenzar Carrera");
           botonComenzar.addActionListener(controladorJuego);
           botonComenzar.setActionCommand("comenzar");
           this.add(botonComenzar, c);
           Boton botonGuardar = new Boton("Guardar Partida");
           botonGuardar.addActionListener(controladorJuego);
           
           botonGuardar.setActionCommand("guardar");
           c.gridx=2;
           c.gridy=2;
           Insets in2=new Insets(200,20,0,0);
           c.insets=in2;
           c.anchor = GridBagConstraints.NORTH;
           this.add(botonGuardar, c);
           
       }
       
       /* PANEL AUTO */         
       public void limpiarInformacionAuto() {
    	   panelAutoActual.removeAll();
       }
       
       public void agregarInformacionAuto(String nombreParte, String vidaUtil, String ubicacion) { 
    	   JLabel jParteAuto = new JLabel();
    	   jParteAuto.setText(ubicacion + ":  " +nombreParte+ "   " +vidaUtil);
    	   panelAutoActual.add(jParteAuto);
       }
       
       /*PANEL USUARIO*/             
       public void actualizarInformacionUsuario(String nombreUsuario, ImageIcon avatarUsuario) {
    	   panelUsuario.removeAll();  	   
    	   
    	   JLabel jUsuario = new JLabel("JUGADOR ACTUAL:  " + nombreUsuario);
    	   jUsuario.setIcon( avatarUsuario );
    	   jUsuario.setHorizontalTextPosition(JLabel.CENTER);
    	   jUsuario.setVerticalTextPosition(JLabel.NORTH);
    	   
           panelUsuario.add(jUsuario);         
       }

       /* PANEL RESERVAS */
       public void agregarAReserva(String nombre,String vidaUtil){
    	   String nombreEnReserva=nombre + " " + vidaUtil ;
    	   reservas.put(nombreEnReserva, nombre);
    	   comboPartesReserva.addItem( nombreEnReserva );
    	   
       }
       
       public void agregarARemover(String parteARemover){
    	   comboParteARemover.addItem(parteARemover);   
       }
       
       public String obtenerReservaSeleccionada(){
    	   String nombreEnReserva= (String)comboPartesReserva.getSelectedItem();
    	   return reservas.get(nombreEnReserva);
       }
       
       public String obtenerParteARemover(){ 	
    	   return (String)comboParteARemover.getSelectedItem();
       }
       
       public void limpiarInformacionReserva() {
    	   comboPartesReserva.removeAllItems();
       }
      
       /* PANEL DINERO */     
       public void actualizarInformacionDinero(String dinero){ 	   
    	   this.labelDinero.setText(dinero + " Algo$");
       }
          
       /* PANEL NAFTA */   
    	public void actualizarInformacionNafta(Double cantidad, Double capacidad, String precio){  	   
    	   panelNafta.removeAll();
    	    	   
    	   JProgressBar barraTanque = new JProgressBar(0, (int)Math.floor(capacidad));
    	   barraTanque.setPreferredSize(new Dimension(227,20));
    	   barraTanque.setValue((int)Math.floor(cantidad));
    	   barraTanque.setStringPainted(true);
    	   double cantidadRedondeada =   Math.round(cantidad*Math.pow(10,2))/Math.pow(10,2);
    	   barraTanque.setString( Double.toString(cantidadRedondeada) + " / " + Double.toString(capacidad) + " Litros");
    	   
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =0; 
           c.gridwidth=2;
           c.gridy =0;
           c.anchor = GridBagConstraints.CENTER;
           panelNafta.add(barraTanque,c);   
                              
           Boton botonCargar = new Boton("Cargar Nafta");
           if(capacidad.equals(cantidad))
        	   botonCargar.setEnabled(false);
           botonCargar.addActionListener(controladorTaller);
           botonCargar.setActionCommand("cargar");
           botonCargar.setOpaque(false);
                  	   
           SpinnerModel model = new SpinnerNumberModel(0,-capacidad,capacidad+10, 0.5);         
           cantidadSeleccionada.setModel(model);
          
           JLabel cantidadLitros = new JLabel(" Litros");
           c.gridwidth=0;
           c.gridx =0;
           c.gridy =2;
           c.anchor = GridBagConstraints.LAST_LINE_END;
           
           panelNafta.add(botonCargar,c);
           c.gridx =1;
           c.gridy =1;
           c.anchor = GridBagConstraints.CENTER;
           panelNafta.add(cantidadSeleccionada,c); 
           
           labelPrecioNafta = new JLabel(precio + " $ / litro ");
           labelPrecioNafta.setMinimumSize(new Dimension(30, 30));
           c.anchor = GridBagConstraints.WEST;
           panelNafta.add(labelPrecioNafta,c);
           
           c.gridx =1;
           c.gridy =1;
           c.anchor = GridBagConstraints.EAST;
           panelNafta.add(cantidadLitros,c); 

       }
       
       public void cambiarPrecioNafta(String precio){
    	   labelPrecioNafta.setText(precio);	  
       }
       
       public String obtenerCantidadNafta() {
    	   return(cantidadSeleccionada.getValue().toString());
       }
           
       /* PANEL PISTA */      
       public void actualizarInformacionPista(String longitud, String superficie, String velocidad){ 
    	   panelPista.removeAll();
    	   
    	   JLabel jLongitud = new JLabel("Longitud: " + longitud + " metros");
    	   JLabel jVelocidadAire = new JLabel("Velocidad del aire: " + velocidad + " Km/h");
    	   JLabel jSuperficie = new JLabel("Superficie: " + superficie);  	   
    	  
    	   panelPista.add(jLongitud);
    	   panelPista.add(jVelocidadAire);
    	   panelPista.add(jSuperficie);
    	      	   
           panelPista.setLayout(new BoxLayout(panelPista, BoxLayout.PAGE_AXIS));
       }     
       
       /* PANEL MENSAJE */    
       public void generarMensaje(String mensaje){
           labelMensaje.setText(mensaje);
           labelMensaje.setForeground(Color.BLUE);
       }
       
       public void generarMensajeError(String mensaje){
           labelMensaje.setText(mensaje);
           labelMensaje.setForeground(Color.RED);
       }
       
       /* PANEL CATALOGO */
       public JComboBox obtenerCatalogo() {
    	   return catalogo;
       }

       public void agregarACatalogo(String parteAgregar) {
    	   catalogo.addItem(parteAgregar);
       }
       
       public String obtenerParteAComprar(){ 	
    	   return (String)catalogo.getSelectedItem();
       }
       
       public void cambiarPrecioParteSeleccionada(String precio){
	    	labelPrecio.setText(precio);	    	  
       }
       
       
       
       private void crearPanelAuto() {
           JTabbedPane tabbedPane = new JTabbedPane();
           tabbedPane.setPreferredSize(new Dimension(300,350));
           
           JPanel panelAuto = new JPanel();
           panelAuto.setLayout(new GridBagLayout());
        
           Color nc = new Color(176,196,222);
           panelAuto.setBackground(nc);
          
           this.panelAutoActual = new JPanel();
           panelAutoActual.setLayout(new BoxLayout(panelAutoActual,BoxLayout.Y_AXIS));
           panelAutoActual.setOpaque(false);
           panelAuto.add(panelAutoActual);
         
           tabbedPane.addTab("Mi Auto",panelAuto);
       
           GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridy =1;
           c.weighty = 1.0;
           c.anchor = GridBagConstraints.CENTER;
           this.add(tabbedPane,c);        
       }
       
       private void crearPanelUsuario() { 	   
    	   this.panelUsuario = new JPanel();
    	   Color nc = new Color(176,196,222);
    	   panelUsuario.setPreferredSize(new Dimension(300,130));
    	   panelUsuario.setBackground(nc);
    	          
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =1;
           c.gridy =1;
           c.gridheight =1;
           Insets in=new Insets(100,30,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTH;
        
    	   this.add(panelUsuario,c);         
       }
       
       private JPanel crearPanelReserva() {
    	   Color nc = new Color(176,196,222);
    	   JPanel panelBodega = new JPanel();
    	   panelBodega.setLayout(new GridBagLayout());
    	   panelBodega.setBackground(nc);
    	 
    	   
    	   GridBagConstraints ubicacion = new GridBagConstraints();
	       ubicacion.gridx=0;
	       ubicacion.gridy=0;
	       ubicacion.anchor= GridBagConstraints.NORTH;
	   	   
	       comboPartesReserva = new JComboBox();

	       
    	   comboPartesReserva.addItem("- Lista  Vac�a -");
    	   panelBodega.add(comboPartesReserva,ubicacion);
    	  
    	   comboParteARemover=new JComboBox();
	       comboParteARemover.addItem("-Parte a Cambiar-");
	       ubicacion.anchor= GridBagConstraints.SOUTH;
    	   panelBodega.add(comboParteARemover,ubicacion);
 
		   ActionListener listener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						String nombreParte=obtenerReservaSeleccionada();
						InformacionDelModelo informacion=RegistroDeModelos.getInstance().getInformacion(nombreParte);
						if(informacion!=null){
							textoInformacionParte.setText(informacion.toString());
						}
			}
		   };
    	   
    	   Boton botonCambiar = new Boton("Cambiar");
    	   botonCambiar.addActionListener(controladorTaller);
           botonCambiar.setActionCommand("cambiar");
    	   panelBodega.add(botonCambiar,posicionBoton());
    	   
    	   Boton botonInformacion = new Boton("Ver Info.");
    	   botonInformacion.addActionListener(listener);
    	   panelBodega.add(botonInformacion,posicionBotonInformacion());
               
    	   return panelBodega;
       }
       
       private JPanel crearPanelDinero(){             
    	   Color nc = new Color(176,196,222);
           JPanel panelDinero = new JPanel();
           panelDinero.setLayout(new GridBagLayout());
           panelDinero.setBackground(nc);
       
           labelDinero = new JLabel();
           panelDinero.add(labelDinero);
               
           return panelDinero;
       	}
       
       private JPanel crearPanelCargarNafta(){ 	   
    	   this.panelNafta = new JPanel();
    	   Color nc = new Color(176,196,222);
    	   panelNafta.setPreferredSize(new Dimension(100,150)); 
   	   
    	   panelNafta.setLayout (new GridBagLayout());
           panelNafta.setBackground(nc); 
           
           return panelNafta;
   	   }
       
       private JPanel crearPanelPista(){            
           Color nc = new Color(176,196,222);
           this.panelPista = new JPanel();
           panelPista.setLayout(new GridBagLayout());
           panelPista.setBackground(nc);
                         
           return panelPista;
       }
       
       private void crearPanelPistaDineroBodega(){             
           JTabbedPane tabbedPane = new JTabbedPane();
           tabbedPane.setPreferredSize(new Dimension(300,200));

		   tabbedPane.addTab("Dinero",crearPanelDinero());
		   tabbedPane.addTab("Bodega",crearPanelReserva());  
		   tabbedPane.addTab("Cargar Nafta",crearPanelCargarNafta());
		   tabbedPane.addTab("Proxima Pista",crearPanelPista());
	           
	       GridBagConstraints c = new GridBagConstraints();
	       c.gridx =1;
	       c.gridy =1;
	       c.gridheight =1;
	       Insets in=new Insets(200,50,0,0);
	       c.insets=in;
	       c.anchor = GridBagConstraints.CENTER;
           
	       this.add(tabbedPane,c);
       }
       
       private void crearPanelMensajes() {       
           JPanel panelMensaje = new JPanel();
           panelMensaje.setPreferredSize(new Dimension(300,100));
        
           Color nc = new Color(176,196,222);
           panelMensaje.setBackground(nc);
           panelMensaje.setOpaque(true);
           Color nc2 = new Color(224,224,255);
           Border border2 = BorderFactory.createMatteBorder(2,2,2,2,nc2);
           Border border= BorderFactory.createTitledBorder(border2,"Informacion");
        
           labelMensaje = new JLabel();
           panelMensaje.add(labelMensaje);
           
           panelMensaje.setBorder(border);
           GridBagConstraints c = new GridBagConstraints();
           c.gridx =2;
           c.gridy =2;
           Insets in=new Insets(30,0,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTHWEST;
     
           this.add(panelMensaje,c);         
       }
       
       private void crearPanelInformacion() {       
           JPanel panelMensaje = new JPanel();
           panelMensaje.setPreferredSize(new Dimension(300,100));
        
           Color nc = new Color(176,196,222);
           panelMensaje.setBackground(nc);
           panelMensaje.setOpaque(true);
           Color nc2 = new Color(224,224,255);
           Border border2 = BorderFactory.createMatteBorder(2,2,2,2,nc2);
           Border border= BorderFactory.createTitledBorder(border2,"Informacion de Parte");
        
           textoInformacionParte = new JTextArea(5,15);
           textoInformacionParte.setEditable(false);
           panelMensaje.add(textoInformacionParte);
           
           panelMensaje.setBorder(border);
           GridBagConstraints c = new GridBagConstraints();
           c.gridx =2;
           c.gridy =4;
           Insets in=new Insets(30,0,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTHWEST;
     
           this.add(panelMensaje,c);         
       }
       
       
     
       private void crearPanelCatalogo(){            
           Color nc = new Color(176,196,222);
 
		   JTabbedPane tabbedPane = new JTabbedPane();
		   tabbedPane.setPreferredSize(new Dimension(400,200));
		 
		   panelCatalogo = new JPanel();
		   panelCatalogo.setBackground(nc);
		   panelCatalogo.setPreferredSize(new Dimension(100,150));
		   panelCatalogo.setLayout(new GridBagLayout());
		       
		   GridBagConstraints c4 = new GridBagConstraints();
		   Insets in=new Insets(0,30,0,0);
		   c4.gridx=0;
		   c4.gridy=0;
		   c4.insets=in;
		   c4.anchor = GridBagConstraints.SOUTH;
		   panelCatalogo.add(crearFabricas(),c4);
		   c4.gridx=1;
		   c4.gridy=0;
		   c4.anchor = GridBagConstraints.CENTER;
		   panelCatalogo.add(crearPanelPrecio(),c4);
		     
		   Boton botonComprar = new Boton("Comprar");
           botonComprar.addActionListener(controladorTaller);
           botonComprar.setActionCommand("comprar");
		   c4 = posicionBoton();
		   
		   c4.gridx=1;
		   c4.gridy=0;
		   c4.anchor = GridBagConstraints.SOUTHEAST;

		   panelCatalogo.add(botonComprar,c4);
		   tabbedPane.addTab("Catalogo",panelCatalogo);
		     
		   GridBagConstraints c = new GridBagConstraints();
		   c.gridx=0;
		   c.gridy=2;
		   c.weighty = 1.0;
		   c.gridwidth =2;
		   c.anchor = GridBagConstraints.NORTH;
		   
		   ActionListener listener=new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
							String nombreParte=(String)catalogo.getSelectedItem();
							InformacionDelModelo informacion=RegistroDeModelos.getInstance().getInformacion(nombreParte);
							if(informacion!=null){
								textoInformacionParte.setText(informacion.toString());
							}
				}
			   };
			   Boton botonInfo = new Boton("Ver Info.");
			   botonInfo.addActionListener(listener);
			   panelCatalogo.add(botonInfo,posicionBotonInformacion());
			   
			   
		       
		   this.add(tabbedPane,c);
       }     
    
       private JPanel crearFabricas() {
	       
		   JPanel panelCombo = new JPanel();
		   GridLayout g=new GridLayout (3,3);
		   g.setVgap(5);
		   g.setHgap(10);
		   panelCombo.setLayout (g); 
		   panelCombo.setOpaque(false);
		       
		   catalogo = new JComboBox();
		   catalogo.addItem("-Catalogo-");

		   panelCombo.add(catalogo);
		     
		   return panelCombo;
       }

       private JPanel crearPanelPrecio() {       
           JPanel panelPrecio = new JPanel();
           panelPrecio.setPreferredSize(new Dimension(150,50));
           labelPrecio = new JLabel();
           labelPrecio.setMinimumSize(new Dimension(150, 30));
           Color nc = new Color(176,196,222);
           panelPrecio.setBackground(nc);
           panelPrecio.setOpaque(true);
           
           Color nc2 = new Color(224,224,255);
           Border border2 = BorderFactory.createMatteBorder(2,2,2,2,nc2);
           Border border= BorderFactory.createTitledBorder(border2,"Precio");
           cambiarPrecioParteSeleccionada("precio");
    
           panelPrecio.add(labelPrecio);
           panelPrecio.setBorder(border);
           return panelPrecio;      
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
       
       private GridBagConstraints posicionBotonInformacion(){
    	   GridBagConstraints ubicacion = new GridBagConstraints();
	       Insets in=new Insets(0,0,5,0);
	       ubicacion.insets=in;
	       ubicacion.weightx = 1.0;
	       ubicacion.weighty = 1.0;
	       ubicacion.anchor = GridBagConstraints.NORTHWEST;
               
           return ubicacion;
        }

       
}