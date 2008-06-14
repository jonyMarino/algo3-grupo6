package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeEjes extends FabricaDePartes {
 
	public FabricaDeEjes() {
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "90");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Eje b�sico.");
		nuevaInfo.agregarCaracteristica("PESO", "40");
		agregarModelo(nuevaInfo); //agrega uneje b�sico
	}
	
	public Eje fabricar(InformacionDelModelo modelo) {
		try{
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			Eje unEje = new Eje(null);
			unEje.setDescripcion(descripcion);
			unEje.setCosto(costo);
			unEje.setPeso(peso);
			return unEje;
		}
		catch(BoundsException e){}
		return null;
	}
	 
}
 
