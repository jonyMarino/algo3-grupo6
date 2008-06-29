package programaAuto;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;

import nu.xom.Element;
import excepciones.BoundsException;
import excepciones.IncorrectPartForUbicationException;
import excepciones.TankIsFullException;
import excepciones.UbicationUnkownException;
import proveedorDePartes.fabricas.Acelerador;
import proveedorDePartes.fabricas.Caja;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.Freno;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.Rueda;
import proveedorDePartes.fabricas.TanqueCombustible;

/**
 * Auto es una clase que intenta encapsular el comportamiento y las características de un auto.
 * Cada Auto está formado por distintas {@link PartesAuto}, como ser el {@link Motor}, la {@link Caja}, el {@link Eje} o
 * la {@link Carroceria}, entre otras.
 *
 *   @see PartesAuto
 *   @see Motor
 *   @see Caja
 *   @see Carroceria
 *   @see Escape
 *   @see TanqueCombustible
 *   @see Acelerador
 *   @see Freno
 *   @see Rueda
 *   @see Eje
 *   @see Mezclador
 */
public abstract class Auto extends Observable{
	public enum Ubicacion{
		
		MOTOR("Motor"),
		CAJA("Caja"),
		MEZCLADOR("Mezclador"),
		EJE("Eje"),
		ESCAPE("Escape"),
		CARROCERIA("Carroceria"),
		FRENO("Freno"),
		TANQUE("Tanque"),
		RUEDA_TRASERA_IZQUIERDA("Rueda trasera izquierda"),
		RUEDA_TRASERA_DERECHA("Rueda trasera derecha"),
		RUEDA_DELANTERA_IZQUIERDA("Rueda delantera izquierda"),
		RUEDA_DELANTERA_DERECHA("Rueda delantera derecha");
		private String descripcion;
		Ubicacion(String descripcion){
			this.descripcion=descripcion;
		}
		@Override
		public String toString(){
			return descripcion;
		}
		
	}
	private Escape 		           escape;
	private Carroceria 	           carroceria;
	private Motor 		           motor;
	private Freno 		           freno;
	private Eje                    eje;
	private TanqueCombustible      tanqueCombustible;
	private Mezclador              mezclador;
	private double		           velocidad;
	private double		           posicion;
	private Hashtable<Ubicacion, ParteAuto> partes= new Hashtable <Ubicacion, ParteAuto>();
	private Caja 				   caja;
	private Pista	               pista;
	// cuando se coloca una parte, se le avisa a esta cadena.
	private CadenaEnsambladores cadenaEnsambladores;	
	//Ensambladores. Implementa patron de diseño cadena.
	// El asunto es si modelar que partes de la misma jerarquia puedan ensamblarse de diferentes formas
	// yo en ese caso utilizaria Strategy pasando los ensambladores. Habria que agregar un des-ensamblar  
	// y Tratar solo al objeto en cuestion
	protected interface Ensamblador{
		/**
		 * Colaca la nueva parte, des-ensamblando la nueva
		 * @param parte parte a colocar en el auto
		 * @param Ubicacion 
		 * @return verdadero si sabe colocar la parte
		 */
		boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException;
		/**
		 * Ensambla la parte ya colocada al Auto si es que corresponde a Ubicacion
		 * @param ubicacion ubicacion en el Auto
		 * @return verdadero si pudo ensamblarla
		 */
		boolean ensamblar(Ubicacion ubicacion);
		/**
		 * Ensambla su parte.
		 */
		//void ensamblar();
	}
	/**
	 * CadenaEnsambladores guarda los ensambladores para el auto
	 * y las partes que deben ensamblarse
	 *
	 */
	private class CadenaEnsambladores{
		LinkedList<Ensamblador> ensambladores=new LinkedList<Ensamblador>();
		LinkedList<Ubicacion> partesAEnsamblar=new LinkedList<Ubicacion>();
		LinkedList<Ensamblador> ensambladoresParaEnsamblar=new LinkedList<Ensamblador>();
		//Hashtable<String,Ensamblador> partesAEnsamblarConEnsamblador=new Hashtable<String,Ensamblador>();
		void colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException,UbicationUnkownException{
			for(Ensamblador ensamblador:ensambladores){
				if(ensamblador.colocar(parte, ubicacion)){
					//aniadirAListaPorEnsamblar(ubicacion,ensamblador);
					return;
				}
			}
			throw new UbicationUnkownException(ubicacion.toString());
		}
		void ensamblar()throws UbicationUnkownException{
			for(Ubicacion ubicacion:partesAEnsamblar){
				boolean found=false;
				for(Ensamblador ensamblador:ensambladores){
					found=ensamblador.ensamblar(ubicacion);
					if(found)
						break;
				}
				if(!found)
					throw new UbicationUnkownException(ubicacion.toString());
			}
			partesAEnsamblar.clear();
			
			
			/*for(String ubicacion:partesAEnsamblarConEnsamblador.keySet()){
				Ensamblador ensamblador=partesAEnsamblarConEnsamblador.get(ubicacion);
				ensamblador.ensamblar(ubicacion);
			}
			*/
		}
		void aniadirAListaPorEnsamblar(Ubicacion ubicacion){
			partesAEnsamblar.add(ubicacion);
		}
		void aniadirAListaPorEnsamblar(Ensamblador ensamblador){
			ensambladoresParaEnsamblar.add(ensamblador);
		}
		/*void aniadirAListaPorEnsamblar(String ubicacion,Ensamblador ensamblador){
			partesAEnsamblarConEnsamblador.put(ubicacion, ensamblador);
		}
		*/
		void aniadirEnsamblador(Ensamblador ensamblador){
			ensambladores.add(ensamblador);
		}
		
	}
/**
 * 
 * @author Jony
 *
 */
	private class EnsambladorMotor implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.MOTOR)
				return false;
			if(!(parte instanceof Motor))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un motor");			
			Motor motorASacar=getMotor();
			if(motorASacar!=null){
				motorASacar.setCaja(null);
				motorASacar.setEscape(null);
				motorASacar.setMezclador(null);				
			}
			if(getCaja()!=null)
				getCaja().setMotor(null);
			setMotor((Motor)parte);	//coloco el nuevo motor
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.MOTOR)
				return false;
			ensamblar();
			return true;
		}
		public void ensamblar(){
			getMotor().setCaja(getCaja());
			getMotor().setEscape(getEscape());
			getMotor().setMezclador(getMezclador());
			getCaja().setMotor(getMotor());	
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorCaja implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.CAJA)
				return false;
			if(!(parte instanceof Caja))
				throw new IncorrectPartForUbicationException("No se tiene referencia a una caja");
			getCaja().setEje(null);//Caja vieja
			getCaja().setMotor(null);
			setCaja((Caja)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.CAJA)
				return false;
			ensamblar();
			return true;
		}
		public void ensamblar(){
			getCaja().setEje(getEje());
			getCaja().setMotor(getMotor());
			getMotor().setCaja(getCaja());	
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorEje implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.EJE)
				return false;
			if(!(parte instanceof Eje))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un eje");
			getCaja().setEje(null);//Caja vieja
			getCaja().setMotor(null);
			getMotor().setCaja(null);
			setCaja((Caja)parte);
			return true;			
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.EJE)
				return false;
			ensamblar();
			return true;
		}
		public void ensamblar(){
			getCaja().setEje(getEje());
			getCaja().setMotor(getMotor());
			getMotor().setCaja(getCaja());
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorEscape implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.ESCAPE)
				return false;
			if(!(parte instanceof Escape))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un escape");			
			getMotor().setEscape(null);
			setEscape((Escape)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.ESCAPE)
				return false;	
			ensamblar();
			return true;
		}
		public void ensamblar(){			
			getMotor().setEscape(getEscape());
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorCarroceria implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.CARROCERIA)
				return false;
			if(!(parte instanceof Carroceria))
				throw new IncorrectPartForUbicationException("No se tiene referencia a una carroceria");			
			getCarroceria().setAuto(null);
			setCarroceria((Carroceria)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.CARROCERIA)
				return false;
			ensamblar();
			return true;
		}
		public void ensamblar(){
			getCarroceria().setAuto(Auto.this);
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorFreno implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.FRENO)
				return false;
			if(!(parte instanceof Freno))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un freno");			
			getFreno().setEje(null);
			setFreno((Freno)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.FRENO)
				return false;
			ensamblar();
			return true;
		}
		public void ensamblar(){
			getFreno().setEje(getEje());
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorMezclador implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.MEZCLADOR)
				return false;
			if(!(parte instanceof Mezclador))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un mezclador");			
			getMezclador().setTanqueCombustible(null);
			getMotor().setMezclador(null);	
			setMezclador((Mezclador)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.MEZCLADOR)
				return false;
			getMezclador().setTanqueCombustible(getTanqueCombustible());
			getMotor().setMezclador(getMezclador());
			return true;
		}
		public void ensamblar(){}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorRueda implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.RUEDA_DELANTERA_DERECHA 
			   && ubicacion!=Ubicacion.RUEDA_DELANTERA_IZQUIERDA
			   && ubicacion!=Ubicacion.RUEDA_TRASERA_DERECHA
			   && ubicacion!=Ubicacion.RUEDA_TRASERA_IZQUIERDA)
				return false;
			if(!(parte instanceof Rueda))
				throw new IncorrectPartForUbicationException("No se tiene referencia a una rueda.");			
			if ( ubicacion == Ubicacion.RUEDA_DELANTERA_DERECHA ){
					getRuedaDelanteraDerecha().setAuto(null);
					setRuedaDelanteraDerecha((Rueda)parte);
			}
			if ( ubicacion == Ubicacion.RUEDA_DELANTERA_IZQUIERDA){
					getRuedaDelanteraIzquierda().setAuto(null);
					setRuedaDelanteraIzquierda((Rueda)parte);
			}
			if ( ubicacion == Ubicacion.RUEDA_TRASERA_DERECHA){
					getRuedaTraseraDerecha().setAuto(null);
					setRuedaTraseraDerecha((Rueda)parte);
			}
			if ( ubicacion == Ubicacion.RUEDA_TRASERA_IZQUIERDA ){
					getRuedaTraseraIzquierda().setAuto(null);
					getEje().setRuedaTrasera(null);
					setRuedaTraseraIzquierda((Rueda)parte);
			}
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.RUEDA_DELANTERA_DERECHA 
			   && ubicacion!=Ubicacion.RUEDA_DELANTERA_IZQUIERDA
			   && ubicacion!=Ubicacion.RUEDA_TRASERA_DERECHA
			   && ubicacion!=Ubicacion.RUEDA_TRASERA_IZQUIERDA)
						return false;		
			if ( ubicacion == Ubicacion.RUEDA_DELANTERA_DERECHA ){
					getRuedaDelanteraDerecha().setAuto(Auto.this);
			}
			if ( ubicacion == Ubicacion.RUEDA_DELANTERA_IZQUIERDA ){
					getRuedaDelanteraIzquierda().setAuto(Auto.this);
			}
			if ( ubicacion == Ubicacion.RUEDA_TRASERA_DERECHA ){
					getRuedaTraseraDerecha().setAuto(Auto.this);
			}
			if ( ubicacion == Ubicacion.RUEDA_TRASERA_IZQUIERDA ){
					getRuedaTraseraIzquierda().setAuto(Auto.this);
					getEje().setRuedaTrasera(getRuedaTraseraIzquierda());
			}
		
			return true;
		}
	}
	/**
	 * 
	 * @author Jony
	 *
	 */
	private class EnsambladorTanqueCombustible implements Ensamblador{
		public boolean colocar(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException{
			if(ubicacion!=Ubicacion.TANQUE)
				return false;
			if(!(parte instanceof TanqueCombustible))
				throw new IncorrectPartForUbicationException("No se tiene referencia a un tanque");			
			getMezclador().setTanqueCombustible(null);
			setTanqueCombustible((TanqueCombustible)parte);
			return true;
		}
		public boolean ensamblar(Ubicacion ubicacion){	
			if(ubicacion!=Ubicacion.TANQUE)
				return false;
			getMezclador().setTanqueCombustible(getTanqueCombustible());
			return true;	
		}
		public void ensamblar(){}
	}
	/**
	 * 
	 */
	private void crearCadenaDeEnsamblaje(){
		cadenaEnsambladores=new CadenaEnsambladores();
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorMotor());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorCaja());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorEje());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorEscape());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorFreno());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorMezclador());	
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorTanqueCombustible());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorRueda());
		cadenaEnsambladores.aniadirEnsamblador(new EnsambladorCarroceria());

	}
	/*
	 * Inicializacion comun a todos los constructores 
	 */
	{
		crearCadenaDeEnsamblaje();
	}
	/**
	 * Crea un nuevo auto con las partes especificadas.
	 *
	 * @param escape El escape.
	 * @param carroceria La carrocería.
	 * @param motor El motor.
	 * @param caja La caja.
	 * @param mezclador El mezclador.
	 * @param tanque El tanqueCombustible.
	 * @param rueda1 La rueda  delantera derecha.
	 * @param rueda2 La rueda delantera izquierda.
	 * @param rueda3 La rueda trasera derecha.
	 * @param rueda4 La rueda trasera izquierda.
	 */
	public Auto(Escape escape, Carroceria carroceria, Motor motor,
	            Caja caja,Mezclador mezclador, TanqueCombustible tanqueCombustible,
	            Rueda rueda1, Rueda rueda2, Rueda rueda3, Rueda rueda4, Eje eje, Freno freno) {

		setCarroceria(carroceria);
		setMotor(motor);
		setEscape(escape);
		setMezclador(mezclador);
		setTanqueCombustible(tanqueCombustible);
		setEje(eje);
		setCaja(caja);	
		setFreno(freno);
		setRuedaDelanteraDerecha(rueda1);
		setRuedaDelanteraIzquierda(rueda2);
		setRuedaTraseraDerecha(rueda3);
		setRuedaTraseraIzquierda(rueda4);
		
		setPista(null);
		
		ensamblar();
	}
	/**
	 * 
	 * @param auto
	 */
	public Auto(ProveedorDePartes proveedor,Element element){
		
		Element auto=element.getFirstChildElement("auto");
		
		for(Ubicacion ubicacion: Ubicacion.values()){
			Element elemento=auto.getFirstChildElement(ubicacion.toString());
			String nombreModelo = elemento.getFirstChild("modelo");
			InformacionDelModelo informacionDelModelo=proveedor.getModelo(nombreModelo);
			ParteAuto parte=proveedor.obtenerParte(informacionDelModelo);
			parte.updateSinceElement(elemento);
			colocarParte(parte,ubicacion);
		
		}
		ensamblar();
	}

	/**
	 * Coloca la parte en el auto.
	 * @param parte
	 * @param ubicacion
	 * @throws IncorrectPartForUbicationException
	 * @throws UbicationUnkownException
	 */
	public void colocarParte(ParteAuto parte,Ubicacion ubicacion)throws IncorrectPartForUbicationException,UbicationUnkownException{
		cadenaEnsambladores.colocar(parte, ubicacion);
	}
	/**
	 * ensambla las piezas agregadas.
	 */
	public void ensamblar(){
		try{
			cadenaEnsambladores.ensamblar();
		}catch(UbicationUnkownException e){
			throw new RuntimeException("Falta ensamblador para: "+e.getUbicacion());
		}
	}
