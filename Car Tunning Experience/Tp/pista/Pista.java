package pista;

public class Pista {
	
	
	private double coeficienteDeRozamientoRelativo;
	private double longitud;
	private int velocidadAire;
	
	public Pista(double longitud){
		setLongitud(longitud);
		setVelocidadAire(0);
		setCoeficienteDeRozamientoRelativo(0);
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
	
	
	public void setVelocidadAire(int velocidadAire) {
		this.velocidadAire=velocidadAire;
	}
	
	public int getVelocidadAire(){
		return this.velocidadAire;
	}

}
