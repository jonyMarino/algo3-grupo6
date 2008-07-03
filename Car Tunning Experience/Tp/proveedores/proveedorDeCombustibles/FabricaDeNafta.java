package proveedores.proveedorDeCombustibles;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Clsae particular de FabricaCombustible, que fabrica Nafta
 * @see Nafta
 * @see FabricaDeCombustible
 */
public class FabricaDeNafta extends FabricaDeCombustible{
	

    /**
     *
     * Crea una fabrica de Nafta.
     * @see Nafta
     * 
     */
	public FabricaDeNafta() {
	
		agregarTipo(nuevoTipoNafta("Fangio 2000",600,50.0));
	}

	
	private InformacionCombustible nuevoTipoNafta(String nombre,int octanaje,Double costo){
		InformacionCombustible nuevaInfo = new InformacionCombustible(nombre);
		nuevaInfo.agregarCaracteristica("OCTANAJE",Integer.toString(octanaje));
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		
		return nuevaInfo;
	}
	
    /**
     * Fabrica el tipo de nafta especificado.
     * @param modelo El tipo de Nafta a fabricar
     * @return Una instancia de la Nafta pedida.
     * @exception NoSuchModelException
     */
	public Nafta fabricar(InformacionCombustible modelo) throws NoSuchModelException{
		NoSuchModelException unaExcepcion = new NoSuchModelException("No es un tipo de Nafta");
		Nafta nafta;
		try{
			double costo = Double.parseDouble(modelo.getCaracteristica("COSTO"));
			int octanaje = Integer.parseInt(modelo.getCaracteristica("OCTANAJE"));
			nafta = new Nafta(octanaje,costo);

			nafta.setInformacionCombustible(modelo);
			return nafta;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	

}
