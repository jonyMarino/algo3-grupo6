package proveedores.proveedorDeCombustibles;

import java.util.ArrayList;
import programaAuto.Usuario;
import proveedores.proveedorDeCombustibles.FabricaDeNafta;
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
	
	private CadenaDeFabricasCombustibles fabricasDisponibles;
	
    /**
     *
     * Crea un nuevo proveedor de combustibles.
     *
     * @see Combustible
     */
	public ProveedorDeCombustibles(){
		fabricasDisponibles = new CadenaDeFabricasCombustibles();
		fabricasDisponibles.agregarFabrica(new FabricaDeNafta());	
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
	
	public Combustible comprar(InformacionCombustible modelo,double cantidad,Usuario usuario) throws  NoSuchModelException,NotEnoughMoneyException, BoundsException{
		
		Combustible unCombustible = null;
		
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
		
		unCombustible =obtenerCombustible(modelo);
		usuario.gastarDinero(dineroNecesario);
		
		return unCombustible;
	}
	/** Hace fabicar el combustible */
	public Combustible obtenerCombustible(InformacionCombustible modelo) throws NoSuchModelException{
		return fabricasDisponibles.fabricar(modelo);
	}
	
	public Combustible obtenerCombustible(String nombre) throws NoSuchModelException{
		InformacionCombustible info=RegistroDeCombustibles.getInstance().getInformacion(nombre);
		return obtenerCombustible(info);
	}	
	
	/** Retorna las fabricas de combustible disponibles **/
	public CadenaDeFabricasCombustibles getFabricasDisponibles(){
		return fabricasDisponibles;
	}
	
	public ArrayList<InformacionCombustible> getTiposDisponibles(){
		return fabricasDisponibles.getModelos();
	}


}