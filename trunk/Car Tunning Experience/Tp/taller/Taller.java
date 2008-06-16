package taller;

import java.util.ArrayList;
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
	// TODO hay que ver como se distingue entre partes del mismo tipo, ej: Ruedas
	public void colocarParteDeReserva(int parteEnReserva, String keyParteEnAuto){
		ParteAuto parteAutoAColocar = partesDeReserva.get(parteEnReserva);
		if(!usuario.getAuto().getHashDePartes().containsKey(keyParteEnAuto))
			throw new RuntimeException();
		partesDeReserva.remove(parteEnReserva);
		if( parteAutoAColocar instanceof Motor)	//TODO habria que hacer para cada uno
			ensamblar((Motor)parteAutoAColocar,keyParteEnAuto);
		if( parteAutoAColocar instanceof Rueda)
			ensamblar((Rueda)parteAutoAColocar,keyParteEnAuto);
			
	}
	
	public void ensamblar(Motor motor,String keyParteEnAuto){
		Auto auto = usuario.getAuto();		
		partesDeReserva.add(auto.getMotor());
		auto.setMotor(motor);	// lo coloco
		motor.setCaja(auto.getCaja());
		motor.setEscape(auto.getEscape());
		motor.setMezclador(auto.getMezclador());
	}
	
	public Iterator<ParteAuto> getPartesDeReserva(){
		return ((List)partesDeReserva.clone()).iterator();
	}
	
	public Hashtable<String,ParteAuto> getPartesEnAuto(){
		return usuario.getAuto().getHashDePartes();
		
	}
	
	
}
