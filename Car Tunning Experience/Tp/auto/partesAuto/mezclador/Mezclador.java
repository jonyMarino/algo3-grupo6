package auto.partesAuto.mezclador;
import auto.PartesAuto;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link Tanque},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Motor
 * @see PartesAuto
 */

public abstract class Mezclador extends PartesAuto{

	private int rendimiento;

	/**
	* Crea un nuevo Mezclador y le asocia un {@link Tanque}.
	*
	*@param rendimiento El rendmiento con la que opera el Mezclador (0..100)
	*
	*/
	//TODO: ¿recibe TanqueNafta o Tanque?
	//TODO: no recibe nada es un mezclador generico
	public Mezclador(int rendimiento) {
		super();
		this.setRendimiento(rendimiento);
}

	/**
	*
	* Devuelve la cantidad de Mezcla pedida por el Motor. En caso de no poseer la
	* cantidad de nafta necesaria para realizar la Mezcla pedida, devuelve la
	* cantidad máxima de Mezcla que puede generar.
	*
	*/
	public abstract double obtenerMezcla(double litrosMezcla);

	/**
	*
	* Devuelve el rendimiento con la que opera el Mezclador
	*
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

}

