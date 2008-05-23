package auto.partesAuto.tanque;

import combustible.Combustible;
import auto.ParteAuto;
import auto.partesAuto.BoundsException;

/**
*
* Clase abstracta que encapsula el comportamiento y las características
* de un Tanque de Combustible.
*
* @see PartesAuto
* @see Combustible
*
*/
public abstract class TanqueCombustible extends ParteAuto{

	private int capacidad;
	private double cantidadCombustible;
//TODO: Agrego excepciones
	/**
	*
	* Crea un nuevo TanqueCombustible (vacío) con la capacidad especificada.
	*
	* @param capacidad La capacidad del tanque.
	*
	*/
	public TanqueCombustible(int capacidad,Combustible combustible) {
			super();
				try {
					this.setCapacidad(capacidad);
				} catch (BoundsException e) {
					e.printStackTrace();
				}
				this.setCantidadCombustible(0);
	}

	/**
	*
	*Devuelve la capacidad del Tanque.
	*
	* @return la capacidad del Tanque
	*/
	public double getCapacidad(){
		return capacidad;
	}

	private void setCapacidad(int capacidad)throws BoundsException {
		if(capacidad < 0)
			throw new BoundsException("Valor capacidad negativo");
		else this.capacidad = capacidad;
	}

	/**
	*
	*Devuelve la cantidad de {@link Combustible} que le queda.
	*
	* @return la cantidad de {@link Combustible} restante
	*
	* @see Combustible
	*/
	public double getCantidadCombustible() {
		return cantidadCombustible;
	}

	protected void setCantidadCombustible(double cantidadCombustible) {
		this.cantidadCombustible = cantidadCombustible;
	}

	/**
	*
	* Carga el Tanque.
	*
	* @param litros Los litros a cargar en el tanque, del tipo de Combustible
	*       que almacena el Tanque.
	* @throws BoundsException
	* @see Combustible
	*/
	public void llenarTanque(double litros) throws BoundsException {		
		if(litros < 0)
				throw new BoundsException("Llenar Tanque con litros negativos");
		else if ((litros + this.getCantidadCombustible()) > this.getCapacidad())
				throw new BoundsException("Se supera la capacidad del Tanque");
		else this.setCantidadCombustible(getCantidadCombustible() + litros);
	}

	/**
	*
	* Le entrega combustible a quien se lo solicite.
	*
	* @param litros los litros de combustible a entregar.
	* @return litros de combustible que pudo entregar.
	* @throws BoundsException
	* @see Combustible
	*/
	public double usarCombustible(double litros) throws BoundsException{
		double combustibleConsumo = 0;
		if(this.getVidaUtil() > 0){
				if (litros > this.getCantidadCombustible())
					throw new BoundsException("No se posee la cantidad de combustible pedida");
				else if(litros < 0)
					throw new BoundsException("Se quiere usar una cantidad de combustible negativa");
				else{
					this.setCantidadCombustible(this.getCantidadCombustible() - litros);
					combustibleConsumo = litros;
				}
		}
		return combustibleConsumo;
	}

	/**
	*
	* Calcula el peso del Tanque Combutible de acuerdo a su cantidad de combustible.
	*
	* @return el peso del tanque y su contenido
	* @see PartesAuto
	* @see Combustible
	*/
	public abstract double getPeso();

	public boolean desgastar(int tiempo) {
		setVidaUtil(getVidaUtil()-tiempo/1000);
		return desgastado();
	}

}
