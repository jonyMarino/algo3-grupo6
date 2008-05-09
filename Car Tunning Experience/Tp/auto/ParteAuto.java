package auto;

public abstract class ParteAuto {
 
	private double peso;
	private double costo;
	private double vidaUtil;
	private double desgaste;

	public ParteAuto(double peso,double costo,double desgaste){
		setPeso(peso);
		setCosto(costo);
		setVidaUtil(100);
		setDesgaste(desgaste);
	}
	
	public void setPeso(double peso){
		this.peso=peso;
	}
	
	public double getPeso(){
		return this.peso;
	}
	
	public void setCosto(double costo){
		this.costo=costo;
	}
	
	public double getCosto(){
		return this.costo;
	}
	
	public void setVidaUtil(double vidaUtil){
		this.vidaUtil=vidaUtil;
	}
	
	public double getVidaUtil(){
		return this.vidaUtil;
	}
	
	public double calcularDesgaste(int tiempo){
		return (tiempo*getDesgaste());	
	}
	
	public void setDesgaste(double desgaste){
		this.desgaste=desgaste;	
	}
	
	public double getDesgaste(){
		return this.desgaste;
	}

	
}