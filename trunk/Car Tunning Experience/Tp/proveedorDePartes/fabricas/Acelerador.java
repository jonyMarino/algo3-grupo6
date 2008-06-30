package proveedorDePartes.fabricas;

import excepciones.BoundsException;

/**
 * El {@link Pedal} del Acelerador.
 *
 * @see Pedal
 */
public class Acelerador extends Pedal {

	private	Motor motor;

	/**
	 * Crea un nuevo pedal Acelerador con las características especificadas.
	 *
	 * @param motor El {@link Motor}.
	 *
	 * @see Motor
	 */
	Acelerador(Motor motor) {
		super();
		this.setMotor(motor);
	}

	/**
	* Realiza la presion sobre el Acelerador.
	*
	* @param intensidad La intensidad con que se presiona.
	*
	* @throws BoundsException
	*/
	public void presionar(double intensidad) throws BoundsException {
		super.setUsado(intensidad > 0);
		getMotor().acelerar(intensidad); 
	}

	/**
	 * Devuelve el {@link Motor} asociado al Acelerador.
	 *
	 * @return El {@link Motor} asociado.
	 *
	 * @see Motor
	 */
	public Motor getMotor() {
		return motor;
	}

	/**
	 * Asigna un {@link Motor} al Acelerador.
	 *
	 * @param motor El {@link Motor} a asignar.
	 *
	 * @see Motor
	 */
	public void setMotor(Motor motor) {
		this.motor = motor;
	}

}