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

		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();

/*
		elPrograma = unPrograma;
		try {
			largada = ImageIO.read(getClass().getResource("/vista/images/"+ "UnAuto" +".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		*/
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
		return bufferPrincipal;
	}
	
	public int getWidth() {
		return bufferPrincipal.getWidth();
	}

}
