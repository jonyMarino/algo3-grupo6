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

	double mezcla = 0;
	if (tanqueNaftaAux.obtenerCantidadNafta() !=0){
	mezcla = litrosDeMezcla+(litrosDeMezcla *(200 -(this.getEficiencia()+naftaAux.getOctanaje())));
	double litrosUsados = tanqueNaftaAux.usarNafta(mezcla);
	if(litrosUsados < mezcla)
	       mezcla = litrosUsados;
	}
	return mezcla;
}

	
	
}
