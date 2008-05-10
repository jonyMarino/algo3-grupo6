package combustible;

public class Nafta extends Combustible{

	private int           octanaje;
	private static double pesoEspecifico = 0.75;
	
public Nafta (int octanaje, double costo){
	super(costo);
	this.setOctanaje(octanaje);
}

/*********************************************************************************/
public int getOctanaje(){
	return octanaje;
}

public double getPesoEspecifico(){
	return pesoEspecifico;
}

private void setOctanaje(int octanaje){
	if(octanaje > 100)
		this.octanaje = 100;
	if else(octanaje < 0)
		this.octanaje = 0;
	else this.octanaje = octanaje;
}

/*********************************************************************************/
}
