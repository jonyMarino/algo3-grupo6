package pruebas;

import proveedorDeCombustibles.FabricaDeNafta;
import proveedorDeCombustibles.Nafta;
import proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.TanqueNafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.TankIsFullException;
import junit.framework.TestCase;

public class TanqueNaftaTest extends TestCase {

	TanqueNafta tanque;
	FabricaDeTanquesDeCombustible fabricaDeTanques;
	FabricaDeMezcladores fabricaDeMezcladores;
	FabricaDeNafta fabricaDeNafta;
	Nafta nafta;
	private Mezclador mezclador;
	double naftaUtilRestante;
	double mezclaMinima;

	protected void setUp() throws Exception {
		super.setUp();
		fabricaDeTanques = new FabricaDeTanquesDeCombustible();
		fabricaDeTanques.proponerTanque("Tanque De Nafta", 70, 10.0, "NAFTA");
		fabricaDeMezcladores = new FabricaDeMezcladores();
		mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(0));
		fabricaDeNafta = new FabricaDeNafta();
		nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		nafta = null;
		mezclador = null;
	}

	public void testGetPeso() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		assertEquals(0.0, tanque.getPeso());
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(20.0*0.75, tanque.getPeso());
		
		tanque = null;
	}

	public void testLlenarTanque() {
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
		
		try {
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(70.0, tanque.getCantidadCombustible());
		
		try {
			tanque.llenarTanque(60);
			fail("No se puede superar la capacidad máxima del tanque");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			assertTrue(true);
		}
		
		tanque = null;
	}

	public void testUsarNafta() {
		
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
			
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		
		try {
			tanque.usarCombustible(10);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(10.0, tanque.getCantidadCombustible());
		
		try {
			tanque.usarCombustible(2.5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(7.5, tanque.getCantidadCombustible());
		
		try {
			tanque.usarCombustible(50);
			fail("No se puede utilizar mas Nafta de la disponible");
		} catch (BoundsException e) {
			if("No se posee la cantidad de combustible pedida" == e.getMessage())
				try {
					tanque.usarCombustible(tanque.getCantidadCombustible());
				} catch (BoundsException e1) {}
		}
		assertEquals(0.0, tanque.getCantidadCombustible());

		tanque = null;
	}

	public void testUsarNaftaNegativa() {
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		
		try {
			tanque.usarCombustible(-10);
			fail("No se pueden usar cantidades negativas de combustible");
		} catch (BoundsException e) {
			assertTrue(true);
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
	}

	public void testLlenarTanqueNegativo() {
		try {
			tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(1));
		} catch (NoSuchModelException e1) {
			e1.printStackTrace();
		}
		tanque.setCombustible(nafta);
		mezclador.setTanqueCombustible(tanque);
		
		try {
			tanque.llenarTanque(-50);
			fail("No se puede llenar el Tanque con una cantidad de litros negativa");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(0.0, tanque.getCantidadCombustible());

		try {
			tanque.llenarTanque(40);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());

		try {
			tanque.llenarTanque(-10);
			fail("No se puede llenar el Tanque con una cantidad de litros negativa");
		} catch (BoundsException e) {
			assertTrue(true);
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(40.0, tanque.getCantidadCombustible());
		
		tanque = null;
	}

}