//Partes Auto
	@SuppressWarnings("unchecked")
	public Hashtable<Ubicacion, ParteAuto> getHashDePartes(){
		return (Hashtable<Ubicacion, ParteAuto>)partes.clone();
	}
//VELOCIDAD

	/**
	 * Devuelve la velocidad actual del Auto en metros por segundo.
	 * @see getKilometrosPorHora
	 * @return La velocidad actual del Auto.
	 */
	public double getVelocidad() {
		return velocidad;
	}

	/**
	 * Devuelve las revoluciones por minuto de las Ruedas.
	 *
	 * @return Las Rpm de las Ruedas.
	 *
	 * @see Rueda
	 */
	public double obtenerRpm() {
		return getRuedaTraseraIzquierda().getRPM();
	}
	/**
	 * convierte metros por segundo a kilometros por hora
	 *
	 * @param metrosPorSegundo magnitud en metros por segundo
	 */
	private double convertirKilometrosPorHora(double metrosPorSegundo){
		return metrosPorSegundo /1000 * 3600;
	}
	/**
	 * Devuelve la velocidad en kilometros por hora
	 *
	 * @param metrosPorSegundo magnitud en metros por segundo
	 */
	public double getKilometrosPorHora(){
		return convertirKilometrosPorHora(getVelocidad());
	}
	/**
	 * Calcula la velocidad para el Auto despues de transcurridos t segundos.
	 *
	 * @param segundosTranscurridos los segundos transcurridos.
	 * @param pista la pista por la que se mueve el auto.
	 */
	public void simular(double segundosTranscurridos) {
		double masaAuto = this.getPeso();
		double fuerzaEje = this.getEje().getFuerza();
		double fuerzaAire = this.getCarroceria().getFuerzaAire();
		double aceleracion = (((fuerzaEje - fuerzaAire) /masaAuto));
		double velocidadAnterior = getVelocidad();
		this.setVelocidad(velocidadAnterior + aceleracion * segundosTranscurridos);
		calcularPosicion(velocidadAnterior, aceleracion, segundosTranscurridos);
		for(ParteAuto partesAuto:this.getPartes())
			partesAuto.desgastar((int)(segundosTranscurridos));
		setChanged();
	    notifyObservers(Double.toString(segundosTranscurridos));
	}

