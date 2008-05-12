package auto.partesAuto.caja;

import auto.partesAuto.Eje;

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

	public CajaAutomatica(Eje eje) {
		super(eje);
	}
	
}
