package proveedores.proveedorDePartes.fabricas;

/**
 * Un caso particular de {@link Caja}.
 * Su principal característica es que no es necesario que el usuario pase los cambios.
 * Los cambios pasan conforme a las necesidades del {@link Motor}.
 *
 * @see Caja
 * @see Motor
 *
 */
public class CajaAutomatica extends Caja {

	private static final int MAX_CAMBIO = 5;

	CajaAutomatica(Eje eje,Motor motor) {
		super();
		super.setCambio(5);
	}
	
	CajaAutomatica() {
		super();
		super.setCambio(5);
	}
	public double getTorque() {
		if(this.getMotor()!=null){
			if(getMotor().obtenerRPM()>getMotor().getRPMMaximo()*3/4 && getCambio()<MAX_CAMBIO)
				incCambio();
			return convertir(getMotor().getTorque());
		}
		return 0;
	}
	
	public void setCambio(int cambio){
		
	}

}
