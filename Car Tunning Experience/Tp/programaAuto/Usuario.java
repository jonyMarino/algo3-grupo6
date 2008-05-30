package programaAuto;
import java.util.ArrayList;

import auto.Auto;
import auto.ParteAuto;
import excepciones.NotEnoughMoneyException;

public class Usuario {
	private double dinero;
	private Auto auto;
	private ArrayList<ParteAuto> partesDisponibles;
	
	public Usuario(Auto miAuto) {
		setAuto(miAuto);
		setDinero(0);
		partesDisponibles = new ArrayList<ParteAuto>();
	}
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
	public boolean gastarDinero(double cantidadDeDineroAGastar) throws NotEnoughMoneyException{
		if(cantidadDeDineroAGastar > getDinero())
			throw new NotEnoughMoneyException("El usuario no puede gastar mas dinero del que tiene.");
		else setDinero(getDinero()-cantidadDeDineroAGastar);
		return true;
	}
	
}
