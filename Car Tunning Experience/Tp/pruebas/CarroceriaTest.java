package pruebas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import junit.framework.TestCase;

import programaAuto.Auto;
import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;

public class CarroceriaTest extends TestCase {

	Carroceria carroceria;
	FabricaDeCarrocerias fabrica;
	Pista pista;

	Auto auto;
	
	protected void setUp() throws Exception {
		ProgramaAuto programa = new ProgramaAuto("Prueba");
		super.setUp();
		auto = programa.autoInicial();
		pista=new Pista("unaPista");
		auto.setPista(pista);
		fabrica = new FabricaDeCarrocerias();
		
	}
	
	public void testFuerzaAire() throws BoundsException {

		try {
			carroceria= fabrica.fabricar(fabrica.getModelos().get(0));
		} catch (NoSuchModelException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		auto.setCarroceria(carroceria);
		assertEquals(0.0, carroceria.getFuerzaAire());
		pista.setVelocidadAire(15);
		assertEquals(1.5, carroceria.getFuerzaAire());
	}

	public void testFuerzaAireCarroceriaDesgastada()throws BoundsException {
		try {
			carroceria= fabrica.fabricar(fabrica.getModelos().get(0));
		} catch (NoSuchModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		auto.setCarroceria(carroceria);
		carroceria.desgastar(100000);
		try {
			//pista.setVelocidadAire(25);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(25.0,carroceria.getFuerzaAire());
	}

}
