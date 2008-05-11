package pruebas;

import junit.framework.TestCase;
import auto.partesAuto.Escape;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.motor.Motor;
import auto.partesAuto.tanque.TanqueNafta;

import combustible.Nafta;

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
		tanque.llenarTanque(50);
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
		motor.acelerar(0.2);
		assertEquals(1.2, motor.obtenerRPM());
		motor.acelerar(1);
}
	
	public void testAcelerarHastaFundir(){
		escape.setEficiencia(20);
		int contador;
		for(contador=0;contador<100;contador++)
			motor.acelerar(1);
		assertEquals(0.0, motor.getVidaUtil());
	}


}
