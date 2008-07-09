package controlador;


import java.io.File;
import java.util.Hashtable;
import javax.swing.filechooser.*;

/**
* Implementacion de la clase abstracta FileFilter, que
* se utiliza para filtrar los archivos por ejemplo en un 
* FileChooser.
* 
* @version 1.0
*/
public class FiltroDeArchivos extends FileFilter {
	private Hashtable filtros = new Hashtable();
	private String descripcion = null;
	
	public boolean accept(File archivo) {
	     if(archivo != null) {
	         if(archivo.isDirectory()) {
	        return true;
	         }
	        String extension = getExtension(archivo);
	         if(extension != null && filtros.get(getExtension(archivo)) != null) {
	         return true;
	         };
	     }
	     return false;
     }
	
	public void addExtension(String extension) {
     	filtros.put(extension.toLowerCase(), this);
    }
	public String getDescription() {
		return descripcion;
    }	
	
	public void setDescription(String description) {
     this.descripcion = description;
    }

    
	public String getExtension(File f) {
     if(f != null) {
         String filename = f.getName();
         int i = filename.lastIndexOf('.');
         if(i>0 && i<filename.length()-1) {
         return filename.substring(i+1).toLowerCase();
        };
     }
     return null;
     }
}






