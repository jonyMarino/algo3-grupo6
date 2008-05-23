package pruebas;

import combustible.Nafta;
import auto.partesAuto.BoundsException;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

//TODO: Se agrego excepciones, falta hacer algo en los bloques catch señalados
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
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(20.0*0.75, tanque.getPeso());
	}

	public void testLlenarTanque() {
		assertEquals(0.0, tanque.getCantidadCombustible());
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(20.0, tanque.getCantidadCombustible());

		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());
	
		try {
			tanque.llenarTanque(100);
		} catch (BoundsException e) {
			//FALTA HACER ALGO ACA SI SUPERA LA CAPACIDAD DEL TANQUE
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());
	}

	public void testUsarNafta() {
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			tanque.usarCombustible(10);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(10.0, tanque.getCantidadCombustible());
	
		try {
			tanque.usarCombustible(2.5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(7.5, tanque.getCantidadCombustible());

		try {
			tanque.usarCombustible(-2);
		} catch (BoundsException e) {
				e.printStackTrace();
				//FALTA HACER ALGO SI EL COMBUSTIBLE ES NEGATIVO
		}
		assertEquals(7.5, tanque.getCantidadCombustible());
		
		try {
			tanque.usarCombustible(50);
		} catch (BoundsException e) {
			//FALTA HACER ALGO SI NO SE POSEE EL COMBUSTIBLE
		}
		assertEquals(7.5, tanque.getCantidadCombustible());
		
	}

	public void testUsarNaftaNegativa() {
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			tanque.usarCombustible(-10);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
	}

	public void testConsumoDeNaftaAlMezclarMaximaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(1);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		try {
			mezclador.obtenerMezcla(5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(43.5, tanque.getCantidadCombustible(), 0.2);
		try {
			mezclador.obtenerMezcla(10);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(33.15, tanque.getCantidadCombustible(), 0.4);
		try {
			mezclador.obtenerMezcla(90);
		} catch (BoundsException e) {
			e.printStackTrace();
			//FALTA HACER ALGO SI NO SE POSEE LA NAFTA NECESARIA PARA LA MEZCLA
		}
		assertEquals(33.15, tanque.getCantidadCombustible(), 0.4);
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMinimaEficiencia() {
		mezclador= new MezcladorNafta(1,tanque);
		try {
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(0.01);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMediaEficiencia() {
		mezclador= new MezcladorNafta(50,tanque);
		try {
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(0.5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testLlenarTanqueNegativo() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
			tanque.llenarTanque(-50);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(0.0, tanque.getCantidadCombustible());
		mezclador = null;

	}

}
