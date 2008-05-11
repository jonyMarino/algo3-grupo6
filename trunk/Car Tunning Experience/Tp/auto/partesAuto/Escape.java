package auto.partesAuto;
import auto.ParteAuto;

public class Escape extends ParteAuto{
	
	private double eficiencia;
	
	public Escape(double eficiencia){
		super();
		setEficiencia(eficiencia);
	}
	
	public void setEficiencia(double eficiencia){
		if(eficiencia > 100))
			this.eficiencia = 100;
		else if(eficiencia < 0)
			this.eficiencia = 0;
		else
			this.eficiencia = eficiencia;
	}
	
	public double getEficiencia(){
		return this.eficiencia;
	}

}
