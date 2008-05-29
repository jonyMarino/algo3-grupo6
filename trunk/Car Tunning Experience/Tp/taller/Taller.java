package taller;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	private ArrayList<InformacionParteAutoEnElTaller> listaDePartes;
	
	public Taller(){
		listaDePartes = new ArrayList<InformacionParteAutoEnElTaller>();
		ultimoNumeroDeParteEnElCatalogo=0;
	}
	
	public int catalogar(ParteAuto parte, int precioEnAlgo$) {
		listaDePartes.add(new InformacionParteAutoEnElTaller(parte, precioEnAlgo$));
		ultimoNumeroDeParteEnElCatalogo++;
		return ultimoNumeroDeParteEnElCatalogo;
	}

	public void comprar(Usuario usuario, int indiceDelCatalogo) throws NotEnoughMoneyException,NotInIndexException{
		
	}

	public Iterator getCatalogo() {
		return ((ArrayList)listaDePartes.clone()).iterator();
	}

	public Class getTipoDeParte(Object object) throws NotInIndexException {
		return ((InformacionParteAutoEnElTaller) object).getTipoDeParte();
	}
	
	
	
	

}
