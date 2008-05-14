package pruebas;

import junit.framework.TestCase;
import auto.AutoManual;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
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
	Rueda rueda;
	Eje eje;
	AutoManual auto;
	Carroceria carroceria;

	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
		mezclador = new MezcladorNafta(100,tanque);
		escape = new Escape(100);
		carroceria = new Carroceria(5,5);
		rueda = new Rueda(1,0.0,0.0,auto); //TODO: ¿no puede una rueda pertenecer a otra cosa que no sea un auto?
		eje = new Eje(rueda);
		motor=new Motor(100,7500,mezclador,escape,caja, auto); //TODO: idem
		caja = new CajaManual(eje, motor);
		//TODO: tanto Motor, como rueda, poseen referencias a un auto que no existe.
		tanque.llenarTanque(50);
		auto = new AutoManual(escape, carroceria, motor, caja, mezclador, tanque, rueda, rueda, rueda, rueda);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		nafta = null;
		tanque = null;
		mezclador = null;
		escape = null;
		motor = null;
	}

	public void testAcelerar() throws BoundsException {
		assertEquals(0.0, motor.obtenerRPM());
		motor.acelerar(1);
		assertEquals(1.2, motor.obtenerRPM());
	}

	public void testAcelerarDeMas() throws BoundsException {
		try{
			motor.acelerar(20);
			fail("Debería haberse generado una excepción.");
		}
		catch (BoundsException excepcion){
		}
		try{
			motor.acelerar(-90);
			fail("Debería haberse generado una excepción.");
		}
		catch (BoundsException excepcion){
		}
	}

	public void testAcelerarHastaFundir() throws BoundsException{
		escape.setEficiencia(20);
		int contador;
		for(contador=0;contador<900;contador++)
			motor.acelerar(1);
		assertEquals(0.0, motor.getVidaUtil());
	}


}
