package auto.partesAuto.tanque;
import auto.PartesAuto;

public abstract class Tanque extends PartesAuto{
		
	private int capacidad;
	
public Tanque(int capacidad) {
		super();
		this.setCapacidad(capacidad);
}

public double getCapacidad(){
	return capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

/*********************************************************************************/
}
