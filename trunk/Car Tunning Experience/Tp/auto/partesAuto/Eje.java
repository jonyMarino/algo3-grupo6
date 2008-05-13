package auto.partesAuto;
import auto.Auto;
import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.pedal.Freno;
import java.util.*;
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
	 

	private LinkedList<Torqueador> torques = new LinkedList<Torqueador>();
	 
	private Rueda ruedaTrasera;
	 
	public Eje(Rueda ruedaTrasera){
		this.ruedaTrasera=ruedaTrasera;	
	}
	 
	public double getFuerza() {
		double torque=0;
		for(Torqueador t:torques)
			torque+=t.getTorque();
		return torque/ruedaTrasera.getRodado();
	}
	 
	public void addTorqueador(Torqueador t) {
		if(t==null)
			return;
		torques.add(t);
	}
	public boolean desgastar(int tiempo){
		setVidaUtil(getVidaUtil()-tiempo/1000);
		return desgastado();
	}
	public double getRpm(){
		return ruedaTrasera.getRPM();
	}
	 
}
 

 
