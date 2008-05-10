package auto.partesAuto.carroceria;
import auto.ParteAuto;
import pista.Pista;


public class Carroceria extends ParteAuto{
	
	private String color;
	private double volumen;
	private int aeroDinamia;

	public Carroceria(double volumen,int aeroDinamia){
		super();
		setVolumen(volumen);
		setAeroDinamia(aeroDinamia);
		setColor("#FFFFFF");
	}
	
	public void setColor(String color){
		this.color=color;
	}
	
	public String getColor(){
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
