package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoManual extends Auto {

public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
	        Caja caja, MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
	        Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){
		super(escape,carroceria,motor,caja,mezcladorNafta,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
}

/*********************************************************************************/
//CAJA

public void ponerCambio(int cambio){
	Caja cajaAux = this.getCaja();
	//cajaAux.setCambio(cambio); //TODO: setCambio por algún motivo es protegido
	                             //DeVero13: Mirar Caja!!!!
}

/*********************************************************************************/
}
