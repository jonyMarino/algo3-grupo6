package proveedores.proveedorDePartes.fabricas;

import java.util.Hashtable;

import excepciones.ModelRegisteredException;
/**
 * Guarda un registro de modelos al igual que se registra un modelo en la realidad.
 *
 */
public class RegistroDeModelos {
	private Hashtable<String,InformacionDelModelo> tablaModelo=new Hashtable<String,InformacionDelModelo>();
	private static RegistroDeModelos instance=null;
	
	public static RegistroDeModelos getInstance(){
		if(instance==null)
			instance = new RegistroDeModelos();
		return instance;
	}
	
	public void registrarModelo(InformacionDelModelo informacion){
		String modelo = informacion.getModelo();
		if(tablaModelo.containsKey(modelo))
			throw new ModelRegisteredException();
		tablaModelo.put(modelo,informacion);
	}
	
	public String[] modelosRegistrados(){
		return (String[])tablaModelo.keySet().toArray();
	}
	
	public InformacionDelModelo getInformacion(String modeloRegistrado){
		return tablaModelo.get( modeloRegistrado);
	}

}
