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

	private Eje eje;
	private double torque;
	
	public Freno(Eje eje){
		super();
		torque = 0;
		this.setEje(eje);
		eje.addTorqueador(this);
		//TODO: Chekear
	}

	/**
	 * 
	 * Modifica la magnitud del Torque Freno.
	 * 
	 * @see Torque
	 */

	public void presionar(double cantidad){
		if (this.getVidaUtil() > 0){
			this.setTorque(-cantidad);
			//TODO: Chekear
		}
	}

	public Eje getEje() {
		return eje;
	}

	private void setEje(Eje eje) {
		this.eje = eje;
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
