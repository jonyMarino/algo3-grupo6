package proveedores.proveedorDeCombustibles;

import proveedores.InformacionDeProducto;
import proveedores.RegistroProducto;

/**
 * Guarda un registro de combustibles al igual que se registra un modelo en la realidad.
 *
 */
public class RegistroDeCombustibles extends RegistroProducto{

		static RegistroDeCombustibles instance=null;
		
		public static RegistroDeCombustibles getInstance() {
			if(instance==null)
				instance = new RegistroDeCombustibles();
			return instance;
		}
		
		void registrar(InformacionCombustible informacion){
			registrar((InformacionDeProducto) informacion);
		}
		
		public  InformacionCombustible getInformacion(String modeloRegistrado) {
			return (InformacionCombustible)super.getInformacion(modeloRegistrado);
		}
}