//POSICION
	
	private void calcularPosicion(double velocidad, double aceleracion, double segundosTranscurridos) {
		setPosicion(getPosicion()+velocidad*segundosTranscurridos+aceleracion*segundosTranscurridos*segundosTranscurridos/2);
	}


	public void setPosicion(double posicion) {
		this.posicion = posicion;
	}


	public double getPosicion() {
		return posicion;
	}


	private void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

//ESCAPE

	/**
	 * Devuelve el {@link Escape} asociado al Auto.
	 *
	 * @return El {@link Escape} asociado.
	 *
	 * @see Escape
	 */
	public Escape getEscape() {
		return escape;
	}

	/**
	 * Le asigna un {@link Escape} al Auto.
	 *
	 * @param escape el {@link Escape} a asignar.
	 *
	 * @see Escape
	 */
	public void setEscape(Escape escape) {
		this.escape = escape;
		partes.put(Ubicacion.ESCAPE, escape);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.ESCAPE);
	}

//CARROCERIA

	/**
	 * Devuelve la {@link Carroceria} asociada al Auto.
	 *
	 * @return La {@link Carroceria} asociada.
	 *
	 * @see Carroceria
	 */
	public Carroceria getCarroceria() {
		return carroceria;
	}

	/**
	 * Asigna una {@link Carroceria} al Auto.
	 *
	 * @param carroceria La {@link Carroceria} a asignar.
	 *
	 * @see Carroceria
	 */
	public void setCarroceria(Carroceria carroceria) {
		this.carroceria = carroceria;
		partes.put(Ubicacion.CARROCERIA, carroceria);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.CARROCERIA);
	}

