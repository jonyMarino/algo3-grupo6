package programaAuto;
import java.util.ArrayList;

import auto.Auto;
import auto.ParteAuto;
import auto.partesAuto.BoundsException;
import excepciones.NotEnoughMoneyException;

public class Usuario {
	private double dinero;
	private Auto auto;
	private String nombre;
	
	public Usuario(String nombre, Auto miAuto) {
		this.nombre = nombre;
		setAuto(miAuto);
		setDinero(1000);
	}
	private void setAuto(Auto miAuto) {
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
	public void gastarDinero(double cantidadDeDineroAGastar) throws NotEnoughMoneyException{
		if(cantidadDeDineroAGastar > getDinero())
			throw new NotEnoughMoneyException("El usuario no puede gastar mas dinero del que tiene.");
		else setDinero(getDinero()-cantidadDeDineroAGastar);
	}
	
	public void adquirirDinero(double cantidadDeDineroAdquirido) throws BoundsException{
		if(cantidadDeDineroAdquirido < 0){
			throw new BoundsException("No se puede adquirir unacantidad Negativa de dinero. Use gastarDinero(:double);.");
		}
		else setDinero(getDinero()+cantidadDeDineroAdquirido);
	}
	
}
