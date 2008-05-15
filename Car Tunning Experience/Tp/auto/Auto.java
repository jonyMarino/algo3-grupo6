package auto;
import java.util.LinkedList;
import pista.Pista;
import auto.partesAuto.Carroceria;
import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;
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
	private TanqueNafta            tanqueNafta;
	private Acelerador	           acelerador;
	private Freno 		           freno;
	private LinkedList<Rueda>      ruedas;
	private Eje                    eje;
	private MezcladorNafta         mezcladorNafta;
	private double		           velocidad;
	private LinkedList<PartesAuto> partes;
	private static double          aceleracionGravedad = 9.8;

	public Auto(Escape escape, Carroceria carroceria, Motor motor,
	            Caja caja,MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
	            Rueda rueda1, Rueda rueda2, Rueda rueda3, Rueda rueda4){

		this.escape = escape;
		this.setCarroceria(carroceria);
		this.setMotor(motor);
		this.mezcladorNafta = mezcladorNafta;
		this.tanqueNafta = tanqueNafta;

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
	public double getVelocidad(){
		return velocidad;
	}

	public double obtenerRpm(){
		return ruedas.get(0).getRPM();
	}

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
	public Escape getEscape() {
		return escape;
	}

	public void setEscape(Escape escape) {
		this.escape = escape;
		Motor motor = this.getMotor();
		motor.setEscape(this.getEscape());
	}


//CARROCERIA
	public Carroceria getCarroceria() {
		return carroceria;
	}

	public void setCarroceria(Carroceria carroceria) {
		this.carroceria = carroceria;
	}


//MOTOR
	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public double getRPM(){
		Motor motor = this.getMotor();
		double rpm = motor.obtenerRPM();
		return rpm;
	}


//TANQUE
	public TanqueNafta getTanqueNafta() {
		return tanqueNafta;
	}

	public void setTanqueNafta(TanqueNafta tanqueNafta) {
		this.tanqueNafta = tanqueNafta;
		MezcladorNafta mezcladorNafta = this.getMezcladorNafta();
		mezcladorNafta.setTanqueNafta(this.getTanqueNafta());
	}

	public double obtenerCantidadCombustible(){
		TanqueNafta tanqueNafta = this.getTanqueNafta();
		double cantidadCombustible = tanqueNafta.getCantidadCombustible();
		return cantidadCombustible;
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


//MEZCLADOR
	public MezcladorNafta getMezcladorNafta() {
		return mezcladorNafta;
	}

	public void setMezcladorNafta(MezcladorNafta mezcladorNafta) {
		this.mezcladorNafta = mezcladorNafta;
		Motor motor = this.getMotor();
		motor.setMezclador(this.getMezcladorNafta());
	}

	protected LinkedList<PartesAuto> getPartes(){
		return partes;
	}
	protected void addParte(PartesAuto parte){
		partes.add(parte);
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
