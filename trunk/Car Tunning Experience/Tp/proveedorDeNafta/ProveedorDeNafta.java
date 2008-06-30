package proveedorDeNafta;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedorDeNafta.FabricaDeNafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;

public class ProveedorDeNafta {
	
	private ArrayList<FabricaDeNafta> fabricasDisponibles;
	
	public ProveedorDeNafta(){
		fabricasDisponibles = new ArrayList<FabricaDeNafta> ();
		agregarFabrica(new FabricaDeNafta());	
	}
	
	public double comprar(double cantidad,Usuario usuario) throws NotEnoughMoneyException, BoundsException{
		InformacionCombustible modelo =getFabricasDisponibles().get(0).getTipos().get(0);
		double dineroNecesario = 0;
		try {
			dineroNecesario = Double.parseDouble(modelo.getCaracteristica("COSTO"))*cantidad;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BoundsException e) {
			e.printStackTrace();
		}

		if (usuario.getDinero() < dineroNecesario)
			throw new NotEnoughMoneyException("No tiene suficiente dinero como para comprar la parte");
		
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