package auto.partesAuto.pedal;

import auto.partesAuto.BoundsException;
import auto.partesAuto.Eje;
import auto.partesAuto.Torqueador;

/**
 * El {@link Pedal} del Freno.
 *
 * @see Pedal
 */
public class Freno extends Pedal implements Torqueador {

	private double torque;
	private double pastillaDeFreno;

	/**
	 * Crea un nuevo pedal Freno con las características especificadas.
	 *
	 * @param eje El {@link Eje}.
	 * @param pastillaDeFreno La pastilla de freno.
	 *
	 * @see Eje
	 */
	public Freno(Eje eje, double pastillaDeFreno) {
		super();
		this.setTorque(0);
		eje.addTorqueador(this);
		this.setPastillaDeFreno(pastillaDeFreno);
	}

	/**
	* Realiza la presion sobre el Freno.
	*
	* @param intensidad La intensidad con que se presiona.
	*
	* @throws BoundsException
	*/
	public void presionar(double intensidad) {
		super.setUsado(false);
		if (this.getVidaUtil() > 0){
			this.setTorque(- (intensidad * this.getPastillaDeFreno()));
		}
	}

	/**
	 * Devuelve el Torque asociado al Freno.
	 *
	 * @see Torque
	 */
	public double getTorque() {
		return torque;
	}

	private void setTorque(double intensidad) {
		this.torque = intensidad;
	}

	/**
	 * Devuelve la pastilla de freno asociada al Freno.
	 *
	 * @return Pastilla de freno asociada.
	 */
	public double getPastillaDeFreno() {
		return pastillaDeFreno;
	}

	/**
	 * Le asigna una pastilla de freno al Freno.
	 *
	 * @param pastillaDeFreno La pastilla de freno a asignar.
	 */
	public void setPastillaDeFreno(double pastillaDeFreno) {
		this.pastillaDeFreno = pastillaDeFreno;
	}

}
