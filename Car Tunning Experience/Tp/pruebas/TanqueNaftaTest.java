package pruebas;

import proveedores.proveedorDeCombustibles.FabricaDeNafta;
import proveedores.proveedorDeCombustibles.Nafta;
import proveedores.proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedores.proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedores.proveedorDePartes.fabricas.Mezclador;
import proveedores.proveedorDePartes.fabricas.TanqueNafta;
import excepciones.BoundsException;
import excepciones.PartBrokenException;
import excepciones.NoSuchModelException;
import excepciones.TankIsFullException;
import junit.framework.TestCase;

public class TanqueNaftaTest extends TestCase {

	TanqueNafta tanque;
	FabricaDeTanquesDeCombustible fabricaDeTanques;
	FabricaDeMezcladores fabricaDeMezcladores;
	FabricaDeNafta fabricaDeNafta;
	Nafta nafta;
	private Mezclador mezclador;
	double naftaUtilRestante;
	double mezclaMinima;

	protected void setUp() throws Exception {
		super.setUp();
		fabricaDeTanques = new FabricaDeTanquesDeCombustible();
		fabricaDeTanques.proponerTanque("Tanque De Nafta", 70, 10.0, "NAFTA");
		fabricaDeMezcladores = new FabricaDeMezcladores();
		mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(0));
		fabricaDeNafta = new FabricaDeNafta();
		nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		nafta = null;
		mezclador = null;
	}

	public void testGetPeso() {

		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		assertEquals(0.0, tanque.getPeso());
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();	
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(20.0*0.75, tanque.getPeso());
		
		tanque = null;
	}

	public void testLlenarTanque() {

		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
		
		try {
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(70.0, tanque.getCantidadCombustible());
		
		try {
			tanque.llenarTanque(60);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			assertTrue(true);
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
	
		tanque = null;
	}

	public void testUsarNafta() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
			
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		try {
			tanque.usarCombustible(10);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(10.0, tanque.getCantidadCombustible());
		
		try {
			tanque.usarCombustible(2.5);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(7.5, tanque.getCantidadCombustible());
		
		try {
			tanque.usarCombustible(50);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}		
		assertEquals(7.5, tanque.getCantidadCombustible());

		tanque = null;
	}

	public void testUsarNaftaNegativa() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		
		try {
			tanque.usarCombustible(-10);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
	}

	public void testLlenarTanqueNegativo() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		mezclador.setTanqueCombustible(tanque);
		
		try {
			tanque.llenarTanque(-50);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(0.0, tanque.getCantidadCombustible());

		try {
			tanque.llenarTanque(40);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());

		try {
			tanque.llenarTanque(-10);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());
		
		tanque = null;
	}
	
	public void testTanqueDesgastado() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		tanque.desgastar(5000);
		assertEquals(95.0, tanque.getVidaUtil());
		
		tanque.desgastar(2000000000);
		assertEquals(0.0, tanque.getVidaUtil());
			
		try {
			tanque.llenarTanque(10);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			assertTrue(true);
		}
		
		try {
			tanque.usarCombustible(10);
			fail("Debería haberse lanzado una excepción");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (PartBrokenException e) {
			assertTrue(true);
		}
	
		tanque = null;
	}

}
