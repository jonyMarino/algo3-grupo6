package pruebas;

import java.util.ArrayList;

import junit.framework.TestCase;
import pista.Pista;
import auto.AutoManual;
import auto.Auto;
import programaAuto.ProgramaAuto; 
import auto.partesAuto.BoundsException;


public class VelocidadTest extends TestCase {
	
	Pista pista=new Pista(200);
	/*protected void setUp() throws Exception {
		super.setUp();
		auto= (AutoManual)ProgramaAuto.autoInicial();
		auto.setPista(pista);
	}
	*/
	public void testCambio()throws BoundsException {
		ArrayList<Auto> autos = new ArrayList<Auto>();
		for(int i=0;i<5;i++){
			AutoManual auto=(AutoManual)ProgramaAuto.autoInicial(); 
			auto.setPista(pista);
			auto.setCambio(i+1);
			auto.presionarAcelerador(1);
			auto.simular(0.5);		
			autos.add(auto);
		}
		for(int i=0;i<4;i++)	// compruebo que empezando en un cambio mas grande acelero mas lento
			assertTrue(autos.get(i).getKilometrosPorHora()> autos.get(i+1).getKilometrosPorHora());
				
		/*for(int i=0;i<10;i++){		// de 0 a 100 en 10 seg aprox.
			auto.simular(0.5);
			System.out.println( auto.getKilometrosPorHora());
		}
		auto.setCambio(5);
		for(int i=0;i<10;i++){		// de 0 a 100 en 10 seg aprox.
			auto.simular(0.5);
			System.out.println( auto.getKilometrosPorHora());
		}
		assertEquals(100.0,auto.getKilometrosPorHora());
	*/
	}

}
