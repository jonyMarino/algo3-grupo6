package auto.partesAuto.mezclador;

import auto.PartesAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.tanque.TanqueCombustible;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link TanqueCombustible},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Motor
 * @see PartesAuto
 * @see TanqueCombustible
 */

public abstract class Mezclador extends PartesAuto{

	private int rendimiento;

	/**
	* Crea un nuevo Mezclador.
	*
	*@param rendimiento El rendmiento con el que opera el Mezclador (0..100)
	*
	*/
	public Mezclador(int rendimiento,TanqueCombustible tanque) {
		super();
		try {
			this.setRendimiento(rendimiento);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	*
	* Devuelve la cantidad de Mezcla pedida por el Motor. En caso de no poseer la
	* cantidad de nafta necesaria para realizar la Mezcla pedida, devuelve la
	* cantidad máxima de Mezcla que puede generar.
	*
	* @param litrosMezcla la cantidad de mezcla que le quiero pedir
	*
	*@return cantidad de mezcla que pudo generar
	* @throws BoundsException 
	*/
	public abstract double obtenerMezcla(double litrosMezcla) throws BoundsException;

	/**
	*
	* Devuelve el rendimiento con el que opera el Mezclador
	*
	*@return el rendimiento (0..100)
	*/
	public int getRendimiento(){
		return rendimiento;
	}

	public boolean desgastar(int tiempo) {
		setVidaUtil(getVidaUtil()-tiempo/1000);
		try {
			setRendimiento(getRendimiento()-tiempo/1000);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		return desgastado();
	}

	private void setRendimiento(int rendimiento) throws BoundsException {
		if(rendimiento < 0)
		   throw new BoundsException("Rendimiento Mezclador negativo");
		else if (rendimiento > 100)
		   throw new BoundsException("Rendimiento Mezclador excesivo");
		else this.rendimiento = rendimiento;
	}
	
}

