package auto.partesAuto;
import auto.PartesAuto;

/**
 * El escape es necesario para el funcionamiento del motor.
 * Un escape con buen rendimiento, ayuda a evacuar mejor los gases de la combustión y mejoran el desempeño del motor.
 *@see  PartesAuto
 */
public class Escape extends PartesAuto{

	private double eficiencia;

	/**
	 * Crea un nuevo Escape con la eficiencia especificada.
	 * @param eficiencia La eficiencia del escape (0..100)
	 */
	public Escape(double eficiencia){
		super();
		setEficiencia(eficiencia);
	}

	public void setEficiencia(double eficiencia)throws BoundsException{
		if(eficiencia < 0 || eficiencia > 100)
			throw new BoundsException("Valor de eficiencia de escape");
		this.eficiencia = eficiencia;
	}

	public double getEficiencia(){
		return this.eficiencia;
	}

	public boolean desgastar(int tiempo){
		 setVidaUtil(getVidaUtil()-tiempo/getEficiencia());
		 return desgastado();
	}


}

