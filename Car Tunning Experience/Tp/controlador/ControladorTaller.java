package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import excepciones.BoundsException;
import excepciones.IncorrectPartForUbicationException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import excepciones.TankIsFullException;
import excepciones.UbicationUnkownException;
import programaAuto.ProgramaAuto;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;


public class ControladorTaller implements ActionListener {
	
	private PantallaTaller pantallaTaller;
	private ProgramaAuto programaAuto;
	private ActualizadorPantallaTaller actualizadorTaller;
	
	public ControladorTaller(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
		this.actualizadorTaller = new ActualizadorPantallaTaller(programaAuto, null);
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
		this.actualizadorTaller.setPantallaTaller(pantallaTaller);
	}


	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("cargar"))
			this.llenarTanque(Double.valueOf(pantallaTaller.obtenerCantidadPanelNafta()));
		if (comando.equals("comprar"))
			this.comprarParte();
		if (comando.equals("cambiar"))
			this.cambiarParte();		
	}

    private void cambiarParte() {
    	  	String nombreProducto = pantallaTaller.parteAComprar();
           	InformacionDelModelo informacionModelo = actualizadorTaller.buscarInformacionModelo(nombreProducto);
        	ParteAuto parte = this.buscarParteEnReserva(informacionModelo);
        	try {
				programaAuto.getUsuario().getAuto().colocarParte(parte, pantallaTaller.parteARemover());
				programaAuto.getUsuario().getAuto().ensamblar();
				pantallaTaller.generarMensaje("El cambio ha sido realizado satisfactoriamente");
				} catch (IncorrectPartForUbicationException e) {
					pantallaTaller.generarMensajeError("No se puede realizar ese cambio");
				} catch (UbicationUnkownException e) {
        	 		pantallaTaller.generarMensajeError("La parte seleccionada no es del tipo del auto");
				} finally {
					actualizadorTaller.actualizarPantallaTaller();
				}
    }
   
    private ParteAuto buscarParteEnReserva(InformacionDelModelo informacionModelo) {
		Iterator<ParteAuto>  itReservas = programaAuto.getUsuario().getTaller().getPartesReserva();
		boolean encontrado = false;
		ParteAuto parte = null;
		while(itReservas.hasNext() && !encontrado){
			parte = itReservas.next();
			if(parte.getInformacionDelModelo().equals(informacionModelo))
				encontrado = true;			
		}
		return parte;
	}

	private void comprarParte() {
    	try{
        	String nombreProducto = pantallaTaller.parteAComprar();
       	 	InformacionDelModelo info = actualizadorTaller.buscarInformacionModelo(nombreProducto);
        	ParteAuto unaParte = programaAuto.getUnProveedor().comprar(info, programaAuto.getUsuario());
         	programaAuto.getUsuario().getTaller().aniadirAReserva(unaParte);
			pantallaTaller.generarMensaje("La compra ha sido realizada satisfactoriamente");
    		} catch (NotEnoughMoneyException e) {
         		pantallaTaller.generarMensajeError("No posee el dinero necesario");
         	} catch (NoSuchModelException e) {
         		pantallaTaller.generarMensajeError("El modelo elegido es invalido.");
			} catch(ClassCastException e){
				e.printStackTrace();
			} finally {
				actualizadorTaller.actualizarPantallaTaller();
			}
    }

	private void llenarTanque(double cantidad) {
		try {
			if(cantidad != 0){
			programaAuto.comprarNafta(cantidad);
			pantallaTaller.generarMensaje("La carga ha sido realizada satisfactoriamente");
			}
		} catch (TankIsFullException e1) {
	    	pantallaTaller.generarMensajeError("El tanque esta lleno");		
		} catch (BoundsException e1) {
	    	pantallaTaller.generarMensajeError("No puede cargar esa cantidad de nafta");		
		} catch (NumberFormatException e1) {
		} catch (NotEnoughMoneyException e1) {
			pantallaTaller.generarMensajeError("No posee el dinero necesario");
		} finally {
			actualizadorTaller.actualizarPantallaTaller();
		}
		
	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	public ActualizadorPantallaTaller getActualizadorTaller() {
		return actualizadorTaller;
	}
	
}
