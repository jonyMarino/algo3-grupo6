package proveedorDePartes.fabricas;


import excepciones.BoundsException;
import excepciones.PartBrokenException;
import excepciones.WrongPartClassException;

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
	 * @throws IsBrokenException 
	 */
	public double obtenerMezcla(double litrosMezcla) throws BoundsException, PartBrokenException {
		double mezclaProducida = 0;
		if(this.getVidaUtil() > 0){
				if (litrosMezcla < 0)
					throw new BoundsException("Litros mezcla negativa");
				else{
					double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
					double naftaNecesaria = ((mezclaNecesaria*100)/(this.getTanqueCombustible().getCombustible().getOctanaje()));
					naftaNecesaria /= 50;

					if(naftaNecesaria > this.getTanqueCombustible().getCantidadCombustible())
							throw new BoundsException("Faltante de nafta necesaria para la mezcla pedida");
					else{
						try {
							this.getTanqueCombustible().usarCombustible(naftaNecesaria);
							mezclaProducida = litrosMezcla;
						} catch (BoundsException e) {
							mezclaProducida = 0;
						} catch (PartBrokenException e) {
							mezclaProducida = 0;
						}
					}
				}
				return mezclaProducida;
		} else
			throw new PartBrokenException("El Mezclador se rompio");	
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
	* @throws WrongPartClassException
	*/
    public void setTanqueCombustible(TanqueNafta tanqueNafta) throws WrongPartClassException {
	    if(tanqueNafta instanceof TanqueNafta)
		super.setTanqueCombustible(tanqueNafta);
	    else throw new WrongPartClassException("El tanque deberia ser un TanqueNafta");
	}

}
