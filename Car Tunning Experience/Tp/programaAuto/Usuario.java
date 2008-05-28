package programaAuto;
import auto.Auto;

public class Usuario {
	private double dinero;
	private Auto auto;
	
	public void setAuto(Auto miAuto) {
		this.auto = miAuto;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	public double getDinero() {
		return dinero;
	}
	
	
}
