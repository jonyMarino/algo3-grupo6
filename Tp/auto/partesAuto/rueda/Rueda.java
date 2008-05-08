public class Rueda extends ParteAuto {
	
	private int rodado;
	private double coeficienteEstatico;
	private double coeficienteDinamico;
	
	public Rueda(){
		setRodado(0);
		setCoeficienteEstatico(0);
		setCoeficienteDinamico(0);
	}
	
	public void setRodado(int rodado){
		this.rodado=rodado;
	}
	
	public int getRodado(){
		return this.rodado;
	}
	
	public void setCoeficienteEstatico(double coeficienteEstatico){
		this.coeficienteEstatico=coeficienteEstatico;
	}
	
	public double getCoeficienteEstatico(){
		return this.coeficienteEstatico;
	}
	
	public void setCoeficienteDinamico(double coeficienteDinamico){
		this.coeficienteDinamico=coeficienteEstatico;
	}
	
	public double getCoeficienteDinamico(){
		return this.coeficienteDinamico;
	}

}
