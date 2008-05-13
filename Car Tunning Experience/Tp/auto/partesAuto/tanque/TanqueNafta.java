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


/**
*
*Crea un nuevo TanqueNafta (vac�o) con la capacidad especificada.
*
*@param capacidad La capacidad del Tanque.
*@param tipoNafta El tipo de {@link Nafta} que almacena.
*
*/	
public TanqueNafta(int capacidad,Nafta tipoNafta){
	super(capacidad);
	this.setCantidadNafta(0);
	this.setTipoNafta(tipoNafta);
}

//TODO: Modificado se puede cambiar de tipo de Nafta
public void setTipoNafta(Nafta nafta) {
	this.setCantidadNafta(0);
	this.tipoNafta = nafta;
}

public Nafta getTipoNafta() {
	return tipoNafta;
}

/**
*
* Carga el Tanque.
*
*@param litros Los litros a cargar en el tanque.
*
*@see Combustible
*/
public void llenarTanque(float litros) {
	if (litros > 0) {
		if ((litros + this.getCantidadNafta()) <= this.getCapacidad())
			this.setCantidadNafta(getCantidadNafta()+ litros);
		else 
			this.setCantidadNafta(this.getCapacidad());
	}
}

/**
*
* Le entrega combustible a quien se lo solicite.
*
*@param litros los litros de combustible a entregar.
*@see Combustible
*/
public double usarNafta(double litros){
	double naftaConsumo = 0;
	if(this.getVidaUtil() > 0){
		if (litros <= this.getCantidadNafta() && litros > 0){
			this.cantidadNafta -= litros;
			naftaConsumo = litros;
		}else if(litros > this.getCantidadNafta()){ 
			naftaConsumo = this.getCantidadNafta();
			this.cantidadNafta = 0;
		}
	}
	return naftaConsumo;
}

/**
*
* Calcula el peso del Tanque de acuerdo a cuán lleno esté.
*
*@see PartesAuto
*@see Combustible
*/
public double getPeso(){
	Nafta tipoNaftaAux = this.getTipoNafta();
	double peso= this.getCantidadNafta() * tipoNaftaAux.getPesoEspecifico();
	return peso;
}

/**
*
*Devuelve la cantidad de {@link Combustible} que le queda.
*
*@see Combustible
*/
public double getCantidadNafta() {
	return cantidadNafta;
}

private void setCantidadNafta(double cantidadNafta){
	this.cantidadNafta = cantidadNafta;
}

public boolean desgastar(int tiempo) {
	setVidaUtil(getVidaUtil()-tiempo/1000);
	return desgastado();
}

/*********************************************************************************/
}