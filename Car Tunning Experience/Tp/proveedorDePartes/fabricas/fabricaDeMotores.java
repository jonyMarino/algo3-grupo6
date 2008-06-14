package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import excepciones.NoSuchModelException;
import excepciones.NotInIndexException;

import auto.ParteAuto;
import auto.partesAuto.Motor;

public class fabricaDeMotores extends FabricaDePartes {

	//private Hashtable<Integer, Hashtable<String, String>> informacionDeModelos;
	private final String[] clavesNecesarias = {"COSTO", "CILINDRADA", "RPMMAX", "RENDIMIENTO", "DESCRIPCION" };

	public Motor fabricar(int modelo) {
		try{
		double cilindrada = Double.parseDouble(getInformacionModelo(getModelo(modelo), "CILINDRADA"));
		double rpmmax = Double.parseDouble(getInformacionModelo(getModelo(modelo), "RPMMAX"));
		int rendimiento = Integer.parseInt(getInformacionModelo(getModelo(modelo), "RENDIMIENTO"));
		Motor motor = new Motor(rendimiento, rpmmax, cilindrada);
		motor.setDescripcion(getInformacionModelo(getModelo(modelo), "DESCRIPCION"));
		int costo = Integer.parseInt((getInformacionModelo(getModelo(modelo), "COSTO")));
		motor.setCosto(costo);
		return motor;
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; //nunca llega aca

	}

	public void agregarModelo(Hashtable<String, String> datosDelModelo) throws NotInIndexException {
		nuevoModelo(datosDelModelo, clavesNecesarias);
	}

	public String[] caracteristicasDisponibles() {
		return clavesNecesarias;
	}
	
	
	/*	
	public Integer consultarPrecio(int modelo) throws NoSuchModelException {
		if(informacionDeModelos.containsKey(modelo)){
			return Integer.parseInt((informacionDeModelos.get(modelo).get("PRECIO")));
		}
		else throw new NoSuchModelException("La fabrica no conoce el modelo de parte pedida.");
	}
*/

/*
	public ArrayList getModelos() {
		Enumeration<Integer> modelos = informacionDeModelos.keys();
		
		ArrayList<Integer> listaDeModelos = new ArrayList<Integer>();
		
		while(modelos.hasMoreElements()){
			listaDeModelos.add(new Integer(modelos.nextElement()));
		}
		
		return listaDeModelos;
	}
	*/

}
