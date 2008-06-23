package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeCajas extends FabricaDePartes {
 
	public FabricaDeCajas(){
		super();
		agregarModelo(nuevoModeloMotor(200, "Caja básica.", 100.0, "MANUAL"));
		agregarModelo(nuevoModeloMotor(200, "Caja básica.", 10.0, "AUTOMATICA"));
	}
	
	private InformacionDelModelo nuevoModeloMotor(Integer costo, String descripcion, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica(clase, "");
		return nuevaInfo;
	}
	
	public Caja fabricar(InformacionDelModelo modelo) {
		Caja caja;
		try{
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
		}
		catch(BoundsException e){
			e.printStackTrace();
		}
		return null;
	}
	 
}
 
