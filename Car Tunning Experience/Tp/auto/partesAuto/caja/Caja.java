package auto.partesAuto.caja;

import auto.PartesAuto;
import auto.partesAuto.Eje;
import auto.partesAuto.Torque;
import auto.partesAuto.motor.Motor;

public abstract class Caja extends PartesAuto {
 
	private int cambio;
			 
	private Motor motor;
		 
	private Torque torque;
	 
	private Eje eje;
	 
	public float convertir(double fuerza) {
		return 0;
	}
	
	public void setCambio(int cambio) {
		
	}
	
	public Motor getMotor(){
		return motor;
	}
	
	public int getCambio() {
		return cambio;
	}
	 
	public float obtenerRpsEntrada() {
		return 0;
	}
	 
}