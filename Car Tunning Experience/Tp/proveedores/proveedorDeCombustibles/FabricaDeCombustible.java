package proveedores.proveedorDeCombustibles;

import java.util.ArrayList;
import java.util.Iterator;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
*
* Clase abstracta que define la funcionalidad b�sica que deben implementar
* las distintas fabricas de combustible.
*
*/

public abstract class FabricaDeCombustible {
	
	 
		private ArrayList<InformacionCombustible> tiposConocidos;
		 
	    /**
	     *Crea una nueva fabrica.
	     *
	     */

		public FabricaDeCombustible() {
			tiposConocidos = new ArrayList<InformacionCombustible>();
		}
	
            /**
	     *Fabrica el tipo de combustible que se le especifica.
	     *
	     *@exception NoSuchModelException
	     */
		public abstract Combustible fabricar(InformacionCombustible modelo) throws NoSuchModelException;

    /**
     * Dado un tipo de combustible, devuelve su precio unitario
     *
     *@param modelo Una instancia de InformacionCombustible que describe el tipo de combustible
     *@see Combustible
     *@see InformacionCombustible
     */
		public Integer consultarPrecio(InformacionCombustible modelo) {
			return null;
		}
		 
	    /**
	     *
	     * Devuelve una lista con los tipos de Combustible que la fabrica es capaz de fabricar
	     *
	     * @return Un ArrayList de los tipos de combustible que se pueden fabricar
	     * @see Combustible
	     * @see InformacionCombustible
	     */
	  
		public ArrayList<InformacionCombustible> getTipos() {
			return tiposConocidos;
		}
		 
	 
		public void agregarTipo(InformacionCombustible datosDelTipo) {
			Iterator<InformacionCombustible> iteradorModelos = getTipos().iterator();
			ArrayList<String> claves = datosDelTipo.getCaracteristicasDisponibles();
			boolean encontrado = false;
			
			while(iteradorModelos.hasNext() && !encontrado){
				Iterator<String> iteradorClaves = claves.iterator();
				InformacionCombustible modeloAComparar = iteradorModelos.next();
				encontrado = true;
				while(iteradorClaves.hasNext() && encontrado){
					try{
						if(modeloAComparar.getCaracteristica(iteradorClaves.next()) != datosDelTipo.getCaracteristica(iteradorClaves.next())){
							encontrado=false;
							break;
						}
					}catch(BoundsException e){ encontrado=false; break;}
							
				}
					
			}
				if(!encontrado)
				tiposConocidos.add(datosDelTipo);
				
		}
		 
	}

