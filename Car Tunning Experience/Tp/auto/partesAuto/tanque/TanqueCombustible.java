package auto.partesAuto.tanque;

import auto.PartesAuto;

/**
*
* Clase abstracta que encapsula el comportamiento y las características
* de un Tanque de Combustible.
*
* @see PartesAuto
* @see Combustible
*
*/
public abstract class TanqueCombustible extends PartesAuto{

	private int capacidad;
	private double cantidadCombustible;

	/**
	*
	* Crea un nuevo TanqueCombustible (vacío) con la capacidad especificada.
	*
	*@param capacidad La capacidad del tanque.
	*
	*/
	public TanqueCombustible(int capacidad) {
			super();
			this.setCapacidad(capacidad);
			this.setCantidadCombustible(0);
	}

	/**
	*
	*Devuelve la capacidad del Tanque.
	*
	*@return la capacidad del Tanque
	*/
	public double getCapacidad(){
		return capacidad;
	}

	private void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	*
	*Devuelve la cantidad de {@link Combustible} que le queda.
	*
	*@return la cantidad de {@link Combustible} restante
	*@see Combustible
	*/
	public double getCantidadCombustible() {
		return cantidadCombustible;
	}

	protected void setCantidadCombustible(double cantidadCombustible){
		this.cantidadCombustible = cantidadCombustible;
	}

	/**
	*
	* Carga el Tanque.
	*
	*@param litros Los litros a cargar en el tanque, del tipo de Combustible
	*       que almacena el Tanque.
	*
	*@see Combustible
	*/
	public void llenarTanque(float litros) {
		if (litros > 0) {
			if ((litros + this.getCantidadCombustible()) <= this.getCapacidad())
				this.setCantidadCombustible(getCantidadCombustible()+ litros);
			else
				this.setCantidadCombustible(this.getCapacidad());
		}
	}

	/**
	*
	* Le entrega combustible a quien se lo solicite.
	*
	*@param litros los litros de combustible a entregar.
	*@return litros de combustible que pudo entregar.
	*@see Combustible
	*/
	public double usarCombustible(double litros){
		double combustibleConsumo = 0;
		if(this.getVidaUtil() > 0){
			if (litros <= this.getCantidadCombustible() && litros > 0){
				this.cantidadCombustible -= litros;
				combustibleConsumo = litros;
			}else if(litros > this.getCantidadCombustible()){
				combustibleConsumo = this.getCantidadCombustible();
				this.cantidadCombustible = 0;
			}
		}
		return combustibleConsumo;
	}

	/**
	*
	* Calcula el peso del Tanque Combutible de acuerdo a su cantidad de combustible.
	*
	*@return el peso del tanque y su contenido
	*@see PartesAuto
	*@see Combustible
	*/
	public abstract double getPeso();

	public boolean desgastar(int tiempo) {
		setVidaUtil(getVidaUtil()-tiempo/1000);
		return desgastado();
	}

}
