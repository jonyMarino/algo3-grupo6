package auto.partesAuto.mezclador;
import auto.PartesAuto;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link Tanque}, 
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Tanque
 * @see Motor
 * @see PartesAuto
 */

public abstract class Mezclador extends PartesAuto{

	private int eficiencia;
	private TanqueNafta tanqueNafta;
	
	/**
	* Crea un nuevo Mezclador y le asocia un {@link Tanque}.
	*
	*@param eficiencia La eficiencia con la que opera el Mezclador (0..100)
	*@param tanqueNafta El {@link Tanque} que quiero asociar al Motor.
	*
	*@see Tanque
	*/
	//TODO: ¿recibe TanqueNafta o Tanque?
public Mezclador(int eficiencia,TanqueNafta tanqueNafta) {
		super();
		this.setEficiencia(eficiencia);
		this.setTanqueNafta(tanqueNafta);
}

/**
*
*
*
*/
//TODO: Revisar el significado de litrosDeMezcla. ¿Es la cantidad de Mezcla que le pido o el combustible que consume?
public abstract double obtenerMezcla(double litrosDeMezcla);

/**
*
* Devuelve la eficiencia con la que opera el Mezclador
*/		
public int getEficiencia(){
	return eficiencia;
}

// TODO: ¿public o private?
public void setEficiencia(int eficiencia) {
	if(eficiencia < 0)
	   this.eficiencia = 0;
	else if (eficiencia > 100)
		this.eficiencia = 100;
	else this.eficiencia = eficiencia;
}

/**
*
*Devuelve el {@link Tanque} asociado al Mezclador.
*@see Tanque
*/
public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

/**
*
* Asigna un {@link Tanque} al Mezclador
*
*@see Tanque
*/
public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
}

}

