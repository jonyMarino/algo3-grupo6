package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import programaAuto.Auto;
import programaAuto.ProgramaAuto;

/**
 * Vista gráfica que muestra una representacion de la carrera con texto.
 * 
 * @see VistaGrafica
 *
 */

public class VistaCarreraInformativa  implements VistaGrafica {
	private ArrayList<String> listaDeTexto;
	private Auto unAuto;
	private BufferedImage bufferPrincipal;
	private ProgramaAuto elPrograma;
	

    /**
     * Crea una nueva {@link VistaGrafica} que muestra una representacion de
     * algunos parametros del {@link Auto} del @{link Usuario} principal, como ser
     * la velocidad, las RPM del {@link Motor}, la distancia recorrida, etc., con texto.
     *
     * @param ancho El ancho de la vista
     * @param alto El alto de la vista
     *
     * @see VistaGrafica
     */

	public VistaCarreraInformativa(int ancho, int alto, ProgramaAuto unPrograma) {
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_ARGB);
		elPrograma = unPrograma;
		unAuto = elPrograma.getUsuario().getAuto();
		listaDeTexto = new ArrayList<String>();
	}
	
	private void dibujarMensajes(){
		Graphics2D superficie = bufferPrincipal.createGraphics();
		superficie.setBackground(new Color(0,0,0,0));
		superficie.clearRect(0, 0, getWidth(), getHeight());
		superficie.setColor(Color.black); //colorMensajes);
		superficie.setFont(new Font("Arial",Font.BOLD,12));
		Iterator<String> iteradorTexto = listaDeTexto.iterator();
		int indice=0;
		while(iteradorTexto.hasNext()){
			superficie.drawString((String)iteradorTexto.next(), 5, 9+21*indice);
			indice++;
		}
		
	}
	
	private void actualizar() {

			listaDeTexto.clear();
			listaDeTexto.add("Velocidad:        " + (int)unAuto.getKilometrosPorHora() + " Km/h");
			listaDeTexto.add("RPM:              " + (int)unAuto.getMotor().obtenerRPM());
			listaDeTexto.add("Peso:             " + unAuto.getPeso());
			listaDeTexto.add("Posicion:         " + (int)unAuto.getPosicion());
			listaDeTexto.add("Combustible:      " + unAuto.getTanqueCombustible().getCantidadCombustible());
			listaDeTexto.add("Vida util Motor:  " + unAuto.getMotor().getVidaUtil());
			listaDeTexto.add("Cambio:           " + unAuto.getCaja().getCambio());
			dibujarMensajes();
	}


	public int getHeight() {
		return bufferPrincipal.getHeight();
	}


	public BufferedImage getVista() {
		actualizar();
		return bufferPrincipal;
	}


	public int getWidth() {
		return bufferPrincipal.getWidth();
	}
}

