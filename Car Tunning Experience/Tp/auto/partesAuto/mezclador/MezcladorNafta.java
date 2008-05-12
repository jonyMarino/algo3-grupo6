package auto.partesAuto.mezclador;
import auto.partesAuto.tanque.TanqueNafta;
import combustible.Nafta;


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
public MezcladorNafta(int eficiencia, TanqueNafta tanqueNafta) {
	super(eficiencia, tanqueNafta);
}

//TODO: revisar definicion en Mezclador.java
public double obtenerMezcla(double litrosMezcla){
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	Nafta naftaAux = tanqueNaftaAux.getTipoNafta();
	double mezclaProducida = 0;
	if(this.getVidaUtil() > 0){
		double mezclaNecesaria = ((litrosMezcla*100)/this.getEficiencia());
		double naftaNecesaria = ((mezclaNecesaria*100)/naftaAux.getOctanaje());
		tanqueNaftaAux.usarNafta(naftaNecesaria);
		mezclaProducida = litrosMezcla;
		if(naftaNecesaria > tanqueNaftaAux.getCantidadNafta()){
			double naftaUtilRestante = ((tanqueNaftaAux.getCantidadNafta()*naftaAux.getOctanaje())/100);
			tanqueNaftaAux.usarNafta(tanqueNaftaAux.getCantidadNafta());
			mezclaProducida = ((this.getEficiencia()*naftaUtilRestante)/100);
		}
	}
	return mezclaProducida;
}
	
}
