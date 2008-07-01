package proveedorDePartes.fabricas;
import excepciones.BoundsException;
import excepciones.PartBrokenException;

/**
 * El Pedal encapsula el comportamiento y las características de un pedal.
 *
 * @see PartesAuto
 */
public abstract class Pedal extends ParteAuto {

	private boolean usado;

	/**
	 * Crea un nuevo pedal.
	 */
	Pedal() {
		this.setUsado(false);
	}

	/**
	* Realiza la presion sobre el pedal.
	*
	* @param intensidad La intensidad con que se presiona.
	*
	* @throws BoundsException
	*/
	public abstract void presionar(double intensidad) throws PartBrokenException;

	/**
	* Produce el desgaste del pedal debido al uso y al tiempo.
	*
	* @param tiempo El tiempo que se desgasta.
	*/
	public void desgastar(double tiempo) {
		try{
			if(getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/100);
			}
		}
		catch(BoundsException e){
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}
	}

	/**
	 * Devuelve true si el pedal ha sido utilizado.
	 */
	public boolean isUsado() {
		return usado;
	}

	protected void setUsado(boolean usado) {
		this.usado = usado;
	}

}
