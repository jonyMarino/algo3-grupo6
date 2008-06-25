package proveedorDePartes.fabricas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import excepciones.BoundsException;
import excepciones.NoSuchModelException;



public class CadenaDeFabricas{

	private ArrayList<FabricaDePartes> miCadenaDeFabricas;
	
	public CadenaDeFabricas() {
		miCadenaDeFabricas = new ArrayList<FabricaDePartes>();
	}

	public ParteAuto fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		Iterator<FabricaDePartes> iteradorCadena = miCadenaDeFabricas.iterator();
		boolean fabricado = false;
		ParteAuto unaParte = null;
		
		while(iteradorCadena.hasNext() && !fabricado){
			try {
				unaParte = iteradorCadena.next().fabricar(modelo);
				fabricado = true;
			} catch (NoSuchModelException e) {
				fabricado=false;
			}
			catch(NullPointerException e){
				fabricado = false;
			}
		}
		
		if (fabricado)
			return unaParte;
		else throw new  NoSuchModelException("Ninguna de las fábricas sabe como fabricar ese modelo.");
	}

	public void agregarFabrica(FabricaDePartes unaFabrica) {
		miCadenaDeFabricas.add(unaFabrica);
		
	}

	public ArrayList<InformacionDelModelo> getModelos() {
		Iterator<FabricaDePartes> iteradorCadena = miCadenaDeFabricas.iterator();
		ArrayList<InformacionDelModelo> catalogo = new ArrayList<InformacionDelModelo>();
		while(iteradorCadena.hasNext()){
			catalogo.addAll(iteradorCadena.next().getModelos());
		}
		return catalogo;
	}

}
