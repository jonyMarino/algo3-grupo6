package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import auto.Auto;

import programaAuto.NoPistaPickedException;
import programaAuto.NotAbleWhileSimulatingException;
import programaAuto.NotInTallerException;
import programaAuto.NotSimulatingException;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.CajaManual;
import vista.PantallaCarrera;
import excepciones.BoundsException;

public class ControladorCarrera implements KeyListener, Observer{

	ProgramaAuto programaAuto;
	Auto unAuto;
	PantallaCarrera laPantalla;
	boolean simulando;
	double tiempo;
	
	public ControladorCarrera(ProgramaAuto programaAuto, PantallaCarrera pantalla) {
		this.programaAuto = programaAuto;
		unAuto = programaAuto.getUsuario().getAuto();
		laPantalla=pantalla;
		simulando = true;
		tiempo = 10;
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
		default: aceleracion=-1;
			break;
		}
		try {
			//TODO: SOLO ES PARA PROBAR LA VISTA, BORRAR CUANDO ESTÉ FUNCIONANDO
			unAuto.presionarAcelerador(aceleracion);
			//unAuto.setPosicion(unAuto.getPosicion()+aceleracion*10);
			//laPantalla.actualizar();
		} catch (BoundsException e1) {
		}
	}


	public void keyReleased(KeyEvent e) {
		double aceleracion=0;
		switch (e.getKeyChar()) {
			case '1': aceleracion=0; 
			case '2': aceleracion=0;
			case '3': aceleracion=0;
			case '4': aceleracion=0; 
			case '5': aceleracion=0;
			break;
		default: break;
		}
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
	}
	
	public void update(Observable o, Object arg) {
		if (o==programaAuto)
		{
		}
	}

	public void comenzar() {

		try {
			programaAuto.entrarAlTaller();
			programaAuto.entrarALaCarrera();
		} catch (NoPistaPickedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotInTallerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotAbleWhileSimulatingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		AuxiliarSimulacion laSimulacion = new AuxiliarSimulacion(programaAuto);
		
		laSimulacion.start();
	
		while (simulando){
			//Thread.sleep((int)(tiempo));
			laPantalla.actualizar();

		}
		
		

	}
	
	
	private class AuxiliarSimulacion extends Thread {
		private ProgramaAuto unPrograma;
		
		public AuxiliarSimulacion(ProgramaAuto unPrograma) {
			this.unPrograma = unPrograma;
		}
		
		public void run() {
			try {
				unPrograma.correr();
			} catch (NotSimulatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	
}
