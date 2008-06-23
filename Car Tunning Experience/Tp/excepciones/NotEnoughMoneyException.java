package excepciones;

public class NotEnoughMoneyException extends Exception{
	static final long serialVersionUID=10;
	public NotEnoughMoneyException(String string){
		super(string);
	}
}
