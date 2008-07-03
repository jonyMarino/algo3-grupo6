package proveedores.proveedorDePartes.fabricas;

import proveedores.InformacionDeProducto;
import proveedores.RegistroProducto;



/**
 * Guarda un registro de modelos al igual que se registra un modelo en la realidad.
 *
 */
public class RegistroDeModelos extends RegistroProducto {
	static RegistroDeModelos instance=null;
	
	public static RegistroDeModelos getInstance() {
		if(instance==null)
			instance = new RegistroDeModelos();
		return instance;
	}
	
	void registrar(InformacionDelModelo informacion){
		registrar((InformacionDeProducto) informacion);
	}
	
	public  InformacionDelModelo getInformacion(String modeloRegistrado) {
		return (InformacionDelModelo)super.getInformacion(modeloRegistrado);
	}
}
