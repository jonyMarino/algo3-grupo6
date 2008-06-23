package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeMotores extends FabricaDePartes {
	public FabricaDeMotores(){
		super();
		agregarModelo(nuevoModeloMotor(600, "Motor b�sico.", 80, 200.0, 5800.0, 2.0)); //agrega un motor b�sico al cat�logo
	}
	
	private InformacionDelModelo nuevoModeloMotor(Integer costo, String descripcion, Integer rendimiento, Double peso, Double rpmmax, Double cilindrada){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("RENDIMIENTO", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("RPMMAX", rpmmax.toString());
		nuevaInfo.agregarCaracteristica("CILINDRADA", cilindrada.toString());
		return nuevaInfo;
	}
	
	public Motor fabricar(InformacionDelModelo modelo) {
		try{
			double rpmmaximo = Double.parseDouble(modelo.getCaracteristica("RPMMAX"));
			int rendimiento = Integer.parseInt(modelo.getCaracteristica("RENDIMIENTO"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double cilindrada = Double.parseDouble(modelo.getCaracteristica("CILINDRADA"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Motor unMotor = new Motor(rendimiento, rpmmaximo, cilindrada);
			unMotor.setDescripcion(descripcion);
			unMotor.setCosto(costo);
			unMotor.setPeso(peso);
			unMotor.setInformacionDelModelo(modelo);
			return unMotor;
		}
		catch(BoundsException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void proponerMotor(String descripcion, int rendimiento, double peso, double rpmmax, double cilindrada) throws BoundsException{
		if(rendimiento>100 || rendimiento<0)
			throw new BoundsException("El rendimiento solicitado es invalido.");
		if(peso < 80)
			throw new BoundsException("El peso no puede ser menor a 80 kilos");
		int costo = (int) (rendimiento*rpmmax*cilindrada/peso)*2;
		agregarModelo(nuevoModeloMotor(costo, descripcion, rendimiento, peso, rpmmax, cilindrada));
	}
	 
	
	 
}
 
