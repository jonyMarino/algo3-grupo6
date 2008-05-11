package auto.partesAuto;
import auto.Auto;
import auto.PartesAuto;

public abstract class Caja extends PartesAuto {
 
	private int cambio;
	 
	private Auto auto;
	 
	private Motor motor;
		 
	private Torque torque;
	 
	private Eje eje;
	 
	public float convertir(double fuerza) {
		return 0;
	}
	 
	public void setCambio(float cambio) {
	}
	 
	public int getCambio() {
		return 0;
	}
	 
	public float obtenerRpsEntrada() {
		return 0;
	}
	 
}