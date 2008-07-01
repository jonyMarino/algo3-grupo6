package proveedorDeCombustibles;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;

/**
 * Clase que contiene los par�metros necesarios para describir un tipo de Combustible.
 * Es utilizado por la Fabricas de Combustible para producirlo.
 * @see Combustible
 * @see FabricaDeCombustible
 */

public class InformacionCombustible {
	private Hashtable<String,String> informacionCombustible;

    /**
     * Crea una nueva descripccion vac�a.
     */	  
	public InformacionCombustible(){
		informacionCombustible = new Hashtable<String, String>();
	}

    /**
     * Devuelve una lista de caracter�sticas disponibles para este modelo
     * de combustible.
     * @return Un Arraylist de Strings que representan las caracter�sticas de este modelo.
     * @see Combustible
     */	

	public ArrayList<String> getCaracteristicasDisponibles() {
		Enumeration<String> listaDeClaves = informacionCombustible.keys();
		ArrayList<String> listaCaracteristicas = new ArrayList<String>();
		while(listaDeClaves.hasMoreElements())
			listaCaracteristicas.add(listaDeClaves.nextElement());
		return listaCaracteristicas;
	}

    /**
     * Agrega una caracter�stica al modelo.
     * @param nombreDeLaCaracteristica El nombre de la nueva caracter�stica
     * @param valorDeLaCaracteristica El valor de esa caracter�stica
     */
	 
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		informacionCombustible.put(nombreDeLaCaracteristica, valorDeLaCaracteristica);
	}
	 
    /**
     * Devuelve el valor de la caracter�stica solicitada.
     * @param caracteristica El nombre de la caracter�stica.
     * @return Un String con el valor de la caracter�stica.
     * @exception BoundsException
     */
	public String getCaracteristica(String caracteristica) throws BoundsException {
		if(informacionCombustible.containsKey(caracteristica)){
			return informacionCombustible.get(caracteristica);
		}
		else throw new BoundsException("El combustible no posee la caracter�stica "+ caracteristica + ".");

	}
	 
    /**
     * Cambia el valor de una caracter�stica.
     * @param nombre El nombre de la caracter�stica.
     * @param valor El nuevo valor de la caracter�stica
     * @exception BoundsException
     */
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		if(informacionCombustible.containsKey(nombre)){
			informacionCombustible.put(nombre, valor);
		}
		else throw new BoundsException("El combustible no posee la caracter�stica "+ nombre + ".");
	}
	 
    /**
     * Crea una nueva instancia de InformacionCombustible, y le agrega las
     * caracter�sticas especificadas. Los valores se inicializan vac�os.
     *
     * @param listaDeCaracteristicasDisponibles Un ArrayList de Strings con los nombres de las caracter�sticas.
     *
     */
	void InformacionCombutible(ArrayList<String> listaDeCaracteristicasDisponibles) {
		Iterator<String> iteradorDeCaracteristicas = listaDeCaracteristicasDisponibles.iterator();
		informacionCombustible = new Hashtable<String, String>();
		while(iteradorDeCaracteristicas.hasNext()){
			agregarCaracteristica(iteradorDeCaracteristicas.next(), "");
		}
	}

	
    /**
     *Crea un String que describe el objeto
     * 
     * @return Un String con el valor de la caracter�stica "DESCRIPCION"
     */
	public String toString() {
		try {
			return getCaracteristica("DESCRIPCION");
		} catch (BoundsException e) {
			return super.toString();
		}
	}

}