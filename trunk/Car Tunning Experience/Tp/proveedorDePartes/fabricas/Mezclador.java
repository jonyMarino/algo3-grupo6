package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.PartBrokenException;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link TanqueCombustible},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 *
 * @see Motor
 * @see PartesAuto
 * @see TanqueCombustible
 * @see Combustible
 * @see MezcladorNafta
 */

public abstract class Mezclador extends ParteAuto {

	private double            rendimiento;
    private TanqueCombustible tanque;

	/**
	* Crea un nuevo Mezclador.
	*
	* @param rendimiento El rendmiento con el que opera el Mezclador (0..100).
	* @param tanque El {@link TanqueCombustible} asignar al Mezclador.
	*/
	Mezclador(int rendimiento,TanqueCombustible tanque) {
		super();
		this.setRendimiento(rendimiento);
		this.setTanqueCombustible(tanque);
	}

	/**
	* Devuelve la cantidad de Mezcla pedida por el Motor. En caso de no poseer la
	* cantidad de nafta necesaria para realizar la Mezcla pedida, devuelve 0.
	*
	* @param litrosMezcla La cantidad de mezcla que le quiero pedir.
	*
	* @return cantidad de mezcla que pudo generar.
	*
	* @throws BoundsException 
	* @throws PartBrokenException
	*/
	public abstract double obtenerMezcla(double litrosMezcla) throws BoundsException,PartBrokenException;

	/**
	* Devuelve el rendimiento con el que opera el Mezclador.
	*
	* @return el rendimiento (0..100).
	*/
	public double getRendimiento(){
		return rendimiento;
	}

	/**
	 * Devuelve el {@link TanqueCombustible} asociado al Mezclador.
	 *
	 * @return El {@link TanqueCombustible} asociado.
	 */
    public TanqueCombustible getTanqueCombustible() {
    	return tanque;
	}

	/**
	 * Asigna un {@link TanqueCombustible} al Mezclador.
	 *
	 * @param El {@link TanqueCombustible} a asignar.
	 */
    public void setTanqueCombustible(TanqueCombustible tanque) {
	    this.tanque=tanque;
	}

	/**
	* Desgasta el {@link TanqueCombustible} segun el tiempo pasado por parametro.
	*
	* @param tiempo El tiempo ha desgastar.
	*  
	* @return true si esta desgastado, false caso contrario.
	*/
	public void desgastar(double tiempo) {
		try {
			if(getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/1000);
				setRendimiento(getRendimiento()-tiempo/1000);
			}
		} catch (BoundsException e) {
			try{
				setVidaUtil(0);
				setRendimiento(0);
			}catch(BoundsException f){}
		}
	}

	void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

}

