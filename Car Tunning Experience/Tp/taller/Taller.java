package taller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import programaAuto.Usuario;
import auto.Auto;
import auto.ParteAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.tanque.TanqueCombustible;
import excepciones.NotEnoughMoneyException;
import excepciones.NotInIndexException;
import excepciones.PartAlreadyInCatalogException;
import excepciones.WrongPartClassException;


public class Taller {

	private class InformacionParteAutoEnElTaller{
		int precio;
		int cantidad; //N�mero de partes con estas caracter�sticas
		ParteAuto parte;
		private String descripcion;
				
		public InformacionParteAutoEnElTaller(ParteAuto parte, int precioEnAlgo$, String descripcion){
			precio = precioEnAlgo$;
			this.parte = parte;
			this.descripcion = descripcion;
			cantidad = 1;
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
		
		public String getDescripcion(){
			return descripcion;
		}
		
		public int getCantidad(){
			return cantidad;
		}
		
		public int incrementarCantidad(){
			return ++cantidad;
		}
		
		public int decrementarCantidad() throws BoundsException{
			if (cantidad<=0)
				throw new BoundsException("No hay mas partes con esas caracter�sticas disponibles");
			return --cantidad;
		}
	}
	
	private int ultimoNumeroDeParteEnElCatalogo;
	private ArrayList<Integer> listaDeNumerosDePartes;
	private Hashtable<String, InformacionParteAutoEnElTaller> hashDePartes;
	
	
	/**
	 * 
	 */
	public Taller(){
		listaDeNumerosDePartes = new ArrayList<Integer>();
		ultimoNumeroDeParteEnElCatalogo=0;
		hashDePartes = new Hashtable<String, InformacionParteAutoEnElTaller>();
	}
	
	/**
	 * @param parte
	 * @param precioEnAlgo$
	 * @param descripcion
	 * @return
	 */
	public int catalogar(ParteAuto parte, int precioEnAlgo$, String descripcion) {
		try{
			buscarParteRepetida(parte, precioEnAlgo$, descripcion);
			ultimoNumeroDeParteEnElCatalogo++;
			listaDeNumerosDePartes.add(new Integer(ultimoNumeroDeParteEnElCatalogo));
			String clave = generarClave(ultimoNumeroDeParteEnElCatalogo);
			InformacionParteAutoEnElTaller informacion =  new InformacionParteAutoEnElTaller(parte, precioEnAlgo$, descripcion);
			hashDePartes.put(clave, informacion);
			return ultimoNumeroDeParteEnElCatalogo;
		}catch (PartAlreadyInCatalogException miExcepcion){
			InformacionParteAutoEnElTaller informacion = hashDePartes.get(miExcepcion.getMessage());
			informacion.incrementarCantidad();
			return extraerNumeroDeLaClave(miExcepcion.getMessage());

		}
	}


	private void buscarParteRepetida(ParteAuto parte, int precioEnAlgo$, String descripcion) throws PartAlreadyInCatalogException {
		Enumeration misClaves = hashDePartes.keys();
		InformacionParteAutoEnElTaller miParteInfo = null;
		String unaClave = null;
		while(misClaves.hasMoreElements()){
			unaClave = (String) misClaves.nextElement();
			miParteInfo = hashDePartes.get(unaClave);
			if ( (miParteInfo.getDescripcion() == descripcion) && (miParteInfo.getPrecio() == precioEnAlgo$) )
				throw new PartAlreadyInCatalogException(unaClave);
		}
	}

