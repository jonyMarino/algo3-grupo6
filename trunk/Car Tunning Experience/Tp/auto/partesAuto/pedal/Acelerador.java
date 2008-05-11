package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.motor.Motor;
/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Acelerador extends PartesAuto implements Pedal{
	
	private	Motor motor;

public Acelerador(Motor motor){
	super();
	this.setMotor(motor);
	
}

/*********************************************************************************/
public void presionar(double cantidad){
	Motor motorAux = this.getMotor();
	motorAux.acelerar(cantidad);
}
	
public Motor getMotor() {
	return motor;
}

public void setMotor(Motor motor) {
	this.motor = motor;
}

/*********************************************************************************/
}