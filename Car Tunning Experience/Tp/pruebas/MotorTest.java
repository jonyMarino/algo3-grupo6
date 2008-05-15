package pruebas;

import pista.Pista;
import junit.framework.TestCase;
import auto.AutoManual;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
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
	CajaManual caja;
	Rueda rueda;
	Eje eje;
	AutoManual auto;
	Carroceria carroceria;
	Pista pista;

	protected void setUp() throws Exception {
		super.setUp();
		pista = new Pista(100);
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
		mezclador = new MezcladorNafta(100,tanque);
		escape = new Escape(100);
		carroceria = new Carroceria(5,5,200);
		rueda = new Rueda(1,0.9,0.6);
		motor=new Motor(100,7500,mezclador,escape,2.0);
		caja = new CajaManual();

		tanque.llenarTanque(50);
		auto = new AutoManual(escape, carroceria, motor, caja, mezclador, tanque, rueda, rueda, rueda, rueda);
		rueda.setPista(pista);
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
		auto.calcularVelocidad(10, pista);
		double rpm = motor.obtenerRPM();
		System.out.println(rpm);
		int result =(int)(motor.obtenerRPM()*100);//comparo contra 2 decimales
		assertEquals(538, result);
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
		motor.acelerar(1);

		for(contador=0;contador<900;contador++)
			auto.calcularVelocidad(60000, pista);
		assertEquals(0.0, motor.getVidaUtil());
	}


}
