package auto.partesAuto.rueda;
import auto.ParteAuto;

public class Rueda extends ParteAuto {
	
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
		this.rodado=rodado;
	}
	
	public int getRodado(){
		return this.rodado;
	}
	
	public void setCoeficienteEstatico(double coeficienteEstatico){
		this.coeficienteEstatico=coeficienteEstatico;
	}
	
	public double getCoeficienteEstatico(){
		return this.coeficienteEstatico;
	}
	
	public void setCoeficienteDinamico(double coeficienteDinamico){
		this.coeficienteDinamico=coeficienteEstatico;
	}
	
	public double getCoeficienteDinamico(){
		return this.coeficienteDinamico;
	}

}