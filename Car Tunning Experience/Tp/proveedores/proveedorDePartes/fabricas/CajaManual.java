package proveedores.proveedorDePartes.fabricas;

/**
 * Un caso particular de {@link Caja}. Su principal caracterÝstica es que los cambios deben ser pasados por el usuario.
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

}

