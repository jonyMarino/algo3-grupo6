package auto.partesAuto.carroceria;
import  auto.ParteAuto;

public class Carroceria extends ParteAuto{
	
	private String color;
	private double volumen;

	public Carroceria(){
		setVolumen(0);
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

}
