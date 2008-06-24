package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeCarrocerias extends FabricaDePartes {
 
	public FabricaDeCarrocerias() {
		super();
		agregarModelo(nuevoModeloCarroceria(100, "Carrocería básica.", 50.0, 10, 5.0)); //agrega una carroceria básica al catálogo
	}
	
	private InformacionDelModelo nuevoModeloCarroceria(Integer costo, String descripcion, Double peso, Integer aerodinamia, Double volumen){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("AERODINAMIA", aerodinamia.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("VOLUMEN", volumen.toString());
		return nuevaInfo;
	}
	
	public Carroceria fabricar(InformacionDelModelo modelo) throws BoundsException {
		try{
			double volumen = Double.parseDouble(modelo.getCaracteristica("VOLUMEN"));
			int aerodinamia = Integer.parseInt(modelo.getCaracteristica("AERODINAMIA"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			Carroceria unaCarroceria = new Carroceria(volumen, aerodinamia, peso);;
			unaCarroceria.setDescripcion(descripcion);
			unaCarroceria.setCosto(costo);
			unaCarroceria.setPeso(peso);
			unaCarroceria.setInformacionDelModelo(modelo);
			return unaCarroceria;
		}
		catch(BoundsException e){
			throw e;	
		}
	}
	
	public void proponerCarroceria(String descripcion, double peso, int aerodinamia, double volumen) throws BoundsException{
		if(peso < 10)
			throw new BoundsException("El peso no puede ser menor a 10 kilos");
		int costo = (int) (aerodinamia/(peso*volumen))*5000;
		agregarModelo(nuevoModeloCarroceria(costo, descripcion,peso,  aerodinamia, volumen));
	}
	 
}
 
