package auto.partesAuto;
import auto.Auto;
import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.pedal.Freno;

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
	 

	private LinkedList torques;
	 
	private Rueda rueda;
	 
	private Auto auto;
	 
	private Caja caja;
	 
	public double getFuerza() {
		double torque=0;
		for(Torque t:torques)
			torque+=t.getMagnitud();
		return torque/rueda.rodado();
	}
	 
	public Torque obtenerUnTorque() {
		Torque torque= new Torque();
		torques.add(torque);
		return torque;
	}
	 
}
 

 
