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
			if(posicion<0)
				throw new BoundsException("Valor de la posicion incorrecto");
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
		setCoeficienteDeRozamientoRelativo(0.8);
		autos = new LinkedList<AutoPosicionado>();
	}

	public void addAuto(Auto auto){
		if(auto==null)
			return;
		autos.add(new AutoPosicionado(auto));
	}

	public void setLongitud(double longitud) {
		if(longitud<0)
			throw new BoundsException("Valor de la longitud incorrecto");
		this.longitud=longitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setCoeficienteDeRozamientoRelativo(double coeficienteDeRozamientoRelativo) {
		if( (coeficienteDeRozamientoRelativo < 0)||(coeficienteDeRozamientoRelativo > 1) )
			throw new BoundsException("Valor del coeficiente de rozamient relativa incorrecto.");
		this.coeficienteDeRozamientoRelativo = coeficienteDeRozamientoRelativo;
	}

	public double getCoeficienteDeRozamientoRelativo() {
		return this.coeficienteDeRozamientoRelativo;
	}


	public void setVelocidadAire(int velocidadAire) {
		if(velocidadAire<0)
			throw new BoundsException("Valor de la velocidad del aire incorrecto");
		this.velocidadAire=velocidadAire;
	}

	public int getVelocidadAire(){
		return this.velocidadAire;
	}

}
