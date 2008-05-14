package auto.partesAuto.mezclador;

import combustible.Nafta;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Nafta} del {@link TanqueNafta},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see TanqueNafta
 * @see Motor
 * @see Nafta
 */
public class MezcladorNafta extends Mezclador {

	private TanqueNafta tanqueNafta;

	/**
	*
	* Crea un nuevo MezcladorNafta con un {@link TanqueNafta} asociado.
	*
	*/
	public MezcladorNafta(int rendimiento, TanqueNafta tanqueNafta) {
		super(rendimiento);
		this.setTanqueNafta(tanqueNafta);
	}

	/**
	 * Genera una mezcla formada por {@link Nafta} mezclada con aire, teniendo en
	 * cuenta su rendimiento y la calidad de la Nafta.
	 *
	 *æreturn la cantidad de mezcla que fue posible obtener
	 */
	public double obtenerMezcla(double litrosMezcla){
		TanqueNafta tanqueNafta = this.getTanqueNafta();
		Nafta nafta = tanqueNafta.getTipoNafta();
		double mezclaProducida = 0;
		if(this.getVidaUtil() > 0 && litrosMezcla >= 0){
			double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
			double naftaNecesaria = ((mezclaNecesaria*100)/(nafta.getOctanaje()));
			if(naftaNecesaria > tanqueNafta.getCantidadCombustible()){
				double naftaUtilRestante = ((tanqueNafta.getCantidadCombustible()*nafta.getOctanaje())/100);
				tanqueNafta.usarCombustible(tanqueNafta.getCantidadCombustible());
				mezclaProducida = ((this.getRendimiento()*naftaUtilRestante)/100);
			}else{
				tanqueNafta.usarCombustible(naftaNecesaria);
				mezclaProducida = litrosMezcla;
			}
		}
		return mezclaProducida;
	}

	/**
	*
	*Devuelve el {@link TanqueNafta} asociado al MezcladorNafta.
	*@return el {@link TanqueNafta} asociado al MezcladorNafta
	*@see TanqueNafta
	*/
	public TanqueNafta getTanqueNafta() {
		return tanqueNafta;
	}

	/**
	*
	* Asigna un {@link TanqueNafta} al Mezclador
	*
	*@see TanqueNafta
	*/
	public void setTanqueNafta(TanqueNafta tanqueNafta) {
		this.tanqueNafta = tanqueNafta;
	}


}
