package proveedores.proveedorDePartes.fabricas;

import nu.xom.Element;
import proveedores.proveedorDeCombustibles.Combustible;
import proveedores.proveedorDeCombustibles.Nafta;
import excepciones.BoundsException;
import excepciones.PartBrokenException;
import excepciones.TankIsFullException;

/**
 * 
 * El Tanque de Combustible sirve para almacenar combustibles varios.
 *
 * @see TanqueCombustible
 * @see Nafta
 * 
 */
public abstract class TanqueCombustible extends ParteAuto {

	private int         capacidad;
	private double      cantidadCombustible;
	private Combustible combustible;

	TanqueCombustible(int capacidad,Combustible combustible) {
			super();
			this.setCapacidad(capacidad);
			this.setCantidadCombustible(0);
			this.setCombustible(combustible);
	}

	public double getCapacidad() {
		return capacidad;
	}

	private void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public double getCantidadCombustible() {
		return cantidadCombustible;
	}

	protected void setCantidadCombustible(double cantidadCombustible) {
		this.cantidadCombustible = cantidadCombustible;
	}

	public void llenarTanque(double litros) throws TankIsFullException, BoundsException, PartBrokenException {
		if(getVidaUtil() > 0){
			if(litros < 0)
					throw new BoundsException("Llenar Tanque con litros negativos");
			else if ((litros + this.getCantidadCombustible()) > this.getCapacidad())
					throw new TankIsFullException("Se supera la capacidad del Tanque");
			else this.setCantidadCombustible(getCantidadCombustible() + litros);
		} else
			throw new PartBrokenException("El Tanque se rompio");		
	}

	public double usarCombustible(double litros) throws BoundsException, PartBrokenException{
		double combustibleConsumo = 0;
		if(this.getVidaUtil() > 0){
				if (litros > this.getCantidadCombustible())
					throw new BoundsException("No se posee la cantidad de combustible pedida");
				else if(litros < 0)
					throw new BoundsException("Se quiere usar una cantidad de combustible negativa");
				else{
					this.setCantidadCombustible(this.getCantidadCombustible() - litros);
					combustibleConsumo = litros;
					return combustibleConsumo;
				}
		} else
			throw new PartBrokenException("El Tanque se rompió");
		
}

	public abstract double getPeso();

	public void desgastar(double tiempo) {
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/1000);
		}catch(BoundsException e){
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}

	}

	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}
	
	protected Element getElement(){
		Element tanque=super.getElement();
		Element combustible = new Element("combustible");
		combustible.appendChild(cantidadCombustible+"");
		tanque.appendChild(combustible);
		return tanque;
	}
	
	protected Element restaurar(Element elemento){
		Element tanque=super.restaurar(elemento);
		cantidadCombustible= Double.parseDouble(tanque.getFirstChildElement("combustible").getValue());
		return tanque;
	}	
}
