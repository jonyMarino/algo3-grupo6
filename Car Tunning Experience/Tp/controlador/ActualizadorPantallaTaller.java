package controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import excepciones.BoundsException;
import programaAuto.ProgramaAuto;
import programaAuto.Taller.InformacionParteEnAuto;
import proveedores.proveedorDePartes.fabricas.FabricaDePartes;
import proveedores.proveedorDePartes.fabricas.InformacionDelModelo;
import proveedores.proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;

/**
*
* Clase que se encarg� de la actualizacion gr�fica de la vista Taller.
* Administra las vistas del Taller durante la misma.
* 
*/
public class ActualizadorPantallaTaller {
	
	private PantallaTaller pantallaTaller;
	private ProgramaAuto programaAuto;
	
	/**
	 * Crea un nuevo actualizadorTaller para el programa auto especificado.
	 * @param programaAuto La instancia de ProgramaAuto.
	 * @param pantallaTaller El panel en el que se realizar�n las actualizaciones.
	 * @see PantallaTaller
	 * @see ProgramaAuto 
	 */
	public ActualizadorPantallaTaller(ProgramaAuto programaAuto, PantallaTaller pantallaTaller){
		this.pantallaTaller = pantallaTaller;
		this.programaAuto = programaAuto;
	}
	
	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	public void cargarPantallaTaller(ImageIcon avatarUsuario) {
		pantallaTaller.actualizarInformacionUsuario(programaAuto.getUsuario().getNombre(), avatarUsuario);
		Action mostrarPrecio = new AccionActualizarPrecio();
		pantallaTaller.obtenerCatalogo().setAction(mostrarPrecio);
		this.actualizarCatalogo();
		this.actualizarParteARemover();
		this.actualizarPantallaTaller();
	}
	
	private void actualizarParteARemover() {
		Iterator<InformacionParteEnAuto> itPartesAuto = programaAuto.getUsuario().getTaller().getPartesEnAuto();
		while(itPartesAuto.hasNext()) {
			pantallaTaller.agregarARemover(itPartesAuto.next().getUbicacion().toString());		
		}
	}

	public void actualizarPantallaTaller(){
		pantallaTaller.actualizarInformacionDinero(Double.toString(programaAuto.getUsuario().getDinero()));	
		this.actualizarInformacionPista();
		this.actualizarInformacionReserva();
		this.actualizarInformacionNafta();
		this.actualizarInformacionAuto();
	}
	
	public void actualizarResultado(){
		this.informarPremio(programaAuto.getUltimoPremio());
			
	}
	
	private void actualizarInformacionPista() {
		String longitud = Double.toString(programaAuto.getPista().getLongitud());
		String nombre = programaAuto.getPista().getNombre();
		String velocidadAire = Integer.toString(programaAuto.getPista().getVelocidadAire());
		pantallaTaller.actualizarInformacionPista( longitud, nombre, velocidadAire);
	}

	private void actualizarInformacionReserva() {	
		pantallaTaller.limpiarInformacionReserva();
	
		String nombreModelo,vidaUtil;
		boolean cargo = false;
		Iterator<ParteAuto> iteradorReserva = programaAuto.getUsuario().getTaller().getPartesReserva(); 
		
		while (iteradorReserva.hasNext()) {
			cargo = true;
			ParteAuto parte = iteradorReserva.next();
			nombreModelo = parte.getInformacionDelModelo().getNombre();
			vidaUtil = Double.toString(parte.getVidaUtil());
			pantallaTaller.agregarAReserva(nombreModelo, vidaUtil);	    	
		}	
		if(!cargo){
			pantallaTaller.agregarAReserva("- Lista ", "Vac�a -");
		}
	}
	
	private void actualizarInformacionNafta() {
		Double cantidad = programaAuto.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible();
		Double capacidad = programaAuto.getUsuario().getAuto().getTanqueCombustible().getCapacidad();
		String precio = "";
		try {
			precio = programaAuto.getUnProveedorDeNafta().getFabricasDisponibles().getModelos().get(0).getCaracteristica("COSTO");
		} catch (BoundsException e) {
			e.printStackTrace();
		} 
		pantallaTaller.actualizarInformacionNafta(cantidad, capacidad, precio);
	}
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();	
		String vidaUtil,nombreParte,ubicacion;
		Iterator<InformacionParteEnAuto> itPartesAuto = programaAuto.getUsuario().getTaller().getPartesEnAuto();
		InformacionParteEnAuto informacionParte = null; 
		while(itPartesAuto.hasNext()){
			informacionParte = itPartesAuto.next();
                nombreParte = informacionParte.getInformacionModelo().getModelo();           
                vidaUtil = Double.toString(informacionParte.getVidaUtil());
                ubicacion = informacionParte.getUbicacion().toString();
                pantallaTaller.agregarInformacionAuto(nombreParte, vidaUtil, ubicacion);
         }
	}
	
	private void actualizarCatalogo() {
		ArrayList<FabricaDePartes> fabricas = programaAuto.getUnProveedor().getMiCadenaDeFabricas().getMiCadenaDeFabricas();
		Iterator<FabricaDePartes> it = fabricas.iterator();
		FabricaDePartes fabrica;
		ArrayList<InformacionDelModelo> productos;
		Iterator<InformacionDelModelo> itProducto;
		int contador = 0;
		while(it.hasNext()){
			contador++;
			fabrica = (FabricaDePartes)it.next();
			productos = fabrica.getModelos();
			itProducto = productos.iterator();
			while(itProducto.hasNext()){
				InformacionDelModelo info = (InformacionDelModelo) itProducto.next();			
				pantallaTaller.agregarACatalogo(info.getNombre());				
			}
		}		
	}

	private class AccionActualizarPrecio extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evento) {
		 String precio = "";
         try {
        	 String nombreProducto = pantallaTaller.obtenerParteAComprar();
        	 InformacionDelModelo info = buscarInformacionModelo(nombreProducto);
        	 try {
				precio = info.getCaracteristica("COSTO");
				precio = "Algo$ " + precio;
        	 	} catch (BoundsException e) {
        	 		e.printStackTrace();
        	 	}
         		} catch (ClassCastException e){
         			precio = "Seleccione Una parte";
         		}
         		pantallaTaller.cambiarPrecioParteSeleccionada(precio);
		}
	}
	
	InformacionDelModelo buscarInformacionModelo(String nombreProducto){
		ArrayList<FabricaDePartes> fabricas = programaAuto.getUnProveedor().getMiCadenaDeFabricas().getMiCadenaDeFabricas();
		Iterator<FabricaDePartes> it = fabricas.iterator();
		FabricaDePartes fabrica;
		InformacionDelModelo informacionModelo = null;			
		ArrayList<InformacionDelModelo> productos;
		Iterator<InformacionDelModelo> itProducto;
		while(it.hasNext()){
			fabrica = (FabricaDePartes)it.next();
			productos = fabrica.getModelos();
			itProducto = productos.iterator();
			while(itProducto.hasNext()){
				informacionModelo = (InformacionDelModelo) itProducto.next();			
				if((informacionModelo.getNombre()).equals(nombreProducto))
					return informacionModelo;
			}
		}		
		return null;
	}
	
	private void informarPremio(double premio) {
		if (premio > 0)
		pantallaTaller.generarMensaje("En hora Buena! has ganado " + Double.toString(premio) + " Algo$");
		else if(premio == -1)
			pantallaTaller.generarMensajeError("Looser!!!");  
	}

}