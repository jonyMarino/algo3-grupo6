package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.BoundsException;
/*
 * getCaracteristicasDisponibles(), getCaracteristica() Deben ser final porque:
 * "[...]no queremos que nadie defina clases derivadas por razones de seguridad. "
 * ("Piensa en Java" Capitulo 7 seccion "Clases final") 
 * e "impedir que cualquier clase que herede de ésta cambie su significado."
 * ("Piensa en Java" Capitulo 7 seccion "Metodos final").
 * Dado que la razon de hacer fabricas y que estas instancien InformacionDelModelo
 * es por seguridad a que nadie instancie partes donde no debemos, las condiciones 
 * citadas son aplicables. 
 */
//TODO: getCaracteristicasDisponibles() y getCaracteristica() a final
public class InformacionDelModelo {
 
	private Hashtable<String,String> informacionDeEstaParte;
	  
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

	/*
	 * No queremos que otros puedan cambiar los atributos de la instancia de InformacionDelModelo
	 * public Hashtable<String, String> getInformacionDeEstaParte() {
		
		return (Hashtable<String, String>)informacionDeEstaParte;
	}
	 */
}
 
