package proveedorDePartes.fabricas;

import auto.partesAuto.BoundsException;


public class fabricaDeMotores extends FabricaDePartes {
 
	public Motor fabricar(InformacionDelModelo modelo) {
		try{
			double rpmmaximo = Double.parseDouble(modelo.getCaracteristica("RPMMAX"));
			int rendimiento = Integer.parseInt(modelo.getCaracteristica("RENDIMIENTO"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double cilindrada = Double.parseDouble(modelo.getCaracteristica("CILINDRADA"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			Motor unMotor = new Motor(rendimiento, rpmmaximo, cilindrada);
			unMotor.setDescripcion(descripcion);
			unMotor.setCosto(costo);
			return unMotor;
		}
		catch(BoundsException e){}
		return null;
	}
	 
	public void agregarModelo(InformacionDelModelo datosDelModelo) {
	}
	 
}
 
