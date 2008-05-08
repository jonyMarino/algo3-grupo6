package pista;

public class Pista {
	
	private double coeficienteDeRozamientoRelativo;
	private double longitud;

	public Pista(){
		setLongitud(0);
	}
	
	public void setLongitud(double longitud) {
		this.longitud=longitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
	
	public void setCoeficienteDeRozamientoRelativo(double coeficienteDeRozamientoRelativo) {
		this.coeficienteDeRozamientoRelativo=coeficienteDeRozamientoRelativo;
	}
	
	public double getCoeficienteDeRozamientoRelativo() {
		return this.coeficienteDeRozamientoRelativo;
	}

}
