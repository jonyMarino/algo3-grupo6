package proveedorDePartes.fabricas;
import excepciones.BoundsException;

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
	public abstract void presionar(double intensidad) throws BoundsException;

	/**
	* Produce el desgaste del pedal debido al uso y al tiempo.
	*
	* @param tiempo El tiempo que se desgasta.
	*/
	public boolean desgastar(int tiempo) {
		try{
			if(this.isUsado() && getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/1000);
			}
		}
		catch(BoundsException e){
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		}
		return desgastado();
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
