import junit.framework.TestCase;
import pista.Pista;
import junit.framework.TestCase;
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
	
	protected void setUp(){
		Motor motor = new Motor(100,10000,4.0); // muy buen motor(caro)
		Auto auto = ProgramaAuto.autoInicial();
		Usuario usuario = new Usuario(auto);
		usuario.setDinero(1000);
		Taller taller = new Taller();
		indiceTmp=taller.catalogar(motor,4000); 
	}
	public void testCompraFallida(){
		try{
			taller.comprar(usuario,indiceTmp);
			fail("Deberia lanzar excepcion");
		}catch(NotEnoughMoneyException e){
			assertTrue(true);
			
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
			fail("Do deberia lanzar excepcion");
		}	
	}
	public void testCompraInexistente(){
		try{
			taller.comprar(usuario,2);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		}	
		try{
			taller.comprar(usuario,-1);
			fail("Deberia lanzar excepcion");
		}catch(NotInIndexException e){
			assertTrue(true);
		}	
	}
	
	public void testMostrarCatalogo(){
		try{
			Iterator piezasDelTaller = taller.getCatalogo();
			assertTrue(piezasDelTaller.hasNext());
			assertEquals(motor, piezasDelTaller.next().parte());
		}catch(NotInIndexException e){
			fail("No deberia lanzar excepcion"););
		}	
	}
}
