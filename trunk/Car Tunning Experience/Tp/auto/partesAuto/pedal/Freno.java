package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.Eje;

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
			//eje.setTorqueFreno(cantidad);  //TODO: ¿set torque freno o set torque con un parametro negativo?
										   //TODO: de todas formas, no existe ninguno de los dos
		}
	}

	public Eje getEje() {
		return eje;
	}

	private void setEje(Eje eje) {
		this.eje = eje;
	}

	@Override
	public boolean desgastar(int tiempo) {
		// TODO Auto-generated method stub
		return false;
	}

}
