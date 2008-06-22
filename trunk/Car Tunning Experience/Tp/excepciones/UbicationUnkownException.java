package excepciones;

public class UbicationUnkownException extends Exception {
	static final long serialVersionUID=13;
	String ubicacion;
	public UbicationUnkownException(String ubicacion){
		this.ubicacion=ubicacion;
	}
	public String getUbicacion(){
		return ubicacion;
	}
}
