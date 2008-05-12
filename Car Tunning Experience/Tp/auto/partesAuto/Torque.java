package auto.partesAuto;
import java.lang.reflect.*;
public class Torque {
 
	private Method getMagnitud;
	 
	public void setMethod(Method metodo) {
		getMagnitud = metodo;
	}
	 
	public float getMagnitud() {
		return getMagnitud.invoke();
	}
	 
}
 

