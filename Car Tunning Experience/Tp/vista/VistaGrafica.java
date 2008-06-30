package vista;

import java.awt.image.BufferedImage;

/**
 *  Interfaz que comparten todas las vistas gr�ficas.
 * 
 *
 */

public interface VistaGrafica {

	
	/**
	 * M�todo que devuelve una representaci�n del estado actual de la vista.
	 * 
	 * @return La representacion del estado actual de la vista.
	 * @see BufferedImage
	 */
	public BufferedImage getVista();
	
	/**
	 * Devuelve el ancho de la vista
	 * @return Ancho
	 */
	public int getWidth();
	
	/**
	 * Devuelve el alto de la vista
	 * @return Alto
	 */
	public int getHeight();
}
