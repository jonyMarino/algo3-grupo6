package proveedorDePartes.fabricas;
import java.awt.Color;

import nu.xom.Element;

import programaAuto.Auto;

import excepciones.BoundsException;



/**
 * La parte exterior del {@link Auto}.
 * Una carrocería aerodinámica y liviana puede ayudar mucho a aumentear
 * la velocidad del auto, ya que la oposición que presente al viento será menor.
 *
 *@see PartesAuto
 *@see Auto
 */
public class Carroceria extends ParteAuto{

	private Color  color;
	private double volumen;
	private int    aeroDinamia;
	private Auto   auto;

	Carroceria(double volumen,int aeroDinamia,double peso)throws BoundsException{
		super();
		try {
			setVolumen(volumen);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setAeroDinamia(aeroDinamia);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setColor(0,0,0);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		this.setPeso(peso);
		
		this.setAuto(null);
	}



	void setColor(int R,int G,int B)throws BoundsException{
		if (R<0 || R>255)
			throw new BoundsException("Valor de R incorrecto.");
		if( G<0 || G>255)
			throw new BoundsException("Valor de G incorrecto.");
		if(B<0 || B>255)
			throw new BoundsException("Valor de B incorrecto.");
		this.color=new Color(R,G,B);
	}

	public Color getColor(){
		return this.color;
	}

	void setVolumen(double volumen)throws BoundsException{
		if(volumen<0)
			throw new BoundsException("Valor de volumen: "+volumen+" incorrecto.");
		this.volumen=volumen;
	}

	public double getVolumen(){
		return this.volumen;
	}

	void setAeroDinamia(int aeroDinamia)throws BoundsException{
		if(aeroDinamia<0)
			throw new BoundsException("Valor de aerodinamia incorrecto.");
		this.aeroDinamia=aeroDinamia;
	}

	public double getAeroDinamia(){
		return this.aeroDinamia;
	}

	public void desgastar(double tiempo){
		if(this.getVidaUtil() != 0) {
			try {
				setVidaUtil(this.getVidaUtil()-(tiempo/100));
			} catch (BoundsException e) {
				e.printStackTrace();
			}
		} else {
			try {
				setVidaUtil(0);
			} catch (BoundsException e) {
				e.printStackTrace();
			}
		}	
	}

	public double getFuerzaAire(){
		double velocidadRespectoDelViento = auto.getVelocidad() + auto.getPista().getVelocidadAire(); 
		if (this.getVidaUtil()>0)
			return (velocidadRespectoDelViento / getAeroDinamia());
		else 
			return velocidadRespectoDelViento;
	}
	
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

}
