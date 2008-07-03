package auto;

import java.util.Hashtable;

import nu.xom.Element;

import programaAuto.Auto;
import programaAuto.AutoFactory;
import programaAuto.CadenaDeFabricas;
import programaAuto.Auto.Ubicacion;
import proveedores.proveedorDePartes.fabricas.Caja;
import proveedores.proveedorDePartes.fabricas.CajaAutomatica;
import proveedores.proveedorDePartes.fabricas.CajaManual;
import proveedores.proveedorDePartes.fabricas.Carroceria;
import proveedores.proveedorDePartes.fabricas.Eje;
import proveedores.proveedorDePartes.fabricas.Escape;
import proveedores.proveedorDePartes.fabricas.Freno;
import proveedores.proveedorDePartes.fabricas.MezcladorNafta;
import proveedores.proveedorDePartes.fabricas.Motor;
import proveedores.proveedorDePartes.fabricas.ParteAuto;
import proveedores.proveedorDePartes.fabricas.Rueda;
import proveedores.proveedorDePartes.fabricas.TanqueNafta;
import excepciones.IncorrectPartForUbicationException;
import excepciones.UbicationUnkownException;
import excepciones.WrongPartClassException;

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
	public static class Factory implements AutoFactory{
		public Auto crear(CadenaDeFabricas fabrica,Element elemento) throws IncorrectPartForUbicationException, UbicationUnkownException {		
			return new AutoSecuencial(fabrica,elemento);
		}
	}
	/**
	 * Crea un nuevo autoSecuencial con las partes especificadas.
	 *
	 * @param escape El escape.
	 * @param carroceria La carrocería.
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
	                      Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4, Eje eje, Freno freno) throws WrongPartClassException {

		super(escape,carroceria,motor,cajaAutomatica,mezclador,tanque,rueda1,rueda2,rueda3,rueda4, eje, freno);
	}

	public AutoSecuencial(CadenaDeFabricas fabrica,Element elemento) throws IncorrectPartForUbicationException, UbicationUnkownException {
		super(fabrica,elemento);
		if( !(this.getHashDePartes().get(Ubicacion.CAJA) instanceof CajaAutomatica) )
			throw new IncorrectPartForUbicationException(Ubicacion.CAJA.toString());
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
			throw new WrongPartClassException("La parte debería ser una caja Automatica");
		super.setCaja(cajaAutomatica);
	}

}
