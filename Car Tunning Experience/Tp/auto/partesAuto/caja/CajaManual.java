package auto.partesAuto.caja;
import auto.partesAuto.Motor;

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

	public double getTorque(){
	if(this.getMotor()!= null)
		return convertir(this.getMotor().getTorque());
	return 0;
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

