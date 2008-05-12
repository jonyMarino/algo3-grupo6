package auto.partesAuto;
import java.util.LinkedList;

import auto.Auto;
import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.Torque;

/**
 * El Eje vincula la {@link Caja} con las {@link Rueda}s.
 * Es el vínculo por el cual se transmite el torque hacia las {@link Rueda}s
 * 
 * @see Rueda
 * @see Caja
 * @see PartesAuto
 *
 */
public class Eje extends PartesAuto{
 
	private Freno freno;
	private LinkedList torques;
	private Rueda rueda;
	private Auto auto;
	private Caja caja;
	
	public Eje(Rueda rueda){
		this.setRueda(rueda);
		//Crea torques
	}
	
	
	public double getFuerza() {
		//1 Obtiene los torques
		int rodado= rueda.getRodado();
		double coeficienteEstatico = rueda.getCoeficienteEstatico();	
		double coeficienteDinamico = rueda.getCoeficienteDinamico();
		
		return(0);
	}

	public Torque obtenerUnTorque() {
		return null;
	}


	public Rueda getRueda() {
		return rueda;
	}


	public void setRueda(Rueda rueda) {
		this.rueda = rueda;
	}



}
 
