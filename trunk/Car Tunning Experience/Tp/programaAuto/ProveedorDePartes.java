package programaAuto;

import java.util.ArrayList;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;

import proveedores.proveedorDePartes.fabricas.FabricaDeCajas;
import proveedores.proveedorDePartes.fabricas.FabricaDeCarrocerias;
import proveedores.proveedorDePartes.fabricas.FabricaDeEjes;
import proveedores.proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedores.proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedores.proveedorDePartes.fabricas.FabricaDeMotores;
import proveedores.proveedorDePartes.fabricas.FabricaDePedales;
import proveedores.proveedorDePartes.fabricas.FabricaDeRuedas;
import proveedores.proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedores.proveedorDePartes.fabricas.InformacionDelModelo;
import proveedores.proveedorDePartes.fabricas.ParteAuto;

/**
 *
 * Clase que utiliza las se encarga de vender las partes fabricadas por 
 * las fabricas de partes al usuario
 *
 * @see FabricaDePartes
 * @see Usuario
 * @see ParteAuto
 */

public class ProveedorDePartes {
	
	CadenaDeFabricas miCadenaDeFabricas;

    /**
     * Crea un nuevo proveedor de partes, conteniendo las fábricas 
     * necesarias para producir cualquier parte del auto.
     *
     * @see CadenaDeFabricas
     * @see ParteAuto
     * @see FabricaDePartes
     */	
	public ProveedorDePartes(){
		miCadenaDeFabricas = new CadenaDeFabricas();
		miCadenaDeFabricas.agregarFabrica(new FabricaDeCajas());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeCarrocerias());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeEjes());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeEscapes());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeMezcladores());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeMotores());
		miCadenaDeFabricas.agregarFabrica(new FabricaDePedales());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeRuedas());
		miCadenaDeFabricas.agregarFabrica(new FabricaDeTanquesDeCombustible());
	}
	
    /**
     * Devuelve los modelos de partes que vende el proveedor.
     *
     * @return Un ArrayList de InformacionDelModelo que describen cada 
     * uno de los modelos disponibles. 
     *
     * @see InformacionDelModelo
     */
	public ArrayList<InformacionDelModelo> getModelosDisponibles(){
		return miCadenaDeFabricas.getModelos();
	}

    /**
     * Vende a un usuario la parte que le pide, simpre y cuando éste posea
     * el dinero suficiente.
     *
     * @param  modelo Una instancia de InformacionModelo que describe 
     * la parte a comprar
     * @param usuario El usuario que compra la parte.
     *
     * @return La parte comprada
     *
     * @see Usuario
     * @see InformacionDelModelo
     * @see ParteAuto
     *
     * @exception  NoSuchModelException
     * @exception NotEnoughMoneyException
     */
	public ParteAuto comprar(InformacionDelModelo modelo, Usuario usuario) throws NoSuchModelException, NotEnoughMoneyException{
		if (modelo == null)
			throw new NoSuchModelException("No existe ese modelo");
		ParteAuto unaParte = null;
		int dineroNecesario = 0;
		try {
			dineroNecesario = Integer.parseInt(modelo.getCaracteristica("COSTO"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BoundsException e) {
			e.printStackTrace();
		}

		if( (usuario.getDinero()) < dineroNecesario ){
				throw new NotEnoughMoneyException("No tiene suficiente dinero como para comprar la parte");
		}

		unaParte = miCadenaDeFabricas.fabricar(modelo);
		usuario.gastarDinero(dineroNecesario);
		return unaParte;
	}

    /**
     * Devuelve la cadena de fábricas asociada a el proveedor.
     *
     * @see CadenaDeFabricas
     */
	public CadenaDeFabricas getMiCadenaDeFabricas() {
		return miCadenaDeFabricas;
	}
}
