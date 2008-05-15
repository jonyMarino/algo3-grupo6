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

	//private int cambio;
	private int[] relaciones;
	private Motor motor;
	private Eje eje;

	public Caja(Eje eje, Motor motor){
		this.eje=eje;
		eje.addTorqueador(this);
		this.motor=motor;
		//cambio = 1; //empieza en primera
		relaciones = new int[6];
		for(int i=0;i<6;i++){
			relaciones[i]=30-i*5;
		}
	}

	protected abstract double convertir(double torque);

	public abstract double getTorque();

	public abstract double obtenerRpmEntrada();


	//TODO: Modifico para evitar que sea protected
	/*
	protected double convertir(double torque){
		return torque * relaciones[cambio];
	}
	*/

	/*
	protected void setCambio(int cambio) {
		this.cambio=cambio;
	}
	*/

	/*
	public int getCambio() {
		return cambio;
	}

	protected void incCambio(){
		cambio++;
	}
	*/

	public Eje getEje() {
		return eje;
	}

	public void setEje(Eje eje) {
		this.eje = eje;
	}

	public Motor getMotor(){
		return motor;
	}

	public void setMotor(Motor motor){
		this.motor = motor;
	}

	protected int[] getRelaciones() {
		return relaciones;
	}

	protected void setRelaciones(int[] relaciones) {
		this.relaciones = relaciones;
	}

}