package auto;
import auto.parteAuto.carroceria.Carroceria;
import auto.parteAuto.escape.Escape;
import auto.parteAuto.mezclador.Mezclador;
import auto.parteAuto.rueda.Rueda;
import auto.parteAuto.tanque.TanqueNafta;

public class AutoSecuencial extends Auto {

public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
        Caja caja, Rueda rueda, Mezclador mezclador, TanqueNafta tanqueNafta){
	super(escape,carroceria,motor,caja,rueda,mezclador,tanqueNafta);
}

/*********************************************************************************/
}
