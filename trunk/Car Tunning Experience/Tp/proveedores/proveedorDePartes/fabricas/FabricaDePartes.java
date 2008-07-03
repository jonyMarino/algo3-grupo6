package proveedores.proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

import programaAuto.ProveedorDePartes;

/**
 *
 *Clase abstracta que define la funcionalidad básica que deben implementar
 *las distintas fabricas.
 *
 *@see InformacionDelModelo
 *@see Proveedor
 */
public abstract class FabricaDePartes {
 
	private ArrayList<InformacionDelModelo> modelosConocidos;
	 
	private ProveedorDePartes proveedorDePartes;
	
    /**
     *Crea una nueva fabrica.
     *
     */
	public FabricaDePartes() {
		modelosConocidos = new ArrayList<InformacionDelModelo>();
	}
	
    /**
     *Fabrica la {@link ParteAuto} que especifica el modelo dado.
     *
     *@param modelo Una instancia de {@link InformacionDelModelo} que describe la parte
     *
     *@return La {@link ParteAuto} fabricada
     *
     *@exception NoSuchModelException
     */
	public abstract ParteAuto fabricar(InformacionDelModelo modelo) throws NoSuchModelException;

    /**
     *
     * Dado un determinado modelo, devuelve su costo de fabricación.
     *
     * @param modelo La instancia de {@link InformacionDelModelo} que describe la parte en cuestion
     *
     * @return El costo de la parte
     */
	public Integer consultarPrecio(InformacionDelModelo modelo) {
		return null;
	}
	 
    /**
     *
     * Devuelve una lista con los modelos que la fabrica es capaz de fabricar
     *
     * @return Un {@link ArrayList} de {@link InformacionDelModelo}
     */
	public ArrayList<InformacionDelModelo> getModelos() {
		return modelosConocidos;
	}
	 
    /**
     *
     *
     */
	public void eliminarModelo(InformacionDelModelo modelo) {
	}

    /**
     *
     *
     */	 
	public void agregarModelo(InformacionDelModelo datosDelModelo) {
		Iterator<InformacionDelModelo> iteradorModelos = getModelos().iterator();
		ArrayList<String> claves = datosDelModelo.getCaracteristicasDisponibles();
		boolean encontrado = false;
		
		while(iteradorModelos.hasNext() && !encontrado){
			Iterator<String> iteradorClaves = claves.iterator();
			InformacionDelModelo modeloAComparar = iteradorModelos.next();
			encontrado = true;
			while(iteradorClaves.hasNext() && encontrado){
				try{
					if(modeloAComparar.getCaracteristica(iteradorClaves.next()) != datosDelModelo.getCaracteristica(iteradorClaves.next())){
						encontrado=false;
						break;
					}
				}catch(BoundsException e){ encontrado=false; break;}
						
			}
				
		}
			if(!encontrado)
			modelosConocidos.add(datosDelModelo);
			
	}
	 
}
 
