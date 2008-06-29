package programaAuto;

import java.util.ArrayList;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;
import excepciones.NotEnoughMoneyException;

import proveedorDePartes.fabricas.CadenaDeFabricas;
import proveedorDePartes.fabricas.FabricaDeCajas;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;
import proveedorDePartes.fabricas.FabricaDeEjes;
import proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedorDePartes.fabricas.FabricaDeMotores;
import proveedorDePartes.fabricas.FabricaDePedales;
import proveedorDePartes.fabricas.FabricaDeRuedas;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.InformacionDelModelo;
import proveedorDePartes.fabricas.ParteAuto;

public class ProveedorDePartes {
	
	CadenaDeFabricas miCadenaDeFabricas;
	
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
	
	public ArrayList<InformacionDelModelo> getModelosDisponibles(){
		return miCadenaDeFabricas.getModelos();
	}
	
	public ParteAuto comprar(InformacionDelModelo modelo, Usuario usuario) throws NoSuchModelException, NotEnoughMoneyException{
		ParteAuto unaParte = null;
		int dineroNecesario = 0;
		try {
			dineroNecesario = Integer.parseInt(modelo.getCaracteristica("COSTO"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			if( (usuario.getDinero()) < dineroNecesario ){
				throw new NotEnoughMoneyException("No tiene suficiente dinero como para comprar la parte");
			}

		unaParte = obtenerParte(modelo);
		usuario.gastarDinero(dineroNecesario);
		return unaParte;
	}
	
	ParteAuto obtenerParte(InformacionDelModelo modelo) throws NoSuchModelException{
		return miCadenaDeFabricas.fabricar(modelo);
	}

	public CadenaDeFabricas getMiCadenaDeFabricas() {
		return miCadenaDeFabricas;
	}
}
