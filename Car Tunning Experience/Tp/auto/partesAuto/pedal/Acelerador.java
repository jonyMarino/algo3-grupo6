package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Motor;
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
	if(this.getVidaUtil() > 0){
		try {
			getMotor().acelerar(cantidad);
		} catch (BoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public Motor getMotor() {
	return motor;
}

public void setMotor(Motor motor) {
	this.motor = motor;
}

public boolean desgastar(int tiempo) {
	setVidaUtil(getVidaUtil()-tiempo/1000);
	return desgastado();
}

/*********************************************************************************/
}