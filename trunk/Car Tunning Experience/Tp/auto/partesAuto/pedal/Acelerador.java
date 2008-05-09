package auto.parteAuto.pedal;
import auto.ParteAuto;

public class Acelerador extends ParteAuto implements Pedal{
	
	private	Motor motor;

public Acelerador(double peso,double costo,double desgaste,Motor motor){
	super(peso,costo,desgaste);
	this.setMotor(motor);
	
}

/*********************************************************************************/
public void presionar(double cantidad){
	Motor motorAux = this.getMotor();
	motorAux.acelerar(cantidad);
}
	
public Motor getMotor() {
	return motor;
}

public void setMotor(Motor motor) {
	this.motor = motor;
}

/*********************************************************************************/
}