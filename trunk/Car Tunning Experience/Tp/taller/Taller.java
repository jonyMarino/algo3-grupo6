package taller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;

import programaAuto.Usuario;

import auto.ParteAuto;
import auto.partesAuto.Motor;

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
		
	}

	public Iterator getCatalogo() {
		return ((ArrayList)listaDePartes.clone()).iterator();
	}

	public Class getTipoDeParte(Object indiceEnElCatalogo) throws NotInIndexException {
		if ( !(indiceEnElCatalogo instanceof Integer))
			throw new NotInIndexException("No existe tal parte, el índice es incorrecto");
		return hashDePartes.get("PARTE_"+indiceEnElCatalogo).getTipoDeParte();
	}
	
}
