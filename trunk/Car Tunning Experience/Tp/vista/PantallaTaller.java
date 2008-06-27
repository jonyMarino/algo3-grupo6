package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import proveedorDePartes.fabricas.InformacionDelModelo;

import controlador.ControladorJuego;
import controlador.ControladorTaller;
import excepciones.BoundsException;


public class PantallaTaller extends JPanelConImagen {

	   private JPanel panelAutoActual;
       private JPanel panelPista;
       private JPanel panelUsuario;
       private JPanel panelCatalogo;
       private JPanel panelNafta;
       private JLabel labelDinero;
       private JLabel labelMensaje;
       private JLabel labelPrecio;
       private JSpinner cantidadSeleccionada = new JSpinner();
       private JComboBox comboPartesReserva;     
       private JComboBox elCatalogo;
       private ControladorTaller controladorTaller;;
       private static final long serialVersionUID = 1L;
     
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
       }
       
       
       /* PANEL INFORMACION AUTO USUARIO */
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
            
       public void limpiarInformacionAuto() {
    	   panelAutoActual.removeAll();
       }
       
       public void agregarInformacionAuto(String nombreParte, String vidaUtil) { 
    	   JLabel jParteAuto = new JLabel();
    	   jParteAuto.setText(nombreParte+" "+vidaUtil);
    	   panelAutoActual.add(jParteAuto);
       }
       
      
       /*PANEL USUARIO*/
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
       
       public void actualizarInformacionUsuario(String nombreUsuario, ImageIcon avatarUsuario) {
    	   panelUsuario.removeAll();  	   
    	   
    	   JLabel jUsuario = new JLabel("JUGADOR ACTUAL:  " + nombreUsuario);
    	   jUsuario.setIcon( avatarUsuario );
    	   jUsuario.setHorizontalTextPosition(JLabel.CENTER);
    	   jUsuario.setVerticalTextPosition(JLabel.NORTH);
    	   
           panelUsuario.add(jUsuario);         
       }

       
       /* PANEL LISTA DE RESERVAS DEL TALLER*/
       private JPanel crearPanelReserva() {
    	   Color nc = new Color(176,196,222);
    	   JPanel panelBodega = new JPanel();
    	   panelBodega.setLayout(new GridBagLayout());
    	   panelBodega.setBackground(nc);
    	 
    	   
    	   GridBagConstraints ubicacion = new GridBagConstraints();
	       Insets in=new Insets(30,100,0,0);
	       ubicacion.insets=in;
	       ubicacion.gridx=1;
	       ubicacion.gridy=0;
	       ubicacion.anchor= GridBagConstraints.NORTH;
	   	   
    	   this.comboPartesReserva = new JComboBox();
    	   comboPartesReserva.addItem("-Lista Vacía-");
    	   panelBodega.add(comboPartesReserva,ubicacion);
    	   Boton botonCambiar = new Boton("Cambiar");
    	   panelBodega.add(botonCambiar,posicionBoton());
               
    	   return panelBodega;
       }

       public void limpiarInformacionReserva() {
    	   comboPartesReserva.removeAllItems();
       }
   
       public void agregarAReserva(String descripcion,String vidaUtil){ 
    	   comboPartesReserva.addItem( descripcion + " " + vidaUtil );
       }
     
       
       /* PANEL DINERO */
       private JPanel crearPanelDinero(){             
    	   Color nc = new Color(176,196,222);
           JPanel panelDinero = new JPanel();
           panelDinero.setLayout(new GridBagLayout());
           panelDinero.setBackground(nc);
       
           labelDinero = new JLabel();
           panelDinero.add(labelDinero);
               
           return panelDinero;
       	}
       
       public void actualizarInformacionDinero(String dinero){ 	   
    	   this.labelDinero.setText(dinero + " Algo$");
       }
       
       
       /* PANEL CARGAR NAFTA */
       private JPanel crearPanelCargarNafta(){ 	   
    	   this.panelNafta = new JPanel();
    	   Color nc = new Color(176,196,222);
    	   panelNafta.setPreferredSize(new Dimension(100,150)); 
   	   
    	   panelNafta.setLayout (new GridBagLayout());
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridy =0;
           c.anchor = GridBagConstraints.NORTH;

           panelNafta.setBackground(nc); 

           return panelNafta;
   	   }
       
       public void actualizarInformacionNafta(Double cantidad, Double capacidad){  	   
    	   panelNafta.removeAll();
    	    	   
    	   JProgressBar barraTanque = new JProgressBar(0, (int)Math.floor(capacidad));
    	   barraTanque.setPreferredSize(new Dimension(227,20));
    	   barraTanque.setValue((int)Math.floor(cantidad));
    	   barraTanque.setStringPainted(true);
    	   barraTanque.setString(Double.toString(cantidad)+ " / " + Double.toString(capacidad) + " Litros");
    	   
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridwidth =3; 
           c.gridy =0;
           c.anchor = GridBagConstraints.CENTER;
           panelNafta.add(barraTanque,c);   
                              
           Boton botonCargar = new Boton("Cargar Nafta");
           botonCargar.addActionListener(controladorTaller);
           botonCargar.setActionCommand("cargar");
           botonCargar.setOpaque(false);
           SpinnerModel model = new SpinnerNumberModel(0,0,capacidad-0, 0.5);         
           cantidadSeleccionada.setModel(model);
           JLabel cantidadLitros = new JLabel(" Litros");
           
           c.gridx =0;
           c.gridy =2;
           c.anchor = GridBagConstraints.LAST_LINE_END;
           panelNafta.add(botonCargar,c);
           c.gridx =0;
           c.gridy =1;
           panelNafta.add(cantidadSeleccionada,c);   
           c.gridx =0;
           c.gridy =0;
           panelNafta.add(cantidadLitros,c);             
       }
       
       public String obtenerCantidadPanelNafta() {
    	   return(cantidadSeleccionada.getValue().toString());
       }
       
     
       /* PANEL PISTA */
       private JPanel crearPanelPista(){            
           Color nc = new Color(176,196,222);
           this.panelPista = new JPanel();
           panelPista.setLayout(new GridBagLayout());
           panelPista.setBackground(nc);
                         
           return panelPista;
       }
       
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

             
       /* PANEL PISTA DINERO BODEGA */
       private void crearPanelPistaDineroBodega(){             
           JTabbedPane tabbedPane = new JTabbedPane();
           tabbedPane.setPreferredSize(new Dimension(300,150));

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
       
       
       /*PANEL MENSAJES*/ 
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
           c.gridheight =1;
           Insets in=new Insets(30,30,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTH;
     
           this.add(panelMensaje,c);         
       }
       
       public void generarMensaje(String mensaje){
           labelMensaje.setText(mensaje);
           labelMensaje.setForeground(Color.GREEN);
       }
       
       public void generarMensajeError(String mensaje){
           labelMensaje.setText(mensaje);
           labelMensaje.setForeground(Color.RED);
       }

       /* PANEL CATALOGO */
       private void crearPanelCatalogo(){            
           Color nc = new Color(176,196,222);
 
		   JTabbedPane tabbedPane = new JTabbedPane();
		   tabbedPane.setPreferredSize(new Dimension(500,200));
		 
		   panelCatalogo = new JPanel();
		   panelCatalogo.setBackground(nc);
		   panelCatalogo.setPreferredSize(new Dimension(400,150));
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
		   c4 = posicionBoton();
		   panelCatalogo.add(botonComprar,c4);
		   tabbedPane.addTab("Catalogo",panelCatalogo);
		     
		   GridBagConstraints c = new GridBagConstraints();
		   c.gridx=0;
		   c.gridy=2;
		   c.weighty = 1.0;
		   c.gridwidth =2;
		   c.anchor = GridBagConstraints.NORTH;
		       
		   this.add(tabbedPane,c);
       }
            
    
       private JPanel crearFabricas() {
	       
		   JPanel panelCombo = new JPanel();
		   GridLayout g=new GridLayout (3,3);
		   g.setVgap(5);
		   g.setHgap(10);
		   panelCombo.setLayout (g); 
		   panelCombo.setOpaque(false);
		       
		   elCatalogo = new JComboBox();
		   elCatalogo.addItem("-Catalogo-");

		   panelCombo.add(elCatalogo);
		   Action mostrarPrecio = new AccionActualizaarPrecio();
	 		elCatalogo.setAction(mostrarPrecio);
		     
		   return panelCombo;
       }
       
       private class AccionActualizaarPrecio extends AbstractAction {

           public void actionPerformed(ActionEvent evento) {
               String precio = "";
               try{
            	   InformacionDelModelo info = (InformacionDelModelo) elCatalogo.getSelectedItem();
            	   try {
    				precio = info.getCaracteristica("COSTO");
    			} catch (BoundsException e) {
    				e.printStackTrace();
    			}
               }catch(ClassCastException e){
            	   precio = "Seleccione Una parte";
               }

               labelPrecio.setText(precio);
           }
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

       public JComboBox getElCatalogo() {
    	   return elCatalogo;
       }
	
       public void agregarACatalogo(InformacionDelModelo parte) {
    	   elCatalogo.addItem(parte);
       }
       
       /*RETORNA EL STRING QUE SELECCIONAS EN EL CATALOGO DE LA PARTE QUE SELECCIONAS*/
       public Object parteAComprar(){ 	
    	   return elCatalogo.getSelectedItem();
       }
       
       public void precioParteSeleccionada(String precio){
	    	labelPrecio.setText(precio);
	    	  
       }
       
       private JPanel crearPanelPrecio() {       
           JPanel panelPrecio = new JPanel();
           panelPrecio.setPreferredSize(new Dimension(150,50));
           labelPrecio = new JLabel();
           Color nc = new Color(176,196,222);
           panelPrecio.setBackground(nc);
           panelPrecio.setOpaque(true);
           
           Color nc2 = new Color(224,224,255);
           Border border2 = BorderFactory.createMatteBorder(2,2,2,2,nc2);
           Border border= BorderFactory.createTitledBorder(border2,"Precio");
           precioParteSeleccionada("precio");
           String precio = "";
           try{
        	   InformacionDelModelo info = (InformacionDelModelo) elCatalogo.getSelectedItem();
        	   try {
				precio = info.getCaracteristica("COSTO");
			} catch (BoundsException e) {
				e.printStackTrace();
			}
           }catch(ClassCastException e){
        	   precio = "Seleccione Una parte";
           }

           labelPrecio.setText(precio);

           panelPrecio.add(labelPrecio);
           panelPrecio.setBorder(border);
           return panelPrecio;      
       }

}