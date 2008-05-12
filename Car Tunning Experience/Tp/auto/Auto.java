package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Rueda;
import auto.partesAuto.Torque;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.motor.Motor;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;
import pista.Pista;



/**
 * Auto es una clase que intenta encapsular el comportamiento y las características de un auto.
 * Cada Auto está formado por distintas {@link PartesAuto}, como ser el {@link Motor}, la {@link Caja}, el {@link Eje} o
 * la {@link Carroceria}, entre otras.
 * 
 *   @see PartesAuto
 *   @see Motor
 *   @see Carroceria
 *   @see Escape
 *   @see Tanque
 *   @see Caja
 *   @see Acelerador
 *   @see Freno
 *   @see Rueda
 *   @see Eje
 *   @see Mezclador
 */
public abstract class Auto {
	private double		   peso;
	private Escape 		   escape;
	private Carroceria 	   carroceria;
	private Motor 		   motor;
	private TanqueNafta	   tanqueNafta;
	private Caja 		   caja;
	private Acelerador	   acelerador;
	private Freno 		   freno;
	private Rueda 	 	   rueda;
	private Eje            eje;
	private Mezclador      mezclador;
	private double		   velocidad;

public Auto(Escape escape, Carroceria carroceria, Motor motor,
            Caja caja, Rueda rueda, Mezclador mezclador, TanqueNafta tanqueNafta){

	this.escape = escape;
	this.setCarroceria(carroceria);
	this.setMotor(motor);
	this.setCaja(caja);
	this.setRueda(rueda);
	this.mezclador = mezclador;
	this.tanqueNafta = tanqueNafta;

	//Asignar Eje
	this.asignarEje();
	
	//Asignar Pedales
	this.asignadorPedales();
	
	//Calculador Peso
	this.calculadorPeso();
	
	//Velocidad
	this.setVelocidad(0);
	
}

/*********************************************************************************/

//VELOCIDAD
public double obtenerVelocidad(){
	return velocidad;
}

/* Quizas podriamos pones acalcularVelocidad como un metodo privado;
 * entonces hacemos 
 * obtenerVelocidad(Tiempo){
 *  	this.calcularVelocidad(segundos);
 *  	return velocidad;
 * }
 * 
 */

public void calcularVelocidad(int segundosTranscurridos,Pista pista){
	Eje ejeAux = this.getEje();
	double fuerzas = ejeAux.getFuerza();
	Carroceria carroceriaAux = this.getCarroceria();
	double fuerzaAire = carroceriaAux.getFuerzaAire(pista);
	double incrementoVelocidad = 0;
	incrementoVelocidad = (((fuerzas-fuerzaAire)/this.getPeso())*segundosTranscurridos);
	double velocidadActual = this.obtenerVelocidad() + incrementoVelocidad;
	this.setVelocidad(velocidadActual);
}

public double obtenerRps(){
	Caja cajaAux = this.getCaja();
	return (cajaAux.obtenerRpsEntrada());
}

public double obtenerRpm(){
	Motor motorAux = this.getMotor();
	return (motorAux.getRPM());
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
	Motor motorAux = this.getMotor();
	motorAux.setEscape(this.getEscape());
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
	Motor motorAux = this.getMotor();
	double rpm = motorAux.getRPM();
	return rpm;
}


//TANQUE
public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
	Mezclador mezcladorAux = this.getMezclador();
	mezcladorAux.setTanqueNafta(this.getTanqueNafta());
}

public double obtenerCantidadCombustible(){
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	double cantidadCombustible = tanqueNaftaAux.obtenerCantidadNafta();
	return cantidadCombustible;
}


//CAJA
public Caja getCaja() {
	return caja;
}

public void setCaja(Caja caja) {
	this.caja = caja;
	Motor motorAux = this.getMotor();
	motorAux.setCaja(this.getCaja());
}

public float getCambio(){
	Caja cajaAux = this.getCaja();
	float cambio = cajaAux.getCambio();
	return cambio;
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
	Acelerador aceleradorAux = this.getAcelerador();
	aceleradorAux.presionar(cantidad);
}

public void presionarFreno(double cantidad){
	Freno frenoAux = this.getFreno();
	frenoAux.presionar(cantidad);
}


//RUEDA
public Rueda getRueda() {
	return rueda;
}

public void setRueda(Rueda rueda) {
	this.rueda = rueda;
}


//EJE
public Eje getEje() {
	return eje;
}

public void setEje(Eje eje) {
	this.eje = eje;
}


//MEZCLADOR
public Mezclador getMezclador() {
	return mezclador;
}

public void setMezclador(Mezclador mezclador) {
	this.mezclador = mezclador;
	Motor motorAux = this.getMotor();
	motorAux.setMezclador(this.getMezclador());
}


//PESO
public double getPeso(){
	//Se actualiza constantemente
	this.setPeso(this.calculadorPeso()); 
	return peso;
}

private void setPeso(double peso){
	this.peso = peso;
}

/***********************************************************/
//Funciones internas

private void asignadorPedales(){
	//Acelerador
	Acelerador acelerador = new Acelerador(this.getMotor());
	this.setAcelerador(acelerador);
	
	//Freno
	Torque torque = new Torque(0);
	Freno freno = new Freno(torque);
	this.setFreno(freno);
}

private void asignarEje() {
	Eje eje = new Eje();
	this.setEje(eje);
	
}

private double calculadorPeso(){
	this.peso = 0;
	Escape escapeAux = this.getEscape();
	this.peso += escapeAux.getPeso();
	Carroceria carroceriaAux = this.getCarroceria();
	this.peso += carroceriaAux.getPeso();
	Motor motorAux = this.getMotor();
	this.peso += motorAux.getPeso();
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	this.peso += tanqueNaftaAux.getPeso();
	Caja cajaAux = this.getCaja();
	this.peso += cajaAux.getPeso();
	Rueda ruedaAux = this.getRueda();
	this.peso += 4*(ruedaAux.getPeso());
	return (this.getPeso());
}

/*********************************************************************************/
}

