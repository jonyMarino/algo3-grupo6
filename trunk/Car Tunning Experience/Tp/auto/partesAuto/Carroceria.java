package auto.partesAuto;
import auto.PartesAuto;
import pista.Pista;
import java.awt.Color;
import java.lang.Exception;
/**
 * La parte exterior del {@link Auto}.
 * Una carrocer�a aerodin�mica y liviana puede ayudar mucho a aumentear
 * la velocidad del auto, ya que la oposici�n que presente al viento ser� menor.
 *
 *@see PartesAuto
 *@see Auto
 */
public class Carroceria extends PartesAuto{

	private Color color;
	private double volumen;
	private int aeroDinamia;

	public Carroceria(double volumen,int aeroDinamia,double peso){
		super();
		setVolumen(volumen);
		setAeroDinamia(aeroDinamia);
		setColor(0,0,0);
		this.setPeso(peso);
	}

	public void setColor(int R,int G,int B)throws BoundsException{
		if (R<0 || R>255)
			throw new BoundsException("Valor de R incorrecto.");
		if( G<0 || G>255)
			throw new BoundsException("Valor de G incorrecto.");
		if(B<0 || B>255)
			throw new BoundsException("Valor de B incorrecto.");
		this.color=new Color(R,G,B);
	}

	public Color getColor(){
		return this.color;
	}

	public void setVolumen(double volumen)throws BoundsException{
		if(volumen<0)
			throw new BoundsException("Valor de volumen: "+volumen+" incorrecto.");
		this.volumen=volumen;
	}

	public double getVolumen(){
		return this.volumen;
	}

	public void setAeroDinamia(int aeroDinamia)throws BoundsException{
		if(aeroDinamia<0)
			throw new BoundsException("Valor de aerodinamia incorrecto.");
		this.aeroDinamia=aeroDinamia;
	}

	public double getAeroDinamia(){
		return this.aeroDinamia;
	}

	public boolean desgastar(int tiempo){
		setVidaUtil(getVidaUtil()-tiempo/100);
		return desgastado();
	}

	public double getFuerzaAire(Pista pista){
		if(this.getVidaUtil()>0)
			return pista.getVelocidadAire()/getAeroDinamia();
		else 
			return pista.getVelocidadAire();
	}

}
