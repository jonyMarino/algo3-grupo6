package proveedorDeNafta;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedorDeNafta.FabricaDeNafta;
import excepciones.BoundsException;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			usuario.gastarDinero(dineroNecesario);
			return cantidad;
	}
	
	public ArrayList<FabricaDeNafta> getFabricasDisponibles(){
		return fabricasDisponibles;
	}
	
	public void agregarFabrica(FabricaDeNafta unaFabrica) {
		fabricasDisponibles.add(unaFabrica);	
	}

}