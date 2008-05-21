package auto;
import java.util.LinkedList;
import pista.Pista;
import auto.partesAuto.Carroceria;
import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueCombustible;
import auto.partesAuto.caja.Caja;

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
public abstract class Auto {
	private Escape 		           escape;
	private Carroceria 	           carroceria;
	private Motor 		           motor;
	private Acelerador	           acelerador;
	private Freno 		           freno;
	private LinkedList<Rueda>      ruedas;
	private Eje                    eje;
	private double		           velocidad;
	private LinkedList<PartesAuto> partes;
	private static double          aceleracionGravedad = 9.8;

	/**
	 * Crea un nuevo auto con las partes especificadas.
	 * 
	 * @param escape el escape
	 * @param carroceria la carrocería
	 * @param motor el motor
	 * @param caja la caja
	 * @param mezcladorNafta el mezclador
	 * @param tanque el tanque
	 * @param rueda1 la rueda  delantera derecha
	 * @param rueda2 la rueda delantera izquierda
	 * @param rueda3 la rueda trasera derecha
	 * @param rueda4 la rueda trasera derecha
	 */
	public Auto(Escape escape, Carroceria carroceria, Motor motor,
	            Caja caja,Mezclador mezclador, TanqueCombustible tanque,
	            Rueda rueda1, Rueda rueda2, Rueda rueda3, Rueda rueda4){

		this.escape = escape;
		this.setCarroceria(carroceria);
		this.setMotor(motor);
		
		//Ruedas
		ruedas = new LinkedList<Rueda>();
		ruedas.add(rueda1);
		ruedas.add(rueda2);
		ruedas.add(rueda3);
		ruedas.add(rueda4);
		for(Rueda r : ruedas)
			r.setAuto(this);
		//Velocidad
		this.setVelocidad(0);

		//Asignar Eje
		this.asignarEje();

		caja.setEje(getEje());
		caja.setMotor(getMotor());
		motor.setCaja(caja);

		//Asignar Pedales
		this.asignadorPedales();

		partes = new LinkedList<PartesAuto>();
		partes.add(escape);
		partes.add(carroceria);
		partes.add(motor);
		partes.add(mezclador);
		partes.add(tanque);
		partes.add(rueda1);
		partes.add(rueda2);
		partes.add(rueda3);
		partes.add(rueda4);
		partes.add(freno);
		partes.add(acelerador);
		partes.add(caja);
	}


//VELOCIDAD
	
	/**
	 * Devuelve la velocidad actual del Auto.
	 * 
	 * @return La velocidad actual del Auto.
	 */
	public double getVelocidad(){
		return velocidad;
	}

	/**
	 * Devuelve las revoluciones por minuto de las Ruedas.
	 * 
	 * @return Las Rpm de las Ruedas
	 * @see Rueda
	 */
	public double obtenerRpm(){
		return ruedas.get(0).getRPM();
	}

	/**
	 * Calcula la velocidad para el Auto despues de transcurridos t segundos.
	 * 
	 * @param segundosTranscurridos los segundos transcurridos
	 * @param pista la pista por la que se mueve el auto
	 * 
	 * @see Pista
	 */
	public void calcularVelocidad(int segundosTranscurridos,Pista pista){
		Eje eje = this.getEje();
		Carroceria carroceria = this.getCarroceria();
		double masaAuto = this.getPeso()*aceleracionGravedad;
		double fuerzaEje = eje.getFuerza();
		double fuerzaAire = carroceria.getFuerzaAire(pista);
		double incrementoVelocidad = (((fuerzaEje-fuerzaAire)/masaAuto)*segundosTranscurridos);
		this.setVelocidad(this.getVelocidad()+incrementoVelocidad);
		for(PartesAuto p:partes)
			p.desgastar(segundosTranscurridos);
	}

	private void setVelocidad(double velocidad){
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
	 * 
	 * Le asigna un {@link Escape} al Auto.
	 * 
	 * @param escape el {@link Escape} a asignar.
	 * 
	 * @see Escape
	 */
	public void setEscape(Escape escape) {
		this.escape = escape;
		Motor motor = this.getMotor();
		motor.setEscape(this.getEscape());
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
	 * @param carroceria La {@link Carroceria} a asignar
	 * 
	 * @see Carroceria
	 */
	public void setCarroceria(Carroceria carroceria) {
		this.carroceria = carroceria;
	}


//MOTOR
	
	/**
	 * Devuelve el {@link Motor} asociado al Auto
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
	}

	
	/**
	 * Devuelve las revoluciones por minuto del {@link Motor}.
	 * 
	 * @return Las RPM del {@link Motor}.
	 * 
	 * @see Motor
	 */
	public double getRPM(){
		Motor motor = this.getMotor();
		double rpm = motor.obtenerRPM();
		return rpm;
	}

//PEDALES
	public Acelerador getAcelerador() {
		return acelerador;
	}

	public void setAcelerador(Acelerador acelerador) {
		this.acelerador = acelerador;
	}

	public Freno getFreno() {
		return freno;
	}

	public void setFreno(Freno freno) {
		this.freno = freno;
	}

	public void presionarAcelerador(double cantidad){
		Acelerador acelerador = this.getAcelerador();
		acelerador.presionar(cantidad);
	}

	public void presionarFreno(double cantidad){
		Freno frenoAux = this.getFreno();
		frenoAux.presionar(cantidad);
	}


//RUEDA
	public Rueda getRuedaDelanteraDerecha() {
		return ((Rueda)(ruedas.get(0)));
	}

	public void setRuedaDelanteraDerecha(Rueda rueda) {
		ruedas.set(0,rueda);
	}

	public Rueda getRuedaDelanteraIzquierda() {
		return ((Rueda)(ruedas.get(1)));
	}

	public void setRuedaDelanteraIzquierda(Rueda rueda) {
		ruedas.set(1,rueda);
	}

	public Rueda getRuedaTraseraDerecha() {
		return ((Rueda)(ruedas.get(2)));
	}

	public void setRuedaTraseraDerecha(Rueda rueda) {
		ruedas.set(2,rueda);
	}

	public Rueda getRuedaTraseraIzquierda() {
		return ((Rueda)(ruedas.get(3)));
	}

	public void setRuedaTraseraIzquierda(Rueda rueda) {
		ruedas.set(3,rueda);
	}


//EJE
	public Eje getEje() {
		return eje;
	}

	public void setEje(Eje eje) {
		this.eje = eje;
	}

//PESO
	public double getPeso(){
		//Se actualiza constantemente
		return this.calcularPeso();
	}

	private double calcularPeso(){
		int peso = 0;
		for(PartesAuto p:getPartes())
			peso += p.getPeso();
		return peso;
	}
	
	protected LinkedList<PartesAuto> getPartes(){
		return partes;
	}
	protected void addParte(PartesAuto parte){
		partes.add(parte);
	}

	private void asignadorPedales(){
		//Acelerador
		Acelerador acelerador = new Acelerador(this.getMotor());
		this.setAcelerador(acelerador);

		//Freno
		Freno freno = new Freno(this.getEje(),100);
		this.setFreno(freno);
	}

	private void asignarEje() {
		Eje eje = new Eje(this.getRuedaTraseraDerecha());
		this.setEje(eje);

	}

}
