package programaAuto;
import excepciones.NotEnoughMoneyException;
import auto.Auto;
import auto.ParteAuto;
import auto.partesAuto.Escape;

public class Usuario {
	private double dinero;
	private Auto auto;
	
	public Usuario(Auto miAuto) {
		setAuto(miAuto);
		setDinero(0);
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
	public void ensamblarParte(ParteAuto miParte){
		if(miParte instanceof Escape)
			getAuto().setEscape((Escape) miParte);
	}
}
