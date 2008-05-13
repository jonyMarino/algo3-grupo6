package pista;
import auto.Auto;
import java.util.*;

public class Pista {
		
	private class AutoPosicionado{
		double posicion=0;
		Auto auto;
		public AutoPosicionado(Auto auto){
			this.auto=auto;
		}
		public void setPosicion(double posicion){
			this.posicion=posicion;
		}
		public double getPosicion(){
			return this.posicion;
		}
	}
	private double coeficienteDeRozamientoRelativo;
	private double longitud;
	private int velocidadAire;
	private LinkedList<AutoPosicionado> autos;
	
	public Pista(double longitud){
		setLongitud(longitud);
		setVelocidadAire(0);
		setCoeficienteDeRozamientoRelativo(0);
		autos = new LinkedList<AutoPosicionado>();
	}
	
	public void addAuto(Auto auto){
		if(auto==null)
			return;
		autos.add(new AutoPosicionado(auto));
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
			this.coeficienteDeRozamientoRelativo = 1;
		else if (coeficienteDeRozamientoRelativo < 0)
			this.coeficienteDeRozamientoRelativo = 0;
		else this.coeficienteDeRozamientoRelativo = coeficienteDeRozamientoRelativo;
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
