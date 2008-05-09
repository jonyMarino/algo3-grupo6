package auto.parteAuto.mezclador;
import auto.parteAuto.tanque.TanqueNafta;
import auto.ParteAuto;

public abstract class Mezclador extends ParteAuto{

	private int eficiencia;
	
public Mezclador(int eficiencia,double peso, double costo, double desgaste) {
		super(peso, costo, desgaste);
		this.setEficiencia(eficiencia);
}

public abstract double obtenerMezcla(double litrosDeMezcla, TanqueNafta tanque);

public int getEficiencia(){
	return eficiencia;
}

public void setEficiencia(int eficiencia) {
	this.eficiencia = eficiencia;
}
	
/*********************************************************************************/
}

