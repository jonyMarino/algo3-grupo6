package proveedorDeNafta;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedorDeNafta.FabricaDeNafta;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;
import excepciones.TankIsFullException;

public class ProveedorDeNafta {
	
	private ArrayList<FabricaDeNafta> fabricasDisponibles;
	
	public ProveedorDeNafta(){
		fabricasDisponibles = new ArrayList<FabricaDeNafta> ();
		agregarFabrica(new FabricaDeNafta());	
	}
	
	public double comprar(double cantidad,Usuario usuario) throws NotEnoughMoneyException, BoundsException{
		InformacionCombustible modelo =getFabricasDisponibles().get(0).getTipos().get(0);
		//como siempre usamos el mismo tipo de nafta por eso esto es interno
		double dineroNecesario = 0;
		try {
			dineroNecesario = Double.parseDouble(modelo.getCaracteristica("COSTO"))*cantidad;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BoundsException e) {
			e.printStackTrace();
		}

			usuario.gastarDinero(dineroNecesario);
			return cantidad;
	}
	
	public Nafta obtenerNafta() throws NoSuchModelException{
		InformacionCombustible modelo =getFabricasDisponibles().get(0).getTipos().get(0);
		return fabricasDisponibles.get(0).fabricar(modelo);
	}
	
	public ArrayList<FabricaDeNafta> getFabricasDisponibles(){
		return fabricasDisponibles;
	}
	
	public void agregarFabrica(FabricaDeNafta unaFabrica) {
		fabricasDisponibles.add(unaFabrica);	
	}

}