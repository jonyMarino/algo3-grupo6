package excepciones;

public class WrongUsername extends Exception {
	private static final long serialVersionUID = 1L;
	public WrongUsername(String string) {
		super(string);
	}
}
