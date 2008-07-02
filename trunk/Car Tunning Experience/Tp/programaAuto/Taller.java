package programaAuto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import nu.xom.Elements;

import nu.xom.Element;

import programaAuto.Auto.Ubicacion;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import proveedorDePartes.fabricas.RegistroDeModelos;
import excepciones.IncorrectPartForUbicationException;
import excepciones.NoSuchModelException;
import excepciones.UbicationUnkownException;

public class Taller {
	
	static public class InformacionParteReserva {
		private ParteAuto parte;

		private InformacionParteReserva(ParteAuto parte){
			this.parte=parte;	
		}
		public InformacionDelModelo getInformacionModelo(){
			return parte.getInformacionDelModelo();
		}
		
		public double getVidaUtil(){
			return parte.getVidaUtil();
		}
		
		private ParteAuto getParte(){
			return parte;
		}
	}
	
	static public class InformacionParteEnAuto extends InformacionParteReserva{
		Auto.Ubicacion ubicacion;
		
		private InformacionParteEnAuto(ParteAuto parte,Auto.Ubicacion ubicacion){
			super(parte);
			this.ubicacion=ubicacion;
		}
		public Auto.Ubicacion getUbicacion(){
			return ubicacion; //TODO: PARA PASAR UNA COPIA LO ANTERIOR GENERABA UN BUG... 
			// si alguien se toma el trabajo de clonar un string ...
		}
	}
	
	ArrayList<ParteAuto> partesDeReserva=new ArrayList<ParteAuto>();;
	Usuario usuario;
	
	public Taller(Usuario usuario){
		this.usuario = usuario;
	}

	public Taller(Usuario usuario, CadenaDeFabricas fabrica, Element elemento) {
		this.usuario = usuario;
		Element taller=elemento.getFirstChildElement("Taller");
		Elements partes = taller.getChildElements();
		for(int i=0;i<partes.size();i++){
			Element parteElement= partes.get(i);
			String nombreModelo = parteElement.getFirstChildElement("modelo").getValue();
			InformacionDelModelo informacionDelModelo=RegistroDeModelos.getInstance().getInformacion(nombreModelo);
			try {
				ParteAuto parteAuto = fabrica.fabricar(informacionDelModelo);
				parteAuto.restaurar(parteElement);
				aniadirAReserva(parteAuto);
			} catch (NoSuchModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void colocarParteDeReserva(InformacionParteReserva informacionReserva, InformacionParteEnAuto informacionParte)throws IncorrectPartForUbicationException{
		try{
			usuario.getAuto().colocarParte(informacionReserva.getParte(),informacionParte.getUbicacion());
		}catch(UbicationUnkownException e){
			throw new RuntimeException("Falta ensamblador para: "+e.getUbicacion());
		}
		
		aniadirAReserva(((InformacionParteReserva)informacionParte).getParte());
		
		partesDeReserva.remove(informacionReserva.getParte());
	}
	
	void ensamblar(){
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
		Hashtable<Auto.Ubicacion,ParteAuto> hashPartesAuto=usuario.getAuto().getHashDePartes();
		for(Auto.Ubicacion keyParte :hashPartesAuto.keySet()){
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

	public Usuario getUsuario() {
		return usuario;
	}
	
	//TODO: Lo agregue por conveniencia
	//TODO: comentar	
	public Iterator<ParteAuto> getPartesReserva(){
		return partesDeReserva.iterator();
	}

	public Element getElement() {
		Element auto=new Element("Taller");
		//partes
		for(ParteAuto parteAuto:partesDeReserva){
			Element parteElemento = new Element("Parte de reserva");
			Element modelo = new Element("modelo");
			String nombre = parteAuto.getInformacionDelModelo().getModelo();
			modelo.appendChild(nombre);
			Element parte = parteAuto.getElement();
			parteElemento.appendChild(modelo);
			parteElemento.appendChild(parte);
			auto.appendChild(parteElemento);
		}
		return auto;
	}
	
}
