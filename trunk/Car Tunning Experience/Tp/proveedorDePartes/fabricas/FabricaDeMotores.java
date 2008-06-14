package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import excepciones.BoundsException;


public class FabricaDeMotores extends FabricaDePartes {
	public FabricaDeMotores(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "500");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Motor Basico");
		nuevaInfo.agregarCaracteristica("RPMMAX", "5800");
		nuevaInfo.agregarCaracteristica("RENDIMIENTO", "80");
		nuevaInfo.agregarCaracteristica("CILINDRADA", "2.0");
		agregarModelo(nuevaInfo); //agrega un motor básico al catálogo
	}
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
	 
	
	 
}
 
