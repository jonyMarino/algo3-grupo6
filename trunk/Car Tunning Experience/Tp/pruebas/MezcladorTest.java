package pruebas;

import proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.MezcladorNafta;
import proveedorDePartes.fabricas.TanqueNafta;
import junit.framework.TestCase;

import combustible.Nafta;
import excepciones.BoundsException;

public class MezcladorTest extends TestCase {

	Mezclador mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	FabricaDeMezcladores fabricaDeMezcladores;
	FabricaDeTanquesDeCombustible fabricaDeTanques;

	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,10);
		fabricaDeTanques = new FabricaDeTanquesDeCombustible();
		fabricaDeMezcladores = new FabricaDeMezcladores();
		fabricaDeMezcladores.proponerMezclador("Mezclador 100% eficiente", 100, 50, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 50% eficiente", 50, 25, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 0% eficiente", 1, 10, "NAFTA");
		tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(0));
		tanque.setCombustible(nafta);
		tanque.llenarTanque(70);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
		nafta = null;
	}

	public void testObtenerMezclaMaximaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
			mezclador.setTanqueCombustible(tanque);
			assertEquals(0.01, mezclador.obtenerMezcla(0.01));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(1.1, mezclador.obtenerMezcla(1.1));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(5.9, mezclador.obtenerMezcla(5.9));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(40.49, mezclador.obtenerMezcla(90));
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			//prueba exitosa
		}
		mezclador = null;

	}

	public void testObtenerMezclaMínimaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(3));
			mezclador.setTanqueCombustible(tanque);
			assertEquals(0.1, mezclador.obtenerMezcla(0.1));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
	}

	public void testObtenerMezclaMediaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(3));
			mezclador.setTanqueCombustible(tanque);
			assertEquals(0.2, mezclador.obtenerMezcla(0.2));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
	}

	public void testObtenerMezclaNegativa() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
			mezclador.setTanqueCombustible(tanque);
			assertEquals(0.0, mezclador.obtenerMezcla(-90));
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			//prueba exitosa
		}
		mezclador = null;
	}

}
