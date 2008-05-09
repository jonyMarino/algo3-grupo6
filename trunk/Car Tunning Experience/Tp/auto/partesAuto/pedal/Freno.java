package auto.parteAuto.pedal;
import auto.ParteAuto;

public class Freno extends ParteAuto implements Pedal {

	private Torque torque;

public Freno(Torque torque,double peso,double costo,double desgaste){
	super(peso,costo,desgaste);
	this.setTorque(torque); 
}

/*********************************************************************************/
public void presionar(double cantidad){
	Torque torqueAux = this.getTorque();
	torqueAux.setTorque(cantidad);
}

public Torque getTorque() {
	return torque;
}

public void setTorque(Torque torque) {
	this.torque = torque;
}	

/*********************************************************************************/
}
