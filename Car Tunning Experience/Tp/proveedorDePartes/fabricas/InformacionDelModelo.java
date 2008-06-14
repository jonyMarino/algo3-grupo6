package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;

import proveedorDePartes.fabricas.FabricaDePartes;

public class InformacionDelModelo {
 
	private Hashtable<String,String> informacionDeEstaParte;
	 
	private FabricaDePartes fabricaDePartes;
	 
	public ArrayList<String> getCaracteristicasDisponibles() {
		return null;
	}
	 
	void agregarCaracteristica(String nombreDeLaCaracteristica, String valorDeLaCaracteristica) {
		informacionDeEstaParte.put(nombreDeLaCaracteristica, valorDeLaCaracteristica);
	}
	 
	public String getCaracteristica(String caracteristica) {
		return informacionDeEstaParte.get(caracteristica);
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
	 
}
 
