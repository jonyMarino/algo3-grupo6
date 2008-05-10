package auto;
import auto.partesAuto.carroceria.Carroceria;
import auto.partesAuto.escape.Escape;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.rueda.Rueda;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoSecuencial extends Auto {

public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
        Caja caja, Rueda rueda, Mezclador mezclador, TanqueNafta tanqueNafta){
	super(escape,carroceria,motor,caja,rueda,mezclador,tanqueNafta);
}

/*********************************************************************************/
}
