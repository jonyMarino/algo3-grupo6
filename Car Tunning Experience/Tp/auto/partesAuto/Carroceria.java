package auto.partesAuto;
import auto.PartesAuto;
import pista.Pista;
import java.awt.Color;


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

	public Carroceria(double volumen,int aeroDinamia){
		super();
		setVolumen(volumen);
		setAeroDinamia(aeroDinamia);
		setColor(0,0,0);
	}
	
	public void setColor(int R,int G,int B){
		if (R>=0 && R<255 && G>=0 && G<255 && G>=0 && G<255)
			this.color=new Color(R,G,B);
		else 
			this.color=new Color(0,0,0);
			
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setVolumen(double volumen){
		if(volumen>0)
			this.volumen=volumen;
		else 
			this.volumen=0;
	}
	
	public double getVolumen(){
		return this.volumen;
	}
	
	public void setAeroDinamia(int aeroDinamia){
		if(aeroDinamia>0)
			this.aeroDinamia=aeroDinamia;
		else 
			this.aeroDinamia=0;
	}
	
	public double getAeroDinamia(){
		return this.aeroDinamia;
	}
	
	public double desgastar(int tiempo){
		return tiempo*getDesgaste();	
	}
	
	public double getFuerzaAire(Pista pista){
		if(this.getVidaUtil()>0)
		return pista.getVelocidadAire()/getAeroDinamia();
		else return pista.getVelocidadAire();
	}

}