package programaAuto;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import programaAuto.Taller.InformacionParteEnAuto;
import programaAuto.Taller.InformacionParteReserva;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.Freno;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.MezcladorNafta;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.Rueda;
import proveedorDePartes.fabricas.TanqueNafta;
import auto.AutoManual;
import combustible.Nafta;
import excepciones.BoundsException;
import excepciones.IncorrectPartForUbicationException;
import excepciones.InvalidPistaNameException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import excepciones.TankIsFullException;
import excepciones.WrongPartClassException;
import excepciones.WrongUserNameException;

public class ProgramaAuto extends Observable {
		/*
		 * En nuestro modelo hay un solo usuario.
		 */
        private Usuario usuario;
        private final int[] PremiosEnAlgo={3000,1500,500};
        private Pista pistaActual=null;	//comienzo en la eleccion de pistas
        SimuladorCarrera simulador=null;	//Objeto que se encarga de la simulacion de la carrera
        Taller tallerActual=null;
        private LinkedList<Pista> pistas=generarPistas();
        private static final double  PASO_SIMULACION = 0.05;
        ProveedorDePartes unProveedor=new ProveedorDePartes();
        
        
        public class SimuladorCarrera{
                private ArrayList<Usuario> listaDeCompetidores;
                private boolean simulando;
                private boolean pausa;
                private Pista pistaActual;
                private LinkedList<Usuario> posicionesFinales;// indica el orden de llegada de los autos
                
                SimuladorCarrera(Pista unaPista) {
                        pistaActual=unaPista;
                        simulando = false;
                        pausa = false;
                        listaDeCompetidores = new ArrayList<Usuario>();
                        posicionesFinales = new LinkedList<Usuario>();
                }
                /**
                 * Indica si la carrera se esta simulando o si no empezo  o termino.
                 * @return
                 */
                public boolean estaCorriendo(){
                	return simulando; 
                }
                /**
                 * Obtiene una lista de los competidores de la carrera
                 * @return
                 */
                @SuppressWarnings("unchecked")
				public List<Usuario> getCompetidores(){
                	return (List<Usuario>)listaDeCompetidores.clone();
                }
                
                private void agregarCompetidor(Usuario usuario){
                		usuario.getAuto().resetVariables();
                        
                		usuario.getAuto().setPista(getPista());
                		listaDeCompetidores.add(usuario);
                }
                
                synchronized void simular(){
                        simulando = true;
                        try{
	                        while(simulando){
	                                simularUnPaso();
	                                Thread.sleep(50);	//0.5 segundos
	                                while(pausa)			//espero a salir de la pausa
	                                	Thread.sleep(0);
	                        }
                        }catch(InterruptedException e){}

                }

                private void simularUnPaso(){
                        for (Usuario competidor:listaDeCompetidores){
                                Auto unAuto = competidor.getAuto();
                                if(unAuto.getPosicion() >= pistaActual.getLongitud()){
                                		agregarAPosiciones(competidor);
                                        listaDeCompetidores.remove(competidor);
                                        if(competidor==usuario){
                                        	simulando=false;
                                        	rellenarPosiciones();
                                        }
                                        	
                                }else if(unAuto.puedeSeguir()){
                                                unAuto.simular(PASO_SIMULACION);
                                }else if(competidor==usuario){
                                	listaDeCompetidores.remove(competidor); //saco usuario
                                	rellenarPosiciones();	//relleno las posiciones
                                	agregarAPosiciones(competidor);	//se agrega como ultimo
                                	simulando=false;
                      
                                }
                        }
               setChanged();
               notifyObservers();
                }
                
                private void agregarAPosiciones(Usuario competidor){
	                posicionesFinales.add(competidor);
	                competidor.getAuto().setPista(null);	//sale de la pista
                }
                
                public synchronized void setPausa(boolean pausar){
                	pausa=pausar;
                }
                
                public synchronized boolean getPausa(){
                	return pausa;
                }
                public synchronized LinkedList<Usuario> getResultados(){
                	return posicionesFinales;
                }
                
