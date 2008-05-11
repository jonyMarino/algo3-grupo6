package auto.partesAuto.motor;

import auto.PartesAuto;
import auto.partesAuto.Escape;
import auto.partesAuto.mezclador.Mezclador;

public class Motor extends PartesAuto {
	private long rpmMaximo;
	private int rendimiento;
	private long rpm;
	//private double cilindrada;
	private Mezclador mezclador;
	private int cambio;
	private boolean NecesitoCambio;
	private Escape escape;
	private double temperatura;
	
	public Motor(int rendimiento, long rpmMaximo, Mezclador mezclador, Escape escape){
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		this.mezclador = mezclador;
		this.escape=escape;
		rpm=0;
		temperatura=0;
	}
	
	private void setRendimiento(int rendimiento){
		if (rendimiento>100)
			this.rendimiento=100;
		else if (rendimiento < 0)
			this.rendimiento=0;
		else this.rendimiento=rendimiento;
	}

	private void setRPMMaximo(long rpmMaximo){
		if (rpmMaximo>100)
			this.rpmMaximo=100;
		else if (rpmMaximo < 0)
			this.rpmMaximo=0;
		else this.rpmMaximo=rpmMaximo;
	}
	
	public acelerar(){
		double mezcla;
		mezcla=mezclador.obtenerMezcla(litrosDeMezcla);
		realizarCombustión(mezcla);
	}
	
	private double realizarCombustión(double mezcla){
		return (mezcla*getRendimiento()/100);
	}
		
	public long obtenerRPM(){
		return rpm; 
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public void aumentarRpm(long incrementoRPM) {
		rpm += incrementoRPM*((getRPMMaximo()-rpm)/getRPMMaximo());
		temperatura += 2*((getRPMMaximo()- temperatura));
	}

	public long getRPMMaximo() {
		return rpmMaximo;
	}
}
