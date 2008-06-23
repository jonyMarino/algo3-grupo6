package programaAuto;

import javax.swing.ImageIcon;
import taller.Taller;
import auto.Auto;
import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;

public class Usuario {
	private double dinero;
	private Auto auto;
	private String nombre;
	private ImageIcon avatar;
	private Taller taller;

	public Usuario(String nombre) {
		this.nombre = nombre;
		setDinero(1000);
		this.taller = new Taller(this);
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
	
	public ImageIcon getAvatar() {
		return avatar;
	}
	public void setAvatar(ImageIcon avatar) {
		this.avatar = avatar;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Taller getTaller() {
		return taller;
	}

}