//MOTOR

	/**
	 * Devuelve el {@link Motor} asociado al Auto.
	 *
	 * @return El {@link Motor} asociado.
	 *
	 * @see Motor
	 */
	public Motor getMotor() {
		return motor;
	}

	/**
	 * Asigna un {@link Motor} al Auto.
	 *
	 * @param motor El {@link Motor} a asignar.
	 *
	 * @see Motor
	 */
	public void setMotor(Motor motor) {
		this.motor = motor;
		partes.put(Ubicacion.MOTOR, motor);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.MOTOR);
	}

	/**
	 * Devuelve las revoluciones por minuto del {@link Motor}.
	 *
	 * @return Las RPM del {@link Motor}.
	 *
	 * @see Motor
	 */
	public double getRPM() {
		double rpm = this.getMotor().obtenerRPM();
		return rpm;
	}

//Freno

	/**
	 * Devuelve el {@link Freno} asociado al Auto.
	 *
	 * @return El {@link Freno} asociado.
	 *
	 * @see Freno
	 */
	public Freno getFreno() {
		return freno;
	}

	/**
	 * Asigna un {@link Freno} al Auto.
	 *
	 * @param freno El {@link Freno} a asignar.
	 *
	 * @see Freno
	 */
	public void setFreno(Freno freno) {
		this.freno = freno;
		partes.put(Ubicacion.FRENO, freno);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.FRENO);
	}

	/**
	 * aumenta la aceleracion del motor.
	 *
	 * @param intensidad Intensidad es grado de fuerza que se le aplica al Acelerador.
	 *
	 * @see Acelerador
	 */
	public void presionarAcelerador(double intensidad)throws BoundsException  {
		getMotor().acelerar(intensidad);
	}

	/**
	 * Pisa el Pedal del Freno.
	 *
	 * @param intensidad Intensidad es grado de fuerza que se le aplica al Freno.
	 *
	 * @see Freno
	 */
	public void presionarFreno(double intensidad) {
		this.getFreno().presionar(intensidad);
	}

