package combustible;



/**
 *  El Combustible es necesario para que el {@link Auto} se mueva. Sin �l no se podr�a realizar el proceso de combusti�n.
 *  La clase Combustible es abstracta y sirve de base a distintos tipos de Combustible.
 *  @see Motor
 *  @see Tanque
 *  @see Nafta
 *
 */
public abstract class Combustible {
	
	private double        costo;

public Combustible(double costo){
	this.setCosto(costo);
}

/*********************************************************************************/
public abstract double getPesoEspecifico();

public double getCosto() {
	return costo;
}

public void setCosto(double costo) {
	this.costo = costo;
}

/*********************************************************************************/
}
