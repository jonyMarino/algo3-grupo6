package pruebas;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import junit.framework.TestCase;
import programaAuto.Auto;
import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import programaAuto.ProgramaAuto.TipoAuto;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;

public class CarroceriaTest extends TestCase {

	Carroceria carroceria;
	FabricaDeCarrocerias fabricaDeCarrocerias;
	Pista pista;
	Auto auto;
	
	protected void setUp() throws Exception {
		super.setUp();
		ProgramaAuto programa = new ProgramaAuto("Prueba", TipoAuto.MANUAL);
		auto = programa.autoInicial(TipoAuto.MANUAL);
		pista = new Pista("Pista Prueba",20,15,1);
		auto.setPista(pista);
		fabricaDeCarrocerias = new FabricaDeCarrocerias();	
		fabricaDeCarrocerias.proponerCarroceria("Basica", 50, 10, 5);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		pista = null;
		auto = null;
	}

	public void testFuerzaAire() {
		try {
			carroceria = fabricaDeCarrocerias.fabricar(fabricaDeCarrocerias.getModelos().get(1));
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		auto.setCarroceria(carroceria);
		carroceria.setAuto(auto);
		assertEquals(1.5, carroceria.getFuerzaAire());
		
		carroceria = null;
	}

	public void testFuerzaAireCarroceriaDesgastada()throws BoundsException {
		try {
			carroceria = fabricaDeCarrocerias.fabricar(fabricaDeCarrocerias.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		auto.setCarroceria(carroceria);
		carroceria.setAuto(auto);
		carroceria.desgastar(100000);
		assertEquals(15.0,carroceria.getFuerzaAire());
		
		carroceria = null;
	}

}
