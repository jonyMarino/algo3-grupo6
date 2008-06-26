package vista;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import programaAuto.ProgramaAuto;

public class VistaDeCostado implements VistaGrafica {
	
	BufferedImage bufferPrincipal;
	Image autoPrincipal;
	Image unArbol;
	Image unArbolNevado;
	ProgramaAuto elPrograma;
	ArrayList<Integer> listaDeArboles;
	
	public VistaDeCostado(int ancho, int alto, ProgramaAuto unPrograma){
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
	
		Graphics2D buffer = (Graphics2D) bufferPrincipal.getGraphics();
		buffer.setColor(Color.cyan);
		buffer.fillRect(0, 0, ancho, alto);
		buffer.setColor(Color.green);
		buffer.fillRect(0, alto*3/4, ancho, alto/4);

		elPrograma = unPrograma;
		
		autoPrincipal = new ImageIcon(getClass().getResource("/vista/images/"+ "UnAuto" +".gif")).getImage();
		unArbol = new ImageIcon(getClass().getResource("/vista/images/"+ "arbolito" +".gif")).getImage();
		unArbolNevado = new ImageIcon(getClass().getResource("/vista/images/"+ "arbolitonieve" +".gif")).getImage();
		
		generarVista();

	}

	private void generarVista() {
		int cantidadArboles = 5;
		int distanciaEntreArboles = (int) (elPrograma.getPista().getLongitud()/cantidadArboles);
		
		System.out.println(distanciaEntreArboles);
		
		listaDeArboles = new ArrayList<Integer>();
		
		for(int i=0; i<cantidadArboles; i++){
			listaDeArboles.add(i*distanciaEntreArboles);
		}
	}

	public int getHeight() {
		return bufferPrincipal.getHeight();
	}

	
	public BufferedImage getVista() {
		BufferedImage temporal = new  BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D temporal2D = (Graphics2D) temporal.getGraphics();
		temporal2D.drawImage(bufferPrincipal,0, 0, null);
		int inicio=0;
		int posicion =  (int) elPrograma.getUsuario().getAuto().getPosicion();
		int anchoAuto = autoPrincipal.getWidth(null);
		if(posicion > (getWidth()-anchoAuto)/2){
			inicio = posicion-getWidth()/2;
			posicion = (getWidth()-anchoAuto)/2;
		}
		dibujarArboles(temporal2D, inicio);
		temporal2D.drawImage(autoPrincipal,posicion, getHeight()*3/4-autoPrincipal.getHeight(null)*7/8, null);
		return temporal;
	}


	private void dibujarArboles(Graphics2D temporal, int posicionDeInicio) {
		System.out.println(posicionDeInicio);
		System.out.println(unArbol.getWidth(null));
		for( Integer p:listaDeArboles){
			if((p>=(posicionDeInicio-unArbol.getWidth(null))) && (p<=(posicionDeInicio+getWidth()))){
				temporal.drawImage(unArbol, p-posicionDeInicio, getHeight()*3/4-unArbol.getHeight(null)+5, unArbol.getWidth(null), unArbol.getHeight(null), null);
				//
			}
			else{

			}
		}
		
	}

	public int getWidth() {
		return bufferPrincipal.getWidth();
	}
}
