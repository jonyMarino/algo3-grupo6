package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import programaAuto.ProgramaAuto;
import programaAuto.Usuario;

public class VistaLineal implements VistaGrafica {

	BufferedImage bufferPrincipal;
	BufferedImage largada;
	BufferedImage meta;
	BufferedImage auto;
	ArrayList<BufferedImage> arboles;
	ProgramaAuto elPrograma;
	ArrayList<Point2D> listaDeArboles;
	private ArrayList<Usuario> competidores;

    /**
     * Crea una nueva {@link VistaLineal}, con los parámetros dados.
     *
     * @param ancho El ancho de la Vista.
     * @param alto  El alto de la vista.
     * @param unPrograma La instancia de {@link ProgramaAuto} a la cual se quiere representar.
     *
     * @see ProgramaAuto
     */

	public VistaLineal(int ancho, int alto, ProgramaAuto unPrograma){
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_ARGB);


		meta = largada = new BufferedImage(alto, alto, BufferedImage.TYPE_INT_ARGB);
				
		elPrograma = unPrograma;
		try {
			BufferedImage auxiliar = ImageIO.read(getClass().getResource("/vista/images/"+ "bandera" +".gif"));
			meta.createGraphics().drawImage(auxiliar, 0, 0, alto, alto, null);			
		} catch (IOException e) {
			e.printStackTrace();
		}


		competidores = new ArrayList<Usuario>();

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
		actualizar();
		return bufferPrincipal;
	}
	
	private void actualizar() {
		Graphics2D superficie = bufferPrincipal.createGraphics();
		superficie.setBackground(new Color(0,0,0,0));
		superficie.clearRect(0, 0, getWidth(), getHeight());
		superficie.setColor(Color.red);
		int medio = bufferPrincipal.getHeight()/2;
		superficie.drawLine(0, medio, bufferPrincipal.getWidth(), medio);
		superficie.drawImage(largada, 0, 0, null);
		superficie.drawImage(meta, bufferPrincipal.getWidth()-meta.getWidth(), 0, null);
		double factorDePosicion = bufferPrincipal.getWidth()/elPrograma.getPista().getLongitud();
		superficie.setColor(Color.DARK_GRAY);
		for(Usuario p:competidores){
			if(p.getAuto() != elPrograma.getUsuario().getAuto())
				superficie.fillOval((int) (p.getAuto().getPosicion()*factorDePosicion), medio/2, medio, medio);
		}
		superficie.setColor(Color.yellow);
		superficie.fillOval((int) (elPrograma.getUsuario().getAuto().getPosicion()*factorDePosicion), medio/2, medio, medio);
	}


	public int getWidth() {
		return bufferPrincipal.getWidth();
	}

}
