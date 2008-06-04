package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combustible.Nafta;

import programaAuto.ProgramaAuto;
import vistas.VistaGraficador;
import auto.Auto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.tanque.TanqueCombustible;
import auto.partesAuto.tanque.TanqueNafta;

public class ControladorPrincipal implements KeyListener{

	private static boolean simulando;
	private static Auto unAuto;
	private static int tiempo;
	
	public ControladorPrincipal(){
		
		ProgramaAuto miPrograma = new ProgramaAuto();
		miPrograma.generarProximaPista();
		unAuto = (miPrograma.nuevoUsuario("Lucas")).getAuto();

		TanqueCombustible tanque = new TanqueNafta(99999, new Nafta(100,100));
		
		VistaGraficador vista1 = new VistaGraficador("Velocidad", 800,600, unAuto);
		vista1.setPixelesPorUnidady(80);
		vista1.setPixelesPorUnidadx(1);
		vista1.addKeyListener(this);
		unAuto.addObserver(vista1);
		simulando = true;
		tiempo =100;
		while (simulando){
					unAuto.simular(tiempo);
		}
		
		vista1.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		double aceleracion=0;
		switch (e.getKeyChar()) {
		case '1': aceleracion=0.001; break;
		case '2': aceleracion=0.005; break;
		case '3': aceleracion=0.01; break;
		case '4': aceleracion=0.05; break;
		case '5': aceleracion=0.1; break;
		default: aceleracion=0;
			break;
		}
		
		try {
			unAuto.getAcelerador().presionar(aceleracion);
		} catch (BoundsException e1) {
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		double aceleracion=0;
		switch (e.getKeyChar()) {
		case '1': aceleracion=0; 
		case '2': aceleracion=0;
		case '3': aceleracion=0;
		case '4': aceleracion=0; 
		case '5': aceleracion=0;
		try {
					unAuto.getAcelerador().presionar(0);
				} catch (BoundsException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} break;
		default: break;
		}
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
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
		
	}

}
