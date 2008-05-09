package auto.parteAuto.tanque;
import auto.ParteAuto;

public abstract class Tanque extends ParteAuto{
		
	private int capacidad;
	
public Tanque(int capacidad,double peso, double costo, double desgaste) {
		super(peso, costo, desgaste);
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
