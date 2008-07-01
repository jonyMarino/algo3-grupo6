package proveedorDeCombustibles;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedorDeCombustibles.FabricaDeNafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;

/**
 *
 * Clase que obtiene el Combustible de las fábricas y lo vende a un Usuario.
 *
 * @see Combustible
 * @see FabricaDeCombustible
 * @see Usuario
 */

public class ProveedorDeCombustibles {
	
	private ArrayList<FabricaDeCombustible> fabricasDisponibles;
	
    /**
     *
     * Crea un nuevo proveedor de combustibles.
     *
     * @see Combustible
     */
	public ProveedorDeCombustibles(){
		fabricasDisponibles = new ArrayList<FabricaDeCombustible>();
		agregarFabrica(new FabricaDeNafta());	
	}
	
	/** 
	 *
	 * Vende al usuario la cantidad de Combustible pedida, si éste posee
	 * la cantidad de dinero necesaria.
	 *
	 * @param cantidad La cantidad de combustible que se desea comprar
	 * @param usuario El usuario que realiza la compra.
	 *
	 * @return La cantidad de combustible comprado.
	 *
	 * @exception NotEnoughMoneyException
	 * @exception BoundsException
	 */

    //TODO: ???????? COMPRA SIEMPRE NAFTA ??????????????????????
	
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

    //TODO: ?????????????????????????????????	
	public Combustible obtenerNafta() throws NoSuchModelException{
		InformacionCombustible modelo =getFabricasDisponibles().get(0).getTipos().get(0);
		return fabricasDisponibles.get(0).fabricar(modelo);
	}
	
	/** Retorna las fabricas de combustible disponibles **/
	public ArrayList<FabricaDeCombustible> getFabricasDisponibles(){
		return fabricasDisponibles;
	}

    /**
     * Agrega una nueva fábrica.
     *
     */
	
	public void agregarFabrica(FabricaDeNafta unaFabrica) {
		fabricasDisponibles.add(unaFabrica);	
	}

}