package auto.partesAuto.escape;
import auto.ParteAuto;

public class Escape extends ParteAuto{
	
	private double eficiencia;
	
	public Escape(double eficiencia){
		super();
		setEficiencia(eficiencia);
	}
	
	public void setEficiencia(double eficiencia){
		if((eficiencia>=0)&&(eficiencia<=100))
			this.eficiencia=eficiencia;
		else 
			this.eficiencia=0;
	}
	
	public double getEficiencia(){
		return this.eficiencia;
	}

}
