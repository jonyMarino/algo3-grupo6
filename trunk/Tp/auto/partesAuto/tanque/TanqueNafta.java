package auto.partesAuto.tanque;
import combustible.Nafta;

public class TanqueNafta extends Tanque{
	
	private Nafta         tipoNafta;
	private double        cantidadNafta;
	private static double capacidad=50;
	
public TanqueNafta(){
	this.setCantidadNafta(0);
}

/*********************************************************************************/
public void setTipoCombustible(Nafta nafta) {
	this.setCantidadNafta(0);
	this.tipoNafta = nafta;
}

public Nafta getTipoNafta() {
	return tipoNafta;
}

public void llenarTanque(float litros) {
	if ((litros + this.getCantidadNafta()) <= this.getCapacidad())
		this.cantidadNafta += litros;
	else 
		this.setCantidadNafta(this.getCapacidad());
}

public double getCantidadNafta() {
	return cantidadNafta;
}

public double usarNafta(double litros){
	double naftaConsumo = 0;
	if (litros <= this.getCantidadNafta()){
		this.cantidadNafta -= litros;
		naftaConsumo = litros;
	}else{ 
		this.cantidadNafta = 0;
		naftaConsumo = this.getCantidadNafta();
	}
	return naftaConsumo;
}

public double getPeso(){
	double peso= this.getCantidadNafta() * this.tipoNafta.getPesoEspecifico();
	return peso;
}

public double getCapacidad(){
	return capacidad;
}

private void setCantidadNafta(double cantidadNafta){
	this.cantidadNafta =cantidadNafta;
}

/*********************************************************************************/
}