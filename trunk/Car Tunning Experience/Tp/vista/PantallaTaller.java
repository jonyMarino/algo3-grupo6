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
import excepciones.WrongUsername;
import pista.Pista;
import programaAuto.Usuario;
import proveedorDePartes.fabricas.*;
import taller.Taller;

public class PantallaTaller extends JPanelConImagen{

       private DefaultComboBoxModel listaPartesEnTaller;
       private JLabel boxPistaLongitud;
       private JLabel boxPistaVelodidadAire;
       private JLabel boxPistaSuperficie;
       private JLabel boxDinero;
       private JPanel panelLabel;
       private static final long serialVersionUID = 1L;
   
       public PantallaTaller(ControladorJuego controladorJuego) {
               
               super();
               
               this.setImage("FondoTransparente");
               
               this.setLayout(new GridBagLayout());
               
               crearPanelCatalogo();
               crearPanelAuto();
               crearPanelPistaDineroBodega();
               
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
       private void crearPanelAuto( ) {

           JTabbedPane tabbedPane = new JTabbedPane();
           tabbedPane.setPreferredSize(new Dimension(300,300));
           
           JPanel panelAuto = new JPanel();
           panelAuto.setLayout(new GridBagLayout());
         
           GridBagConstraints c2 = posicionBoton();
           
           Boton botonCargar = new Boton("Cargar Nafta");
           botonCargar.setOpaque(false);
           Color nc = new Color(176,196,222);
           panelAuto.setBackground(nc);
          
           this.panelLabel = new JPanel();
           panelLabel.setLayout(new BoxLayout(panelLabel,BoxLayout.Y_AXIS));
           panelLabel.setOpaque(false);
           panelAuto.add(panelLabel);
           
           panelAuto.add(botonCargar,c2);
           tabbedPane.addTab("Mi Auto",panelAuto);
       
           GridBagConstraints c = new GridBagConstraints();
           c.gridx =0;
           c.gridy =1;
           c.weighty = 1.0;
           c.anchor = GridBagConstraints.CENTER;
           this.add(tabbedPane,c);        
       }
            
       //Esto actualiza la PANEL INFORMACION AUTO
       public void actualizarInformacionAuto(Auto auto){
    	   
       	   //Primero debe borrar contenido anterior
    	   panelLabel.removeAll();
    	   
    	   ParteAuto parte;
           
           Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
           tabla = auto.getHashDePartes();
           Enumeration<ParteAuto> enumeracion = tabla.elements();
           
           /*RECORRO LAS PARTES DEL AUTO IMRPIMO DESCRIPCION Y VIDA UTIL*/
           while(enumeracion.hasMoreElements()){   
        	        JLabel jParteAuto = new JLabel();
        	   		parte=enumeracion.nextElement();
             try{
                     String nombreParte =parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                     Double vidaUtil= parte.getVidaUtil();
                     jParteAuto.setText(nombreParte+" "+vidaUtil);  
                     panelLabel.add(jParteAuto);                
             }catch (BoundsException e){}
           
           }
      	   
       }
       
       /*TENGO QUE RECIBIR LA LISTA DE RESERVAS DEL TALLER*/
       private JPanel crearPanelReserva() {

    	   Color nc = new Color(176,196,222);
    	   JPanel panelBodega = new JPanel();
    	   panelBodega.setLayout(new GridBagLayout());
    	   panelBodega.setBackground(nc);
         
    	    try {
    	    	panelBodega.add(actualizarListaReserva());
    		} catch(WrongUsername e){}
          
          Boton botonCambiar = new Boton("Cambiar");
          panelBodega.add(botonCambiar);
               
          return panelBodega;
       }


       public JComboBox actualizarListaReserva()throws WrongUsername{

		    /*DATOS TEMPORALES PARA PROBAR*/
		    Usuario usuario = new Usuario("Vicky");
		    Taller taller = new Taller(usuario);
		    
		    FabricaDeEscapes  fabricaEscapes = new FabricaDeEscapes();
		    Escape escape = fabricaEscapes.fabricar(fabricaEscapes.getModelos().get(0));
		    taller.aniadirAReserva(escape);
		    FabricaDeCarrocerias  fabricaCarrocerias = new FabricaDeCarrocerias();
		    Carroceria carroceria = fabricaCarrocerias.fabricar(fabricaCarrocerias.getModelos().get(0));
		    taller.aniadirAReserva(carroceria);
		    /**/
	    
		    listaPartesEnTaller = new DefaultComboBoxModel();
	
		    JComboBox comboBox = new JComboBox();
	        //no me toma bien el iterador nose porque sigue en tratativas
		    try{
		    	String descripcion = taller.getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
		    	agregarABodega(descripcion);
		    }catch(BoundsException e){}
		    
		    comboBox.setModel(listaPartesEnTaller);
			
		    return comboBox;
       
       }
       
       private void agregarABodega(String nombreParte){
    	   		listaPartesEnTaller.addElement(nombreParte); 
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
       public void actualizarPanelDinero(double dinero){
    	   this.boxDinero.setText(Double.toString(dinero) + " Algo$");
       }
       
       /* PANEL PISTA */
       private JPanel crearPanelPista(){
               
               Color nc = new Color(176,196,222);
               JPanel panelPista = new JPanel();
               panelPista.setLayout(new GridBagLayout());
               panelPista.setBackground(nc);
               
               boxPistaLongitud = new JLabel();
               boxPistaVelodidadAire = new JLabel();
               boxPistaSuperficie = new JLabel();
               
               panelPista.add(boxPistaLongitud);
               panelPista.add(boxPistaVelodidadAire);
               panelPista.add(boxPistaSuperficie);
               
               panelPista.setLayout(new BoxLayout(panelPista, BoxLayout.PAGE_AXIS));
                              
               return panelPista;
       }
       
       //Esto actualiza la PANELPISTA
       public void actualizarPanelPista(Pista proximaPista){
    	   //TODO: fijate que puse 3 label... si ves alguna mejor cambialo
    	   this.boxPistaLongitud.setText( "Longitud: " + Double.toString( proximaPista.getLongitud() ) + " metros" );
    	   //TODO: siempre es cero la velocidad del aire??
    	   this.boxPistaVelodidadAire.setText( "Velocidad del aire: " + Integer.toString( proximaPista.getVelocidadAire() ) + " Km/h" );
    	   //TODO: Se tendria que decir de que tipo es la pista. 
    	   String superficie = "Asfalto"; //Supongo que es ASFALTO
    	   this.boxPistaSuperficie.setText( "Superficie: " + superficie );	   
       }
       
       /* PANEL PISTA DINERO BODEGA */
       private void crearPanelPistaDineroBodega(){
               
               JTabbedPane tabbedPane = new JTabbedPane();
               tabbedPane.setPreferredSize(new Dimension(250,100));

		       tabbedPane.addTab("Dinero",crearPanelDinero());
		       
		       tabbedPane.addTab("Bodega",crearPanelReserva());
		      
		       tabbedPane.addTab("Proxima Pista",crearPanelPista());
	           
	           GridBagConstraints c = new GridBagConstraints();
	           c.gridx =1;
	           c.gridy =1;
	           c.gridheight =1;
	           Insets in=new Insets(200,0,0,0);
	           c.insets=in;
	           c.anchor = GridBagConstraints.CENTER;
           
	           this.add(tabbedPane,c);
       }
       
       //Esto actualiza la PANELBODEGA
       public void actualizarPanelBodega(String datosBodega){
    	   //metodos necesarios
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
               
               TanqueNafta tanque = fabricaTanques.fabricar(fabricaTanques.getModelos().get(0));
               tanque.setCombustible(nafta);
       
               try {
                       tanque.llenarTanque(70);
               } catch (BoundsException e1) {
                       e1.printStackTrace();
               }
                       MezcladorNafta mezclador = (MezcladorNafta) fabricaMezcladores.fabricar(fabricaMezcladores.getModelos().get(0));
                       
                       Escape escape = fabricaEscapes.fabricar(fabricaEscapes.getModelos().get(0));
                       
                       Carroceria carroceria = fabricaCarrocerias.fabricar(fabricaCarrocerias.getModelos().get(0));

                       ArrayList<Rueda> ruedas = new ArrayList<Rueda>();
                       for(int i=0;i<4;i++){
                               Rueda rueda = fabricaRuedas.fabricar(fabricaRuedas.getModelos().get(0));
                               ruedas.add(rueda);                              
                       }
                       
                       Eje eje = fabricaEjes.fabricar(fabricaEjes.getModelos().get(0));
                       
                       CajaManual caja=(CajaManual) fabricaCajas.fabricar(fabricaCajas.getModelos().get(0));
                       
                       Motor motor=fabricaMotores.fabricar(fabricaMotores.getModelos().get(0));
                       
                       Freno freno =  (Freno) fabricaPedales.fabricar(fabricaPedales.getModelos().get(1));
                       
                       try {
                               auto = new AutoManual(escape, carroceria, motor, (CajaManual) caja, (MezcladorNafta) mezclador, tanque, ruedas.get(0), ruedas.get(1),ruedas.get(2),ruedas.get(3), eje, freno);
                       } catch (WrongPartClassException e) {
                               e.printStackTrace();
                       }
                       auto.setPista(pista);

               return auto;
       }
       

}