	/**
	 * @param usuario
	 * @param indiceDelCatalogo
	 * @throws NotEnoughMoneyException
	 * @throws NotInIndexException
	 * @throws WrongPartClassException
	 */
	public void comprar(Usuario usuario, int indiceDelCatalogo) throws NotEnoughMoneyException,NotInIndexException, WrongPartClassException{
		InformacionParteAutoEnElTaller informacionDeEstaParte = getParteAutoEnElTaller(indiceDelCatalogo);
		if (informacionDeEstaParte.getPrecio() > usuario.getDinero())
			throw new NotEnoughMoneyException("El usuario no posee dinero suficiente para comprar esta parte.");
		else{
			usuario.gastarDinero(informacionDeEstaParte.getPrecio());
			ParteAuto otraParte = ensamblarParteAuto(usuario.getAuto(), informacionDeEstaParte.getParte());
			venderParteAutoDelTaller(indiceDelCatalogo);
			try {
				usuario.adquirirDinero(calcularValorParteExtraida(otraParte));
			} catch (BoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private double calcularValorParteExtraida(ParteAuto unaParte){
		return 0.0;
	}

	/**
	 * @return
	 */
	public Iterator getCatalogo() {
		return ((ArrayList)listaDeNumerosDePartes.clone()).iterator();
	}

	/**
	 * @param indiceEnElCatalogo
	 * @return
	 * @throws NotInIndexException
	 */
	public Class getTipoDeParte(Object indiceEnElCatalogo) throws NotInIndexException {
		if ( !(indiceEnElCatalogo instanceof Integer))
			throw new NotInIndexException("No existe tal parte, el �ndice es incorrecto");
		return getParteAutoEnElTaller((Integer)(indiceEnElCatalogo)).getTipoDeParte();
	}
	
	private InformacionParteAutoEnElTaller getParteAutoEnElTaller(Integer indiceEnElCatalogo) throws NotInIndexException{
		if(! (hashDePartes.containsKey(generarClave(indiceEnElCatalogo))) )
			throw new NotInIndexException("No existe ese n�mero de parte en el catalogo.");
		else return hashDePartes.get(generarClave(indiceEnElCatalogo));
		
	}
	
	private void venderParteAutoDelTaller(Integer indiceEnElCatalogo) throws NotInIndexException{
		if(! (hashDePartes.containsKey(generarClave(indiceEnElCatalogo))) )
			throw new NotInIndexException("No existe ese n�mero de parte en el catalogo.");
		else {
			try {
				hashDePartes.get(generarClave(indiceEnElCatalogo)).decrementarCantidad();
			} catch (BoundsException unaExcepcion) {
				hashDePartes.remove(generarClave(indiceEnElCatalogo));
			}
		}
	}
	
	/**
	 * @param unAuto
	 * @param unaParte
	 * @return
	 * @throws WrongPartClassException
	 */
	public ParteAuto ensamblarParteAuto(Auto unAuto, ParteAuto unaParte) throws WrongPartClassException{
		if(unaParte instanceof Escape)
			return ensamblarEscape(unAuto, (Escape) unaParte);
		else if(unaParte instanceof Carroceria)
			return ensamblarCarroceria(unAuto, (Carroceria) unaParte);
		else if(unaParte instanceof Caja)
			return ensamblarCaja(unAuto, (Caja)unaParte);
		else if(unaParte instanceof Motor)
			return ensamblarMotor(unAuto, (Motor)unaParte);
		else if( unaParte instanceof Eje)
			return ensamblarEje(unAuto, (Eje)unaParte);
		else if( unaParte instanceof Mezclador)
			return ensamblarMezclador(unAuto, (Mezclador)unaParte);
		else if( unaParte instanceof TanqueCombustible)
			return ensamblarTanqueCombustible(unAuto, (TanqueCombustible)unaParte);
		else return unaParte;
	}
	
	private ParteAuto ensamblarTanqueCombustible(Auto unAuto, TanqueCombustible unTanque) throws WrongPartClassException {
		TanqueCombustible parteTemporal = unAuto.getTanqueCombustible();
		if( ! (unTanque.getClass() == parteTemporal.getClass()) ){
			throw new WrongPartClassException("No se puede ensamblar la parte. Se requiere de " + parteTemporal.getClass().getSimpleName() + " pero se tiene " + unTanque.getClass().getSimpleName());
		}
		unAuto.setTanqueCombustible(unTanque);
		return parteTemporal;
	}

	private ParteAuto ensamblarMezclador(Auto unAuto, Mezclador unMezclador) throws WrongPartClassException {
		Mezclador parteTemporal = unAuto.getMezclador();
		if( ! (unMezclador.getClass() == parteTemporal.getClass()) ){
			throw new WrongPartClassException("No se puede ensamblar la parte. Se requiere de " + parteTemporal.getClass().getSimpleName() + " pero se tiene " + unMezclador.getClass().getSimpleName());
		}
		unMezclador.setTanqueCombustible(unAuto.getTanqueCombustible());
		parteTemporal.setTanqueCombustible(null);
		unAuto.setMezclador(unMezclador);
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
		//parteTemporal.setEje(null);
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
	
	private String generarClave(int numeroDeItem){
		return new String("PARTE_"+numeroDeItem);
	}

	private int extraerNumeroDeLaClave(String clave){
		return Integer.parseInt((clave.substring("PARTE_".length() )));
	}
	
}