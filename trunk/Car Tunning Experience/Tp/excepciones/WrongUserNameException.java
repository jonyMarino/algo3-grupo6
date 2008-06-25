package excepciones;

public class WrongUserNameException extends Exception {
	private static final long serialVersionUID = 1L;
	public WrongUserNameException(String string) {
		super(string);
	}
}
