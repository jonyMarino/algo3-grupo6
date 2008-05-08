package auto.partesAuto.escape;
import  auto.ParteAuto;


public class Escape extends ParteAuto{
	
	private double eficiencia;
	
	public Escape(){
		setEficiencia(0);
	}
	 
	public void setEficiencia(double eficiencia){
		this.eficiencia=eficiencia;
	}
	
	public double getEficiencia(){
		return this.eficiencia;
	}

}
