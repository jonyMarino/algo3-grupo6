package auto.partesAuto;
import java.util.LinkedList;
import java.lang.RuntimeException;
import auto.ParteAuto;
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


public class Eje extends ParteAuto{

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
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/1000);
		}catch(BoundsException e){
			try{
				setVidaUtil(0);			
			}catch(BoundsException f){}
		}
		return desgastado();
	}
	
	public double getRpm(){
		return ruedaTrasera.getRPM();
	}

}




