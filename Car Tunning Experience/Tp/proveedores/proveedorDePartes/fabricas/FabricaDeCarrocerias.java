package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Carrocerías
 *
 * @see FabricaDePartes
 * @see Carroceria
 *
 */

public class FabricaDeCarrocerias extends FabricaDePartes {
	static int modeloNumero=0;
	public FabricaDeCarrocerias() {
		super();
		agregarModelo(nuevoModeloCarroceria("Coupe 256",100, "Carrocería básica.", 50.0, 10, 5.0)); //agrega una carroceria básica al catálogo
	
	}
	
	private InformacionDelModelo nuevoModeloCarroceria(String modelo,Integer costo, String descripcion, Double peso, Integer aerodinamia, Double volumen){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo(modelo);
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("AERODINAMIA", aerodinamia.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("VOLUMEN", volumen.toString());
		nuevaInfo.agregarCaracteristica("PARTE", "CARROCERIA");
		return nuevaInfo;
	}
	
	public Carroceria fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es una Carroceria");
		try {
			if (modelo.getCaracteristica("PARTE") != "CARROCERIA")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
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
			throw unaExcepcion;
		}
	}
	
	public void proponerCarroceria(String descripcion, double peso, int aerodinamia, double volumen) throws BoundsException{
		if(peso < 10)
			throw new BoundsException("El peso no puede ser menor a 10 kilos");
		int costo = (int) (aerodinamia/(peso*volumen))*5000;
		String modelo ="Carroceria"+ modeloNumero++; 
		agregarModelo(nuevoModeloCarroceria(modelo,costo, descripcion,peso,  aerodinamia, volumen));
	}
	 
}
 
