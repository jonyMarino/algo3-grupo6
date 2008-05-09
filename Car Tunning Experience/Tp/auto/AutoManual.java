package auto;
import auto.parteAuto.carroceria.Carroceria;
import auto.parteAuto.escape.Escape;
import auto.parteAuto.mezclador.Mezclador;
import auto.parteAuto.rueda.Rueda;
import auto.parteAuto.tanque.TanqueNafta;

public class AutoManual extends Auto {
	
public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
	        Caja caja, Rueda rueda, Mezclador mezclador, TanqueNafta tanqueNafta){
		super(escape,carroceria,motor,caja,rueda,mezclador,tanqueNafta);
}

/*********************************************************************************/
//CAJA

public void ponerCambio(float cambio){
	Caja cajaAux = this.getCaja();
	cajaAux.setCambio(cambio);
}

/*********************************************************************************/
}
