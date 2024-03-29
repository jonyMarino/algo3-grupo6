package pruebas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import programaAuto.Auto;
import programaAuto.ProgramaAuto;
import programaAuto.ProgramaAuto.TipoAuto;
import proveedores.proveedorDeCombustibles.FabricaDeNafta;
import proveedores.proveedorDeCombustibles.Nafta;
import proveedores.proveedorDePartes.fabricas.CajaManual;
import proveedores.proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedores.proveedorDePartes.fabricas.TanqueCombustible;
import vista.VistaGraficador;
import excepciones.BoundsException;
import excepciones.NoPistaPickedException;
import excepciones.NoSuchModelException;
import excepciones.NotAbleWhileSimulatingException;
import excepciones.NotContainedPistaException;
import excepciones.NotInTallerException;
import excepciones.PartBrokenException;
import excepciones.PistaPickedException;
import excepciones.TankIsFullException;
import excepciones.WrongUserNameException;

public class ControladorDePrueba implements KeyListener{

	private static boolean simulando;
	private static Auto unAuto;
	private static double tiempo;
	private int fastForward;
	VistaGraficador vista1;
	public ControladorDePrueba(){
		
		ProgramaAuto miPrograma = null;
		try {
			miPrograma = new ProgramaAuto("PROGRAMAPRUEBA",TipoAuto.MANUAL);
		} catch (WrongUserNameException e3) {
			e3.printStackTrace();
		}
		miPrograma.generarProximaPista();
		unAuto = (miPrograma.getUsuario()).getAuto();
		FabricaDeTanquesDeCombustible fabricaTanques = new FabricaDeTanquesDeCombustible();
		try {
			fabricaTanques.proponerTanque("Tanque exageradamente grande", 99999, 200, "NAFTA");
		} catch (BoundsException e1) {
			e1.printStackTrace();
		}

		try {
		TanqueCombustible tanque = fabricaTanques.fabricar(fabricaTanques.getModelos().get(1));
		FabricaDeNafta fabricaDeNafta = new FabricaDeNafta();
		Nafta nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));	
		tanque.setCombustible(nafta);
		unAuto.setTanqueCombustible(tanque);

			try {
				tanque.llenarTanque(99999);
			} catch (TankIsFullException e) {
				e.printStackTrace();
			} catch (PartBrokenException e) {
				e.printStackTrace();
			}
		} catch (NoSuchModelException e) {
		} catch (BoundsException e) {
			e.printStackTrace();
		}


		vista1 = new VistaGraficador("Velocidad", 800,600, unAuto);
		vista1.setPixelesPorUnidady(1);
		vista1.setPixelesPorUnidadx(1);
		vista1.addKeyListener(this);
		unAuto.addObserver(vista1);
		simulando = true;
		tiempo =0.5;
		fastForward = 1;
		unAuto.ensamblar();

		try {
			miPrograma.setPista(miPrograma.generarProximaPista());
		} catch (NotContainedPistaException e1) {
			e1.printStackTrace();
		} catch (PistaPickedException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			miPrograma.entrarAlTaller();
			miPrograma.entrarALaCarrera();
		} catch (NoPistaPickedException e1) {
			e1.printStackTrace();
		} catch (NotAbleWhileSimulatingException e1) {
			e1.printStackTrace();
		} catch (NotInTallerException e) {
			e.printStackTrace();
		}
	


		while (simulando){
					unAuto.simular(tiempo*fastForward);
					try {
					Thread.sleep((int)(tiempo*1000/fastForward));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		}
		
		vista1.dispose();
	}


	public void keyPressed(KeyEvent e) {
		double aceleracion=0;
		switch (e.getKeyChar()) {
		case '0': aceleracion=0; break;
		case '1': aceleracion=0.01; break;
		case '2': aceleracion=0.05; break;
		case '3': aceleracion=0.1; break;
		case '4': aceleracion=0.5; break;
		case '5': aceleracion=1; break;
		case '+': tiempo+=0.005; aceleracion = -1; break; 
		case '-': tiempo-=0.005; aceleracion = -1;break;  //PELIGROSOS
		default: aceleracion=-1;
			break;
		}
		try {
			unAuto.acelerar(aceleracion);
		} catch (BoundsException e1) {
		}


	}


	public void keyReleased(KeyEvent e) {
/*		double aceleracion=0;
		switch (e.getKeyChar()) {
		case '1': aceleracion=0; 
		case '2': aceleracion=0;
		case '3': aceleracion=0;
		case '4': aceleracion=0; 
		case '5': aceleracion=0;
		try {
					unAuto.getAcelerador().presionar(0);
				} catch (BoundsException e1) {
				} break;
		default: break;
		}
		
*/
	}


	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'c'){
			int cambioActual=unAuto.getCaja().getCambio();
			((CajaManual)unAuto.getCaja()).setCambioManual(cambioActual+1);
		}
		if(e.getKeyChar() == 'v'){
			int cambioActual=unAuto.getCaja().getCambio();
			((CajaManual)unAuto.getCaja()).setCambioManual(cambioActual-1);
		}
		if(e.getKeyChar() == 'q'){
			simulando=false;
		}
		if(e.getKeyChar() == ' '){
			if(fastForward==1)
				fastForward=100;
			else fastForward =1;
		}
		if(e.getKeyChar() == 'y'){
			if (vista1.getPixelesPorUnidady()>1)
				vista1.setPixelesPorUnidady(vista1.getPixelesPorUnidady()-1);
		}
		if(e.getKeyChar() == 'Y'){
			vista1.setPixelesPorUnidady(vista1.getPixelesPorUnidady()+1);
		}
	}

}
