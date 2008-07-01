package proveedorDeCombustibles;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedorDeCombustibles.FabricaDeNafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;

public class ProveedorDeCombustibles {
	
	private ArrayList<FabricaDeCombustible> fabricasDisponibles;
	
	public ProveedorDeCombustibles(){
		fabricasDisponibles = new ArrayList<FabricaDeCombustible>();
		agregarFabrica(new FabricaDeNafta());	
	}
	
	/** Compra la cantidad de combustible requerida por el usuario **/
	
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
	
	public Combustible obtenerNafta() throws NoSuchModelException{
		InformacionCombustible modelo =getFabricasDisponibles().get(0).getTipos().get(0);
		return fabricasDisponibles.get(0).fabricar(modelo);
	}
	
	/** Retorna las fabricas de combustible disponibles **/
	public ArrayList<FabricaDeCombustible> getFabricasDisponibles(){
		return fabricasDisponibles;
	}
	
	public void agregarFabrica(FabricaDeNafta unaFabrica) {
		fabricasDisponibles.add(unaFabrica);	
	}

}