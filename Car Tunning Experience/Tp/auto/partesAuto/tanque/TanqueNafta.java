package auto.partesAuto.tanque;
import combustible.Nafta;

/**
 * El tanque de nafta sirve para almacenar {@link Nafta}.
 * 
 * @see Tanque
 * @see Nafta
 *
 */
public class TanqueNafta extends Tanque{
	
	private Nafta  tipoNafta;
	private double cantidadNafta;
	
public TanqueNafta(int capacidad,Nafta tipoNafta){
	super(capacidad);
	this.setCantidadNafta(0);
	this.setTipoCombustible(tipoNafta);
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
	if ((litros + this.obtenerCantidadNafta()) <= this.getCapacidad())
		this.setCantidadNafta(obtenerCantidadNafta()+ litros);
	else 
		this.setCantidadNafta(this.getCapacidad());
}


public double usarNafta(double litros){
	double naftaConsumo = 0;
	if (litros <= this.obtenerCantidadNafta()){
		this.cantidadNafta -= litros;
		naftaConsumo = litros;
	}else{ 
		naftaConsumo = this.obtenerCantidadNafta();
		this.cantidadNafta = 0;
	}
	return naftaConsumo;
}

public double getPeso(){
	Nafta tipoNaftaAux = this.getTipoNafta();
	double peso= this.obtenerCantidadNafta() * tipoNaftaAux.getPesoEspecifico();
	return peso;
}

public double obtenerCantidadNafta() {
	return cantidadNafta;
}

private void setCantidadNafta(double cantidadNafta){
	this.cantidadNafta = cantidadNafta;
}

/*********************************************************************************/
}