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
		if (longitud < 0)
			this.longitud = 0;
		else this.longitud = longitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
	
	public void setCoeficienteDeRozamientoRelativo(double coeficienteDeRozamientoRelativo) {
		if (coeficienteDeRozamientoRelativo > 1)
			this.coeficienteDeRozamientoRelativo=1;
		else if (coeficienteDeRozamientoRelativo < 0)
			this.coeficienteDeRozamientoRelativo=0;
		else this.coeficienteDeRozamientoRelativo=coeficienteDeRozamiento;
	}
	
	public double getCoeficienteDeRozamientoRelativo() {
		return this.coeficienteDeRozamientoRelativo;
	}
	
	
	public void setVelocidadAire(int velocidadAire) {
		if(velocidadAire>=0)
			this.velocidadAire=velocidadAire;
		else this.velocidadAire=0;
	}
	
	public int getVelocidadAire(){
		return this.velocidadAire;
	}

}
