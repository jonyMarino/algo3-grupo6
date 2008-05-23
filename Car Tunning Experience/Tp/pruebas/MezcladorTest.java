package pruebas;

import junit.framework.TestCase;
import auto.partesAuto.BoundsException;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

import combustible.Nafta;

//TODO: Se agrego excepciones
public class MezcladorTest extends TestCase {

	MezcladorNafta mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	
	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,10);
		tanque = new TanqueNafta(50, nafta);
		tanque.llenarTanque(50);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
		nafta = null;
	}
	
	public void testObtenerMezclaMaximaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
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
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
		
	}
	
	public void testObtenerMezclaMínimaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
			assertEquals(0.1, mezclador.obtenerMezcla(0.1));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
	}
	
	public void testObtenerMezclaMediaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
			assertEquals(0.2, mezclador.obtenerMezcla(0.2));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
	}
	
	public void testObtenerMezclaNegativa() {
		mezclador= new MezcladorNafta(100,tanque);
		try {
			assertEquals(0.0, mezclador.obtenerMezcla(-90));
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		mezclador = null;
	}

}
