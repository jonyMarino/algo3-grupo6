package auto.partesAuto.pedal;

import auto.partesAuto.BoundsException;



/**
 * Interfaz para los pedales. 
 *
 *@see Acelerador
 *@see Freno
 */
public interface Pedal {
	
public void presionar(double cantidad) throws BoundsException;

/*********************************************************************************/
}
