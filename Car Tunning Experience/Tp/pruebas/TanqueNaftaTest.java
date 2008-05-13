package pruebas;

import combustible.Nafta;

import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

public class TanqueNaftaTest extends TestCase {

	TanqueNafta tanque;
	Nafta nafta;
	private MezcladorNafta mezclador;

	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
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
		assertEquals(0.0, tanque.getCantidadCombustible());
		tanque.llenarTanque(20);
		assertEquals(20.0, tanque.getCantidadCombustible());
		tanque.llenarTanque(20);
		assertEquals(40.0, tanque.getCantidadCombustible());
		tanque.llenarTanque(100);
		assertEquals(50.0, tanque.getCantidadCombustible());
	}

	public void testUsarNafta() {
		tanque.llenarTanque(20);
		tanque.usarCombustible(10);
		assertEquals(10.0, tanque.getCantidadCombustible());
		tanque.usarCombustible(2.5);
		assertEquals(7.5, tanque.getCantidadCombustible());
		tanque.usarCombustible(50);
		assertEquals(0.0, tanque.getCantidadCombustible());
	}

	public void testUsarNaftaNegativa() {
		tanque.llenarTanque(20);
		tanque.usarCombustible(-10);
		assertEquals(20.0, tanque.getCantidadCombustible());
	}

	public void testConsumoDeNaftaAlMezclarMaximaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		tanque.llenarTanque(50);
		mezclador.obtenerMezcla(1);
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador.obtenerMezcla(5);
		assertEquals(43.5, tanque.getCantidadCombustible(), 0.2);
		mezclador.obtenerMezcla(10);
		assertEquals(33, tanque.getCantidadCombustible(), 0.4);
		mezclador.obtenerMezcla(90);
		assertEquals(0.0, tanque.getCantidadCombustible());
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMinimaEficiencia() {
		mezclador= new MezcladorNafta(1,tanque);
		tanque.llenarTanque(50);
		mezclador.obtenerMezcla(0.01);
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMediaEficiencia() {
		mezclador= new MezcladorNafta(50,tanque);
		tanque.llenarTanque(50);
		mezclador.obtenerMezcla(0.5);
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testLlenarTanqueNegativo() {
		mezclador= new MezcladorNafta(100,tanque);
		tanque.llenarTanque(-50);
		assertEquals(0.0, tanque.getCantidadCombustible());
		mezclador = null;

	}

}
