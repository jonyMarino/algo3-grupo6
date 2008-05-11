package auto.partesAuto.caja;

import auto.partesAuto.motor.Motor;



/**
 * Un caso particular de {@link Caja}. Su principal característica es que los cambios deben ser pasados por el usuario.
 *
 *@see Caja
 *
 */
public class CajaManual extends Caja {

	public CajaManual() {
		super();
	}

	public void setCambio(int cambio) {
		Motor motor = getMotor();
		if(cambio == getCambio()+1 && cambio <= 5){  //sube un cambio
			if(motor.necesitaCambio()){
				motor.nuevoCambio();
			}
			else motor.detenerse();
		}
		else if(cambio < getCambio() && cambio >= 0){  //baja uno o mas cambios
			motor.nuevoCambio();
		}
		else motor.detenerse(); //si suba mas de un cambio se detiene
	}
	
}
