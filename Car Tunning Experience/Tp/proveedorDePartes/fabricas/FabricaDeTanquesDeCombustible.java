package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeTanquesDeCombustible extends FabricaDePartes {
 
	public FabricaDeTanquesDeCombustible(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "100");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Tanque de Nafta básico.");
 		nuevaInfo.agregarCaracteristica("CAPACIDAD", "70");
		nuevaInfo.agregarCaracteristica("PESO", "10");
		nuevaInfo.agregarCaracteristica("NAFTA", "");
		agregarModelo(nuevoModeloTanque(100, "Tanque de Nafta básico.", 70, 10.0, "NAFTA")); //agrega un motor básico al catálogo
	}
	
	
	private InformacionDelModelo nuevoModeloTanque(Integer costo, String descripcion, Integer capacidad, Double peso, String clase){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("CAPACIDAD", capacidad.toString());
		nuevaInfo.agregarCaracteristica(clase, "");
		
		return nuevaInfo;
	}
	
	public TanqueNafta fabricar(InformacionDelModelo modelo) {
		try{
			int capacidad = Integer.parseInt(modelo.getCaracteristica("CAPACIDAD"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			TanqueNafta unTanque;
			try{
				modelo.getCaracteristica("NAFTA");
				unTanque = new TanqueNafta(capacidad, null);
			}catch(BoundsException e){throw new BoundsException("No se sabe de que clase es el Tanque.");};
			unTanque.setDescripcion(descripcion);
			unTanque.setCosto(costo);
			unTanque.setPeso(peso);
			return unTanque;
		}
		catch(BoundsException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void proponerMotor(String descripcion, int capacidad, double peso, String clase) throws BoundsException{
		if(capacidad < 10)
			throw new BoundsException("La capacidad no puede ser menor a 10 litros");
		if(peso < 5)
			throw new BoundsException("El peso no puede ser menor a 5 kilos");
		int costo = (int) (capacidad/peso)*60;
		agregarModelo(nuevoModeloTanque(costo, descripcion, capacidad, peso, clase));
	}
	 
}
 
