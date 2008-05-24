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
//TODO: Se agrego comentarios
	private	Motor motor;
	private boolean usado;

	public Acelerador(Motor motor){
		super();
		this.setMotor(motor);
		usado = false;
	}

	public void presionar(double intensidad){
		usado = true;
		if(this.getVidaUtil() > 0){
			try {
				getMotor().acelerar(intensidad);
			} catch (BoundsException e) {
				e.printStackTrace();
			}
		}
	}

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

	public boolean desgastar(int tiempo) {
		if(usado){
			setVidaUtil(getVidaUtil()-tiempo/1000);
			return desgastado();
		}else{
			return usado;
		}
	}

}