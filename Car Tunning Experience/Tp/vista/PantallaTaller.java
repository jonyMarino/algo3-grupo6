package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

import auto.Auto;
import controlador.ControladorJuego;
import excepciones.BoundsException;
import pista.Pista;
import programaAuto.Usuario;
import proveedorDePartes.ProveedorDePartes;
import proveedorDePartes.fabricas.*;
import taller.Taller;

public class PantallaTaller extends JPanelConImagen {

	   private JPanel panelAutoActual;
       private JPanel panelPista;
       private JPanel panelUsuario;
       private JPanel panelNafta;
       private JLabel labelDinero;
       private JComboBox comboPartesReserva;
       private static final long serialVersionUID = 1L;
     
       public PantallaTaller(ControladorJuego controladorJuego) {
               
               super();
               
               this.setImage("FondoTransparente");
               
               this.setLayout(new GridBagLayout());
               
               crearPanelCatalogo();
               crearPanelAuto();
               crearPanelPistaDineroBodega();
               crearPanelUsuario();
               
               GridBagConstraints c = new GridBagConstraints();
               c.gridx=2;
               c.gridy=2;
               Insets in=new Insets(170,20,0,0);
               c.insets=in;
               c.anchor = GridBagConstraints.NORTH;
               
               Boton botonComenzar = new Boton("Comenzar Carrera");
               botonComenzar.addActionListener(controladorJuego);
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
            
       //Esto actualiza la PANEL INFORMACION AUTO
       public void actualizarInformacionAuto(Auto auto) {
    	   
       	   panelAutoActual.removeAll();
    	   
    	   ParteAuto parte;
           
           Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
           tabla = auto.getHashDePartes();
           Enumeration<ParteAuto> enumeracion = tabla.elements();
           Double vidaUtil;
           
           while(enumeracion.hasMoreElements()){   
        	        JLabel jParteAuto = new JLabel();
        	   		parte=enumeracion.nextElement();
             try{
                     String nombreParte =parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                     vidaUtil = parte.getVidaUtil();
                     jParteAuto.setText(nombreParte+" "+vidaUtil);  
                     panelAutoActual.add(jParteAuto);                
             }catch (BoundsException e){}
           
           }
      	   
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
           Insets in=new Insets(150,30,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTH;
        
    	   this.add(panelUsuario,c);         
       }
       
       //Esto actualiza la PANEL INFORMACION USUARIO
       public void actualizarInformacionUsuario(Usuario usuario) {

    	   panelUsuario.removeAll();  	   
    	   
    	   JLabel jUsuario = new JLabel("JUGADOR ACTUAL: "+usuario.getNombre());
    	   jUsuario.setIcon(usuario.getAvatar());
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
    	   panelBodega.add(comboPartesReserva,ubicacion);
    	   Boton botonCambiar = new Boton("Cambiar");
    	   panelBodega.add(botonCambiar,posicionBoton());
               
    	   return panelBodega;
       }

       //Esto actualiza el PANEL LISTA RESERVA
       public void actualizarInformacionReserva(Taller taller) {

    	    comboPartesReserva.removeAllItems();
    	   	
    	    Double vidaUtil;
		    String descripcion;
		    boolean entre = false;
		    				
		    while (taller.getPartesDeReserva().hasNext()){
		    	entre = true;
		    	try {
					descripcion = taller.getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
			    	vidaUtil = taller.getPartesDeReserva().next().getVidaUtil();
					
			    	comboPartesReserva.addItem( descripcion + " " + vidaUtil );

		    	} catch (BoundsException e) { }
    			    
		    }
			
		    if (!entre) {
		    	String mensajeVacio = "- Lista Vacía -";
		    	comboPartesReserva.addItem(mensajeVacio);
			}           
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
       
       //Esto actualiza la PANEL DINERO
       public void actualizarInformacionDinero(double dinero){
    	   
    	   this.labelDinero.setText(Double.toString(dinero) + " Algo$");
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
       
     //Esto actualiza el PANEL NAFTA
       public void actualizarInformacionNafta(Auto auto){
    	   
    	   panelNafta.removeAll();
    	    	   
    	   JProgressBar barraTanque = new JProgressBar(0, (int)auto.getTanqueCombustible().getCapacidad());
    	   barraTanque.setPreferredSize(new Dimension(227,20));
    	   barraTanque.setValue((int)auto.getTanqueCombustible().getCantidadCombustible());
    	   barraTanque.setStringPainted(true);
    	   barraTanque.setString(Double.toString(auto.getTanqueCombustible().getCantidadCombustible())+ 
    			     " / " + Double.toString(auto.getTanqueCombustible().getCapacidad()) + " Litros");
    	   
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridwidth =3; 
           c.gridy =0;
           c.anchor = GridBagConstraints.CENTER;
           panelNafta.add(barraTanque,c);   
           
           //TODO: boton informativo, por si algo ocurre [falta de dinero].
           JLabel mensaje = new JLabel("");
           c.gridx =0;
           c.gridwidth =0; 
           c.gridy =1;
           c.anchor = GridBagConstraints.CENTER;
           panelNafta.add(mensaje,c);
                      
           Boton botonCargar = new Boton("Cargar Nafta");
           botonCargar.setOpaque(false);
           //TODO: 0,5 es cada cuanto aumenta el boton.
           SpinnerModel model = new SpinnerNumberModel(0,0,auto.getTanqueCombustible().getCantidadCombustible() - auto.getTanqueCombustible().getCantidadCombustible(), 0.5);                
           JSpinner seleccionCargar = new JSpinner(model);
           JLabel cantidadLitros = new JLabel(" Litros");
           
           c.gridx =0;
           c.gridy =2;
           c.anchor = GridBagConstraints.LAST_LINE_END;
           panelNafta.add(botonCargar,c);
           c.gridx =0;
           c.gridy =1;
           panelNafta.add(seleccionCargar,c);   
           c.gridx =0;
           c.gridy =0;
           panelNafta.add(cantidadLitros,c);    
          
       }
       
     
       /* PANEL PISTA */
       private JPanel crearPanelPista(){
             
               Color nc = new Color(176,196,222);
               this.panelPista = new JPanel();
               panelPista.setLayout(new GridBagLayout());
               panelPista.setBackground(nc);
              
            
               return panelPista;
       }
       
       //Esto actualiza la PANELPISTA
       public void actualizarInformacionPista(Pista proximaPista){

   	   
    	   panelPista.removeAll();
    	   
    	   JLabel jLongitud = new JLabel("Longitud: " + Double.toString( proximaPista.getLongitud() ) + " metros");
    	   JLabel jVelocidadAire = new JLabel("Velocidad del aire: " + Integer.toString( proximaPista.getVelocidadAire() ) + " Km/h");
    	   String superficie = "Asfalto"; //Supongo que es ASFALTO
    	   JLabel jSuperficie = new JLabel("Superficie: " + superficie);  	   
    	   //TODO: siempre es cero la velocidad del aire??
    	   //TODO: Se tendria que decir de que tipo es la pista. 
    	  
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
       

       /* PANEL CATALOGO */
       private void crearPanelCatalogo(){
               
               Color nc = new Color(176,196,222);
 
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
		       c.anchor = GridBagConstraints.NORTH;
		       
		       this.add(tabbedPane,c);
       }
       
       //Esto actualiza la PANELCATALOGO
       public void actualizarPanelCatalogo(String catalogo){
    	  //lo que haga actualizarla
       }
     
       /*ACA TENGO QUE RECIBIR UN PARAMETRO QUE SEA LA LISTA DE TODAS LAS LISTAS DE LAS FABRICAS*/
       private JPanel contenedorPartes(){
       
		       ArrayList<FabricaDePartes> temporal = new   ArrayList<FabricaDePartes>();
	           
		       ProveedorDePartes nuevoPorveedor = new ProveedorDePartes();  //dato temporal
		       temporal=nuevoPorveedor.getMiCadenaDeFabricas().getMiCadenaDeFabricas();
	           JPanel panel = new JPanel();
	           panel.setOpaque(false);
	           panel.add(actualizarInformacionCatalogo(temporal));
	           
	           return panel;
       }
 
       public JPanel actualizarInformacionCatalogo(ArrayList<FabricaDePartes> contenedorDeListas) {
	       
		       JPanel panelCombo = new JPanel();
		       panelCombo.setLayout (new GridLayout (5,2)); 
		       JComboBox combo;
		
		       Iterator <FabricaDePartes> it;
		       Iterator <InformacionDelModelo> itInformacion;
		       
		       FabricaDePartes informacionDeFabrica;
		       InformacionDelModelo informacionDelModelo;
		      
		       it = contenedorDeListas.iterator();
		               
		            while (it.hasNext()){   //leo cada fabrica
		            combo = new JComboBox();
		            informacionDeFabrica = (FabricaDePartes)it.next();
		
		            itInformacion = informacionDeFabrica.getModelos().iterator();
		            combo = new JComboBox();
		                       
			            while (itInformacion.hasNext()) {  //leo el catalogo de cada fabrica
			            		informacionDelModelo = (InformacionDelModelo)itInformacion.next();
			                    try{
			                    	combo.addItem(informacionDelModelo.getCaracteristica("DESCRIPCION"));
			                    }catch(BoundsException e){}
			            } 
		            
			            panelCombo.add(combo);
		            }
		       
		       return panelCombo;
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

       
       public void actionPerformed(ActionEvent e) {
               
               
       }

}