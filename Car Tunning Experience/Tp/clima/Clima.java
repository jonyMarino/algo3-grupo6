package clima;
public abstract class Clima {
	
	private double coeficienteDeRozamiento;
		
	public Clima(double coeficienteDeRozamiento,double velocidadAire){
		setCoeficienteDeRozamiento(coeficienteDeRozamiento);
	}
	
	public void setCoeficienteDeRozamiento(double coeficienteDeRozamiento) {
		this.coeficienteDeRozamiento=coeficienteDeRozamiento;
	}
	
	public double getCoeficienteDeRozamiento(){
		return this.coeficienteDeRozamiento;
	}


}
