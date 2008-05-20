package auto.partesAuto;
import java.util.LinkedList;
import java.lang.RuntimeException;
import auto.PartesAuto;
import auto.partesAuto.caja.Caja;

class NoTorqueadorException extends RuntimeException{
	static final long serialVersionUID=1;
}
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
		double fuerza= torque/ruedaTrasera.getRodado();
		if(fuerza>=ruedaTrasera.getFuerzaRozamientoEstatico()){	// Verifica que no haya dezliz
			if(getRpm()>0)
				return - ruedaTrasera.getFuerzaRozamientoDinamico();
			return 0;
		}
		return fuerza;
	}

	public void addTorqueador(Torqueador t) {
		if(t==null)
			throw new NoTorqueadorException();
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




