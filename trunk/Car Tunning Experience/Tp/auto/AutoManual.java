package auto;

import excepciones.WrongPartClassException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * AutoManual es una clase particular de {@link Auto}.
 *
 *   @see Motor
 *   @see Caja
 *   @see Carroceria
 *   @see Escape
 *   @see TanqueNafta
 *   @see Acelerador
 *   @see Freno
 *   @see Rueda
 *   @see Eje
 *   @see MezcladorNafta
 */
public class AutoManual extends Auto {

	/**
	 * Crea un nuevo autoManual con las partes especificadas.
	 *
	 * @param escape El escape.
	 * @param carroceria La carrocer�a.
	 * @param motor El motor.
	 * @param caja La caja.
	 * @param mezclador El mezcladorNafta.
	 * @param tanque El tanqueNafta.
	 * @param rueda1 La rueda  delantera derecha.
	 * @param rueda2 La rueda delantera izquierda.
	 * @param rueda3 La rueda trasera derecha.
	 * @param rueda4 La rueda trasera izquierda.
	 *
	 * @throws WrongPartClassException.
	 */
	public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
		          CajaManual cajaManual, MezcladorNafta mezclador, TanqueNafta tanque,
		          Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4) throws WrongPartClassException {

		super(escape,carroceria,motor,cajaManual,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
	}

	/**
	 * Modifica el cambio del AutoManual.
	 *
	 * @param cambio El cambio a pasar.
	 */
	public void setCambio(int cambio) {
		((CajaManual)getCaja()).setCambioManual(cambio);
	}

	/**
	 * Devuelve el cambio actual del AutoManual.
	 *
	 * @return cambio El cambio actual.
	 */
	public float getCambio() {
		float cambio = ((CajaManual)getCaja()).getCambio();
		return cambio;
	}

	/**
	 * Devuelve el {@link CajaManual} asociado al AutoManual.
	 *
	 * @return La {@link CajaManual} asociada.
	 *
	 * @see CajaManual.
	 */
	public CajaManual getCaja() {
		return (CajaManual)super.getCaja();
	}

	/**
	 * Asigna un {@link CajaManual) al AutoManual.
	 *
	 * @param El {@link Caja} a asignar.
	 *
	 * @see CajaManual.
	 *
	 * @throws WrongPartClassException.
	 */
	public void setCaja(Caja cajaManual) throws WrongPartClassException {
		if (! (cajaManual instanceof CajaManual))
			throw new WrongPartClassException("La parte deber�a ser una caja Manual");
		super.setCaja(cajaManual);
	}

}
