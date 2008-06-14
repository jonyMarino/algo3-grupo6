package proveedorDePartes.fabricas;

import java.util.ArrayList;

import proveedorDePartes.ProveedorDePartes;
import auto.ParteAuto;


public abstract class FabricaDePartes {
 
	private ArrayList<InformacionDelModelo> modelosConocidos;
	 
	private ProveedorDePartes proveedorDePartes;
	 
	public abstract ParteAuto fabricar(InformacionDelModelo modelo);
	public Integer consultarPrecio(InformacionDelModelo modelo) {
		return null;
	}
	 
	public ArrayList<InformacionDelModelo> getModelos() {
		return modelosConocidos;
	}
	 
	public void eliminarModelo(InformacionDelModelo modelo) {
	}
	 
	public void agregarModelo(InformacionDelModelo informacionDelModelo) {
	}
	 
}
 
