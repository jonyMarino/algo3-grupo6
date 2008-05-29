package auto;

import excepciones.WrongPartClassException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.caja.CajaAutomatica;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoSecuencial extends Auto {

	private CajaAutomatica cajaAutomatica;

	public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
	                      CajaAutomatica cajaAutomatica, MezcladorNafta mezclador, TanqueNafta tanque,
	                      Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4) throws WrongPartClassException{

		super(escape,carroceria,motor,cajaAutomatica,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
		setCaja(cajaAutomatica);

	}
/*
	public CajaAutomatica getCajaAutomatica() {
		return cajaAutomatica;
	}
*/
	public void setCaja(Caja cajaAutomatica) throws WrongPartClassException{
		if (! (cajaAutomatica instanceof CajaAutomatica))
			throw new WrongPartClassException("La parte debería ser una caja Automatica");
		super.setCaja(cajaAutomatica);
	}
}
