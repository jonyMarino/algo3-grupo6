package auto.partesAuto.tanque;
import auto.PartesAuto;
/**
*
* Clase abstraca que encapsula el comportamiento y las características 
* de un Tanque.
*
* @see PartesAuto
* @see Combustible
*
*/
public abstract class Tanque extends PartesAuto{
		
	private int capacidad;

/**
*
* Crea un nuevo Tanque con la capacidad especificada.
*
*@param capacidad La capacidad del tanque.
*
*/	
public Tanque(int capacidad) {
		super();
		this.setCapacidad(capacidad);
}

/**
*
*Devuelve la capacidad del tanque.
*
*/
public double getCapacidad(){
	return capacidad;
}

private void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

}
