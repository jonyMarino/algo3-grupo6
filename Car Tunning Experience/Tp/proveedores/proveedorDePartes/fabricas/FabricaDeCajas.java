package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.ModelRegisteredException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Cajas
 *
 * @see FabricaDePartes
 * @see Caja
 *
 */
public class FabricaDeCajas extends FabricaDePartes {
 
	public FabricaDeCajas(){
		super();
		agregarModelo(nuevoModeloCaja("Ford NT10",200, "Caja Manual b�sica.", 100.0, "MANUAL"));
		agregarModelo(nuevoModeloCaja("Nisan 123",200, "Caja Autom�tica b�sica.", 80.0, "AUTOMATICA"));
	}
	
	private InformacionDelModelo nuevoModeloCaja(String modelo,Integer costo, String descripcion, Double peso, String clase){
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
		nuevaInfo.agregarCaracteristica("PARTE", "CAJA");
		return nuevaInfo;
	}
	
	public Caja fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es una Caja");
		try {
			if (modelo.getCaracteristica("PARTE") != "CAJA")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		try{
			Caja caja;
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			try{
				modelo.getCaracteristica("MANUAL");
				caja = new CajaManual();
				caja.setDescripcion(descripcion);
				caja.setCosto(costo);
				caja.setPeso(peso);
				caja.setInformacionDelModelo(modelo);
				return caja;		
			}catch(BoundsException e){
				caja = new CajaAutomatica();
				caja.setDescripcion(descripcion);
				caja.setCosto(costo);
				caja.setPeso(peso);
				caja.setInformacionDelModelo(modelo);
				return caja;		
			}
		}catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	 
}
 
