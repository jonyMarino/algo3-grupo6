package auto;

import proveedorDePartes.fabricas.Motor;
import excepciones.WrongPartClassException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Eje;
import auto.partesAuto.Escape;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.caja.CajaAutomatica;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.pedal.Acelerador;
import auto.partesAuto.pedal.Freno;
import auto.partesAuto.tanque.TanqueNafta;

/**
 * AutoSecuencial es una clase particular de {@link Auto}.
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
public class AutoSecuencial extends Auto {

	/**
	 * Crea un nuevo autoSecuencial con las partes especificadas.
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
	public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
	                      CajaAutomatica cajaAutomatica, MezcladorNafta mezclador, TanqueNafta tanque,
	                      Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4) throws WrongPartClassException {

		super(escape,carroceria,motor,cajaAutomatica,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
	}

	/**
	 * Devuelve el {@link CajaAutomatica} asociado al AutoSecuencial.
	 *
	 * @return La {@link CajaAutomatica} asociada.
	 *
	 * @see CajaAutomatica
	 */
	public CajaAutomatica getCaja() {
		return (CajaAutomatica)super.getCaja();
	}

	/**
	 * Asigna un {@link CajaAutomatica} al AutoSecuencial.
	 *
	 * @param El {@link Caja} a asignar.
	 *
	 * @see CajaAutomatica
	 *
	 * @throws WrongPartClassException
	 */
	public void setCaja(Caja cajaAutomatica) throws WrongPartClassException {
		if (! (cajaAutomatica instanceof CajaAutomatica))
			throw new WrongPartClassException("La parte deber�a ser una caja Automatica");
		super.setCaja(cajaAutomatica);
	}

}
