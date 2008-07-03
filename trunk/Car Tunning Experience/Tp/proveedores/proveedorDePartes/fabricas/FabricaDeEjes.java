package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.ModelRegisteredException;
import excepciones.NoSuchModelException;


/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Ejes
 *
 * @see FabricaDePartes
 * @see Eje
 *
 */

public class FabricaDeEjes extends FabricaDePartes {
 
	public FabricaDeEjes() {
		super();
		InformacionDelModelo nuevaInfo = null;
		String modelo = "Renault SD100";
		try{
			nuevaInfo = new InformacionDelModelo(modelo);
			nuevaInfo.agregarCaracteristica("COSTO", "90");
			nuevaInfo.agregarCaracteristica("DESCRIPCION", "Eje básico.");
			nuevaInfo.agregarCaracteristica("PESO", "40");
			nuevaInfo.agregarCaracteristica("PARTE", "EJE");
		}catch (ModelRegisteredException e){
			nuevaInfo = RegistroDeModelos.getInstance().getInformacion(modelo);
		}
		finally{
			agregarModelo(nuevaInfo); 
		}
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
 
