package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import excepciones.NoSuchModelException;
import excepciones.NotInIndexException;

import auto.ParteAuto;

public abstract class FabricaDePartes {

	protected Hashtable<Integer, Hashtable<String, String>> informacionDeModelos;
	private int ultimoNumeroUsado;
	
	abstract public ParteAuto fabricar(int modelo);

	//abstract public ArrayList getModelos();
	//abstract public Integer consultarPrecio(int modelo) throws NoSuchModelException;
	
	public Integer consultarPrecio(int modelo) throws NoSuchModelException {
		try {
			return Integer.parseInt(getInformacionModelo(getModelo(modelo), "COSTO"));
		} catch (NotInIndexException e) {
			e.printStackTrace(); //se supone que nunca llega a este punto, la existencia de COSTO, se verifica en agregarModelo();  
		}
		return null;		//tampoco debería llegar a este punto
	}
	
	public abstract String[] caracteristicasDisponibles();
	
	public String obtenerCaracteristica(int modelo, String caracteristica) throws NotInIndexException, NoSuchModelException{
		return getInformacionModelo(getModelo(modelo), caracteristica);
	}
	
	public ArrayList getModelos() {
		Enumeration<Integer> modelos = informacionDeModelos.keys();
		
		ArrayList<Integer> listaDeModelos = new ArrayList<Integer>();
		
		while(modelos.hasMoreElements()){
			listaDeModelos.add(new Integer(modelos.nextElement()));
		}
		
		return listaDeModelos;
	}
	
	public void eliminarModelo(int modelo) throws NoSuchModelException{
		getModelo(modelo); //si el modelo no existe lanza una excepcion
		informacionDeModelos.remove(modelo);
		
	}
	
	abstract public void agregarModelo(Hashtable<String, String> informacionDelModelo) throws NotInIndexException;
	
	protected Hashtable<String, String> getModelo(int numeroDeModelo) throws NoSuchModelException{
		if(informacionDeModelos.containsKey(numeroDeModelo))
			return informacionDeModelos.get(numeroDeModelo);
		else throw new NoSuchModelException("La fabrica no conoce el modelo de parte especificada.");
	}
	
	protected String getInformacionModelo(Hashtable<String, String> modelo, String clave) throws NotInIndexException{
			if(modelo.containsKey(clave))
				return modelo.get(clave);
			else throw new NotInIndexException("El modelo dado no especifíca ningún parametro llamado " + clave + ".");
	}
	
	private void validarModelo(Hashtable<String, String> datosDelModelo, String[] clavesNecesarias) throws NotInIndexException{
		for(int i=0;i< clavesNecesarias.length; i++)
			getInformacionModelo(datosDelModelo, clavesNecesarias[i]);
			 //se verifica que las claves existan, para que el modelo no quede incompleto
	}
	
	protected int nuevoModelo(Hashtable<String, String> datosDelModelo, String[] clavesNecesarias) throws NotInIndexException{
		validarModelo(datosDelModelo, clavesNecesarias);
		
		Hashtable<String, String> nuevoModelo = new Hashtable<String, String>();
			for(int i=0;i< clavesNecesarias.length; i++)
				nuevoModelo.put(clavesNecesarias[i], getInformacionModelo(datosDelModelo, clavesNecesarias[i]));
			ultimoNumeroUsado++;
			informacionDeModelos.put(ultimoNumeroUsado, nuevoModelo);
			return ultimoNumeroUsado;
	}
}
