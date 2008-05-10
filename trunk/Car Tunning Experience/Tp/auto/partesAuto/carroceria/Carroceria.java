package auto.partesAuto.carroceria;
import auto.ParteAuto;

public class Carroceria extends ParteAuto{
	
	private String color;
	private double volumen;
	private int aeroDinamica;

	public Carroceria(double volumen){
		super();
		setVolumen(volumen);
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
	
	public void setAeroDinamica(int aeroDinamica){
		this.aeroDinamica=aeroDinamica;
	}
	
	public double getAeroDinamica(){
		return this.aeroDinamica;
	}
	
	public double getFuerzaAire(){
		//falta el calculo
	}
	
	

}
