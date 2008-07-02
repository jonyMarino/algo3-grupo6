package proveedorDePartes.fabricas;

import excepciones.BoundsException;

/**
 * 
 * El {@link Pedal} del Acelerador.
 *
 * @see Pedal
 * 
 */

public class Acelerador extends Pedal {

	private	Motor motor;

	Acelerador(Motor motor) {
		super();
		this.setMotor(motor);
	}

	public void presionar(double intensidad) {
		try {
			if (intensidad > 0)
				getMotor().acelerar(intensidad);
		} catch (BoundsException e) {
			e.printStackTrace();
		} 
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

}