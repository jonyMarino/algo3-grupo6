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
		 
	private Torque torque;
	 
	private Eje eje;
	 
	public float convertir(double fuerza) {
		return 0;
	}
	
	public void setCambio(int cambio) {
		
	}
	
	public Motor getMotor(){
		return motor;
	}
	
	public int getCambio() {
		return cambio;
	}
	 
	public float obtenerRpsEntrada() {
		return 0;
	}
	 
}