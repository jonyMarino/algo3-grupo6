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

	public void presionar(double intensidad)throws BoundsException{
		usado = intensidad>0;	//deMarino: esta siendo usado se la aceleracion no es 0
		//if(this.getVidaUtil() > 0){ //deMarino: en caso de ser cero tiene su funcion
		getMotor().acelerar(intensidad); //deMarino: el caso de excepcion es el mismo
		//}
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
		try{
			if(usado && getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/1000);
			}
		}
		catch(BoundsException e){	//me pase??
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}
		return desgastado();	//deMarino:siempre devuelve si esta desgastado, por lo que deberia estar en una abstract class!!!!
	}

}