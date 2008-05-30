package pruebas;

import junit.framework.TestCase;

import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import pista.Pista;

public class CarroceriaTest extends TestCase {
//TODO: Se modifico excepciones
	Carroceria carroceria;
	Pista pista;

	public void testFuerzaAire() throws BoundsException {
		pista=new Pista(5);
		carroceria= new Carroceria(5,5,30);
		assertEquals(0.0, carroceria.getFuerzaAire(pista));
		pista.setVelocidadAire(15);
		carroceria.setAeroDinamia(10);
		assertEquals(1.5, carroceria.getFuerzaAire(pista));
	}

	public void testFuerzaAireCarroceriaDesgastada()throws BoundsException {
		carroceria= new Carroceria(25,6,40);
		pista=new Pista(5);
		carroceria.setVidaUtil(0);
		try {
			pista.setVelocidadAire(25);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(25.0,carroceria.getFuerzaAire(pista));
	}

}