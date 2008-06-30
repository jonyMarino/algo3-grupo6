package proveedorDeNafta;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;

public class InformacionCombustible {
	private Hashtable<String,String> informacionCombustible;
	  
	public InformacionCombustible(){
		informacionCombustible = new Hashtable<String, String>();
	}
	
	public ArrayList<String> getCaracteristicasDisponibles() {
		Enumeration<String> listaDeClaves = informacionCombustible.keys();
		ArrayList<String> listaCaracteristicas = new ArrayList<String>();
		while(listaDeClaves.hasMoreElements())
			listaCaracteristicas.add(listaDeClaves.nextElement());
		return listaCaracteristicas;
	}
	 
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		informacionCombustible.put(nombreDeLaCaracteristica, valorDeLaCaracteristica);
	}
	 
	public String getCaracteristica(String caracteristica) throws BoundsException {
		if(informacionCombustible.containsKey(caracteristica)){
			return informacionCombustible.get(caracteristica);
		}
		else throw new BoundsException("El combustible no posee la característica "+ caracteristica + ".");

	}
	 
	void setCaracteristica(String nombre, String valor) throws BoundsException {
		if(informacionCombustible.containsKey(nombre)){
			informacionCombustible.put(nombre, valor);
		}
		else throw new BoundsException("El combustible no posee la característica "+ nombre + ".");
	}
	 
	void InformacionCombutible(ArrayList<String> listaDeCaracteristicasDisponibles) {
		Iterator<String> iteradorDeCaracteristicas = listaDeCaracteristicasDisponibles.iterator();
		informacionCombustible = new Hashtable<String, String>();
		while(iteradorDeCaracteristicas.hasNext()){
			agregarCaracteristica(iteradorDeCaracteristicas.next(), "");
		}
	}

	
	public String toString() {
		try {
			return getCaracteristica("DESCRIPCION");
		} catch (BoundsException e) {
			return super.toString();
		}
	}

}