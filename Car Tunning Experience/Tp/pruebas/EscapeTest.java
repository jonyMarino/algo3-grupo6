package pruebas;

import excepciones.NoSuchModelException;
import proveedores.proveedorDePartes.fabricas.Escape;
import proveedores.proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedores.proveedorDePartes.fabricas.FabricaDeMotores;
import proveedores.proveedorDePartes.fabricas.Motor;

import junit.framework.TestCase;

public class EscapeTest extends TestCase {
	
	Escape escape;
	Motor motor;
	FabricaDeEscapes fabricaDeEscapes;
	FabricaDeMotores fabricaDeMotores;
	

	protected void setUp() throws Exception {
		super.setUp();
		fabricaDeEscapes = new FabricaDeEscapes();
		fabricaDeMotores = new FabricaDeMotores();
		escape = fabricaDeEscapes.fabricar(fabricaDeEscapes.getModelos().get(0));
		fabricaDeEscapes.proponerEscape("Escape 50% eficiente",25,60);
		motor = fabricaDeMotores.fabricar(fabricaDeMotores.getModelos().get(0));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetPeso() {

		assertEquals(5.0, escape.getPeso());
		escape = null;
	}
	
	
	public void testEscapeDesgastado() {
		
		escape.desgastar(5000);
		assertEquals(37.5, escape.getVidaUtil());

		escape.desgastar(2000000000);
		assertEquals(0.0, escape.getVidaUtil());
				
	}
	
	public void testEvacuarGases() {
		
		double mezcla=5.0;
		double energiaMaxima = mezcla*motor.getRendimiento()/100;
		assertEquals(3.2,escape.evacuarGases(energiaMaxima));
		
		
		try {
			escape = fabricaDeEscapes.fabricar(fabricaDeEscapes.getModelos().get(1));
		
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		
		assertEquals(1.0,escape.evacuarGases(energiaMaxima));
		
	}
	
}
