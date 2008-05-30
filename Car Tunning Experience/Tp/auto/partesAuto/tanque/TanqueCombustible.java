package auto.partesAuto.tanque;

import combustible.Combustible;
import auto.ParteAuto;
import auto.partesAuto.BoundsException;

/**
* Clase abstracta que encapsula el comportamiento y las características
* de un Tanque de Combustible.
*
* @see PartesAuto
* @see Combustible
* @see TanqueNafta
*/
public abstract class TanqueCombustible extends ParteAuto {

	private int         capacidad;
	private double      cantidadCombustible;
	private Combustible combustible;

	/**
	* Crea un nuevo TanqueCombustible (vacío) con la capacidad especificada.
	*
	* @param capacidad La capacidad del tanque.
	* @param combustible El tipo de {@link Combustible} que almacena.
	*/
	public TanqueCombustible(int capacidad,Combustible combustible) {
			super();
			this.setCapacidad(capacidad);
			this.setCantidadCombustible(0);
			this.setCombustible(combustible);
	}

	/**
	* Devuelve la capacidad del TanqueCombustible.
	*
	* @return la capacidad del Tanque
	*/
	public double getCapacidad() {
		return capacidad;
	}

	private void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	* Devuelve la cantidad de {@link Combustible} que le queda.
	*
	* @return la cantidad de {@link Combustible} restante.
	*/
	public double getCantidadCombustible() {
		return cantidadCombustible;
	}

	protected void setCantidadCombustible(double cantidadCombustible) {
		this.cantidadCombustible = cantidadCombustible;
	}

	/**
	* Carga el Tanque.
	*
	* @param litros Los litros a cargar en el tanque, del tipo de {@link Combustible}
	*       que almacena el Tanque.
	*
	* @throws BoundsException
	*/
	public void llenarTanque(double litros) throws BoundsException {
		if(litros < 0)
				throw new BoundsException("Llenar Tanque con litros negativos");
		else if ((litros + this.getCantidadCombustible()) > this.getCapacidad())
				throw new BoundsException("Se supera la capacidad del Tanque");
		else this.setCantidadCombustible(getCantidadCombustible() + litros);
	}

	/**
	* Le entrega {@link Combustible} a quien se lo solicite.
	*
	* @param litros Los litros de combustible a entregar.
	*
	* @return litros de combustible que pudo entregar.
	*
	* @throws BoundsException
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
	* Calcula el peso del Tanque Combutible de acuerdo a la cantidad y tipo de {@link Combustible}.
	*
	* @return el peso del tanque.
	*/
	public abstract double getPeso();

	public boolean desgastar(int tiempo) {
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/1000);
		}catch(BoundsException e){
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}

		return desgastado();
	}

	/**
	 * Devuelve el tipo de {@link Combustible) que almacena.
	 *
	 * @return un objeto de la clase de {@link Combustible) que almacena.
	 */
	public Combustible getCombustible() {
		return combustible;
	}

	/**
	 * Especifíca que tipo {@link Combustible) de va a almacenar el TanqueCombustible, eliminando la
	 * cantidad de tipo de Combustible anterior.
	 *
	 * @param combustible La nueva clase de {@link Combustible) a almacenar.
	 */
	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

}
