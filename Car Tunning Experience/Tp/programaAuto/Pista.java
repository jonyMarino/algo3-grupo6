package programaAuto;
import auto.Auto;

import java.util.*;

import excepciones.BoundsException;
import excepciones.InvalidPistaNameException;

public class Pista {
	private String nombre;
	private double coeficienteDeRozamientoRelativo;
	private double longitud;
	private int velocidadAire;
	
	private LinkedList<Auto> autos;

	public Pista(String nombre)throws InvalidPistaNameException{
		setNombre(nombre);
		try {
			setVelocidadAire(0);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setCoeficienteDeRozamientoRelativo(0.8);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		autos = new LinkedList<Auto>();
	}

	void addAuto(Auto auto)throws NullPointerException{
		if(auto==null)
			throw new NullPointerException("Referencia a auto null.");
		autos.add(auto);
	}

	void setLongitud(double longitud) throws BoundsException {
		if(longitud<0)
			throw new BoundsException("Valor de la longitud incorrecto");
		this.longitud=longitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	void setCoeficienteDeRozamientoRelativo(double coeficienteDeRozamientoRelativo) throws BoundsException {
		if( (coeficienteDeRozamientoRelativo < 0)||(coeficienteDeRozamientoRelativo > 1) )
			throw new BoundsException("Valor del coeficiente de rozamient relativa incorrecto.");
		this.coeficienteDeRozamientoRelativo = coeficienteDeRozamientoRelativo;
	}

	public double getCoeficienteDeRozamientoRelativo() {
		return this.coeficienteDeRozamientoRelativo;
	}


	void setVelocidadAire(int velocidadAire) throws BoundsException {
		if(velocidadAire<0)
			throw new BoundsException("Valor de la velocidad del aire incorrecto");
		this.velocidadAire=velocidadAire;
	}

	public int getVelocidadAire(){
		return this.velocidadAire;
	}
	
	void setNombre(String nombre)throws InvalidPistaNameException{
		if(nombre=="")
			throw new InvalidPistaNameException();
		this.nombre=nombre;
	}
	public String getNombre(){
		return nombre;
	}

}
