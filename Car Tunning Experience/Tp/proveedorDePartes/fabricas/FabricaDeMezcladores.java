package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeMezcladores extends FabricaDePartes {
	public FabricaDeMezcladores(){
		super();
		agregarModelo(nuevoModeloMezclador(200, "Mezclador básico", 80, 30.0, "NAFTA"));
		//agrega un motor básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloMezclador(Integer costo, String descripcion, Integer rendimiento, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("RENDIMIENTO", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica(clase, "");
		return nuevaInfo;
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
			unMezclador.setInformacionDelModelo(modelo);
			return unMezclador;
		}
		catch(BoundsException e){
			e.printStackTrace();
		}
		return null;
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
 
