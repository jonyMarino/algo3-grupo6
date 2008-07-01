package proveedorDePartes.fabricas;
import java.util.LinkedList;
import java.lang.RuntimeException;

import excepciones.BoundsException;


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

	Eje(Rueda ruedaTrasera){
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
		double fuerza= torque/ruedaTrasera.getRadioEnMetros();
		//if(fuerza>=ruedaTrasera.getFuerzaRozamientoEstatico()){	// Verifica que no haya dezliz
			//if(getRpm()>0)
				//return - ruedaTrasera.getFuerzaRozamientoDinamico();
			//return 0;
		//}
		//TODO: revisar cuidadosamente el código. Sobre todo el caso en que fuerza< rozamiento estático y el uto está detenido
		return fuerza;
	}

    /**
     *
     *  Agrega un nuevo {@link Torqueador} al {@link Eje}
     *
     *  @see Torqueador
     */
//Desgraciadamente, por como implementa Java la visibilidad de paquete y los paquetes, solo puede ser declarado como publico
	/*
	 * Llamar al parametro iteradorTorqueador, no tiene sentido porque no es ningun iterador.
	 */
	public void addTorqueador(Torqueador torqueador) {
		if(torqueador==null)
			throw new NoTorqueadorException();
		torques.add(torqueador);
	}
	
	public void deleteTorqueador(Torqueador torqueador){
		torques.remove(torqueador);
	}
	
	public void desgastar(double tiempo){
		try{
			if(getVidaUtil()!=0)
				setVidaUtil(getVidaUtil()-tiempo/1000);
		}catch(BoundsException e){
			try{
				setVidaUtil(0);			
			}catch(BoundsException f){}
		}
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




