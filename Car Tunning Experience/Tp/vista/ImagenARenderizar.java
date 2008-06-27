package vista;

import java.awt.image.BufferedImage;

public class ImagenARenderizar implements Comparable<ImagenARenderizar> {

	int capa;
	BufferedImage imagen;
	int x,y;

	public ImagenARenderizar(BufferedImage imagen, int x, int y, int capa) {
		this.capa = capa;
		this.imagen = imagen;
		this.x=x;
		this.y=y;
	}

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

	public BufferedImage getImagen() {
		return imagen;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


}
