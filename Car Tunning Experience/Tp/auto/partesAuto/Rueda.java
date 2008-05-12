package auto.partesAuto;
import auto.PartesAuto;
import auto.partesAuto;
public class Rueda extends PartesAuto {
	
	private int rodado;
	private double rpm;
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

	public void setCoeficienteEstatico(double coeficienteEstatico, Pista pista){
		
		this.coeficienteEstatico = this.getPeso()*pista.getCoeficienteDeRozamientoRelativo(); 

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
	
	public double getRPM(){
		return rpm; 
	}
	
	private void setRPM(double rpm){
		this.rpm=rpm; 
	}
	
	public double calcularDesgaste(int tiempo){
		return (tiempo*getRPM())*getDesgaste();	
	}

}