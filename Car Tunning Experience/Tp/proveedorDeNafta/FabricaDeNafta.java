package proveedorDeNafta;

import java.util.ArrayList;
import java.util.Iterator;



import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Clase que se encarga de producir los diferentes tipos de combustible
 * necesarios para el funcionamiento de los {@link Auto}s.
 *
 * @see Nafta
 * @see Combustible
 * @see Auto
 */
//TODO:  ???? FabricaNafta o FabricaCombustible ????
public class FabricaDeNafta {
	
	private ArrayList<InformacionCombustible> tiposConocidos;
    /**
     *Crea una fabrica de combustible.
     *
     */
	public FabricaDeNafta() {
		tiposConocidos = new ArrayList<InformacionCombustible>();
		agregarTipo(nuevoTipoNafta(600,50.0));
	}
	
    /**
     *
     *  Devuelve el precio de la clase de nafta solicitada.
     *
     *@see InformacionCombustible
     */
	public Integer consultarPrecio(InformacionCombustible modelo) {
		return null;
	}
	 
	public ArrayList<InformacionCombustible> getTipos() {
		return tiposConocidos;
	}

	public void agregarTipo(InformacionCombustible datosDelModelo) {
		Iterator<InformacionCombustible> iteradorModelos = getTipos().iterator();
		ArrayList<String> claves = datosDelModelo.getCaracteristicasDisponibles();
		boolean encontrado = false;
		
		while(iteradorModelos.hasNext() && !encontrado){
			Iterator<String> iteradorClaves = claves.iterator();
			InformacionCombustible modeloAComparar = iteradorModelos.next();
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
			tiposConocidos.add(datosDelModelo);
			
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
