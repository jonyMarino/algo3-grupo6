package taller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.management.Descriptor;

import programaAuto.Usuario;
import proveedorDePartes.fabricas.Caja;
import proveedorDePartes.fabricas.Rueda;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.TanqueCombustible;
import proveedorDePartes.fabricas.InformacionDelModelo;
import auto.Auto;
import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import excepciones.PartAlreadyInCatalogException;
import excepciones.WrongPartClassException;


public class Taller {
	
	static public class InformacionParteReserva {
		private ParteAuto parte;
		/*
		 * Es private para que solo pueda instanciarlo Taller
		*/
		private InformacionParteReserva(ParteAuto parte){
			this.parte=parte;	
		}
		public InformacionDelModelo getInformacionModelo(){
			return parte.getInformacionDelModelo();
		}
		
		public double getVidaUtil(){
			return parte.getVidaUtil();
		}
		
		/*
		 * Es protected para que solo pueda llamarlo Taller y classes derivadas
		*/
		private ParteAuto getParte(){
			return parte;
		}
	}
	
	static public class InformacionParteEnAuto extends InformacionParteReserva{
		String ubicacion;
		private InformacionParteEnAuto(ParteAuto parte,String ubicacion){
			super(parte);
			this.ubicacion=ubicacion;
		}
		public String getUbicacion(){
			return new String(ubicacion);	// no queremos que se modifique la ubicacion, por eso la copia.
		}
	}
	ArrayList<ParteAuto> partesDeReserva;
	Usuario usuario;
	
	public Taller(Usuario usuario){
		this.usuario = usuario;
		partesDeReserva = new ArrayList<ParteAuto>();
	}
	// TODO hay que ver como se distingue entre partes del mismo tipo, ej: Ruedas
	public void colocarParteDeReserva(InformacionParteReserva informacionReserva, InformacionParteEnAuto informacionParte){
		
		usuario.getAuto().colocarParte(informacionReserva.getParte(),informacionParte.getUbicacion());
		
		aniadirAReserva(((InformacionParteReserva)informacionParte).getParte());
		
		partesDeReserva.remove(informacionReserva.getParte());
	}
	
	public void ensamblar(){
		usuario.getAuto().ensamblar();
	}
	
	public void aniadirAReserva(final ParteAuto parte){
		partesDeReserva.add(parte);
	}
	
	public Iterator<InformacionParteReserva> getPartesDeReserva(){		
		return new IteradorInformacionReservas();
	}
	
	public Iterator<InformacionParteEnAuto> getPartesEnAuto(){
		LinkedList<InformacionParteEnAuto> lista = new LinkedList<InformacionParteEnAuto>();
		Hashtable<String,ParteAuto> hashPartesAuto=usuario.getAuto().getHashDePartes();
		for(String keyParte :hashPartesAuto.keySet()){
			InformacionParteEnAuto info = new InformacionParteEnAuto(hashPartesAuto.get(keyParte),keyParte);
			lista.add(info);
		}
		return lista.iterator();	
	}
	
	/**
	 * Implementacion de Iterator para recorrer las reservas del Taller
	 *
	 */
	private class IteradorInformacionReservas implements Iterator<InformacionParteReserva>{
		Iterator<ParteAuto> iteradorReservas;
		
		IteradorInformacionReservas(){
			iteradorReservas=Taller.this.partesDeReserva.iterator();
		}	
		
		public InformacionParteReserva next(){
			ParteAuto parte=iteradorReservas.next();
			InformacionParteReserva info = new InformacionParteReserva(parte);
			return info;
		}
		public boolean hasNext(){
			return iteradorReservas.hasNext();
		}
		public void remove(){}
	}
	
	
}
