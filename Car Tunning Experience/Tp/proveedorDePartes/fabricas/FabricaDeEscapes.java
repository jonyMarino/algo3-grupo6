package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeEscapes extends FabricaDePartes {
	public FabricaDeEscapes(){
		super();
		agregarModelo(nuevoModeloEscape(50, "Escape básico.", 80, 5.0)); //agrega un escape básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloEscape(Integer costo, String descripcion, Integer rendimiento, Double peso){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("EFICIENCIA", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
		return nuevaInfo;
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
		catch(BoundsException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void proponerEscape(String descripcion, int rendimiento, double peso) throws BoundsException{
		if(rendimiento>100 || rendimiento<0)
			throw new BoundsException("El rendimiento solicitado es invalido.");
		if(peso < 0.5)
			throw new BoundsException("El peso no puede ser menor a 0.5 kilos");
		int costo = (int) (rendimiento/peso)*4;
		agregarModelo(nuevoModeloEscape(costo, descripcion, rendimiento, peso));
	}

}
 
