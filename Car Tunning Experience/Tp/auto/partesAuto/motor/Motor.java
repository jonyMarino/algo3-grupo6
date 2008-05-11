package auto.partesAuto.motor;

import auto.PartesAuto;
import auto.partesAuto.Escape;
import auto.partesAuto.mezclador.Mezclador;

public class Motor extends PartesAuto {
	private double rpmMaximo;
	private int rendimiento;
	private double rpm;
	//private double cilindrada;
	private Mezclador mezclador;
	private int cambio;
	private boolean NecesitoCambio;
	private Escape escape;
	private double temperatura;
	
	public Motor(int rendimiento, double rpmMaximo, Mezclador mezclador, Escape escape){
		super();
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

	private void setRPMMaximo(double rpmMaximo){
		if (rpmMaximo>100)
			this.rpmMaximo=100;
		else if (rpmMaximo < 0)
			this.rpmMaximo=0;
		else this.rpmMaximo=rpmMaximo;
	}
	
	public void acelerar(){
		double mezcla;
		if(getVidaUtil()>0){
		mezcla=mezclador.obtenerMezcla(0.2);
		aumentarRpm(realizarCombustión(mezcla));
		actualizarVidaUtil();
		}
		else disminuiRPM(2.0);
	}
	
	private void actualizarVidaUtil() {
	    double deltaVidaUtil=temperatura/20;
	    if (NecesitoCambio)
	    	deltaVidaUtil *= 2;
		setVidaUtil(getVidaUtil()-deltaVidaUtil);
	}

	private double realizarCombustión(double mezcla){
		return (evacuarGases(mezcla*getRendimiento()/100));
	}
	
	private double evacuarGases(double mezcla) {
		temperatura+=(100-escape.getEficiencia())/3;
		return(escape.getEficiencia()*mezcla/100);
	}

	public double obtenerRPM(){
		return rpm; 
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public void aumentarRpm(double incrementoRPM) {
		rpm += incrementoRPM*((getRPMMaximo()-rpm)/getRPMMaximo());
		temperatura += ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm>=getRPMMaximo())
			NecesitoCambio = true;
	}
	
	public void disminuiRPM(double decrementoRPM){
		rpm += decrementoRPM*((getRPMMaximo()+rpm)/getRPMMaximo());
		temperatura -= ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm<getRPMMaximo())
			NecesitoCambio = false;
	}

	public double getRPMMaximo() {
		return rpmMaximo;
	}
}
