package auto.pedal;

public class Acelerador extends Pedal {
	
	private	Motor motor;

public void presionar(double cantidad){
	this.motor.acelerar(cantidad);
}

/*********************************************************************************/
public Acelerador(Motor motor){
	
	this.motor= motor;
}

/*********************************************************************************/
}