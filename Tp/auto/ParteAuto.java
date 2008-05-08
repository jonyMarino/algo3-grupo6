public class ParteAuto {
 
	private double peso;
	private double costo;
	private int vidaUtil;
	private int desgaste;
	private int vidaActual;

	public ParteAuto(){
		setPeso(0);
		setCosto(0);
		setVidaUtil(0);
		setVidaActual(0);
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
	
	public void setVidaUtil(int vidaUtil){
		this.vidaUtil=vidaUtil;
	}
	
	public int getVidaUtil(){
		return this.vidaUtil;
	}
	
	public void setDesgaste(){
		this.desgaste=5;   //necesito saber cuantas vuelta se corrieron a quien se las pido?
	}
	
	public int getDesgaste(){
		return this.desgaste;
	}
	
	public void setVidaActual(int vidaActual){
		this.vidaActual= getVidaUtil()-getDesgaste();
	}
	
	public int getVidaACtual(){
		return this.vidaActual;
	}
	
}
 
