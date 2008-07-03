package vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import programaAuto.ProgramaAuto;
import programaAuto.Usuario;

/**
 * Vista gráfica que muestra una representacion de la carrera vista de costado.
 * 
 * @see VistaGrafica
 *
 */
public class VistaDeCostado implements VistaGrafica {

	BufferedImage bufferPrincipal;
	BufferedImage autoPrincipal;
	BufferedImage autoRival;
	BufferedImage unArbol;
	ArrayList<BufferedImage> arboles;
	ProgramaAuto elPrograma;
	ArrayList<Point2D> listaDeArboles;
	private ArrayList<Usuario> competidores;

    /**
     * Crea una nueva {@link VistaDeCostado}, con los parámetros dados.
     *
     * @param ancho El ancho de la Vista.
     * @param alto  El alto de la vista.
     * @param unPrograma La instancia de {@link ProgramaAuto} a la cual se quiere representar.
     *
     * @see ProgramaAuto
     */

	public VistaDeCostado(int ancho, int alto, ProgramaAuto unPrograma){
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);

		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();
		GradientPaint degrade = new GradientPaint(50.0f, 0.0f, new Color(0,115,202), 50.0f, 250.0f, new Color(171,221,230));
		buffer.setPaint(degrade);
		buffer.fillRect(0, 0, ancho, alto);
		buffer.setColor(new Color(30,80,46));

		elPrograma = unPrograma;
		try {
			autoPrincipal = ImageIO.read(getClass().getResource("/vista/images/"+ "UnAuto" +".gif"));
			autoRival = new BufferedImage(autoPrincipal.getWidth()*4/5, autoPrincipal.getHeight()*4/5, BufferedImage.TYPE_INT_ARGB);
			autoRival.createGraphics().drawImage(autoPrincipal, 0, 0, autoRival.getWidth(), autoRival.getHeight(), null);
			if(elPrograma.getPista().getNombre() == "Nieve"){
				degrade = new GradientPaint(50.0f, 0.0f, new Color(120,183,226), 50.0f, 250.0f, new Color(238,106,0));
				buffer.setPaint(degrade);
				buffer.fillRect(0, 0, ancho, alto);
				unArbol =ImageIO.read(getClass().getResource("/vista/images/"+ "arbolitonieve" +".gif"));
				buffer.setColor(Color.white);
			}
			else unArbol = ImageIO.read(getClass().getResource("/vista/images/"+ "arbolito" +".gif"));
		
			buffer.fillRect(0, alto*3/4, ancho, alto/4);			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		proyectarArboles();
		competidores = new ArrayList<Usuario>();

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

    /**
     * Opcionalmente, la {@link VistaDeCostado}, puede renderizar arboles a los costados de la {@link Pista}
     * Este mètodo sirve para asignar esa lista de arboles.
     *
     * @param lista Una lista de {@link Point2D}, que representa las posiciones de los arboles
     * @see Point2D
     */
	
	public void setArboles(ArrayList<Point2D> lista){
		listaDeArboles = lista;
	}


    /**
     * 
     *@param competidores Un {@link ArrayList} de {@link Usuario}s que contiene a todos los Usuarios que intervienen en la carrera
     *@see Usuario
     */

	public void setCompetidores(ArrayList<Usuario> competidores){
		this.competidores = competidores;
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
		int posicion =  (int) (elPrograma.getUsuario().getAuto().getPosicion()*PantallaCarrera.pixelesPorMetro);
		int anchoAuto = autoPrincipal.getWidth(null);
		if(posicion > (getWidth()-anchoAuto)/2){
			inicio = posicion-(getWidth()-anchoAuto)/2;
			posicion = (getWidth()-anchoAuto)/2;
		}
		LinkedList<ImagenARenderizar> listaDeRenderizado = new LinkedList<ImagenARenderizar>();
			
		for(Usuario p:competidores){
			if(p.getAuto() != elPrograma.getUsuario().getAuto())
				listaDeRenderizado.add(new ImagenARenderizar((BufferedImage) autoRival, (int) (p.getAuto().getPosicion()*PantallaCarrera.pixelesPorMetro-inicio), getHeight()*3/4-autoPrincipal.getHeight(null)/2, 3));
		}
		
		listaDeRenderizado.add(new ImagenARenderizar((BufferedImage) autoPrincipal,posicion, getHeight()*3/4-autoPrincipal.getHeight(null)/2, 2));
		
		agregarArboles(listaDeRenderizado, inicio);
		Collections.sort(listaDeRenderizado);
		dibujarTodo(temporal2D, listaDeRenderizado);
	}

	private void dibujarTodo(Graphics2D temporal2D, LinkedList<ImagenARenderizar> listaDeRenderizado) {
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
