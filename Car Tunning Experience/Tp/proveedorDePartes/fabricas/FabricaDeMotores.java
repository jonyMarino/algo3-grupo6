package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Motores
 *
 * @see FabricaDePartes
 * @see Motor
 *
 */

public class FabricaDeMotores extends FabricaDePartes {
	static int modeloNumero=1;
	public FabricaDeMotores(){
		super();
		agregarModelo(nuevoModeloMotor("General motors 2000",600, "Motor básico.", 80, 200.0, 5800.0, 2.0)); //agrega un motor básico al catálogo
	}
	
	private InformacionDelModelo nuevoModeloMotor(String modelo,Integer costo, String descripcion, Integer rendimiento, Double peso, Double rpmmax, Double cilindrada){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo(modelo);
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("DESCRIPCION", descripcion);
 		nuevaInfo.agregarCaracteristica("RENDIMIENTO", rendimiento.toString());
		nuevaInfo.agregarCaracteristica("PESO", peso.toString());
 		nuevaInfo.agregarCaracteristica("RPMMAX", rpmmax.toString());
		nuevaInfo.agregarCaracteristica("CILINDRADA", cilindrada.toString());
		nuevaInfo.agregarCaracteristica("PARTE", "MOTOR");
		return nuevaInfo;
	}
	
	public Motor fabricar(InformacionDelModelo modelo) throws NoSuchModelException{
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es un Motor");
		try {
			if (modelo.getCaracteristica("PARTE") != "MOTOR")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
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
			throw 	unaExcepcion;
		}
	}
	
	public void proponerMotor(String descripcion, int rendimiento, double peso, double rpmmax, double cilindrada) throws BoundsException{
		if(rendimiento>100 || rendimiento<0)
			throw new BoundsException("El rendimiento solicitado es invalido.");
		if(peso < 80)
			throw new BoundsException("El peso no puede ser menor a 80 kilos");
		int costo = (int) (rendimiento*rpmmax*cilindrada/peso)*2;
		String modelo = "Motor "+modeloNumero++;
		agregarModelo(nuevoModeloMotor(modelo,costo, descripcion, rendimiento, peso, rpmmax, cilindrada));
	}
	 
	
	 
}
 
