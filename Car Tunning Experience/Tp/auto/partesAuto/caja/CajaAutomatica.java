package auto.partesAuto.caja;
import auto.partesAuto.Eje;
import auto.partesAuto.Motor;

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

	public CajaAutomatica(Eje eje,Motor motor) {
		super();
	}
	public double getTorque() {
		Motor m=getMotor();
		if(m!=null){
			if(getMotor().obtenerRPM()>getMotor().getRPMMaximo()*3/4 && getCambio()<MAX_CAMBIO)
				incCambio();
			return convertir(getMotor().getTorque());
		}
		return 0;
	}

	public boolean desgastar(int tiempo){
		return desgastado();
	}

}
