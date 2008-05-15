package pruebas;

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

	protected void setUp() throws Exception {
		super.setUp();
		nafta = new Nafta(95,20);
		tanque = new TanqueNafta(50, nafta);
		mezclador = new MezcladorNafta(100,tanque);
		escape = new Escape(100);
		carroceria = new Carroceria(5,5);
		rueda = new Rueda(1,0.0,0.0); //TODO: ¿no puede una rueda pertenecer a otra cosa que no sea un auto
		eje = new Eje(rueda);
		caja = new CajaManual(eje, motor);
		motor=new Motor(100,7500,mezclador,escape,caja, 300); //TODO: Idem, creo que auto le puede pasar la velocidad alas ruedas.De esta forma Rueda no tiene que guardar una referencia de Auto.
		//TODO: tanto Motor, como rueda, poseen referencias a un auto que no existe.
		tanque.llenarTanque(50);
		auto = new AutoManual(escape, carroceria, motor, caja, mezclador, tanque, rueda, rueda, rueda, rueda);
		rueda.setAuto(auto);
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
		assertEquals(0.0, motor.getTorque());
		motor.acelerar(1);
		assertEquals(47.5, motor.getTorque());
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
