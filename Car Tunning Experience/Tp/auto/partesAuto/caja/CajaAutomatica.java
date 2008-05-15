package auto.partesAuto.caja;

import auto.partesAuto.Eje;
import auto.partesAuto.Motor;
/**
 * Un caso particular de {@link Caja}.
 * Su principal caracter�stica es que no es necesario que el usuario pase los cambios.
 * Los cambios pasan conforme a las necesidades del {@link Motor}.
 *
 * @see Caja
 * @see Motor
 *
 */
public class CajaAutomatica extends Caja {

	private static final int MAX_CAMBIO = 5; //TODO: fix r�pido para getTorque, no s� cual era la idea

		private int cambio;

	public CajaAutomatica(Eje eje,Motor motor) {
		super(eje,motor);
		cambio = 1;//empieza en primera
	}
	public double getTorque() {
		if(getMotor().obtenerRPM()>getMotor().getRPMMaximo()*3/4 && getCambio()<MAX_CAMBIO)
			incCambio();
		return convertir(getMotor().getTorque());
	}

	public boolean desgastar(int tiempo){
		return desgastado();
	}

	public int getCambio() {
		return cambio;
	}

	protected void incCambio(){
		cambio++;
	}

	public double obtenerRpmEntrada(){
		int[] relaciones = getRelaciones();
		return getEje().getRpm()*relaciones[cambio];
	}

	public double convertir(double torque){
		int[] relaciones = getRelaciones();
		return torque * relaciones[cambio];
	}

}
