package vista;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import programaAuto.Auto;
import programaAuto.ProgramaAuto;

/**
 * Vista gráfica que muestra una representacion de la velocidad del auto principal
 * con forma de velocímetro
 * 
 * @see VistaGrafica
 *
 */

public class VistaVelocimetro  implements VistaGrafica {
	private Auto unAuto;
	private BufferedImage bufferPrincipal;
	private ProgramaAuto elPrograma;
	private BufferedImage panel;
	private BufferedImage aguja;

    /**
     *
     * Crea una nueva {@link VistaGrafica} que representa la velocidad del
     * @link{Auto} del {@link Usuario} principal en forma de un velocímetro.
     *
     * @param ancho El ancho de la vista
     * @param alto El alto de la vista
     * @param unPrograma La instancia de {@link ProgramaAuto} a la que pertenece el @link{Usuario} principal.
     *
     * @see VistaGrafica
     */
	
	public VistaVelocimetro(int ancho, int alto, ProgramaAuto unPrograma) {
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_ARGB);
		elPrograma = unPrograma;
		unAuto = elPrograma.getUsuario().getAuto();
		try {
			panel = ImageIO.read(getClass().getResource("/vista/images/"+ "velocimetro" +".gif"));
			aguja = ImageIO.read(getClass().getResource("/vista/images/"+ "aguja" +".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		superficie.drawImage(panel, 0, 0, bufferPrincipal.getWidth(), bufferPrincipal.getHeight(), null);
		AffineTransform rotacion = new AffineTransform();
		double sx = bufferPrincipal.getWidth();
		sx /= aguja.getWidth();
		double sy = bufferPrincipal.getHeight();
		sy /= aguja.getHeight();
		rotacion.scale(sx, sy);
		rotacion.rotate(Math.toRadians(unAuto.getVelocidad()*5),aguja.getWidth()/2, aguja.getHeight()/2);
		superficie.drawImage(aguja, rotacion, null);
	}


	public int getWidth() {
		return bufferPrincipal.getWidth();
	}
}