//RUEDA

	/**
	 * Devuelve la {@link Rueda} asociado al Auto.
	 *
	 * @return La {@link Rueda} delatera derecha asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaDelanteraDerecha() {
		return (Rueda)partes.get(Ubicacion.RUEDA_DELANTERA_DERECHA);
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda delantera derecha La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaDelanteraDerecha(Rueda rueda) {
		partes.put(Ubicacion.RUEDA_DELANTERA_DERECHA, rueda);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.RUEDA_DELANTERA_DERECHA);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto.
	 *
	 * @return La {@link Rueda} delatera izquierda asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaDelanteraIzquierda() {
		return ((Rueda)partes.get(Ubicacion.RUEDA_DELANTERA_IZQUIERDA));
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda delantera izquierda La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaDelanteraIzquierda(Rueda rueda) {
		partes.put(Ubicacion.RUEDA_DELANTERA_IZQUIERDA, rueda);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.RUEDA_DELANTERA_IZQUIERDA);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto.
	 *
	 * @return La {@link Rueda} trasera derecha asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaTraseraDerecha() {
		return (Rueda)partes.get(Ubicacion.RUEDA_TRASERA_DERECHA);
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda trasera derecha La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaTraseraDerecha(Rueda rueda) {
		partes.put(Ubicacion.RUEDA_TRASERA_DERECHA, rueda);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.RUEDA_TRASERA_DERECHA);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto.
	 *
	 * @return La {@link Rueda} trasera izquierda asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaTraseraIzquierda() {
		return (Rueda)partes.get(Ubicacion.RUEDA_TRASERA_IZQUIERDA);
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda trasera izquierda La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaTraseraIzquierda(Rueda rueda) {
		partes.put(Ubicacion.RUEDA_TRASERA_IZQUIERDA, rueda);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.RUEDA_TRASERA_IZQUIERDA);
	}

//TANQUE COMBUSTIBLE

	/**
	 * Devuelve el {@link TanqueCombustible} asociado al Auto.
	 *
	 * @return El {@link TanqueCombustible} asociado.
	 *
	 * @see TanqueCombustible
	 */
	public TanqueCombustible getTanqueCombustible() {
		return tanqueCombustible;
	}

	/**
	 * Asigna un {@link TanqueCombustible} al Auto.
	 *
	 * @param El {@link TanqueCombustible} a asignar.
	 *
	 * @see TanqueCombustible
	 */
	public void setTanqueCombustible(TanqueCombustible tanqueCombustible) {
		this.tanqueCombustible = tanqueCombustible;
		partes.put(Ubicacion.TANQUE, tanqueCombustible);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.TANQUE);
	}

	/**
	 * Devuelve la cantidad de Combustible que posee el Auto.
	 *
	 * @return La cantidad de Combustible asociada.
	 *
	 * @see TanqueCombustible
	 */
	public double obtenerCantidadCombustible() {
		return this.getTanqueCombustible().getCantidadCombustible();
	}

	/**
	 * Carga el {@link TanqueCombustible} de Combustible.
	 *
	 * @param El combustible a asignar.
	 * @throws BoundsException 
	 * @throws TankIsFullException 
	 *
	 * @see TanqueCombustible
	 */
	public void cargarCombustible(double litros) throws TankIsFullException, BoundsException {
		this.getTanqueCombustible().llenarTanque(litros);
	}

