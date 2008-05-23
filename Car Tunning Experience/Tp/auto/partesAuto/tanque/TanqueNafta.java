package auto.partesAuto.tanque;

import combustible.Nafta;

/**
 * El Tanque de Nafta sirve para almacenar {@link Nafta}.
 *
 * @see TanqueCombustible
 * @see Nafta
 *
 */
public class TanqueNafta extends TanqueCombustible{

	private Nafta  tipoNafta;

	/**
	*
	*Crea un nuevo Tanque de Nafta (vacío) con la capacidad especificada.
	*
	*@param capacidad La capacidad del Tanque de Nafta
	*@param tipoNafta El tipo de {@link Nafta} que almacena.
	*
	*/
	public TanqueNafta(int capacidad,Nafta tipoNafta){
		super(capacidad,tipoNafta);
		this.setTipoNafta(tipoNafta);
	}

	//TODO: se modifico codigo
	/**
	*
	* Calcula el peso del TanqueNafta de acuerdo a la cantidad de Nafta que almacena
	*
	*@return el peso del Tanque y su contenido
	*@see PartesAuto
	*@see Combustible
	*/
	public double getPeso(){
		double peso= this.getCantidadCombustible()*this.getTipoNafta().getPesoEspecifico();
		return peso;
	}

	//TODO: agrego excepciones
	/**
	 * Especifíca que tipo de Nafta va a almacenar el TanqueNafta, eliminando la
	 * cantidad de tipo de Nafta anterior.
	 * @param nafta la nueva clase de Nafta a almacenar
	 */
	public void setTipoNafta(Nafta nafta) {
		this.setCantidadCombustible(0);
		this.tipoNafta = nafta;
	}

	/**
	 * Devuelve el tipo de Nafta que almacena.
	 * @return un objeto de la clase de Nafta que almacena
	 */
	public Nafta getTipoNafta() {
		return tipoNafta;
	}

}