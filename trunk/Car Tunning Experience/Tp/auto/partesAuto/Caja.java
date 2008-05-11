package auto.partesAuto;
import auto.Auto;
import auto.PartesAuto;


public abstract class Caja extends PartesAuto {
 
	private int cambio;
			 
	private auto.partesAuto.motor.Motor motor;
		 
	private Torque torque;
	 
	private Eje eje;
	 
	public float convertir(double fuerza) {
		return 0;
	}
	 
	public void setCambio(int cambio) {
		if(cambio == getCambio()+1){
			if(motor.necesitaCambio()){
				motor.nuevoCambio();
			}
			else motor.detenerse();
		}
		else if(cambio == getCambio()-1)
			
		
	}
	 
	public int getCambio() {
		return cambio;
	}
	 
	public float obtenerRpsEntrada() {
		return 0;
	}
	 
}