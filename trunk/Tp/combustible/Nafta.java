package combustible;

public abstract class Nafta extends Combustible{

	private static double pesoEspecifico = 0.75;
	
public abstract int getOctanaje();

public double getPesoEspecifico(){
	return pesoEspecifico;
}

/*********************************************************************************/
}


