/**
 * 
 */
package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 * @author lucas
 *
 */
public class FabricaDePedales extends FabricaDePartes {

	public FabricaDePedales(){
		super();
		agregarModelo(nuevoModeloMezclador(10, "Un acelerador.", 1.0, "ACELERADOR"));
		agregarModelo(nuevoModeloMezclador(10, "Un acelerador.", 1.0, "FRENO"));
		//agrega un motor básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloMezclador(Integer costo, String descripcion, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
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
			try{
				modelo.getCaracteristica("ACELERADOR");
				unPedal = new Acelerador(null);
				unPedal.setDescripcion(descripcion);
				unPedal.setCosto(costo);
				unPedal.setPeso(peso);
				unPedal.setInformacionDelModelo(modelo);
				return unPedal;
			}catch(BoundsException e){
				unPedal= new Freno(100);
				unPedal.setDescripcion(descripcion);
				unPedal.setCosto(costo);
				unPedal.setPeso(peso);
				unPedal.setInformacionDelModelo(modelo);
				return unPedal;
			}
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}

}
