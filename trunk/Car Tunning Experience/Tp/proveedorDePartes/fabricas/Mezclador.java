package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.PartBrokenException;

/**
 * 
 * El Mezclador es el encargado de obtener {@link Combustible} del {@link TanqueCombustible},
 * mezclarlo y dejarlo listo para el proceso de combustión.
 *
 * @see Motor
 * @see PartesAuto
 * @see TanqueCombustible
 * @see Combustible
 * @see MezcladorNafta
 * 
 */
public abstract class Mezclador extends ParteAuto {

	private double            rendimiento;
    private TanqueCombustible tanque;

	Mezclador(int rendimiento,TanqueCombustible tanque) {
		super();
		this.setRendimiento(rendimiento);
		this.setTanqueCombustible(tanque);
	}

	public abstract double obtenerMezcla(double litrosMezcla) throws BoundsException,PartBrokenException;

	public double getRendimiento(){
		return rendimiento;
	}

	public TanqueCombustible getTanqueCombustible() {
    	return tanque;
	}

	public void setTanqueCombustible(TanqueCombustible tanque) {
	    this.tanque=tanque;
	}

	public void desgastar(double tiempo) {
		try {
			if(getVidaUtil()!=0){
				setVidaUtil(getVidaUtil()-tiempo/1000);
				setRendimiento(getRendimiento()-tiempo/1000);
			}
		} catch (BoundsException e) {
			try{
				setVidaUtil(0);
				setRendimiento(0);
			}catch(BoundsException f){}
		}
	}

	void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

}