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
public double obtenerMezcla(double litrosDeMezcla){
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	Nafta naftaAux = tanqueNaftaAux.getTipoNafta();

	double mezcla = 0;
	if (tanqueNaftaAux.obtenerCantidadNafta() !=0){
	mezcla = litrosDeMezcla+(litrosDeMezcla *(200 -(this.getEficiencia()+naftaAux.getOctanaje())));
	double litrosUsados = tanqueNaftaAux.usarNafta(mezcla);
	if(litrosUsados < mezcla)
	       mezcla = litrosUsados;
	}
	return mezcla;
}
	
}
