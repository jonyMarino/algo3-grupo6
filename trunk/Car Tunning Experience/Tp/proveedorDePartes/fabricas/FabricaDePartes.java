package proveedorDePartes.fabricas;

import java.util.ArrayList;

import proveedorDePartes.ProveedorDePartes;
import auto.ParteAuto;


/**
 *
 *	public ArrayList getModelos() {
 *		Enumeration<Integer> modelos = informacionDeModelos.keys();
 *		
 *		ArrayList<Integer> listaDeModelos = new ArrayList<Integer>();
 *		
 *		while(modelos.hasMoreElements()){
 *			listaDeModelos.add(new Integer(modelos.nextElement()));
 *		}
 *		
 *		return listaDeModelos;
 *	}
 *	
 */
public abstract class FabricaDePartes {
 
	private ArrayList<InformacionDelModelo> modelosConocidos;
	 
	private ProveedorDePartes proveedorDePartes;
	 
	private InformacionDelModelo[] informacionDelModelo;
	 
	public abstract ParteAuto fabricar(InformacionDelModelo modelo);
	public Integer consultarPrecio(InformacionDelModelo modelo) {
		return null;
	}
	 
	public ArrayList<InformacionDelModelo> getModelos() {
		return null;
	}
	 
	public void eliminarModelo(InformacionDelModelo modelo) {
	}
	 
	public void agregarModelo(InformacionDelModelo informacionDelModelo) {
	}
	 
}
 
