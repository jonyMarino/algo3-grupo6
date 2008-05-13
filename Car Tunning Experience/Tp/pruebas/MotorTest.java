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
		eje = new Eje(rueda);  // TODO: rueda no existe en este punto
		carroceria = new Carroceria(5,5);
		auto = new AutoManual(escape, carroceria, motor, caja, mezclador, tanque, rueda, rueda, rueda, rueda);
		caja = new CajaManual(eje, motor);
		rueda = new Rueda(1,0.0,0.0,auto); //TODO: �no puede una rueda pertenecer a otra cosa que no sea un auto?
		motor=new Motor(100,7500.0,mezclador,escape,caja, auto); //TODO: idem
		tanque.llenarTanque(50);
		auto.setMotor(motor);
		auto.setMezclador(mezclador);
		//TODO: Fijense que por la forma en la que dependen los objetos entre s�, a auto, hay que pasarle un motor y ruedas que no existen.
		//TODO: O si se quiere hacer al reves, hay que pasarle a las ruedas y al motor, un auto que no existe
		//TODO: escucho argumentos y comentarios.
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
		motor.acelerar(0.2);
		assertEquals(1.2, motor.obtenerRPM());
		motor.acelerar(1);
}
	
	public void testAcelerarHastaFundir() throws BoundsException{
		escape.setEficiencia(20);
		int contador;
		for(contador=0;contador<100;contador++)
			motor.acelerar(1);
		assertEquals(0.0, motor.getVidaUtil());
	}


}
