package proveedorDeCombustibles;



/**
 *  El Combustible es necesario para que el {@link Auto} se mueva. Sin él no se podría realizar el proceso de combustión.
 *  La clase Combustible es abstracta y sirve de base a distintos tipos de Combustible.
 *  @see Motor
 *  @see TanqueCombustible
 *  @see Nafta
 *
 */
public abstract class Combustible {
	private double        costo;
	private InformacionCombustible informacionCombustible;

	/**
	 * Crea un nuevo Combustible con el costo especificado.
	 *
	 * @param costo El costo del nuevo Combustible.
	 */
	Combustible(double costo) {
		this.setCosto(costo);
	}

	/**
	 * Devuelve el peso específico del combustible.
	 *
	 * @return peso específico del combustible.
	 */
	public abstract double getPesoEspecifico();


	/**
	 * Devuelve el costo por litro del combustible.
	 *
	 * @return el costo por litro del combustible.
	 */
	public double getCosto() {
		return costo;
	}

	void setCosto(double costo) {
		this.costo = costo;
	}
	
	public InformacionCombustible getInformacionCombustible() {
		return informacionCombustible;
	}

	void setInformacionCombustible(InformacionCombustible InformacionCombustible) {
		this.informacionCombustible = InformacionCombustible;
	}

}