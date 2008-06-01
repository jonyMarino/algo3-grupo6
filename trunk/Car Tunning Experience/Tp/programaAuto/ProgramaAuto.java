package programaAuto;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import auto.*;
import auto.partesAuto.*;
import auto.partesAuto.caja.*;
import auto.partesAuto.tanque.*;
import auto.partesAuto.mezclador.*;
import combustible.Nafta;
import excepciones.WrongPartClassException;
import pista.*;


public class ProgramaAuto {

	private static ArrayList<Usuario> usuarios;
	private Pista pista;
	private final int  SEGUNDOSASIMULAR = 10;

	public ProgramaAuto () {
		this.pista=null;
	}

	public static Auto autoInicial(){
		Auto auto=null;
		Nafta nafta = new Nafta(85,15);
		TanqueNafta tanque = new TanqueNafta(70, nafta);
		usuarios = new ArrayList<Usuario>();
		try {
			tanque.llenarTanque(70);
		} catch (BoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	public void nuevoUsuario(String nombre){
		Auto unAuto = autoInicial();
		Usuario unUsuario = new Usuario(nombre, unAuto);
		usuarios.add(unUsuario);
	}
	
	
	public void comenzarCarrera(){
		java.util.Iterator<Usuario> usuarios = this.usuarios.iterator();
		
		while(usuarios.hasNext()){
			Auto unAuto = usuarios.next().getAuto();
			unAuto.simular(SEGUNDOSASIMULAR);
		}
	}
}
