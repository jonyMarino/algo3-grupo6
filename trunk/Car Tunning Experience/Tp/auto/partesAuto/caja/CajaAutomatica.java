package auto.partesAuto.caja;

import auto.partesAuto.Eje;
import auto.partesAuto.motor.*;
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

	public CajaAutomatica(Eje eje,Motor motor) {
		super(eje,motor);
	}
	public double getTorque() {
		if(getMotor().getRPM()>getMotor().getRPMMaximo()*3/4 && cambio<MAX_CAMBIO)
			incCambio();
		return convertir(getMotor().getTorque());
	}
	
	public boolean desgastar(int tiempo){
		return desgastado();
	}
	
}
