package pruebas;

import java.util.ArrayList;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

import proveedorDePartes.fabricas.FabricaDeMotores;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.Motor;
import junit.framework.TestCase;

public class FabricaDeMotoresTest extends TestCase {

	public void testFabricarMotor() {
		FabricaDeMotores fabrica = new FabricaDeMotores();
		ArrayList<InformacionDelModelo> modelos = fabrica.getModelos();
		Motor unMotor = null;
		try {
			unMotor = fabrica.fabricar(modelos.get(0));
		} catch (NoSuchModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			assertTrue(unMotor.getRPMMaximo()== Double.parseDouble(modelos.get(0).getCaracteristica("RPMMAX")));
			assertTrue(unMotor.getDescripcion()== modelos.get(0).getCaracteristica("DESCRIPCION"));
		//} catch (NumberFormatException e) {
			//	e.printStackTrace();
		} catch (BoundsException e) {
			fail(e.getMessage());
		}
		
	}

	public void testConsultarPrecio() {
		fail("Falta implementar");

	}

}