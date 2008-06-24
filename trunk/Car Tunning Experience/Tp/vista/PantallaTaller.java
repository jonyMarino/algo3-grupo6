package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import auto.Auto;
import auto.AutoManual;
import combustible.Nafta;
import controlador.ControladorJuego;
import excepciones.BoundsException;
import excepciones.WrongPartClassException;
import pista.Pista;
import programaAuto.Usuario;
import proveedorDePartes.fabricas.*;
import taller.Taller;

public class PantallaTaller extends JPanelConImagen {

	   private JPanel panelAutoActual;
       private JPanel panelPista;
       private JPanel panelUsuario;
       private JLabel boxDinero;
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
           tabbedPane.setPreferredSize(new Dimension(300,300));
           
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
    	   panelUsuario.setPreferredSize(new Dimension(250,200));
    	   panelUsuario.setBackground(nc);
    	          
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =1;
           c.gridy =1;
           c.gridheight =1;
           Insets in=new Insets(100,0,0,0);
           c.insets=in;
           c.anchor = GridBagConstraints.NORTH;
        
    	   this.add(panelUsuario,c);         
       }
       
       //Esto actualiza la PANEL INFORMACION USUARIO
       public void actualizarInformacionUsuario(Usuario usuario) {
    	  
    	   panelUsuario.removeAll();  	   
    	   
    	   JLabel jUsuario = new JLabel(usuario.getNombre());
    	   jUsuario.setIcon(usuario.getAvatar());
           panelUsuario.add(jUsuario);           
       }

       
       /* PANEL LISTA DE RESERVAS DEL TALLER*/
       private JPanel crearPanelReserva() {

    	   Color nc = new Color(176,196,222);
    	   JPanel panelBodega = new JPanel();
    	   panelBodega.setLayout(new GridBagLayout());
    	   panelBodega.setBackground(nc);
        
    	   this.comboPartesReserva = new JComboBox();
    	   panelBodega.add(comboPartesReserva);
    	   Boton botonCambiar = new Boton("Cambiar");
    	   panelBodega.add(botonCambiar);
               
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
       
               boxDinero = new JLabel();
               panelDinero.add(boxDinero);
               
               return panelDinero;
       	}
       
       //Esto actualiza la PANEL DINERO
       public void actualizarInformacionDinero(double dinero){
    	   
    	   this.boxDinero.setText(Double.toString(dinero) + " Algo$");
       }
       
       
       /* PANEL CARGAR NAFTA */
       private JPanel crearPanelCargarNafta(){
    	   
    	   JPanel panelNafta = new JPanel();
    	   Color nc = new Color(176,196,222);
    	   panelNafta.setPreferredSize(new Dimension(100,150)); 
   	   
    	   panelNafta.setLayout (new GridBagLayout());
    	   GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridy =0;
           c.anchor = GridBagConstraints.NORTH;

           panelNafta.setBackground(nc); 
           JLabel boxNafta = new JLabel("1");
           Scrollbar barraTemp = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, -50, 160); 
           barraTemp.setPreferredSize(new Dimension(100,20)); 
           
           panelNafta.add(boxNafta,c);
           c.gridx =1;
           c.gridy =0;
           panelNafta.add(barraTemp,c);
           
           c.gridx =0;
           c.gridy =2;
           c.anchor = GridBagConstraints.SOUTH;
           Boton botonCargar = new Boton("Cargar Nafta");
           botonCargar.setOpaque(false);
          
           panelNafta.add(botonCargar,c);
    
           return panelNafta;
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
       
           /*ESTAS FABRICAS SON DE PRUEBA NOMAS*/
                FabricaDeEscapes  fabricaEscapes = new FabricaDeEscapes();
                FabricaDeCarrocerias  fabricaCarrocerias = new FabricaDeCarrocerias();
                FabricaDeEjes  fabricaEjes = new FabricaDeEjes();
                               
                ArrayList <ArrayList<InformacionDelModelo>> temporal = new ArrayList<ArrayList<InformacionDelModelo>>();
                temporal.add(fabricaEscapes.getModelos());
                temporal.add(fabricaEscapes.getModelos());
                temporal.add(fabricaCarrocerias.getModelos());
	            temporal.add(fabricaCarrocerias.getModelos());
	            temporal.add(fabricaEjes.getModelos());
	            temporal.add(fabricaEscapes.getModelos());
	            temporal.add(fabricaEscapes.getModelos());
	            temporal.add(fabricaCarrocerias.getModelos());
	            temporal.add(fabricaCarrocerias.getModelos());
	            temporal.add(fabricaEjes.getModelos());
           //
           
           JPanel panel = new JPanel();
           panel.setOpaque(false);
           panel.add(actualizarInformacionCatalogo(temporal));
           return panel;
       }

       /*ACA TENGO QUE RECIBIR UN PARAMETRO QUE SEA LA LISTA DE TODAS LAS LISTAS DE LAS FABRICAS*/
       public JPanel actualizarInformacionCatalogo( ArrayList <ArrayList<InformacionDelModelo>> contenedorDeListas) {
       /* se crean todas las fabricas y se las pasa una vez creadas todas juntas para armar el catalgo.*/
               
               JPanel panelCombo = new JPanel();
               panelCombo.setLayout (new GridLayout (5,2)); //TOTAL 10 FABRICAS
       JComboBox combo;

       Iterator <ArrayList<InformacionDelModelo>> it;
       Iterator <InformacionDelModelo> itInformacion;
       InformacionDelModelo informacionDelModelo;
       ArrayList<InformacionDelModelo> contenedorInformacion;
               it = contenedorDeListas.iterator();
               
               while (it.hasNext()){
                       contenedorInformacion = (ArrayList<InformacionDelModelo>)it.next();
                       itInformacion = contenedorInformacion.iterator();
                       combo = new JComboBox();
                       
                                       while (itInformacion.hasNext()) {
                                               informacionDelModelo = (InformacionDelModelo)itInformacion.next();
                                     
                                               try{
                                       combo.addItem(informacionDelModelo.getCaracteristica("DESCRIPCION"));
                                               }catch(BoundsException e){}
                                       }
                       panelCombo.add(combo);
               }
       
       return panelCombo;
   }
       
       public void actionPerformed(ActionEvent e) {
               
               
       }
       
       /*AUTO DE PRUEBA NOMAS*/
       public Auto autoInicial(){
               Pista pista = new Pista(5);
               pista=null;
               FabricaDeTanquesDeCombustible fabricaTanques = new FabricaDeTanquesDeCombustible();
               FabricaDeMezcladores fabricaMezcladores = new FabricaDeMezcladores();
               FabricaDeEscapes fabricaEscapes = new FabricaDeEscapes();
                FabricaDeMotores fabricaMotores = new FabricaDeMotores();
                FabricaDeRuedas fabricaRuedas = new FabricaDeRuedas();
                FabricaDeCarrocerias fabricaCarrocerias = new FabricaDeCarrocerias();
                FabricaDeCajas fabricaCajas = new FabricaDeCajas();
                FabricaDeEjes fabricaEjes = new FabricaDeEjes();
                FabricaDePedales fabricaPedales = new FabricaDePedales();
               Auto auto=null;
               Nafta nafta = new Nafta(85,15);
               
               TanqueNafta tanque = null;
			try {
				tanque = fabricaTanques.fabricar(fabricaTanques.getModelos().get(0));
			} catch (BoundsException e2) {
				e2.printStackTrace();
			}
               tanque.setCombustible(nafta);
       
               try {
                       tanque.llenarTanque(70);
               } catch (BoundsException e1) {
                       e1.printStackTrace();
               }
               
               MezcladorNafta mezclador = null;
               Escape escape = null;
               Carroceria carroceria = null;
               Motor motor = null;
               Caja caja = null;
               Eje eje = null;
               Freno freno = null;
               ArrayList<Rueda> ruedas = new ArrayList<Rueda>();
               
               try{
               
            	   		mezclador = (MezcladorNafta) fabricaMezcladores.fabricar(fabricaMezcladores.getModelos().get(0));
                       
                      escape = fabricaEscapes.fabricar(fabricaEscapes.getModelos().get(0));
                       
                      carroceria = fabricaCarrocerias.fabricar(fabricaCarrocerias.getModelos().get(0));

                       
                       for(int i=0;i<4;i++){
                               Rueda rueda = fabricaRuedas.fabricar(fabricaRuedas.getModelos().get(0));
                               ruedas.add(rueda);                              
                       }
                       
                       eje = fabricaEjes.fabricar(fabricaEjes.getModelos().get(0));
                       

                       try {
						caja = (CajaManual) fabricaCajas.fabricar(fabricaCajas.getModelos().get(0));
					} catch (BoundsException e1) {
						e1.printStackTrace();
					}
                       
                       motor=fabricaMotores.fabricar(fabricaMotores.getModelos().get(0));
                       
                       freno =  (Freno) fabricaPedales.fabricar(fabricaPedales.getModelos().get(1));
               }catch(BoundsException e){
            	   e.printStackTrace();
               }
                       try {
                               auto = new AutoManual(escape, carroceria, motor, (CajaManual) caja, (MezcladorNafta) mezclador, tanque, ruedas.get(0), ruedas.get(1),ruedas.get(2),ruedas.get(3), eje, freno);
                       } catch (WrongPartClassException e) {
                               e.printStackTrace();
                       }
                       auto.setPista(pista);

               return auto;
       }
       

}