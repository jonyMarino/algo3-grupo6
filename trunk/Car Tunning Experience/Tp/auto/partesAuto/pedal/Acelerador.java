package auto.partesAuto.pedal;

import auto.ParteAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Motor;
/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Acelerador extends ParteAuto implements Pedal{

	private	Motor motor;
	private boolean usado;

	public Acelerador(Motor motor){
		super();
		this.setMotor(motor);
		usado = false;
	}

	public void presionar(double cantidad){
		usado = true;
		if(this.getVidaUtil() > 0){
			try {
				getMotor().acelerar(cantidad);
			} catch (BoundsException e) {
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
		if(usado){
			setVidaUtil(getVidaUtil()-tiempo/1000);
			return desgastado();
		}else{
			return usado;
		}
	}

}