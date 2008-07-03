package programaAuto;

import java.util.ArrayList;
import java.util.Iterator;

import proveedores.proveedorDePartes.fabricas.FabricaDePartes;
import proveedores.proveedorDePartes.fabricas.InformacionDelModelo;
import proveedores.proveedorDePartes.fabricas.ParteAuto;
import proveedores.proveedorDePartes.fabricas.RegistroDeModelos;
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

public class CadenaDeFabricas{

	private ArrayList<FabricaDePartes> miCadenaDeFabricas;
	
    /**
     *Crea una nueva cadena de fabricas vacía.
     *
     */	
        public CadenaDeFabricas() {
		miCadenaDeFabricas = new ArrayList<FabricaDePartes>();
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
	public ParteAuto fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		Iterator<FabricaDePartes> iteradorCadena = miCadenaDeFabricas.iterator();
		boolean fabricado = false;
		ParteAuto unaParte = null;
		
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
	
	public ParteAuto fabricar(String modelo) throws NoSuchModelException {
		return fabricar(RegistroDeModelos.getInstance().getInformacion(modelo));
	}

    /**
     * Agrega una fábrica a la cadena.
     *
     * @param unaFabrica La {@link FabricaDePartes} a agregar
     */
	public void agregarFabrica(FabricaDePartes unaFabrica) {
		miCadenaDeFabricas.add(unaFabrica);
		
	}

    /**
     *Devuelve una lista de todos los modelos que esta {@link CadenaDeFabricas}
     *es capaz de hacer fabricar.
     *
     * @return Un {@link ArrayList} que contiene los modelos que se pueden fabricar.
     */
	public ArrayList<InformacionDelModelo> getModelos() {
		Iterator<FabricaDePartes> iteradorCadena = miCadenaDeFabricas.iterator();
		ArrayList<InformacionDelModelo> catalogo = new ArrayList<InformacionDelModelo>();
		while(iteradorCadena.hasNext()){
			catalogo.addAll(iteradorCadena.next().getModelos());
		}
		return catalogo;
	}


    /**
     *Devuelve una lista de las fábricas que integran esta cadena
     *
     *@return Un {@link Arraylist} con las fabricas integrantes.
     */
	public ArrayList<FabricaDePartes> getMiCadenaDeFabricas() {
		return miCadenaDeFabricas;
	}
}
