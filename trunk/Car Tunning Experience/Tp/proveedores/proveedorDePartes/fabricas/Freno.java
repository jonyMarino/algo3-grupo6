package proveedores.proveedorDePartes.fabricas;

import excepciones.PartBrokenException;

/**
 * 
 * El pedal Freno es el encargado de generar un fuerza contraria a la aceleracion del auto.
 *
 * @see Pedal
 * 
 */
public class Freno extends Pedal implements Torqueador {

	private double torque;
	private double pastillaDeFreno;
	private Eje eje;

	Freno(double pastillaDeFreno) {
		super();
		this.setTorque(0);
		this.setPastillaDeFreno(pastillaDeFreno);
	}
	
	public void setEje(Eje unEje){
		if(eje!=null)
			eje.deleteTorqueador(this);
		eje=unEje;
		if(eje!=null)
			eje.addTorqueador(this);		
	}

	public void presionar(double intensidad) throws PartBrokenException {
		setUsado(false);
		if (this.getVidaUtil() > 0){
			this.setTorque(- (intensidad * this.getPastillaDeFreno()));
		} else
			throw new PartBrokenException("El Freno se rompio");
	}

	public double getTorque() {
		return torque;
	}

	private void setTorque(double intensidad) {
		this.torque = intensidad;
	}

	public double getPastillaDeFreno() {
		return pastillaDeFreno;
	}

	public void setPastillaDeFreno(double pastillaDeFreno) {
		this.pastillaDeFreno = pastillaDeFreno;
	}

}
