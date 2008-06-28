package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import excepciones.TankIsFullException;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.FabricaDePartes;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;


public class ControladorTaller implements ActionListener {
	
	private PantallaTaller pantallaTaller;
	private ProgramaAuto programaAuto;
	
	public ControladorTaller(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
	}

	public void cargarPantallaTaller(ImageIcon avatarUsuario) {
		pantallaTaller.actualizarInformacionUsuario(programaAuto.getUsuario().getNombre(), avatarUsuario);
		Action mostrarPrecio = new AccionActualizarPrecio();
		pantallaTaller.getElCatalogo().setAction(mostrarPrecio);
		this.actualizarPantallaTaller();
	}
	
	public void actualizarPantallaTaller(){
		pantallaTaller.actualizarInformacionDinero(Double.toString(programaAuto.getUsuario().getDinero()));
		pantallaTaller.actualizarInformacionPista(Double.toString(programaAuto.getPista().getLongitud()) , programaAuto.getPista().getNombre(), Integer.toString(programaAuto.getPista().getVelocidadAire()));
		this.actualizarInformacionReserva();
		pantallaTaller.actualizarInformacionNafta(programaAuto.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible() , programaAuto.getUsuario().getAuto().getTanqueCombustible().getCapacidad());
		this.actualizarInformacionAuto();
		this.actualizarCatalogo();
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
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
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();
		
		ParteAuto parte;
		Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
        tabla = programaAuto.getUsuario().getAuto().getHashDePartes();
        Enumeration<ParteAuto> enumeracion = tabla.elements();
        String vidaUtil,nombreParte;
         
        while(enumeracion.hasMoreElements()) {   
      	   		parte=enumeracion.nextElement();
      	   		try{
                   nombreParte = parte.getInformacionDelModelo().getCaracteristica("DESCRIPCION");
                   vidaUtil = Double.toString(parte.getVidaUtil());
                   pantallaTaller.agregarInformacionAuto(nombreParte, vidaUtil);
      	   		}catch (BoundsException e){}
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
						pantallaTaller.agregarACatalogo(info);
					
			}
			
		}	
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("cargar"))
			this.llenarTanque(Double.valueOf(pantallaTaller.obtenerCantidadPanelNafta()));
		if (comando.equals("comprar"))
			this.comprarParte();
			
	}

    private void comprarParte() {
           try{
        	   InformacionDelModelo info = (InformacionDelModelo) pantallaTaller.parteAComprar();
         	   ParteAuto unaParte = programaAuto.getUnProveedor().comprar(info, programaAuto.getUsuario());
         	   programaAuto.getUsuario().getTaller().aniadirAReserva(unaParte);
         	   this.actualizarPantallaTaller();
         	   } catch (NotEnoughMoneyException e) {
         		  pantallaTaller.generarMensajeError("No posee el dinero necesario");
         	   } catch (NoSuchModelException e) {
         		  pantallaTaller.generarMensajeError("El modelo elegido es invalido.");
			   } catch(ClassCastException e){}        
    }

	private void llenarTanque(double cantidad) {
		try {
			if(cantidad != 0){
			programaAuto.comprarNafta(cantidad);
			this.lanzarMensajeOperacionRealizada();
			}
		} catch (TankIsFullException e1) {
	    	pantallaTaller.generarMensajeError("El tanque esta lleno");		
		} catch (BoundsException e1) {
	    	pantallaTaller.generarMensajeError("No puede cargar esa cantidad de nafta");		
		} catch (NumberFormatException e1) {
		} catch (NotEnoughMoneyException e1) {
			pantallaTaller.generarMensajeError("No posee el dinero necesario");
		} finally {
			this.actualizarPantallaTaller();
		}
		
	}

	private void lanzarMensajeOperacionRealizada() {
		pantallaTaller.generarMensaje("La operacion ha sido realizada satisfactoriamente");

	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}
	

	
	private class AccionActualizarPrecio extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evento) {
		 String precio = "";
         try {
      	   InformacionDelModelo info = (InformacionDelModelo) pantallaTaller.getElCatalogo().getSelectedItem();
      	   try {
				precio = info.getCaracteristica("COSTO");
				precio = "Algo$ " + precio;
			} catch (BoundsException e) {
				e.printStackTrace();
			}
         } catch (ClassCastException e){
      	   precio = "Seleccione Una parte";
         }
         	pantallaTaller.precioParteSeleccionada(precio);
    	}
	}
	
}
