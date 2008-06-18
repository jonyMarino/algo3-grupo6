package programaAuto;

import java.util.Observer;

//PROBLEMA CON HERENCIA
public interface Observado {

	void agregarObservador(Observer obs);
	
	void sacarObservador(Observer obs);
	
	void cambie();
}
