package proveedorDeCombustibles;

import java.util.ArrayList;
import java.util.Iterator;
import excepciones.NoSuchModelException;

/**
 *
 * Cadena de responsabilidades. Dado un objeto {@link InformacionDelModelo}
 * y dada una serie de {@link FabricaDePartes}, intenta hacer fabricar la
 * parte.
 *
 * @see ProveedorDePartes
 * @see InformacionDelModelo
 */

public class CadenaDeFabricasCombustibles{

	private ArrayList<FabricaDeCombustible> miCadenaDeFabricas;
	
    /**
     *Crea una nueva cadena de fabricas vac�a.
     *
     */	
        public CadenaDeFabricasCombustibles() {
		miCadenaDeFabricas = new ArrayList<FabricaDeCombustible>();
	}

    /**
     *
     * Intenta que alguna de las fabricas de la cadena fabrique la parte en cuestion
     *
     *@param modelo Una instancia de InformacionDelModelo que describe la parte.
     *
     *@return La {@link ParteAuto} fabricada.
     *
     *@exception NoSuchModelException
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
		else throw new  NoSuchModelException("Ninguna de las f�bricas sabe como fabricar ese modelo.");
	}

    /**
     * Agrega una f�brica a la cadena.
     *
     * @param unaFabrica La {@link FabricaDeCombustible} a agregar
     */
	public void agregarFabrica(FabricaDeCombustible unaFabrica) {
		miCadenaDeFabricas.add(unaFabrica);
		
	}

    /**
     *Devuelve una lista de todos los modelos que esta {@link CadenaDeFabricas}
     *es capaz de hacer fabricar.
     *
     * @return Un {@link ArrayList} que contiene los modelos que se pueden fabricar.
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
     *Devuelve una lista de las f�bricas que integran esta cadena
     *
     *@return Un {@link Arraylist} con las fabricas integrantes.
     */
	public ArrayList<FabricaDeCombustible> getMiCadenaDeFabricas() {
		return miCadenaDeFabricas;
	}

}
