package excepciones;

public class PartAlreadyInCatalogException extends Exception {

	private static final long serialVersionUID = 1L;

	public PartAlreadyInCatalogException(String message) {
		super(message);
	}
}
