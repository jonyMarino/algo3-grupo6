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
		double mezcla = litrosDeMezcla+(litrosDeMezcla *(200 - (this.getEficiencia() + naftaAux.getOctanaje())));
		tanqueNafta.usarNafta(mezcla);
		return mezcla;
}


public int getEficiencia(){
	return eficiencia;
}

public void setEficiencia(int eficiencia) {
	this.eficiencia = eficiencia;
}

public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
}
	
/*********************************************************************************/
}

