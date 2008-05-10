package auto.partesAuto.mezclador;
import auto.partesAuto.tanque.TanqueNafta;
import combustible.Nafta;


public class MezcladorNafta extends Mezclador {

	public MezcladorNafta(int eficiencia, TanqueNafta tanqueNafta) {
		super(eficiencia, tanqueNafta);
	}
	
public double obtenerMezcla(double litrosDeMezcla){
	TanqueNafta tanqueNaftaAux = this.getTanqueNafta();
	Nafta naftaAux = tanqueNaftaAux.getTipoNafta();
	// Creo yo que no es la forma mas conveniente de hacerlo. ¿Que pasa si le pedimos al tanque que
	// use mas nafta de la que tiene? Creo que debería hacerse al revés. Propongo algo así:
	
	// tanqueNafta.usarNafta(litrosDeMezcla);
	// double mezcla = litros+(litros *(200 - (this.getEficiencia() + naftaAux.getOctanaje())));
		
	// Creo que sería "mas adecuado"
		
	/*En todo caso hacemos es asi
	 * litrosUsados = tanqueNaftaAux.usarNafta();
	 *  //ahi te devuelve los litros que "puede darte"
	 *  //y despues aplicamos el calcular,
	 *  double mezcla = litros+(litros *(200 - (this.getEficiencia() + naftaAux.getOctanaje())));
	 *  if(litrosUsados > mezcla)
	 *  	mezcla = litrosUsados;
	 *  //creo que ahi deberia andar no??
	 */
		
	double mezcla = litrosDeMezcla+(litrosDeMezcla *(200 - (this.getEficiencia() + naftaAux.getOctanaje())));
	tanqueNaftaAux.usarNafta(mezcla);
	return mezcla;
}

	
	
}
