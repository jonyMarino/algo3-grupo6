package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.ModelRegisteredException;
import excepciones.NoSuchModelException;

/**
 *
 * Una clase particular de FabricaDePartes que se dedica a fabricar Ruedas
 *
 * @see FabricaDePartes
 * @see Rueda
 *
 */

public class FabricaDeRuedas extends FabricaDePartes {
 
	public FabricaDeRuedas(){
		super();
		InformacionDelModelo nuevaInfo = null;
		String modelo = "Pirelli 3000";
		try{
			nuevaInfo = new InformacionDelModelo(modelo);
			nuevaInfo.agregarCaracteristica("COSTO", "500");
			nuevaInfo.agregarCaracteristica("DESCRIPCION", "Rueda b�sica.");
			nuevaInfo.agregarCaracteristica("COEFICIENTEESTATICO", "0.9");
			nuevaInfo.agregarCaracteristica("RODADO", "28");
			nuevaInfo.agregarCaracteristica("COEFICIENTEDINAMICO", "0.6");
			nuevaInfo.agregarCaracteristica("PESO", "10");
			nuevaInfo.agregarCaracteristica("PARTE", "RUEDA");
		}catch (ModelRegisteredException e){
			nuevaInfo = RegistroDeModelos.getInstance().getInformacion(modelo);
		}
		finally{
			agregarModelo(nuevaInfo); //agrega una rueda b�sica al cat�logo
		}
	}
	public Rueda fabricar(InformacionDelModelo modelo) throws NoSuchModelException {
		NoSuchModelException unaExcepcion = new NoSuchModelException("El modelo no es una Rueda");
		try {
			if (modelo.getCaracteristica("PARTE") != "RUEDA")
				throw unaExcepcion;
		} catch (BoundsException e1) {
			throw unaExcepcion;
		}
		try{
			double coeficienteEstatico = Double.parseDouble(modelo.getCaracteristica("COEFICIENTEESTATICO"));
			int rodado = Integer.parseInt(modelo.getCaracteristica("RODADO"));
			String descripcion = modelo.getCaracteristica("DESCRIPCION");
			double coeficienteDinamico = Double.parseDouble(modelo.getCaracteristica("COEFICIENTEDINAMICO"));
			int costo = Integer.parseInt(modelo.getCaracteristica("COSTO"));
			double peso = Double.parseDouble(modelo.getCaracteristica("PESO"));
			Rueda unaRueda = new Rueda(rodado, coeficienteEstatico, coeficienteDinamico);
			unaRueda.setDescripcion(descripcion);
			unaRueda.setCosto(costo);
			unaRueda.setPeso(peso);
			unaRueda.setInformacionDelModelo(modelo);
			return unaRueda;
		}
		catch(BoundsException e){
			throw unaExcepcion;
		}
	}
	 
}
 
