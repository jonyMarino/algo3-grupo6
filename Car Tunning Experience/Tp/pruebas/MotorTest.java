package pruebas;

import junit.framework.TestCase;
import auto.Auto;
import auto.AutoManual;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

import combustible.Nafta;

public class MotorTest extends TestCase {

	Motor motor;
	Escape escape;
	MezcladorNafta mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	Caja caja;
	
	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
		mezclador = new MezcladorNafta(100,tanque);
		escape = new Escape(100);
		Eje eje = new Eje();
		caja = new CajaManual(eje);
		AutoManual auto = new AutoManual();
		motor=new Motor(100,7500.0,mezclador,escape,caja, auto);
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
		assertEquals(0.0, motor.getRPM());
		motor.acelerar(0.2);
		assertEquals(1.2, motor.getRPM());
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
