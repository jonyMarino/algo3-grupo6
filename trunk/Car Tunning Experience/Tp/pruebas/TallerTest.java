package pruebas;

import java.util.Iterator;

import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import junit.framework.TestCase;
import pista.Pista;
import programaAuto.ProgramaAuto;
import programaAuto.Usuario;
import taller.Taller;
import junit.framework.TestCase;
import auto.Auto;
import auto.AutoManual;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class TallerTest extends TestCase {
	private int indiceTmp=0;
	private Motor motor;
	Taller taller;
	Usuario usuario;
	
	protected void setUp() throws BoundsException{
		motor = new Motor(100,10000,4.0); // muy buen motor(caro)
		Auto auto = ProgramaAuto.autoInicial();
		usuario = new Usuario();
		usuario.setDinero(1000);
		taller = new Taller();
		indiceTmp=taller.catalogar(motor,4000); 
	}
	public void testCompraFallida(){
		try{
			taller.comprar(usuario,indiceTmp);
			fail("Deberia lanzar excepcion");
		}catch(NotEnoughMoneyException e){
			assertTrue(true);
		} catch (NotInIndexException e) {
			e.printStackTrace();
		}	
	}
	public void testCompraExitosa(){
		Escape escape = new Escape(80);
		
		int indice = taller.catalogar(escape,200);
		try{
			taller.comprar(usuario,indice);
			assertEquals(800, usuario.getDinero());
			assertEquals(escape,usuario.getAuto().getEscape());
		}catch(NotEnoughMoneyException e){
			fail("No deberia lanzar excepcion");
		} catch (NotInIndexException e) {
			fail("La parte existe.");
		}	
	}
	public void testCompraInexistente(){
		try{
			taller.comprar(usuario,2);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		} catch (NotEnoughMoneyException e) {
			fail("Se supone que el dinero no era problema.");
		}	
		try{
			taller.comprar(usuario,-1);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		} catch (NotEnoughMoneyException e) {
			fail("Se supone que el dinero no era problema.");
		}	
	}
	
	public void testMostrarCatalogo(){
		try{
			Iterator partesDisponibles = taller.getCatalogo();
			assertTrue(partesDisponibles.hasNext());
			assertEquals(motor.getClass(), taller.getTipoDeParte(partesDisponibles.next()));
		}catch(NotInIndexException e){
			fail("No deberia lanzar excepcion");
		}	
	}
}
