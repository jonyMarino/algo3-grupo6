package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeEscapes extends FabricaDePartes {
	public FabricaDeEscapes(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "50");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Escape básico.");
		nuevaInfo.agregarCaracteristica("EFICIENCIA", "80");
		nuevaInfo.agregarCaracteristica("PESO", "5");
		agregarModelo(nuevaInfo); //agrega un escape básico al catálogo
	}
	public Escape fabricar(InformacionDelModelo modelo) {
		try{
			int eficiencia = Integer.parseInt(modelo.getCaracteristica("EFICIENCIA"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Escape unEscape = new Escape(eficiencia);
			unEscape.setDescripcion(descripcion);
			unEscape.setCosto(costo);
			unEscape.setPeso(peso);
			return unEscape;
		}
		catch(BoundsException e){}
		return null;
	}

}
 
