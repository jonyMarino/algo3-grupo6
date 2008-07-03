package proveedores;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import proveedores.proveedorDeCombustibles.Combustible;
import proveedores.proveedorDePartes.fabricas.RegistroDeModelos;

import excepciones.BoundsException;

public class InformacionDeProducto {
	private Hashtable<String,String> informacion= new Hashtable<String, String>();

	/**
	 * Constructor por defecto
	 */
	public InformacionDeProducto(){}
	/**
     * Crea una nueva instancia de InformacionCombustible, y le agrega las
     * caracter�sticas especificadas. Los valores se inicializan vac�os.
     *
     * @param listaDeCaracteristicasDisponibles
     */
	public InformacionDeProducto(ArrayList<String> listaDeCaracteristicasDisponibles) {
		Iterator<String> iteradorDeCaracteristicas = listaDeCaracteristicasDisponibles.iterator();
		while(iteradorDeCaracteristicas.hasNext()){
			aniadirCaracteristica(iteradorDeCaracteristicas.next(), "");
		}	
	}

	/**
     * Devuelve una lista de caracter�sticas disponibles para este modelo
     * de combustible.
     * @return Un Arraylist de Strings que representan las caracter�sticas de este modelo.
     * @see Combustible
     */	

	public ArrayList<String> getCaracteristicasDisponibles() {
		Enumeration<String> listaDeClaves = informacion.keys();
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
	 
	protected void aniadirCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		informacion.put(nombreDeLaCaracteristica, valorDeLaCaracteristica);
	}
	 
    /**
     * Devuelve el valor de la caracter�stica solicitada.
     * @param caracteristica El nombre de la caracter�stica.
     * @return Un String con el valor de la caracter�stica.
     * @exception BoundsException
     */
	public String getCaracteristica(String caracteristica) throws BoundsException {
		if(informacion.containsKey(caracteristica)){
			return informacion.get(caracteristica);
		}
		else throw new BoundsException("El combustible no posee la caracter�stica "+ caracteristica + ".");

	}
	 
    /**
     * Cambia el valor de una caracter�stica.
     * @param nombre El nombre de la caracter�stica.
     * @param valor El nuevo valor de la caracter�stica
     * @exception BoundsException
     */
	protected void ponerCaracteristica(String nombre, String valor) throws BoundsException {
		if(informacion.containsKey(nombre)){
			informacion.put(nombre, valor);
		}
		else throw new BoundsException("El combustible no posee la caracter�stica "+ nombre + ".");
	}
	
    /**
     *Crea un String que describe el objeto
     * 
     * @return Un String con el valor de la caracter�stica "DESCRIPCION"
     */
	public String toString() {
		return informacion.toString();
	}

}
