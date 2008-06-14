package pruebas;

import java.util.ArrayList;

import proveedorDePartes.fabricas.FabricaDeMotores;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.Motor;
import junit.framework.TestCase;

public class FabricaDeMotoresTest extends TestCase {

	public void testFabricarInformacionDelModelo() {
		FabricaDeMotores fabrica = new FabricaDeMotores();
		ArrayList<InformacionDelModelo> modelos = fabrica.getModelos();
		Motor unMotor = fabrica.fabricar(modelos.get(0));
		assertTrue(unMotor.getRPMMaximo()== Double.parseDouble(modelos.get(0).getCaracteristica("RPMMAX")));
		assertTrue(unMotor.getDescripcion()== modelos.get(0).getCaracteristica("DESCRIPCION"));
	}

	public void testConsultarPrecio() {
		fail("Falta implementar");

	}

}
