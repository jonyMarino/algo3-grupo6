package excepciones;

public class NotInIndexException extends Exception{
	static final long serialVersionUID=10;
	public NotInIndexException(String string){
		super(string);
	}
}
