package programaAuto;


import nu.xom.Element;
import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;
import excepciones.NotRegisteredCarException;
import excepciones.WrongUserNameException;

/**
 * Clase que encapsula toda la informacion necesaria para describir a un
 * usuario (jugador).
 *
 * @see Taller
 * @see ProgramaAuto
 */
public class Usuario {
	private double dinero;
	private Auto auto;
	private String nombre;
	private Taller taller;

    /**
     * Crea un nuevo usuario y le asocia un nombre
     *
     * @param nombre El nombre del usuario
     * @exception WrongUserNameException
     */
	public Usuario(String nombre) throws WrongUserNameException {
		this.setNombre(nombre);
    	this.setDinero(1000);
		this.taller = new Taller(this);
	}

	/**
	 * Constructor para restaurar un objeto Usuario
	 * @param usuario
	 */
	public Usuario(CadenaDeFabricas fabrica,AutosFactory fabricaAutos,Element element){
		//Element usuario = element.getFirstChildElement("usuario");
		nombre=element.getFirstChildElement("nombre").getValue();
		dinero = Double.parseDouble(element.getFirstChildElement("dinero").getValue());
		auto = fabricaAutos.crear(fabrica, element);
		this.taller = new Taller(this,fabrica,element);	
	}
	
	public Element getElement(AutosFactory fabricaAutos){
		Element usuario=new Element("usuario");
		Element unNombre=new Element("nombre");
		unNombre.appendChild(nombre);
		Element unDinero=new Element("dinero");
		unDinero.appendChild(dinero+"");
		usuario.appendChild(unNombre);
		usuario.appendChild(unDinero);
		try {
			usuario.appendChild(fabricaAutos.getElement(auto));
		} catch (NotRegisteredCarException e) {
			e.printStackTrace();
		}
		usuario.appendChild(taller.getElement());
		return usuario;
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
