package proveedorDePartes.fabricas;

import programaAuto.CadenaDeFabricas;
import nu.xom.Element;
import excepciones.BoundsException;
import excepciones.NoSuchModelException;

/**
 * Es la clase que se usa como base para todas las partes del auto ({@link Carroceria}, {@link Motor}, {@link Escape}, etc).
 *
 *Define las características básicas de todas las partes, como el costo, peso, vida útil y desgaste.
 *
 */
public abstract class ParteAuto {

	private double peso;
	private double vidaUtil;
	private int costo;
	private String descripcion;
	private InformacionDelModelo informacionDelModelo;

	public ParteAuto(){
		try{
			setDescripcion("");
			setCosto(0);
			setPeso(0);
			setVidaUtil(100);
		}catch(BoundsException e){}
	}

	void setCosto(int costo) {
		this.costo = costo;
	}
	
	public int getCosto(){
		return costo;
	}

	void setPeso(double peso)throws BoundsException{
		if(peso < 0)
			throw new BoundsException("Valor de peso incorrecto.");
		else this.peso=peso;

	}

	public double getPeso(){
		return this.peso;
	}

	void setVidaUtil(double vidaUtil)throws BoundsException{
		if( (vidaUtil < 0) || (vidaUtil > 100) )
			throw new BoundsException("Valor de la vida util incorrecto");
		else this.vidaUtil=vidaUtil;
	}

	public double getVidaUtil(){
		double vidaUtilRedondeada =  Math.round(vidaUtil*Math.pow(10,4))/Math.pow(10,4);
		return vidaUtilRedondeada;
	}

	public abstract void desgastar(double segundosTranscurridos);

	public boolean desgastado(){
		return getVidaUtil()==0;
	}

	public String getDescripcion() {
		return descripcion;
	}

	void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public InformacionDelModelo getInformacionDelModelo() {
		return informacionDelModelo;
	}

	void setInformacionDelModelo(InformacionDelModelo informacionDelModelo) {
		this.informacionDelModelo = informacionDelModelo;
	}
	protected void restaurar(Element elemento){
		Element parte=elemento.getFirstChildElement("parte");
		vidaUtil=Double.parseDouble(parte.getFirstChildElement("vida util").getValue());
	} 
	
	protected Element getElement(){
		Element parte=new Element("parte");
		Element vida=new Element("vida util");
		vida.appendChild(vidaUtil+"");
		parte.appendChild(vida);
		return parte;
		
	}
	public Element getElementParte(){
		Element parteElemento = new Element("parte");
		Element modelo = new Element("modelo");
		String nombre = getInformacionDelModelo().getModelo();
		modelo.appendChild(nombre);
		Element parte = getElement();
		parteElemento.appendChild(modelo);
		parteElemento.appendChild(parte);
		return parteElemento;
	}

	public static ParteAuto recobrar(CadenaDeFabricas fabrica, Element elemento) {
		String nombreModelo = elemento.getFirstChildElement("modelo").getValue();
		InformacionDelModelo informacionDelModelo=RegistroDeModelos.getInstance().getInformacion(nombreModelo);

		ParteAuto parteAuto;
		try {
			parteAuto = fabrica.fabricar(informacionDelModelo);
			parteAuto.restaurar(elemento);
			return parteAuto;
		} catch (NoSuchModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}