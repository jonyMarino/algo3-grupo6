package combustible;

/**
 * Una clase particular de {@link Combustible}.
 *
 *@see Combustible
 */
public class Nafta extends Combustible{

	private int           octanaje;
	private static double pesoEspecifico = 0.75;

/**
 * Crea un nuevo objeto de tipo nafta.
 *
 * @param octanaje el octanaje de la Nafta
 * @param costo el costo por litro de la Nafta
 */
public Nafta (int octanaje, double costo){
	super(costo);
	this.setOctanaje(octanaje);
}

/*********************************************************************************/

/**
 * devuelve el octanaje de la Nafta
 * @return octanaje de la Nafta
 */
public int getOctanaje(){
	return octanaje;
}

/* (non-Javadoc)
 * @see combustible.Combustible#getPesoEspecifico()
 */
public double getPesoEspecifico(){
	return pesoEspecifico;
}

private void setOctanaje(int octanaje){
	if(octanaje > 100)
		this.octanaje = 100;
	else if(octanaje < 0)
		this.octanaje = 0;
	else this.octanaje = octanaje;
}

/*********************************************************************************/
}
