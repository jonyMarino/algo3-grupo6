package auto.partesAuto;
import auto.PartesAuto;
import auto.partesAuto;
import auto.Auto;
import pista.Pista;

public class Rueda extends PartesAuto {
	
	private int rodado;
//	private double rpm;	//deMarino: no tiene porque mantenerlo guardado, el valor se calcula a partir de la velocidad del auto
	private double coeficienteEstatico;
	private double coeficienteDinamico;
	private Auto auto;
	private Pista pista=null;
	public Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico,Auto auto){
		super();
		this.auto=auto;
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
	}
	public setPista(Pista pista){
		this.pista=pista;
	}
	
	public Rueda(double peso, double costo, double desgaste, int rodado,
		double coeficienteEstatico, double coeficienteDinamico) {
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
		setPeso(peso);
		setCosto(costo);
		setDesgaste(desgaste);
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

	
	public boolean calcularDesgaste(int tiempo){
		 setVidaUtil(getVidaUtil()-tiempo*getRPM()/100);	
		 return desgastado();
	}

}