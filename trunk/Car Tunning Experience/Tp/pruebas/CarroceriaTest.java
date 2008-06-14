package pruebas;

import excepciones.BoundsException;
import junit.framework.TestCase;

import auto.Auto;
import pista.Pista;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;

public class CarroceriaTest extends TestCase {

	Carroceria carroceria;
	FabricaDeCarrocerias fabrica;
	Pista pista;

	Auto auto;
	
	protected void setUp() throws Exception {
		super.setUp();
		auto = ProgramaAuto.autoInicial();
		pista=new Pista(5);
		auto.setPista(pista);
		fabrica = new FabricaDeCarrocerias();
		
	}
	
	public void testFuerzaAire() throws BoundsException {

		carroceria= fabrica.fabricar(fabrica.getModelos().get(0));
		auto.setCarroceria(carroceria);
		assertEquals(0.0, carroceria.getFuerzaAire());
		pista.setVelocidadAire(15);
		assertEquals(1.5, carroceria.getFuerzaAire());
	}

	public void testFuerzaAireCarroceriaDesgastada()throws BoundsException {
		carroceria= fabrica.fabricar(fabrica.getModelos().get(0));
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
