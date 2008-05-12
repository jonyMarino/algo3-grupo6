package auto.partesAuto.pedal;
import auto.PartesAuto;
import auto.partesAuto.Torque;

/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Freno extends PartesAuto implements Pedal {

	private Torque torqueFreno;

	public Freno(){
		super();
		Torque torqueFreno = new Torque(0);
		this.setTorqueFreno(torqueFreno); 
	}

	/**
	 * 
	 * Modifica la magnitud del Torque Freno.
	 * 
	 * @see Torque
	 */

	public void presionar(double cantidad){
		if (this.getVidaUtil() > 0){
		Torque torqueAux = this.getTorqueFreno();
		torqueAux.setMagnitud(cantidad);
		}
	}

	/**
	*
	*Devuelve la {@link Torque} asociada al Freno.
	*
	*@see Torque
	*/
	public Torque getTorqueFreno() {
		return torqueFreno;
	}

	private void setTorqueFreno(Torque torqueFreno){
		this.torqueFreno = torqueFreno;
	}	

}
