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

	public Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico,Auto auto){
		super();
		this.auto= auto;
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
	}
	
	public Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico){
		super();
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
	}
	
	public void setPista(Pista pista){
		this.pista=pista;
	}

	public Rueda(double peso, double costo, double desgaste, int rodado,
		double coeficienteEstatico, double coeficienteDinamico) {
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
		setPeso(peso);
		setCosto(costo);
	}


	public void setRodado(int rodado){
		if(rodado>0)
			this.rodado=rodado;
		else
			this.rodado=1;
	}

	public int getRodado(){
		return this.rodado;
	}

	public double getRPM(){
		return auto.getVelocidad()/rodado;
	}

	public void setCoeficienteEstatico(double coeficienteEstatico){
		this.coeficienteEstatico = coeficienteEstatico;

	}

	public double getCoeficienteEstatico(){
		if(pista==null)
			return 0;
		return this.coeficienteEstatico*pista.getCoeficienteDeRozamientoRelativo();
	}

	//TODO: agrego, modifica eje(desliz)
	public double getFuerzaRozamientoEstatico(){
		return this.getCoeficienteEstatico()*auto.getPeso();
	}

	public void setCoeficienteDinamico(double coeficienteDinamico){
		if (coeficienteDinamico < 0)
			this.coeficienteDinamico = 0;
		else if (coeficienteDinamico > 1)
			this.coeficienteDinamico = 1;
		else
			this.coeficienteDinamico = coeficienteDinamico;
	}


	public double getCoeficienteDinamico(){
		if(pista==null)
			return 0;
		return this.coeficienteDinamico*pista.getCoeficienteDeRozamientoRelativo();
	}

	//TODO: agrego, modifica eje
	public double getFuerzaRozamientoDinamico(){
		return getCoeficienteDinamico()*auto.getPeso();
	}

	public boolean calcularDesgaste(int tiempo){
		 setVidaUtil(getVidaUtil()-tiempo*getRPM()/100);
		 return desgastado();
	}

	public boolean desgastar(int tiempo) {
		 setVidaUtil(getVidaUtil()-tiempo*getRPM()/100);//TODO:consultar formula
		 return desgastado();
	}
	
	public void setAuto(Auto auto){
		this.auto = auto;		
	}
}