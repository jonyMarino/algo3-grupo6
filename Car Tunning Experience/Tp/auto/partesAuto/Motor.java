package auto.partesAuto;

import auto.Auto;
import auto.PartesAuto;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;


/**
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
	//private double rpm;	// deMarino: no tiene porque acumularlo el motor, es funcion del auto
	private double cilindrada;	// // deMarino: acercamiento con el modelo real
	private Mezclador mezclador;
	private Caja caja;
	private Auto auto;
	//private boolean NecesitoCambio;//// deMarino: no sigue el modelo que planteamos, Si es por la caja automática usar herencia
	private Escape escape;
	//private double temperatura;
	private double aceleracion; // deMarino: tenemos que guardar la aceleracion para ir cambiando el torque a medida que lo pedimos
	
	public Motor(int rendimiento, double rpmMaximo, Mezclador mezclador, Escape escape, Caja caja,Auto auto){
		super();
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		this.mezclador = mezclador;
		this.escape=escape;
		this.caja=caja;
		this.auto = auto;
		aceleracion=0;
//		rpm=0;
//		temperatura=0;
	}
	
	private void setRendimiento(int rendimiento){
		if (rendimiento>100 || rendimiento < 0)
			throw new BoundsException;
		else this.rendimiento=rendimiento;
	}

	private void setRPMMaximo(double rpmMaximo){
		if (rpmMaximo < 0)
			throw new BoundsException;
		this.rpmMaximo=rpmMaximo;
	}
	
	/**
	 * Pide {@link Combustible} mezclado con aire al {@link Mezclador} y luego realiza la combustión eliminando los gases por el {@link Escape}.
	 * // ConstMarino: A partir de la fuerza generada, el auto aumenta las RPM de las Ruedas, por ende del Motor tambien.
	 * 
	 * @param acelerar No se refiere a la mágnitud física, sino a un número de 0 a 1  que indica cuanto se presionó el {@link Acelerador}.  
	 */
	public void acelerar(double acelerar)throws BoundsException{  //acelerar [0..1]
		double mezcla;
		if(getVidaUtil()>0){
			if (aceleracion>1 || aceleracion < 0)
				throw new BoundsException;	//deMarino: Creo que tiene mas sentido ya que los otros valores no son validos
		this.aceleracion=acelerar;
		/* deMarino: Lo siguiente lo tiene que hacer el metodo llamado por Torque
		 * 
		 * mezcla=mezclador.obtenerMezcla(aceleracion);
		aumentarRpm(realizarCombustión(mezcla));
		actualizarVidaUtil();
		}
		else disminuiRPM(2.0);
		*/
	}
	
	private void actualizarVidaUtil(double tiempo) {	//deMarino: Nico quiere que dependa del tiempo.
	    double deltaVidaUtil=obtenerRPM()/getRPMMaximo()/200*tiempo;
		setVidaUtil(getVidaUtil()-deltaVidaUtil);
	}

	private double realizarCombustion(double mezcla){
		return (evacuarGases(mezcla*getRendimiento()/100));
	}
	
	private double evacuarGases(double mezcla) {
		return(escape.getEficiencia()*mezcla/100);
	}

	public double obtenerRPM(){
		return auto.obtenerRpm(); 
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public double getTorque() {	//deMarino: Es llamado por Caja. Esto es un cambio a lo que habiamos planteado originalmente
		if(aceleracion==0)
			return 0;
		double torque = -1/rpmMaximo * obtenerRPM() +1; // es una función lineal de imagen [0..1] (En realidad con un rebaje se puede hacer negativo)
		double mezcla=mezclador.obtenerMezcla(aceleracion*cilindrada);	//deMarino: No importa si es mayor que 1
		double energiaDeCombustion=realizarCombustion(mezcla);
		torque*=energiaDeCombustion; 
		//temperatura += obtenerRPM()/getRPMMaximo()+ temperatura;	//deMarino: Me parece mejor no modelar la temperatura, nunca lo hablamos
		return torque;

	}

	public double getRPMMaximo() {
		return rpmMaximo;
	}

	/* deMarino : El motor no tiene pq enterarse directamente de esto, la caja disminuye la fuerza y por ende tambien lo haran las rpm
	public void nuevoCambio() {
		disminuiRPM(obtenerRPM()/2); //bajo las revoluciones a la mitad				
	}
	*/

	public void detenerse() {
				
	}
}
