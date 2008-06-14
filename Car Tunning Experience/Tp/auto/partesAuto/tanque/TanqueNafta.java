package auto.partesAuto.tanque;

import combustible.Nafta;

/**
 * El Tanque de Nafta sirve para almacenar {@link Nafta}.
 *
 * @see TanqueCombustible
 * @see Nafta
 */
public class TanqueNafta extends TanqueCombustible {

	/**
	* Crea un nuevo Tanque de Nafta (vacío) con la capacidad especificada.
	*
	* @param capacidad La capacidad del Tanque de Nafta.
	* @param nafta El tipo de {@link Nafta} que almacena.
	*/
	TanqueNafta(int capacidad,Nafta nafta) {
		super(capacidad,nafta);
	}

	/**
	* Calcula el peso del TanqueNafta de acuerdo a la cantidad y tipo de {@link Nafta} que almacena.
	*
	* @return el peso del TanqueNafta.
	*/
	public double getPeso() {
		double peso = this.getCantidadCombustible() * this.getCombustible().getPesoEspecifico();
		return peso;
	}

	/**
	 * Especifíca que tipo de {@link Nafta) va a almacenar el TanqueNafta, eliminando la
	 * cantidad de tipo de Nafta anterior.
	 *
	 * @param nafta la nueva clase de {@link Nafta} a almacenar.
	 */
	public void setCombustible(Nafta nafta) {
		this.setCantidadCombustible(0);
		super.setCombustible(nafta);
	}

	/**
	 * Devuelve el tipo de {@link Nafta} que almacena.
	 *
	 * @return un objeto de la clase de {@link Nafta} que almacena.
	 */
	public Nafta getCombustible() {
		return (Nafta)super.getCombustible();
	}

}