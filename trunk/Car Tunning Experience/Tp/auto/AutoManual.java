package auto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaManual;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoManual extends Auto {

	CajaManual cajaManual;
	private TanqueNafta    tanqueNafta;
	private MezcladorNafta mezcladorNafta;

public AutoManual(Escape escape, Carroceria carroceria, Motor motor,
		          CajaManual cajaManual, MezcladorNafta mezclador, TanqueNafta tanque,
		          Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){

		super(escape,carroceria,motor,cajaManual,mezclador,tanque,rueda1,rueda2,rueda3,rueda4);
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
	
	//TODO: Falta modificar
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
	
	public void cargarCombustible(double litros){
		try {
			this.getTanqueNafta().llenarTanque(litros);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
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
