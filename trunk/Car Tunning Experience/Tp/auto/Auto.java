package auto;
import java.util.LinkedList;

import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.motor.Motor;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;
import pista.Pista;



/**
 * Auto es una clase que intenta encapsular el comportamiento y las caracter�sticas de un auto.
 * Cada Auto est� formado por distintas {@link PartesAuto}, como ser el {@link Motor}, la {@link Caja}, el {@link Eje} o
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
	private LinkedList<Rueda> 	   ruedas;
	private Eje            eje;
	private Mezclador      mezclador;
	private double		   velocidad;
	private static double  gravedad = 9.8;
	//private LinkedList<ParteAuto> partes;

public Auto(Escape escape, Carroceria carroceria, Motor motor,
            Caja caja, Mezclador mezclador, TanqueNafta tanqueNafta,
            Rueda rueda1, Rueda rueda2, Rueda rueda3, Rueda rueda4){

	this.escape = escape;
	this.setCarroceria(carroceria);
	this.setMotor(motor);
	this.setCaja(caja);
	this.mezclador = mezclador;
	this.tanqueNafta = tanqueNafta;

	//Ruedas
	ruedas = new LinkedList<Rueda>();
	ruedas.add(rueda1);
	ruedas.add(rueda2);
	ruedas.add(rueda3);
	ruedas.add(rueda4);
	
	//Velocidad
	this.setVelocidad(0);
		
	//Asignar Eje
	this.asignarEje(this.getFreno());
	
	//Asignar Pedales
	this.asignadorPedales();
		
	//Calculador Peso
	this.calcularPeso();
	
}

/*********************************************************************************/

//VELOCIDAD
public double getVelocidad(){
	return velocidad;
}

private void setVelocidad(double velocidad){
	this.velocidad = velocidad;
}

public double obtenerRpm(){
	return ruedas.get(0).getRPM();
}

public void calcularVelocidad(int segundosTranscurridos,Pista pista){
	Eje eje = this.getEje();
	Carroceria carroceria = this.getCarroceria();
	double masaAuto = this.getPeso()*gravedad;	//deMarino: Multiplica la gravedad
	double fuerzaEje = eje.getFuerza();
	double fuerzaAire = carroceria.getFuerzaAire(pista);
	double incrementoVelocidad = (((fuerzaEje-fuerzaAire)/masaAuto)*segundosTranscurridos);
		
	this.setVelocidad(this.getVelocidad()+incrementoVelocidad);
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
	double cantidadCombustible = tanqueNaftaAux.getCantidadNafta();
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
	this.setPeso(this.calcularPeso()); 
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
	Freno freno = new Freno(this.getEje());
	this.setFreno(freno);
}

private void asignarEje(Freno freno) {
	Eje eje = new Eje(this.getRuedaDelanteraDerecha());
	this.setEje(eje);
	
}

private double calcularPeso(){
	this.peso = 0;
	this.peso += this.escape.getPeso();
	this.peso += this.carroceria.getPeso();
	this.peso += this.motor.getPeso();
	this.peso += this.tanqueNafta.getPeso();
	this.peso += this.caja.getPeso();
	Rueda rueda1 = this.getRuedaDelanteraDerecha();
	this.peso += rueda1.getPeso();
	Rueda rueda2 = this.getRuedaDelanteraIzquierda();
	this.peso += rueda2.getPeso();
	Rueda rueda3 = this.getRuedaTraseraDerecha();
	this.peso += rueda3.getPeso();
	Rueda rueda4 = this.getRuedaTraseraIzquierda();
	this.peso += rueda4.getPeso();
	return (this.getPeso());
}
	
/*********************************************************************************/
}

