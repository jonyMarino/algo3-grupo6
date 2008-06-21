package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;

import proveedorDePartes.fabricas.FabricaDePartes;

public class InformacionDelModelo {
 
	private Hashtable<String,String> informacionDeEstaParte;
	 
	private FabricaDePartes fabricaDePartes;
	 
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
	 
	InformacionDelModelo(ArrayList<String> listaDeCaracteristicasDisponibles) {
		Iterator<String> iteradorDeCaracteristicas = listaDeCaracteristicasDisponibles.iterator();
		informacionDeEstaParte = new Hashtable<String, String>();
		while(iteradorDeCaracteristicas.hasNext()){
			agregarCaracteristica(iteradorDeCaracteristicas.next(), "");
		}
	}
	
	InformacionDelModelo(){
		informacionDeEstaParte = new Hashtable<String, String>();
	}

	public Hashtable<String, String> getInformacionDeEstaParte() {
		return informacionDeEstaParte;
	}
	 
}
 
