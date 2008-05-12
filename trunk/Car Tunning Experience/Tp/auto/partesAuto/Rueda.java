package auto.partesAuto;
import auto.PartesAuto;

public class Rueda extends PartesAuto {
	
	private int rodado;
	private double coeficienteEstatico;
	private double coeficienteDinamico;
	
	public Rueda(int rodado,double coeficienteEstatico,double coeficienteDinamico){
		super();
		setRodado(rodado);
		setCoeficienteEstatico(coeficienteEstatico);
		setCoeficienteDinamico(coeficienteDinamico);
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
	
	public void setCoeficienteEstatico(double coeficienteEstatico){
		if (coeficienteEstatico < 0)
			this.coeficienteEstatico = 0;
		else if (coeficienteEstatico > 1)
			this.coeficienteEstatico = 1;
		else
			this.coeficienteEstatico = coeficienteEstatico;
	}
	
	public double getCoeficienteEstatico(){
		return this.coeficienteEstatico;
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
		return this.coeficienteDinamico;
	}

}