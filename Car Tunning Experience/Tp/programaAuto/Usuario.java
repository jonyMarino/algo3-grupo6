package programaAuto;

/*
 * El usuario pertenece al modelo, no puede tener referencias a Swing,
 * TODO: usuario visual
 */
import auto.Auto;
import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;
import excepciones.WrongUserNameException;

public class Usuario {
	private double dinero;
	private Auto auto;
	private String nombre;
	private Taller taller;

	public Usuario(String nombre) throws WrongUserNameException {
		this.setNombre(nombre);
    	this.setDinero(1000);
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
	
	public String getNombre() {
		return nombre;
	}

	public Taller getTaller() {
		return taller;
	}

	private void setNombre(String nombre) throws WrongUserNameException {
		if(nombre.equals(""))
			throw new WrongUserNameException("Nombre usuario inválido");
		else
			this.nombre = nombre;
	}

}
