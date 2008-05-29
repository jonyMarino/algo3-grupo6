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
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.caja.Caja;

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

	public void comprar(Usuario usuario, int indiceDelCatalogo) throws NotEnoughMoneyException,NotInIndexException{
		InformacionParteAutoEnElTaller informacionDeEstaParte = getParteAutoEnElTaller(indiceDelCatalogo);
		if (informacionDeEstaParte.getPrecio() > usuario.getDinero())
			throw new NotEnoughMoneyException("El usuario no posee dinero suficiente para comprar esta parte.");
		else{
			usuario.gastarDinero(informacionDeEstaParte.getPrecio());
			usuario.ensamblarParte(informacionDeEstaParte.getParte());
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
			parteTemporal = unAuto.getEscape();
			unAuto.setEscape((Escape) unaParte);
		}
		else if(unaParte instanceof Carroceria){
			parteTemporal = unAuto.getCarroceria();
			unAuto.setCarroceria((Carroceria) unaParte);
		}
		else if(unaParte instanceof Caja){
			parteTemporal = unAuto.getCaja();
			((Caja)unaParte).setEje(unAuto.getEje());
			((Caja)unaParte).setMotor(unAuto.getMotor());
			unAuto.setCaja((Caja) unaParte);
		}
		else if(unaParte instanceof Motor){
			parteTemporal = unAuto.getMotor();
			unAuto.setMotor((Motor) unaParte);
		}
		
		return parteTemporal;
	}
	
}
