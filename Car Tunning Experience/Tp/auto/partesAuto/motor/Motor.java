package auto.partesAuto.motor;

import auto.PartesAuto;
import auto.partesAuto.Escape;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;


/**
 * El Motor es el alma del {@link Auto}.
 * Es el encargado del proceso de combustión del {@link Combustible}, que obtiene a traves del {@link Mezclador}.
 * A partir del proceso de combustión genera un torque que luego pasa por la {@link Caja}.
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
	private Escape escape;
	private double temperatura;
	private boolean NecesitoCambio;
	
	/**
	*
	* Crea un nuevo Motor con un {@link Mezclador}, {@link Escape} y {@link Caja} dados.
	*
	*@param rendimiento (0..100),
	*@param rpmMaximo  Las revoluciones maximas que pueda alcanzar el Motor
	*@param Mezclador El {@link Mezclador} al cual el Motor pide {@link Combustible}
	*@param Escape  El {@link} Escape a travez del cual se eliminan los gases de ombustion. 
	*@param Caja  La {@link Caja} asociada al Motor
	*
	*@see PartesAuto
	*@see Mezclador
	*@see Escape
	*@see Caja
	*@see Combustible
	*/
	public Motor(int rendimiento, double rpmMaximo, Mezclador mezclador, 
			     Escape escape, Caja caja){
		super();
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		setMezclador(mezclador);
		setEscape(escape);
		setCaja(caja);
		setRPM(0);
		temperatura=0;
	}
	
	private void setRendimiento(int rendimiento){
		if (rendimiento>100)
			this.rendimiento=100;
		else if (rendimiento < 0)
			this.rendimiento=0;
		else this.rendimiento=rendimiento;
	}
	
	/**
	*
	* Devuelve el rendimiento del Motor (0..100)
	*
	*/	
	public int getRendimiento() {
		return rendimiento;
	}
	
	/**
	*
	* Las maximas revoluciones por minuto que puede alcanzar el Motor.
	*
	*/
	public double getRPMMaximo() {
		return rpmMaximo;
	}
	
	private void setRPMMaximo(double rpmMaximo){
		if (rpmMaximo>100)
			this.rpmMaximo=100;
		else if (rpmMaximo < 0)
			this.rpmMaximo=0;
		else this.rpmMaximo=rpmMaximo;
	}
	
	/**
	*
	* Devuelve las revoluciones por minuto actuales del Motor.
	*
	*/	
	public double getRPM(){
		return rpm; 
	}
	
	private void setRPM(double rpm){
		this.rpm=rpm; 
	}

	/**
	*
	*Devuelve el {@link Escape} asociado al Motor.
	*@see Escape
	*/
	public Escape getEscape() {
		return escape;
	}

	/**
	*
	*Asocia un {@link Escape} al Motor.
	*
	*@param escape El {@link Escape} el cual quiero asignar al Motor.
	*
	*@see Escape
	*/
	public void setEscape(Escape escape) {
		this.escape = escape;
	}
	
	/**
	*
	*Devuelve la {@link Caja} asociada al Motor.
	*
	*@see Caja
	*/
	public Caja getCaja() {
		return caja;
	}

	/**
	*
	*Asigna una {@link Caja} al Motor.
	*
	*@param caja  La {@link Caja} a asignar.
	*
	*@see Caja
	*/

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	/**
	*
	*Devuelve el {@link Mezclador} asociado al Motor.
	*
	*@see Mezclador
	*/
	
	public Mezclador getMezclador() {
		return mezclador;
	}

	/**
	*
	*Asocia un {@link Mezclador} al Motor.
	*
	*@param mezclador El {@link Mezclador} el cual quiero asignar al Motor.
	*
	*@see Mezclador
	*/

	public void setMezclador( Mezclador mezclador ) {
		this.mezclador = mezclador ;
	}
	
	/**
	 * Pide {@link Combustible} mezclado con aire al {@link Mezclador} y luego realiza la combustión eliminando los gases por el {@link Escape}.
	 * A partisr de la combustion, aumenta las RPM del Motor.
	 * 
	 * @param aceleracion No se refiere a la mágnitud física, sino a un número de 0 a 1  que indica cuanto se presionó el {@link Acelerador}.  
	 */
	public void acelerar(double aceleracion){  //No es aceleracion del punto de vista físico. aceleracion [0..1]
		double mezcla;
		if(getVidaUtil()>0){
			if (aceleracion>1)
				aceleracion=1;
			else if (aceleracion < 0)
				aceleracion = 0;
		mezcla=mezclador.obtenerMezcla(aceleracion);
		aumentarRPM(realizarCombustión(mezcla));
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



	private void aumentarRPM(double incrementoRPM) {
		rpm += Math.exp(-getRPM()/getRPMMaximo()/2)*incrementoRPM;
		temperatura += ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm>=getRPMMaximo()/(6-caja.getCambio()))
			NecesitoCambio = true;
	}
	
	private void disminuiRPM(double decrementoRPM){
		rpm += decrementoRPM*((getRPMMaximo()+rpm)/getRPMMaximo());
		temperatura -= ((getRPMMaximo() + temperatura))/getRPMMaximo();
		if (rpm<getRPMMaximo()/(6-caja.getCambio()))
			NecesitoCambio = false;
	}

	/**
	*
	*Devuelve True si el Motor posee las revoluciones suficientes como para pasar al siguiente cambio.
	*
	*@see Caja
	*/
	public boolean necesitaCambio() {
		return NecesitoCambio;
	}

	/**
	*
	*Notifica al Motor que quiero pasar un Cambio.
	*
	*@see Caja
	*/
	public void nuevoCambio() {
		disminuiRPM(getRPM()/2); //bajo las revoluciones a la mitad				
	}

	/**
	*
	*Detiene al Motor.
	*/
	public void detenerse() {
				
	}

}
