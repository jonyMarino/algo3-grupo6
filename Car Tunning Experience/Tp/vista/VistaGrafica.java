package vista;

import java.awt.image.BufferedImage;

/**
 *  Interfaz que comparten todas las vistas gráficas.
 * 
 *
 */

public interface VistaGrafica {

	
	/**
	 * Método que devuelve una representación del estado actual de la vista.
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
