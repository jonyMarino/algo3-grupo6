package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Mezcladores
 *
 * @see FabricaDePartes
 * @see Mezclador
 *
 */

public class FabricaDeMezcladores extends FabricaDePartes {
	public FabricaDeMezcladores(){
		super();
		agregarModelo(nuevoModeloMezclador(200, "Mezclador básico.", 80, 30.0, "NAFTA"));
		//agrega un motor básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloMezclador(Integer costo, String descripcion, Integer rendimiento, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("RENDIMIENTO", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica(clase, "");
		nuevaInfo.agregarCaracteristica("PARTE", "MEZCLADOR");
		return nuevaInfo;
	}
	
	public Mezclador fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Mezclador");
		try {
			if (modelo.getCaracteristica("PARTE") != "MEZCLADOR")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
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
			unMezclador.setInformacionDelModelo(modelo);
			return unMezclador;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}

	}
	
	public void proponerMezclador(String descripcion, int rendimiento, double peso, String clase) throws BoundsException{
		if(rendimiento>100 || rendimiento<0)
			throw new BoundsException("El rendimiento solicitado es invalido.");
		if(peso < 2)
			throw new BoundsException("El peso no puede ser menor a 2 kilos");
		int costo = (int) (rendimiento/peso)*500;
		agregarModelo(nuevoModeloMezclador(costo, descripcion, rendimiento, peso, clase));
	}
}
 
