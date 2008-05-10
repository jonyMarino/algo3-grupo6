package clima;
public abstract class Clima {
	
	private double coeficienteDeRozamiento;
	
	public Clima(double coeficienteDeRozamiento){
		setCoeficienteDeRozamiento(coeficienteDeRozamiento);
	}
	
	public void setCoeficienteDeRozamiento(double coeficienteDeRozamiento) {
		this.coeficienteDeRozamiento=coeficienteDeRozamiento;
	}
	
	public double getCoeficienteDeRozamiento(){
		return this.coeficienteDeRozamiento;
	}

}
