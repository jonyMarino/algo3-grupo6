package auto.partesAuto.caja;

import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torque;
import auto.partesAuto.motor.Motor;

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
public abstract class Caja extends PartesAuto {
 
	private int cambio;	 
	private Motor motor; 
	private Eje eje;
	 
	
	public Caja(Eje eje){
		super();
		this.setEje(eje);
		
	}
	
	public void convertir(double fuerza) {
		Eje eje = this.getEje();
		eje.setTorqueCaja(fuerza);
	}
	
	public void setCambio(int cambio) {
		this.cambio=cambio;
	}
	
	public int getCambio() {
		return cambio;
	}
	
	public Motor getMotor(){
		return motor;
	}
	
	public float obtenerRpsEntrada() {
		return 0;
	}

	public Eje getEje() {
		return eje;
	}

	public void setEje(Eje eje) {
		this.eje = eje;
	}
	 
}