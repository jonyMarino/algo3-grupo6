package programaAuto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import pista.Pista;
import auto.Auto;
import auto.AutoManual;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

import combustible.Nafta;

import excepciones.WrongPartClassException;


public class ProgramaAuto {

	private static ArrayList<Usuario> usuarios;
	private Pista pista;
	private final int  SEGUNDOSASIMULAR = 10;

	private class SimulacionDeLaCarrera{
		private ArrayList<Auto> listaDeAutos;
		private boolean simulando;
		private Pista laPista;
		private Hashtable<Auto, Integer> posicionesFinales;
		private Integer proximaPosicion;
		
		public SimulacionDeLaCarrera(Pista unaPista) {
			laPista=unaPista;
			simulando = false;
			listaDeAutos = new ArrayList<Auto>();
			posicionesFinales = new Hashtable<Auto, Integer>();
			proximaPosicion = new Integer(1);
		}
		
		public void agregarAutoALaSimulacion(Auto unAuto){
			unAuto.setPosicion(0);
			unAuto.setPista(getPista());
			listaDeAutos.add(unAuto);
		}
		
		public void simular(){
			simulando = true;
			while(simulando){
				simulando = false;
				Iterator<Auto> iteradorAutos = listaDeAutos.iterator();
				while (iteradorAutos.hasNext()){
					Auto unAuto = iteradorAutos.next();
					if(unAuto.getPosicion() >= laPista.getLongitud())
						llegoAlFinal(unAuto);
					else if(unAuto.puedeSeguir()){
							unAuto.simular(SEGUNDOSASIMULAR);
							simulando = true;
					}

				}
			}

		}

		private void llegoAlFinal(Auto unAuto) {
			if(posicionesFinales.get(unAuto) == null){
				posicionesFinales.put(unAuto, proximaPosicion);
				proximaPosicion++;
			}
		}
		
	}
	
	
	public ProgramaAuto () {
		this.pista=null;
	}

	public static Auto autoInicial(){
		Auto auto=null;
		Nafta nafta = new Nafta(85,15);
		TanqueNafta tanque = new TanqueNafta(70, nafta);
		usuarios = new ArrayList<Usuario>();
		try {
			tanque.llenarTanque(70);
		} catch (BoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			Carroceria carroceria = new Carroceria(5,5,250);
			MezcladorNafta mezclador = new MezcladorNafta(100,tanque);
			Escape escape = new Escape(100);
			Motor motor=new Motor(100,5000,mezclador,escape,2.0);
			Rueda rueda = new Rueda(1,0.7,0.4);
			CajaManual caja = new CajaManual();
			try {
				auto = new AutoManual(escape,carroceria,motor,caja,mezclador,tanque,rueda,rueda,rueda,rueda);
			} catch (WrongPartClassException e) {
				e.printStackTrace();
			}

		}catch(BoundsException e){}

		return auto;
	}


	private void setPista(Pista pista){
		this.pista = pista;
	}

	public Pista getPista(){
		return this.pista;
	}
	
	private void generarProximaPista(){
		setPista(new Pista(new Random().nextDouble()*40));
		//TODO: Rápido y feo, pero puede servir para el testing. Alguien creativo que se encargue...
	}
	
	public void nuevoUsuario(String nombre){
		Auto unAuto = autoInicial();
		Usuario unUsuario = new Usuario(nombre, unAuto);
		usuarios.add(unUsuario);
	}
	
	
	public void comenzarCarrera(){
		SimulacionDeLaCarrera unaSimulacion = inicializarCarrera();
		unaSimulacion.simular();
	}

	private SimulacionDeLaCarrera inicializarCarrera() {
		SimulacionDeLaCarrera unaSimulacion = new SimulacionDeLaCarrera(getPista());
		Iterator<Usuario> usuarios = this.usuarios.iterator();

		while(usuarios.hasNext())
			unaSimulacion.agregarAutoALaSimulacion(usuarios.next().getAuto());
		
		return unaSimulacion;
	}
}
