package vista;

import java.awt.image.BufferedImage;

/**
 *
 * Clase que se utiliza para relacionar una imagen con una posicion en la 
 * pantalla y una capa. Utilizado por las {@link VistaGrafica} para 
 * crear una lista de renderizado.
 *
 *@see VistaGrafica
 *@see VistaDeCostado
 */
public class ImagenARenderizar implements Comparable<ImagenARenderizar> {

	int capa;
	BufferedImage imagen;
	int x,y;

	/**
	 *
	 * Crea una nueva {@link ImagenARenderizar} con los parametros dados.
	 *
	 * @param imagen Una imagen
	 * @param x La posicion horizontal en la pantalla
	 * @param y la posicion vertical en la pantalla
	 * @param capa La capa a la cual pertenece la imagen. Sirve para determinar que objetos se dibujarán primero.
	 */
	public ImagenARenderizar(BufferedImage imagen, int x, int y, int capa) {
		this.capa = capa;
		this.imagen = imagen;
		this.x=x;
		this.y=y;
	}


	/**
	 * Devuelve el numero de capa asociado con esta ImagenARenderizar
	 *
	 *@return El numero de capa.
	 */
	public int getCapa(){
		return capa;
	}

	public int compareTo(ImagenARenderizar o) {
		if (((ImagenARenderizar)o).getCapa()<capa)
			return -1;
		else if (((ImagenARenderizar)o).getCapa()>capa)
			return 1;
			
			else return 0;
	}


	/**
	 *
	 * Devuelve la imagen asociada a este objeto.
	 *
	 * @return La imagen asociada.
	 */
	public BufferedImage getImagen() {
		return imagen;
	}

	/**
	 *Devuelve la posicion horizontal en pantalla del objeto.
	 *
	 *@return La posicion horizontal en pantalla
	 */
	public int getX() {
		return x;
	}

	/**
	 *Devuelve la posicion Vertical en pantalla del objeto
	 *
	 *@return La posicion vertical en pantalla
	 */
	public int getY() {
		return y;
	}


}
