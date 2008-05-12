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
	
	public Caja(Eje eje, Motor motor){
		this.motor=motor;
		eje.obtenerUnTorque().setMethod(getTorque);
		
		cambio=1; //empieza en primera
	} 
	 
	protected abstract float convertir(double torque);
	
	private void getTorque() {
		convertir(motor.getTorque());
	}
	/* // deMarino: si es Automatico no se puede hacer
	public void setCambio(int cambio) {
		
	}
	*/
	public Motor getMotor(){
		return motor;
	}
	
	public int getCambio() {
		return cambio;
	}
	 
	public abstract float obtenerRpsEntrada();
	 
}