package auto;
import auto.partesAuto.carroceria.Carroceria;
import auto.partesAuto.escape.Escape;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.rueda.Rueda;
import auto.partesAuto.tanque.TanqueNafta;

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
