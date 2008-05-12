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

/**
*
* Intenta pasar al cambio especificado, preguntandole al {@link Motor} si posee
* las revoluciones suficientes. Si no pose las revoluciones suficientes el {@link Motor}
* se detiene.
*
* @param cambio El cambio a poner
*
*@see Motor
*/
//TODO: Creo que la Caja sabe cosas que no deberìa saber. Por ejemplo, ¿como sabe que si las
// revoluciones no son suficientes, el Motor se detiene? Habría que replantear el código
// y hacerlo menos dependiente de Motor. Escucho ofertas.
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
		else if (cambio == getCambio()){}
		else motor.detenerse(); //si intenta subir mas de un cambio se detiene
	}
	
}
