package auto.partesAuto.caja;

import auto.ParteAuto;
import auto.partesAuto.*;
import auto.partesAuto.Eje;
import auto.partesAuto.Motor;
import java.lang.RuntimeException;
/**
 * La Caja transforma (se podría decir que lo "amplifica") el torque que genera el {@link Motor}.
 * La transformación del torque depende del cambio.
 * Caja es una clase abstracta que sirve como base para distintos tipos de Caja.
 * @see PartesAuto
 * @see Motor
 * @see CajaManual
 * @see CajaAutomatica
 *
 */
public abstract class Caja extends ParteAuto implements Torqueador{

	private int cambio;
	private int[] relaciones;
	private Motor motor;
	private Eje eje;

	public Caja(){
		cambio=0; //empieza en punto muerto
		relaciones = new int[6];
		for(int i=0;i<6;i++){
			relaciones[i]=30-i*5;
		}
	}

	protected double convertir(double torque){
			return torque*relaciones[cambio];
	}
	public abstract double getTorque();

	protected void setCambio(int cambio) {
		if((cambio>=0) && (cambio<=5))
		this.cambio=cambio;
		//TODO: NO SE DEBERÍA PODER PASAR AL PROXIMO CAMBIO SI LAS REVOLUCIONES NO SON SUFICIENTES
	}

	public void setMotor(Motor motor){
		this.motor = motor;
	}
	public Motor getMotor(){
		return motor;
	}

	public void setEje(Eje eje){
		if(eje==null)
			throw new RuntimeException();
		this.eje=eje;
		eje.addTorqueador(this);
	}
	public Eje getEje(){
		return eje;
	}

	public int getCambio() {
		return cambio;
	}

	protected void incCambio(){
		if(cambio<=5)
			cambio++;
		//TODO: MANEJAR CON EXCEPCIONES
		//TODO: NO SE DEBERÍA PODER PASAR AL PROXIMO CAMBIO SI LAS REVOLUCIONES NO SON SUFICIENTES
	}

	public double obtenerRpmEntrada(){
		if(eje==null)
			return 0;
		return eje.getRpm()*relaciones[cambio];
	}

}