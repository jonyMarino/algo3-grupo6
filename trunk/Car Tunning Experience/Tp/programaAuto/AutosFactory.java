package programaAuto;

import java.util.Hashtable;



import excepciones.IncorrectPartForUbicationException;
import excepciones.NotRegisteredCarException;
import excepciones.UbicationUnkownException;

import nu.xom.Element;

/**
 * Implementacion del patron de diseño "metodo de factoria"
 * La creacion del auto es a traves de un Element donde se 
 * halla guardado la subclase de Auto.
 *
 */
public class AutosFactory {
	Hashtable<String,AutoFactory> autos= new Hashtable<String,AutoFactory>();
	
	public Element getElement(Auto auto) throws NotRegisteredCarException{
		if( ! autos.containsKey(auto.getClass().getName()))
			throw new NotRegisteredCarException();
		Element factory=new Element("AutoFactory");
		Element tipo = new Element("tipo");
		tipo.appendChild(auto.getClass().getName());
		factory.appendChild(tipo);
		factory.appendChild(auto.getElement());
		
		return factory;
	}
	
	public Auto crear(CadenaDeFabricas fabrica,Element elemento){
		Element factory=elemento.getFirstChildElement("AutoFactory");
		String tipo = factory.getFirstChildElement("tipo").getValue();

		try {
			Auto auto = autos.get(tipo).crear(fabrica,factory);
			return auto;
		} catch (IncorrectPartForUbicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UbicationUnkownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void add(Class<? extends Auto> auto,AutoFactory factory){
		autos.put(auto.getName(), factory);
	}
	
}
