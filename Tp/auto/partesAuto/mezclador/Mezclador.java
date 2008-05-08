package auto.mezclador;
import combustible.Nafta;
import auto.Tanque.TanqueNafta;

public abstract class Mezclador {

public double obtenerMezcla(double litrosDeMezcla, TanqueNafta tanque){
	Nafta  nafta    = tanque.getTipoNafta();
	int    octanaje = nafta.getOctanaje();
	int    perdida  = 200 - (octanaje+getEficiencia());
	double mezcla   = tanque.usarNafta(litrosDeMezcla + (litrosDeMezcla*perdida)/100);
	return(mezcla);
}

public abstract int getEficiencia();
	
/*********************************************************************************/
}


