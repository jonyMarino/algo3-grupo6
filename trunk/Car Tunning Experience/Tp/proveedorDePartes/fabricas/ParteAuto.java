package proveedorDePartes.fabricas;

import excepciones.BoundsException;

/**
 * Es la clase que se usa como base para todas las partes del auto ({@link Carroceria}, {@link Motor}, {@link Escape}, etc).
 *
 *Define las características básicas de todas las partes, como el costo, peso, vida útil y desgaste.
 *
 */
public abstract class ParteAuto {

	private double peso;
	private double vidaUtil;
	private int costo;
	private String descripcion;
	private InformacionDelModelo informacionDelModelo;

	public ParteAuto(){
		try{
			setDescripcion("");
			setCosto(0);
			setPeso(0);
			setVidaUtil(100);
		}catch(BoundsException e){}

	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public int getCosto(){
		return costo;
	}

	public void setPeso(double peso)throws BoundsException{
		if(peso < 0)
			throw new BoundsException("Valor de peso incorrecto.");
		else this.peso=peso;

	}

	public double getPeso(){
		return this.peso;
	}

	public void setVidaUtil(double vidaUtil)throws BoundsException{
		if( (vidaUtil < 0) || (vidaUtil > 100) )
			throw new BoundsException("Valor de la vida util incorrecto");
		else this.vidaUtil=vidaUtil;
	}

	public double getVidaUtil(){
		return this.vidaUtil;
	}

	public abstract boolean desgastar(int segundosTranscurridos);

	public boolean desgastado(){
		return getVidaUtil()==0;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public InformacionDelModelo getInformacionDelModelo() {
		return informacionDelModelo;
	}

	void setInformacionDelModelo(InformacionDelModelo informacionDelModelo) {
		this.informacionDelModelo = informacionDelModelo;
	}

}