package auto.partesAuto.motor;

import auto.PartesAuto;
import auto.partesAuto.Escape;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;


/**
 * El Motor es el alma del {@link Auto}.
 * Es el encargado del proceso de combusti�n del {@link Combustible}, que obtiene a traves del {@link Mezclador}.
 * A partir del proceso de combusti�n genera un torque que luego pasa por la {@link Caja}.
 * @see PartesAuto
 * @see Mezclador
 * @see Combstible
 * @see Caja
 *
 */
public class Motor extends PartesAuto {
	private double rpmMaximo;
	private int rendimiento;
	private double rpm;
	//private double cilindrada;
	private Mezclador mezclador;
	private Caja caja;
	private boolean NecesitoCambio;
	private Escape escape;
	private double temperatura;
	
	public Motor(int rendimiento, double rpmMaximo, Mezclador mezclador, 
			     Escape escape, Caja caja){
		super();
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		this.mezclador = mezclador;
		this.escape=escape;
		this.caja=caja;
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
	
	/**
	 * Pide {@link Combustible} mezclado con aire al {@link Mezclador} y luego realiza la combusti�n eliminando los gases por el {@link Escape}.
	 * A partisr de la combustion, aumenta las RPM del Motor.
	 * 
	 * @param aceleracion No se refiere a la m�gnitud f�sica, sino a un n�mero de 0 a 1  que indica cuanto se presion� el {@link Acelerador}.  
	 */
	public void acelerar(double aceleracion){  //No es aceleracion del punto de vista f�sico. aceleracion [0..1]
		double mezcla;
		if(getVidaUtil()>0){
			if (aceleracion>1)
				aceleracion=1;
			else if (aceleracion < 0)
				aceleracion = 0;
		mezcla=mezclador.obtenerMezcla(aceleracion);
		aumentarRPM(realizarCombusti�n(mezcla));
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

	private double realizarCombusti�n(double mezcla){
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

	public void aumentarRPM(double incrementoRPM) {
		rpm += Math.exp(-obtenerRPM()/getRPMMaximo()/2)*incrementoRPM;
		temperatura += ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm>=getRPMMaximo()/(6-caja.getCambio()))
			NecesitoCambio = true;
	}
	
	public void disminuiRPM(double decrementoRPM){
		rpm += decrementoRPM*((getRPMMaximo()+rpm)/getRPMMaximo());
		temperatura -= ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm<getRPMMaximo()/(6-caja.getCambio()))
			NecesitoCambio = false;
	}

	public double getRPMMaximo() {
		return rpmMaximo;
	}

	public boolean necesitaCambio() {
		return NecesitoCambio;
	}

	public void nuevoCambio() {
		disminuiRPM(obtenerRPM()/2); //bajo las revoluciones a la mitad				
	}

	public void detenerse() {
				
	}

	public Escape getEscape() {
		return escape;
	}

	public void setEscape(Escape escape) {
		this.escape = escape;
	}
}
