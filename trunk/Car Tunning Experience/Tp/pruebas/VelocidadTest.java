package pruebas;

import java.util.ArrayList;

import excepciones.BoundsException;
import excepciones.WrongUserNameException;

import junit.framework.TestCase;
import auto.AutoManual;
import programaAuto.Auto;
import programaAuto.Pista;
import programaAuto.ProgramaAuto; 


public class VelocidadTest extends TestCase {
	
	Pista pista;
	protected void setUp() throws Exception {
		super.setUp();
		pista =new Pista("Pista");
	}
	
	public void testCambio()throws BoundsException {
		ProgramaAuto programa = null;
		try {
			programa = new ProgramaAuto("PruebaVelocidad");
		} catch (WrongUserNameException e) {
			e.printStackTrace();
		}
		ArrayList<Auto> autos = new ArrayList<Auto>();
		for(int i=0;i<5;i++){
			AutoManual auto=(AutoManual)programa.autoInicial(); 
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
