package auto.partesAuto.caja;

import auto.PartesAuto;
import auto.partesAuto.*;
import auto.partesAuto.Eje;
import auto.partesAuto.Motor;

/**
 * La Caja transforma (se podría decir que lo "amplifica") el torque que genera el {@link Motor}.
 * La transformación del torque depende del cambio.
 * Caja es una clase abstracta que sirve como base para distintos tipos de Caja.
 * @see PartesAuto
 * @see Motor
 * @see CajaManual
 * @see CajaAutomatica
 *
 */
public abstract class Caja extends PartesAuto implements Torqueador{

	private int cambio;
	private int[] relaciones;
	private Motor motor;
	private Eje eje;

	public Caja(){
		cambio=1; //empieza en primera
		relaciones = new int[6];
		for(int i=0;i<6;i++){
			relaciones[i]=30-i*5;
		}
	}

	protected double convertir(double torque){
		return torque * relaciones[cambio];
	}
	public abstract double getTorque();

	protected void setCambio(int cambio) {
		this.cambio=cambio;
	}

	public void setMotor(Motor m ){
		motor=m;
	}
	public Motor getMotor(){
		return motor;
	}

	public void setEje(Eje e ){
		if(eje!=null)
			return;
		eje=e;
		eje.addTorqueador(this);
	}
	public Eje getEje(){
		return eje;
	}

	public int getCambio() {
		return cambio;
	}

	protected void incCambio(){
		cambio++;
	}

	public double obtenerRpmEntrada(){
		if(eje==null)
			return 0;
		return eje.getRpm()*relaciones[cambio];
	}

}