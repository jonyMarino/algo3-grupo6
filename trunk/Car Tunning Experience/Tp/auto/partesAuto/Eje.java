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


    /**
     *
     *   Devuelve un fuerza en funcion de los torques aplicados al {@link Eje}
     *   y de la fuerza de rozamiento a la cual estan sometidas las {@link Rueda}s
     *   @return La fuerza neta generada
     *
     *   @see Torqueador
     *   @see Rueda
     */
	public double getFuerza() {
		double torque=0;
		for(Torqueador iteradorTorques:torques)
			torque+=iteradorTorques.getTorque();
		double fuerza= torque/ruedaTrasera.getRodado();
		if(fuerza>=ruedaTrasera.getFuerzaRozamientoEstatico()){	// Verifica que no haya dezliz
			if(getRpm()>0)
				return - ruedaTrasera.getFuerzaRozamientoDinamico();
			return 0;
		}
		return fuerza;
	}

    /**
     *
     *  Agrega un nuevo {@link Torqueador} al {@link Eje}
     *
     *  @see Torqueador
     */

	public void addTorqueador(Torqueador iteradorTorques) {
		if(iteradorTorques==null)
			throw new NoTorqueadorException();
		torques.add(iteradorTorques);
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
	

    /**
     *
     *   Calcula las RPM del {@link Eje}
     *
     *   @return Las RPM del {@link Eje}
     */

	public double getRpm(){
		return ruedaTrasera.getRPM();
	}


	public Rueda getRuedaTrasera() {
		return ruedaTrasera;
	}


	public void setRuedaTrasera(Rueda ruedaTrasera) {
		this.ruedaTrasera = ruedaTrasera;
	}

}




