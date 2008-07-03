package proveedores;

import java.util.Hashtable;

import excepciones.ModelRegisteredException;

public abstract class RegistroProducto {

	private Hashtable<String,InformacionDeProducto> tablaRegistros = new Hashtable<String,InformacionDeProducto>();



	public RegistroProducto() {
		super();
	}

	protected void registrar(InformacionDeProducto informacion) {
		String nombre = informacion.getNombre();
		if(tablaRegistros.containsKey(nombre))
			throw new ModelRegisteredException();
		tablaRegistros.put(nombre,informacion);
	}

	public String[] obtenerRegistrados() {
		return (String[])tablaRegistros.keySet().toArray();
	}

	protected InformacionDeProducto getInformacion(String modeloRegistrado) {
		return tablaRegistros.get( modeloRegistrado);
	}

}