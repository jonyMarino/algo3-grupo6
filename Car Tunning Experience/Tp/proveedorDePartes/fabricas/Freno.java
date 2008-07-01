package proveedorDePartes.fabricas;
import excepciones.BoundsException;
import excepciones.PartBrokenException;

/**
 * El {@link Pedal} del Freno.
 *
 * @see Pedal
 */
public class Freno extends Pedal implements Torqueador {

	private double torque;
	private double pastillaDeFreno;
	private Eje eje;
	/**
	 * Crea un nuevo pedal Freno con las características especificadas.
	 *
	 * @param pastillaDeFreno La pastilla de freno.
	 *
	 * @see Eje
	 */
	Freno(double pastillaDeFreno) {
		super();
		this.setTorque(0);
		this.setPastillaDeFreno(pastillaDeFreno);
	}
	
	public void setEje(Eje unEje){
		if(eje!=null)
			eje.deleteTorqueador(this);
		eje=unEje;
		if(eje!=null)
			eje.addTorqueador(this);		
	}

	/**
	* Realiza la presion sobre el Freno.
	*
	* @param intensidad La intensidad con que se presiona.
	*
	* @throws BoundsException
	*/
	public void presionar(double intensidad) throws PartBrokenException {
		super.setUsado(false);
		if (this.getVidaUtil() > 0){
			this.setTorque(- (intensidad * this.getPastillaDeFreno()));
		} else
			throw new PartBrokenException("El Freno se rompio");
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
