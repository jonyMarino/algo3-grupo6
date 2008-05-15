
package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoManual extends Auto {

	CajaManual cajaManual;

public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
		        CajaManual cajaManual, MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
		        Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){
		super(escape,carroceria,motor,cajaManual,mezcladorNafta,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
			this.setCajaManual(cajaManual);

		}

	public void ponerCambio(int cambio){
		CajaManual cajaManual = this.getCajaManual();
		cajaManual.setCambioManual(cambio);
	}

	public float getCambio(){
		CajaManual cajaManual = this.getCajaManual();
		float cambio = cajaManual.getCambio();
		return cambio;
	}

	public CajaManual getCajaManual() {
		return cajaManual;
	}

	public void setCajaManual(CajaManual cajaManual) {
		this.cajaManual = cajaManual;
	}

}
