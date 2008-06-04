package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

import auto.Auto;

public class VistaGraficador extends Frame implements Observer{

	private  ArrayList<Point2D> listaDePuntos;
	private int MaximoX;
	private int MaximoY;
	private int MinimoX;
	private int MinimoY;
	private BufferedImage bufferPrincipal;
	private double pixelesPorUnidadx, pixelesPorUnidady;
	private Color colorTrazo;
	private Color colorFondo;
	private Object objetoObservado;
	private String titulo;
	
    public VistaGraficador(String Titulo, int ancho, int alto, Object objetoObservado){
    	this.show();
    	titulo = Titulo;
    	this.objetoObservado=objetoObservado;
		MaximoX = ancho;
		MaximoY=alto;
		MinimoX=0;
		MinimoY=0;
		this.setSize(MaximoX, MaximoY);
    	listaDePuntos = new ArrayList<Point2D>();
		bufferPrincipal = new BufferedImage(getMaximoX(), getMaximoY(),BufferedImage.TYPE_INT_RGB );
		//bufferSecundario = new BufferedImage(getMaximoX(), getMaximoY(),BufferedImage.TYPE_INT_RGB );
		this.pixelesPorUnidadx=1;
		this.pixelesPorUnidady=1;
		colorTrazo = Color.red;
		colorFondo = Color.black;
		//TODO: limpiar código
    }
    
    public void nuevoPunto(double delta_x, double y){
    	if (listaDePuntos.size() >= getMaximoX()/pixelesPorUnidadx){
    		Point2D.Double punto = (Double) listaDePuntos.remove(0);
    	}
    	Point2D.Double punto = new Point2D.Double(delta_x, (-y*getPixelesPorUnidady()+getMaximoY()));
    	listaDePuntos.add(punto);
    }

	public int getMaximoX() {
		return MaximoX;
	}
	
	public int getMaximoY() {
		return MaximoY;
	}
	
	
	public void dibujar(){
		Graphics2D gr = (Graphics2D)this.getGraphics();
		dibujar_buffer();
		double yMedio = (getMaximoY()+getMinimoY())/2;
		gr.drawImage(bufferPrincipal, 0, 0, getMaximoX(), getMaximoY(), 0, 0, bufferPrincipal.getWidth(), bufferPrincipal.getHeight(), null);
		
	}

	private void dibujar_buffer(){
		Graphics2D superficie = bufferPrincipal.createGraphics();
		Iterator<Point2D> iterador = listaDePuntos.iterator();
		Point2D punto;
		double pos_x = getMinimoX();
		double yMedio = -(getMaximoY()+getMinimoY())/2;
		superficie.setBackground(colorFondo);
		superficie.clearRect(0, 0, getMaximoX(), getMaximoY());
		superficie.setColor(Color.white);
		superficie.fillRect(0, -(int)yMedio-1 ,getMaximoX(), 2);
		superficie.fillRect(getMinimoX()-1, 0 ,2, getMaximoY());
		superficie.setColor(colorTrazo);
		while (iterador.hasNext()){
			punto = (Point2D) iterador.next();
			pos_x+=punto.getX()*pixelesPorUnidadx;
			superficie.drawRect((int)(pos_x-1*pixelesPorUnidadx), (int)(punto.getY()+yMedio), 1, 1);
		}
		superficie.setColor(Color.green);
		int tamanioDeLetra=(int)(getMaximoY()/7);
		superficie.setFont(new Font("Arial",Font.BOLD,tamanioDeLetra));
		superficie.drawString(titulo, (getMaximoX()-getMinimoX())/2-titulo.length()*tamanioDeLetra/4, getMaximoY()-5);

	}
	
	public int getMinimoX() {
		return MinimoX;
	}

	public int getMinimoY() {
		return MinimoY;
	}

	public Color getColorTrazo() {
		return colorTrazo;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o==objetoObservado)
		{
			nuevoPunto(1,((Auto)o).getVelocidad());
			dibujar();
		}
	}

	public double getPixelesPorUnidadx() {
		return pixelesPorUnidadx;
	}

	public void setPixelesPorUnidadx(double pixelesPorUnidadx) {
		this.pixelesPorUnidadx = pixelesPorUnidadx;
	}

	public double getPixelesPorUnidady() {
		return pixelesPorUnidady;
	}

	public void setPixelesPorUnidady(double pixelesPorUnidady) {
		this.pixelesPorUnidady = pixelesPorUnidady;
	}

}