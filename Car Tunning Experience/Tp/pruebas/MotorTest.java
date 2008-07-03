package pruebas;

import junit.framework.TestCase;
import programaAuto.Pista;
import proveedores.proveedorDeCombustibles.FabricaDeNafta;
import proveedores.proveedorDeCombustibles.Nafta;
import proveedores.proveedorDePartes.fabricas.Caja;
import proveedores.proveedorDePartes.fabricas.CajaManual;
import proveedores.proveedorDePartes.fabricas.Carroceria;
import proveedores.proveedorDePartes.fabricas.Eje;
import proveedores.proveedorDePartes.fabricas.Escape;
import proveedores.proveedorDePartes.fabricas.FabricaDeCajas;
import proveedores.proveedorDePartes.fabricas.FabricaDeCarrocerias;
import proveedores.proveedorDePartes.fabricas.FabricaDeEjes;
import proveedores.proveedorDePartes.fabricas.FabricaDeEscapes;
import proveedores.proveedorDePartes.fabricas.FabricaDeMezcladores;
import proveedores.proveedorDePartes.fabricas.FabricaDeMotores;
import proveedores.proveedorDePartes.fabricas.FabricaDePedales;
import proveedores.proveedorDePartes.fabricas.FabricaDeRuedas;
import proveedores.proveedorDePartes.fabricas.FabricaDeTanquesDeCombustible;
import proveedores.proveedorDePartes.fabricas.Freno;
import proveedores.proveedorDePartes.fabricas.Mezclador;
import proveedores.proveedorDePartes.fabricas.MezcladorNafta;
import proveedores.proveedorDePartes.fabricas.Motor;
import proveedores.proveedorDePartes.fabricas.Rueda;
import proveedores.proveedorDePartes.fabricas.TanqueNafta;
import auto.AutoManual;



import excepciones.BoundsException;
import excepciones.ModelRegisteredException;

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
		assertEquals(4671.63, motor.obtenerRPM(), 50);
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
			auto.simular(9);
		
		assertEquals(0.0, motor.getVidaUtil());
	}
	


}
