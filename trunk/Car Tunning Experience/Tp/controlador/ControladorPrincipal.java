package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combustible.Nafta;
import excepciones.BoundsException;

import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.TanqueCombustible;
import proveedorDePartes.fabricas.TanqueNafta;
import vistas.VistaGraficador;
import auto.Auto;

public class ControladorPrincipal implements KeyListener{

	private static boolean simulando;
	private static Auto unAuto;
	private static double tiempo;
	private int fastForward;
	VistaGraficador vista1;
	public ControladorPrincipal(){
		
		ProgramaAuto miPrograma = new ProgramaAuto();
		miPrograma.generarProximaPista();
		unAuto = (miPrograma.nuevoUsuario("Lucas")).getAuto();
		FabricaDeTanquesDeCombustible fabricaTanques = new FabricaDeTanquesDeCombustible();
		try {
			fabricaTanques.proponerMotor("Tanque exageradamente grande", 99999, 200, "NAFTA");
		} catch (BoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TanqueCombustible tanque = fabricaTanques.fabricar(fabricaTanques.getModelos().get(1));
		tanque.setCombustible(new Nafta(90, 10));
		unAuto.setTanqueCombustible(tanque);
		try {
			tanque.llenarTanque(99999);
		} catch (BoundsException e) {
		}


		vista1 = new VistaGraficador("Velocidad", 800,600, unAuto);
		vista1.setPixelesPorUnidady(1);
		vista1.setPixelesPorUnidadx(1);
		vista1.addKeyListener(this);
		unAuto.addObserver(vista1);
		simulando = true;
		tiempo =0.05;
		fastForward = 1;
		while (simulando){
					unAuto.simular(tiempo*fastForward);
					try {
					Thread.sleep((int)(tiempo*1000/fastForward));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
			unAuto.getAcelerador().presionar(aceleracion);
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