                private Comparator<Usuario> getComparadorDePosiciones(){
                	return new Comparator<Usuario>(){
						public int compare(Usuario competidor1, Usuario competidor2) {
							return (int)(competidor1.getAuto().getPosicion()-competidor2.getAuto().getPosicion());
						}
                	};
                }
                private void rellenarPosiciones(){
                	
                	Collections.sort(listaDeCompetidores,getComparadorDePosiciones());
                	for(Usuario competidor:listaDeCompetidores)
                		agregarAPosiciones(competidor);
                }
                
        }
        /**
         * genero la pista normal
         * @return
         */
        private Pista pistaNormal(){
        	try {
				Pista pistaNormal = new Pista("Normal");
				pistaNormal.setCoeficienteDeRozamientoRelativo(1);
				pistaNormal.setLongitud(2000);	//2000m
				pistaNormal.setVelocidadAire(10); //10km/h
				return pistaNormal;
			} catch (InvalidPistaNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	throw new RuntimeException("o deberia llegar a aqui en pistaNormal()");
        	
        }
        /**
         * genero la pista nieve
         * @return
         */
        private Pista pistaNieve(){
        	try {
				Pista pistaNieve = new Pista("Nieve");
				pistaNieve.setCoeficienteDeRozamientoRelativo(0.4);
				pistaNieve.setLongitud(2000);	//2000m
				pistaNieve.setVelocidadAire(30); //30km/h
				return pistaNieve;
			} catch (InvalidPistaNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	throw new RuntimeException("o deberia llegar a aqui en pistaNieve()");
        	
        }
        /**
         * genero la pista larga
         * @return
         */
        private Pista pistaLarga(){
        	try {
				Pista pistaLarga = new Pista("Larga");
				pistaLarga.setCoeficienteDeRozamientoRelativo(0.8);
				pistaLarga.setLongitud(20000);	//20000m
				pistaLarga.setVelocidadAire(15); //15km/h
				return pistaLarga;
			} catch (InvalidPistaNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	throw new RuntimeException("no deberia llegar a aqui en pistaLarga()");
        	
        }        
        /**
         * Genera las pistas que se pueden elegir para jugar.
         * @return
         */
        protected LinkedList<Pista> generarPistas(){
        	LinkedList<Pista> pistas = new LinkedList<Pista>();
        	pistas.add(pistaNormal());
        	pistas.add(pistaNieve());
        	pistas.add(pistaLarga());
        	return pistas;
        }
        /*
        public ProgramaAuto () {
                this.pistaActual=null;
                unProveedor = new ProveedorDePartes();
                usuarios = new ArrayList<Usuario>();            
        }
        */      
        /*
         * segun el diagrama de clases ProgramaAuto comienza con el usuario cargado
         */
        public ProgramaAuto (String nombre) throws WrongUserNameException {
        	usuario=nuevoUsuario(nombre);
        }
        
        /**
         * 
         * @param programa
         */
        public ProgramaAuto (Element programa) {
        	usuario= new Usuario(unProveedor,programa.getChildElements("programa").get(0));
        }
        /**
         * Esta llamada guarda el estado actual del programa con su usuario.
         */
        public Element getElement(){
            	Element raiz= new Element("programa");
            	raiz.appendChild(usuario.getElement());
            	return raiz;
            	//Document doc= new Document(raiz);
				//format(new BufferedOutputStream(new FileOutputStream(usuario.getNombre()+".xml")),
				//		doc);
        }
        
        /** Da un formato legible para los humanos
         * 
         * @param os
         * @param doc
         * @throws Exception
         */
        private void format(OutputStream os, Document doc) throws Exception {
          Serializer serializer= new Serializer(os,"ISO-8859-1");
          serializer.setIndent(4);
          serializer.setMaxLength(60);
          serializer.write(doc);
          serializer.flush();
        }
             

        public Auto autoInicial(){
        	Usuario usuariotemporal = null;
			try {
				usuariotemporal = new Usuario("___TEMPORAL_DE_PROGRAMA_AUTO___");
			} catch (WrongUserNameException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        	usuariotemporal.setDinero(999999999);
                Auto auto=null;
                Nafta nafta = new Nafta(85,15);
                
                ArrayList<InformacionDelModelo> modelos = unProveedor.getModelosDisponibles();
                
                try{
                        TanqueNafta tanque = (TanqueNafta) unProveedor.comprar(modelos.get(9), usuariotemporal);
                        tanque.setCombustible(nafta);
        
                        try {
                        		tanque.llenarTanque(70);
						} catch (TankIsFullException e) {
									e.printStackTrace();
						} catch (BoundsException e1) {
                                e1.printStackTrace();
                        }
                
                        MezcladorNafta mezclador = (MezcladorNafta) unProveedor.comprar(modelos.get(5), usuariotemporal);
                        Escape escape = (Escape) unProveedor.comprar(modelos.get(4), usuariotemporal);
                        
                        Carroceria carroceria = (Carroceria) unProveedor.comprar(modelos.get(2), usuariotemporal);

                        ArrayList<Rueda> ruedas = new ArrayList<Rueda>();
                
                        for(int i=0;i<4;i++){
                                Rueda rueda = (Rueda) unProveedor.comprar(modelos.get(8), usuariotemporal);
                                ruedas.add(rueda);                              
                        }
                        
                        Eje eje = (Eje) unProveedor.comprar(modelos.get(3), usuariotemporal);
                        
                        CajaManual caja=(CajaManual) unProveedor.comprar(modelos.get(0), usuariotemporal);
                        
                        Motor motor=(Motor) unProveedor.comprar(modelos.get(6), usuariotemporal);
                        
                        Freno freno =  (Freno) unProveedor.comprar(modelos.get(7), usuariotemporal);
                        
                        try {
                                auto = new AutoManual(escape, carroceria, motor, (CajaManual) caja, (MezcladorNafta) mezclador, tanque, ruedas.get(0), ruedas.get(1),ruedas.get(2),ruedas.get(3), eje, freno);
                        } catch (WrongPartClassException e) {
                                e.printStackTrace();
                        }
                        auto.setPista(pistaActual);
                }
                catch (NoSuchModelException e)
                {
                        e.printStackTrace();  //TODO: bastante sucio
                } catch (NotEnoughMoneyException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return auto;
        }

        /**
         * Devuelve las pistas disponibles para jugar.
         * @return
         */
        public Iterator<Pista> getPistas(){
        	return ((Collection<Pista>)pistas.clone()).iterator();
        }
        /**
         * Setea la pista a correr.
         * @param pista
         * @throws NotContainedPistaException
         * @throws PistaPickedException
         */
        public void setPista(Pista pista)throws NotContainedPistaException, PistaPickedException{
            if(pistaActual!=null)
            	throw new PistaPickedException();            	
        	if(!pistas.contains(pista)) 
            	throw new NotContainedPistaException();
        	pistaActual = pista;
        }
        /**
         * Devuelve la pista actual
         * @return
         */
        public Pista getPista(){
                return this.pistaActual;
        }
        
        public Taller entrarAlTaller() throws NoPistaPickedException, NotAbleWhileSimulatingException{
        	if(pistaActual==null)
        		throw new NoPistaPickedException();
        	if(simulador!=null)
        		throw new NotAbleWhileSimulatingException();        		
        	tallerActual=usuario.getTaller();
        	return tallerActual;
        }
        /*
         * TODO: Ver si no combiene hacer un Proxy aqui, en vez de esto.
         */
        /**
         * Checkea que se este en el estado correcto antes de llamar al Taller.
         */
        public 	void colocarParteDeReserva(InformacionParteReserva informacionReserva, InformacionParteEnAuto informacionParte)throws IncorrectPartForUbicationException, NotInTallerException{
        	if(tallerActual==null)
        		throw new NotInTallerException();       		
        	tallerActual.colocarParteDeReserva(informacionReserva, informacionParte);
        }
        /**
         * Salida del Taller y entrada a la carrera
         * @return Simulador para conocer el estado de la carrera
         * @throws NoPistaPickedException
         * @throws NotInTallerException
         */
        public SimuladorCarrera entrarALaCarrera() throws NoPistaPickedException, NotInTallerException{
        	if(pistaActual==null)
        		throw new NoPistaPickedException();
        	if(tallerActual==null)
        		throw new NotInTallerException();
        	tallerActual.ensamblar();	//ensamblo lo que coloque en el taller.
        	tallerActual=null;	//salgo del Taller
        	
        	simulador = inicializarCarrera();
            return simulador;
        }
        /**
         * Se simula la carrera hasta terminar
         * @throws NotSimulatingException 
         */
        public void correr() throws NotSimulatingException{
	    	if(simulador==null)
	    		throw new NotSimulatingException();
        	simulador.simular();
	    	premiarUsuario();
	        simulador=null;	    // termino la simulacion
	        pistaActual=null;	// ya se debe elegir otra pista        
        }
        /**
         * De acuerdo al resultado de la carrera, aumenta el dinero del usuario.
         */
        protected void premiarUsuario(){
        	int posicion=simulador.getResultados().indexOf(usuario);
        	       	
			try {
				if(posicion<PremiosEnAlgo.length)
					usuario.adquirirDinero(PremiosEnAlgo[posicion]);
			} catch (BoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        /*
         * El usuario no se crea con una pista,
         * Esta se elige luego.
         */
        protected Usuario nuevoUsuario(String nombre) throws WrongUserNameException {
                Auto unAuto = autoInicial();
                Usuario unUsuario = new Usuario(nombre);
                unUsuario.setAuto(unAuto);
                                      
                return unUsuario;
        }

        private SimuladorCarrera inicializarCarrera() {
                SimuladorCarrera unaSimulacion = new SimuladorCarrera(getPista());
                unaSimulacion.agregarCompetidor(usuario);
                
                return unaSimulacion;
        }
		
        //TODO: Necesito el proveedor!!!!!
        public ProveedorDePartes getUnProveedor() {
			return unProveedor;
		}
        
        //TODO: debe ser generado por el programa, anda mal el random
        /*
         * No deberia poner esto aca, pero para que utilizen el random...
         */
        public Pista generarProximaPista(){
    		Random rand= new Random(new Date().getTime());
    		return(pistas.get((int)(rand.nextDouble()*pistas.size())));	
        }
		
        public Usuario getUsuario() {
			return usuario;
		}
        
        public void comprarNafta(double cantidad) throws NotEnoughMoneyException, BoundsException, TankIsFullException {
            usuario.getAuto().cargarCombustible(cantidad);
            usuario.gastarDinero(cantidad * 4 );
        }
        
}
