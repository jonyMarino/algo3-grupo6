package vista;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import programaAuto.ProgramaAuto;

public class VistaDeCostado implements VistaGrafica {
	
	BufferedImage bufferPrincipal;
	Image autoPrincipal;
	Image unArbol;
	Image unArbolNevado;
	ProgramaAuto elPrograma;
	
	public VistaDeCostado(int ancho, int alto, ProgramaAuto unPrograma){
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
	
		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();
		buffer.setColor(Color.cyan);
		buffer.fillRect(0, 0, ancho, alto);
		buffer.setColor(Color.green);
		buffer.fillRect(0, alto*3/4, ancho, alto/4);

		elPrograma = unPrograma;
		
		autoPrincipal = new ImageIcon(getClass().getResource("/vista/images/"+ "UnAuto" +".gif")).getImage();
		//unArbol = new ImageIcon(getClass().getResource("/vista/images/"+ "arbolito" +".gif")).getImage();
		//unArbolNevado = new ImageIcon(getClass().getResource("/vista/images/"+ "arbolitonevado" +".gif")).getImage();
		
		generarVista();

	}

	private void generarVista() {
		///elPrograma.getPista().getLongitud()
	}

	public int getHeight() {
		return bufferPrincipal.getHeight();
	}

	
	public BufferedImage getVista() {
		BufferedImage temporal = new  BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D temporal2D = (Graphics2D) temporal.getGraphics();
		temporal2D.drawImage(bufferPrincipal,0, 0, null);
		temporal2D.drawImage(autoPrincipal,(int) elPrograma.getUsuario().getAuto().getPosicion(), getHeight()*3/4-autoPrincipal.getHeight(null)*7/8, null);
		return temporal;
	}


	public int getWidth() {
		return bufferPrincipal.getWidth();
	}
}
