package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeTanquesDeCombustible extends FabricaDePartes {
 
	public FabricaDeTanquesDeCombustible(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "100");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Tanque de Nafta básico.");
 		nuevaInfo.agregarCaracteristica("CAPACIDAD", "70");
		nuevaInfo.agregarCaracteristica("PESO", "10");
		nuevaInfo.agregarCaracteristica("NAFTA", "");
		agregarModelo(nuevaInfo); //agrega un motor básico al catálogo
	}
	public TanqueNafta fabricar(InformacionDelModelo modelo) {
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
			return unTanque;
		}
		catch(BoundsException e){}
		return null;
	}
	 
}
 
