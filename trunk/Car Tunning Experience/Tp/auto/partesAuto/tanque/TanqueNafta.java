package auto.partesAuto.tanque;
import combustible.Nafta;

/**
 * El Tanque de Nafta sirve para almacenar {@link Nafta}.
 *
 * @see Tanque
 * @see Nafta
 *
 */
public class TanqueNafta extends TanqueCombustible{

	private Nafta  tipoNafta;

	/**
	*
	*Crea un nuevo Tanque de Nafta (vacío) con la capacidad especificada.
	*
	*@param capacidad La capacidad del Tanque de Nafta
	*@param tipoNafta El tipo de {@link Nafta} que almacena.
	*
	*/
	public TanqueNafta(int capacidad,Nafta tipoNafta){
		super(capacidad);
		this.setTipoNafta(tipoNafta);
	}

	/**
	*
	* Calcula el peso del TanqueNafta de acuerdo a su cantidad de Nafta
	*
	*@see PartesAuto
	*@see Combustible
	*/
	public double getPeso(){
		Nafta tipoNaftaAux = this.getTipoNafta();
		double peso= this.getCantidadCombustible() * tipoNaftaAux.getPesoEspecifico();
		return peso;
	}

	public void setTipoNafta(Nafta nafta) {
		this.setCantidadCombustible(0);
		this.tipoNafta = nafta;
	}

	public Nafta getTipoNafta() {
		return tipoNafta;
	}


}