package proveedores.proveedorDeCombustibles;

import nu.xom.Element;

/**
 * Una clase particular de {@link Combustible}.
 *
 * @see Combustible
 */
public class Nafta extends Combustible {

	private int           octanaje;
	private static double pesoEspecifico = 0.75;

	/**
	 * Crea un nuevo objeto de tipo nafta.
	 *
	 * @param octanaje El octanaje de la Nafta.
	 * @param costo El costo por litro de la Nafta.
	 */
	Nafta (int octanaje, double costo) {
		super(costo);
		this.setOctanaje(octanaje);
	}

	public Nafta(Element elemento) {
		super(elemento.getFirstChildElement("Nafta"));
		Element nafta = elemento.getFirstChildElement("Nafta");
		octanaje = Integer.parseInt(nafta.getFirstChildElement("Octanaje").getValue());
		pesoEspecifico = Double.parseDouble(nafta.getFirstChildElement("Peso_Especifico").getValue());
	}

	/**
	 * Devuelve el octanaje de la Nafta.
	 *
	 * @return octanaje de la Nafta.
	 */
	public int getOctanaje() {
		return octanaje;
	}

	/**
	 * Devuelve el pesoEspecifico de la Nafta.
	 *
	 * @return pesoEspecifico de la Nafta.
	 */
	public double getPesoEspecifico() {
		return pesoEspecifico;
	}

	private void setOctanaje(int octanaje) {
		this.octanaje = octanaje;
	}

	public Element getElement() {
		Element nafta = new Element("Nafta");
		nafta.appendChild(super.getElement());
		Element octanajeElement = new Element("Octanaje");
		octanajeElement.appendChild(octanaje+"");
		Element pesoEspecificoElement = new Element("Peso_Especifico");
		pesoEspecificoElement.appendChild(pesoEspecifico+"");
		nafta.appendChild(octanajeElement);
		nafta.appendChild(pesoEspecificoElement);
		return nafta;
	}

}