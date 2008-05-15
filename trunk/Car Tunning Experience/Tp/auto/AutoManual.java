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
		super(escape,carroceria,motor,mezcladorNafta,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
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

	public double getPeso(){
		//Se actualiza constantemente
		this.setPeso(this.calcularPeso());
		return getPeso();
	}

	private double calcularPeso(){
		this.setPeso(0);
		this.incrementarPeso(this.getEscape().getPeso());
		this.incrementarPeso(this.getCarroceria().getPeso());
		this.incrementarPeso(this.getMotor().getPeso());
		this.incrementarPeso(this.getTanqueNafta().getPeso());
		this.incrementarPeso(this.getCajaManual().getPeso());
		this.incrementarPeso(this.getRuedaDelanteraDerecha().getPeso());
		this.incrementarPeso(this.getRuedaDelanteraIzquierda().getPeso());
		this.incrementarPeso(this.getRuedaTraseraDerecha().getPeso());
		this.incrementarPeso(this.getRuedaTraseraIzquierda().getPeso());
		return (getPeso());
	}

}
