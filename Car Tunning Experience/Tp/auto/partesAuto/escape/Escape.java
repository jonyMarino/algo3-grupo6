package auto.parteAuto.escape;
import auto.ParteAuto;

public class Escape extends ParteAuto{
	
	private double eficiencia;
	
	public Escape(double eficiencia){
		super();
		setEficiencia(eficiencia);
	}
	
	public void setEficiencia(double eficiencia){
		this.eficiencia=eficiencia;
	}
	
	public double getEficiencia(){
		return this.eficiencia;
	}

}
