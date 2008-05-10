package auto.partesAuto.rueda;

public class RuedaSeco extends Rueda {
// Rueda si la pista Asfalto o Tierra esta seca
	public RuedaSeco (double peso,double costo,double desgaste,int rodado,
					  double coeficienteEstatico,double coeficienteDinamico){
		
		super(peso,costo,desgaste,rodado,coeficienteEstatico,coeficienteDinamico);
	}
}
