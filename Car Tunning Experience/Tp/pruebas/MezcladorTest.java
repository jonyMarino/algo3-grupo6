package pruebas;

import combustible.Nafta;

import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.Tanque;
import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

public class MezcladorTest extends TestCase {

	MezcladorNafta mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	
	protected void setUp() throws Exception {
		super.setUp();
		tanque = new TanqueNafta(50);
		tanque.llenarTanque(50);
		nafta = new Nafta(95,10);
		tanque.setTipoCombustible(nafta);
		mezclador = new MezcladorNafta(0, tanque);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
		mezclador=null;
		nafta = null;
	}
	
	public void testObtenerMezclaMaximaEficiencia() {
		mezclador.setEficiencia(100);
		assertEquals(300, mezclador.obtenerMezcla(50), 0);
		
	}
	
	public void testObtenerMezclaMínimaEficiencia() {
		mezclador.setEficiencia(0);
		assertEquals(5300, mezclador.obtenerMezcla(50), 0);
		
	}
	
	public void testObtenerMezclaMediaEficiencia() {
		mezclador.setEficiencia(50);
		assertEquals(2800, mezclador.obtenerMezcla(50), 0);
		
	}

}
