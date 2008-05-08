package auto;
import auto.Tanque.TanqueNafta;
import auto.mezclador.Mezclador;
import auto.pedal.Acelerador;
import auto.pedal.Freno;


public class Auto {
	private double			      peso;
	private Escape 		          escape;
	private Carroceria 	          carroceria;
	private Motor 		          motor;
	private TanqueNafta	 		  tanqueNafta;
	private Caja 		 		  caja;
	private Acelerador		      acelerador;
	private Freno 		 		  freno;
	private Rueda 	 	 		  rueda;
	private CalculadorDeVelocidad calculador;
	private Mezclador             mezclador;

public Auto(Escape escape, Carroceria carroceria, Motor motor,
            Caja caja, Rueda rueda){

	this.setEscape(escape);
	this.setCarroceria(carroceria);
	this.setMotor(motor);
	this.setCaja(caja);
	this.setRueda(rueda);

	//Asignar Calculador de Velocidad
	this.asignadorCalculoDeVelocidad();
		
	//Asignar Pedales
	this.asignadorPedales();
	
	//Asignar Tanque
	this.asignadorTanqueNafta();
	
	//Calculador Peso
	this.calculadorPeso();
	
}
/*********************************************************************************/
private float getVelocidad(){
	float velocidad =this.calculador.getVelocidad();
	return velocidad;
}


//ESCAPE
public Escape getEscape() {
	return escape;
}

public void setEscape(Escape escape) {
	this.escape = escape;
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

public Freno getFreno() {
	return freno;
}

public void presionarAcelerador(double cantidad){
	this.acelerador.presionar(cantidad);
}

public void presionarFreno(double cantidad){
	this.freno.presionar(cantidad);
}


//Rueda
public Rueda getRueda() {
	return rueda;
}

public void setRueda(Rueda rueda) {
	this.rueda = rueda;
}


//CALCULADOR DE VELOCIDAD
public CalculadorDeVelocidad getCalculador() {
	return calculador;
}

public void setCalculador(CalculadorDeVelocidad calculador) {
	this.calculador = calculador;
}


//MEZCLADOR
public Mezclador getMezclador() {
	return mezclador;
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
	Acelerador acelerador =new Acelerador(this.motor);
	this.acelerador	=acelerador;
	
	//Freno
	PuntoDeAplicacionDeTorque puntoDeTorque =new PuntoDeAplicacionDeTorque();
	Freno freno =new Freno(puntoDeTorque);
	this.freno  =freno;
}

private void asignadorTanqueNafta(){
	TanqueNafta tanqueNafta =new TanqueNafta();
	this.tanqueNafta= tanqueNafta;
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

private void asignadorCalculoDeVelocidad(){
	CalculadorDeVelocidad calculador =new CalculadorDeVelocidad(); 
	this.calculador =calculador;
}

/*********************************************************************************/
}
