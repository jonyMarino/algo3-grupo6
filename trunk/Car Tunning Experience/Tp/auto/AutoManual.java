package auto;

import excepciones.WrongPartClassException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoManual extends Auto {

//private	CajaManual cajaManual;

public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
		          CajaManual cajaManual, MezcladorNafta mezclador, TanqueNafta tanque,
		          Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4) throws WrongPartClassException{

		super(escape,carroceria,motor,cajaManual,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
		setCaja(cajaManual);
	}

	public void ponerCambio(int cambio){
		//CajaManual cajaManual = this.getCajaManual();
		((CajaManual)getCaja()).setCambioManual(cambio);
	}

	public float getCambio(){
		//CajaManual cajaManual = this.getCajaManual();
		float cambio = ((CajaManual)getCaja()).getCambio();
		return cambio;
	}
/*
	public CajaManual getCajaManual() {
		return cajaManual;
	}
*/
	public void setCaja(Caja cajaManual) throws WrongPartClassException{
		if (! (cajaManual instanceof CajaManual))
			throw new WrongPartClassException("La parte debería ser una caja Manual");
		super.setCaja(cajaManual);
	}

}
