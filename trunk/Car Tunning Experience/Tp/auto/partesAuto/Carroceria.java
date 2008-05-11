package auto.partesAuto;
import auto.ParteAuto;
import pista.Pista;
import java.awt.Color;


public class Carroceria extends ParteAuto{
	
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
	
	public double getFuerzaAire(Pista pista){
		return pista.getVelocidadAire()/getAeroDinamia();
	}

}
