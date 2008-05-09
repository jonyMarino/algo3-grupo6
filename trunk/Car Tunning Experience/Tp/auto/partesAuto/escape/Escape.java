package auto.parteAuto.escape;
import auto.ParteAuto;

public class Escape extends ParteAuto{
	
	private double eficiencia;
	
	public Escape(double peso,double costo,double desgaste){
		super(peso,costo,desgaste);
		setEficiencia(100);
	}
	
	public void setEficiencia(double eficiencia){
		this.eficiencia=eficiencia;
	}
	
	public double getEficiencia(){
		return this.eficiencia;
	}

}
