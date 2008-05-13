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
		nafta = new Nafta(95,10);
		tanque = new TanqueNafta(50, nafta);
		tanque.llenarTanque(50);
		//tanque.setTipoCombustible(nafta);
		mezclador = new MezcladorNafta(0, tanque);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
		mezclador=null;
		nafta = null;
	}
	
	public void testObtenerMezclaMaximaEficiencia() {
		mezclador= new MezcladorNafta(100,tanque);
		assertEquals(6.6, mezclador.obtenerMezcla(1.1));
		assertEquals(35.4, mezclador.obtenerMezcla(5.9));
		assertEquals(8.0, mezclador.obtenerMezcla(90));
		
	}
	
	public void testObtenerMezclaMínimaEficiencia() {
		mezclador= new MezcladorNafta(0,tanque);
		assertEquals(10.6, mezclador.obtenerMezcla(0.1));
		
	}
	
	public void testObtenerMezclaMediaEficiencia() {
		mezclador= new MezcladorNafta(50,tanque);
		assertEquals(11.2, mezclador.obtenerMezcla(0.2));
		
	}

}
