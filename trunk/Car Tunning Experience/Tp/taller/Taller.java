package taller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.management.Descriptor;

import programaAuto.Usuario;
import proveedorDePartes.fabricas.Caja;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.TanqueCombustible;
import auto.Auto;
import excepciones.BoundsException;
import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import excepciones.PartAlreadyInCatalogException;
import excepciones.WrongPartClassException;


public class Taller {
	ArrayList<ParteAuto> partesDeReserva;
	Usuario usuario;
	
	public Taller(Usuario usuario){
		this.usuario = usuario;
		partesDeReserva = new ArrayList<ParteAuto>();
	}
	// hay que ver como se distingue entre partes del mismo tipo, ej: Ruedas
	public void colocarParteDeReserva(int numeroParte){
		ParteAuto parteAuto = partesDeReserva.get(numeroParte);
		partesDeReserva.remove(numeroParte);
		if( parteAuto instanceof Motor)	//de ejemplo, habria que hacer para cada uno
			ensamblar((Motor)parteAuto);
	}
	
	public void ensamblar(Motor motor){
		Auto auto = usuario.getAuto();		
		partesDeReserva.add(auto.getMotor());
		auto.setMotor(motor);	// lo coloco
		motor.setCaja(auto.getCaja());
		motor.setEscape(auto.getEscape());
		motor.setMezclador(auto.getMezclador());
	}
	
	public Iterator getInformacionPartesDeReserva(){
		return new InformacionParteAutoIterator(partesDeReserva);
	}
	
	public Iterator getInformacionPartesEnAuto(){
		List partesEnAuto = usuario.getAuto().getListaPartes();
		return new InformacionParteAutoIterator(partesEnAuto);
	}
	
	private static class InformacionParteAutoIterator implements Iterator{
		Iterator iteradorPartesDeAuto;
		public InformacionParteAutoIterator(List<ParteAuto> partesDeAuto){
			iteradorPartesDeAuto = partesDeAuto.iterator();
		}
		public Object next(){
			return new InformacionDelModelo(iteradorPartesDeAuto.next());
		}
		public boolean hasNext(){
			return iteradorPartesDeAuto.hasNext();
		}
	}
	
	
}