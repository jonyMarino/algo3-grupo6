package pruebas;

import proveedorDeNafta.FabricaDeNafta;
import proveedorDeNafta.Nafta;
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
		fabricaDeMezcladores = new FabricaDeMezcladores();
		fabricaDeNafta = new FabricaDeNafta();
		fabricaDeMezcladores.proponerMezclador("Mezclador 100% eficiente", 100, 50, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 50% eficiente", 50, 25, "NAFTA");
		fabricaDeMezcladores.proponerMezclador("Mezclador 0% eficiente", 1, 10, "NAFTA");
		nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));
		tanque = fabricaDeTanques.fabricar(fabricaDeTanques.getModelos().get(0));
		tanque.setCombustible(nafta);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tanque = null;
	}

	public void testGetPeso() {
		assertEquals(0.0, tanque.getPeso());
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(20.0*0.75, tanque.getPeso());
	}

	public void testLlenarTanque() {
		assertEquals(0.0, tanque.getCantidadCombustible());
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			fail("Error no esperado");
		} catch (TankIsFullException e) {
			fail("Error no esperado");
		}
		
		assertEquals(20.0, tanque.getCantidadCombustible());
		try {
			tanque.llenarTanque(20);
		} catch (BoundsException e) {
			fail("Error no esperado");
		} catch (TankIsFullException e) {
			fail("Error no esperado");
		}
		assertEquals(40.0, tanque.getCantidadCombustible());
		try {
			tanque.llenarTanque(100);
			fail("No se puede superar la capacidad máxima del tanque");
		} catch (BoundsException e) {
			fail("Error no esperado");
		} catch (TankIsFullException e) {
			assertTrue(true);
		}
	}

	public void testUsarNafta() {
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

	}

	public void testUsarNaftaNegativa() {
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
			//prueba exitosa
		}
		assertEquals(20.0, tanque.getCantidadCombustible());
	}

	public void testConsumoDeNaftaAlMezclarMaximaEficiencia() throws TankIsFullException {
		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(1));
			mezclador.setTanqueCombustible(tanque);
			naftaUtilRestante = 0;
			mezclaMinima = 0;
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(1);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		try {
			mezclador.obtenerMezcla(5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(43.5, tanque.getCantidadCombustible(), 0.2);
		try {
			mezclador.obtenerMezcla(10);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(33.15, tanque.getCantidadCombustible(), 0.4);
		try {
			mezclador.obtenerMezcla(90);
			fail("No se puede obtener esa cantidad de mezcla");
		} catch (BoundsException e) {
			if("Faltante de nafta necesaria para la mezcla pedida" == e.getMessage())
				naftaUtilRestante = ((tanque.getCantidadCombustible()*tanque.getCombustible().getOctanaje())/100);
				mezclaMinima = ((mezclador.getRendimiento()*naftaUtilRestante)/100);
				try {
					mezclador.obtenerMezcla(mezclaMinima);
				} catch (BoundsException e1) {}
		}
		assertEquals(0.0, tanque.getCantidadCombustible(), 0.4);
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMinimaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(3));
			mezclador.setTanqueCombustible(tanque);
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(0.01);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testConsumoDeNaftaAlMezclarMediaEficiencia() {

		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(2));
			mezclador.setTanqueCombustible(tanque);
			tanque.llenarTanque(50);
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		try {
			mezclador.obtenerMezcla(0.5);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		assertEquals(49, tanque.getCantidadCombustible(), 0.2);
		mezclador = null;

	}

	public void testLlenarTanqueNegativo() {
		try {
			mezclador = fabricaDeMezcladores.fabricar(fabricaDeMezcladores.getModelos().get(0));
			mezclador.setTanqueCombustible(tanque);
			tanque.llenarTanque(-50);
			fail("No se puede llenar el Tanque con una cantidad de litros negativa");
		} catch (BoundsException e) {
			//Prueba exitosa
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		} catch (TankIsFullException e) {
			e.printStackTrace();
		}
		assertEquals(0.0, tanque.getCantidadCombustible());
		mezclador = null;

	}

}
