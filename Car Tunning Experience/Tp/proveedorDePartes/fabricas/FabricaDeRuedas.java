package proveedorDePartes.fabricas;

import excepciones.BoundsException;


public class FabricaDeRuedas extends FabricaDePartes {
 
	public FabricaDeRuedas(){
		super();
		InformacionDelModelo nuevaInfo = new InformacionDelModelo();
		nuevaInfo.agregarCaracteristica("COSTO", "500");
		nuevaInfo.agregarCaracteristica("DESCRIPCION", "Rueda básica.");
		nuevaInfo.agregarCaracteristica("COEFICIENTEESTATICO", "0.9");
		nuevaInfo.agregarCaracteristica("RODADO", "10");
		nuevaInfo.agregarCaracteristica("COEFICIENTEDINAMICO", "0.6");
		nuevaInfo.agregarCaracteristica("PESO", "10");
		agregarModelo(nuevaInfo); //agrega una rueda básica al catálogo
	}
	public Rueda fabricar(InformacionDelModelo modelo) {
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
			e.printStackTrace();
		}
		return null;
	}
	 
}
 
