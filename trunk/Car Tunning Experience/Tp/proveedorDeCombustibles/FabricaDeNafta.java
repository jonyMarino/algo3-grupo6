package proveedorDeCombustibles;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

public class FabricaDeNafta extends FabricaDeCombustible{
	

	public FabricaDeNafta() {
	
		agregarTipo(nuevoTipoNafta(600,50.0));
	}

	
	private InformacionCombustible nuevoTipoNafta(int octanaje,Double costo){
		InformacionCombustible nuevaInfo = new InformacionCombustible();
		nuevaInfo.agregarCaracteristica("OCTANAJE",Integer.toString(octanaje));
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		
		return nuevaInfo;
	}
	
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
