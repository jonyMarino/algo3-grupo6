package auto.partesAuto.mezclador;

import combustible.Nafta;
import auto.partesAuto.BoundsException;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Nafta} del {@link TanqueNafta},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 *
 * @see TanqueNafta
 * @see Motor
 * @see Nafta
 */
public class MezcladorNafta extends Mezclador {

	/**
	* Crea un nuevo MezcladorNafta con un {@link TanqueNafta} asociado.
	*
	* @param rendimiento El rendmiento con el que opera el MezcladorNafta (0..100).
	* @param tanqueNafta El {@link TanqueNafta} asignar al MezcladorNafta.
	*
	* @throws BoundsException
	*/
	MezcladorNafta(int rendimiento, TanqueNafta tanqueNafta) throws BoundsException {
		super(rendimiento,tanqueNafta);
	}

	/**
	 * Genera una mezcla formada por {@link Nafta} mezclada con aire, teniendo en
	 * cuenta su rendimiento y la calidad de la Nafta.
	 *
	 * @return la cantidad de mezcla obtenida, sino se pudo realizar devuelve 0.
	 *
	 * @throws BoundsException
	 */
	public double obtenerMezcla(double litrosMezcla) throws BoundsException {
		double mezclaProducida = 0;
		if(this.getVidaUtil() > 0){
				if (litrosMezcla < 0)
					throw new BoundsException("Litros mezcla negativa");
				else{
					double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
					double naftaNecesaria = ((mezclaNecesaria*100)/(this.getTanqueCombustible().getCombustible().getOctanaje()));

					if(naftaNecesaria > this.getTanqueCombustible().getCantidadCombustible())
							throw new BoundsException("Faltante de nafta necesaria para la mezcla pedida");
					else{
						this.getTanqueCombustible().usarCombustible(naftaNecesaria);
						mezclaProducida = litrosMezcla;
					}
				}

			}
		return mezclaProducida;
	}

	/**
	* Devuelve el {@link TanqueNafta} asociado al MezcladorNafta.
	*
	* @return el {@link TanqueNafta} asociado al MezcladorNafta.
	*/
	public TanqueNafta getTanqueCombustible() {
		return (TanqueNafta)super.getTanqueCombustible();
	}

	/**
	* Asigna un {@link TanqueNafta} al MezcladorNafta.
	*
	* @param tanqueNafta {@link TanqueNafta} asignar al MezcladorNafta.
	*
	* @throws BoundsException
	*/
    public void setTanqueCombustible(TanqueNafta tanqueNafta) throws BoundsException { //Si, ya sé, despues creo otro tipo de excepción
	    if(tanqueNafta instanceof TanqueNafta)
		super.setTanqueCombustible(tanqueNafta);
	    else throw new BoundsException();
	}

}
