package auto.partesAuto.motor;

import auto.ParteAuto;
import auto.partesAuto.mezclador.Mezclador;

public class Motor extends ParteAuto {
	private long rpmMaximo;
	private int rendimiento;
	private double cilindrada;
	Mezclador mezclador;
	int cambio;
	
	public Motor(int rendimiento, long rpmMaximo, Mezclador mezclador){
		setRendimiento(rendimiento);
		setRPMMaximo(rpmMaximo);
		this.mezclador = mezclador;
	}
	
	private void setRendimiento(int rendimiento){
		if (rendimiento>100)
			this.rendimiento=100;
		else if (rendimiento < 0)
			this.rendimiento=0;
		else this.rendimiento=rendimiento;
	}

	private void setRPMMaximo(long rpmMaximo){
		if (rpmMaximo>100)
			this.rpmMaximo=100;
		else if (rpmMaximo < 0)
			this.rpmMaximo=0;
		else this.rpmMaximo=rpmMaximo;
	}
	
	public acelerar(){
		
	}
	
	public obtenerRPM(){
	}
}
