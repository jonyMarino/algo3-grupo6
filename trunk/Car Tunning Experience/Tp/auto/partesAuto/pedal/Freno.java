package auto.partesAuto.pedal;

import auto.ParteAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torqueador;

/**
 * El {@link Pedal} del freno.
 *@see Pedal
 *@see PartesAuto
 */
public class Freno extends ParteAuto implements Pedal,Torqueador{

	private double torque;
	private boolean usado;
	private double pastilladeFreno;

	public Freno(Eje eje, double pastillaDeFreno){
		super();
		torque = 0;
		usado = false;
		eje.addTorqueador(this);
		this.setPastilladeFreno(pastilladeFreno);
	}

	/**
	 *
	 * Modifica la magnitud del Torque Freno.
	 *
	 * @see Torque
	 */
	public void presionar(double cantidad){
		usado = true;
		if (this.getVidaUtil() > 0){
			this.setTorque(-(cantidad*pastilladeFreno));
		}
	}

	public boolean desgastar(int tiempo) {
		if(usado){
			setVidaUtil(getVidaUtil()-tiempo/1000);
			return desgastado();
		}else{
			return usado;
		}
	}

	public double getTorque() {
		return torque;
	}

	public void setTorque(double cantidad) {
		this.torque = cantidad;
	}

	public double getPastilladeFreno() {
		return pastilladeFreno;
	}

	public void setPastilladeFreno(double pastilladeFreno) {
		this.pastilladeFreno = pastilladeFreno;
	}

}
