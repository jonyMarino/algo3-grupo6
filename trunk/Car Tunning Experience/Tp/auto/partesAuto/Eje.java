package auto.partesAuto;
import java.util.LinkedList;

import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
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
	
	public Eje(Rueda rueda){
		this.setRueda(rueda);
		torques = new LinkedList();
		//Almacena los torques de Freno y Caja
		Torque torqueFreno = new Torque(0);
		torques.add(torqueFreno);
		Torque torqueCaja = new Torque(0);
		torques.add(torqueCaja);
	}
	
	public double getFuerza(){
		return 0;
	}
	
	public double getFuerzaRozamientoDinamico(double masaAuto) {
		double FuerzaRozamientoDinamico = 0;
		if(this.getVidaUtil() > 0){
			FuerzaRozamientoDinamico = rueda.getCoeficienteDinamico() * masaAuto;  	
		}
		return FuerzaRozamientoDinamico;
	}
	
	public double getFuerzaRozamientoEstatico(double masaAuto) {
		double FuerzaRozamientoEstatico = 0;
		if(this.getVidaUtil() > 0){
			FuerzaRozamientoEstatico = rueda.getCoeficienteEstatico() * masaAuto;
		}
		return FuerzaRozamientoEstatico;
	}
	
	public double getSumatoriaTorques(){
		double sumatoriaTorques = 0;
		if(this.getVidaUtil() > 0){
			Torque torqueFreno =  this.getTorqueFreno();
			Torque torqueCaja = this.getTorqueCaja();
			sumatoriaTorques = torqueFreno.getMagnitud() + torqueCaja.getMagnitud();
		}
		return sumatoriaTorques;
	}
	
	public Torque getTorqueFreno() {
		LinkedList torquesAux = this.getTorques();
		return ((Torque)torquesAux.get(0));
	}
	
	public void setTorqueFreno(double magnitud) {
		Torque torqueFreno = this.getTorqueFreno();
		torqueFreno.setMagnitud(magnitud);
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
 
