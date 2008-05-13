package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoSecuencial extends Auto {

public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
	        Caja caja, Mezclador mezclador, TanqueNafta tanqueNafta,
	        Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){
		super(escape,carroceria,motor,caja,mezclador,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
}
/*********************************************************************************/
}
