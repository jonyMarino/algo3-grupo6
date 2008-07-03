package proveedores.proveedorDeCombustibles;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import proveedores.InformacionDeProducto;

import excepciones.BoundsException;

/**
 * Clase que contiene los parámetros necesarios para describir un tipo de Combustible.
 * Es utilizado por la Fabricas de Combustible para producirlo.
 * @see Combustible
 * @see FabricaDeCombustible
 */

public class InformacionCombustible extends InformacionDeProducto{

	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		aniadirCaracteristica(nombreDeLaCaracteristica,valorDeLaCaracteristica);
	}
	
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		ponerCaracteristica(nombre,valor);
	}
	
}