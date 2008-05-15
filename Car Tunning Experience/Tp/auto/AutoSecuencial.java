package auto;
import auto.partesAuto.Carroceria;
import auto.partesAuto.Escape;
import auto.partesAuto.Motor;
import auto.partesAuto.Rueda;
import auto.partesAuto.caja.CajaAutomatica;
import auto.partesAuto.mezclador.MezcladorNafta;
import auto.partesAuto.tanque.TanqueNafta;

public class AutoSecuencial extends Auto {

		private CajaAutomatica cajaAutomatica;

	public AutoSecuencial(Escape escape, Carroceria carroceria, Motor motor,
	        CajaAutomatica cajaAutomatica, MezcladorNafta mezcladorNafta, TanqueNafta tanqueNafta,
	        Rueda rueda1, Rueda rueda2,Rueda rueda3,Rueda rueda4){
		super(escape,carroceria,motor,mezcladorNafta,tanqueNafta,rueda1,rueda2,rueda3,rueda4);
		this.setCajaAutomatica(cajaAutomatica);
	}

	public CajaAutomatica getCajaAutomatica() {
		return cajaAutomatica;
	}

	public void setCajaAutomatica(CajaAutomatica cajaAutomatica) {
		this.cajaAutomatica = cajaAutomatica;
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
		this.incrementarPeso(this.getCajaAutomatica().getPeso());
		this.incrementarPeso(this.getRuedaDelanteraDerecha().getPeso());
		this.incrementarPeso(this.getRuedaDelanteraIzquierda().getPeso());
		this.incrementarPeso(this.getRuedaTraseraDerecha().getPeso());
		this.incrementarPeso(this.getRuedaTraseraIzquierda().getPeso());
		return (getPeso());
	}

}
