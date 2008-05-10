package auto.partesAuto.carroceria;
import auto.ParteAuto;

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
		this.volumen=volumen;
	}
	
	public double getVolumen(){
		return this.volumen;
	}
	
	public void setAeroDinamia(int aeroDinamia){
		this.aeroDinamia=aeroDinamia;
	}
	
	public double getAeroDinamia(){
		return this.aeroDinamia;
	}
	
	public double getFuerzaAire(){
		//falta el calculo
	}
	
	

}
