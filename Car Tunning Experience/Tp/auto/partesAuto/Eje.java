package auto.partesAuto;
import java.util.LinkedList;

import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.Torque;

/**
 * El Eje vincula la {@link Caja} con las {@link Rueda}s.
 * Es el vínculo por el cual se transmite el torque hacia las {@link Rueda}s
 * 
 * @see Rueda
 * @see PartesAuto
 *
 */
public class Eje extends PartesAuto{
 
	private LinkedList torques;
	private Rueda rueda;
	
	public Eje(Rueda rueda,Freno freno){
		this.setRueda(rueda);
		torques = new LinkedList();
		//Almacena los torques de Freno y Caja
		torques.add(freno.getTorqueFreno());
		Torque torqueCaja = new Torque(0);
		torques.add(torqueCaja);
	}
	
	public double getFuerza() {
		double fuerza = 0;
		if(this.getVidaUtil() > 0){
		//Obtiene los torques
		int rodado= rueda.getRodado();
		double coeficienteEstatico = rueda.getCoeficienteEstatico();	
		double coeficienteDinamico = rueda.getCoeficienteDinamico();
		
		}
		return(fuerza);
	}

	public Torque getTorqueFreno() {
		LinkedList torquesAux = this.getTorques();
		return ((Torque)torquesAux.get(0));
	}
	
	public Torque getTorqueCaja() {
		LinkedList torquesAux = this.getTorques();
		return ((Torque)torquesAux.get(1));
	}
	
	public void setTorqueCaja(double magnitud) {
		Torque torqueCaja = this.getTorqueCaja();
		torqueCaja.setMagnitud(magnitud);
	}

	public Rueda getRueda() {
		return rueda;
	}

	private void setRueda(Rueda rueda) {
		this.rueda = rueda;
		
	}
	
	private LinkedList getTorques(){
		return torques;
	}

}
 
