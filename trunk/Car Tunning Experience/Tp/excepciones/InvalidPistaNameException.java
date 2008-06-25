package excepciones;

public class InvalidPistaNameException extends Exception {
	static final long serialVersionUID=15;
	public InvalidPistaNameException(){
		super();
	}
	public InvalidPistaNameException(String string){
		super(string);
	}
}
