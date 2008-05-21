package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaAutomatica;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoSecuencial extends AutoNaftero {

		private CajaAutomatica cajaAutomatica;

	public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
	                      CajaAutomatica cajaAutomatica, MezcladorNafta mezclador, TanqueNafta tanque,
	                      Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){

		super(escape,carroceria,motor,cajaAutomatica,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
		this.setCajaAutomatica(cajaAutomatica);

	}

	public CajaAutomatica getCajaAutomatica() {
		return cajaAutomatica;
	}

	public void setCajaAutomatica(CajaAutomatica cajaAutomatica) {
		this.cajaAutomatica = cajaAutomatica;
	}

}
