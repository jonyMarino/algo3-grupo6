package pruebas;

import junit.framework.TestCase;

import auto.Auto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import pista.Pista;
import programaAuto.ProgramaAuto;

public class CarroceriaTest extends TestCase {

	Carroceria carroceria;
	Pista pista;

	Auto auto;
	
	protected void setUp() throws Exception {
		super.setUp();
		auto = ProgramaAuto.autoInicial();
		pista=new Pista(5);
		auto.setPista(pista);
	}
	
	public void testFuerzaAire() throws BoundsException {

		carroceria= new Carroceria(5,5,30);
		auto.setCarroceria(carroceria);
		assertEquals(0.0, carroceria.getFuerzaAire());
		pista.setVelocidadAire(15);
		carroceria.setAeroDinamia(10);
		assertEquals(1.5, carroceria.getFuerzaAire());
	}

	public void testFuerzaAireCarroceriaDesgastada()throws BoundsException {
		carroceria= new Carroceria(25,6,40);
		auto.setCarroceria(carroceria);
		carroceria.setVidaUtil(0);
		try {
			pista.setVelocidadAire(25);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(25.0,carroceria.getFuerzaAire());
	}

}
