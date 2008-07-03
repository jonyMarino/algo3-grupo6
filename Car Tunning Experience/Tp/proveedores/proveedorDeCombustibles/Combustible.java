package proveedores.proveedorDeCombustibles;

import nu.xom.Element;



/**
 *  El Combustible es necesario para que el {@link Auto} se mueva. Sin él no se podría realizar el proceso de combustión.
 *  La clase Combustible es abstracta y sirve de base a distintos tipos de Combustible.
 *  @see Motor
 *  @see TanqueCombustible
 *  @see Nafta
 *
 */
public abstract class Combustible {
	private double        costo;
	private InformacionCombustible informacionCombustible;

	/**
	 * Crea un nuevo Combustible con el costo especificado.
	 *
	 * @param costo El costo del nuevo Combustible.
	 */
	Combustible(double costo) {
		this.setCosto(costo);
	}

	public Combustible(Element elemento) {
		// TODO Auto-generated constructor stub
		Element combustible = elemento.getFirstChildElement("Combustible");
		costo = Double.parseDouble(combustible.getFirstChildElement("Costo").getValue());
		String nombreInfo=combustible.getFirstChildElement("Informacion").getValue();
		informacionCombustible = RegistroDeCombustibles.getInstance().getInformacion(nombreInfo);
	}

	/**
	 * Devuelve el peso específico del combustible.
	 *
	 * @return peso específico del combustible.
	 */
	public abstract double getPesoEspecifico();


	/**
	 * Devuelve el costo por litro del combustible.
	 *
	 * @return el costo por litro del combustible.
	 */
	public double getCosto() {
		return costo;
	}

	void setCosto(double costo) {
		this.costo = costo;
	}
	
	public InformacionCombustible getInformacionCombustible() {
		return informacionCombustible;
	}

	void setInformacionCombustible(InformacionCombustible InformacionCombustible) {
		this.informacionCombustible = InformacionCombustible;
	}

	public Element getElement() {
		Element combustible = new Element("Combustible");
		Element costoElement = new Element("Costo");
		costoElement.appendChild(costo+"");
		Element infoElement = new Element("Informacion");
		infoElement.appendChild(getInformacionCombustible().getNombre());
		combustible.appendChild(costoElement);
		combustible.appendChild(infoElement);
		return combustible;
	}

}