package combustible;

public abstract class Combustible {
	
	private double        costo;

public Combustible(double costo){
	this.setCosto(costo);
}

public abstract double getPesoEspecifico();

public double getCosto() {
	return costo;
}

public void setCosto(double costo) {
	this.costo = costo;
}

/*********************************************************************************/
}
