package pruebas;

import java.util.ArrayList;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;

import proveedores.proveedorDePartes.fabricas.FabricaDeMotores;
import proveedores.proveedorDePartes.fabricas.InformacionDelModelo;
import proveedores.proveedorDePartes.fabricas.Motor;
import junit.framework.TestCase;

public class FabricaDeMotoresTest extends TestCase {

	public void testFabricarMotor() {
		FabricaDeMotores fabrica = new FabricaDeMotores();
		ArrayList<InformacionDelModelo> modelos = fabrica.getModelos();
		Motor unMotor = null;
		try {
			unMotor = fabrica.fabricar(modelos.get(0));
		} catch (NoSuchModelException e1) {
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
		FabricaDeMotores fabrica = new FabricaDeMotores();
		try {
			assertEquals(600, (int)(fabrica.consultarPrecio(fabrica.getModelos().get(0))));
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existe");
		} 

	}

}
