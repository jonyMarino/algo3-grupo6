package excepciones;

public class TankIsFullException extends Exception {
	private static final long serialVersionUID = 1L;
	public TankIsFullException(String string) {
		super(string);
	}
}
