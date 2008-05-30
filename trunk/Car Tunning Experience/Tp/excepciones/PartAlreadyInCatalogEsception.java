package excepciones;

public class PartAlreadyInCatalogEsception extends Exception {

	private static final long serialVersionUID = 1L;

	public PartAlreadyInCatalogEsception(String message) {
		super(message);
	}
}
