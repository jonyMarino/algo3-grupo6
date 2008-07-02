package programaAuto.pruebas;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import excepciones.NoSuchModelException;
import programaAuto.ProveedorDePartes;
import proveedorDePartes.fabricas.Motor;
import proveedorDePartes.fabricas.ParteAuto;
import junit.framework.TestCase;

public class PersistenciaTest extends TestCase {
	ProveedorDePartes unProveedor=new ProveedorDePartes();
	

	
    private void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
      }
	public void testMotor() throws FileNotFoundException, Exception{
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
	
	
}
