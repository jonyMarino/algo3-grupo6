package auto.partesAuto;
import auto.ParteAuto;

/**
 * El escape es necesario para el funcionamiento del motor.
 * Un escape con buen rendimiento, ayuda a evacuar mejor los gases de la combustión y mejoran el desempeño del motor.
 *@see  PartesAuto
 */
public class Escape extends ParteAuto{

	private double eficiencia;

	/**
	 * Crea un nuevo Escape con la eficiencia especificada.
	 * @param eficiencia La eficiencia del escape (0..100)
	 */
	public Escape(double eficiencia){
		super();
		try {
			setEficiencia(eficiencia);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
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
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/getEficiencia());
		}catch(BoundsException e){
			try{
				setVidaUtil(0);			
			}catch(BoundsException f){}
		}
		 
		 return desgastado();
	}


}

