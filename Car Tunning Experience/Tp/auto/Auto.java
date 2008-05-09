package auto;
import auto.parteAuto.tanque.*;
import auto.parteAuto.carroceria.*;
import auto.parteAuto.escape.*;
import auto.parteAuto.mezclador.*;
import auto.parteAuto.pedal.*;
import auto.parteAuto.rueda.*;

public class Auto {
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
	
}

/*********************************************************************************/
private float getVelocidad(){
	//FALTA GENERAR
	return (0);
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

public float getRPM(){
	float rpm =this.motor.getRPM;
	return rpm;
}


//TANQUE
public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

public double getCantidadCombustible(){
	double cantidadCombustible =this.tanqueNafta.getCantidadNafta();
	return cantidadCombustible;
}

public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
}


//CAJA
public Caja getCaja() {
	return caja;
}

public void setCaja(Caja caja) {
	this.caja = caja;
}

public void ponerCambio(float cambio){
	caja.setCambio(cambio);
}

public float getCambio(){
	float cambio =this.caja.getCambio();
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
	this.acelerador.presionar(cantidad);
}

public void presionarFreno(double cantidad){
	this.freno.presionar(cantidad);
}


//RUEDA
public Rueda getRueda() {
	return rueda;
}

public void setRueda(Rueda rueda) {
	this.rueda = rueda;
}


//CALCULADOR DE VELOCIDAD
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
}


//PESO
public double getPeso(){
	//Se actualiza constantemente
	this.peso =this.calculadorPeso(); 
	return peso;
}

/***********************************************************/
//Funciones internas

private void asignadorPedales(){
	//Acelerador
	Acelerador acelerador = new Acelerador(this.getMotor());
	this.setAcelerador(acelerador);
	
	//Freno
	Torque torque = new Torque();
	Freno freno =new Freno(torque);
	this.setFreno(freno);
}

private void asignarEje() {
	Eje eje = new Eje();
	this.setEje(eje);
	
}

private double calculadorPeso(){
	this.peso = 0;
	this.peso += escape.getPeso();
	this.peso += carroceria.getPeso();
	this.peso += motor.getPeso();
	this.peso += tanqueNafta.getPeso();
	this.peso += caja.getPeso();
	this.peso += 4*(rueda.getPeso());
}

/*********************************************************************************/
}
