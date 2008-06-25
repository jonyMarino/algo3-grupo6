package pruebas;

import com.sun.net.httpserver.Authenticator.Success;

import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import proveedorDePartes.ProveedorDePartes;
import proveedorDePartes.fabricas.CajaAutomatica;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.Pedal;
import junit.framework.TestCase;

public class ProveedorDePartesTest extends TestCase {

	ProveedorDePartes unProveedor;
	
	protected void setUp() throws Exception {
		super.setUp();
		unProveedor = new ProveedorDePartes();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testComprarParte(){
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(0));
			assertTrue(unaParte instanceof CajaManual);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(1));
			assertTrue(unaParte instanceof CajaAutomatica);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(2));
			assertTrue(unaParte instanceof Carroceria);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(3));
			assertFalse(unaParte instanceof Pedal);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
	}
	
	public void testComprarModeloInexistente(){
		try {
			ParteAuto unaParte = unProveedor.comprar(null);
			fail("Se supone que el modelo era invalido");
		} catch (NoSuchModelException e) {
			assertTrue(true);
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
	}

}
