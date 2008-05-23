package auto.partesAuto;
import pista.Pista;
import auto.Auto;
import auto.PartesAuto;

public class Rueda extends PartesAuto {

	private int rodado;
	private double coeficienteEstatico;
	private double coeficienteDinamico;
	private Auto auto;
	private Pista pista=null;

	//TODO: Modifico Excepciones
	public Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico){
		super();
		this.auto= null;
		try {
			setRodado(rodado);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setCoeficienteEstatico(coeficienteEstatico);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setCoeficienteDinamico(coeficienteDinamico);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
	}
	
	public void setPista(Pista pista){
		this.pista=pista;
	}

	public Rueda(double peso, double costo, double desgaste, int rodado,
		double coeficienteEstatico, double coeficienteDinamico){
		try {
			setRodado(rodado);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setCoeficienteEstatico(coeficienteEstatico);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		try {
			setCoeficienteDinamico(coeficienteDinamico);
		} catch (BoundsException e) {
			e.printStackTrace();
		}
		setPeso(peso);
		setCosto(costo);
	}

	public void setRodado(int rodado) throws BoundsException{
		if(rodado<0)
			throw new BoundsException("Valor del rodado incorrecto");
		this.rodado=rodado;
	}

	public int getRodado(){
		return this.rodado;
	}

	public double getRPM(){
		return auto.getVelocidad()/rodado;
	}

	public void setCoeficienteEstatico(double coeficienteEstatico) throws BoundsException{
		if( (coeficienteEstatico < 0)||(coeficienteEstatico > 1) )
			throw new BoundsException("Valor del coeficiente estatico incorrecto.");
		this.coeficienteEstatico = coeficienteEstatico;
	}

	public double getCoeficienteEstatico(){
		if(pista==null)
			return 0;
		return this.coeficienteEstatico*pista.getCoeficienteDeRozamientoRelativo();
	}

	public double getFuerzaRozamientoEstatico(){
		return this.getCoeficienteEstatico()*auto.getPeso();
	}

	public void setCoeficienteDinamico(double coeficienteDinamico) throws BoundsException{
		if( (coeficienteDinamico < 0)||(coeficienteDinamico > 1) )
			throw new BoundsException("Valor del coeficiente dinamico incorrecto.");
		this.coeficienteDinamico = coeficienteDinamico;
	}

	public double getCoeficienteDinamico(){
		if(pista==null)
			return 0;
		return this.coeficienteDinamico*pista.getCoeficienteDeRozamientoRelativo();
	}

	public double getFuerzaRozamientoDinamico(){
		return getCoeficienteDinamico()*auto.getPeso();
	}

	public boolean calcularDesgaste(int tiempo){
		 setVidaUtil(getVidaUtil()-tiempo*getRPM()/100);
		 return desgastado();
	}

	public boolean desgastar(int tiempo) {
		 setVidaUtil(getVidaUtil()-tiempo*getRPM()/100);
		 return desgastado();
	}

	public void setAuto(Auto auto){
		this.auto = auto;
	}
}