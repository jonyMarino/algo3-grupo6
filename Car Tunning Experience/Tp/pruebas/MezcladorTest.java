package pruebas;

import proveedores.proveedorDeCombustibles.FabricaDeNafta;
import proveedores.proveedorDeCombustibles.Nafta;
import proveedores.proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedores.proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedores.proveedorDePartes.fabricas.Mezclador;
import proveedores.proveedorDePartes.fabricas.TanqueNafta;
import junit.framework.TestCase;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.PartBrokenException;

public class MezcladorTest extends TestCase {

	Mezclador mezclador;
	TanqueNafta tanqueNafta;
	Nafta nafta;
	FabricaDeMezcladores fabricaDeMezcladores;
	FabricaDeTanquesDeCombustible fabricaDeTanques;
	FabricaDeNafta fabricaDeNafta;
	
	protected void setUp() throws Exception {
		super.setUp();
		fabricaDeTanques = new FabricaDeTanquesDeCombustible();
		fabricaDeMezcladores = new FabricaDeMezcladores();
		fabricaDeNafta = new FabricaDeNafta();
		fabricaDeMezcladores.proponerMezclador("Mezclador 100% eficiente", 100, 50, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 50% eficiente", 50, 25, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 0% eficiente", 1, 10, "NAFTA");
		tanqueNafta = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(0));
		nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));
		tanqueNafta.setCombustible(nafta);
		tanqueNafta.llenarTanque(70);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanqueNafta = null;
		nafta = null;
	}

	public void testGetPeso() {
		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		assertEquals(50.0, mezclador.getPeso());
	
		mezclador = null;
	}

	
	public void testObtenerMezclaMaximaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
			mezclador.setTanqueCombustible(tanqueNafta);
			assertEquals(0.01, mezclador.obtenerMezcla(0.01));
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(1.1, mezclador.obtenerMezcla(1.1));
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(5.9, mezclador.obtenerMezcla(5.9));
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(90.0, mezclador.obtenerMezcla(50000));
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		mezclador = null;
	}

	public void testObtenerMezclaMínimaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(3));
			mezclador.setTanqueCombustible(tanqueNafta);
			assertEquals(0.1, mezclador.obtenerMezcla(0.1));
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		mezclador = null;
	}

	public void testObtenerMezclaMediaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(3));
			mezclador.setTanqueCombustible(tanqueNafta);
			assertEquals(0.2, mezclador.obtenerMezcla(0.2));
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		mezclador = null;
	}

	public void testObtenerMezclaNegativa() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
			mezclador.setTanqueCombustible(tanqueNafta);
			assertEquals(0.0, mezclador.obtenerMezcla(-90));
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		mezclador = null;
	}
	
	public void testMezcladorDesgastado() {
		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		mezclador.setTanqueCombustible(tanqueNafta);
		mezclador.desgastar(5000);
		assertEquals(95.0, mezclador.getVidaUtil());

		mezclador.desgastar(20000000);
		assertEquals(0.0, mezclador.getVidaUtil());

		try {
			mezclador.obtenerMezcla(10);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			assertTrue(true);
		}
		
		mezclador = null;
	}
	
	
}
