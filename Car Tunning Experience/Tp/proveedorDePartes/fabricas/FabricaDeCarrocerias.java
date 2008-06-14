package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeCarrocerias extends FabricaDePartes {
 
	public FabricaDeCarrocerias() {
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "100");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Carroceria básica.");
		nuevaInfo.agregarCaracteristica("PESO", "50");
		nuevaInfo.agregarCaracteristica("AERODINAMIA", "10");
		nuevaInfo.agregarCaracteristica("VOLUMEN", "5");
		agregarModelo(nuevaInfo); //agrega una carroceria básica al catálogo
	}
	
	public Carroceria fabricar(InformacionDelModelo modelo) {
		try{
			double volumen = Double.parseDouble(modelo.getCaracteristica("VOLUMEN"));
			int aerodinamia = Integer.parseInt(modelo.getCaracteristica("AERODINAMIA"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			Carroceria unaCarroceria = new Carroceria(volumen, aerodinamia, peso);;
			unaCarroceria.setDescripcion(descripcion);
			unaCarroceria.setCosto(costo);
			return unaCarroceria;
		}
		catch(BoundsException e){}
		return null;
	}
	 
}
 
