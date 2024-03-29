package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import programaAuto.Auto;
import programaAuto.ProgramaAuto;
import programaAuto.Usuario;
import programaAuto.ProgramaAuto.SimuladorCarrera;
import proveedores.proveedorDePartes.fabricas.Caja;
import vista.PantallaCarrera;
import excepciones.BoundsException;
import excepciones.NoPistaPickedException;
import excepciones.NotInTallerException;
import excepciones.NotSimulatingException;

/**
 *
 * Clase que se encarga de controlar todos los aspectos de la interaccion
 * del usuario con la carrera.
 * Administra las vistas de la Carrera y los eventos de teclado durante la misma.
 *
 */

public class ControladorCarrera implements KeyListener{

	ProgramaAuto programaAuto;
	Auto unAuto;
	ArrayList<Usuario> listaDeCompetidores;
	PantallaCarrera laPantalla;
	boolean simulando;
	double tiempo;
	private SimuladorCarrera simulacion;
	
	/**
	 * Crea un nuevo controldor para el programa auto especificado
	 * @param programaAuto La instancia de ProgramaAuto
	 * @param pantalla El panel que captar� los eventos de teclado.
	 * @see PantallaCarrera
	 * @see ProgramaAuto
	 */
	public ControladorCarrera(ProgramaAuto programaAuto, PantallaCarrera pantalla) {
		this.programaAuto = programaAuto;
		unAuto = programaAuto.getUsuario().getAuto();
		laPantalla=pantalla;
		simulando = true;
		tiempo = 10;
		simulacion = null;
		
		try {

			simulacion = programaAuto.entrarALaCarrera();
		} catch (NoPistaPickedException e1) {
			e1.printStackTrace();
		} catch (NotInTallerException e1) {
			e1.printStackTrace();
		}
		
		listaDeCompetidores = (ArrayList<Usuario>) simulacion.getCompetidores();
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
			unAuto.acelerar(aceleracion);
		} catch (BoundsException e1) {
		}
	}


	public void keyReleased(KeyEvent e) {
		double aceleracion=-1;
		switch (e.getKeyChar()) {
			case '1': aceleracion=0; 
			case '2': aceleracion=0;
			case '3': aceleracion=0;
			case '4': aceleracion=0; 
			case '5': aceleracion=0;
			break;
		default: break;
		}
		try {
			unAuto.acelerar(aceleracion);
		} catch (BoundsException e1) {
		}
	}


	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'c'){
			int cambioActual=unAuto.getCaja().getCambio();
			((Caja)unAuto.getCaja()).setCambio(cambioActual+1);
		}
		if(e.getKeyChar() == 'v'){
			int cambioActual=unAuto.getCaja().getCambio();
			((Caja)unAuto.getCaja()).setCambio(cambioActual-1);
		}
		if(e.getKeyChar() == 'q'){
			simulando=false;
		}
	}


    /**
     *
     *Comienza la simulacion de la carrera.
     *
     */
	public void comenzar() {


	
		AuxiliarSimulacion laSimulacion = new AuxiliarSimulacion(programaAuto);
		AuxiliarActualizacion sim = new AuxiliarActualizacion(laPantalla, simulacion);
		Timer timer = new Timer();

		laSimulacion.start();
		
		while(!simulacion.estaCorriendo()){
		}
			
	    timer.schedule(sim, 0, 16);
	}
	
	
	private class AuxiliarActualizacion extends TimerTask{
		private PantallaCarrera laPantalla;
		private SimuladorCarrera simulacion;
		public AuxiliarActualizacion(PantallaCarrera laPantalla, SimuladorCarrera simulacion) {
			this.laPantalla =laPantalla;
			this.simulacion = simulacion;
		}
		
		public void run() {
			if (simulacion.estaCorriendo())
				laPantalla.actualizar();
			else {
				this.cancel();
			}
			
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
				e.printStackTrace();
			}
			finalizarCarrera();
		
		}

		private void finalizarCarrera() {
			unAuto.getCaja().setCambio(0);
			try {
				unAuto.acelerar(0);
			} catch (BoundsException e1) {
			}
			laPantalla.finalizarCarrera();
			
		}
		
	}
/**
 * Devuelve una lista con todos los competidores que intervinen en la carrera.
 * @return Un ArrayList de Usuarios
 * @see Usuario
 */
	public ArrayList<Usuario> getListaDeCompetidores() {
		return listaDeCompetidores;
	}
	
}
