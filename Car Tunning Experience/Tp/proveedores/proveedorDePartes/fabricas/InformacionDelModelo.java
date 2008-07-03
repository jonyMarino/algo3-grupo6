package proveedores.proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import proveedores.InformacionDeProducto;

import excepciones.BoundsException;
import excepciones.ModelRegisteredException;

/**
 * Clase que guarda una descripcion de un determinado modelo de {@link ParteAuto}
 * que entienden las {@link FabricaDePartes}, y que utilizan para fabricar las mismas.
 * @see FabricaDePartes
 */

public class InformacionDelModelo extends InformacionDeProducto{
	private String modelo;


	 

	InformacionDelModelo(String modelo,ArrayList<String> listaDeCaracteristicasDisponibles) {
		super(listaDeCaracteristicasDisponibles);
		setModelo(modelo);		

		RegistroDeModelos.getInstance().registrarModelo(this);
	}
	
	InformacionDelModelo(String modelo){
		setModelo(modelo);		
		RegistroDeModelos.getInstance().registrarModelo(this);	
	}
	
	public String toString() {
		return modelo + super.toString();
	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		aniadirCaracteristica(nombreDeLaCaracteristica,valorDeLaCaracteristica);
	}
	
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		ponerCaracteristica(nombre,valor);
	}

}
 
