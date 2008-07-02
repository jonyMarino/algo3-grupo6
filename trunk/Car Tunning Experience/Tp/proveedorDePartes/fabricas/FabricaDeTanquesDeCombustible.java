package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;


/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar 
 * Tanques de combustible
 *
 * @see FabricaDePartes
 * @see TanqueDeCombustible
 *
 */

public class FabricaDeTanquesDeCombustible extends FabricaDePartes {
	static int modeloNumero=0;
	public FabricaDeTanquesDeCombustible(){
		super();
		agregarModelo(nuevoModeloTanque("Tanque 70.10",100, "Tanque de Nafta básico.",70, 10.0, "NAFTA")); //agrega un motor básico al catálogo
	}
	
	
	private InformacionDelModelo nuevoModeloTanque(String modelo,Integer costo, String descripcion, Integer capacidad, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo(modelo);
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("CAPACIDAD", capacidad.toString());
		nuevaInfo.agregarCaracteristica(clase, "");
		nuevaInfo.agregarCaracteristica("PARTE", "TANQUEDECOMBUSTIBLE");
		
		return nuevaInfo;
	}
	
	public TanqueNafta fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Tanque de Combustible");
		try {
			if (modelo.getCaracteristica("PARTE") != "TANQUEDECOMBUSTIBLE")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		
		try{
			int capacidad = Integer.parseInt(modelo.getCaracteristica("CAPACIDAD"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			TanqueNafta unTanque;
			try{
				modelo.getCaracteristica("NAFTA");
				unTanque = new TanqueNafta(capacidad, null);
			}catch(BoundsException e){throw new BoundsException("No se sabe de que clase es el Tanque.");};
			unTanque.setDescripcion(descripcion);
			unTanque.setCosto(costo);
			unTanque.setPeso(peso);
			unTanque.setInformacionDelModelo(modelo);
			return unTanque;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	
	public void proponerTanque(String descripcion, int capacidad, double peso, String clase) throws BoundsException{
		if(capacidad < 10)
			throw new BoundsException("La capacidad no puede ser menor a 10 litros");
		if(peso < 5)
			throw new BoundsException("El peso no puede ser menor a 5 kilos");
		int costo = (int) (capacidad/peso)*60;
		String modelo="Tanque "+modeloNumero++;
		agregarModelo(nuevoModeloTanque(modelo,costo, descripcion, capacidad, peso, clase));
	}
	 
}
 
