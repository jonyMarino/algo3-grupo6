package pruebas;

import combustible.Nafta;

import auto.partesAuto.Escape;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.motor.Motor;
import auto.partesAuto.tanque.TanqueNafta;
import junit.framework.TestCase;

public class MotorTest extends TestCase {

	Motor motor;
	Escape escape;
	MezcladorNafta mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	
	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
		mezclador = new MezcladorNafta(100,tanque);
		escape = new Escape(100);
		motor=new Motor(100,7500,mezclador,escape);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		nafta = null;
		tanque = null;
		mezclador = null;
		escape = null;
		motor = null;
	}

	public void testAcelerar() {
		assertEquals(0.0, motor.obtenerRPM());
		motor.acelerar();
		assertEquals(1.2, motor.obtenerRPM());
	}

	public void testObtenerRPM() {
		fail("Not yet implemented");
	}

	public void testAumentarRpm() {
		fail("Not yet implemented");
	}

	public void testDisminuiRPM() {
		fail("Not yet implemented");
	}

}
