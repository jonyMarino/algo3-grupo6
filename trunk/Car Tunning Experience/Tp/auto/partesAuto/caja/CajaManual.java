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

	private int cambio;

	public CajaManual(Eje eje,Motor motor) {
		super(eje,motor);
		cambio = 1; //empieza en primera
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

	public boolean desgastar(int tiempo){
		return desgastado();
	}

	public int getCambio() {
		return cambio;
	}

	public void setCambio(int cambio) {
		this.cambio = cambio;
	}

	protected void incCambio(){
		cambio++;
	}

	public double obtenerRpmEntrada(){
		return getEje().getRpm()*getRelaciones()[cambio];
	}

	public double convertir(double torque){
		return torque * getRelaciones()[cambio];
	}

}
