package auto.partesAuto.caja;

import auto.partesAuto.Eje;
import auto.partesAuto.Motor;


/**
 * Un caso particular de {@link Caja}. Su principal característica es que los cambios deben ser pasados por el usuario.
 *
 *@see Caja
 *
 */
public class CajaManual extends Caja {

	public CajaManual(Eje eje,Motor motor) {
		super(eje,motor);
	}

	public double getTorque(){
			return convertir(getMotor().getTorque());
		}

/**
*
* Pasa al cambio especificado.
*
* @param cambio El cambio a poner
*
*@see Motor
*/
	public void setCambioManual(int cambio) {
		this.setCambio(cambio);
	}

	public boolean desgastar(int tiempo){
		return desgastado();
	}

}

