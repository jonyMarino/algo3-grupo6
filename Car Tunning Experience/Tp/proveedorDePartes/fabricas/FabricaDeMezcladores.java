package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeMezcladores extends FabricaDePartes {
	public FabricaDeMezcladores(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "200");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Mezclador de Nafta básico.");
 		nuevaInfo.agregarCaracteristica("RENDIMIENTO", "80");
		nuevaInfo.agregarCaracteristica("PESO", "30");
 		nuevaInfo.agregarCaracteristica("NAFTA", "");
		agregarModelo(nuevaInfo); //agrega un motor básico al catálogo
	}
	public Mezclador fabricar(InformacionDelModelo modelo) {
		try{
			int rendimiento = Integer.parseInt(modelo.getCaracteristica("RENDIMIENTO"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Mezclador unMezclador;
			try{
				modelo.getCaracteristica("NAFTA");
				unMezclador = new MezcladorNafta(rendimiento, null);
			}catch(BoundsException e){throw new BoundsException("No se sabe de que clase es el Mezclador.");};
			unMezclador.setDescripcion(descripcion);
			unMezclador.setCosto(costo);
			unMezclador.setPeso(peso);
			return unMezclador;
		}
		catch(BoundsException e){}
		return null;
	}
	 
}
 
