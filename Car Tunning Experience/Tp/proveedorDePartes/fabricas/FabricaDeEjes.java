package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;


public class FabricaDeEjes extends FabricaDePartes {
 
	public FabricaDeEjes() {
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "90");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Eje básico.");
		nuevaInfo.agregarCaracteristica("PESO", "40");
		nuevaInfo.agregarCaracteristica("PARTE", "EJE");
		agregarModelo(nuevaInfo); //agrega uneje básico
	}
	
	public Eje fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Eje");
		try {
			if (modelo.getCaracteristica("PARTE") != "EJE")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		try{
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			Eje unEje = new Eje(null);
			unEje.setDescripcion(descripcion);
			unEje.setCosto(costo);
			unEje.setPeso(peso);
			unEje.setInformacionDelModelo(modelo);
			return unEje;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	 
}
 
