package proveedorDePartes.fabricas;


/**
 * La Caja transforma (se podría decir que lo "amplifica") el torque que genera el {@link Motor}.
 * La transformación del torque depende del cambio.
 * Caja es una clase abstracta que sirve como base para distintos tipos de Caja.
 * @see PartesAuto
 * @see Motor
 * @see CajaManual
 * @see CajaAutomatica
 *
 */
public abstract class Caja extends ParteAuto implements Torqueador{

	private int cambio;
	private double[] relaciones;
	private Motor motor;
	private Eje eje;

	Caja(){
		int i;
		double reduccionCambio=3.0/4;
		cambio=1; //empieza en punto muerto
		//relaciones 
		//double[] relaciones = {-30, 0, 50};
		//this.relaciones = relaciones;
		relaciones = new double[7];
		relaciones[0]=-24;
		relaciones[1]=0;
		for(i=2;i<5;i++){
			relaciones[i]=24*reduccionCambio;
			reduccionCambio*=reduccionCambio;
		}
		relaciones[5]=relaciones[4]*30/45;
		relaciones[6]=relaciones[5]*3/4;
//		for(int i=0;i<6;i++){
		
//			relaciones[i]=30-i*5;
//		}
	}

	protected double convertir(double torque){
			return torque*relaciones[cambio];
	}
	public abstract double getTorque();

	protected void setCambio(int cambio) {
		if((cambio>=-1) && (cambio<=5))
		this.cambio=cambio+1; //La primer posicion del array (0) es reversa (-1)
		//TODO: NO SE DEBERÍA PODER PASAR AL PROXIMO CAMBIO SI LAS REVOLUCIONES NO SON SUFICIENTES
	}

	public void setMotor(Motor motor){
		this.motor = motor;
	}
	public Motor getMotor(){
		return motor;
	}

	public void setEje(Eje eje){
		if(eje==null)
			throw new RuntimeException();
		this.eje=eje;
		eje.addTorqueador(this);
	}
	public Eje getEje(){
		return eje;
	}

	public int getCambio() {
		return cambio-1;  ////La primer posicion del array (0) es reversa (-1)
	}
	
	
	//TODO:Agregue esto q puede servir para calcular las revoluciones necesarias para cada cambio.
	public double calcularRpmMinimas(){
		return (motor.getRPMMaximo()/5)*cambio;
	}
	
	protected void incCambio(){
		if(cambio<=5)
			if(obtenerRpmEntrada()>=calcularRpmMinimas())
			cambio++;
		//TODO: MANEJAR CON EXCEPCIONES
	}

	public double obtenerRpmEntrada(){
		if(eje==null)
			return 0;
		if (cambio==1)
			return 0;
		return eje.getRpm()*relaciones[cambio];
	}

}