//MEZCLADOR

	/**
	 * Devuelve el {@link Mezclador} asociado al Auto.
	 *
	 * @return El {@link Mezclador} asociado.
	 *
	 * @see Mezclador
	 */
	public Mezclador getMezclador() {
		return mezclador;
	}

	/**
	 * Asigna un {@link Mezclador} al Auto.
	 *
	 * @param El {@link Mezclador} a asignar.
	 *
	 * @see Mezclador
	 */
	public void setMezclador(Mezclador mezclador) {
		this.mezclador = mezclador;
		this.getMotor().setMezclador(this.getMezclador());
		partes.put(Ubicacion.MEZCLADOR, mezclador);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.MEZCLADOR);
	}

// CAJA

	/**
	 * Asigna un {@link Caja} al Auto.
	 *
	 * @param El {@link Caja} a asignar.
	 *
	 * @see Caja.
	 */
	public void setCaja(Caja caja){
		this.caja = caja;
		partes.put(Ubicacion.CAJA, caja);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.CAJA);
	}

	/**
	 * Devuelve el {@link Caja} asociado al Auto.
	 *
	 * @return La {@link Caja} asociada.
	 *
	 * @see Caja.
	 */
	public Caja getCaja() {
		return caja;
	}

//EJE

	/**
	 * Devuelve el {@link Eje} asociado al Auto.
	 *
	 * @return El {@link Eje} asociado.
	 *
	 * @see Eje
	 */
	public Eje getEje() {
		return eje;
	}

	/**
	 * Asigna un {@link Eje} al Auto.
	 *
	 * @param El {@link Eje} a asignar.
	 *
	 * @see Eje
	 */
	public void setEje(Eje eje) {
		this.eje = eje;
		partes.put(Ubicacion.EJE, eje);
		cadenaEnsambladores.aniadirAListaPorEnsamblar(Ubicacion.EJE);
	}

