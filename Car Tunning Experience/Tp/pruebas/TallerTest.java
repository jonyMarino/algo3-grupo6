package pruebas;

import java.util.Iterator;
import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import excepciones.WrongPartClassException;
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
import auto.partesAuto.caja.CajaAutomatica;
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
		usuario = new Usuario(auto);
		usuario.setDinero(1000);
		Taller taller = new Taller();
		indiceTmp=taller.catalogar(motor,4000);
		taller = new Taller();
		indiceTmp=taller.catalogar(motor,4000, "Motor Super Ultra BlahBlah");
	}
	public void testCompraFallida(){
		try{
			taller.comprar(usuario,indiceTmp);
			fail("Deberia lanzar excepcion");
		}catch(NotEnoughMoneyException e){
			assertTrue(true);
		} catch (NotInIndexException e) {
			fail("No debería lanzar esta excepción, la parte existe.");
		} catch (WrongPartClassException e) {
			e.printStackTrace();
		}
	}

	public void testCompraExitosa(){
		Escape escape = new Escape(80);

		int indice = taller.catalogar(escape,200);
		int indice = taller.catalogar(escape,200, "Un escape maso");
		try{
			taller.comprar(usuario,indice);
			assertEquals(800.0, usuario.getDinero());
			assertEquals(escape,usuario.getAuto().getEscape());
		}catch(NotEnoughMoneyException e){
			fail("No deberia lanzar excepcion");
		} catch (NotInIndexException e) {
			fail("Se supone que la parte existe.");
		} catch (WrongPartClassException e) {
			e.printStackTrace();
		}
	}

	public void testCatalogarDosVeces(){
		Escape escape = new Escape(80);
		Escape otroEscape = new Escape(80);
		int indice = taller.catalogar(escape,200, "Un escape maso");
		int otroindice = taller.catalogar(otroEscape,200, "Un escape maso");
		assertEquals(indice, otroindice);
		try{
			taller.comprar(usuario,indice);
			assertEquals(800.0, usuario.getDinero());
			assertEquals(escape,usuario.getAuto().getEscape());
			taller.comprar(usuario,indice);
			assertEquals(600.0, usuario.getDinero());
			//TODO: anotacion personal para no olvidar modificar algo, NO BORRAR
			assertEquals(escape,usuario.getAuto().getEscape());
		}catch(NotEnoughMoneyException e){
			fail("No deberia lanzar excepcion");
		} catch (NotInIndexException e) {
			fail("Se supone que la parte existe.");
		} catch (WrongPartClassException e) {
			e.printStackTrace();
		}
	}

	public void testComprarParteErronea(){
		CajaAutomatica caja = new CajaAutomatica(null, null);
		int indice = taller.catalogar(caja,200, "Cajita Automatica");
		try{
			taller.comprar(usuario,indice);
			assertEquals(800.0, usuario.getDinero());
		}catch(NotEnoughMoneyException e){
			fail("No deberia lanzar excepcion");
		} catch (NotInIndexException e) {
			fail("Se supone que la parte existe.");
		} catch (WrongPartClassException e) {
			assertTrue(true);       //No puedo ponerle una CajaAutomatica al Auto Manual ¿O si?
		}
	}

	public void testCompraInexistente(){
		try{
			taller.comprar(usuario,2);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		}catch (NotEnoughMoneyException e) {
			fail("Se supone que el dinero no era problema.");
		} catch (WrongPartClassException e) {
			e.printStackTrace();
		}
		try{
			taller.comprar(usuario,-1);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		} catch (NotEnoughMoneyException e) {
			fail("Se supone que el dinero no era problema.");
		} catch (WrongPartClassException e) {
			e.printStackTrace();
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
			fail("No deberia lanzar excepcion");
		}
	}

