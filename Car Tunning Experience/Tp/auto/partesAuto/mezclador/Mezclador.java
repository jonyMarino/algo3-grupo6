package auto.partesAuto.mezclador;
import auto.PartesAuto;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link Tanque}, 
 * mezclarlo y dejarlo listo para el proceso de combusti�n.
 * @see Tanque
 * @see Motor
 * @see PartesAuto
 */

public abstract class Mezclador extends PartesAuto{

	private int rendimiento;
	private TanqueNafta tanqueNafta;
	
	/**
	* Crea un nuevo Mezclador y le asocia un {@link Tanque}.
	*
	*@param eficiencia La eficiencia con la que opera el Mezclador (0..100)
	*@param tanqueNafta El {@link Tanque} que quiero asociar al Motor.
	*
	*@see Tanque
	*/
	//TODO: �recibe TanqueNafta o Tanque?
public Mezclador(int rendimiento,TanqueNafta tanqueNafta) {
		super();
		this.setRendimiento(rendimiento);
		this.setTanqueNafta(tanqueNafta);
}

/**
*
* Devuelve la cantidad de Mezcla pedida por el Motor. En caso de no poseer la 
* cantidad de nafta necesaria para realizar la Mezcla pedida, devuelve la
* cantidad m�xima de Mezcla que puede generar.
*
*/
public abstract double obtenerMezcla(double litrosMezcla);

/**
*
* Devuelve la eficiencia con la que opera el Mezclador
*/		
public int getRendimiento(){
	return rendimiento;
}

//si, es privado... la eficiencia del mezclador se define en el constructor... 
// y despues no se puede modificar
//TODO: Vero13 se puede modificar el rendimiento, debido al desgaste. Ok! voy a tenerlo 
//en cuenta.
private void setRendimiento(int rendimiento) {
	if(rendimiento < 0)
	   this.rendimiento = 0;
	else if (rendimiento > 100)
		this.rendimiento = 100;
	else this.rendimiento = rendimiento;
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

