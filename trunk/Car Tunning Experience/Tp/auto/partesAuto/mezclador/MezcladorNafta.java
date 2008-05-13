package auto.partesAuto.mezclador;

import combustible.Nafta;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link Tanque}, mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Tanque
 * @see Motor
 * @see Mezclador
 */
public class MezcladorNafta extends Mezclador {

/**
*
* Crea un nuevo MezcladorNafta con un {@link TanqueNafta} asociado.
*
*@see TanqueNafta
*
*/
public MezcladorNafta(int rendimiento, TanqueNafta tanqueNafta) {
	super(rendimiento, tanqueNafta);
}

/**
 * Genera una mezcla formada por {@link Nafta} mezclado con aire, teniendo en
 * cuenta su rendimiento y la calidad de la Nafta. 
 * 
 */
public double obtenerMezcla(double litrosMezcla){
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	Nafta naftaAux = tanqueNaftaAux.getTipoNafta();
	double mezclaProducida = 0;
	if(this.getVidaUtil() > 0 && litrosMezcla >= 0){
		double mezclaNecesaria = ((litrosMezcla*100)/this.getRendimiento());
		double naftaNecesaria = ((mezclaNecesaria*100)/(naftaAux.getOctanaje()));
		if(naftaNecesaria > tanqueNaftaAux.getCantidadNafta()){
			double naftaUtilRestante = ((tanqueNaftaAux.getCantidadNafta()*naftaAux.getOctanaje())/100);
			tanqueNaftaAux.usarNafta(tanqueNaftaAux.getCantidadNafta());
			mezclaProducida = ((this.getRendimiento()*naftaUtilRestante)/100);
		}else{
			tanqueNaftaAux.usarNafta(naftaNecesaria);
			mezclaProducida = litrosMezcla;
		}
	}
	return mezclaProducida;
}

public boolean desgastar(int tiempo) {
	setVidaUtil(getVidaUtil()-tiempo/1000);
	return desgastado();
}
	
}
