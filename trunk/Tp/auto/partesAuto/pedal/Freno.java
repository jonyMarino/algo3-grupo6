package auto.pedal;

public class Freno extends Pedal  {

	private PuntoDeAplicacionDeTorque puntoDeTorque;

public Freno(PuntoDeAplicacionDeTorque puntoDeTorque){
	this.puntoDeTorque= puntoDeTorque; 
}

/*********************************************************************************/
public void presionar(double cantidad){
	this.puntoDeTorque.setTorque(cantidad);
}	

/*********************************************************************************/
}
