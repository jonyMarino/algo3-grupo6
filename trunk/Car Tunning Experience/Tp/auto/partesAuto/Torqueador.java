package auto.partesAuto;

public interface Torqueador{
	public double getTorque();
}
/* deMarino: Me parece mejor si hacemos una interfaz 
 * y que Eje tenga referencias a tipos Torqueadores.
 * Ademas esta implementacion no me salio :(
 */ 
/*
public class Torque {
 
	private Method getMagnitud;
	private Object obj; 
	Torque(Method metodo,Object obj){
		this.getMagnitud = metodo;
		this.obj=obj;
	}
	/* deMarino : Mejor construir y no setear esto
	public void setMethod(Method metodo,Object obj) {
		this.obj
		getMagnitud = metodo;
	}
	 */
/*
	public float getMagnitud() {
		return getMagnitud.invoke(obj,null);
	}
	 
}
 
*/
