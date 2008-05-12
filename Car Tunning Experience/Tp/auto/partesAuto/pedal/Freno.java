package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torque;

/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Freno extends PartesAuto implements Pedal {

	private Eje eje;
	
	public Freno(Eje eje){
		super();
		this.setEje(eje);
	}

	/**
	 * 
	 * Modifica la magnitud del Torque Freno.
	 * 
	 * @see Torque
	 */

	public void presionar(double cantidad){
		if (this.getVidaUtil() > 0){
			Eje eje = this.getEje();
			eje.setTorqueFreno(cantidad);
		}
	}

	public Eje getEje() {
		return eje;
	}

	private void setEje(Eje eje) {
		this.eje = eje;
	}

}
