package controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import programaAuto.ProgramaAuto;
import programaAuto.Auto.Ubicacion;
import programaAuto.Taller.InformacionParteEnAuto;
import proveedorDePartes.fabricas.FabricaDePartes;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;

public class ActualizadorPantallaTaller {
	
	private PantallaTaller pantallaTaller;
	private ProgramaAuto programaAuto;
	
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
	
	private void actualizarInformacionPista() {
		String longitud = Double.toString(programaAuto.getPista().getLongitud());
		String nombre = programaAuto.getPista().getNombre();
		String velocidadAire = Integer.toString(programaAuto.getPista().getVelocidadAire());
		pantallaTaller.actualizarInformacionPista( longitud, nombre, velocidadAire);
	}

	private void actualizarInformacionReserva() {	
		pantallaTaller.limpiarInformacionReserva();
		
		String descripcion,vidaUtil;
		boolean cargo = false;
		
		Iterator<ParteAuto> iteradorReserva = programaAuto.getUsuario().getTaller().getPartesReserva(); 
		
		while (iteradorReserva.hasNext()) {
			cargo = true;
			try {
				ParteAuto parte = iteradorReserva.next();
				descripcion = parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
				vidaUtil = Double.toString(parte.getVidaUtil());
				pantallaTaller.agregarAReserva(descripcion, vidaUtil);
			} catch (BoundsException e) {
				e.printStackTrace();
			}	    	
		}
		
		if(!cargo){
			pantallaTaller.agregarAReserva("- Lista ", "Vacía -");
		}
	}
	
	private void actualizarInformacionNafta() {
		Double cantidad = programaAuto.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible();
		Double capacidad = programaAuto.getUsuario().getAuto().getTanqueCombustible().getCapacidad();
		String precio = "";
		try {
			precio = programaAuto.getUnProveedorDeNafta().obtenerNafta().getInformacionCombustible().getCaracteristica("COSTO");
		} catch (BoundsException e) {
			e.printStackTrace();
		} catch (NoSuchModelException e) {
			e.printStackTrace();
		}
		pantallaTaller.actualizarInformacionNafta(cantidad, capacidad, precio);
	}
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();
		
		ParteAuto parte;
		Hashtable<Ubicacion,ParteAuto> tabla=new Hashtable<Ubicacion,ParteAuto>();
        tabla = programaAuto.getUsuario().getAuto().getHashDePartes();
        Enumeration<ParteAuto> enumeracion = tabla.elements();
        String vidaUtil,nombreParte;
         
        while(enumeracion.hasMoreElements()) {   
      	   		parte=enumeracion.nextElement();
      	   		try{
                   nombreParte = parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                   vidaUtil = Double.toString(parte.getVidaUtil());
                   pantallaTaller.agregarInformacionAuto(nombreParte, vidaUtil);
      	   		}catch (BoundsException e){
        	 		e.printStackTrace();
      	   		}
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
				try {
					pantallaTaller.agregarACatalogo(info.getCaracteristica("DESCRIPCION"));
				} catch (BoundsException e) {
        	 		e.printStackTrace();
				}				
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
		boolean encontrado = false;
		while(it.hasNext()){
			fabrica = (FabricaDePartes)it.next();
			productos = fabrica.getModelos();
			itProducto = productos.iterator();
			while(itProducto.hasNext() && !encontrado){
				informacionModelo = (InformacionDelModelo) itProducto.next();			
				try {
					if((informacionModelo.getCaracteristica("DESCRIPCION")).equals(nombreProducto))
						encontrado = true;
				} catch (BoundsException e) {
        	 		e.printStackTrace();
				}				
			}
		}		
		return informacionModelo;
	}
	
}
