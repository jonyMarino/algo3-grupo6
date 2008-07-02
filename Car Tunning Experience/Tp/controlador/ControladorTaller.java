package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import excepciones.BoundsException;
import excepciones.IncorrectPartForUbicationException;
import excepciones.NoPistaPickedException;
import excepciones.NoSuchModelException;
import excepciones.NotAbleWhileSimulatingException;
import excepciones.NotEnoughMoneyException;
import excepciones.PartBrokenException;
import excepciones.TankIsFullException;
import excepciones.UbicationUnkownException;
import programaAuto.ProgramaAuto;
import programaAuto.Auto.Ubicacion;
import programaAuto.Taller.InformacionParteEnAuto;
import proveedorDeCombustibles.InformacionCombustible;
import proveedorDeCombustibles.Nafta;
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
		entrarAlTaller();
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
		this.actualizadorTaller.setPantallaTaller(pantallaTaller);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("cargar"))
			this.llenarTanque(Double.valueOf(pantallaTaller.obtenerCantidadNafta()));
		if (comando.equals("comprar"))
			this.comprarParte();
		if (comando.equals("cambiar"))
			this.cambiarParte();		
	}

    private void cambiarParte() {
    	  	String nombreProducto = pantallaTaller.obtenerParteACambiar();
           	InformacionDelModelo informacionModelo = actualizadorTaller.buscarInformacionModelo(nombreProducto);
        	ParteAuto parte = this.buscarParteEnReserva(informacionModelo);
        	try {
				String parteARemover = pantallaTaller.obtenerParteARemover();
        		Ubicacion ubicacion = this.buscarUbicacion(parteARemover);
        		programaAuto.getUsuario().getAuto().colocarParte(parte, ubicacion);
				pantallaTaller.generarMensaje("El cambio ha sido realizado satisfactoriamente");
				} catch (IncorrectPartForUbicationException e) {
					pantallaTaller.generarMensajeError("No se puede realizar ese cambio");
				} catch (UbicationUnkownException e) {
        	 		pantallaTaller.generarMensajeError("La parte seleccionada no es del tipo del auto");
				} catch (RuntimeException e) {
					pantallaTaller.generarMensajeError("La parte no corresponde a este tipo de Auto");
				} finally {
					actualizadorTaller.actualizarPantallaTaller();
				}
    }
   
    private Ubicacion buscarUbicacion(String parteARemover) {
		Iterator<InformacionParteEnAuto> itPartesAuto = programaAuto.getUsuario().getTaller().getPartesEnAuto();
    	boolean encontrado = false;
    	Ubicacion ubicacion = null;
    	while(itPartesAuto.hasNext() && !encontrado){
    		ubicacion = itPartesAuto.next().getUbicacion();
    		if(parteARemover == ubicacion.toString())
    			encontrado = true;
    	}
    	return ubicacion;
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
        	String nombreProducto = pantallaTaller.obtenerParteAComprar();
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
			ArrayList<InformacionCombustible> modelosCombustibles = programaAuto.getUnProveedorDeNafta().getTiposDisponibles();
			double dineroNecesario = 0;
			
			try {
				dineroNecesario = Double.parseDouble(modelosCombustibles.get(0).getCaracteristica("COSTO"))*cantidad;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (BoundsException e) {
				e.printStackTrace();
			}
			
			 if(cantidad != 0  && dineroNecesario < programaAuto.getUsuario().getDinero()) {
				try {
					programaAuto.getUsuario().getAuto().cargarCombustible(cantidad);
					@SuppressWarnings("unused")
					Nafta nafta = (Nafta) programaAuto.getUnProveedorDeNafta().comprar(modelosCombustibles.get(0), cantidad, programaAuto.getUsuario());
					pantallaTaller.generarMensaje("La compra ha sido realizada satisfactoriamente");
				} catch (TankIsFullException e) {
					pantallaTaller.generarMensajeError("La cantidad supera el limite del tanque");
				} catch (BoundsException e) {
					pantallaTaller.generarMensajeError("La cantidad no puede ser negativa");
				} catch (PartBrokenException e) {
					pantallaTaller.generarMensajeError("El tanque esta roto, debe cambiarlo");
				} catch (NoSuchModelException e) {
					e.printStackTrace();
				} catch (NotEnoughMoneyException e) {
					pantallaTaller.generarMensajeError("El Usuario no posee el dinero necesario");
				}
			 } else 
					pantallaTaller.generarMensajeError("El Usuario no posee el dinero necesario");
				actualizadorTaller.actualizarPantallaTaller();
	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	public ActualizadorPantallaTaller getActualizadorTaller() {
		return actualizadorTaller;
	}

	public void entrarAlTaller() {
		try {
			programaAuto.entrarAlTaller();
		} catch (NoPistaPickedException e) {
		} catch (NotAbleWhileSimulatingException e) {
		}
		
	}
	
}
