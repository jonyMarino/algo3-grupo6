package pruebas;

import junit.framework.TestCase;
import programaAuto.Pista;
import proveedorDeNafta.FabricaDeNafta;
import proveedorDeNafta.Nafta;
import proveedorDePartes.fabricas.Acelerador;
import proveedorDePartes.fabricas.Caja;
import proveedorDePartes.fabricas.CajaManual;
import proveedorDePartes.fabricas.Carroceria;
import proveedorDePartes.fabricas.Eje;
import proveedorDePartes.fabricas.Escape;
import proveedorDePartes.fabricas.FabricaDeCajas;
import proveedorDePartes.fabricas.FabricaDeCarrocerias;
import proveedorDePartes.fabricas.FabricaDeEjes;
import proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedorDePartes.fabricas.FabricaDeMotores;
import proveedorDePartes.fabricas.FabricaDePedales;
import proveedorDePartes.fabricas.FabricaDeRuedas;
import proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedorDePartes.fabricas.Freno;
import proveedorDePartes.fabricas.Mezclador;
import proveedorDePartes.fabricas.MezcladorNafta;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.Rueda;
import proveedorDePartes.fabricas.TanqueNafta;
import auto.AutoManual;



import excepciones.BoundsException;

public class MotorTest extends TestCase {

	Motor motor;
	Escape escape;
	Mezclador mezclador;
	TanqueNafta tanque;
	Nafta nafta;
	Caja caja;
	Rueda rueda;
	Eje eje;
	AutoManual auto;
	Carroceria carroceria;
	Pista pista;
	FabricaDeTanquesDeCombustible fabricaTanques;
	FabricaDeMezcladores fabricaMezcladores;
	FabricaDeMotores fabricaMotores;
	FabricaDeCarrocerias fabricaCarrocerias;
	FabricaDeEscapes fabricaEscapes;
	FabricaDeRuedas fabricaRuedas;
	FabricaDeCajas fabricaCajas;
	FabricaDeEjes fabricaEjes;
	FabricaDePedales fabricaPedales;
	FabricaDeNafta fabricaDeNafta;
	Acelerador acelerador;
	Freno freno;
	
	protected void setUp() throws Exception {
		super.setUp();
		pista = new Pista("UnaPista");
		fabricaTanques = new FabricaDeTanquesDeCombustible();
		fabricaMezcladores = new FabricaDeMezcladores();
		fabricaEscapes = new FabricaDeEscapes();
		fabricaMotores = new FabricaDeMotores();
		fabricaRuedas = new FabricaDeRuedas();
		fabricaCarrocerias = new FabricaDeCarrocerias();
		fabricaCajas = new FabricaDeCajas();
		fabricaEjes = new FabricaDeEjes();
		fabricaPedales = new FabricaDePedales();
		fabricaDeNafta = new FabricaDeNafta();
		
		nafta = fabricaDeNafta.fabricar(fabricaDeNafta.getTipos().get(0));
		
		tanque = fabricaTanques.fabricar(fabricaTanques.getModelos().get(0));
		tanque.setCombustible(nafta);
		tanque.llenarTanque(70);
		
		fabricaMezcladores.proponerMezclador("Mezclador 100% eficiente", 100, 50, "NAFTA");
		mezclador = fabricaMezcladores.fabricar(fabricaMezcladores.getModelos().get(1));
		mezclador.setTanqueCombustible(tanque);
		
		fabricaEscapes.proponerEscape("Escape 100% eficiente", 100, 5);
		escape = fabricaEscapes.fabricar(fabricaEscapes.getModelos().get(1));
		
		carroceria = fabricaCarrocerias.fabricar(fabricaCarrocerias.getModelos().get(0));

		rueda = fabricaRuedas.fabricar(fabricaRuedas.getModelos().get(0));//new Rueda(1,0.9,0.6);

		eje = fabricaEjes.fabricar(fabricaEjes.getModelos().get(0));
		
		eje.setRuedaTrasera(rueda);
		
		caja=fabricaCajas.fabricar(fabricaCajas.getModelos().get(0));
		caja.setEje(eje);
		
		fabricaMotores.proponerMotor("Motor 100% eficiente, 7500rpm, 2.0", 100, 500, 7500, 2.0);
		motor=fabricaMotores.fabricar(fabricaMotores.getModelos().get(1));
		motor.setEscape(escape);
		motor.setMezclador(mezclador);
		motor.setCaja(caja);

		caja.setMotor(motor);
		
		freno =  (Freno) fabricaPedales.fabricar(fabricaPedales.getModelos().get(0));
		
		auto = new AutoManual(escape, carroceria, motor, (CajaManual) caja, (MezcladorNafta) mezclador, tanque, rueda, rueda, rueda, rueda, eje, freno);
		auto.setPista(pista);
		
		rueda.setPista(pista);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		pista = null;
		nafta = null;
		tanque = null;
		mezclador = null;
		escape = null;
		carroceria = null;
		rueda = null;
		motor = null;
		caja = null;
		auto = null;
	}

	public void testAcelerar() throws BoundsException {
		auto.setCambio(1);
		assertEquals(0.0, motor.obtenerRPM());
		motor.acelerar(1);
		auto.simular(1);
		assertEquals(36600, motor.obtenerRPM(), 50);
	}

	
	public void testAcelerarDeMas() throws BoundsException {
		try{
			motor.acelerar(20);
			fail("Debería haberse generado una excepción.");
		}
		catch (BoundsException excepcion){
		}
		try{
			motor.acelerar(-90);
			fail("Debería haberse generado una excepción.");
		}
		catch (BoundsException excepcion){
		}
	}

	public void testAcelerarHastaFundir() throws BoundsException{
		auto.setCambio(1);
		int contador;
		motor.acelerar(1);

		for(contador=0;contador<900;contador++)
			auto.simular(90000);
		assertEquals(0.0, motor.getVidaUtil());
	}
	


}
