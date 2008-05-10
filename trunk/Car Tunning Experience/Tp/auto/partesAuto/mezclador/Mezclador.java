package auto.partesAuto.mezclador;
import auto.ParteAuto;
import auto.partesAuto.tanque.TanqueNafta;

import combustible.Nafta;

public abstract class Mezclador extends ParteAuto{

	private int eficiencia;
	private TanqueNafta tanqueNafta;
	
public Mezclador(int eficiencia,TanqueNafta tanqueNafta) {
		super();
		this.setEficiencia(eficiencia);
		this.setTanqueNafta(tanqueNafta);
}

/*********************************************************************************/
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
		tanqueNafta.usarNafta(mezcla);
		return mezcla;
}


public int getEficiencia(){
	return eficiencia;
}

public void setEficiencia(int eficiencia) {
	if(eficiencia < 0)
	   this.eficiencia = 0;
	else if (eficiencia > 100)
		this.eficiencia = 100;
	else this.eficiencia=eficiencia;
}

public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
}
	
/*********************************************************************************/
}

