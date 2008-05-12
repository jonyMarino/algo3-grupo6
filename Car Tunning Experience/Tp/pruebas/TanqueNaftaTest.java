package pruebas;

import combustible.Nafta;

import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

public class TanqueNaftaTest extends TestCase {

	TanqueNafta tanque;
	Nafta nafta;
	
	protected void setUp() throws Exception {
		super.setUp();
		tanque = new TanqueNafta(50, nafta);
		nafta = new Nafta(95,20);
		tanque.setTipoCombustible(nafta);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
	}

	public void testGetPeso() {
		assertEquals(0.0, tanque.getPeso());
		tanque.llenarTanque(20);
		assertEquals(20.0*0.75, tanque.getPeso());
	}

	public void testLlenarTanque() {
		assertEquals(0.0, tanque.getCantidadNafta());
		tanque.llenarTanque(20);
		assertEquals(20.0, tanque.getCantidadNafta());
		tanque.llenarTanque(20);
		assertEquals(40.0, tanque.getCantidadNafta());
		tanque.llenarTanque(20);
		assertEquals(50.0, tanque.getCantidadNafta());
	}

	public void testUsarNafta() {
		tanque.llenarTanque(20);
		tanque.usarNafta(10);
		assertEquals(10.0, tanque.getCantidadNafta());
		tanque.usarNafta(2.5);
		assertEquals(7.5, tanque.getCantidadNafta());
		tanque.usarNafta(50);
		assertEquals(0.0, tanque.getCantidadNafta());
	}

}
