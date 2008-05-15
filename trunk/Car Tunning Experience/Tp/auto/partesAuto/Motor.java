package auto.partesAuto;

import auto.PartesAuto;
import auto.partesAuto.BoundsException;
import auto.partesAuto.caja.Caja;
import auto.partesAuto.mezclador.Mezclador;

/**
 * Es el encargado del proceso de combusti�n del {@link Combustible}, que obtiene a traves del {@link Mezclador}.
 * A partir del proceso de combusti�n genera un torque que luego pasa por la {@link Caja}.
 * @see PartesAuto
 * @see Mezclador
 * @see Combstible
 * @see Caja
 *
 */
public class Motor extends PartesAuto implements Torqueador{
	
	private double rpmMaximo;
	private int rendimiento;
	private double cilindrada;
	private Mezclador mezclador;
	private Caja caja;
	private Escape escape;
	private double aceleracion;

	public Motor(int rendimiento, double rpmMaximo, Mezclador mezclador, Escape escape,double cilindrada)throws BoundsException{
		super();
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		this.mezclador = mezclador;
		this.escape=escape;
		this.caja=null;
		this.cilindrada=cilindrada;
		aceleracion=0;
	}

	private void setRendimiento(int rendimiento)throws BoundsException{
		if (rendimiento>100 || rendimiento < 0)
			throw new BoundsException();
		else this.rendimiento=rendimiento;
	}

	private void setRPMMaximo(double rpmMaximo)throws BoundsException{
		if (rpmMaximo < 0)
			throw new BoundsException();
		this.rpmMaximo=rpmMaximo;
	}

	/**
	 * Pide {@link Combustible} mezclado con aire al {@link Mezclador} y luego realiza la combusti�n eliminando los gases por el {@link Escape}.
	 * // ConstMarino: A partir de la fuerza generada, el auto aumenta las RPM de las Ruedas, por ende del Motor tambien.
	 *
	 * @param acelerar No se refiere a la m�gnitud f�sica, sino a un n�mero de 0 a 1  que indica cuanto se presion� el {@link Acelerador}.
	 */
	public void acelerar(double acelerar)throws BoundsException{
		if(getVidaUtil()>0){
			if (acelerar>1 || acelerar < 0)
				throw new BoundsException();
			this.aceleracion=acelerar;
		}else
			this.aceleracion=0;
	}

	public boolean desgastar(int tiempo) {	
	    double deltaVidaUtil=obtenerRPM()/getRPMMaximo()/200*tiempo;
		setVidaUtil(getVidaUtil()-deltaVidaUtil);
		return getVidaUtil()==0;
	}

	private double realizarCombustion(double mezcla){
		return (evacuarGases(mezcla*getRendimiento()/100));
	}

	private double evacuarGases(double mezcla) {
		return(escape.getEficiencia()*mezcla/100);
	}

	public double obtenerRPM(){
		if(caja==null)
			return 0;
		else
			return caja.obtenerRpmEntrada();
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public double getTorque() {
		if(aceleracion==0)
			return 0;
		double torque = -1/rpmMaximo * obtenerRPM() +1; 
		double mezcla=mezclador.obtenerMezcla(aceleracion*cilindrada);	
		double energiaDeCombustion=realizarCombustion(mezcla);
		torque*=energiaDeCombustion;
		return torque;

	}

	public double getRPMMaximo() {
		return rpmMaximo;
	}

	public void setMezclador(Mezclador mezclador){
		this.mezclador = mezclador;
	}

	public void setCaja(Caja caja) {
		this.caja=caja;

	}

	public void setEscape(Escape escape) {
		this.escape=escape;
	}

}