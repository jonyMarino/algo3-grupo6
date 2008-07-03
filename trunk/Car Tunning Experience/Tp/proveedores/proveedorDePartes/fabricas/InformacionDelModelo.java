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
 

	InformacionDelModelo(String modelo,ArrayList<String> listaDeCaracteristicasDisponibles) {
		super(modelo,listaDeCaracteristicasDisponibles);


		RegistroDeModelos.getInstance().registrar(this);
	}
	
	InformacionDelModelo(String modelo){
		super(modelo);

		RegistroDeModelos.getInstance().registrar(this);	
	}
	


	public String toString() {
		return getModelo() + super.toString();
	}

	public String getModelo() {
		return getNombre();
	}
	
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		aniadirCaracteristica(nombreDeLaCaracteristica,valorDeLaCaracteristica);
	}
	
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		ponerCaracteristica(nombre,valor);
	}

}
 