//PESO

	/**
	 * Devuelve el peso actual del Auto.
	 *
	 * @return El peso actual del Auto.
	 */
	public double getPeso() {
		//Se actualiza constantemente
		return this.calcularPeso();
	}

	private double calcularPeso() {
		int peso = 0;
		for(ParteAuto p:getPartes())
			peso += p.getPeso();
		return peso;
	}

	protected LinkedList<ParteAuto> getPartes(){
		LinkedList<ParteAuto> listaDePartes=new LinkedList<ParteAuto>();
		Enumeration<ParteAuto> enumeracion = partes.elements();
		while(enumeracion.hasMoreElements())
			listaDePartes.add(enumeracion.nextElement());
		return listaDePartes;
	}

	//PISTA
	public Pista getPista() {
		return pista;
	}


	public void setPista(Pista pista) {
		this.pista = pista;
	}
	/**
	 * Resetea las variables fisicas del movimiento del auto.
	 */
	public void resetVariables(){
		setPosicion(0);
		setVelocidad(0);
	}
	


	
//ESTADO DEL AUTO
	public boolean puedeSeguir(){
		if((getMotor().getVidaUtil() == 0) && (getVelocidad() == 0))
			return false;
		if((getTanqueCombustible().getVidaUtil() == 0) || (getTanqueCombustible().getCantidadCombustible() == 0))
			if(getVelocidad()==0)
				return false;
		return true;
	}
}
