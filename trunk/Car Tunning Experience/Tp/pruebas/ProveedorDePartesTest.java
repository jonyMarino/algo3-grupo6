package pruebas;

//import com.sun.net.httpserver.Authenticator.Success;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import programaAuto.ProveedorDePartes;
import programaAuto.Usuario;
import proveedorDePartes.fabricas.CajaAutomatica;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.Pedal;
import junit.framework.TestCase;

public class ProveedorDePartesTest extends TestCase {

	ProveedorDePartes unProveedor;
	Usuario unUsuario;
	
	protected void setUp() throws Exception {
		super.setUp();
		unProveedor = new ProveedorDePartes();
		unUsuario = new Usuario("UsuarioPrueba");
		unUsuario.setDinero(99999);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testComprarParte(){
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(0), unUsuario);
			assertTrue(unaParte instanceof CajaManual);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(1), unUsuario);
			assertTrue(unaParte instanceof CajaAutomatica);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(2), unUsuario);
			assertTrue(unaParte instanceof Carroceria);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
		try {
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(3), unUsuario);
			assertFalse(unaParte instanceof Pedal);
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo existía.");
		} catch (NotEnoughMoneyException e) {
			fail("No implementado");
		}
		
	}
	
	public void testComprarModeloInexistente(){
		try {
			@SuppressWarnings("unused")
			ParteAuto unaParte = unProveedor.comprar(null, unUsuario);
			fail("Se supone que el modelo era invalido");
		} catch (NoSuchModelException e) {
			assertTrue(true);
		} catch (NotEnoughMoneyException e) {
			fail("Ae aupone que el modelo era invalido");
		}
	}
	
	public void testComprarSinDinero(){
		unUsuario.setDinero(0);
		try {
			@SuppressWarnings("unused")
			ParteAuto unaParte = unProveedor.comprar(unProveedor.getModelosDisponibles().get(5), unUsuario);
			fail("Se supone que no tenia suficiente dinero");
		} catch (NoSuchModelException e) {
			fail("Se supone que el modelo era valido");
		} catch (NotEnoughMoneyException e) {
			assertTrue(true);
		}
	}
	

}
