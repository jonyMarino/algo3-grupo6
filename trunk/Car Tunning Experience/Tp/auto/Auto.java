package auto;

import java.util.LinkedList;
import pista.Pista;
import auto.partesAuto.Carroceria;
import auto.ParteAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;
import auto.partesAuto.caja.Caja;

//TODO: Se modifico codigo y se comento el codigo
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
 *   @see TanqueNafta
 *   @see Acelerador
 *   @see Freno
 *   @see Rueda
 *   @see Eje
 *   @see MezcladorNafta
 */
public abstract class Auto {
	private Escape 		           escape;
	private Carroceria 	           carroceria;
	private Motor 		           motor;
	private Acelerador	           acelerador;
	private Freno 		           freno;
	private LinkedList<Rueda>      ruedas;
	private Eje                    eje;
	private TanqueNafta            tanqueNafta;
	private MezcladorNafta         mezcladorNafta;
	private double		           velocidad;
	private LinkedList<ParteAuto>  partes;
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
	            Caja caja,MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
	            Rueda rueda1, Rueda rueda2, Rueda rueda3, Rueda rueda4){

		this.escape = escape;
		this.setCarroceria(carroceria);
		this.setMotor(motor);
		this.setMezcladorNafta(mezcladorNafta);
		this.setTanqueNafta(tanqueNafta);

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

		partes = new LinkedList<ParteAuto>();
		partes.add(escape);
		partes.add(carroceria);
		partes.add(motor);
		partes.add(mezcladorNafta);
		partes.add(tanqueNafta);
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
		double masaAuto = this.getPeso()*aceleracionGravedad;
		double fuerzaEje = this.getEje().getFuerza();
		double fuerzaAire = this.getCarroceria().getFuerzaAire(pista);
		double incrementoVelocidad = (((fuerzaEje-fuerzaAire)/masaAuto)*segundosTranscurridos);
		this.setVelocidad(this.getVelocidad()+incrementoVelocidad);
		for(ParteAuto partesAuto:this.getPartes())
			partesAuto.desgastar(segundosTranscurridos);
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
	 * Le asigna un {@link Escape} al Auto.
	 *
	 * @param escape el {@link Escape} a asignar.
	 *
	 * @see Escape
	 */
	public void setEscape(Escape escape) {
		this.escape = escape;
		this.getMotor().setEscape(this.getEscape());
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
		double rpm = this.getMotor().obtenerRPM();
		return rpm;
	}

//PEDALES

	/**
	 * Devuelve el {@link Acelerador} asociado al Auto
	 *
	 * @return El {@link Acelerador} asociado.
	 *
	 * @see Acelerador
	 */
	public Acelerador getAcelerador() {
		return acelerador;
	}

	/**
	 * Asigna un {@link Acelerador} al Auto.
	 *
	 * @param acelerador El {@link Acelerador} a asignar.
	 *
	 * @see Acelerador
	 */
	public void setAcelerador(Acelerador acelerador) {
		this.acelerador = acelerador;
	}

	/**
	 * Devuelve el {@link Freno} asociado al Auto
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
	 * @see Freno
	 */
	public void setFreno(Freno freno) {
		this.freno = freno;
	}

	/**
	 * Pisa el Pedal del Acelerador
	 *
	 * @param intensidad Intensidad es grado de fuerza que se le aplica al Acelerador
	 *
	 * @see Acelerador
	 */
	public void presionarAcelerador(double intensidad)throws BoundsException{
		this.getAcelerador().presionar(intensidad);
	}

	/**
	 * Pisa el Pedal del Freno
	 *
	 * @param intensidad Intensidad es grado de fuerza que se le aplica al Freno
	 *
	 * @see Freno
	 */
	public void presionarFreno(double intensidad){
		this.getFreno().presionar(intensidad);
	}

//RUEDA

	/**
	 * Devuelve la {@link Rueda} asociado al Auto
	 *
	 * @return La {@link Rueda} delatera derecha asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaDelanteraDerecha() {
		return ((Rueda)(ruedas.get(0)));
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda delantera derecha La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaDelanteraDerecha(Rueda rueda) {
		ruedas.set(0,rueda);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto
	 *
	 * @return La {@link Rueda} delatera izquierda asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaDelanteraIzquierda() {
		return ((Rueda)(ruedas.get(1)));
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda delantera izquierda La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaDelanteraIzquierda(Rueda rueda) {
		ruedas.set(1,rueda);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto
	 *
	 * @return La {@link Rueda} trasera derecha asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaTraseraDerecha() {
		return ((Rueda)(ruedas.get(2)));
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda trasera derecha La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaTraseraDerecha(Rueda rueda) {
		ruedas.set(2,rueda);
	}

	/**
	 * Devuelve la {@link Rueda} asociado al Auto
	 *
	 * @return La {@link Rueda} trasera izquierda asociado.
	 *
	 * @see Rueda
	 */
	public Rueda getRuedaTraseraIzquierda() {
		return ((Rueda)(ruedas.get(3)));
	}

	/**
	 * Asigna una {@link Rueda} al Auto.
	 *
	 * @param rueda trasera izquierda La {@link Rueda} a asignar.
	 *
	 * @see Rueda
	 */
	public void setRuedaTraseraIzquierda(Rueda rueda) {
		ruedas.set(3,rueda);
	}

//TANQUE NAFTA

	/**
	 * Devuelve el {@link TanqueNafta} asociado al Auto
	 *
	 * @return El {@link TanqueNafta} asociado.
	 *
	 * @see TanqueNafta
	 */
	public TanqueNafta getTanqueNafta() {
		return tanqueNafta;
	}

	/**
	 * Asigna un {@link TanqueNafta} al Auto.
	 *
	 * @param El {@link TanqueNafta} a asignar.
	 *
	 * @see TanqueNafta
	 */
	public void setTanqueNafta(TanqueNafta tanqueNafta) {
		this.tanqueNafta = tanqueNafta;
		this.getMezcladorNafta().setTanqueNafta(this.getTanqueNafta());
	}

	/**
	 * Devuelve la cantidad de Nafta que posee el Auto
	 *
	 * @return La cantidad de Nafta asociada.
	 *
	 * @see TanqueNafta
	 */
	public double obtenerCantidadNafta(){
		return this.getTanqueNafta().getCantidadCombustible();
	}

	/**
	 * Carga el {@link TanqueNafta} de Combustible.
	 *
	 * @param El combustible a asignar.
	 *
	 * @see TanqueNafta
	 */
	public void cargarCombustible(double litros){
		try {
			this.getTanqueNafta().llenarTanque(litros);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
	}

//MEZCLADOR NAFTA

	/**
	 * Devuelve el {@link MezcladorNafta} asociado al Auto
	 *
	 * @return El {@link MezcladorNafta} asociado.
	 *
	 * @see MezcladorNafta
	 */
	public MezcladorNafta getMezcladorNafta() {
		return mezcladorNafta;
	}

	/**
	 * Asigna un {@link MezcladorNafta} al Auto.
	 *
	 * @param El {@link MezcladorNafta} a asignar.
	 *
	 * @see MezcladorNafta
	 */
	public void setMezcladorNafta(MezcladorNafta mezcladorNafta) {
		this.mezcladorNafta = mezcladorNafta;
		this.getMotor().setMezclador(this.getMezcladorNafta());
	}

//EJE

	/**
	 * Devuelve el {@link Eje} asociado al Auto
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
	}

//PESO

	/**
	 * Devuelve el peso actual del Auto
	 *
	 * @return El peso actual del Auto.
	 */
	public double getPeso(){
		//Se actualiza constantemente
		return this.calcularPeso();
	}

	private double calcularPeso(){
		int peso = 0;
		for(ParteAuto p:getPartes())
			peso += p.getPeso();
		return peso;
	}

	protected LinkedList<ParteAuto> getPartes(){
		return partes;
	}
	protected void addParte(ParteAuto parte){
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
