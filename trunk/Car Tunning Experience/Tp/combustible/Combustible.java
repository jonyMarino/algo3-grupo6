package combustible;

/**
 *  El Combustible es necesario para que el {@link Auto} se mueva. Sin �l no se podr�a realizar el proceso de combusti�n.
 *  La clase Combustible es abstracta y sirve de base a distintos tipos de Combustible.
 *  @see Motor
 *  @see TanqueCombustible
 *  @see Nafta
 *
 */
public abstract class Combustible {

	private double        costo;


/**
 * Crea un nuevo Combustible con el costo especificado.
 *
 * @param costo el costo del nuevo combustible
 */
public Combustible(double costo){
	this.setCosto(costo);
}

/*********************************************************************************/

/**
 * Devuelve el peso espec�fico del combustible.
 * @return peso espec�fico del combustible
 */
public abstract double getPesoEspecifico();


/**
 * Devuelve el costo por litro del combustible.
 * @return el costo por litro del combustible
 */
public double getCosto() {
	return costo;
}

private void setCosto(double costo) {
	this.costo = costo;
}

/*********************************************************************************/
}
