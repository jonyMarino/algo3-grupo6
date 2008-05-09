package auto.parteAuto.mezclador;
import auto.parteAuto.tanque.TanqueNafta;
import auto.ParteAuto;

public abstract class Mezclador extends ParteAuto{

	private int eficiencia;
	
public Mezclador(int eficiencia) {
		super();
		this.setEficiencia(eficiencia);
}

/*********************************************************************************/
public double obtenerMezcla(double litrosDeMezcla, TanqueNafta tanque){
	
}

public int getEficiencia(){
	return eficiencia;
}

public void setEficiencia(int eficiencia) {
	this.eficiencia = eficiencia;
}

private abstract int getPerdidas();
	
/*********************************************************************************/
}

