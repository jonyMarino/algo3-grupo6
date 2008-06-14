package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Iterator;

import proveedorDePartes.ProveedorDePartes;


public abstract class FabricaDePartes {
 
	private ArrayList<InformacionDelModelo> modelosConocidos;
	 
	private ProveedorDePartes proveedorDePartes;
	
	public FabricaDePartes() {
		modelosConocidos = new ArrayList<InformacionDelModelo>();
	}
	
	public abstract ParteAuto fabricar(InformacionDelModelo modelo);
	public Integer consultarPrecio(InformacionDelModelo modelo) {
		return null;
	}
	 
	public ArrayList<InformacionDelModelo> getModelos() {
		return modelosConocidos;
	}
	 
	public void eliminarModelo(InformacionDelModelo modelo) {
	}
	 
	public void agregarModelo(InformacionDelModelo datosDelModelo) {
		Iterator<InformacionDelModelo> iteradorModelos = getModelos().iterator();
		ArrayList<String> claves = datosDelModelo.getCaracteristicasDisponibles();
		boolean encontrado = false;
		
		while(iteradorModelos.hasNext() && !encontrado){
			Iterator<String> iteradorClaves = claves.iterator();
			InformacionDelModelo modeloAComparar = iteradorModelos.next();
			encontrado = true;
			while(iteradorClaves.hasNext() && encontrado){
				if(modeloAComparar.getCaracteristica(iteradorClaves.next()) != datosDelModelo.getCaracteristica(iteradorClaves.next())){
					encontrado=false;
					break;
				}
			}
		}
		if(!encontrado)
			modelosConocidos.add(datosDelModelo);
			
	}
	 
}
 