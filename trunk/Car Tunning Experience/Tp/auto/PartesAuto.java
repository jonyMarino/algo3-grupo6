package auto;

import auto.partesAuto.Motor;

/**
 * Es la clase que se usa como base para todas las partes del auto ({@link Carrocería}, {@link Motor}, {@link Escape}, etc).
 *
 *Define las características básicas de todas las partes, como el costo, peso, vida útil y desgaste.
 *
 */
public abstract class PartesAuto {

	private double peso;
	private double costo;
	private double vidaUtil;

	public PartesAuto(){
		setPeso(0);
		setCosto(0);
		setVidaUtil(100);
	}

	public void setPeso(double peso){
		this.peso=peso;
	}

	public double getPeso(){
		return this.peso;
	}

	public void setCosto(double costo){
		this.costo=costo;
	}

	public double getCosto(){
		return this.costo;
	}

	public void setVidaUtil(double vidaUtil){
		if (vidaUtil<0)
			this.vidaUtil=0;
		else if (vidaUtil>100)
			this.vidaUtil=100;
		else this.vidaUtil=vidaUtil;
	}

	public double getVidaUtil(){
		return this.vidaUtil;
	}

	public abstract boolean desgastar(int tiempo);

	public boolean desgastado(){
		return getVidaUtil()==0;
	}

}