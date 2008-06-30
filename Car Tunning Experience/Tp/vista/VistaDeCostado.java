package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import programaAuto.ProgramaAuto;

public class VistaDeCostado implements VistaGrafica {

	BufferedImage bufferPrincipal;
	BufferedImage autoPrincipal;
	BufferedImage unArbol;
	ArrayList<BufferedImage> arboles;
	ProgramaAuto elPrograma;
	ArrayList<Point2D> listaDeArboles;

	public VistaDeCostado(int ancho, int alto, ProgramaAuto unPrograma){
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);

		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();
		buffer.setColor(Color.cyan);
		buffer.fillRect(0, 0, ancho, alto);
		buffer.setColor(Color.green);
		buffer.fillRect(0, alto*3/4, ancho, alto/4);

		elPrograma = unPrograma;
		try {
			autoPrincipal = ImageIO.read(getClass().getResource("/vista/images/"+ "UnAuto" +".gif"));
			unArbol = ImageIO.read(getClass().getResource("/vista/images/"+ "arbolito" +".gif"));
			//unArbolNevado =ImageIO.read(getClass().getResource("/vista/images/"+ "arbolitonieve" +".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		proyectarArboles();

	}

	private void proyectarArboles(){
		arboles = new ArrayList<BufferedImage>();
		arboles.add(unArbol);
		for (int i=1;i<5;i++){
			int ancho = arboles.get(i-1).getWidth();
			int alto = arboles.get(i-1).getHeight();
			BufferedImage temporal = new BufferedImage(ancho-1*ancho/5, alto-1*alto/5 , BufferedImage.TYPE_INT_ARGB );
			Graphics2D temporal2D = temporal.createGraphics();
			temporal2D.drawImage(unArbol, 0, 0, temporal.getWidth(), temporal.getHeight(), null);
			arboles.add(temporal);
		}
	}
	
	public void setArboles(ArrayList<Point2D> lista){
		listaDeArboles = lista;
	}

	public int getHeight() {
		return bufferPrincipal.getHeight();
	}

	public BufferedImage getVista() {
		BufferedImage temporal = new  BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D temporal2D = (Graphics2D) temporal.getGraphics();
		temporal2D.drawImage(bufferPrincipal,0, 0, null);

		renderizar(temporal2D);

		return temporal;
	}

	private void renderizar(Graphics2D temporal2D) {
		int inicio=0;
		int posicion =  (int) elPrograma.getUsuario().getAuto().getPosicion()*PantallaCarrera.pixelesPorMetro;
		int anchoAuto = autoPrincipal.getWidth(null);
		if(posicion > (getWidth()-anchoAuto)/2){
			inicio = posicion-(getWidth()-anchoAuto)/2;
			posicion = (getWidth()-anchoAuto)/2;
		}
		LinkedList<ImagenARenderizar> listaDeRenderizado = new LinkedList<ImagenARenderizar>();
		listaDeRenderizado.add(new ImagenARenderizar((BufferedImage) autoPrincipal,posicion, getHeight()*3/4-autoPrincipal.getHeight(null)/2, 2));
		agregarArboles(listaDeRenderizado, inicio);
		Collections.sort(listaDeRenderizado);
		dibujarTodo(temporal2D, listaDeRenderizado);
	}

	private void dibujarTodo(Graphics2D temporal2D,
			LinkedList<ImagenARenderizar> listaDeRenderizado) {
		for(ImagenARenderizar p:listaDeRenderizado){
			int y=getHeight()*3/4-p.getImagen().getHeight(null)/2;;
			if(p.getCapa()!=2)
				y=getHeight()*3/4-p.getImagen().getHeight(null)+(4-p.getCapa())*p.getImagen().getHeight(null)/7;
			
			temporal2D.drawImage(p.getImagen(), p.getX(), y, null);
		}
	}

	private void agregarArboles(LinkedList<ImagenARenderizar> listaDeRenderizado, int posicionDeInicio) {
		for( Point2D p:listaDeArboles){
			
			listaDeRenderizado.add(new ImagenARenderizar(arboles.get((int) p.getY()), (int) (p.getX()-posicionDeInicio*(5-p.getY())), (int) (getHeight()-unArbol.getHeight(null)-p.getY()*unArbol.getHeight()/4), (int) p.getY()));
		}
	}

	public int getWidth() {
		return bufferPrincipal.getWidth();
	}
}
