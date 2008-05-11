package auto.partesAuto.mezclador;
import auto.PartesAuto;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link Tanque}, mezclarlo y dejarlo listo para el proceso de combustión.
 * @see Tanque
 * @see Motor
 * @see PartesAuto
 */

public abstract class Mezclador extends PartesAuto{

	private int eficiencia;
	private TanqueNafta tanqueNafta;
	
public Mezclador(int eficiencia,TanqueNafta tanqueNafta) {
		super();
		this.setEficiencia(eficiencia);
		this.setTanqueNafta(tanqueNafta);
}

/*********************************************************************************/
public abstract double obtenerMezcla(double litrosDeMezcla);
		
public int getEficiencia(){
	return eficiencia;
}

public void setEficiencia(int eficiencia) {
	if(eficiencia < 0)
	   this.eficiencia = 0;
	else if (eficiencia > 100)
		this.eficiencia = 100;
	else this.eficiencia = eficiencia;
}

public TanqueNafta getTanqueNafta() {
	return tanqueNafta;
}

public void setTanqueNafta(TanqueNafta tanqueNafta) {
	this.tanqueNafta = tanqueNafta;
}
	
/*********************************************************************************/
}

