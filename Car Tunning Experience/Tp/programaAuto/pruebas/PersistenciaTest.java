package programaAuto.pruebas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import excepciones.WrongUserNameException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import programaAuto.Auto;
import programaAuto.ProgramaAuto;
import programaAuto.ProveedorDePartes;
import proveedores.proveedorDeCombustibles.FabricaDeNafta;
import proveedores.proveedorDeCombustibles.Nafta;
import proveedores.proveedorDeCombustibles.ProveedorDeCombustibles;
import proveedores.proveedorDePartes.fabricas.Motor;
import proveedores.proveedorDePartes.fabricas.ParteAuto;
import proveedores.proveedorDePartes.fabricas.TanqueNafta;
import junit.framework.TestCase;

public class PersistenciaTest extends TestCase {
	static ProgramaAuto prog;
	static {
		try {
			prog=new ProgramaAuto("jon",ProgramaAuto.TipoAuto.AUTOMATICO);
		} catch (WrongUserNameException e) {
			e.printStackTrace();
		}
	}
    private void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
      }
	public void testMotor() throws FileNotFoundException, Exception{
		ProveedorDePartes unProveedor=prog.getUnProveedor();

		ParteAuto parte=unProveedor.getMiCadenaDeFabricas().fabricar("General motors 2000");
		Motor motor=(Motor)parte;
		
		Element raiz = new Element("test");
		raiz.appendChild(motor.getElementParte());
    	Document doc= new Document(raiz);
    	format(System.out,doc);
    	ByteArrayOutputStream buf1= new ByteArrayOutputStream();
    	ObjectOutputStream file = new ObjectOutputStream(buf1);
		format(file,doc);
		
		ByteArrayInputStream in=new ByteArrayInputStream(buf1.toByteArray());
		Document docIn = new Builder().build(new ObjectInputStream(in));
		Motor motorRecobrado=(Motor)ParteAuto.recobrar(unProveedor.getMiCadenaDeFabricas(), docIn.getRootElement());
		
		System.out.println(motor);
		System.out.println(motorRecobrado);
		assertNotSame(motor, motorRecobrado);
		assertEquals(motorRecobrado.getClass(),motor.getClass());
		assertEquals(motorRecobrado.getPeso(),motor.getPeso());
		assertEquals(motorRecobrado.getRPMMaximo(),motor.getRPMMaximo());
		assertEquals(motorRecobrado.getDescripcion(),motor.getDescripcion());
		assertEquals(motorRecobrado.getRendimiento(),motor.getRendimiento());
		assertEquals(motorRecobrado.getVidaUtil(),motor.getVidaUtil());	
	}
	
	public void testTanque() throws FileNotFoundException, Exception{
		ProveedorDePartes unProveedor=prog.getUnProveedor();
		ProveedorDeCombustibles proveedorCombustible=prog.getUnProveedorCombustible();

		ParteAuto parte=unProveedor.getMiCadenaDeFabricas().fabricar("Tanque 70.10");
		TanqueNafta tanque=(TanqueNafta)parte;
		tanque.llenarTanque(30);
		tanque.desgastar(10000); 
		tanque.setCombustible(proveedorCombustible.obtenerCombustible("Fangio 2000"));
		
		Element raiz = new Element("test");
		raiz.appendChild(tanque.getElementParte());
    	Document doc= new Document(raiz);
    	format(System.out,doc);
    	ByteArrayOutputStream buf1= new ByteArrayOutputStream();
    	ObjectOutputStream file = new ObjectOutputStream(buf1);
		format(file,doc);
		
		ByteArrayInputStream in=new ByteArrayInputStream(buf1.toByteArray());
		Document docIn = new Builder().build(new ObjectInputStream(in));
		TanqueNafta tanqueRecobrado=(TanqueNafta)ParteAuto.recobrar(unProveedor.getMiCadenaDeFabricas(), docIn.getRootElement());
		
		System.out.println(tanque);
		System.out.println(tanqueRecobrado);
		System.out.println(tanqueRecobrado.getCantidadCombustible());
		System.out.println(tanqueRecobrado.getVidaUtil());
		
		assertNotSame(tanque, tanqueRecobrado);
		assertEquals(tanqueRecobrado.getClass(),tanque.getClass());
		assertEquals(tanqueRecobrado.getPeso(),tanque.getPeso());
		assertEquals(tanqueRecobrado.getCapacidad(),tanque.getCapacidad());
		assertEquals(tanqueRecobrado.getDescripcion(),tanque.getDescripcion());
		assertEquals(tanqueRecobrado.getCombustible().getInformacionCombustible(),tanqueRecobrado.getCombustible().getInformacionCombustible());
		assertEquals(tanqueRecobrado.getCantidadCombustible(),tanque.getCantidadCombustible());	
	}
	
	public void testAuto() throws FileNotFoundException, Exception{
		
		Auto auto = prog.autoInicial(ProgramaAuto.TipoAuto.AUTOMATICO);
		
		Element raiz = new Element("test");
		raiz.appendChild(prog.getAutosFactory().getElement(auto));
    	Document doc= new Document(raiz);
    	format(System.out,doc);
    	ByteArrayOutputStream buf1= new ByteArrayOutputStream();
    	ObjectOutputStream file = new ObjectOutputStream(buf1);
		format(file,doc);
		
		ByteArrayInputStream in=new ByteArrayInputStream(buf1.toByteArray());
		Document docIn = new Builder().build(new ObjectInputStream(in));
		Auto autoRecobrado= prog.getAutosFactory().crear(prog.getUnProveedor().getMiCadenaDeFabricas(),docIn.getRootElement());
		
		System.out.println(auto);
		System.out.println(autoRecobrado);

		
		assertNotSame(auto, autoRecobrado);
		assertEquals(autoRecobrado.getClass(),auto.getClass());
		assertEquals(autoRecobrado.getPeso(),auto.getPeso());
	}
	
	
}
