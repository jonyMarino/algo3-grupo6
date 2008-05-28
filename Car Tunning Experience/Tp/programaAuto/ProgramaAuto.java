package programaAuto;

import auto.*;
import auto.partesAuto.*;
import auto.partesAuto.caja.*;
import auto.partesAuto.tanque.*;
import auto.partesAuto.mezclador.*;
import combustible.Nafta;
import pista.*;

//TODO:esta en proceso todavia, se que falta completar, pero por ahora esta lo que
//se necesita para el test taller.
public class ProgramaAuto {
	
	private Auto auto;
	private Pista pista;
	
	public ProgramaAuto () {
		this.auto=null;
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
			auto = new AutoManual(escape,carroceria,motor,caja,mezclador,tanque,rueda,rueda,rueda,rueda);

		}catch(BoundsException e){}
			
		return auto;
	}
	
	public void setAuto(Auto auto){
		this.auto = auto;
	}

	public Auto getAuto(){
		return this.auto;
	}

	public void setPista(Pista pista){
		this.pista = pista;
	}

	public Pista getPista(){
		return this.pista;
	}

}
