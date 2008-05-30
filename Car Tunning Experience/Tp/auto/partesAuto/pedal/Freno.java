package auto.partesAuto.pedal;

import auto.ParteAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torqueador;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.BoundsException;
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
	 * Modifica la magnitud del Torque Freno.
	 *
	 * @see Torque
	 */
	public void presionar(double intensidad){
		usado = true;
		if (this.getVidaUtil() > 0){
			this.setTorque(-(intensidad*pastilladeFreno));
		}
	}

	public boolean desgastar(int tiempo) {
		try{
			if(usado && getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/1000);
			}
		}
		catch(BoundsException e){	//me pase??
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}
		return desgastado();
	}

	/**
	 * Devuelve el Torque asociado al Freno
	 */
	public double getTorque() {
		return torque;
	}

	/**
	 * Asigna un Torque al Freno.
	 *
	 * @param El Torque a asignar.
	 *
	 * @see MezcladorNafta
	 */
	public void setTorque(double intensidad) {
		this.torque = intensidad;
	}

	/**
	 * Devuelve la pastilla de freno asociada al Freno
	 *
	 * @return Pastilla de freno asociada.
	 */
	public double getPastilladeFreno() {
		return pastilladeFreno;
	}

	/**
	 * Le asigna una pastilla de freno al Freno.
	 *
	 * @param pastilla de Freno a asignar.
	 */
	public void setPastilladeFreno(double pastilladeFreno) {
		this.pastilladeFreno = pastilladeFreno;
	}

}
