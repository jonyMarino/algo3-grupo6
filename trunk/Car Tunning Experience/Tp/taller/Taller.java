package taller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import excepciones.WrongPartClassException;

import programaAuto.Usuario;

import auto.Auto;
import auto.ParteAuto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;

public class Taller {

	private int ultimoNumeroDeParteEnElCatalogo;
	private class InformacionParteAutoEnElTaller{
		int precio;
		ParteAuto parte;
		
		public InformacionParteAutoEnElTaller(ParteAuto parte, int precioEnAlgo$){
			precio = precioEnAlgo$;
			this.parte = parte;
		}
		
		public Class getTipoDeParte(){
			return parte.getClass();
		}
		
		public int getPrecio(){
			return precio;
		}
		
		public ParteAuto getParte(){
			return parte;
		}
	}
	
	private ArrayList<Integer> listaDePartes;
	private Hashtable<String, InformacionParteAutoEnElTaller> hashDePartes;
	
	public Taller(){
		listaDePartes = new ArrayList<Integer>();
		ultimoNumeroDeParteEnElCatalogo=0;
		hashDePartes = new Hashtable<String, InformacionParteAutoEnElTaller>();
	}
	
	public int catalogar(ParteAuto parte, int precioEnAlgo$) {
		ultimoNumeroDeParteEnElCatalogo++;
		listaDePartes.add(new Integer(ultimoNumeroDeParteEnElCatalogo));
		String clave = new String("PARTE_"+ ultimoNumeroDeParteEnElCatalogo);
		InformacionParteAutoEnElTaller informacion =  new InformacionParteAutoEnElTaller(parte, precioEnAlgo$);
		hashDePartes.put(clave, informacion);
		return ultimoNumeroDeParteEnElCatalogo;
	}

	public void comprar(Usuario usuario, int indiceDelCatalogo) throws NotEnoughMoneyException,NotInIndexException, WrongPartClassException{
		InformacionParteAutoEnElTaller informacionDeEstaParte = getParteAutoEnElTaller(indiceDelCatalogo);
		if (informacionDeEstaParte.getPrecio() > usuario.getDinero())
			throw new NotEnoughMoneyException("El usuario no posee dinero suficiente para comprar esta parte.");
		else{
			usuario.gastarDinero(informacionDeEstaParte.getPrecio());
			//usuario.ensamblarParte(informacionDeEstaParte.getParte());
			ensamblarParteAuto(usuario.getAuto(), informacionDeEstaParte.getParte());
			eliminarParteAutoEnElTaller(indiceDelCatalogo);
			
		}
	}

	public Iterator getCatalogo() {
		return ((ArrayList)listaDePartes.clone()).iterator();
	}

	public Class getTipoDeParte(Object indiceEnElCatalogo) throws NotInIndexException {
		if ( !(indiceEnElCatalogo instanceof Integer))
			throw new NotInIndexException("No existe tal parte, el índice es incorrecto");
		return getParteAutoEnElTaller((Integer)(indiceEnElCatalogo)).getTipoDeParte();
	}
	
	private InformacionParteAutoEnElTaller getParteAutoEnElTaller(Integer indiceEnElCatalogo) throws NotInIndexException{
		if(! (hashDePartes.containsKey("PARTE_"+indiceEnElCatalogo)) )
			throw new NotInIndexException("No existe ese número de parte en el catalogo.");
		else return hashDePartes.get("PARTE_"+indiceEnElCatalogo);
		
	}
	
	private void eliminarParteAutoEnElTaller(Integer indiceEnElCatalogo) throws NotInIndexException{
		if(! (hashDePartes.containsKey("PARTE_"+indiceEnElCatalogo)) )
			throw new NotInIndexException("No existe ese número de parte en el catalogo.");
		else hashDePartes.remove("PARTE_"+indiceEnElCatalogo);
	}
	
	public ParteAuto ensamblarParteAuto(Auto unAuto, ParteAuto unaParte) throws WrongPartClassException{
		ParteAuto parteTemporal = unaParte;
		
		if(unaParte instanceof Escape){
			return ensamblarEscape(unAuto, (Escape) unaParte);
		}
		else if(unaParte instanceof Carroceria){
			return ensamblarCarroceria(unAuto, (Carroceria) unaParte);
		}
		else if(unaParte instanceof Caja){
			return ensamblarCaja(unAuto, (Caja)unaParte);
		}
		else if(unaParte instanceof Motor){
			return ensamblarMotor(unAuto, (Motor)unaParte);
		}
		else if( unaParte instanceof Eje){
			return ensamblarEje(unAuto, (Eje)unaParte);
		}
		else if( unaParte instanceof Mezclador){
			return ensamblarMezclador(unAuto, (Mezclador)unaParte);
		}
		
		return parteTemporal;
	}
	
	private ParteAuto ensamblarMezclador(Auto unAuto, Mezclador unMezclador) throws WrongPartClassException {
		Mezclador parteTemporal = unAuto.getMezclador();
		if( ! (unMezclador.getClass() == parteTemporal.getClass()) ){
			throw new WrongPartClassException("No se puede ensamblar la parte. Se requiere de " + parteTemporal.getClass() + " pero se tiene " + unMezclador.getClass());
		}
		return parteTemporal;
	}

	private ParteAuto ensamblarEje(Auto unAuto, Eje unEje) {
		Eje parteTemporal = unAuto.getEje();
		unEje.setRuedaTrasera(parteTemporal.getRuedaTrasera());
		parteTemporal.setRuedaTrasera(null);
		unAuto.setEje(unEje);
		return parteTemporal;
	}

	private Escape ensamblarEscape(Auto unAuto, Escape unEscape){
		Escape parteTemporal = unAuto.getEscape();
		unAuto.setEscape(unEscape);
		return parteTemporal;
	}
	
	private Carroceria ensamblarCarroceria(Auto unAuto, Carroceria unaCarroceria){
		Carroceria parteTemporal = unAuto.getCarroceria();
		unAuto.setCarroceria(unaCarroceria);
		return parteTemporal;
	}

	private Caja ensamblarCaja(Auto unAuto, Caja unaCaja) throws WrongPartClassException{
		Caja parteTemporal = unAuto.getCaja();
		unaCaja.setEje(unAuto.getEje());
		unaCaja.setMotor(unAuto.getMotor());
		parteTemporal.setMotor(null);
		parteTemporal.setEje(null);
		unAuto.setCaja(unaCaja);
		return parteTemporal;
	}
	private Motor ensamblarMotor(Auto unAuto, Motor unMotor){
		Motor parteTemporal = unAuto.getMotor();
		unMotor.setCaja(unAuto.getCaja());
		unMotor.setEscape(unAuto.getEscape());
		unMotor.setMezclador(unAuto.getMezclador());
		parteTemporal.setCaja(null);
		parteTemporal.setEscape(null);
		parteTemporal.setMezclador(null);
		unAuto.setMotor(unMotor);
		return parteTemporal;
	}
}
