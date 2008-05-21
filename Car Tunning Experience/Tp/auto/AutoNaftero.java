package auto;

import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public abstract class AutoNaftero extends Auto {
	
	private TanqueNafta    tanqueNafta;
	private MezcladorNafta mezcladorNafta;
	
	public AutoNaftero(Escape escape, Carroceria carroceria, Motor motor,
            Caja caja, MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
            Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){

		super(escape,carroceria,motor,caja,mezcladorNafta,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
	}

	//TANQUE NAFTA
	public TanqueNafta getTanqueNafta() {
		return tanqueNafta;
	}

	public void setTanqueNafta(TanqueNafta tanqueNafta) {
		this.tanqueNafta = tanqueNafta;
		MezcladorNafta mezcladorNafta = this.getMezcladorNafta();
		mezcladorNafta.setTanqueNafta(this.getTanqueNafta());
	}
	
	public double obtenerCantidadNafta(){
		TanqueNafta tanqueNafta = this.getTanqueNafta();
		double cantidadCombustible = tanqueNafta.getCantidadCombustible();
		return cantidadCombustible;
	}
	
	//MEZCLADOR NAFTA
	public MezcladorNafta getMezcladorNafta() {
		return mezcladorNafta;
	}

	public void setMezcladorNafta(MezcladorNafta mezcladorNafta) {
		this.mezcladorNafta = mezcladorNafta;
		Motor motor = this.getMotor();
		motor.setMezclador(this.getMezcladorNafta());
	}
	
}
