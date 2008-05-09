package auto.parteAuto.tanque;
import combustible.Nafta;

public class TanqueNafta extends Tanque{
	
	private Nafta  tipoNafta;
	private double cantidadNafta;
	
public TanqueNafta(int capacidad){
	super(capacidad);
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
	Nafta tipoNaftaAux = this.getTipoNafta();
	double peso= this.getCantidadNafta() * tipoNaftaAux.getPesoEspecifico();
	return peso;
}

public double getCantidadNafta() {
	return cantidadNafta;
}

private void setCantidadNafta(double cantidadNafta){
	this.cantidadNafta = cantidadNafta;
}

/*********************************************************************************/
}