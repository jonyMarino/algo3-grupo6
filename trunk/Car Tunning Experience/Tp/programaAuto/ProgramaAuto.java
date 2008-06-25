package programaAuto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import pista.Pista;
import proveedorDePartes.ProveedorDePartes;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.FabricaDeCajas;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;
import proveedorDePartes.fabricas.FabricaDeEjes;
import proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedorDePartes.fabricas.FabricaDeMotores;
import proveedorDePartes.fabricas.FabricaDePedales;
import proveedorDePartes.fabricas.FabricaDeRuedas;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.Freno;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.MezcladorNafta;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.Rueda;
import proveedorDePartes.fabricas.TanqueNafta;
import auto.Auto;
import auto.AutoManual;
import combustible.Nafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import excepciones.WrongPartClassException;
import excepciones.WrongUsername;

public class ProgramaAuto extends Observable {

	private static ArrayList<Usuario> usuarios;
	private Pista pista;
	private final int  SEGUNDOSASIMULAR = 10;
	ProveedorDePartes unProveedor;
	
	
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
		
		public void simularTodo(){
			simulando = true;
			while(simulando){
				simularUnTurno();
			}

		}

		public void simularUnTurno(){
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
	       setChanged();
	       notifyObservers();
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
		unProveedor = new ProveedorDePartes();
		usuarios = new ArrayList<Usuario>();		
	}

	public Auto autoInicial(){
		Auto auto=null;
		Nafta nafta = new Nafta(85,15);
		
		ArrayList<InformacionDelModelo> modelos = unProveedor.getModelosDisponibles();
		
		try{
			TanqueNafta tanque = (TanqueNafta) unProveedor.comprar(modelos.get(9));
	
			try {
				tanque.llenarTanque(70);
			} catch (BoundsException e1) {
				e1.printStackTrace();
			}
		
			MezcladorNafta mezclador = (MezcladorNafta) unProveedor.comprar(modelos.get(5));
			Escape escape = (Escape) unProveedor.comprar(modelos.get(4));
			
			Carroceria carroceria = (Carroceria) unProveedor.comprar(modelos.get(2));

			ArrayList<Rueda> ruedas = new ArrayList<Rueda>();
		
			for(int i=0;i<4;i++){
				Rueda rueda = (Rueda) unProveedor.comprar(modelos.get(8));
				ruedas.add(rueda);				
			}
			
			Eje eje = (Eje) unProveedor.comprar(modelos.get(3));
			
			CajaManual caja=(CajaManual) unProveedor.comprar(modelos.get(0));
			
			Motor motor=(Motor) unProveedor.comprar(modelos.get(6));
			
			Freno freno =  (Freno) unProveedor.comprar(modelos.get(7));
			
			try {
				auto = new AutoManual(escape, carroceria, motor, (CajaManual) caja, (MezcladorNafta) mezclador, tanque, ruedas.get(0), ruedas.get(1),ruedas.get(2),ruedas.get(3), eje, freno);
			} catch (WrongPartClassException e) {
				e.printStackTrace();
			}
			auto.setPista(pista);
		}
		catch (NoSuchModelException e)
		{
			e.printStackTrace();  //TODO: bastante sucio
		} catch (NotEnoughMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auto;
	}


	private void setPista(Pista pista){
		this.pista = pista;
	}

	public Pista getPista(){
		return this.pista;
	}
	
	public void generarProximaPista(){
		setPista(new Pista(new Random().nextDouble()*40));
		//TODO: Rápido y feo, pero puede servir para el testing. Alguien creativo que se encargue...
	}
	
	public Usuario nuevoUsuario(String nombre) throws WrongUsername {
		Auto unAuto = autoInicial();
		unAuto.setPista(getPista());
		Usuario unUsuario = new Usuario(nombre);
		unUsuario.setAuto(unAuto);
		usuarios.add(unUsuario);
					
		return unUsuario;
	}
	
	
	
	
	public void comenzarCarrera(){
		SimulacionDeLaCarrera unaSimulacion = inicializarCarrera();
		unaSimulacion.simularTodo();
	}

	private SimulacionDeLaCarrera inicializarCarrera() {
		SimulacionDeLaCarrera unaSimulacion = new SimulacionDeLaCarrera(getPista());
		Iterator<Usuario> usuarios = this.usuarios.iterator();

		while(usuarios.hasNext())
			unaSimulacion.agregarAutoALaSimulacion(usuarios.next().getAuto());
		
		return unaSimulacion;
	}
}
