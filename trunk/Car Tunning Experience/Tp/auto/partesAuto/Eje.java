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
 
	private Freno freno;
	private Torque torque;
	private Rueda rueda;
	private Auto auto;
	private Caja caja;
	 
	public double getFuerza() {
	}
	 
	public Torque obtenerUnTorque() {
		return null;
	}
	 
}
 
