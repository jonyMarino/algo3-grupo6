/**
 * 
 */
package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.ModelRegisteredException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar pedales
 *
 * @see FabricaDePartes
 * @see Freno
 *
 */
public class FabricaDePedales extends FabricaDePartes {

	public FabricaDePedales(){
		super();
		agregarModelo(nuevoModeloPedal("Freno TD101",10, "Freno.", 1.0, "FRENO"));
	}
	
	private InformacionDelModelo nuevoModeloPedal(String modelo,Integer costo, String descripcion, Double peso, String clase){
		InformacionDelModelo nuevaInfo = null;
		try{
			nuevaInfo = new InformacionDelModelo(modelo);
		}catch(ModelRegisteredException e)
		{
			return RegistroDeModelos.getInstance().getInformacion(modelo);	

		}
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica(clase, "");
		nuevaInfo.agregarCaracteristica("PARTE", "PEDAL");
		return nuevaInfo;
	}
	
	public Pedal fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Pedal");
		try {
			if (modelo.getCaracteristica("PARTE") != "PEDAL")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		try{
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Pedal unPedal;
			unPedal= new Freno(100);
			unPedal.setDescripcion(descripcion);
			unPedal.setCosto(costo);
			unPedal.setPeso(peso);
			unPedal.setInformacionDelModelo(modelo);
			return unPedal;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}

}
