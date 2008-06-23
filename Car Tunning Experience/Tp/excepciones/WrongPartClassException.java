package excepciones;

public class WrongPartClassException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public WrongPartClassException(String string){
		super(string);
	}
}
