package auto.partesAuto;

import java.math.*;
import auto.Auto;
import auto.partesAuto.pedal;
import auto.partesAuto.escape;

public abstract class Motor extends PartesAuto {
 
	private long rpmMaximo;
	 
	private int rendimiento;
	 
	private int cilindrada;
	 
	private Auto auto;
	 
	private Acelerador acelerador;
	 
	private Escape escape;
	 
	private auto.partesAuto.mezclador.Mezclador mezclador;
	 
	private Caja caja;
	 
	public void acelerar(double valor) {	//valor : 0..1
		double mezcla=mezclador.obtenerMezcla(valor*cilindrada);	//SACAR: quiza deberia ser: rpm/rpmMaximo*	cilindrada
		double fuerza = Math.exp(-auto.getRPM()) * mezcla * rendimiento * escape.getEficiencia();
		caja.convertir(fuerza);
	}
	 
	public float obtenerRps() {
		return caja.obtenerRpsEntrada();
	}
	 
}
 
