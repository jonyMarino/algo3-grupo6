package proveedores.proveedorDeCombustibles;

import java.util.ArrayList;
import java.util.Iterator;
import excepciones.NoSuchModelException;

/**
 *
 * Cadena de responsabilidades. Dado un objeto {@link InformacionCombustible}
 * y dada una serie de {@link FabricaDecombustible}, intenta hacer fabricar el
 * combustible.
 *
 * @see ProveedorDeCombustible
 * @see InformacionCombustible
 */

public class CadenaDeFabricasCombustibles{

	private ArrayList<FabricaDeCombustible> miCadenaDeFabricas;
	
    /**
     *Crea una nueva cadena de fabricas vacía.
     *
     */	
        public CadenaDeFabricasCombustibles() {
		miCadenaDeFabricas = new ArrayList<FabricaDeCombustible>();
	}

    /**
     *
     * Intenta que alguna de las fabricas de la cadena fabrique el combustible
     * en cuestion.
     * 
     */
	public Combustible fabricar(InformacionCombustible modelo) throws NoSuchModelException {
		Iterator<FabricaDeCombustible> iteradorCadena = miCadenaDeFabricas.iterator();
		boolean fabricado = false;
		Combustible unaParte = null;
		
		while(iteradorCadena.hasNext() && !fabricado){
			try {
				unaParte = iteradorCadena.next().fabricar(modelo);
				fabricado = true;
			} catch (NoSuchModelException e) {
				fabricado=false;
			}
			catch(NullPointerException e){
				fabricado = false;
			}
		}
		
		if (fabricado)
			return unaParte;
		else throw new  NoSuchModelException("Ninguna de las fábricas sabe como fabricar ese modelo.");
	}

    /**
     * Agrega una fábrica a la cadena.
     *
     * @param unaFabrica La {@link FabricaDeCombustible} a agregar
     */
	public void agregarFabrica(FabricaDeCombustible unaFabrica) {
		miCadenaDeFabricas.add(unaFabrica);
		
	}

    /**
     *Devuelve una lista de todos los tipos que esta {@link CadenaDeFabricasCombustibles}
     *es capaz de hacer fabricar.
     *
     */
	public ArrayList<InformacionCombustible> getModelos() {
		Iterator<FabricaDeCombustible> iteradorCadena = miCadenaDeFabricas.iterator();
		ArrayList<InformacionCombustible> catalogo = new ArrayList<InformacionCombustible>();
		while(iteradorCadena.hasNext()){
			catalogo.addAll(iteradorCadena.next().getTipos());
		}
		return catalogo;
	}


    /**
     *Devuelve una lista de las fábricas que integran esta cadena
     *
     *@return Un {@link Arraylist} con las fabricas integrantes.
     */
	public ArrayList<FabricaDeCombustible> getMiCadenaDeFabricas() {
		return miCadenaDeFabricas;
	}

}
