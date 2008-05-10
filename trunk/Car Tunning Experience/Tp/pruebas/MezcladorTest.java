package pruebas;

import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.Tanque;
import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

public class MezcladorTest extends TestCase {

	MezcladorNafta mezclador;
	TanqueNafta tanque;
	
	protected void setUp() throws Exception {
		super.setUp();
		tanque = new TanqueNafta(50);
		tanque.llenarTanque(50);
		mezclador = new MezcladorNafta(0, tanque);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
		mezclador=null;
	}

	public void testObtenerMezclaMaximaEficiencia() {
		mezclador.setEficiencia(100);
		assertEquals(, mezclador.obtenerMezcla(50), delta)
		
	}

}
