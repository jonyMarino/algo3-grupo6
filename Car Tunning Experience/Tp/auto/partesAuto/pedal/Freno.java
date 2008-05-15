package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torqueador;

/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Freno extends PartesAuto implements Pedal,Torqueador{

	private double torque;

	public Freno(Eje eje){
		super();
		torque = 0;
		eje.addTorqueador(this);
	}

	/**
	 *
	 * Modifica la magnitud del Torque Freno.
	 *
	 * @see Torque
	 */

	//TODO: Chekear
	public void presionar(double cantidad){
		if (this.getVidaUtil() > 0){
			this.setTorque(-cantidad);
		}
	}

	public boolean desgastar(int tiempo) {
		setVidaUtil(getVidaUtil()-tiempo/1000);
		return desgastado();
	}

	public double getTorque() {
		return torque;
	}

	public void setTorque(double cantidad) {
		this.torque = cantidad;
	}

}
