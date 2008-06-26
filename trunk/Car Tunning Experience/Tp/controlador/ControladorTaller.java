package controlador;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.ImageIcon;
import excepciones.BoundsException;
import programaAuto.Pista;
import programaAuto.ProgramaAuto;
import programaAuto.Taller;
import proveedorDePartes.fabricas.FabricaDePartes;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import vista.PantallaTaller;

public class ControladorTaller {
	
	private Taller taller;
	private PantallaTaller pantallaTaller;
	private Pista proximaPista;
	private ProgramaAuto programaAuto;
	
	public ControladorTaller(Taller taller, ProgramaAuto programaAuto) {
		this.taller = taller;
		this.programaAuto = programaAuto;
	}

	public void cargarPantallaTaller(ImageIcon avatarUsuario) {
		pantallaTaller.actualizarInformacionDinero(Double.toString(taller.getUsuario().getDinero()));
		pantallaTaller.actualizarInformacionPista(Double.toString(proximaPista.getLongitud()) , proximaPista.getNombre(), Integer.toString(proximaPista.getVelocidadAire()));
		this.actualizarInformacionReserva();
		pantallaTaller.actualizarInformacionNafta(taller.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible() , taller.getUsuario().getAuto().getTanqueCombustible().getCapacidad());
		pantallaTaller.actualizarInformacionUsuario(taller.getUsuario().getNombre(), avatarUsuario);
		this.actualizarInformacionAuto();
		this.actualizarCatalogo();
	}
	
	public void actualizarPantallaTaller(){
		pantallaTaller.actualizarInformacionDinero(Double.toString(taller.getUsuario().getDinero()));
		pantallaTaller.actualizarInformacionPista(Double.toString(proximaPista.getLongitud()) , proximaPista.getNombre(), Integer.toString(proximaPista.getVelocidadAire()));
		this.actualizarInformacionReserva();
		pantallaTaller.actualizarInformacionNafta(taller.getUsuario().getAuto().getTanqueCombustible().getCantidadCombustible() , taller.getUsuario().getAuto().getTanqueCombustible().getCapacidad());
		this.actualizarInformacionAuto();
		this.actualizarCatalogo();
	}

	public void setPantallaTaller(PantallaTaller pantallaTaller) {
		this.pantallaTaller = pantallaTaller;
	}

	void setProximaPista(Pista proximaPista) {
		this.proximaPista = proximaPista;
	}

	private void actualizarInformacionReserva() {	
		pantallaTaller.limpiarInformacionReserva();
		
		String descripcion,vidaUtil;
		boolean cargo = false;
		
		while (taller.getPartesDeReserva().hasNext()) {
			cargo = true;
			try {
				descripcion = taller.getPartesDeReserva().next().getInformacionModelo().getCaracteristica("DESCRIPCION");
				vidaUtil = Double.toString(taller.getPartesDeReserva().next().getVidaUtil());
				pantallaTaller.agregarAReserva(descripcion, vidaUtil);
			} catch (BoundsException e) {}	    	
		}
		
		if(!cargo){
			pantallaTaller.agregarAReserva("- Lista ", "Vacía -");
		}
	}
	
	private void actualizarInformacionAuto() {
		pantallaTaller.limpiarInformacionAuto();
		
		ParteAuto parte;
		Hashtable<String,ParteAuto> tabla=new Hashtable<String,ParteAuto>();
        tabla = taller.getUsuario().getAuto().getHashDePartes();
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
		String descripcion;
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
					descripcion = info.getCaracteristica("DESCRIPCION");
					if(contador == 1)
						pantallaTaller.agregarAComboCajas(descripcion);
					if(contador == 2)
						pantallaTaller.agregarAComboCarrocerias(descripcion);
					if(contador == 3)
						pantallaTaller.agregarAComboEjes(descripcion);
					if(contador == 4)
						pantallaTaller.agregarAComboEscapes(descripcion);
					if(contador == 5)
						pantallaTaller.agregarAComboMezcladores(descripcion);
					if(contador == 6)
						pantallaTaller.agregarAComboMotores(descripcion);
					if(contador == 7)
						pantallaTaller.agregarAComboPedales(descripcion);
					if(contador == 8)
						pantallaTaller.agregarAComboRuedas(descripcion);
					if(contador == 9)
						pantallaTaller.agregarAComboTanques(descripcion);						
				} catch (BoundsException e) { }
			}
		}
		
	}
}
