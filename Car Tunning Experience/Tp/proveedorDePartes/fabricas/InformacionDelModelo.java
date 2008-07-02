package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;

/**
 * Clase que guarda una descripcion de un determinado modelo de {@link ParteAuto}
 * que entienden las {@link FabricaDePartes}, y que utilizan para fabricar las mismas
 *
 * @see FabricaDePartes
 */

public class InformacionDelModelo {
	private String modelo;
	private Hashtable<String,String> informacionDeEstaParte;

    /**
     * Devuelve una lista de las características necesarias para describir
     * este modelo.
     *
     * @returns Un {@link ArrayList} de {@link String}s que representa las caracterìsticas
     * de este modelo
     */
	  
	public ArrayList<String> getCaracteristicasDisponibles() {
		Enumeration<String> listaDeClaves = informacionDeEstaParte.keys();
		ArrayList<String> listaCaracteristicas = new ArrayList<String>();
		while(listaDeClaves.hasMoreElements())
			listaCaracteristicas.add(listaDeClaves.nextElement());
		return listaCaracteristicas;
	}
	 
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		informacionDeEstaParte.put(nombreDeLaCaracteristica, valorDeLaCaracteristica);
	}
	 
    /**
     * Devuelve el valor de la característica especificada
     *
     * @param caracteristica La característica en cuestion
     * @returns un String que representa el valor de la característica
     *
     */
        public String getCaracteristica(String caracteristica) throws BoundsException {
		if(informacionDeEstaParte.containsKey(caracteristica)){
			return informacionDeEstaParte.get(caracteristica);
		}
		else throw new BoundsException("El modelo no posee la característica "+ caracteristica + ".");

	}
	 
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		if(informacionDeEstaParte.containsKey(nombre)){
			informacionDeEstaParte.put(nombre, valor);
		}
		else throw new BoundsException("El modelo no posee la característica "+ nombre + ".");
	}
	 
	InformacionDelModelo(String modelo,ArrayList<String> listaDeCaracteristicasDisponibles) {
		Iterator<String> iteradorDeCaracteristicas = listaDeCaracteristicasDisponibles.iterator();
		informacionDeEstaParte = new Hashtable<String, String>();
		while(iteradorDeCaracteristicas.hasNext()){
			agregarCaracteristica(iteradorDeCaracteristicas.next(), "");
		}
	}
	
	InformacionDelModelo(String modelo){
		informacionDeEstaParte = new Hashtable<String, String>();
		setModelo(modelo);
	}

	public String toString() {
		try {
			return getCaracteristica("DESCRIPCION");
		} catch (BoundsException e) {
			return super.toString();
		}
	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
 
