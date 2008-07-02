package proveedorDePartes.fabricas;

import java.util.LinkedList;
import java.lang.RuntimeException;
import excepciones.BoundsException;
import excepciones.WheelPunctureException;


class NoTorqueadorException extends RuntimeException{
	static final long serialVersionUID=1;
}
/**
 * 
 * El Eje vincula la {@link Caja} con las {@link Rueda}s.
 * Es el vínculo por el cual se transmite el torque hacia las {@link Rueda}s
 *
 * @see Rueda
 * @see Caja
 * @see PartesAuto
 *
 */

public class Eje extends ParteAuto {

	private LinkedList<Torqueador> torques = new LinkedList<Torqueador>();

	private Rueda ruedaTrasera;

	Eje(Rueda ruedaTrasera) {
		this.ruedaTrasera = ruedaTrasera;
	}

	public double getFuerza() {
		double torque = 0;
		for(Torqueador iteradorTorques:torques)
			torque += iteradorTorques.getTorque();
		double fuerza;
		try {
			fuerza = torque/ruedaTrasera.getRadioEnMetros();
		} catch (WheelPunctureException e) {
			fuerza = 0;
		}
		//TODO: revisar cuidadosamente el código. Sobre todo el caso en que fuerza< rozamiento estático y el uto está detenido
		return fuerza;
	}

 	public void addTorqueador(Torqueador torqueador) {
		if(torqueador == null)
			throw new NoTorqueadorException();
		torques.add(torqueador);
	}
	
	public void deleteTorqueador(Torqueador torqueador) {
		torques.remove(torqueador);
	}
	
	public void desgastar(double tiempo) {
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/1000);
		}catch(BoundsException e){
			try{
				setVidaUtil(0);			
			}catch(BoundsException f){}
		}
	}
	
	public double getRpm() {
		try {
			return ruedaTrasera.getRPM();
		} catch (WheelPunctureException e) {
			return 0;
		}
	}

	public Rueda getRuedaTrasera() {
		return ruedaTrasera;
	}

	public void setRuedaTrasera(Rueda ruedaTrasera) {
		this.ruedaTrasera = ruedaTrasera;
	}
	
}




