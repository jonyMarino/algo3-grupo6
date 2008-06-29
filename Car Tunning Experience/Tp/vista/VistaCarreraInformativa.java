package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import programaAuto.ProgramaAuto;

import auto.Auto;

public class VistaCarreraInformativa  implements VistaGrafica {
	private ArrayList listaDeTexto;
	private Auto unAuto;
	private BufferedImage bufferPrincipal;
	private ProgramaAuto elPrograma;
	
	public VistaCarreraInformativa(int ancho, int alto, ProgramaAuto unPrograma) {
		bufferPrincipal = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_ARGB);
		elPrograma = unPrograma;
		unAuto = elPrograma.getUsuario().getAuto();
		listaDeTexto = new ArrayList<String>();
	}
	
	private void dibujarMensajes(){
		Graphics2D superficie = bufferPrincipal.createGraphics();
		superficie.setBackground(Color.white);
		superficie.clearRect(0, 0, getWidth(), getHeight());
		superficie.setColor(Color.black); //colorMensajes);
		superficie.setFont(new Font("Arial",Font.BOLD,12));
		Iterator iteradorTexto = listaDeTexto.iterator();
		int indice=0;
		while(iteradorTexto.hasNext()){
			superficie.drawString((String)iteradorTexto.next(), 5, 9+21*indice);
			indice++;
		}
		
	}
	
	public void actualizar() {

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
		// TODO Auto-generated method stub
		return bufferPrincipal.getHeight();
	}


	public BufferedImage getVista() {
		// TODO Auto-generated method stub
		actualizar();
		return bufferPrincipal;
	}


	public int getWidth() {
		// TODO Auto-generated method stub
		return bufferPrincipal.getWidth();
	}
}
