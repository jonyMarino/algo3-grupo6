package proveedorDePartes.fabricas;

import excepciones.BoundsException;
import excepciones.WheelPunctureException;
import programaAuto.Auto;
import programaAuto.Pista;

/**
*
* Un rueda es necesaria para el desplazamiento del auto, debido a su interaccion con la superficie.
*
* @see Eje
* @see ParteAuto
*
*/

public class Rueda extends ParteAuto{

	private int rodado;
	private double radioEnMetros;
	private double coeficienteEstatico;
	private double coeficienteDinamico;
	private Auto auto;
	private Pista pista=null;

	Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico) {
		super();
		this.auto= null;
		try {
			setRodado(rodado);
			setCoeficienteEstatico(coeficienteEstatico);
			setCoeficienteDinamico(coeficienteDinamico);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
	}

	Rueda(double peso, double desgaste, int rodado,	double coeficienteEstatico, double coeficienteDinamico) {
			try {
				setRodado(rodado);
				setCoeficienteEstatico(coeficienteEstatico);
				setCoeficienteDinamico(coeficienteDinamico);
				setPeso(peso);
			} catch (BoundsException e) {
				e.printStackTrace();
			}
		}

	public double getRadioEnMetros() throws WheelPunctureException {
		if(getVidaUtil() > 0)
			return radioEnMetros;
		else
			throw new WheelPunctureException("La Rueda se pincho");
	}
	
	public void setPista(Pista pista) {
		this.pista=pista;
	}

	void setRodado(int rodado) throws BoundsException {
		if(rodado<0)
			throw new BoundsException("Valor del rodado incorrecto");
		this.rodado=rodado;
		radioEnMetros = rodado * 2.54 /100/2;
	}

	public int getRodado(){
		return this.rodado;
	}
	
	private double convertirAMetrosPorMinuto(double metrosPorSegundo) {
		return metrosPorSegundo*60;
	}
	
	public double getRPM() throws WheelPunctureException {
		if(getVidaUtil() > 0)
			return convertirAMetrosPorMinuto(auto.getVelocidad())/(radioEnMetros * 2 * Math.PI);
		else
			throw new WheelPunctureException("La Rueda se pincho");
	}

	void setCoeficienteEstatico(double coeficienteEstatico) throws BoundsException {
		if( (coeficienteEstatico < 0)||(coeficienteEstatico > 1) )
			throw new BoundsException("Valor del coeficiente estatico incorrecto.");
		this.coeficienteEstatico = coeficienteEstatico;
	}

	public double getCoeficienteEstatico() {
		if(pista==null)
			return 0;
		return this.coeficienteEstatico*pista.getCoeficienteDeRozamientoRelativo();
	}

	public double getFuerzaRozamientoEstatico() {
		return this.getCoeficienteEstatico()*auto.getPeso();
	}

	void setCoeficienteDinamico(double coeficienteDinamico) throws BoundsException {
		if( (coeficienteDinamico < 0)||(coeficienteDinamico > 1) )
			throw new BoundsException("Valor del coeficiente dinamico incorrecto.");
		this.coeficienteDinamico = coeficienteDinamico;
	}

	public double getCoeficienteDinamico() {
		if(pista == null)
			return 0;
		else
			return coeficienteDinamico*pista.getCoeficienteDeRozamientoRelativo();
	}

	public double getFuerzaRozamientoDinamico() {
		return getCoeficienteDinamico()*auto.getPeso();
	}

	//TODO: hayy q modificar la cuenta, ahora no se desgasta practicamente nada
	public void desgastar(double tiempo) {
		try{
			if(getVidaUtil()!=0){
				double deltaVidaUtil = (getRPM()/50000000) * (tiempo*1000);
				setVidaUtil(getVidaUtil()-Math.abs(deltaVidaUtil));
			}
		}catch(BoundsException e){
			try{
				setVidaUtil(0);
			}catch(BoundsException f){}
		} catch (WheelPunctureException e) {
			try {
				setVidaUtil(0);
			} catch (BoundsException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
}