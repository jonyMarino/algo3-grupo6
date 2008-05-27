package auto.partesAuto.mezclador;

import auto.ParteAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.tanque.TanqueCombustible;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link TanqueCombustible},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Motor
 * @see PartesAuto
 * @see TanqueCombustible
 */

public abstract class Mezclador extends ParteAuto{

	private int rendimiento;
        private TanqueCombustible tanque;

	/**
	* Crea un nuevo Mezclador.
	*
	* @param rendimiento El rendmiento con el que opera el Mezclador (0..100)
	*
	*/
	public Mezclador(int rendimiento,TanqueCombustible tanque) {
		super();
		this.setRendimiento(rendimiento);
		this.tanque = tanque;
	}

	/**
	*
	* Devuelve la cantidad de Mezcla pedida por el Motor. En caso de no poseer la
	* cantidad de nafta necesaria para realizar la Mezcla pedida, devuelve 0.
	*
	* @param litrosMezcla la cantidad de mezcla que le quiero pedir
	*
	* @return cantidad de mezcla que pudo generar
	* @throws BoundsException 
	*/
	public abstract double obtenerMezcla(double litrosMezcla) throws BoundsException;

	/**
	*
	* Devuelve el rendimiento con el que opera el Mezclador
	*
	* @return el rendimiento (0..100)
	*/
	public int getRendimiento(){
		return rendimiento;
	}

        public TanqueCombustible getTanqueCombustible(){
	    return tanque;
	}

        public void setTanqueCombustible(TanqueCombustible tanque){
	    this.tanque=tanque;
	}

	/**
	*
	* 
	*
	* @return 
	*/
	public boolean desgastar(int tiempo) {
		
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
		return desgastado();
	}

	private void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}
	
}

