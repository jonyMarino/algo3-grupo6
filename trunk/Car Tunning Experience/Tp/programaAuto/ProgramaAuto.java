package programaAuto;

import java.util.ArrayList;
import auto.*;
import auto.partesAuto.*;
import auto.partesAuto.caja.*;
import auto.partesAuto.tanque.*;
import auto.partesAuto.mezclador.*;
import combustible.Nafta;
import excepciones.WrongPartClassException;
import pista.*;

//TODO:para mi el programaAuto es quien debe tener la lista de autos y no
//la pista.
public class ProgramaAuto {

	private ArrayList<Auto> autos;
	private Pista pista;

	public ProgramaAuto () {
		autos = new ArrayList<Auto>();
		this.pista=null;
	}

	public static Auto autoInicial(){
		Auto auto=null;
		Nafta nafta = new Nafta(85,15);
		TanqueNafta tanque = new TanqueNafta(70, nafta);
		try{
			Carroceria carroceria = new Carroceria(5,5,250);
			MezcladorNafta mezclador = new MezcladorNafta(100,tanque);
			Escape escape = new Escape(100);
			Motor motor=new Motor(100,5000,mezclador,escape,2.0);
			Rueda rueda = new Rueda(1,0.7,0.4);
			CajaManual caja = new CajaManual();
			try {
				auto = new AutoManual(escape,carroceria,motor,caja,mezclador,tanque,rueda,rueda,rueda,rueda);
			} catch (WrongPartClassException e) {
				e.printStackTrace();
			}

		}catch(BoundsException e){}

		return auto;
	}


	public void setPista(Pista pista){
		this.pista = pista;
	}

	public Pista getPista(){
		return this.pista;
	}

	public void addAuto(Auto auto)throws NullPointerException{
		if(auto==null)
			throw new NullPointerException("Referencia a auto null.");
		autos.add(auto);
	}

}
