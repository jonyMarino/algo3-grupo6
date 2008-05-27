package auto.partesAuto.mezclador;

import combustible.Nafta;
import auto.partesAuto.BoundsException;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Nafta} del {@link TanqueNafta},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see TanqueNafta
 * @see Motor
 * @see Nafta
 */
public class MezcladorNafta extends Mezclador {

	//TODO: Se modifico codigo
        //private TanqueNafta tanqueNafta;

	/**
	*
	* Crea un nuevo MezcladorNafta con un {@link TanqueNafta} asociado.
	*
	*/
	public MezcladorNafta(int rendimiento, TanqueNafta tanqueNafta) throws BoundsException{
		super(rendimiento,tanqueNafta);
		this.setTanqueNafta(tanqueNafta);
	}

	/**
	 * Genera una mezcla formada por {@link Nafta} mezclada con aire, teniendo en
	 * cuenta su rendimiento y la calidad de la Nafta.
	 *
	 * @return la cantidad de mezcla obtenida, sino se pudo realizar devuelve 0.
	 * @throws BoundsException 
	 */
	public double obtenerMezcla(double litrosMezcla) throws BoundsException {
		double mezclaProducida = 0;
		if(this.getVidaUtil() > 0){
				if (litrosMezcla < 0)
					throw new BoundsException("Litros mezcla negativa");
				else{ 
					double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
					double naftaNecesaria = ((mezclaNecesaria*100)/(this.getTanqueNafta().getTipoNafta().getOctanaje()));
					
					if(naftaNecesaria > this.getTanqueNafta().getCantidadCombustible())
							throw new BoundsException("Faltante de nafta necesaria para la mezcla pedida");			
					else{
						this.getTanqueNafta().usarCombustible(naftaNecesaria);
						mezclaProducida = litrosMezcla;
					}			
				}
				
			/*
			double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
			double naftaNecesaria = ((mezclaNecesaria*100)/(this.getTanqueNafta().getTipoNafta().getOctanaje()));
			if(naftaNecesaria > this.getTanqueNafta().getCantidadCombustible()){
				double naftaUtilRestante = ((this.getTanqueNafta().getCantidadCombustible()*this.getTanqueNafta().getTipoNafta().getOctanaje())/100);
				this.getTanqueNafta().usarCombustible(this.getTanqueNafta().getCantidadCombustible());
				mezclaProducida = ((this.getRendimiento()*naftaUtilRestante)/100);
			}else{
				this.getTanqueNafta().usarCombustible(naftaNecesaria);
				mezclaProducida = litrosMezcla;
			}
			*/
		}
		return mezclaProducida;
	}
	
	/**
	*
	*Devuelve el {@link TanqueNafta} asociado al MezcladorNafta.
	* @return el {@link TanqueNafta} asociado al MezcladorNafta
	* @see TanqueNafta
	*/
	public TanqueNafta getTanqueNafta() {
		return (TanqueNafta)getTanqueCombustible();
	}

	/**
	*
	* Asigna un {@link TanqueNafta} al Mezclador
	*
	*@see TanqueNafta
	*/
	public void setTanqueNafta(TanqueNafta tanqueNafta) throws BoundsException {
	    if(tanqueNafta instanceof TanqueNafta )
		setTanqueCombustible(tanqueNafta);
	    else throw new BoundsException();
	}


}
