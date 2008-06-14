package excepciones;

public class BoundsException extends Exception{
	static final long serialVersionUID=10;
	public BoundsException(){
		super();
	}
	public BoundsException(String string){
		super(string);
	}
}
