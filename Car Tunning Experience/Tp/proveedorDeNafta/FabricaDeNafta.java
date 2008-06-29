package proveedorDeNafta;

import java.util.ArrayList;
import java.util.Iterator;

import proveedorDePartes.fabricas.InformacionDelModelo;
import combustible.Nafta;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;

public class FabricaDeNafta {
	
	private ArrayList<InformacionDelModelo> tiposConocidos;

	public FabricaDeNafta() {
		tiposConocidos = new ArrayList<InformacionDelModelo>();
		agregarTipo(nuevoTipoNafta(600,50.0));
	}
	
	public Integer consultarPrecio(InformacionDelModelo modelo) {
		return null;
	}
	 
	public ArrayList<InformacionDelModelo> getTipos() {
		return tiposConocidos;
	}

	public void agregarTipo(InformacionDelModelo datosDelModelo) {
		Iterator<InformacionDelModelo> iteradorModelos = getTipos().iterator();
		ArrayList<String> claves = datosDelModelo.getCaracteristicasDisponibles();
		boolean encontrado = false;
		
		while(iteradorModelos.hasNext() && !encontrado){
			Iterator<String> iteradorClaves = claves.iterator();
			InformacionDelModelo modeloAComparar = iteradorModelos.next();
			encontrado = true;
			while(iteradorClaves.hasNext() && encontrado){
				try{
					if(modeloAComparar.getCaracteristica(iteradorClaves.next()) != datosDelModelo.getCaracteristica(iteradorClaves.next())){
						encontrado=false;
						break;
					}
				}catch(BoundsException e){ encontrado=false; break;}
						
			}
				
		}
			if(!encontrado)
			tiposConocidos.add(datosDelModelo);
			
	}

	private InformacionDelModelo nuevoTipoNafta(int octanaje,Double costo){
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", costo.toString());
		nuevaInfo.agregarCaracteristica("OCTANAJE",Integer.toString(octanaje));
		return nuevaInfo;
	}
	
	public Nafta fabricar(InformacionDelModelo modelo) throws NoSuchModelException{
		NoSuchModelException unaExcepcion = new NoSuchModelException("No es un tipo de Nafta");
		Nafta nafta;
		try{
			double costo = Double.parseDouble(modelo.getCaracteristica("COSTO"));
			int octanaje = Integer.parseInt(modelo.getCaracteristica("OCTANAJE"));
			nafta = new Nafta(octanaje,costo);
			nafta.setOctanaje(octanaje);
			nafta.setCosto(costo);

			nafta.setInformacionDelModelo(modelo);
			return nafta;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	

}
