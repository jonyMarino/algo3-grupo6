package proveedores.proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.PartBrokenException;

/**
 * 
 * El Pedal encapsula el comportamiento y las características de un pedal.
 *
 * @see PartesAuto
 * 
 */
public abstract class Pedal extends ParteAuto {

	private boolean usado;

	Pedal() {
		this.setUsado(false);
	}

	public abstract void presionar(double intensidad) throws PartBrokenException;

	public void desgastar(double tiempo) {
		if(isUsado()){
			try{
				if(getVidaUtil()!=0){
					setVidaUtil(getVidaUtil()-tiempo/100);
				}
			}
			catch(BoundsException e){
				try{
					setVidaUtil(0);
				}catch(BoundsException f){}
			}
		}
	}

	public boolean isUsado() {
		return usado;
	}

	protected void setUsado(boolean usado) {
		this.usado = usado;
	}

}
