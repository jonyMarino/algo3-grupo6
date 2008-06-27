package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;


public class FabricaDeCajas extends FabricaDePartes {
 
	public FabricaDeCajas(){
		super();
		agregarModelo(nuevoModeloMotor(200, "Caja Manual básica.", 100.0, "MANUAL"));
		agregarModelo(nuevoModeloMotor(200, "Caja Automática básica.", 10.0, "AUTOMATICA"));
	}
	
	private InformacionDelModelo nuevoModeloMotor(Integer costo, String descripcion, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
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
				return caja;		
			}
		}catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	 
}
 
