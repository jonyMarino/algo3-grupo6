package proveedorDePartes;

import java.util.ArrayList;

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
	
	ProveedorDePartes(){
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
	
	ArrayList<InformacionDelModelo> getModelosDisponibles(){
		return miCadenaDeFabricas.getModelos();
	}
	
	ParteAuto comprar(InformacionDelModelo modelo) throws NoSuchModelException, NotEnoughMoneyException{
		ParteAuto unaParte = null;
		
		unaParte = miCadenaDeFabricas.fabricar(modelo);
		
		return unaParte;
	}
}
