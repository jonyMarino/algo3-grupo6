package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Escapes
 *
 * @see FabricaDePartes
 * @see Escape
 *
 */

public class FabricaDeEscapes extends FabricaDePartes {
	static int modeloNumero;
	public FabricaDeEscapes(){
		super();
		agregarModelo(nuevoModeloEscape("Turbo DH100",50, "Escape básico.", 80, 5.0)); //agrega un escape básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloEscape(String modelo,Integer costo, String descripcion, Integer rendimiento, Double peso){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo(modelo);
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("EFICIENCIA", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
		nuevaInfo.agregarCaracteristica("PARTE", "ESCAPE");
		return nuevaInfo;
	}
	
	public Escape fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Escape");
		try {
			if (modelo.getCaracteristica("PARTE") != "ESCAPE")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		try{
			int eficiencia = Integer.parseInt(modelo.getCaracteristica("EFICIENCIA"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Escape unEscape = new Escape(eficiencia);
			unEscape.setDescripcion(descripcion);
			unEscape.setCosto(costo);
			unEscape.setPeso(peso);
			unEscape.setInformacionDelModelo(modelo);
			return unEscape;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	
	public void proponerEscape(String descripcion, int rendimiento, double peso) throws BoundsException{
		if(rendimiento>100 || rendimiento<0)
			throw new BoundsException("El rendimiento solicitado es invalido.");
		if(peso < 0.5)
			throw new BoundsException("El peso no puede ser menor a 0.5 kilos");
		int costo = (int) (rendimiento/peso)*4;
		String modelo = "Escape "+modeloNumero++;
		agregarModelo(nuevoModeloEscape(modelo,costo, descripcion, rendimiento, peso));
	}

}
 
